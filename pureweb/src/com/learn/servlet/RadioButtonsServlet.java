package com.learn.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dmitry on 6/21/14.
 */

@WebServlet("/radioButtons")
public class RadioButtonsServlet extends HttpServlet {

    private int someSharedIntValue;

    public int getSomeSharedIntValue() {
        return someSharedIntValue;
    }

    public void setSomeSharedIntValue(int someSharedIntValue) {
        this.someSharedIntValue = someSharedIntValue;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setIntHeader("MyTestInt", 20);

//        response.setIntHeader("Refresh", 10);

        String address = "userdetails.jsp";
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
