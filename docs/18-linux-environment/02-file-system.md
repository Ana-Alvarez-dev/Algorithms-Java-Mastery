# Linux File System

## Algorithms Java Mastery

One of the first concepts every Linux developer must understand is the **Linux File System**. Unlike operating systems that organize storage around multiple drive letters (such as `C:` or `D:`), Linux represents every storage device, directory, and file within a **single hierarchical directory tree** that begins at a common root.

This unified organization is one of the defining characteristics inherited from the Unix operating system. Whether a developer is accessing local files, removable storage, network resources, or virtual devices, everything appears as part of the same filesystem hierarchy.

For Java developers, understanding the Linux filesystem is essential because Java applications, Maven projects, Git repositories, Docker containers, configuration files, log files, and deployment artifacts are all organized according to this hierarchy. Professional developers interact with the filesystem daily when compiling applications, managing dependencies, configuring environments, and deploying software.

This document introduces the structure of the Linux filesystem, explains its most important directories, and establishes the knowledge required to navigate and manage development environments efficiently.

---

# 1. Purpose

The purpose of this document is to:

- explain the Linux filesystem hierarchy;
- introduce the root directory;
- describe the purpose of the main system directories;
- explain absolute and relative paths;
- establish the foundation for filesystem navigation.

---

# 2. What Is the Linux File System?

The **Linux File System** is the hierarchical organization used to store and manage data.

Conceptually:

```text
Root Directory (/)
        ↓
Directories
        ↓
Subdirectories
        ↓
Files
```

Every file and directory belongs somewhere within this hierarchy.

---

# 3. The Root Directory

The highest level of the filesystem is called the **root directory**.

It is represented by:

```text
/
```

Conceptually:

```text
/
│
├── home
├── usr
├── etc
├── var
├── tmp
└── ...
```

Every path in Linux begins from this root.

---

# 4. Hierarchical Organization

Linux organizes data using a tree structure.

Conceptually:

```text
/
│
├── home
│     └── ana
│           └── project
│
├── usr
│
└── etc
```

Each directory may contain additional files and subdirectories.

---

# 5. Everything Is a File

One of the most important Unix principles is:

> **Everything is treated as a file.**

Examples include:

- regular files;
- directories;
- devices;
- sockets;
- pipes.

This unified model simplifies interaction with system resources.

---

# 6. Absolute Paths

An **absolute path** begins at the root directory.

Example:

```text
/home/ana/projects/Algorithms-Java-Mastery
```

Conceptually:

```text
/

↓

home

↓

ana

↓

projects

↓

Algorithms-Java-Mastery
```

Absolute paths always describe the complete location of a file or directory.

---

# 7. Relative Paths

A **relative path** begins from the current working directory.

Example:

```text
projects/Algorithms-Java-Mastery
```

Conceptually:

```text
Current Directory

↓

projects

↓

Algorithms-Java-Mastery
```

Relative paths are shorter and depend on the current location.

---

# 8. Home Directory

Every user has a personal working directory.

Typical example:

```text
/ home / username
```

Example:

```text
/ home / ana
```

Conceptually:

```text
/

↓

home

↓

username
```

This directory stores personal files, projects, and user-specific configuration.

---

# 9. Important System Directories

Linux defines several standard directories.

Examples include:

```text
/
```

Root directory.

---

```text
/home
```

User home directories.

---

```text
/usr
```

Applications, libraries, and shared resources.

---

```text
/etc
```

System configuration files.

---

```text
/var
```

Logs, caches, and variable application data.

---

```text
/tmp
```

Temporary files.

---

```text
/bin
```

Essential command-line programs.

---

```text
/sbin
```

Administrative system programs.

---

```text
/opt
```

Optional third-party software.

---

```text
/dev
```

Hardware device interfaces.

---

```text
/proc
```

Virtual information about running processes and the kernel.

---

# 10. File Extensions

Unlike some operating systems, Linux does **not** determine file type solely from its extension.

Examples:

```text
Main.java
```

```text
README.md
```

```text
pom.xml
```

```text
script.sh
```

The operating system primarily determines file type from the file's contents and metadata rather than its name.

---

# 11. Hidden Files

Files whose names begin with a period are hidden by default.

Examples:

```text
.git
```

```text
.gitignore
```

```text
.bashrc
```

```text
.profile
```

Hidden files commonly store configuration information.

---

# 12. Current Directory

The current working directory represents the location in which commands execute.

Conceptually:

```text
Current Directory

↓

Execute Command
```

Many commands operate relative to this location.

---

# 13. Parent Directory

Each directory has a parent.

The symbol:

```text
..
```

represents the parent directory.

Conceptually:

```text
Current Directory

↓

Parent Directory
```

Moving upward in the directory tree is a common navigation task.

---

# 14. Current Directory Symbol

The symbol:

```text
.
```

represents the current directory.

Examples:

```text
./script.sh
```

```text
./pom.xml
```

This notation explicitly refers to resources located in the current working directory.

---

# 15. Linux File System and Java Projects

A typical Java project might appear as:

```text
/home/ana/projects/
        ↓
Algorithms-Java-Mastery/
        ├── src/
        ├── docs/
        ├── pom.xml
        ├── README.md
        └── target/
```

Understanding this structure simplifies project navigation and build automation.

---

# 16. Relationship with Maven and Git

Both Maven and Git rely heavily on the filesystem.

Examples include:

- project directories;
- repository structure;
- source code organization;
- build artifacts;
- configuration files.

Efficient filesystem navigation directly improves development productivity.

---

# 17. Java Perspective

Professional Java developers interact with the filesystem continuously.

Typical activities include:

- creating projects;
- editing source files;
- executing Maven builds;
- managing Git repositories;
- viewing logs;
- configuring applications.

A solid understanding of the Linux filesystem is therefore a fundamental development skill.

---

# 18. Engineering Perspective

Professional software engineers rarely memorize directory names without understanding their purpose.

Instead, they ask:

```text
Where should
this file
be stored?
```

```text
Which directory
contains system
configuration?
```

```text
Where are
application
logs located?
```

Understanding the filesystem hierarchy leads to more organized projects, easier debugging, and better deployment practices.

---

# 19. Relationship with the Next Document

This document introduced the **Linux File System**, explaining how Linux organizes files and directories within a unified hierarchy.

The next document studies the **Terminal and Shell**, where these directories are accessed and managed through command-line tools that form the foundation of professional Linux development.

---

# 20. Key Takeaways

After completing this document, the learner should understand that:

- the Linux filesystem is organized as a hierarchical directory tree beginning at the root directory (`/`);
- every file, directory, and storage resource belongs somewhere within this hierarchy;
- absolute paths begin at the root directory, while relative paths begin from the current working directory;
- the home directory stores user-specific files and development projects;
- directories such as `/usr`, `/etc`, `/var`, `/tmp`, `/bin`, `/dev`, and `/proc` each have well-defined responsibilities;
- hidden files begin with a period and commonly contain configuration information;
- Maven projects and Git repositories are organized within the Linux filesystem and rely heavily on its directory structure;
- understanding the Linux filesystem is essential for efficient Java development, system configuration, deployment, and professional software engineering.