# Prephouse Android (ph-android)

## Setup Instructions

1. Download and install [Android Studio 2020.3](https://developer.android.com/studio) or later
2. Follow the prompts from Android Studio
3. Download and install a virtual Android device in the AVD manager of Android Studio, or connect
your physical Android device to Android Studio via USB or, with Android 11+, via Wi-Fi
4. In the _Build Variants_ tab, select _debug_ as the active build variant for the _app_ module

## Startup Instructions 

1. Click on the _Run 'app'_ button in the top right menu bar of Android Studio to run the application

## Project Modules

This project contains the following modules

| Module               |  Description                                  |
|----------------------|-----------------------------------------------|
| annotationProcessor  |  Custom annotations and their processors      |   
| app                  |  Android application                          |   
| buildSrc             |  Gradle plugins, classpaths and dependencies  |

## Jetpack Compose

The app is **fully** written in [Jetpack Compose](https://developer.android.com/jetpack/compose)
with [Material3 components](https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary).

This app includes some libraries that wrap well-known views as composables, such as
[Landscapist](https://github.com/skydoves/Landscapist) for a Glide composable and
[Accompanist](https://google.github.io/accompanist/) for, among other things, a Permission composable.
If Compose is not supported by a third-party library, you can continue to use a XML layout
via an `AndroidView` composable.

You may want to annotate your composable with [`@ReadOnlyComposable`](https://developer.android.com/reference/kotlin/androidx/compose/runtime/ReadOnlyComposable)
when it is safe to do so in order to generate a slightly more efficient composable.

## Compatibility

The app is compatible with Android 6 Marshmallow (SDK 23) and later.

## Architecture

The app runs under a single-activity architecture where
[`MainActivity`](app/src/main/java/com/prephouse/prephouse/MainActivity.kt) serves as the root
activity.

Further, the app follows the Model-View-View Model (MVVM) design pattern through the use of Jetpack
Architecture Components. The design pattern is summarized in _Figure 1_ except that Kotlin Flow
replaces LiveData in the view model.

<figure>
    <img src="https://developer.android.com/topic/libraries/architecture/images/final-architecture.png" alt="mvvm skeleton diagram"> <br>
    <figcaption>
        Figure 1: MVVM design pattern <br>
        https://developer.android.com/topic/libraries/architecture/images/final-architecture.png
    </figcaption>
</figure>


## Code Style

We are following the official [Kotlin style guide](https://kotlinlang.org/docs/coding-conventions.html).
You can configure the Kotlin code style on Android Studio by following the instructions
[here](https://kotlinlang.org/docs/coding-conventions.html).

We use [ktlint](https://github.com/pinterest/ktlint) to enforce the code style. You can run
`./gradlew ktlintFormat` on your CLI to get ktlint to detect, and where possible automatically fix,
code violations. A pre-commit hook has also been set up for you.

## Libraries

This section explains some of the libraries that we use for our Android app. Although the documentation
for the respective libraries are generally sufficient, our codebase requires that we conform to certain
rules when using these libraries to ensure that Gradle can build the project and that the code is consistent
throughout the app.

<details>
<summary>Hilt</summary>
Performs dependency injection through Dagger2

- Specify Hilt modules (that is, objects and classes annotated with `@Module`) in the
  [com.prephouse.prephouse.modules](app/src/main/java/com/prephouse/prephouse/modules) package
- Specify any qualifiers (that is, annotation classes annotated with `@Qualifier`) in the
  [com.prephouse.prephouse.modules.qualifiers](app/src/main/java/com/prephouse/prephouse/modules/qualifiers)
  package
</details>

<details>
<summary>Kotlin Serialization</summary>
Serializes and deserializes classes and their fields

- Used by Retrofit to convert between a HTTP JSON body and a Java/Kotlin class, particularly a Kotlin
  data class
- Requires a `@SerialName` annotation for any field whose name is not the same as the serial name,
  especially since JSON object key names use snake case whereas Java/Kotlin field names use camel case
- Implement any custom serializer (that is, any class that implements `KSerializer`) in the
  [com.prephouse.prephouse.models.serializers](app/src/main/java/com/prephouse/prephouse/models/serializers)
- Requires, for any enum class `E` that extends the [`NumberedEnum`](app/src/main/java/com/prephouse/prephouse/utils/NumberedEnum.kt)
  interface, a companion object of `E` that extends the [`NumberedEnumSerializer<E>`](app/src/main/java/com/prephouse/prephouse/models/serializers/NumberedEnumSerializer.kt)
  class and that specifies the serializer for `E`; see [`FeedbackCategory`](app/src/main/java/com/prephouse/prephouse/models/models/feedback/FeedbackCategory.kt)
  as an example
</details>

<details>
<summary>Retrofit</summary>
Converts our APIs into clean and simple Java/Kotlin interfaces

- Relies on the OkHttp library as the HTTP client
- Retrofit and its API interfaces are provided by Hilt via [`ApiModule`](app/src/main/java/com/prephouse/prephouse/modules/ApiModule.kt);
  likewise, when you create a new API interface, please include it in `ApiModule`
</details>

<details>
<summary>Room</summary>
Stores persistent data on device for when we want to store user or app data that would be too
complicated to do so with Database, or when we want to cache certain API responses

- Examine the table schemas for Room DBs in the [app/schemas](app/schemas) directory
- Utilize Room auto migration where possible
- Annotate enums that implement the [`NumberedEnum`](app/src/main/java/com/prephouse/prephouse/utils/NumberedEnum.kt) interface with the `@Convertible` annotation
- Implement any Room type converters where necessary in the
  [com.prephouse.prephouse.models.converters](app/src/main/java/com/prephouse/prephouse/models/converters) package

**Note**: Room currently does not support Kotlin inline classes as column types
</details>

<details>
<summary>DataStore Preferences</summary>
Stores persistent key-value pairs as a replacement for shared preferences
</details>
