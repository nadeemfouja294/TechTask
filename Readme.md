# Android Coding Task
The purpose of this project is to showcase proficiency in Android programming by utilizing the latest available components.

## Features
The project has 2 different screens.
- Image List Screen with Username provided by [Pixabay](https://pixabay.com)
- Detail Screen
   - A bigger version of the image.
   - The name of the user.
   - A list of the image’s tags.
   - The number of likes.
   - The number of downloads.
   - The number of comments.

## Architecture
Clean Architecture by [Uncle Bob's](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) as described in my favorite [book](https://www.amazon.com/Clean-Architecture-Android-Expert-led-Maintainable/dp/9355510497)

# Clean Architecture

The core principles of the clean approach can be summarized as followed:

#### 1. The application code is separated into layers.

These layers define the separation of concerns inside the code base.

#### 2. The layers follow a strict dependency rule.

Each layer can only interact with the layers below it.

#### 3. As we move toward the bottom layer — the code becomes generic.

The bottom layers dictate policies and rules, and the upper layers dictate implementation details such as the database, networking manager, and UI.

<p align="center">
<img src="https://github.com/nadeemfouja294/TechTask/blob/master/screenshot/architecture0.png">
</p>

# Architecture Layers

The application consists of three layers:

The domain layer, the data layer, and the app (presentation) layer.

Looking at project’s high-level structure, you’ll see that each layer is represented by a module in the project.

![image](https://github.com/nadeemfouja294/TechTask/blob/master/screenshot/structure.png)

I like it because it helps me avoid accidentals “leaks” between the layers.


# Structure
<p align="center">
<img src="https://github.com/nadeemfouja294/TechTask/blob/master/screenshot/structure0.png">
</p>


## Dependency Injection
Hilt used for DI

## UI 
Built with [Jetpack Compose.](https://developer.android.com/jetpack/compose)

## Language
Written using Kotlin and formatting with [Ktlint](https://ktlint.github.io/)


## Testing
- #### Unit Testing
  - Yes
- #### UI Testing
  - Yes, but not completed would love to write Snapshot testing as well, If i get some more free time in future.

## Important Note
- New Splash Screen 12 API is added 
- Jetpack compose navigation added

## Night Mode Support
- Yes 
 
## Offline Support
- Yes, Refresh interval 24 hours

## For Documentation
- Please see the documentation here [Documentation](https://htmlpreview.github.io/?https://github.com/nadeemfouja294/TechTask/blob/master/docs/dokka/app/index.html)

## Demo
<img align="centre" src="/demo.gif" width="360" height="640"/>