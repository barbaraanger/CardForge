# CardForge

CardForge is a distributed platform for coordinating group purchases of trading cards, enabling users to batch orders, reduce shipping costs, and streamline international sourcing.
CardForge is a platform for coordinating group purchases of trading cards, enabling users to batch orders, reduce shipping costs, and streamline international sourcing.

The system is built using a microservices architecture with event-driven communication to support scalable and resilient order processing.
The system is currently designed as a **modular monolith (Spring Modulith style)**, with clear domain boundaries and event-driven communication between modules. This keeps development fast while preserving a migration path to microservices if needed.

## Problem

Buying trading cards internationally is expensive due to shipping costs and fragmented orders.

Users often want to:
- Combine orders to reduce shipping fees
- Coordinate purchases with others
- Manage deadlines for group buys

However, there is no simple system to orchestrate these group purchases efficiently.

## Solution

CardForge enables users to create and join purchase batches.

Each batch:
- Has a deadline
- Aggregates multiple user orders
- Is processed as a single grouped purchase

The system handles:
- Batch lifecycle management
- Order aggregation
- Asynchronous processing of purchases

## Architecture Overview

CardForge follows a microservices architecture with event-driven communication.
CardForge follows a **modular monolith architecture** with event-oriented workflows.

Core services:
Core modules:

- **User Service** → manages users
- **Batch Service** → manages group purchase batches
- **Order Service** → handles user orders
- **Payment Service** → simulates payment processing
- **User Module** → manages users
- **Batch Module** → manages group purchase batches
- **Order Module** → handles user orders
- **Payment Module** → simulates payment processing

Services communicate via REST and asynchronous messaging (Kafka).
Modules communicate via:
- Internal application calls (synchronous)
- Domain/application events (asynchronous flow inside the monolith)
- External messaging integration via Kafka (for future service extraction and integration needs)

## Architecture Diagram
````
                   [ API Gateway ]
                    [ REST API ]
                         |
        -------------------------------------
        |           |           |           |
[User Service] [Batch Service] [Order Service]
|           |           |
[DB]        [DB]        [DB]

                 ↓ (events via Kafka)

                 [ Kafka / Event Bus ]
                         |
                  [ Payment Service ]
                         |
                       [DB]
        -----------------------------------------------
        |             |             |                  |
   [User Module] [Batch Module] [Order Module] [Payment Module]
        \             |             /                  /
         \------------|------------/------------------/
                      |
               [ Domain Events Bus ]
                      |
                 [ Shared Database ]

         (Optional external integration via Kafka)
````

## Key Flows

### Creating a Batch
1. User creates a batch
2. Batch Service stores batch with deadline
2. Batch Module stores batch with deadline
3. Event emitted: `BatchCreated`

### Joining a Batch
1. User submits order
2. Order Service validates and stores order
2. Order Module validates and stores order
3. Event emitted: `OrderCreated`

### Closing a Batch
1. Batch deadline reached
2. Batch Service emits `BatchClosed`
3. Order Service aggregates orders
4. Payment Service processes grouped payment
2. Batch Module emits `BatchClosed`
3. Order Module aggregates orders
4. Payment Module processes grouped payment

## Tech Stack

- Java (Spring Boot)
- Spring Modulith-style modularization (package/module boundaries)
- PostgreSQL
- Docker
- Kafka (event-driven communication)
- Kafka (optional external event integration and future extraction path)
- REST APIs


---

## Design Decisions

### Why microservices?
To separate concerns and allow independent scaling of batch, order, and payment processing.
### Why modular monolith first?
- Faster initial delivery with lower operational overhead
- Easier local development and debugging
- Strong domain separation without distributed-system complexity on day one
- Safer evolution path to microservices when scale/team constraints justify it

### Why event-driven architecture?
To handle asynchronous workflows such as batch closing and order aggregation.
### Why event-oriented workflows?
To handle asynchronous processes such as batch closing and order aggregation while keeping module boundaries explicit.

### Trade-offs
- Increased complexity compared to a monolith
- Requires handling eventual consistency
- Single deploy unit (less independent deploy flexibility vs microservices)
- Requires discipline to enforce module boundaries

## Future Improvements

- Implement authentication and authorization
- Add retry and failure handling mechanisms
- Introduce observability (logs, metrics, tracing)
- Deploy to AWS (ECS/EKS)
- Introduce module boundary tests and architecture validation
- Gradually extract modules to microservices when justified by load/team autonomy
- Deploy to AWS (ECS/EKS)