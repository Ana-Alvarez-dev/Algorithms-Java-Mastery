# Permissions

## Algorithms Java Mastery

Security is one of the fundamental principles of every operating system. In Linux, security begins with the **permission system**, which determines **who can read, modify, or execute every file and directory**. Unlike many desktop operating systems where users often have unrestricted access, Linux follows a strict security model based on **users**, **groups**, and **permission bits**.

Every Java application, source file, executable program, configuration file, shell script, and deployment artifact is protected by this permission model. Understanding how permissions work is essential not only for system administration but also for software development. Developers frequently encounter permission-related issues when executing shell scripts, running Maven builds, deploying applications, configuring Docker containers, or accessing files on remote Linux servers.

The Linux permission model follows the **principle of least privilege**: users should have only the permissions necessary to perform their tasks. This approach improves system security, prevents accidental modifications, and reduces the impact of software vulnerabilities.

This document introduces the Linux permission system, explains users and groups, studies permission management, and presents the commands most commonly used to control file access in professional development environments.

---

# 1. Purpose

The purpose of this document is to:

- introduce the Linux permission model;
- explain users and groups;
- understand read, write, and execute permissions;
- study file ownership;
- establish the foundation for secure software development on Linux.

---

# 2. Why Permissions Exist

Permissions protect files and system resources.

Conceptually:

```text
User

↓

Permission Check

↓

Allow

or

Deny
```

Every attempt to access a file is verified by the operating system.

---

# 3. File Ownership

Every file and directory belongs to:

- one **owner (user)**;
- one **group**.

Conceptually:

```text
File

↓

Owner

↓

Group
```

Ownership determines which permission rules apply.

---

# 4. Users

A **user** represents an individual account on the operating system.

Examples include:

- developers;
- administrators;
- service accounts;
- automated applications.

Each user has independent permissions and resources.

---

# 5. Groups

A **group** is a collection of users.

Conceptually:

```text
Group

↓

User A

User B

User C
```

Groups simplify permission management by allowing multiple users to share access rights.

---

# 6. Permission Categories

Linux defines permissions for three categories.

```text
Owner

↓

Group

↓

Others
```

Each category has its own permission set.

---

# 7. Permission Types

Three basic permissions exist.

### Read (`r`)

Allows viewing file contents.

Conceptually:

```text
Read File
```

---

### Write (`w`)

Allows modifying file contents.

Conceptually:

```text
Edit File
```

---

### Execute (`x`)

Allows executing programs or scripts.

Conceptually:

```text
Run Program
```

---

# 8. Permission Representation

Permissions are commonly displayed as:

```text
rwxr-xr--
```

This can be interpreted as:

```text
Owner   → rwx
Group   → r-x
Others  → r--
```

Each position corresponds to a specific permission.

---

# 9. Numeric Permissions

Permissions may also be represented numerically.

| Permission | Binary | Numeric |
|------------|:------:|:-------:|
| --- | 000 | 0 |
| --x | 001 | 1 |
| -w- | 010 | 2 |
| -wx | 011 | 3 |
| r-- | 100 | 4 |
| r-x | 101 | 5 |
| rw- | 110 | 6 |
| rwx | 111 | 7 |

Common examples include:

```text
755
```

Owner:

```text
rwx
```

Group:

```text
r-x
```

Others:

```text
r-x
```

---

```text
644
```

Owner:

```text
rw-
```

Group:

```text
r--
```

Others:

```text
r--
```

---

# 10. Viewing Permissions

The command:

```bash
ls -l
```

displays permission information.

Example:

```text
-rwxr-xr-- Main.java
```

Conceptually:

```text
ls -l

↓

Permission Information
```

---

# 11. Changing Permissions

The command:

```bash
chmod
```

modifies file permissions.

Example:

```bash
chmod 755 script.sh
```

Conceptually:

```text
Current Permissions

↓

chmod

↓

New Permissions
```

---

# 12. Changing Ownership

The command:

```bash
chown
```

changes the owner of a file.

Example:

```bash
sudo chown ana Main.java
```

Ownership can also be changed together with the group.

---

# 13. Changing Groups

The command:

```bash
chgrp
```

changes the associated group.

Example:

```bash
chgrp developers project/
```

This allows multiple users to collaborate on shared files.

---

# 14. Directory Permissions

Permissions affect directories differently than regular files.

For directories:

- **Read (`r`)** allows listing directory contents.
- **Write (`w`)** allows creating, deleting, or renaming entries.
- **Execute (`x`)** allows entering the directory and accessing its contents.

Conceptually:

```text
Directory

↓

Permissions

↓

Access Control
```

---

# 15. Executable Files

Programs and shell scripts require execute permission.

Example:

```bash
chmod +x build.sh
```

Afterward:

```bash
./build.sh
```

Without execute permission, Linux refuses to run the file.

---

# 16. Principle of Least Privilege

Professional software engineering follows the principle:

> **Grant only the permissions that are necessary.**

Conceptually:

```text
Minimum Required Access

↓

Improved Security
```

Excessive permissions increase security risks.

---

# 17. Permissions in Java Development

Java developers regularly encounter permissions when:

- executing Maven Wrapper (`mvnw`);
- running shell scripts;
- accessing configuration files;
- deploying applications;
- managing Docker volumes;
- configuring CI/CD pipelines.

Understanding permissions simplifies troubleshooting in Linux environments.

---

# 18. Java Perspective

Typical permission-related tasks include:

- making scripts executable;
- protecting configuration files;
- sharing project directories;
- managing deployment artifacts;
- securing application resources.

Linux permissions form an essential part of professional backend development.

---

# 19. Engineering Perspective

Professional software engineers rarely ask:

```text
How do I
disable
permissions?
```

Instead, they ask:

```text
Who should
have access?
```

```text
What is the
minimum
required
permission?
```

```text
Can this file
be executed
securely?
```

A well-configured permission model protects both applications and development environments.

---

# 20. Relationship with the Next Document

This document introduced the Linux permission model, explaining how ownership, users, groups, and permission bits control access to files and directories.

The next document studies **Processes**, explaining how Linux executes programs, manages running applications, allocates CPU resources, and allows developers to monitor and control software execution.

---

# 21. Key Takeaways

After completing this document, the learner should understand that:

- every Linux file and directory belongs to an owner and a group;
- permissions are assigned separately to the owner, group, and other users;
- the three basic permissions are **read (`r`)**, **write (`w`)**, and **execute (`x`)**;
- permissions can be represented symbolically (`rwx`) or numerically (for example, `755` and `644`);
- commands such as `ls -l`, `chmod`, `chown`, and `chgrp` are essential for permission management;
- directory permissions behave differently from file permissions, particularly regarding traversal and file creation;
- the principle of least privilege is a fundamental security practice in Linux and professional software engineering;
- understanding Linux permissions is essential for Java development, deployment, CI/CD pipelines, Docker, cloud environments, and secure backend systems.