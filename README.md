# DAVEBOOSTZZ

DAVEBOOSTZZ is an Android Kotlin/Jetpack Compose starter for a game-utility dashboard.

## Build
GitHub Actions uses JDK 17, Android SDK 35 and Gradle 8.11.1 to build `assembleDebug`.
Open the **Actions** tab after pushing to `main` and download the `DAVEBOOSTZZ-debug-apk` artifact from a successful run.

## Included
- Dark black/red Compose interface and navigation
- Device CPU/RAM/battery snapshot repository
- User-started floating overlay service (currently displays `FPS: N/A`)
- Shizuku binder/permission status display
- Game launcher by package name
- Battery, network, background-app, notification, statistics and settings screens
- GitHub Actions build workflow

## Not yet implemented
- Actual game-render FPS measurement
- Privileged Shizuku shell operations
- Automatic game detection / full installed-game library
- Battery saver or performance tuning controls
- Historical statistics and notification-listener functionality

Android limits what ordinary apps can control. This app does not bypass game anti-cheat, change game files, or promise higher FPS.
