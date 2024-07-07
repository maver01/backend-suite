# Create simple REST API

Run as project-spring-boot-hello-world.

Once the server is running, use curl to test the POST endpoint:

`curl -X POST http://localhost:8080/data -H "Content-Type: application/json" -d '{"name": "John Doe", "age": 30}'`

It should return: `{"name":"John Doe","age":30}`
