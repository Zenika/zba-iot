./gradlew :app:assemble;
adb install -r -g '/home/henri/AndroidStudioProjects/zba-iot/app/build/outputs/apk/debug/app-debug.apk';
adb shell am start -n com.example.androidthings.testapp/.MainActivity
