# zba-iot

Android things repository for zba project. Embedded service for raspberryPi, To get data from sensors and control actuators.

## Getting started

### Prerequisites

 - To modify content just use your favorite IDE (nano , vim ...)

 - RaspberryPi with android things installed on it

 - adb bridge to connect to the raspberrPi

### Installation

To install connect your RaspberryPi :

```adb connect <ip-address>```

Open terminal in the project's root folder and execute :

```./gradlew :app:assemble;
   adb install -r -g '/app/build/outputs/apk/debug/app-debug.apk';
   adb shell am start -n com.example.androidthings.testapp/.MainActivity
```

To access logs just connect to adb in your terminal then execute :

```adb logcat ZBA:I *S``` or just ```adb logcat``` for full logs

## Built with

[Android Studio](https://developer.android.com/studio) - Integrated development environment for android
[Android Things](https://developer.android.com/things) - Android operating system for embedded devices
[Restlet API](https://restlet.com/open-source/) - Rest framework to create and use rest API

