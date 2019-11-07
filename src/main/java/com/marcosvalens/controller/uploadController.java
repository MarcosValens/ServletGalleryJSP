package com.marcosvalens.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

@WebServlet(name = "upload", urlPatterns = "/upload")
@MultipartConfig(location = "/tmp")
        /*fileSizeThreshold = 1048576, // 1mb
        maxFileSize = 1048576, // 1mb
        maxRequestSize = 5242880) // 5mb*/

public class uploadController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("upload.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Part file = req.getPart("file");
        String filename = file.getSubmittedFileName();

        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Configure a repository (to ensure a secure temp location is used)
        //ServletContext servletContext = this.getServletConfig().getServletContext(); //Preguntar cuando se instancia esta clase!!
        File repository = new File("/home/marcos/Proyectos/UpdateFilesMal2/src/main/webapp/uploads");

        // Create a new file upload handler
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Parse the request
        try {
            List<FileItem> items = upload.parseRequest(req);

            File thisFile = new File(repository, filename);

            try (InputStream input = file.getInputStream()) {
                Files.copy(input, thisFile.toPath());
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("http://localhost:8080/UpdateFilesMal2_war_exploded/gallery");
    }
}
