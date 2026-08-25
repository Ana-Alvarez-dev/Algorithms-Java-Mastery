# Workflow Anatomy

## Algorithms Java Mastery

Automation platforms execute workflows by following a well-defined structure. In GitHub Actions, every automated process is described inside a **workflow file**, written in **YAML (YAML Ain't Markup Language)**. Rather than writing executable programs, developers describe *what* should happen, *when* it should happen, and *under which conditions* it should execute.

Understanding the internal anatomy of a workflow is essential for designing reliable CI/CD pipelines. Every GitHub Actions workflow is composed of several building blocks, including **events**, **workflows**, **jobs**, **steps**, **actions**, **runners**, and **environment variables**. Together, these components define the complete execution of an automated software pipeline.

For Java projects, workflow files typically describe processes such as checking out the repository, installing the Java Development Kit (JDK), restoring Maven dependencies, compiling the project, executing automated tests, packaging the application, and storing build artifacts.

This document studies the internal structure of GitHub Actions workflows and explains how each component contributes to the automation of professional Java development.

---

# 1. Purpose

The purpose of this document is to:

- explain the structure of GitHub Actions workflows;
- introduce YAML workflow files;
- study triggers, jobs, steps, actions, and runners;
- understand workflow execution order;
- establish the foundation for designing professional CI pipelines.

---

# 2. What Is a Workflow?

A **workflow** is a complete automation process executed by GitHub Actions.

Conceptually:

```text
Repository Event
        ↓
Workflow
        ↓
Automated Tasks
        ↓
Result
```

A workflow defines everything that should happen after a specific event occurs.

---

# 3. Workflow File Location

GitHub Actions automatically discovers workflow files stored inside:

```text
.github/
        ↓
workflows/
```

Example:

```text
.github/
└── workflows/
    └── maven-ci.yml
```

Every workflow must be stored in this directory.

---

# 4. YAML Structure

GitHub Actions workflows are written using **YAML**.

A simplified structure is:

```text
Workflow
        ↓
Trigger
        ↓
Jobs
        ↓
Steps
```

Each section has a specific responsibility.

---

# 5. Workflow Name

A workflow usually begins with a descriptive name.

Example:

```yaml
name: Maven CI
```

Conceptually:

```text
Workflow

↓

Human-Readable Name
```

The name helps developers identify workflow executions inside GitHub.

---

# 6. Triggers

A workflow begins when an **event** occurs.

Typical triggers include:

- push;
- pull_request;
- release;
- workflow_dispatch;
- schedule.

Conceptually:

```text
Repository Event

↓

Workflow Starts
```

Triggers define **when** the workflow executes.

---

# 7. Jobs

A workflow consists of one or more **jobs**.

Conceptually:

```text
Workflow

↓

Job A

↓

Job B

↓

Job C
```

Each job performs a logical stage of the pipeline.

Jobs may execute:

- sequentially;
- independently;
- in parallel.

---

# 8. Job Configuration

Each job specifies:

- execution environment;
- steps;
- dependencies;
- optional conditions.

Conceptually:

```text
Job

↓

Runner

↓

Steps
```

The job is the primary execution unit inside a workflow.

---

# 9. Runners

A **runner** is the machine responsible for executing a job.

GitHub provides hosted runners such as:

- Ubuntu Linux;
- Windows;
- macOS.

Conceptually:

```text
Job

↓

Runner

↓

Execution
```

For Java projects, Linux runners are the most common choice.

---

# 10. Steps

Each job is divided into **steps**.

Conceptually:

```text
Job

↓

Step 1

↓

Step 2

↓

Step 3
```

Each step performs one specific action.

Examples include:

- checking out the repository;
- installing Java;
- executing Maven;
- uploading artifacts.

---

# 11. Actions

An **Action** is a reusable automation component.

Conceptually:

```text
Workflow

↓

Action

↓

Reusable Logic
```

Instead of rewriting common tasks, workflows reuse existing actions published by GitHub or the community.

Examples include:

- repository checkout;
- Java installation;
- artifact upload.

---

# 12. Commands

Some workflow steps execute shell commands directly.

Conceptually:

```text
Step

↓

Shell Command

↓

Execution
```

Typical Java commands include:

```bash
mvn compile
```

```bash
mvn test
```

```bash
mvn package
```

These commands are executed automatically inside the workflow.

---

# 13. Environment Variables

Workflows may define environment variables.

Conceptually:

```text
Environment Variable

↓

Workflow

↓

Jobs

↓

Steps
```

Variables simplify configuration and reduce duplication.

Examples include:

- Java version;
- Maven options;
- application configuration.

---

# 14. Dependencies Between Jobs

Some jobs depend on others.

Conceptually:

```text
Build

↓

Tests

↓

Package
```

Packaging should occur only after successful compilation and testing.

Job dependencies help enforce the correct execution order.

---

# 15. Workflow Execution

A typical Java workflow executes as follows.

```text
Push
        ↓
Checkout Repository
        ↓
Install Java
        ↓
Restore Dependencies
        ↓
Compile
        ↓
Run Tests
        ↓
Package
        ↓
Upload Artifact
```

Each stage contributes to software validation.

---

# 16. Failure Handling

If one step fails:

```text
Step Failure

↓

Job Failure

↓

Workflow Stops
```

Subsequent steps are generally not executed unless explicitly configured otherwise.

This behavior prevents invalid builds from progressing through the pipeline.

---

# 17. Workflow Visualization

GitHub displays workflow execution graphically.

Conceptually:

```text
Workflow

↓

Job

↓

Step

↓

Result
```

Developers can inspect:

- execution logs;
- step duration;
- failures;
- successful executions.

This improves debugging and pipeline maintenance.

---

# 18. Java Perspective

A professional Java workflow commonly includes:

- repository checkout;
- JDK installation;
- Maven dependency resolution;
- project compilation;
- JUnit execution;
- AssertJ validation;
- artifact generation.

These activities are executed automatically after every meaningful repository event.

---

# 19. Engineering Perspective

Professional software engineers rarely ask:

```text
Which command
should I run
next?
```

Instead, they ask:

```text
Can the
workflow
execute every
step
automatically?
```

```text
Can every
developer
use the
same
pipeline?
```

```text
Is the
workflow
reproducible?
```

Well-designed workflows improve reliability, maintainability, and team collaboration.

---

# 20. Relationship with the Next Document

This document introduced the internal structure of GitHub Actions workflows, explaining how triggers, jobs, steps, actions, runners, and environment variables work together to automate software development.

The next document studies **Maven CI Pipeline**, demonstrating how these workflow components are combined to create a complete Continuous Integration pipeline for professional Java projects using Maven.

---

# 21. Key Takeaways

After completing this document, the learner should understand that:

- GitHub Actions workflows are defined as YAML files stored in the `.github/workflows/` directory;
- workflows are triggered by repository events such as pushes, pull requests, scheduled executions, or manual requests;
- workflows consist of jobs, and jobs are composed of individual steps;
- runners provide the execution environment in which workflow jobs are performed;
- reusable actions simplify workflow development by encapsulating common automation tasks;
- shell commands allow workflows to compile, test, and package Java applications automatically;
- job dependencies define the correct execution order within a pipeline;
- understanding workflow anatomy is essential for designing reliable, maintainable, and reproducible CI/CD pipelines for professional Java development.