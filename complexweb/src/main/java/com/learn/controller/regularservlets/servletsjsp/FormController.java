package com.learn.controller.regularservlets.servletsjsp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 01.07.13
 * Time: 21:25
 * To change this template use File | Settings | File Templates.
 */
public class FormController extends HttpServlet {
    private int i;

    @Override
    public void init(ServletConfig config) throws ServletException {
        config.getInitParameter("testInitParam");
        i++;
    }

    public FormController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("/ObjectForm.jsp");
        view.forward(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("/ObjectForm.jsp");
        view.forward(request, response);
    }
}
