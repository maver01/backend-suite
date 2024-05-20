import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.net.InetSocketAddress;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.http.HttpServletRequestContext;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class FileServer {

    public static void main(String[] args) throws Exception {
        // Define the port number
        int port = 8000;

        // Create HTTP server instance
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        // Create a context for the backend endpoint
        server.createContext("/files", new MessageHandler());

        // Start the server
        server.start();

        System.out.println("Server started on port " + port);

        // folder path to store the files
        String folderPath = "/home/maver02/Projects/Infrastructure_suite_project/Development/backend-suite/framework-java-SE/project-receive-files-v1/files";
    }

    static class FileHandler implements HttpHandler {

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
                // Check if the request is multipart
                if (ServletFileUpload.isMultipartContent(new HttpServletRequestContext(exchange))) {
                    try {
                        // Set up file upload factory and servlet file upload
                        DiskFileItemFactory factory = new DiskFileItemFactory();
                        ServletFileUpload upload = new ServletFileUpload(factory);
    
                        // Parse the request
                        List<FileItem> items = upload.parseRequest(new HttpServletRequestContext(exchange));
    
                        // Process the uploaded items
                        for (FileItem item : items) {
                            if (!item.isFormField()) {
                                // Process the file item
                                String fileName = item.getName();
                                InputStream fileContent = item.getInputStream();
    
                                // You can save the file here. For now, we'll just read and print its contents
                                BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent));
                                StringBuilder fileData = new StringBuilder();
                                String line;
                                while ((line = reader.readLine()) != null) {
                                    fileData.append(line).append("\n");
                                }
                                reader.close();
                                System.out.println("Received file: " + fileName);
                                System.out.println("File content:\n" + fileData);
                            }
                        }
    
                        // Send a response back to the client
                        String response = "File(s) received successfully.\n";
                        exchange.sendResponseHeaders(200, response.length());
                        OutputStream os = exchange.getResponseBody();
                        os.write(response.getBytes());
                        os.close();
    
                    } catch (Exception e) {
                        e.printStackTrace();
                        String response = "Error processing upload.\n";
                        exchange.sendResponseHeaders(500, response.length());
                        OutputStream os = exchange.getResponseBody();
                        os.write(response.getBytes());
                        os.close();
                    }
                } else {
                    // Send a response indicating the request is not a multipart request
                    String response = "Invalid request format. Please use multipart/form-data.\n";
                    exchange.sendResponseHeaders(400, response.length());
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }
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
