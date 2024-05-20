package com.mycompany.app;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig
public class FileUploadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Directory where the files will be saved
        String uploadDirectory = "uploads";
        File uploadDir = new File(uploadDirectory);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        // Process each uploaded file
        for (Part part : request.getParts()) {
            String fileName = getFileName(part);
            File file = new File(uploadDir, fileName);
            Files.copy(part.getInputStream(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        // Send response
        response.getWriter().write("{\"message\": \"Files uploaded successfully\"}");
    }

    // Helper method to extract file name
    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] tokens = contentDisposition.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
