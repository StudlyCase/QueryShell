# QueryShell

**QueryShell** is a terminal-based SQL penetration testing tool for authorized security assessments. It helps systematically detect SQL injection vulnerabilities through multiple detection strategies and produces structured reports (terminal, JSON, CSV).

**Authorized security testing only.** QueryShell technically enforces an `--authorized` flag at startup. Using it against systems without explicit permission is unlawful and not the purpose of this project.

---

## Contents

- [Features](#features)
- [Architecture](#architecture)
- [Requirements](#requirements)
- [Build & Installation](#build--installation)
- [Usage](#usage)
- [Configuration](#configuration)
- [Payloads](#payloads)
- [Reports](#reports)
- [Project Status](#project-status)
- [Contributing](#contributing)
- [License](#license)

---

## Features

- **CLI-first**: fully operated from the terminal (picocli-based)
- **Modular detection engine**: swappable strategies for error-based, boolean-based, and time-based SQL injection
- **Database connectivity**: connection pooling via HikariCP, abstracted behind a generic `DbConnectionFactory` interface
- **External payload management**: payloads are loaded from YAML files at runtime — no hardcoded strings in code
- **Multiple report formats**: terminal table, JSON, CSV
- **Profile-based target configuration**: targets defined via YAML profiles (Jackson)
- **Clean resource management**: connection pooling and release checked for leaks

---

## Architecture

```
queryshell/
├── cli/                    # Entry point, argument parsing, terminal I/O
├── core/                   # Domain logic, orchestration (Connect → Detect → Report)
├── db/                     # Connection handling, DB-agnostic abstraction
├── detection/              # Injection detection engine, test strategies
├── payloads/               # Payload definitions (strategy pattern, pluggable)
├── report/                 # Output formatting (JSON, CSV, terminal table)
├── config/                 # Profile/session configuration
└── util/                   # Logging, exceptions, helpers
```

**Core flow:** `ScanOrchestrator` drives the **Connect → Detect → Report** sequence, tying together the CLI, DB layer, detection engine, and reporting modules.

---

## Requirements

| Tool | Version |
|---|---|
| Java (JDK) | 21 |
| Gradle | included via wrapper |
| MySQL target instance | 5.7+ / 8.x (for testing, see planned Docker setup) |

Developed and tested on **Windows 11 / PowerShell**.

---

## Build & Installation

```powershell
git clone https://github.com/StudlyCase/queryshell.git
cd queryshell
.\gradlew.bat build
```

Build the executable JAR:

```powershell
.\gradlew.bat shadowJar
```

---

## Usage

```powershell
java -jar queryshell.jar --authorized --profile targets/example-profile.yaml
```

The `--authorized` flag is **mandatory** — QueryShell refuses to start without it.

Additional flags (subject to final CLI scope):

| Flag | Description |
|---|---|
| `--authorized` | **Required.** Confirms the test is authorized |
| `--profile <path>` | Path to the target profile file (YAML) |
| `--report-format <fmt>` | `terminal` \| `json` \| `csv` |
| `--output <path>` | Output path for the report file |

---

## Configuration

Target systems are described via `TargetProfile` YAML files and loaded through `ProfileLoader` (Jackson). Example structure:

```yaml
target:
  host: localhost
  port: 3306
  database: testdb
  username: pentest_user
  password: ${DB_PASSWORD}
scan:
  strategies:
    - error-based
    - boolean-based
    - time-based
```

Central application configuration lives in `AppConfig`.

---

## Payloads

Payloads are deliberately **not stored in code**; they are loaded at runtime from external YAML files via `PayloadRepository`. This allows:

- Extension without code changes
- Separation of logic and test data
- Controlled, version-controlled payload sets per engagement

---

## Reports

QueryShell supports three output formats:

- **Terminal table** — for direct visual inspection during a scan
- **JSON** — for downstream processing/automation
- **CSV** — for reporting/documentation

---

## License

This project is licensed under the **GNU General Public License v3.0**. See [LICENSE](LICENSE).