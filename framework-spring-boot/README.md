How to initialise a Spring-Boot java project.

Using Maven:

- Make sure you have Apache Maven 3.6.3 or later installed. If not, you can install it using your package manager (e.g., brew install maven on macOS or sudo apt-get install maven on Ubuntu). Verify installation with `mvn --version`.
- Create a new Maven project by running the following command:

  ```
  mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
  ```

  Replace com.mycompany.app with your desired group ID and my-app with your project’s name.

- Verify the Maven installation by running mvn -version.

Start coding inside scr folder. Assuming App.java is inside scr, use the following to compile and run.

```
javac App.java
java App
```
