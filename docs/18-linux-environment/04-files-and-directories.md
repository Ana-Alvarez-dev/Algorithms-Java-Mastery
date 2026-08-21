# Files and Directories

## Algorithms Java Mastery

Files and directories are the fundamental building blocks of every Linux system. Every Java project, configuration file, source file, executable program, log, Git repository, and Maven dependency is stored somewhere within the Linux filesystem. Consequently, the ability to efficiently manage files and directories is one of the most important practical skills for professional software developers.

Unlike graphical file managers, Linux provides a rich set of command-line utilities that allow developers to create, organize, search, copy, move, rename, and delete files quickly and precisely. These commands form the basis of daily development workflows, whether working locally, on remote servers, inside Docker containers, or through Continuous Integration pipelines.

Professional Java developers interact with files constantly: creating new projects, editing source code, compiling applications, managing configuration files, viewing logs, organizing documentation, and manipulating build artifacts. Mastering file and directory management significantly improves productivity and enables efficient automation through shell scripts.

This document introduces the essential concepts and commands required to manage files and directories in Linux from the command line.

---

# 1. Purpose

The purpose of this document is to:

- explain files and directories in Linux;
- introduce the most important file management commands;
- distinguish files from directories;
- study common filesystem operations;
- establish the foundation for daily Linux development.

---

# 2. Files and Directories

Linux organizes information into two fundamental elements.

### File

Stores information.

Examples include:

- Java source code;
- configuration files;
- images;
- documents;
- executables.

---

### Directory

Organizes files and other directories.

Conceptually:

```text
Directory
        ↓
Files
        ↓
Subdirectories
```

Directories create the hierarchical structure of the filesystem.

---

# 3. The Current Working Directory

Every shell session has a **current working directory**.

Conceptually:

```text
Current Directory

↓

Commands Execute Here
```

Many Linux commands operate relative to this location.

---

# 4. Displaying the Current Directory

The command:

```bash
pwd
```

(Print Working Directory)

displays the absolute path of the current working directory.

Example:

```text
/home/ana/projects/Algorithms-Java-Mastery
```

This command helps developers understand where they are within the filesystem.

---

# 5. Listing Directory Contents

The command:

```bash
ls
```

lists the contents of a directory.

Common variations include:

```bash
ls -l
```

Long format.

```bash
ls -a
```

Displays hidden files.

```bash
ls -la
```

Displays detailed information including hidden files.

Conceptually:

```text
Directory

↓

ls

↓

Files
```

---

# 6. Changing Directories

The command:

```bash
cd
```

changes the current working directory.

Examples:

```bash
cd Documents
```

```bash
cd ..
```

```bash
cd ~
```

Conceptually:

```text
Current Directory

↓

cd

↓

New Directory
```

---

# 7. Creating Directories

The command:

```bash
mkdir
```

creates new directories.

Example:

```bash
mkdir algorithms
```

To create nested directories:

```bash
mkdir -p docs/linux
```

Conceptually:

```text
mkdir

↓

New Directory
```

---

# 8. Creating Files

Several commands create empty files.

The most common is:

```bash
touch README.md
```

Conceptually:

```text
touch

↓

Empty File
```

Developers frequently use this command to create source files and documentation.

---

# 9. Copying Files

The command:

```bash
cp
```

copies files.

Example:

```bash
cp Main.java Backup.java
```

Conceptually:

```text
Original File

↓

cp

↓

Copy
```

Directories may be copied recursively using:

```bash
cp -r project backup
```

---

# 10. Moving and Renaming Files

The command:

```bash
mv
```

moves or renames files.

Examples:

Rename:

```bash
mv old.txt new.txt
```

Move:

```bash
mv Main.java src/
```

Conceptually:

```text
File

↓

mv

↓

New Location
```

---

# 11. Removing Files

The command:

```bash
rm
```

deletes files.

Example:

```bash
rm notes.txt
```

Deleting a file is permanent unless backups or version control are available.

Developers should use this command carefully.

---

# 12. Removing Directories

Empty directories:

```bash
rmdir emptyFolder
```

Directories containing files:

```bash
rm -r project
```

Conceptually:

```text
Directory

↓

Remove

↓

Deleted
```

Recursive deletion should always be performed with caution.

---

# 13. Viewing File Contents

Several commands display file contents.

Examples include:

```bash
cat
```

Displays the complete file.

```bash
less
```

Scrollable file viewer.

```bash
more
```

Paged file viewer.

```bash
head
```

Displays the beginning of a file.

```bash
tail
```

Displays the end of a file.

These commands are commonly used to inspect configuration files and application logs.

---

# 14. Searching for Files

Linux provides tools for locating files.

Example:

```bash
find
```

Conceptually:

```text
Search Criteria

↓

find

↓

Matching Files
```

Searching is essential when working with large projects.

---

# 15. Wildcards

Shell wildcards simplify file selection.

Common examples:

```text
*
```

Matches multiple characters.

```text
?
```

Matches one character.

Example:

```bash
ls *.java
```

Lists every Java source file in the current directory.

---

# 16. Hidden Files

Files beginning with:

```text
.
```

are hidden.

Examples:

```text
.gitignore
```

```text
.bashrc
```

Hidden files commonly store configuration information.

---

# 17. File Organization

Professional developers organize projects using logical directory structures.

Typical Java project:

```text
Algorithms-Java-Mastery/
│
├── src/
├── docs/
├── target/
├── pom.xml
└── README.md
```

Clear organization improves readability and maintainability.

---

# 18. Java Perspective

Java developers manipulate files constantly.

Typical activities include:

- creating source files;
- organizing packages;
- viewing logs;
- editing configuration files;
- managing Maven projects;
- cloning Git repositories;
- cleaning build directories.

Efficient filesystem management directly improves development productivity.

---

# 19. Engineering Perspective

Professional software engineers rarely ask:

```text
Which command
should I
memorize?
```

Instead, they ask:

```text
How should
the project
be organized?
```

```text
Can these
operations be
automated?
```

```text
Will another
developer easily
understand this
directory structure?
```

Effective file organization and command-line proficiency contribute to maintainable software projects and efficient development workflows.

---

# 20. Relationship with the Next Document

This document introduced the management of **Files and Directories** in Linux, presenting the commands most frequently used by software developers.

The next document studies **Permissions**, explaining how Linux controls access to files and directories through users, groups, and permission bits, and why understanding permissions is essential for secure software development and system administration.

---

# 21. Key Takeaways

After completing this document, the learner should understand that:

- files store data, while directories organize the filesystem hierarchy;
- the current working directory determines how relative paths are interpreted;
- commands such as `pwd`, `ls`, and `cd` are fundamental for filesystem navigation;
- `mkdir`, `touch`, `cp`, `mv`, and `rm` provide the core operations for managing files and directories;
- commands such as `cat`, `less`, `head`, and `tail` allow developers to inspect file contents efficiently;
- the `find` command and shell wildcards simplify file discovery and selection;
- well-organized directory structures improve project maintainability and developer productivity;
- mastering Linux file management is an essential professional skill for Java backend development, automation, DevOps, and cloud-based software engineering.