Steps to create a new Java project with Maven, from terminal:

1. Use Maven to create the project structure:
   ```
   mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
   ```
2. Use `cd my-app`, then use `tree`from terminal to check the folder tree.

3. Build the project, create and compile the JAR file with:

   ```
   mvn package
   ```

The project is now ready to be executed. 4. Run the JAR with:

```
java -cp target/my-app-1.0-SNAPSHOT.jar com.mycompany.app.App
```

com.mycompany.app.App is the main file that is being run. Modify it to run a different file.

5. Add dependencies to the pom-xml file like so:

   ```
    <dependency>
      <groupId>log4j</groupId>
      <artifactId>log4j</artifactId>
      <version>1.2.12</version>
      <scope>compile</scope>
    </dependency>
   ```

   Then use `mvn compile` for have Maven install them.

6. (Alternatively) install the project using `mvn clean install` and run the project using maven:
   `mvn exec:java -Dexec.mainClass="com.mycompany.app.App"`
