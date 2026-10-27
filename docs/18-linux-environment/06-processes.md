# Processes

A Maven build, test JVM and JMH fork are separate processes. A JMH iteration repeatedly invokes an operation inside a worker; it is not another OS process for every algorithm call. The existing two-fork aggregation protocol creates distinct forked JVMs whose observations remain separately retained.

Inspect an ordinary Linux environment with `ps -eo pid,ppid,comm` or tools available on that machine. Process visibility and identifiers can differ inside containers and namespaces. This module's actual verification ran in a container and does not claim full host visibility, fixed power conditions or controlled CPU load.

Interrupt a foreground command you started with the terminal's Ctrl+C when needed. Investigate the selected process before applying any signal. Avoid blanket termination of all Java processes: an IDE, build and unrelated service may all use Java.

The preflight launches short Java/Javac version queries and metadata commands. It never launches Maven or a benchmark. Those queries are excluded from timed work. A successful environment check cannot establish lack of background interference, stable frequency or thread scheduling; retain those as separate experimental conditions.

## Applied verification

The [implementation and verification guide](implementation-and-verification-guide.md) connects these concepts to script contracts, commands and primary sources. The [verification report](verification-report.md) distinguishes real Linux/JDK checks from injected shell fixtures, historical research and pending author execution.
