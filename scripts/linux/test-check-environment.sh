#!/usr/bin/env bash
# Offline subprocess tests. Fake Java launchers test the shell contract, not a JVM.
set -euo pipefail
root=$(cd -P -- "${BASH_SOURCE[0]%/*}/../.." && pwd)
bash_bin=$(type -P bash)
fixture_dir=$(mktemp -d "${TMPDIR:-/tmp}/algorithms-linux-check.XXXXXX")
trap 'rm -rf -- "$fixture_dir"' EXIT
repo="$fixture_dir/repository with spaces"
mkdir -p "$repo/scripts/linux" "$fixture_dir/jdk/bin" "$fixture_dir/tools" "$fixture_dir/other-jdk/bin"
cp -- "$root/scripts/linux/check-environment.sh" "$repo/scripts/linux/"
printf '<project/>\n' > "$repo/pom.xml"
printf '#!/bin/sh\nexit 0\n' > "$repo/mvnw"
printf 'target/\n' > "$repo/.gitignore"
cat > "$fixture_dir/jdk/bin/java" <<'JAVA'
#!/usr/bin/env bash
if [[ ${TEST_LAUNCH_FAIL:-false} == true ]]; then
    printf 'private-option-value\n' >&2; exit 1
fi
if [[ ${TEST_MALFORMED:-false} == true ]]; then
    printf 'unrecognised properties\n'; exit 0
fi
printf '    java.specification.version = %s\n' "${TEST_JAVA_FEATURE:-21}"
printf '    java.runtime.version = fixture-21\n    java.vendor = Shell test double\n'
printf '    java.vm.name = Fake VM for subprocess tests\n    file.encoding = UTF-8\n'
JAVA
cat > "$fixture_dir/jdk/bin/javac" <<'JAVAC'
#!/usr/bin/env bash
printf 'javac %s.0.0\n' "${TEST_JAVAC_FEATURE:-21}"
JAVAC
cat > "$fixture_dir/tools/uname" <<'UNAME'
#!/usr/bin/env bash
case "${1-}" in
    -s) printf '%s\n' "${TEST_OS:-Linux}" ;;
    -m) printf 'fixture-architecture\n' ;;
    -r) printf 'fixture-kernel\n' ;;
    *) exit 1 ;;
esac
UNAME
chmod +x "$fixture_dir/jdk/bin/java" "$fixture_dir/jdk/bin/javac" "$fixture_dir/tools/uname"
cp -- "$fixture_dir/jdk/bin/java" "$fixture_dir/other-jdk/bin/java"
cp -- "$fixture_dir/jdk/bin/javac" "$fixture_dir/other-jdk/bin/javac"
export GIT_CONFIG_NOSYSTEM=1 GIT_CONFIG_GLOBAL=/dev/null
git -c init.defaultBranch=main init -q "$repo"
git -C "$repo" add .
git -C "$repo" -c user.name='Shell fixture' -c user.email=fixture@example.invalid -c core.hooksPath=/dev/null commit --no-gpg-sign -qm 'test fixture'
script="$repo/scripts/linux/check-environment.sh"
test_path="$fixture_dir/tools:$fixture_dir/jdk/bin:/usr/bin:/bin"
invoke() {
    local mode=$1; shift
    local args=()
    [[ -z "$mode" ]] || args+=("$mode")
    env -u JAVA_TOOL_OPTIONS -u JDK_JAVA_OPTIONS -u _JAVA_OPTIONS \
        -u TEST_LAUNCH_FAIL -u TEST_MALFORMED -u TEST_JAVA_FEATURE -u TEST_JAVAC_FEATURE -u TEST_OS \
        PATH="$test_path" JAVA_HOME="$fixture_dir/jdk" "$@" "$bash_bin" "$script" "${args[@]}"
}
count=0
run_case() {
    local name=$1 expected=$2 needle=$3; shift 3
    local output code
    set +e
    output=$("$@" 2>&1)
    code=$?
    set -e
    if [[ "$code" != "$expected" || "$output" != *"$needle"* ]]; then
        printf 'FAIL: %s (expected exit %s, got %s)\n%s\n' "$name" "$expected" "$code" "$output" >&2
        exit 1
    fi
    [[ "$output" != *private-option-value* ]] || { printf 'FAIL: option value disclosed\n' >&2; exit 1; }
    count=$((count+1))
    printf 'PASS: %s\n' "$name"
}
run_case 'clean repo and spaced paths' 0 'Working tree: clean' invoke ''
run_case 'missing optional jar' 0 'optional for environment-only check' invoke ''
run_case 'required jar absent' 2 'Benchmark jar is absent or empty' invoke --require-benchmark
run_case 'Java 17 rejected' 2 'java.specification.version = 21' invoke '' TEST_JAVA_FEATURE=17
run_case 'Javac 17 rejected' 2 'javac must report version 21' invoke '' TEST_JAVAC_FEATURE=17
run_case 'JAVA_HOME absent' 2 'JAVA_HOME must name' invoke '' JAVA_HOME=
run_case 'PATH/JAVA_HOME mismatch' 2 'select different executables' invoke '' JAVA_HOME="$fixture_dir/other-jdk"
run_case 'malformed Java properties rejected' 2 'java.specification.version = 21' invoke '' TEST_MALFORMED=true
run_case 'launcher failure does not disclose diagnostics' 2 'inspect launcher diagnostics locally' invoke '' TEST_LAUNCH_FAIL=true
run_case 'non-Linux rejected' 2 'Run this preflight in Linux Bash' invoke '' TEST_OS=Darwin
run_case 'missing tool rejected' 2 'Required tool is missing' invoke '' PATH="$fixture_dir/jdk/bin"
run_case 'unknown argument rejected' 64 'Usage:' invoke --unexpected
run_case 'help needs no successful preflight' 0 'Usage:' invoke --help JAVA_HOME=
run_case 'injected option names reported without values' 0 'JAVA_TOOL_OPTIONS: set (value withheld' invoke '' JAVA_TOOL_OPTIONS=private-option-value
run_case 'injected options rejected for benchmark capture' 2 'Benchmark preflight requires' invoke --require-benchmark JAVA_TOOL_OPTIONS=private-option-value
cd -- "$fixture_dir"
run_case 'script-relative root from another current directory' 0 "Repository: $repo" invoke ''
printf 'untracked fixture\n' > "$repo/change.txt"
run_case 'dirty state reported without discarding work' 0 'Working tree: dirty' invoke ''
mkdir -p "$repo/target"
: > "$repo/target/benchmarks.jar"
run_case 'empty jar rejected for required capture' 2 'Benchmark jar is absent or empty' invoke --require-benchmark
# Deliberately just bytes: the check hashes identity; JMH discovery validates jars later.
printf 'identity-only artifact fixture\n' > "$repo/target/benchmarks.jar"
digest=$(sha256sum -- "$repo/target/benchmarks.jar"); digest=${digest%% *}
run_case 'existing artifact hashed without execution' 0 "JAR SHA-256: $digest" invoke --require-benchmark
after=$(sha256sum -- "$repo/target/benchmarks.jar"); after=${after%% *}
[[ "$digest" == "$after" ]] || { printf 'FAIL: artifact modified\n' >&2; exit 1; }
run_case 'artifact hash is not a freshness claim' 0 'Artifact freshness/source linkage: not established' invoke --require-benchmark
printf 'PASS: %s offline shell cases. Temporary fixtures removed on exit.\n' "$count"
