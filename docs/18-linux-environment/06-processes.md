# Processes

## Algorithms Java Mastery

A computer rarely executes only one program at a time. Modern operating systems are designed to run hundreds—or even thousands—of applications simultaneously while sharing hardware resources such as the CPU, memory, storage devices, and network interfaces. In Linux, every running program is represented as a **process**.

Processes are one of the fundamental abstractions of operating systems. They allow multiple applications to execute concurrently while remaining isolated from one another. Each Java application, Maven build, Git command, Docker container, shell session, and background service executes as one or more Linux processes.

For software engineers, understanding process management is essential. Developers frequently monitor running applications, identify resource consumption, terminate unresponsive programs, inspect process hierarchies, and manage services running on development or production servers.

This document introduces the Linux process model, explains how processes are created and managed, and presents the commands most commonly used by Java developers to monitor and control application execution.

---

# 1. Purpose

The purpose of this document is to:

- introduce Linux processes;
- explain the process lifecycle;
- understand foreground and background execution;
- study process monitoring;
- establish the foundation for process management in professional software development.

---

# 2. What Is a Process?

A **process** is a program that is currently executing.

Conceptually:

```text
Program

↓

Execution

↓

Process
```

A process includes:

- executable code;
- memory;
- processor state;
- open files;
- execution context.

Every running application is represented by one or more processes.

---

# 3. Program vs Process

A program is different from a process.

### Program

```text
Stored On Disk
```

Example:

```text
java
```

---

### Process

```text
Executing In Memory
```

Conceptually:

```text
Program

↓

Run

↓

Process
```

A single program may create multiple processes.

---

# 4. Process Identifier (PID)

Every process receives a unique **Process Identifier (PID)**.

Conceptually:

```text
Process

↓

PID

↓

Unique Number
```

The operating system uses the PID to identify and manage processes.

---

# 5. Parent and Child Processes

Processes may create other processes.

Conceptually:

```text
Parent Process

↓

Child Process
```

Example:

```text
Terminal

↓

Maven

↓

Java Application
```

This creates a process hierarchy.

---

# 6. Process Lifecycle

A simplified process lifecycle is:

```text
Create
        ↓
Ready
        ↓
Running
        ↓
Waiting
        ↓
Finished
```

The operating system continuously manages these state transitions.

---

# 7. Foreground Processes

A **foreground process** interacts directly with the user.

Conceptually:

```text
Terminal

↓

Running Program

↓

User Waits
```

The terminal remains occupied until the program finishes.

---

# 8. Background Processes

A **background process** executes independently of the terminal.

Conceptually:

```text
Program

↓

Background

↓

Terminal Available
```

Background execution allows developers to continue working while another program runs.

---

# 9. Viewing Running Processes

The command:

```bash
ps
```

displays information about active processes.

Common examples include:

```bash
ps
```

Current session.

```bash
ps -ef
```

All system processes.

Conceptually:

```text
Processes

↓

ps

↓

Process List
```

---

# 10. Real-Time Process Monitoring

The command:

```bash
top
```

provides real-time process monitoring.

It displays:

- CPU usage;
- memory usage;
- process identifiers;
- execution state.

Many Linux systems also provide:

```bash
htop
```

which offers a more interactive interface.

---

# 11. Finding Processes

The command:

```bash
pgrep
```

searches for processes by name.

Example:

```bash
pgrep java
```

Conceptually:

```text
Process Name

↓

pgrep

↓

Matching PID
```

---

# 12. Terminating Processes

The command:

```bash
kill
```

sends signals to a process.

Example:

```bash
kill 2451
```

Conceptually:

```text
PID

↓

kill

↓

Signal
```

The process may terminate depending on the signal received.

---

# 13. Forcefully Terminating Processes

If a process does not respond normally:

```bash
kill -9 PID
```

sends the **SIGKILL** signal.

Conceptually:

```text
Unresponsive Process

↓

SIGKILL

↓

Immediate Termination
```

This should be used only when graceful termination is not possible.

---

# 14. Signals

Signals are notifications sent to processes.

Examples include:

- `SIGTERM` — request graceful termination;
- `SIGKILL` — immediate termination;
- `SIGINT` — interruption (for example, `Ctrl + C`);
- `SIGHUP` — reload or restart configuration (depending on the application).

Signals allow the operating system and users to control process behavior.

---

# 15. Background Execution

Appending:

```text
&
```

to a command executes it in the background.

Example:

```bash
java -jar app.jar &
```

Conceptually:

```text
Command

↓

&

↓

Background Process
```

The shell immediately returns control to the user.

---

# 16. Process Resources

Each process consumes system resources.

Examples include:

- CPU time;
- memory;
- disk I/O;
- network I/O.

Monitoring resource usage helps identify performance problems.

---

# 17. Processes in Java Development

Java developers regularly work with processes when:

- running Java applications;
- executing Maven builds;
- launching Spring Boot services;
- debugging applications;
- monitoring servers;
- stopping unresponsive programs.

Understanding process management simplifies application deployment and troubleshooting.

---

# 18. Java Perspective

Typical Java development workflow:

```text
Terminal

↓

Maven Build

↓

Java Virtual Machine

↓

Application Process
```

Each stage executes as one or more Linux processes managed by the operating system.

---

# 19. Engineering Perspective

Professional software engineers rarely ask:

```text
How do I
close the
terminal?
```

Instead, they ask:

```text
Which process
is consuming
CPU?
```

```text
Why is this
application
still running?
```

```text
Can this
process be
terminated
safely?
```

Understanding processes enables developers to diagnose performance issues, manage services, and maintain stable production environments.

---

# 20. Relationship with the Next Document

This document introduced **Processes**, explaining how Linux executes programs, assigns process identifiers, and manages application execution.

The next document studies **Environment Variables**, explaining how Linux stores configuration values such as `PATH`, `JAVA_HOME`, and `MAVEN_HOME`, and how these variables influence software development and application execution.

---

# 21. Key Takeaways

After completing this document, the learner should understand that:

- a process is a program currently executing in memory;
- every process has a unique **Process Identifier (PID)** assigned by the operating system;
- programs and processes are different concepts: programs are stored on disk, while processes represent executing instances;
- Linux supports foreground and background process execution;
- commands such as `ps`, `top`, `htop`, `pgrep`, and `kill` are essential for monitoring and managing processes;
- processes communicate with the operating system through signals such as `SIGTERM`, `SIGKILL`, and `SIGINT`;
- every Java application, Maven build, and Spring Boot service executes as one or more Linux processes;
- understanding Linux process management is essential for debugging, deployment, performance analysis, and professional backend software development.