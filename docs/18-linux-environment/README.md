# Linux Environment

## Algorithms Java Mastery

Modern software development extends far beyond writing source code. Professional developers are expected to understand the operating system in which their applications are developed, tested, deployed, and maintained. For Java backend engineers, that operating system is overwhelmingly **Linux**.

Linux powers the vast majority of cloud servers, containerized environments, enterprise infrastructure, and high-performance computing systems. Technologies such as **Spring Boot**, **Docker**, **Kubernetes**, **GitHub Actions**, and most cloud platforms execute primarily on Linux-based operating systems. Consequently, understanding Linux has become an essential skill for every professional Java developer.

This module introduces Linux from the perspective of **software engineering**, not system administration. Its objective is to provide the practical knowledge required to develop, build, execute, and maintain Java applications in a Linux environment.

Rather than focusing on advanced administration topics, this module explores the Linux concepts that every backend developer uses daily, including the file system, terminal, shell commands, file permissions, processes, environment variables, Java installation, Maven, Git, and common command-line utilities.

By mastering these concepts, students will be able to work confidently in professional development environments, Continuous Integration pipelines, Docker containers, remote servers, and cloud platforms.

---

# Learning Objectives

After completing this module, the learner should be able to:

- explain the role of Linux in modern software development;
- understand the Linux filesystem hierarchy;
- navigate the filesystem using the terminal;
- manage files and directories efficiently;
- understand Linux users, groups, and permissions;
- monitor and control processes;
- configure environment variables for Java development;
- install and configure Java development tools on Linux;
- use Maven and Git from the command line;
- work productively in Linux-based development environments.

---

# Module Structure

```text
18-linux-environment/
│── README.md
│── 01-linux-fundamentals.md
│── 02-file-system.md
│── 03-terminal-and-shell.md
│── 04-files-and-directories.md
│── 05-permissions.md
│── 06-processes.md
│── 07-environment-variables.md
│── 08-java-development-environment.md
│── 09-maven-and-git.md
└── 10-common-commands.md
```

---

# Module Contents

## 01. Linux Fundamentals

Introduces Linux as the dominant operating system for software development and servers.

Topics include:

- Linux philosophy;
- Unix heritage;
- distributions;
- kernel;
- shell;
- open-source ecosystem.

---

## 02. File System

Explains how Linux organizes data.

Topics include:

- filesystem hierarchy;
- root directory;
- `/home`;
- `/usr`;
- `/etc`;
- `/var`;
- `/tmp`;
- path navigation.

---

## 03. Terminal and Shell

Introduces the Linux command-line environment.

Topics include:

- terminal;
- shell;
- Bash;
- command execution;
- navigation;
- command syntax.

---

## 04. Files and Directories

Studies file management using Linux commands.

Topics include:

- creating files;
- moving files;
- copying files;
- deleting files;
- searching;
- directory management.

---

## 05. Permissions

Introduces Linux security fundamentals.

Topics include:

- users;
- groups;
- file permissions;
- ownership;
- `chmod`;
- `chown`;
- execution permissions.

---

## 06. Processes

Explains process management in Linux.

Topics include:

- processes;
- process identifiers;
- foreground;
- background execution;
- monitoring;
- process termination.

---

## 07. Environment Variables

Studies environment configuration.

Topics include:

- environment variables;
- PATH;
- JAVA_HOME;
- Maven configuration;
- shell configuration files.

---

## 08. Java Development Environment

Explains how Java is installed and configured on Linux.

Topics include:

- JDK installation;
- Java version management;
- project execution;
- development environment setup.

---

## 09. Maven and Git

Introduces Java development tools from the Linux terminal.

Topics include:

- Maven lifecycle;
- dependency management;
- Git commands;
- repository management;
- project builds.

---

## 10. Common Commands

Summarizes the Linux commands most frequently used by Java developers.

Topics include:

- filesystem navigation;
- process management;
- file permissions;
- package management;
- networking basics;
- development workflow.

---

# Learning Progression

This module follows the progression typically experienced by Java developers when beginning to work in Linux environments.

```text
Linux Fundamentals
        ↓
Filesystem
        ↓
Terminal
        ↓
Files & Directories
        ↓
Permissions
        ↓
Processes
        ↓
Environment Variables
        ↓
Java Environment
        ↓
Maven & Git
        ↓
Daily Development Workflow
```

Each document introduces concepts that build naturally upon the previous topics.

---

# Relationship with Previous Modules

This module extends the development workflow established throughout the repository.

```text
Problem Understanding
        ↓
Algorithm Design
        ↓
Correctness Reasoning
        ↓
Complexity Analysis
        ↓
Java Implementation
        ↓
Automated Testing
        ↓
Benchmarking
        ↓
Linux Environment
```

Previous modules focused on algorithm design, implementation, testing, and performance.

This module focuses on the operating system where professional Java applications are typically developed and deployed.

---

# Java Perspective

Modern Java backend development is closely associated with Linux.

Typical technologies include:

- OpenJDK;
- Maven;
- Git;
- Spring Boot;
- Docker;
- GitHub Actions;
- Kubernetes;
- cloud platforms.

