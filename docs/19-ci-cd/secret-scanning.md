# Secret Scanning with Gitleaks

## Purpose and scope

This guide documents secret scanning for **Algorithms-Java-Mastery**. Its objective is to identify credentials accidentally committed to Git history or present in the current working tree. Secret scanning is a focused security control, not a complete security audit: it does not establish dependency safety, application correctness, or the absence of all secrets.

## Tool and environment

- Tool: [Gitleaks](https://github.com/gitleaks/gitleaks), using its modern `git` and `dir` commands.
- Local environment: Windows 11 and PowerShell.
- Hosted environment: GitHub Actions on Ubuntu.
- Repository: `Ana-Alvarez-dev/Algorithms-Java-Mastery`.

## Local procedure

From the repository root:

```powershell
git status
gitleaks version
gitleaks git . --redact
gitleaks dir . --redact
```

- `git` examines accessible Git history, including earlier committed versions of files.
- `dir` examines the current directory contents, including uncommitted files (subject to Gitleaks exclusions and configuration).
- `--redact` masks detected secret values in displayed findings; avoid exporting raw findings to public artifacts.
- For reliable historical coverage, use a complete local clone rather than a shallow clone.

The commands scan locally and do not push, delete, rewrite, or modify repository history.

## Observed local audit — 2026-11-01

These values are **user-reported terminal observations**, not independently reproduced CI results.

| Scan | Scope | Amount scanned | Duration | Result |
| --- | --- | --- | --- | --- |
| `gitleaks git . --redact` | 136 commits | ~7.04 MB | 1.62 s | `no leaks found` |
| `gitleaks dir . --redact` | Working directory | ~8.09 MB | 1.33 s | `no leaks found` |

**Interpretation:** no matches were reported under the tool's active detection rules in these two runs. This is not proof that no secrets or vulnerabilities exist.

## Continuous integration

The independent workflow [`secret-scanning.yml`](../../.github/workflows/secret-scanning.yml) is designed to run on:

- Pull requests targeting `main`.
- Pushes to `main`.
- Manual `workflow_dispatch` requests.

It uses read-only repository permissions, disables checkout credential persistence, fetches full Git history, and invokes a versioned Gitleaks container. The scan fails the job when Gitleaks reports a finding. It does not upload raw secret reports.

The container image is version-tagged rather than digest-pinned. Reproducibility can be strengthened by reviewing and pinning its immutable digest. A passing workflow is only evidence for the particular checked-out revision and scanner configuration.

## Findings and incident response

1. **Validate carefully:** identify the rule, file, and commit without publishing the matched secret.
2. **Treat a genuine credential as compromised:** revoke or rotate it with its provider, and review usage or access logs where available.
3. **Remove the source of exposure:** replace hard-coded secrets with environment variables or an approved secrets manager.
4. **Assess historical exposure:** removing a value from the current file does not remove it from prior commits. History rewriting, when necessary, requires coordination because it affects collaborators and clones.
5. **Document the decision:** record remediation and any justified false positive without including the secret value.

Do not paste live credentials into issues, pull requests, chat, logs, or documentation. Avoid adding broad allowlists solely to silence detections.

## Java and backend-oriented prevention

- Do not commit `.env`, private keys, tokens, or real application credentials.
- Use safe example values in documentation and tests.
- Store deployment secrets in an appropriate secret store, not in `application.properties` or `application.yml` committed to Git.
- Keep `.gitignore` rules current, while remembering that ignoring a file does not remove already committed content.
- Run local checks before publishing sensitive changes.

## Verification and limitations

To complete hosted verification, inspect the workflow result in GitHub Actions for the new pull request and again after merging. Record the run URL, checked-out commit, tool version, and conclusion before claiming hosted success. **At document creation, hosted execution had not yet been verified.**

Secret scanning complements—not replaces—Maven tests, dependency analysis, access control, and security review.

## References

- [Gitleaks repository and usage](https://github.com/gitleaks/gitleaks)
- [GitHub Actions workflow syntax](https://docs.github.com/en/actions/reference/workflows-and-actions/workflow-syntax)
- [GitHub: Removing sensitive data from a repository](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/removing-sensitive-data-from-a-repository)
- [OWASP Secrets Management Cheat Sheet](https://cheatsheetseries.owasp.org/cheatsheets/Secrets_Management_Cheat_Sheet.html)
