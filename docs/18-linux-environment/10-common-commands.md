# Common Commands

## Algorithms Java Mastery

One of the greatest strengths of Linux is its command-line interface. Professional software engineers interact with the operating system primarily through commands that automate repetitive tasks, simplify project management, and enable efficient software development. Rather than relying on graphical interfaces, developers execute commands to navigate directories, manipulate files, manage processes, inspect logs, compile applications, interact with version control systems, and deploy software.

Although Linux provides hundreds of command-line utilities, a relatively small set of commands is used daily by Java backend developers. Mastering these commands significantly improves productivity and allows developers to work comfortably on local machines, remote servers, Docker containers, cloud environments, and Continuous Integration pipelines.

This document summarizes the most important Linux commands introduced throughout this module. Instead of presenting exhaustive documentation, it serves as a practical reference for the commands that professional Java developers use most frequently during everyday software engineering activities.

---

# 1. Purpose

The purpose of this document is to:

- summarize the most important Linux commands;
- reinforce command-line proficiency;
- organize commands by category;
- establish a practical command reference;
- conclude the Linux Environment module.

---

# 2. Filesystem Navigation

### Display current directory

```bash
pwd
```

Displays the absolute path of the current working directory.

---

### List directory contents

```bash
ls
```

Common options:

```bash
ls -l
```

Detailed information.

```bash
ls -a
```

Hidden files.

```bash
ls -la
```

Detailed information including hidden files.

---

### Change directory

```bash
cd directory
```

Examples:

```bash
cd ..
```

Parent directory.

```bash
cd ~
```

Home directory.

---

# 3. File and Directory Management

### Create directory

```bash
mkdir project
```

Nested directories:

```bash
mkdir -p docs/linux
```

---

### Create file

```bash
touch README.md
```

---

### Copy files

```bash
cp source.txt destination.txt
```

Copy directories:

```bash
cp -r project backup
```

---

### Move or rename

```bash
mv old.txt new.txt
```

Move file:

```bash
mv Main.java src/
```

---

### Remove file

```bash
rm notes.txt
```

---

### Remove directory

```bash
rm -r project
```

Use recursive deletion carefully.

---

# 4. Viewing File Contents

### Display complete file

```bash
cat file.txt
```

---

### View large files

```bash
less file.txt
```

---

### Display first lines

```bash
head file.txt
```

---

### Display last lines

```bash
tail file.txt
```

Frequently used for application logs.

---

# 5. Searching

### Search for files

```bash
find . -name "*.java"
```

---

### Wildcards

All Java files:

```bash
*.java
```

All Markdown files:

```bash
*.md
```

Wildcards simplify file selection.

---

# 6. Permissions

### View permissions

```bash
ls -l
```

---

### Change permissions

```bash
chmod 755 script.sh
```

---

### Make script executable

```bash
chmod +x build.sh
```

---

### Change owner

```bash
chown user file
```

---

### Change group

```bash
chgrp developers project
```

---

# 7. Process Management

### Display processes

```bash
ps
```

System-wide:

```bash
ps -ef
```

---

### Real-time monitoring

```bash
top
```

or

```bash
htop
```

---

### Find a process

```bash
pgrep java
```

---

### Terminate a process

```bash
kill PID
```

Force termination:

```bash
kill -9 PID
```

---

### Execute in background

```bash
java -jar app.jar &
```

---

# 8. Environment Variables

### Display environment

```bash
printenv
```

---

### Display a variable

```bash
echo $JAVA_HOME
```

---

### Create a session variable

```bash
export PROJECT_NAME=Algorithms-Java-Mastery
```

---

# 9. Java Development

### Verify Java

```bash
java -version
```

---

### Verify compiler

```bash
javac -version
```

---

### Verify JAVA_HOME

```bash
echo $JAVA_HOME
```

---

# 10. Maven

### Verify Maven

```bash
mvn -version
```

---

### Compile

```bash
mvn compile
```

---

### Run tests

```bash
mvn test
```

---

### Package application

```bash
mvn package
```

---

### Clean project

```bash
mvn clean
```

---

### Install locally

```bash
mvn install
```

---

# 11. Git

### Verify Git

```bash
git --version
```

---

### Repository status

```bash
git status
```

---

### Stage changes

```bash
git add .
```

---

### Commit

```bash
git commit -m "message"
```

---

### View history

```bash
git log
```

---

### Switch branches

```bash
git switch branch-name
```

---

### List branches

```bash
git branch
```

---

### Download updates

```bash
git pull
```

---

### Upload commits

```bash
git push
```

---

# 12. Daily Java Developer Workflow

A typical Linux development session might follow this sequence:

```text
Open Terminal
        ↓
Navigate to Project
        ↓
git pull
        ↓
Write Code
        ↓
mvn test
        ↓
mvn package
        ↓
git status
        ↓
git add
        ↓
git commit
        ↓
git push
```

This workflow combines Linux commands with Maven and Git to support efficient software development.

---

# 13. Command Categories

| Category | Common Commands |
|-----------|-----------------|
| Navigation | `pwd`, `ls`, `cd` |
| Files | `touch`, `cp`, `mv`, `rm`, `mkdir` |
| File Viewing | `cat`, `less`, `head`, `tail` |
| Search | `find` |
| Permissions | `chmod`, `chown`, `chgrp` |
| Processes | `ps`, `top`, `htop`, `pgrep`, `kill` |
| Environment | `printenv`, `echo`, `export` |
| Java | `java`, `javac` |
| Maven | `mvn compile`, `mvn test`, `mvn package`, `mvn clean`, `mvn install` |
| Git | `git status`, `git add`, `git commit`, `git switch`, `git pull`, `git push` |

---

# 14. Best Practices

Professional Java developers should:

- use the terminal daily to build confidence and speed;
- prefer command-line workflows for automation and reproducibility;
- understand commands before using options that modify or delete files;
- verify commands such as `rm -r` and `kill -9` before execution;
- organize projects consistently within the Linux filesystem;
- integrate Linux commands naturally with Maven and Git workflows.

Command-line proficiency grows through regular practice rather than memorization.

---

# 15. Engineering Perspective

Professional software engineers rarely memorize every Linux command.

Instead, they develop an understanding of:

```text
Problem

↓

Appropriate Command

↓

Expected Result
```

Their goal is not simply to execute commands, but to build reliable, reproducible, and automated development workflows that improve software quality and team productivity.

---

# 16. Relationship with the Repository

This document concludes the **Linux Environment** module.

At this point, the learner understands:

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
Common Commands
```

Together, these topics provide the Linux knowledge required to develop, build, test, and maintain Java applications in professional environments.

The next module, **CI/CD (Continuous Integration and Continuous Delivery)**, builds directly upon these concepts by showing how Linux, Git, Maven, and automated testing are integrated into modern software delivery pipelines.

---

# 17. Key Takeaways

After completing this document, the learner should understand that:

- a relatively small set of Linux commands supports most daily software development activities;
- filesystem navigation, file management, permissions, process management, and environment variables form the foundation of command-line work;
- Maven and Git integrate naturally into Linux development workflows;
- professional Java developers rely heavily on terminal-based tools for building, testing, version control, and deployment;
- command-line proficiency improves automation, reproducibility, and productivity;
- Linux commands are best learned through consistent practice in real development projects;
- mastering these commands prepares developers for Docker, CI/CD pipelines, cloud platforms, remote servers, and enterprise Java backend development.