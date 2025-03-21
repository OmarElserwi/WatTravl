# WatTravl 🌍

<div align="center">

  [![Kotlin](https://img.shields.io/badge/Kotlin-1.8.0-purple.svg)](https://kotlinlang.org/)
  [![Android](https://img.shields.io/badge/Android-API%2024-green.svg)](https://developer.android.com/)
  [![Gradle](https://img.shields.io/badge/Gradle-7.5-blue.svg)](https://gradle.org/)
  
  A modern navigation application for University of Waterloo students that helps users find the shortest paths between classrooms and destinations within campus buildings.
</div>

## 📱 Demo

[![WatTravl Demo](https://img.youtube.com/vi/rKdbXoNlu4o/maxresdefault.jpg)](https://www.youtube.com/watch?v=rKdbXoNlu4o)
[Watch Demo Video](https://www.youtube.com/watch?v=rKdbXoNlu4o)


## ✨ Features

<div align="center">
  <table>
    <tr>
      <td align="center">🏢</td>
      <td align="center">🗺️</td>
      <td align="center">🔒</td>
      <td align="center">🎨</td>
    </tr>
    <tr>
      <td align="center">Indoor Navigation</td>
      <td align="center">Google Maps</td>
      <td align="center">Secure Access</td>
      <td align="center">Modern UI</td>
    </tr>
  </table>
</div>

### Implemented Features

- **Internal Building Pathing**
  - Shortest path calculation within MC and DC buildings
  - Support for outdoor, tunnel, bridge, and accessibility options
  - User preference-based routing

- **Scalable Architecture**
  - Modular design for easy building additions
  - Prepared for campus-wide expansion
  - Efficient path-finding algorithms

- **Outdoor Navigation**
  - Google Maps integration for outdoor routes
  - Seamless indoor-outdoor transition support
  - Real-time location updates

- **Interactive Map Interface**
  - Pinch-to-zoom functionality
  - Swipe gestures for navigation
  - Clear visualization of routes and destinations

- **Modern User Experience**
  - Clean, responsive design
  - Consistent color scheme
  - Toast notifications for navigation guidance

- **Security Features**
  - UW email verification
  - Privacy-focused classroom-only search
  - Secure user authentication

### Planned Features

- **Additional Buildings**
  - Expansion to more campus buildings
  - Enhanced building data modeling
  - Comprehensive campus coverage

- **Advanced Navigation**
  - Seamless indoor-outdoor path transitions
  - Elevation and geolocation integration
  - Distance and time metrics for routes

- **Enhanced UI Elements**
  - Recognizable building icons
  - Detailed floor plans
  - Interactive building markers

- **Accessibility Improvements**
  - Wheelchair-accessible route highlighting
  - Elevator and ramp location indicators
  - Voice-guided navigation

- **Social Features**
  - Share routes with classmates
  - Save favorite locations
  - Building-specific announcements

- **Performance Optimizations**
  - Offline map caching
  - Faster path-finding algorithms
  - Reduced battery consumption

- **Data Management**
  - Local storage for frequently used routes
  - Building data updates system
  - User preferences persistence

## 🚀 Getting Started

### Prerequisites

- Android Studio Arctic Fox or newer
- Kotlin 1.8.0 or higher
- Android SDK API 24 or higher
- Gradle 7.5 or higher

### Installation

1. Clone the repository
```bash
git clone https://github.com/yourusername/WatTravl.git
```

2. Open the project in Android Studio
```bash
cd WatTravl
```

3. Sync the project with Gradle files
```bash
./gradlew clean build
```

4. Run the application on your device or emulator

## 🛠️ Built With

<div align="center">
  <table>
    <tr>
      <td align="center">
        <a href="https://kotlinlang.org/">
          <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/kotlin/kotlin-original.svg" alt="Kotlin" width="40" height="40"/>
        </a>
      </td>
      <td align="center">
        <a href="https://developer.android.com">
          <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/android/android-original.svg" alt="Android" width="40" height="40"/>
        </a>
      </td>
      <td align="center">
        <a href="https://developers.google.com/maps">
          <img src="https://www.vectorlogo.zone/logos/google_maps/google_maps-icon.svg" alt="Google Maps" width="40" height="40"/>
        </a>
      </td>
    </tr>
  </table>
</div>

- [Kotlin](https://kotlinlang.org/) - Programming Language
- [Android SDK](https://developer.android.com) - Mobile Platform
- [Google Maps API](https://developers.google.com/maps) - Maps Integration
- [Gradle](https://gradle.org/) - Build System

## 📦 Project Structure

```
WatTravl/
├── src/
│   └── main/
│       └── kotlin/
│           └── com/
│               └── example/
│                   ├── model/      # Data Models and Building Data
│                   │   ├── main.kt # Main Application Logic
│                   │   ├── MC/     # Mathematics & Computing Building Data
│                   │   └── DC/     # Davis Centre Building Data
│                   └── ui/         # User Interface Components
├── res/           # Android Resources
└── build.gradle   # Build Configuration
```

## 🤝 Contributing

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 👥 Team Members

<div align="center">
  <table>
    <tr>
      <td align="center">
        <strong>Omar Elserwi</strong>
      </td>
      <td align="center">
        <strong>Nicholas Lee</strong>
      </td>
      <td align="center">
        <strong>David Kim</strong>
      </td>
    </tr>
    <tr>
      <td align="center">
        <strong>Edmond Zhang</strong>
      </td>
      <td align="center">
        <strong>Roshan Kumar</strong>
      </td>
      <td align="center">
        <strong>Duc Huu Nguyen</strong>
      </td>
    </tr>
  </table>
</div>

## 🙏 Acknowledgments

- Special thanks to our professor and teaching assistants for their guidance
- All contributors who helped shape this project
- The Android and Kotlin communities for their excellent documentation and support

---

<div align="center">
  Made with ❤️ by Team 9
</div>