package com.marcosvalens.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "Photos", urlPatterns = ("/gallery"))
public class PhotoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        File file = new File("/home/marcos/DWES_2020/Ejemplos_Clase/Tema2/serverlogingallery2/src/main/webapp/uploads");

        req.setAttribute("files",file.listFiles());
        req.setAttribute("hola","hola que tal");
        req.getRequestDispatcher("fotos.jsp").forward(req,resp);
    }
}
