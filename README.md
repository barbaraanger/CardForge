# CardForge

CardForge is a distributed platform for coordinating group purchases of trading cards, enabling users to batch orders, reduce shipping costs, and streamline international sourcing.

The system is built using a microservices architecture with event-driven communication to support scalable and resilient order processing.

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

Core services:

- **User Service** → manages users
- **Batch Service** → manages group purchase batches
- **Order Service** → handles user orders
- **Payment Service** → simulates payment processing

Services communicate via REST and asynchronous messaging (Kafka).

## Architecture Diagram
````
                [ Client / Frontend ]
                         |
                   [ API Gateway ]
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
````

## Key Flows

### Creating a Batch
1. User creates a batch
2. Batch Service stores batch with deadline
3. Event emitted: `BatchCreated`

### Joining a Batch
1. User submits order
2. Order Service validates and stores order
3. Event emitted: `OrderCreated`

### Closing a Batch
1. Batch deadline reached
2. Batch Service emits `BatchClosed`
3. Order Service aggregates orders
4. Payment Service processes grouped payment

## Tech Stack

- Java (Spring Boot)
- PostgreSQL
- Docker
- Kafka (event-driven communication)
- REST APIs


---

## Design Decisions

### Why microservices?
To separate concerns and allow independent scaling of batch, order, and payment processing.

### Why event-driven architecture?
To handle asynchronous workflows such as batch closing and order aggregation.

### Trade-offs
- Increased complexity compared to a monolith
- Requires handling eventual consistency

## Future Improvements

- Implement authentication and authorization
- Add retry and failure handling mechanisms
- Introduce observability (logs, metrics, tracing)
- Deploy to AWS (ECS/EKS)