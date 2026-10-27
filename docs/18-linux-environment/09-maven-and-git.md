# Maven and Git

Git identifies tracked source history; Maven builds and tests that source using the selected environment. The Maven Wrapper pins 3.9.11 in this project, while a system-wide `mvn` can select another version. First wrapper use may require network access and a local cache.

The applied sequence is: pull main, preflight, wrapper version, clean tests, clean benchmark package, strict artifact capture, discovery, then a separately attributed controlled run if desired. Normal tests execute 710 JUnit invocations; `-Pbenchmark` adds 13 fixture cases for 723. The twenty shell subprocess cases are a separate count, not extra JUnit tests.

Capture `git rev-parse HEAD`, dirty state and jar SHA-256 immediately before timing. The aggregation author's reflog supported an inferred source of 5eee0dc before results merge 12f2973, but the absent jar left its original hash unavailable. Reflog describes checkout movement, not builds or working-tree cleanliness at execution.

A failed build is a stop point. Do not infer artifact freshness from a jar merely being present. Clean builds, discovery and source/artifact capture complement each other. Coverage should run separately because another `clean` replaces the generated artifact set. This module adds no GitHub Actions workflow; CI remains the next numbered subject.

## Applied verification

The [implementation and verification guide](implementation-and-verification-guide.md) connects these concepts to script contracts, commands and primary sources. The [verification report](verification-report.md) distinguishes real Linux/JDK checks from injected shell fixtures, historical research and pending author execution.
