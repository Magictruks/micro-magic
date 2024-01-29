# Documentation

Documentation of micro-magic

## Prerequisites

We assume that everyone who comes here is **`programmer with intermediate knowledge`** and we also need to understand more before we begin in order to reduce the knowledge gap.

1. Understand [Spring Fundamental], Main Framework. Java Framework
1. Understand The SOLID Principle and KISS Principle for better write the code.
2. Understand Microservice Architecture, Clean Architecture, and/or Hexagonal Architecture.
3. Understand Repository Design Pattern or Data Access Object Design Pattern.
4. Understanding [Docker][ref-docker].

## Build with

Describes which version.

## Objective

* Easy to maintenance
* Stateless authentication and authorization
* Repository Design Pattern or Data Access Layer Design Pattern
* Adopt SOLID and KISS principle
* Support for Microservice Architecture, Clean Architecture

## Features
### Main Features

* Spring 3
* Authentication (`Access Token`)
* Multi-tenant with MongoDB


### Database

* MongoDB
* Multi Database (MongoDB)
* Can use SQL

### Security
-###-

### Setting
-###-

### Others

* Support Docker installation
* Support CI/CD (eg: Gitlab pipeline)

## Third Party Integration
-###-

# Installation

## Getting Started

Before start, we need to install some packages and tools.
The recommended version is the LTS version for every tool and package.

> Make sure to check that the tools have been installed successfully.

1. [Java][ref-java]
2. [Maven][ref-maven]
3. [Git][ref-git]

### Clone Repo

Clone the project with git.

```bash
git clone https://gitlab.com/Magictruks/micro-magic.git
```

### Install Dependencies

This project needs some dependencies. Let's go install it.

```bash
mvn clean package
```

### Setup Docker

If you want to use Docker, follow this steps

```bash
cp .env.docker.example .env.docker
```

Modify env file as you need, and build your images

```bash
docker build .
```

Run your containers

```bash
docker compose up --force-recreate
```

#### For MySQL Database

You need to create Database. Login to your MySQL Container

```bash
mysql -h 127.0.0.1 -P 3306 -u root -p
```

Create your database

```bash
CREATE DATATABLE db_name
```

Restart your container

```bash
docker compose up
```

# Get projet version
mvn -q -Dexec.executable=echo -Dexec.args='${project.version}' --non-recursive exec:exec 2>/dev/null 