
# CleanArchitectureDemoJava

A reference Android app demonstrating **Clean Architecture** in Java, using a real public API, simple caching, and a modern UI.

---

## ✨ Features

- **Splash Screen:** Loads user data from the internet or cache, with a friendly minimum 3-second splash.
- **User List Screen:** Shows all users, supports pull-to-refresh, manual refresh, fast search with clear icon.
- **User Detail Screen:** Taps any user to see all details with a clean back navigation.
- **Offline Support:** Uses SharedPreferences to cache API results for offline use.
- **Clean Architecture:** All core layers (Presentation, Application, Domain, Data) are strictly separated.
- **Minimal 3rd-Party Dependencies:** Only [Gson](https://github.com/google/gson) for JSON parsing.

---



## 🏛️ Clean Architecture Overview

> **"Inner layers know nothing about outer layers."**

This app enforces strong separation between:

- **Presentation Layer:** Activities/Adapters/UI code (no business logic or data code)
- **Application Layer:** Use cases, orchestrating business rules (no data/UI dependencies)
- **Domain Layer:** Pure models & repository interfaces (no frameworks or Android code)
- **Data Layer:** API, cache, repository implementations (only this knows about Gson, SharedPreferences, etc.)

**Why?**
- Code is modular, maintainable, and testable.
- You can swap the network/cache logic, or refactor UI, without breaking everything.

<img width="800" height="800" alt="Clean-Architecture-1" src="https://github.com/user-attachments/assets/8b1dc1ce-befb-4700-9a99-5d36a160b11b" />


### 🗂️ Project Structure

- com.biplob.cleanarchitecturedemo
- │
- ├── application/
- │ └── usecase/ # Use cases (Application Layer)
- │
- ├── data/
- │ ├── model/ # API DTOs (UserDto)
- │ ├── repository/ # Repository implementations
- │ └── source/ # Data sources (Remote/Local)
- │
- ├── domain/
- │ ├── model/ # Pure business models (User)
- │ └── repository/ # Repository interfaces
- │
- └── presentation/
- ├── common/ # Adapter, Singleton holder, etc.
- ├── splash/ # SplashActivity
- ├── userdetail/ # UserDetailActivity
- └── userlist/ # UserListActivity


---

### 🔗 Public API Used

- [JSONPlaceholder /users](https://jsonplaceholder.typicode.com/users)

---

### 🛠️ Tech Stack

- Java (Android SDK)
- [Gson](https://github.com/google/gson) (JSON serialization)
- SharedPreferences (for caching)
- Standard Android UI (RecyclerView, Activities, etc.)
- No DI frameworks, no Room/SQLite, no heavy libraries.

---

## 🚀 How To Run

1. Clone this repo.
2. Open in Android Studio.
3. Build & run on any emulator or real device (API 24+).
4. App will fetch users on first run.  
   If offline, will show last cached users (if available).

---

## 🧑‍💻 Code Flow Example

1. **SplashActivity** calls `GetUsersUseCase`
2. **GetUsersUseCase** (Application Layer) calls `UserRepository` interface
3. **UserRepositoryImpl** (Data Layer) loads from API/cache, converts DTOs to domain models
4. **UserListActivity** displays list, handles search/filter/refresh
5. **UserDetailActivity** shows selected user (passed via singleton holder)

---

## 📐 Clean Architecture Diagram

Presentation (Activities, Adapter)
↓
Application (UseCase)
↓
Domain (Repository Interface, Entity)
↓
Data (Repository Impl, Data Sources, DTOs)


---

## 🏷️ License

[MIT License](LICENSE)

---

## 👨‍💻 Author

- [Shafiul Alam Biplob](https://github.com/nillbiplob)
- Inspired by Uncle Bob’s Clean Architecture, Android community best practices

---

## 💡 Want More?

- This project is a perfect reference for porting to Kotlin, Flutter, or modern MVVM + Jetpack Compose.
- For the exact **Flutter Clean Architecture version**, see the next repo!


## 🚦 Demo

- | Splash Screen | User List & Search | User Detail |
- |--------------|--------------------|-------------|

  
<img width="1224" height="2570" alt="Screenshot_20250723_003911" src="https://github.com/user-attachments/assets/87c35d44-16ee-4ffb-89b7-7fc4b94440e9" />

<img width="1224" height="2570" alt="Screenshot_20250723_003918" src="https://github.com/user-attachments/assets/67556fac-e97d-4bc7-90e7-a5480659fb16" />

<img width="1224" height="2570" alt="Screenshot_20250723_003927" src="https://github.com/user-attachments/assets/607145f6-7eac-4081-b936-b02f614dfe17" />



