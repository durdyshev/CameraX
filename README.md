# CameraX

CameraX is a simple Android app built with **Jetpack Compose**. It lets users create sale items, add a photo with **CameraX**, and save everything locally with **Room**.

## Features

- Add new items with title, description, and price
- Take a photo with the camera
- View saved items in a list
- Store data locally on the device

## Tech Stack

- Kotlin
- Jetpack Compose
- Navigation Compose
- CameraX
- Room
- Hilt
- Coil

## Setup

1. Open the project in Android Studio.
2. Let Gradle sync finish.
3. Run the `app` module on a device or emulator.

## Permissions

The app needs camera permission to capture images.

## Project Structure

```text
CameraX/
├── app/
│   └── src/main/java/com/justme/camerax/
│       ├── MainActivity.kt
│       ├── App.kt
│       ├── navigation/
│       ├── presentation/
│       ├── viewmodel/
│       ├── data/
│       ├── di/
│       └── ui/theme/
├── app/src/main/AndroidManifest.xml
└── build.gradle.kts
```

- `MainActivity.kt` - app entry point
- `navigation/` - screen routing
- `presentation/` - Compose screens
- `viewmodel/` - UI state and logic
- `data/` - Room database, DAO, and repository
- `ui/theme/` - Compose theme files

## Notes

- The app uses MVVM architecture.
- Images are saved as `ByteArray` in the local database.
- First launch shows a startup screen, then opens the item list.

## License

MIT


