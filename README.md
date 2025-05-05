# JavaFX Project Template

This is a non-modular, Gradle-based project skeleton for creating JavaFX applications.

It uses `JavaFX 20.0.2`, and includes the [Gradle Wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html) (no need to install Gradle locally).

## Features

- JavaFX-aware coding assistance (code completion, search, navigation in JavaFX-specific source files)
- Logging with [Logback](https://logback.qos.ch/).
- Support for FXML.
- Access to JavaFX's JavaDoc from within your IDE.
- Font icons provided by [Ikonli](https://kordamp.org/ikonli/)

## How Do I Use this Project Template

1. Download this repository as `.zip` file.
2. Unzip the downloaded `javafx-template-main.zip` file.
3. Add the extracted files to your GitHub repo (if you already have one)
4. Open the project with NetBeans
5. Using NetBeans, build the project to download/resolve the dependencies
6. Change the project name in `settings.gradle`
7. Rename the default Java package `edu.vanier.template` to `edu.vanier.projectname` in `MainApp.java` and `MainAppFXMLController.java`.


## How Do I Run Multiple Main Methods?

To execute the main method of a class other than MainApp, you should do so within the `edu.vanier.Launcher` class. For further details, please refer to the examples and comments provided in that class.

**Note:** The `mainClass` property in the `build.gradle` is configured to run the main method of the Launcher class (as shown below):

```gradle
application {
   mainClass = 'edu.vanier.Launcher'   
}
```

## IDEs and JDK

This project template was tested with `Apache NetBeans 2`, IntelliJ Community Edition 2024.2.02, and `JDK 22`.

## How to Use

First create a user account using the sign up button, then log in using the newly created user creditentials.

Once sign in, select a template either (empty, asteroids field or solar system).

Functionality:
- Camera button gives the ability to change the camera speed.
- Setting button gives the ability to exit the scene, save the planets and star and load the previous save.
- The + button at the bottom right allows the user to create new planets and stars
- the pause and unpause button allows the user to pause the animation and start it.
- the planet button allows the user to change the speed of the animation example 1x, 10x, 100x


