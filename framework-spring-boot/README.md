Steps to create a new spring-boot project:

1. Use Maven to create the project structure:
   ```
   mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
   ```
   
2. Use ```cd my-app```, then use ```tree```from terminal to check the folder tree.

3. Use ```mvn package``` to build the project, create and compile the JAR file.
  
5. Run the JAR with:
   ```
java -cp target/my-app-1.0-SNAPSHOT.jar com.mycompany.app.App
   ```
