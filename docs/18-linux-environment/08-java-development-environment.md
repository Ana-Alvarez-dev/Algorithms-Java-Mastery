# Java Development Environment

This repository targets Java 21. The JDK provides both `java` and `javac`; a runtime-only installation is insufficient for building. `JAVA_HOME/bin/java`, `JAVA_HOME/bin/javac` and the executables selected by PATH must agree under the applied preflight.

The check reads the runtime's `java.specification.version` and the compiler version, rather than trusting a folder name such as jdk-21. The local host initially provided Java 17; an explicitly selected OpenJDK 21 installation passes. The shell tests also reject a Java 21 runtime paired with a Java 17 compiler.

On native Linux or an existing WSL distribution, use a Linux JDK. Selecting Windows java.exe through WSL interoperability does not make it the same Linux JVM. The verifier is scoped to a Linux JDK 21 environment and the guide records this distinction.

Use `./mvnw -version` after preflight to verify Maven's actual Java runtime. The wrapper may download its pinned Maven distribution; this is deliberately outside the read-only preflight. Package installation depends on the selected distribution and is not executed by this module. Java validation remains distinct from tests, coverage and performance experiments.

## Applied verification

The [implementation and verification guide](implementation-and-verification-guide.md) connects these concepts to script contracts, commands and primary sources. The [verification report](verification-report.md) distinguishes real Linux/JDK checks from injected shell fixtures, historical research and pending author execution.
