# JDBC PostgreSQL Examples

A collection of Java examples demonstrating core JDBC concepts with PostgreSQL.

## What's included

- **HikariCP.java** – Sets up a connection pool using HikariCP for efficient, reusable database connections.
- **JDBC_Operations.java** – Demonstrates:
  - Basic `Connection` setup via `DriverManager`
  - `Statement` execution
  - `PreparedStatement` for parameterized inserts
  - `CallableStatement` for parameterized queries
  - `DatabaseMetaData` (driver/DB info)
  - `ResultSetMetaData` (column info)
- **JDBC_Transation.java** – Demonstrates transaction management using a pooled connection:
  - Disabling auto-commit
  - Savepoints
  - Rollback and commit

## Tech Stack
- Java
- JDBC
- PostgreSQL
- HikariCP (connection pooling)

## Setup
1. Update database credentials (`url`, `user`, `password`) in `HikariCP.java` and `JDBC_Operations.java`.
2. Ensure a PostgreSQL instance is running with a table named `example` (columns: `id`, `name`, `doj`).
3. Add the PostgreSQL JDBC driver and HikariCP dependencies to your classpath (or `pom.xml`/`build.gradle`).
4. Run the classes individually to see each JDBC feature in action.
