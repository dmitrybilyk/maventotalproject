package com.learn.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dmitry on 6/21/14.
 */
public class GoToAnotherPageServlet extends HttpServlet {

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
