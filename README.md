<p align="center" >
    <img src=".readme/vectaury_logo.png?raw=true" alt="Vectaury logo" title="Vectaury logo">
</p>

<p align="center">
    <img src="https://img.shields.io/badge/platform-Android-green.svg?style=flat" alt="Android"/>
</p>

# Vectaury Sample Apps

Repository with examples of the Vectaury Location SDK implementation and Vectaury CMP implementation. This repository contains one application for now:

* vectaury app : An Android Kotlin application with a default implementation of the Vectaury Location SDK and the Vectaury CMP SDK

## Requirements

* Android SDK API 28
* Android Studio 3

## Use

* Clone or download the Git repository
* Open the project in Android Studio
* Replace the API Key in CustomApplication class with your own

## Enable logs

Logs are enabled automatically when application is build in debug. You can enable them in release.

For the Vectaury Location SDK:

```terminal
adb shell setprop log.tag.Vectaury VERBOSE
```

For the Vectaury CMP:

```terminal
adb shell setprop log.tag.VectauryCmp VERBOSE
```

## Resources

* [Vectaury Documentation](https://cdn.vectaury.io/sdk/doc/android/integration/)
* [vectaury.io](https://www.vectaury.io)

## License

    Copyright 2019 Vectaury.
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
    http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

