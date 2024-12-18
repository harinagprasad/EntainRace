# Next to Go - Upcoming Races Android App

## Overview

The **Next To Go** app fetches and displays a list of upcoming races, providing users with a
streamlined experience to view and filter races by category. Designed using modern Android
development practices, the app ensures a polished and scalable user interface while adhering to
accessibility and error-handling standards.

---

## Features

### User Requirements

1. **Time-Ordered List**: Races are displayed in ascending order based on their advertised start
   time.
2. **Filtering Options**:
    - Filter races by categories: **Horse Racing**, **Harness Racing**, and **Greyhound Racing**.
    - Deselect all filters to show the next five races across all categories.
3. **Live Updates**:
    - Races older than one minute past the advertised start are automatically removed.
    - Data is refreshed to always show the next five races.
4. **Race Details**:
    - Display includes:
        - **Meeting Name**
        - **Race Number**
        - **Advertised Start** shown as a countdown.

---

## App Sample

https://github.com/user-attachments/assets/9e87b8c4-44c7-41f3-ab9d-14fb1ba62bce

---

### Technical Features

1. **Clean Architecture:**
    - Used this pattern to separate business logic from UI code and facilitate easier testing and
      maintenance.
2. **Jetpack Compose**:
    - For building UI components.
3. **ViewModel & Kotlin Flows:**
    - For managing UI-related data lifecycle-aware and observing changes in the UI.
4. **Repository Pattern:**
    - Abstracted data sources (Remote/Local storage) to provide a consistent interface.
5. **Dependency Injection:**
    - Used Dagger Hilt to manage dependencies and ensure easier testing and mocking.
6. **Room Database:**
    - For local storage of transaction history and user data.
7. **Coroutines:**
    - For handling asynchronous operations in a structured and concise manner.
8. **Accessibility**:
    - Supports font scaling and dark mode to enhance usability for all users.
9. **Testing**:
    - Includes unit tests for core functionalities.
10. **Code Quality**:
    - Adheres to Kotlin coding best practices, ensuring clean and maintainable code.

---

## Folder Structure

The project is structured as follows:

## Folder Descriptions

- **`data`**: Responsible for handling all data operations.
    - **`local`**: Manages local data using RoomDB.
    - **`remote`**: Handles API calls and network operations.
    - **`repository`**: Implements repositories to manage data flow.
    - **`source`**: Interfaces for Local and Remote data source.
- **`domain`**: Contains the core business logic of the application.
    - **`model`**: Domain models used throughout the app.
    - **`repository`**: Interfaces for repositories.
    - **`usecase`**: Implements specific business logic as use cases.
- **`presentation`**: Handles UI-related components and logic.
    - **`ui`**: Contains Jetpack Compose UI components.
    - **`theme`**: Defines the app's theme and styling.
    - **`components`**: UI components.
    - **`navigation`**: Manages navigation between screens.
- **`app'`**: Contains the Application class, Dagger Hilt setup, RoomDB setup.
- **`utils`**: Utility classes and extensions.

  This structure ensures a clear separation of concerns, enabling better modularity, testability,
  and maintainability.

---

## Installation

1. **Clone the repository** by -
    - git clone <repository-url>
2. Navigate to the app by -
   - cd next-to-go-app
3. Open the project in Android Studio.
4. Build and run the app on an emulator or physical device.

---

## Usage

1. **Races List**: By default, the app shows the next five races across all categories.
2. **Filter Races**: Use the filtering options to view races of a specific category (Horse Racing,
   Harness Racing, or Greyhound Racing).
3. **Countdown Timer**: Each race displays its advertised start time as a live countdown.
4. **Automatic Updates**: Data refreshes regularly to ensure you see the latest upcoming races.

---

## Testing

1. Run tests using:
   - ./gradlew test

## Deferred Improvements

Due to time constraints, the following improvements have been deferred but are worth considering for
future work:

**UI/UX Enhancements:** The current UI is functional but could benefit from UI/UX refinements, such
as more intuitive and better display of transaction history.
**Unit Tests and UI Tests:** While races have been modeled and is working, unit tests for specific
edge cases need further improvement.

---
