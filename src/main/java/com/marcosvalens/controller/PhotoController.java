package com.marcosvalens.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet(name = "Photos", urlPatterns = ("/gallery"))
public class PhotoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = this.getServletConfig().getServletContext();
        File file = new File("/home/marcos/Proyectos/UpdateFilesMal2/src/main/webapp/uploads");

        System.out.println(servletContext.getAttribute("javax.servlet.context.tempdir"));

        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n");

        for (File thisFile : Objects.requireNonNull(file.listFiles())) {

            writer.println("<img src=\"uploads/" + thisFile.getName() + "\">");
        }

        writer.println("</body>\n" +
                "</html>");
    }
}
