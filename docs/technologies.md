# Project Technologies

This document outlines the main technologies used in the project.

## Primary Language

**Kotlin**

Kotlin is the official language for Android development recommended by Google. It offers modern syntax, null safety by design, and full interoperability with Java.

## Base Versions

- **Minimum SDK version**: 27 (Android 8.1 - Oreo)  
  Selected to support modern Android features while covering the majority of active Android devices.

## Adopted Architecture

**MVVM (Model-View-ViewModel)**

MVVM was chosen for its clear separation of concerns, scalability in modular projects, and seamless integration with tools like LiveData, ViewModel, and Jetpack Compose.

## Key Frameworks and Libraries

- **Hilt**  
  Official dependency injection framework for Android by Google. Simplifies setup and encourages a clean architecture.

- **Retrofit**  
  A type-safe HTTP client that simplifies communication with REST APIs, supporting easy error handling and testing.

- **Room**  
  A persistence library that provides an abstraction layer over SQLite, with support for type safety and reactive data via LiveData.

- **Navigation Component**  
  Manages screen-to-screen navigation, argument passing, and back stack handling, reducing common fragment-related issues.

---
