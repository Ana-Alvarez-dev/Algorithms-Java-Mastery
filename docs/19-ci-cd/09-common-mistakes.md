# Common Mistakes

## Algorithms Java Mastery

Continuous Integration and Continuous Delivery (CI/CD) were introduced to make software development **more reliable, reproducible, and automated**. However, simply using tools such as GitHub Actions or Maven does not guarantee that a project follows good engineering practices. Poorly designed pipelines may still produce unstable builds, unreliable deployments, wasted computational resources, and reduced developer confidence.

Most CI/CD problems are not caused by the automation platform itself but by incorrect workflow design, insufficient testing, inconsistent environments, or misunderstanding the purpose of automation. A pipeline should not merely execute commands—it should serve as an objective mechanism for verifying software quality and ensuring that every validated build can progress safely through the software delivery lifecycle.

Professional software engineers continuously improve their pipelines. They monitor failures, simplify workflows, remove unnecessary complexity, automate repetitive tasks, and establish quality gates that protect the integrity of the codebase. Understanding the most common mistakes is therefore essential for designing CI/CD systems that remain reliable as projects and development teams grow.

This document summarizes the conceptual and practical mistakes frequently encountered in Continuous Integration and Continuous Delivery, together with the engineering principles used to avoid them.

---

# 1. Purpose

The purpose of this document is to:

- identify common mistakes in CI/CD pipelines;
- explain why these mistakes reduce software quality;
- introduce professional engineering practices;
- reinforce automation principles;
- conclude the CI/CD module.

---

# 2. Treating CI/CD as a Tool Instead of a Practice

A common misconception is believing that installing GitHub Actions automatically implements Continuous Integration.

Incorrect mindset:

```text
GitHub Actions

↓

CI Implemented
```

Correct mindset:

```text
Engineering Practice

↓

Automation

↓

Reliable Workflow
```

CI/CD is an engineering practice supported by automation—not automation itself.

---

# 3. Committing Large Changes

Large, infrequent commits increase integration complexity.

Conceptually:

```text
Several Days

↓

Large Commit

↓

Merge Conflicts
```

Professional teams prefer:

```text
Small Change

↓

Commit

↓

Automatic Validation
```

Frequent integration simplifies debugging and reduces integration risk.

---

# 4. Ignoring Failing Pipelines

One of the most damaging habits is allowing failing pipelines to remain unresolved.

Conceptually:

```text
Pipeline Failure

↓

Ignored

↓

Reduced Trust
```

A failing pipeline should become the highest development priority.

If developers stop trusting the pipeline, the CI process loses its value.

---

# 5. Skipping Automated Tests

A pipeline that compiles software but does not execute automated tests provides only partial validation.

Incorrect workflow:

```text
Compile

↓

Package
```

Professional workflow:

```text
Compile

↓

Tests

↓

Quality Checks

↓

Package
```

Testing remains a mandatory stage of every CI pipeline.

---

# 6. Weak Test Suites

Executing automated tests is not sufficient if those tests provide poor coverage or verify only trivial behavior.

Examples include:

- tests without meaningful assertions;
- duplicated test cases;
- missing edge cases;
- insufficient regression testing.

A small number of high-quality tests is generally more valuable than many ineffective tests.

---

# 7. Ignoring Code Quality

Passing tests does not guarantee maintainable software.

Ignoring:

- static analysis;
- code smells;
- excessive complexity;
- duplicated code;

gradually increases technical debt.

Professional pipelines integrate automated quality verification.

---

# 8. Hardcoding Configuration

Embedding configuration values directly inside source code reduces portability.

Incorrect:

```text
Database URL

↓

Source Code
```

Correct:

```text
Environment Variables

↓

Application Configuration
```

Environment-specific values should remain external to the application.

---

# 9. Inconsistent Build Environments

Developers sometimes build software using different Java versions or tool configurations.

Example:

```text
Developer A

↓

Java 21
```

```text
Developer B

↓

Java 17
```

This inconsistency produces unpredictable build results.

Professional pipelines define a standardized execution environment.

---

# 10. Rebuilding Instead of Reusing Artifacts

A validated artifact should be reused throughout deployment.

Incorrect:

```text
Build

↓

Deploy

↓

Build Again
```

Correct:

```text
Build Once

↓

Artifact

↓

Reuse
```

