package com.learn.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@WebServlet("/show-image")
public class ShowImageServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException,IOException

    {
        response.setContentType("image/jpeg");
        OutputStream outputStream = response.getOutputStream();



        try {
            ImageIO.write(getImage(), "jpg", outputStream);
        } catch(IOException ioe) {
            System.err.println("Error writing JPEG file: " + ioe);
        }

    }


public BufferedImage getImage(){

    final int WIDTH = 50;
    final  int HEIGHT = 50;
    final  int NUM_ITER = 1500;

    BufferedImage bi;
    bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    Graphics g = bi.getGraphics();
    for (int i = 0; i < NUM_ITER; i++) {
        g.setColor(Color.RED);
        g.drawLine(1, 2, i, i + 1);
    }
    g.dispose();
    return bi;
}
}