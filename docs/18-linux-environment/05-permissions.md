# Permissions and Line Endings

Read, write and execute permissions answer different questions. Linux requires an executable launcher when using `./mvnw`; an explicit `bash mvnw` reads the script through Bash instead. This contribution changes the tracked wrapper mode from 100644 to 100755, with its content unchanged, and publishes the two Linux scripts executable.

`.gitattributes` pins LF for `/mvnw` and `/scripts/linux/*.sh`. This targeted rule avoids CRLF-related interpreter failures across checkouts; it does not rewrite Java, historical data or Windows `mvnw.cmd`. Line endings and execute mode are distinct properties.

Inspect with `git diff --summary`, `git ls-files -s mvnw` and `git check-attr text eol -- mvnw scripts/linux/check-environment.sh`. On a filesystem that cannot represent executable bits, `bash scripts/linux/check-environment.sh` remains the explicit invocation. An actual no-exec mount can still constrain execution; do not treat `chmod` as a universal fix.

Use permissions on the required project files rather than granting broad access. This module performs no sudo or system permission changes. A hash is content identity, not an assertion about executable permission or trust.

## Applied verification

The [implementation and verification guide](implementation-and-verification-guide.md) connects these concepts to script contracts, commands and primary sources. The [verification report](verification-report.md) distinguishes real Linux/JDK checks from injected shell fixtures, historical research and pending author execution.
