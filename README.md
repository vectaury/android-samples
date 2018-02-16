<p align="center" >
    <img src=".readme/vectaury_logo.png?raw=true" alt="Vectaury logo" title="Vectaury logo">
</p>

<p align="center">
    <img src="https://img.shields.io/badge/platform-Android-green.svg?style=flat" alt="Android"/>
</p>

# Vectaury Sample Apps

Repository with examples of the Vectaury SDK implementation. This repository contains two applications:

* vectaury app : An Android Java application with a default implementation of the Vectaury SDK
* vectaury_kotlin app : An Android Kotlin application with a default implementation of the Vectaury SDK

## Requirements

* Android SDK API 27
* Android Studio 3

## Use

* Clone or download the Git repository
* Open the project in Android Studio
* Replace the API Key in CustomApplication class with your own

## Enable logs

Logs are enabled automatically when application is build in debug. You can enable them in release with the following command line:

```terminal
adb shell setprop log.tag.Vectaury VERBOSE
```

## Resources

* [Vectaury Documentation](https://cdn.vectaury.io/sdk/doc/android/integration/)
* [vectaury.io](https://www.vectaury.io)
