import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.net.InetSocketAddress;

public class FileServer {

    public static void main(String[] args) throws Exception {
        // Define the port number
        int port = 8000;

        // Create HTTP server instance
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        // Create a context for the message endpoint
        server.createContext("/message", new MessageHandler());

        // Start the server
        server.start();

        System.out.println("Server started on port " + port);
    }

    static class MessageHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*"); // Allow all origins for simplicity
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST");

            // Handle preflight requests
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1); // No Content
                return;
            }

            // Get the request method
            String requestMethod = exchange.getRequestMethod();

            if (requestMethod.equalsIgnoreCase("POST")) {
                InputStream inputStream = exchange.getRequestBody();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder requestBody = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) { // while there is data in the request body
                    requestBody.append(line);
                }
                reader.close();

                String message = requestBody.toString();
                System.out.println("Received message: " + message);

                // Send a response back to the client, react
                String response = "Message received: " + message + "\n";
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();

            } else {
                // If the request method is not POST, send an error response
                String response = "Method not allowed\n";
                exchange.sendResponseHeaders(405, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }
}