Understanding Linux improves productivity across all of these technologies.

---

# Engineering Perspective

Professional software engineers view Linux as more than an operating system.

It is the foundation of modern software infrastructure.

Before deploying or maintaining an application, engineers typically ask:

- Is the development environment correctly configured?
- Are environment variables properly defined?
- Does the application execute correctly from the command line?
- Are file permissions configured appropriately?
- Can the project be built and executed entirely from the terminal?

Mastering Linux enables developers to work efficiently in local environments, remote servers, containers, and automated deployment pipelines.

---

# Role Within the Repository

The Linux module belongs to the engineering layer of **Algorithms Java Mastery**.

The project remains academically inspired by **Introduction to Algorithms (CLRS)**, but Linux knowledge is not derived from CLRS.

Its responsibility is different:

```text
CLRS
        ↓
Algorithmic Knowledge
        ↓
Java Implementation
        ↓
Testing / Benchmarking
        ↓
Maven
        ↓
Execution Environment
        ↓
Linux
```

Linux provides the environment in which the learner practises reproducible command-line execution, permissions, processes, environment variables, Java tooling, Maven, and Git.

It supports the engineering lifecycle without redefining the algorithmic theory.

---

# Repository Execution on Linux

The repository should be usable without depending exclusively on an IDE.

From the repository root, the principal verification command is:

```bash
./mvnw clean verify
```

This command uses the Maven Wrapper committed to the project.

A typical Linux workflow is:

```text
Clone / Update Repository
        ↓
Verify Java Environment
        ↓
Check Wrapper Permission
        ↓
./mvnw clean verify
        ↓
Inspect Test Result
        ↓
Commit / Push
```

The engineering objective is to understand why the command works, not merely to copy it.

---

# Cross-Platform Equivalence

Windows remains a valid local development environment.

The repository therefore uses equivalent Maven Wrapper entry points:

```text
Windows
.\mvnw.cmd clean verify

Linux
./mvnw clean verify
```

The command syntax differs because the operating environments differ.

The project contract does not.

Expected invariants include:

- the same source code is compiled;
- the same Maven project model is used;
- the same automated tests express the same behavioural contracts;
- the same algorithmic correctness expectations apply.

Environment-specific differences may still affect:

- shell syntax;
- path syntax;
- filesystem permissions;
- environment variables;
- process management;
- concrete benchmark measurements.

---

# Linux Traceability

The Linux module connects the executable repository with the broader engineering workflow.

```text
docs/<algorithm-module>/
        ↓
src/main/
        ↓
src/test/
        ↓
src/jmh/ when relevant
        ↓
pom.xml + Maven Wrapper
        ↓
docs/18-linux-environment/
        ↓
Linux Terminal Execution
        ↓
docs/19-ci-cd/
```

This relationship prepares the learner to understand how a locally reproducible command later becomes an automated CI step.

---

# Technical Review Questions

The following questions are intentionally limited to concepts studied in this module and directly connected with repository execution.

1. What is the difference between an absolute path and a relative path?
2. Why can `./mvnw` require execution permission on Linux?
3. What is the purpose of `PATH`?
4. What is the purpose of `JAVA_HOME`?
5. How can you verify which Java version is being used from the terminal?
6. What is the difference between a program and a running process?
7. Why can environment variables change the behaviour of a Java build?
8. What does the Maven Wrapper provide compared with relying on a globally installed Maven version?
9. Why should this repository be buildable from the terminal rather than only from IntelliJ IDEA?
10. Which project properties should remain unchanged when moving from Windows to Linux, and which environment details may legitimately differ?

The objective is explanation and reasoning, not command memorisation.

---

# Module Completion Criteria

This module can be considered complete when the learner can:

- navigate to the repository from a Linux terminal;
- explain absolute and relative paths;
- inspect and correct file execution permissions when necessary;
- inspect `PATH` and `JAVA_HOME`;
- verify the active Java version;
- explain basic process concepts;
- execute the repository through `./mvnw clean verify`;
- use Git from the command line for normal repository work;
- explain the relationship between local Linux verification and future CI automation;
- distinguish environment-dependent behaviour from algorithmic correctness and asymptotic complexity.

---

# References

Linux-specific knowledge should be supported by the official and technical sources centralised in:

```text
docs/00-project/10-references.md
```

The principal academic inspiration for the algorithmic content of the repository remains CLRS; Linux documentation serves a complementary engineering role.

---

# Navigation

**Previous:** `docs/17-benchmarking/`

**Next:** `docs/19-ci-cd/`

---

# Key Takeaways

After completing this module, the learner should understand that:

- Linux is the primary operating system for modern backend software development;
- understanding the Linux filesystem is essential for navigating development environments;
- the terminal and shell provide powerful tools for software development and automation;
- file management, permissions, and process control are fundamental Linux skills;
- environment variables play a critical role in configuring Java development tools;
- Maven and Git integrate naturally with Linux command-line workflows;
- professional Java developers use Linux daily for development, testing, deployment, and maintenance;
- mastering Linux significantly improves productivity and prepares developers for enterprise software engineering, cloud computing, DevOps, and containerized application development.