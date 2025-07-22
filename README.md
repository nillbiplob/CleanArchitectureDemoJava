## What is Clean Architecture?

This project uses Clean Architecture principles, which enforce strong separation of concerns between layers:

- **Inner layers** know nothing about **outer layers**.
- The **Domain Layer** is at the center, containing business logic and interfaces, and is independent of frameworks and tools.
- The **Application Layer** (use cases) orchestrates business rules, acting as a bridge between UI and data.
- The **Data Layer** implements interfaces defined in the Domain layer, fetching and storing data from remote and local sources.
- The **Presentation Layer** is responsible for the UI and interacts only with the Application/Domain, never with Data directly.

This structure ensures your codebase is highly testable, maintainable, and scalable.

![Clean Architecture Diagram](https://i.imgur.com/hRQWmOa.png)