Rebuilding introduces unnecessary risk.

---

# 11. Manual Repetitive Tasks

Frequent manual activities reduce reproducibility.

Examples include:

- manual compilation;
- manual testing;
- manual packaging;
- manual deployment preparation.

Professional pipelines automate repetitive processes whenever practical.

---

# 12. Overly Complex Pipelines

Automation should simplify development—not make it more difficult.

Signs of unnecessary complexity include:

- duplicated workflow logic;
- excessive scripting;
- unclear execution order;
- difficult maintenance.

Simple workflows are generally easier to understand and maintain.

---

# 13. Missing Version Control Best Practices

CI/CD depends upon disciplined version control.

Poor practices include:

- committing directly to the main branch without validation;
- unclear commit history;
- inconsistent branching strategies;
- infrequent integration.

Git workflows should support automated validation rather than bypass it.

---

# 14. Ignoring Pipeline Performance

Slow pipelines reduce developer productivity.

Conceptually:

```text
Commit

↓

Long Wait

↓

Delayed Feedback
```

Developers benefit most from fast, reliable feedback.

Pipeline optimization becomes increasingly important as projects grow.

---

# 15. Ignoring Build Reports

Many pipelines generate valuable information such as:

- test reports;
- coverage reports;
- static analysis reports;
- build logs.

Ignoring these reports wastes important diagnostic information.

Developers should regularly review pipeline output to identify opportunities for improvement.

---

# 16. Failing to Maintain Pipelines

CI/CD pipelines are software projects themselves.

Conceptually:

```text
Pipeline

↓

Maintenance

↓

Reliable Automation
```

Dependencies, workflows, and automation scripts require continuous maintenance as technologies evolve.

---

# 17. Engineering Best Practices

Professional software engineers typically follow these principles:

- integrate code frequently;
- automate every repeatable task;
- keep pipelines simple and readable;
- maintain fast feedback cycles;
- execute automated tests on every meaningful change;
- enforce quality gates;
- version and reuse artifacts;
- standardize development environments;
- continuously improve workflow reliability.

These practices increase confidence throughout the software development lifecycle.

---

# 18. Relationship with Previous Modules

Avoiding CI/CD mistakes requires knowledge acquired throughout the repository.

```text
Algorithms
        ↓
Java
        ↓
Testing
        ↓
Benchmarking
        ↓
Linux
        ↓
Git
        ↓
Maven
        ↓
GitHub Actions
        ↓
CI/CD
```

Each module contributes to building reliable automation pipelines.

---

# 19. Engineering Perspective

Professional software engineers rarely ask:

```text
Does the
pipeline
run?
```

Instead, they ask:

```text
Can every
developer
trust it?
```

```text
Will it
produce the
same result
tomorrow?
```

```text
Can the
pipeline
grow with
the project?
```

Reliable automation depends on simplicity, consistency, and continuous improvement.

---

# 20. Relationship with the Repository

This document concludes the **CI/CD** module.

The learner has now studied:

```text
Continuous Integration
        ↓
Continuous Delivery
        ↓
GitHub Actions
        ↓
Workflow Anatomy
        ↓
Maven CI Pipeline
        ↓
Automated Testing
        ↓
Code Quality Checks
        ↓
Build Artifacts
        ↓
Common Mistakes
```

Together, these topics provide a complete academic foundation for understanding how modern Java projects automate software validation, quality assurance, artifact generation, and delivery through professional CI/CD pipelines.

These concepts prepare the learner for advanced topics such as **Docker**, **Kubernetes**, **Cloud Computing**, **DevOps**, and **enterprise deployment architectures**, where automated software delivery is a fundamental engineering requirement.

---

# 21. Key Takeaways

After completing this document, the learner should understand that:

- CI/CD is an engineering practice supported by automation, not merely a collection of tools;
- small, frequent integrations reduce merge conflicts and simplify software validation;
- failing pipelines should be addressed immediately to preserve confidence in the automation process;
- automated testing, static analysis, and quality gates are essential components of reliable software delivery;
- standardized environments and immutable build artifacts improve reproducibility and deployment consistency;
- repetitive manual tasks should be automated whenever practical;
- simple, maintainable workflows are more valuable than unnecessarily complex pipelines;
- continuous improvement of CI/CD pipelines is essential for professional Java development, DevOps, and enterprise software engineering.