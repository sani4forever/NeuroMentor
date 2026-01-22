# 🧠 NeuroMentor

<div align="center">

**AI-powered neuropsychology assistant for mental well-being**

[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://www.android.com/)
[![Kotlin](https://img.shields.io/badge/Language-Kotlin-purple.svg)](https://kotlinlang.org/)
[![API](https://img.shields.io/badge/API-24%2B-brightgreen.svg)](https://android-arsenal.com/api?level=24)

</div>

---

## 📋 Table of Contents

- [📱 Overview](#-overview)
- [✨ Features](#-features)
- [📸 Screenshots](#-screenshots)
- [🛠️ Tech Stack](#️-tech-stack)
- [📦 Installation](#-installation)
- [🏗️ Project Structure](#️-project-structure)
- [🤝 Contributing](#-contributing)
- [👨‍💻 Authors](#authors)

---

## 📱 Overview

NeuroMentor is an intelligent Android application that serves as your personal neuropsychology assistant. Leveraging advanced AI technology, it provides personalized mental health support through an intuitive chat interface.

## ✨ Features

- 🤖 **AI-Powered Conversations** - Intelligent responses tailored to your mental well-being needs
- 👤 **Personalized Onboarding** - Custom profile creation with name, gender, and age
- 💬 **Real-time Chat Interface** - Smooth, responsive messaging experience
- 🔒 **Privacy First** - Secure data storage using DataStore
- 🌐 **Offline Detection** - Graceful handling of network connectivity issues
- 🎨 **Modern UI/UX** - Clean, minimalist design with dark theme

## 📸 Screenshots

<div align="center">

### Connection Status
<img src="https://github.com/user-attachments/assets/49fd9e06-e486-420a-9c53-9216b3b9ceda" width="250" alt="No Connection"/>

### Chat Interface
<img src="https://github.com/user-attachments/assets/096b0843-fec1-4f7f-b489-6c34d111940a" width="250" alt="Chat"/>

### Onboarding Flow

<table>
<tr>
<td><img src="https://github.com/user-attachments/assets/208e855b-fa6c-4788-8e3d-eee8c3d55e47" width="250" alt="Name Input"/></td>
<td><img src="https://github.com/user-attachments/assets/57f3b18c-33b6-4590-92fe-5b381f34faca" width="250" alt="Gender Selection"/></td>
<td><img src="https://github.com/user-attachments/assets/f0972c17-8de2-4d51-9460-71001b2f8cf6" width="250" alt="Age Input"/></td>
</tr>
<tr>
<td align="center"><b>Name Input</b></td>
<td align="center"><b>Gender Selection</b></td>
<td align="center"><b>Age Input</b></td>
</tr>
</table>

### 🎥 Demo Video
https://github.com/user-attachments/assets/2f35f8e3-71ba-4f4c-9c3c-7cae419b6605

</div>

## 🛠️ Tech Stack

### Core Technologies
- **Language**: Kotlin
- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 36 (Android 16)

### Architecture & Design Patterns
- **Architecture**: MVVM (Model-View-ViewModel)
- **Dependency Injection**: Koin
- **Navigation**: Android Navigation Component
- **Asynchronous**: Kotlin Coroutines & Flow

### Libraries & Frameworks

**UI Components**
- Material Design 3 — Modern UI components and theming
- ConstraintLayout — Flexible and responsive layouts
- ViewBinding — Type-safe view access

**Networking**
- Retrofit — Type-safe REST API client
- OkHttp — Advanced HTTP client with interceptors
- Gson — JSON serialization and deserialization

**Data & Storage**
- DataStore Preferences — Modern key-value storage replacing SharedPreferences

**Architecture Components**
- ViewModel — UI state management with lifecycle awareness
- LiveData — Lifecycle-aware observable data holder
- Navigation Component — Fragment navigation and argument passing


## 📦 Installation

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/sani4forever/NeuroMentor.git
   cd NeuroMentor
   ```

2. **Configure API endpoint**
   
   Update the base URL in `di/AppModule.kt`:
   ```kotlin
   const val BASE_URL = "YOUR_API_URL"
   ```

3. **Build and run**
   
   Simply click ▶️ Run in Android Studio

## 🏗️ Project Structure

```
app/src/main/
├── 📱 AndroidManifest.xml
│
├── 💻 java/com/example/neuromentor/
│   │
│   ├── 🔌 di/
│   │   ├── App.kt                          # Application class
│   │   └── AppModule.kt                    # Koin DI configuration
│   │
│   ├── 🌐 domain/
│   │   ├── api/
│   │   │   └── ChatApi.kt                  # Retrofit API interface
│   │   ├── datastore/
│   │   │   └── DataStoreManager.kt         # User preferences storage
│   │   ├── models/
│   │   │   └── NetworkModels.kt            # API request/response models
│   │   └── repository/
│   │       └── ChatRepository.kt           # Data layer abstraction
│   │
│   ├── 📦 models/
│   │   ├── ChatMessage.kt                  # Chat message data class
│   │   └── Gender.kt                       # Gender enum
│   │
│   ├── 🎨 ui/
│   │   ├── activities/
│   │   │   └── MainActivity.kt             # Single activity host
│   │   ├── fragments/
│   │   │   ├── StartFragment.kt            # Splash/connection check
│   │   │   ├── NameFragment.kt             # Onboarding: name input
│   │   │   ├── GenderFragment.kt           # Onboarding: gender selection
│   │   │   ├── AgeFragment.kt              # Onboarding: age input
│   │   │   ├── ChatFragment.kt             # Main chat interface
│   │   │   └── ClearDialogContextFragment.kt  # Clear chat dialog
│   │   └── recyclerview/
│   │       └── ChatAdapter.kt              # RecyclerView adapter for messages
│   │
│   └── 🧠 viewmodels/
│       ├── StartViewModel.kt               # Startup logic
│       ├── PersonInfoViewModel.kt          # Shared user profile data
│       └── ChatViewModel.kt                # Chat state management
│
└── 🎨 res/
    ├── drawable/                           # Vector drawables & backgrounds
    ├── layout/                             # XML layouts
    ├── navigation/
    │   └── nav_graph.xml                   # Navigation graph
    ├── values/
    │   ├── colors.xml                      # Color palette
    │   ├── strings.xml                     # String resources
    │   └── themes.xml                      # App themes
    └── mipmap-*/                           # App icons
```

## 🔑 Key Components

### Dependency Injection (Koin)
```kotlin
val appModule = module {
    viewModel { PersonInfoViewModel(get(), get()) }
    viewModel { ChatViewModel(get()) }
    viewModel { StartViewModel(get()) }
    single { ChatRepository(get()) }
    single { DataStoreManager(get()) }
}
```

### ViewModels
- **`PersonInfoViewModel`** - Manages user profile data (shared across fragments using `activityViewModel()`)
- **`ChatViewModel`** - Handles chat functionality and message state
- **`StartViewModel`** - Controls app initialization and routing logic

### Navigation Flow
```
┌─────────────────┐
│  StartFragment  │
└────────┬────────┘
         │
    ┌────┴────┐
    │         │
    ▼         ▼
User exists   New user
    │         │
    │    ┌────┴────────┐
    │    │NameFragment │
    │    └────┬────────┘
    │         │
    │    ┌────┴──────────┐
    │    │GenderFragment │
    │    └────┬──────────┘
    │         │
    │    ┌────┴────────┐
    │    │AgeFragment  │
    │    └────┬────────┘
    │         │
    └────┬────┘
         │
    ┌────▼────────┐
    │ChatFragment │
    └─────────────┘
```


## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 👨‍💻 Authors

| Developer | GitHub |
|-----------|--------|
| **Aleksandr** | [@sani4forever](https://github.com/sani4forever) |
| **Matvei** | [@M4TE5](https://github.com/M4TE5) |

---

<div align="center">

**Made with 🧠 by NeuroMentor Team**

</div>
