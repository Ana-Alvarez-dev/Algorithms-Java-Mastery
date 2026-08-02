# Environment Variables

## Algorithms Java Mastery

Modern software applications rarely operate in isolation. Instead of embedding configuration values directly into source code, professional software systems obtain important information from their execution environment. Database credentials, application ports, file locations, Java installation paths, API keys, and build tools are commonly configured through **environment variables**.

In Linux, **environment variables** are named values maintained by the operating system and made available to running processes. They provide a flexible mechanism for configuring applications without modifying their source code, allowing the same application to execute correctly across development, testing, staging, and production environments.

For Java developers, environment variables are an essential part of the development workflow. Variables such as **PATH**, **JAVA_HOME**, and **MAVEN_HOME** determine how the operating system locates the Java Development Kit (JDK), Maven, Git, and other development tools. Incorrect configuration often results in build failures or applications that cannot be executed.

Understanding environment variables is therefore fundamental for professional Java development, Continuous Integration pipelines, Docker containers, cloud deployments, and enterprise software engineering.

---

# 1. Purpose

The purpose of this document is to:

- introduce Linux environment variables;
- explain their role in software configuration;
- distinguish local and global variables;
- study commonly used Java development variables;
- establish good configuration practices.

---

# 2. What Are Environment Variables?

An **environment variable** is a named value stored by the operating system and provided to running processes.

Conceptually:

```text
Operating System

↓

Environment Variable

↓

Application
```

Applications read these values during execution to determine how they should behave.

---

# 3. Why Environment Variables Exist

Instead of modifying source code:

```text
Change Code

↓

Recompile

↓

Redeploy
```

developers can simply change configuration.

```text
Environment Variable

↓

Application

↓

New Configuration
```

This makes software easier to maintain and deploy.

---

# 4. Process Environment

Each process receives its own environment when it starts.

Conceptually:

```text
Operating System

↓

Environment

↓

Process
```

The process can read environment variables throughout its execution.

---

# 5. Variable Name and Value

Every environment variable consists of:

```text
Variable Name

↓

Associated Value
```

Example:

```text
JAVA_HOME=/usr/lib/jvm/jdk-21
```

The application reads the value associated with the variable name.

---

# 6. Viewing Environment Variables

The command:

```bash
printenv
```

displays the current environment.

Example:

```bash
printenv
```

To display a specific variable:

```bash
echo $JAVA_HOME
```

Conceptually:

```text
Variable

↓

echo

↓

Value
```

---

# 7. Creating Environment Variables

Temporary variables may be created using:

```bash
export
```

Example:

```bash
export PROJECT_NAME=Algorithms-Java-Mastery
```

Conceptually:

```text
Variable

↓

export

↓

Current Session
```

The variable exists only for the current shell session unless saved permanently.

---

# 8. Session Variables

Variables created during a terminal session disappear when the shell closes.

Conceptually:

```text
Open Terminal

↓

Create Variable

↓

Close Terminal

↓

Variable Removed
```

These variables are called **session variables**.

---

# 9. Persistent Variables

Permanent variables are stored in shell configuration files.

Common files include:

```text
~/.bashrc
```

```text
~/.profile
```

```text
~/.bash_profile
```

When the shell starts, these files are read and the variables become available automatically.

---

# 10. PATH

One of the most important Linux environment variables is:

```text
PATH
```

Conceptually:

```text
Command

↓

PATH

↓

Executable Found
```

PATH contains a list of directories that the shell searches when executing commands.

Without a correct PATH configuration, many development tools cannot be located.

---

# 11. JAVA_HOME

Java development commonly uses:

```text
JAVA_HOME
```

Conceptually:

```text
JAVA_HOME

↓

JDK Installation

↓

Java Tools
```

Many build tools rely on this variable to locate the Java Development Kit.

Example:

```text
JAVA_HOME=/usr/lib/jvm/jdk-21
```

---

# 12. Maven Configuration

Maven typically relies on:

```text
MAVEN_HOME
```

or

```text
M2_HOME
```

Conceptually:

```text
Maven

↓

Environment Variable

↓

Build Tools
```

Although modern Maven installations often work without explicitly defining these variables, they remain common in enterprise environments.

---

# 13. HOME

Linux automatically defines:

```text
HOME
```

Conceptually:

```text
Current User

↓

Home Directory
```

Example:

```text
/home/ana
```

Applications frequently use this variable to locate user-specific configuration files.

---

# 14. USER

The variable:

```text
USER
```

contains the current user's account name.

Conceptually:

```text
Operating System

↓

Current User
```

Applications may use this information for logging or personalization.

---

# 15. Shell Configuration Files

Environment variables are commonly defined inside shell startup files.

Typical workflow:

```text
Edit .bashrc

↓

Save File

↓

Reload Shell

↓

Variable Available
```

This ensures that variables persist across future terminal sessions.

---

# 16. Environment Variables in Java Development

Professional Java developers frequently configure:

- `JAVA_HOME`;
- `PATH`;
- `MAVEN_HOME` or `M2_HOME`;
- `GRADLE_HOME`;
- application-specific configuration variables.

These variables simplify project execution across different environments.

---

# 17. Environment Variables in DevOps

Environment variables are widely used in:

- Docker containers;
- Kubernetes deployments;
- CI/CD pipelines;
- cloud platforms;
- application servers.

They allow the same application to run with different configurations without modifying the source code.

---

# 18. Security Considerations

Although environment variables are convenient, they should be used carefully.

Sensitive information such as:

- passwords;
- API tokens;
- database credentials;

should be protected appropriately and managed using secure configuration mechanisms where possible.

The principle of least privilege applies to configuration as well as file permissions.

---

# 19. Engineering Perspective

Professional software engineers rarely hard-code configuration values.

Instead, they ask:

```text
Can this
configuration
change?
```

```text
Should this
value be
environment-
specific?
```

```text
Can the
application
run in multiple
environments
without changing
the code?
```

Environment variables improve portability, maintainability, and deployment flexibility.

---

# 20. Relationship with the Next Document

This document introduced **Environment Variables**, explaining how Linux stores configuration values that applications use during execution.

The next document studies the **Java Development Environment**, demonstrating how to install and configure the Java Development Kit, verify Java installations, configure Maven, and prepare a Linux system for professional Java software development.

---

# 21. Key Takeaways

After completing this document, the learner should understand that:

- environment variables provide configuration values to running processes;
- applications use environment variables to adapt their behavior without changing source code;
- commands such as `printenv`, `echo`, and `export` allow developers to inspect and create environment variables;
- session variables exist only while the current shell is running, whereas persistent variables are stored in shell configuration files;
- `PATH` determines how Linux locates executable programs;
- `JAVA_HOME` identifies the Java Development Kit used by development tools;
- environment variables are fundamental to Java development, Docker, cloud platforms, and CI/CD pipelines;
- professional software engineers use environment variables to create portable, configurable, and maintainable applications across multiple execution environments.
- 