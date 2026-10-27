#!/usr/bin/env bash
# Read-only Linux/JDK preflight. This script never builds or executes a jar.
set -euo pipefail

usage() {
    printf 'Usage: bash scripts/linux/check-environment.sh [--require-benchmark]\n'
    printf 'Checks Linux, Git, aligned Java/Javac 21 and optional benchmark identity.\n'
}
fail() { printf 'ERROR: %s\n' "$*" >&2; exit 2; }
require_benchmark=false
case "${1-}" in
    '') [[ $# == 0 ]] || { usage >&2; exit 64; } ;;
    --help) [[ $# == 1 ]] || { usage >&2; exit 64; }; usage; exit 0 ;;
    --require-benchmark) [[ $# == 1 ]] || { usage >&2; exit 64; }; require_benchmark=true ;;
    *) usage >&2; exit 64 ;;
esac

for tool in uname git java javac readlink sha256sum date; do
    type -P "$tool" >/dev/null || fail "Required tool is missing from PATH: $tool"
done
[[ $(uname -s) == Linux ]] || fail 'Run this preflight in Linux Bash (native, VM, container or WSL).'
root=$(cd -P -- "${BASH_SOURCE[0]%/*}/../.." && pwd)
[[ -f "$root/pom.xml" && -f "$root/mvnw" ]] || fail 'Expected pom.xml and mvnw at the script-relative repository root.'
repo=$(git -C "$root" rev-parse --show-toplevel 2>/dev/null) || fail 'The script must belong to a Git working tree.'
[[ $(cd -P -- "$repo" && pwd) == "$root" ]] || fail 'The script-relative root differs from the Git root.'
[[ -n ${JAVA_HOME-} ]] || fail 'JAVA_HOME must name the Linux JDK 21 installation.'
[[ -x "$JAVA_HOME/bin/java" && -x "$JAVA_HOME/bin/javac" ]] || fail 'JAVA_HOME must contain executable bin/java and bin/javac.'
java_path=$(readlink -f -- "$(type -P java)")
javac_path=$(readlink -f -- "$(type -P javac)")
[[ "$java_path" == "$(readlink -f -- "$JAVA_HOME/bin/java")" ]] || fail 'PATH java and JAVA_HOME/bin/java select different executables.'
[[ "$javac_path" == "$(readlink -f -- "$JAVA_HOME/bin/javac")" ]] || fail 'PATH javac and JAVA_HOME/bin/javac select different executables.'

injected=()
for variable in JAVA_TOOL_OPTIONS JDK_JAVA_OPTIONS _JAVA_OPTIONS; do
    if [[ -n ${!variable-} ]]; then
        injected+=("$variable")
        printf '%s: set (value withheld; inspect locally)\n' "$variable"
    fi
done
if "$require_benchmark" && (( ${#injected[@]} > 0 )); then
    fail 'Benchmark preflight requires JAVA_TOOL_OPTIONS, JDK_JAVA_OPTIONS and _JAVA_OPTIONS to be empty; inspect them locally without exposing values.'
fi
# Capture launcher diagnostics without echoing potentially injected option values.
properties=$("$java_path" -XshowSettings:properties -version 2>&1) || fail 'java could not report its properties; inspect launcher diagnostics locally.'
feature=''; runtime=''; vendor=''; vm=''; encoding=''
while IFS= read -r line; do
    line="${line#"${line%%[![:space:]]*}"}"
    case "$line" in
        'java.specification.version = '*) feature=${line#* = } ;;
        'java.runtime.version = '*) runtime=${line#* = } ;;
        'java.vendor = '*) vendor=${line#* = } ;;
        'java.vm.name = '*) vm=${line#* = } ;;
        'file.encoding = '*) encoding=${line#* = } ;;
    esac
done <<< "$properties"
[[ "$feature" == 21 ]] || fail 'The selected java must report java.specification.version = 21.'
compiler=$("$javac_path" -version 2>&1) || fail 'javac could not report its version; inspect diagnostics locally.'
[[ "$compiler" =~ (^|$'\n')javac[[:space:]]21([.[:space:]]|$) ]] || fail 'The selected javac must report version 21.'

printf 'Capture time (UTC): %s\n' "$(date -u '+%Y-%m-%dT%H:%M:%SZ')"
printf 'Repository: %s\n' "$root"
printf 'Source SHA: %s\n' "$(git -C "$root" rev-parse --verify HEAD)"
status=$(git --no-optional-locks -C "$root" status --porcelain=v1 --untracked-files=normal)
if [[ -n "$status" ]]; then
    printf 'Working tree: dirty (reported, not rejected)\n%s\n' "$status"
else
    printf 'Working tree: clean\n'
fi
printf 'Kernel: %s; architecture: %s\n' "$(uname -r)" "$(uname -m)"
if [[ -r /etc/os-release ]]; then
    while IFS='=' read -r key value; do
        case "$key" in PRETTY_NAME|ID|VERSION_ID) printf 'OS %s: %s\n' "$key" "$value" ;; esac
    done < /etc/os-release
fi
if [[ -r /proc/cpuinfo ]]; then
    while IFS=':' read -r key value; do
        if [[ "$key" == 'model name'* || "$key" == Hardware* ]]; then
            printf 'Visible CPU:%s\n' "$value"; break
        fi
    done < /proc/cpuinfo
fi
printf 'JAVA_HOME: %s\nJava executable: %s\nJavac executable: %s\n' "$JAVA_HOME" "$java_path" "$javac_path"
printf 'Java feature: %s; runtime: %s; vendor: %s\nVM: %s\n' "$feature" "${runtime:-unreported}" "${vendor:-unreported}" "${vm:-unreported}"
# The compiler diagnostic may contain injected options; print only its version line.
while IFS= read -r line; do
    [[ "$line" == 'javac '* ]] && printf 'Compiler: %s\n' "$line"
done <<< "$compiler"
printf 'Java file.encoding: %s\nLANG: %s; LC_ALL: %s\n' "${encoding:-unreported}" "${LANG-unset}" "${LC_ALL-unset}"
printf 'Maven version: verify separately with ./mvnw -version (wrapper may download Maven).\n'
jar="$root/target/benchmarks.jar"
if [[ -s "$jar" && -f "$jar" ]]; then
    digest=$(sha256sum -- "$jar")
    printf 'Benchmark jar: %s\nJAR SHA-256: %s\n' "$jar" "${digest%% *}"
    printf 'Artifact freshness/source linkage: not established by hashing; retain the successful clean build.\n'
else
    "$require_benchmark" && fail 'Benchmark jar is absent or empty; run ./mvnw -Pbenchmark clean package successfully before capture.'
    printf 'Benchmark jar: absent or empty (optional for environment-only check).\n'
fi
printf 'PASS: environment checks completed. No build, installation or benchmark was executed.\n'
