# Java Development Environment

## Algorithms Java Mastery

Developing professional Java applications requires more than writing source code. Before any program can be compiled, executed, tested, or benchmarked, the development environment must be correctly installed and configured. A properly configured environment ensures that development tools interact correctly, build automation functions reliably, and applications behave consistently across different machines.

For professional Java developers working on Linux, the development environment typically consists of the **Java Development Kit (JDK)**, a build automation tool such as **Maven**, a version control system such as **Git**, and a suitable development environment (IDE) such as **IntelliJ IDEA**. Together, these tools provide everything necessary to create, build, test, benchmark, and maintain modern Java applications.

Unlike many graphical installers available on desktop operating systems, Linux encourages developers to understand how software is installed, configured, and executed through the command line. This approach improves automation, reproducibility, and troubleshooting skills while preparing developers for cloud environments, remote servers, Docker containers, and Continuous Integration pipelines.

This document introduces the components of a professional Java development environment on Linux and explains how they are installed, configured, verified, and maintained.

---

# 1. Purpose

The purpose of this document is to:

- explain the components of a Java development environment;
- introduce the Java Development Kit (JDK);
- study tool installation and verification;
- explain environment configuration;
- establish the foundation for professional Java development on Linux.

---

# 2. What Is a Development Environment?

A **development environment** is the collection of software tools required to create, build, test, and execute applications.

Conceptually:

```text
Operating System
        ↓
Development Tools
        ↓
Java Project
        ↓
Application
```

A properly configured environment improves productivity and ensures consistent software behavior.

---

# 3. The Java Development Kit (JDK)

The **Java Development Kit (JDK)** is the primary tool required for Java development.

It includes:

- Java compiler (`javac`);
- Java Virtual Machine (JVM);
- Java runtime libraries;
- development utilities;
- debugging tools.

Conceptually:

```text
Java Source Code
        ↓
JDK
        ↓
Compiled Application
```

Without the JDK, Java applications cannot be developed.

---

# 4. JDK vs JRE

Understanding the distinction between the JDK and the Java Runtime Environment (JRE) is important.

### JDK

Provides everything required to develop Java software.

Includes:

- compiler;
- runtime;
- development tools.

---

### JRE

Provides only the components required to execute Java applications.

Conceptually:

```text
JDK

↓

Develop

+

Run
```

```text
JRE

↓

Run Only
```

Modern Java distributions generally package the runtime together with the JDK.

---

# 5. Installing Java

Java is commonly installed using the Linux package manager.

Typical workflow:

```text
Package Manager
        ↓
Install JDK
        ↓
Verify Installation
```

Professional developers typically install an **LTS (Long-Term Support)** version of Java for stability and long-term compatibility.

---

# 6. Verifying the Installation

After installation, developers verify that Java is correctly configured.

Common commands include:

```bash
java -version
```

Displays the installed Java Runtime version.

```bash
javac -version
```

Displays the Java compiler version.

Conceptually:

```text
Installation

↓

Verification

↓

Ready To Use
```

---

# 7. Configuring JAVA_HOME

Many Java tools rely on the environment variable:

```text
JAVA_HOME
```

Conceptually:

```text
JAVA_HOME

↓

JDK Location

↓

Development Tools
```

Example:

```text
JAVA_HOME=/usr/lib/jvm/jdk-21
```

Correct configuration allows Maven and other tools to locate the installed JDK.

---

# 8. Configuring PATH

The operating system locates Java commands through:

```text
PATH
```

Conceptually:

```text
PATH

↓

java

↓

Executable Found
```

If the PATH variable is incorrect, Linux cannot execute Java commands directly.

---

# 9. Maven Installation

Modern Java projects typically use **Apache Maven** for build automation.

Maven provides:

- dependency management;
- project compilation;
- automated testing;
- packaging;
- plugin execution.

Conceptually:

```text
Source Code
        ↓
Maven
        ↓
Build
```

---

# 10. Verifying Maven

Developers verify Maven using:

```bash
mvn -version
```

The command displays:

- Maven version;
- Java version;
- operating system;
- installation details.

Successful execution confirms correct integration between Maven and Java.

---

# 11. Git Installation

Version control is an essential component of every professional development environment.

Git enables developers to:

- track changes;
- collaborate;
- manage branches;
- maintain project history.

Verification command:

```bash
git --version
```

---

# 12. Development Directory

Professional developers typically organize projects inside a dedicated workspace.

Example:

```text
/home/ana/projects/
        ↓
Algorithms-Java-Mastery
```

A consistent directory structure simplifies navigation and automation.

---

# 13. Integrated Development Environment (IDE)

Although Linux development can be performed entirely from the terminal, professional developers commonly use an IDE.

Popular choices include:

- IntelliJ IDEA;
- Eclipse;
- Visual Studio Code.

An IDE provides:

- code completion;
- debugging;
- refactoring;
- project management;
- integration with Maven and Git.

---

# 14. Development Workflow

A typical Java workflow on Linux follows this progression.

```text
Write Code
        ↓
Compile
        ↓
Run Tests
        ↓
Package
        ↓
Execute
```

Each step relies on a correctly configured development environment.

---

# 15. Command-Line Development

Professional Java developers regularly use the terminal to:

- compile projects;
- execute Maven builds;
- run tests;
- manage Git repositories;
- launch applications;
- inspect logs.

Command-line proficiency complements IDE usage and is essential in server environments.

---

# 16. Relationship with Previous Modules

The development environment integrates concepts introduced earlier.

```text
Linux
        ↓
Filesystem
        ↓
Terminal
        ↓
Permissions
        ↓
Environment Variables
        ↓
Java Development
```

Each topic contributes to a complete and functional Java development platform.

---

# 17. Relationship with Modern Java Development

A professional Java development environment supports technologies such as:

- Maven;
- Gradle;
- Spring Boot;
- JUnit;
- Docker;
- GitHub Actions;
- Kubernetes.

Correct environment configuration enables these tools to work together seamlessly.

---

# 18. Engineering Perspective

Professional software engineers rarely ask:

```text
Can Java
be installed?
```

Instead, they ask:

```text
Is the
environment
reproducible?
```

```text
Can another
developer
build this
project?
```

```text
Will the
same project
run correctly
on CI and
production?
```

A reliable development environment emphasizes consistency, automation, and reproducibility across all stages of the software lifecycle.

---

# 19. Relationship with the Next Document

This document introduced the **Java Development Environment**, explaining how the JDK, Maven, Git, environment variables, and development tools work together on Linux.

The next document studies **Maven and Git**, demonstrating how professional Java developers build projects, manage dependencies, track changes, collaborate through version control, and automate software development workflows from the Linux command line.

---

# 20. Key Takeaways

After completing this document, the learner should understand that:

- a Java development environment consists of the operating system together with development tools such as the JDK, Maven, Git, and an IDE;
- the JDK provides the compiler, runtime, libraries, and utilities required to develop Java applications;
- the JDK and JRE serve different purposes, although modern Java distributions commonly package them together;
- installation should always be verified using commands such as `java -version`, `javac -version`, `mvn -version`, and `git --version`;
- `JAVA_HOME` and `PATH` are essential environment variables for Java development;
- Maven automates project builds, dependency management, testing, and packaging;
- professional Java developers combine terminal-based workflows with IDE support for efficient development;
- a reproducible and well-configured development environment is fundamental for enterprise Java development, DevOps practices, CI/CD pipelines, and cloud-native software engineering.