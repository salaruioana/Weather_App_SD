# Geospatial Weather Service

A Spring Boot weather forecasting application built as part of a Distributed Systems laboratory, refactored to demonstrate clean architecture principles and SOLID design.

## Overview

The project starts from a basic weather service structure and progressively applies software engineering best practices — decoupling components through interfaces, enforcing SOLID principles, and introducing a geospatial access control layer.

## Architecture

The application is organized around the Spring container with the following layers:

- **Controllers** — handle incoming HTTP requests and delegate to services
- **Interfaces** — define contracts between components to enable decoupling
- **Services** — contain business logic, each implementing a dedicated interface

## Key Design Decisions

### Dependency Inversion
Components depend on interfaces rather than concrete implementations, making them easy to swap and test in isolation. For example, `TimeInterface` decouples the forecast service from the system clock, allowing time to be injected or mocked independently.

### Open/Closed Principle
New weather providers can be added without modifying the controller. The system is open to extension but closed to modification at the controller level.

### Interface Segregation
Interfaces are kept atomic and focused — location lookup and weather forecasting are defined as separate contracts. A dedicated `LocationModel` class acts as a translation layer, making it straightforward to migrate between different location data formats.

## Geospatial Blacklist Service

A geographic access control feature was introduced to restrict weather data based on the physical location of the requesting node (as determined by the local OS configuration).

**How it works:**
1. On each incoming request, the node's current location is resolved.
2. Before processing, the location is checked against a configurable blacklist (provided as a relative path, absolute path, or URL).
3. If the location is on the blacklist, the service returns an access denied response indicating that information for that region is unavailable.

**Components:**
- `BlacklistInterface` — defines the contract for location validation
- `BlacklistService` — implements the interface, loads the blacklist source, and performs the lookup

## Technologies

- Java
- Spring Boot
- OOP / SOLID Principles
