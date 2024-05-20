How to initialize a java project.

1. Initialize project using Maven (also [here](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)):

- Make sure you have Apache Maven 3.6.3 or later installed. If not, you can install it using your package manager (e.g., brew install maven on macOS or sudo apt-get install maven on Ubuntu). Verify installation with ```mvn --version```.
- Create a new Maven project by running the following command:

  ```
  mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
  ```

  Replace com.mycompany.app with your desired group ID and my-app with your project’s name.

- Verify the Maven installation by running mvn -version.

2. Add dependencies inside pom.xml file, the following is an example.

```
  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
```

3. Start coding inside scr folder. Assuming App.java is inside scr, use the following to compile and run.

```
javac App.java
java App
```
