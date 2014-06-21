package com.learn.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by dmitry on 6/21/14.
 */
public class ShowXlsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType
                ("application/vnd.ms-excel");
        PrintWriter out = resp.getWriter();
        out.println("\tQ1\tQ2\tQ3\tQ4\tTotal");
        out.println
                ("Apples\t78\t87\t92\t29\t=SUM(B2:E2)");
        out.println
                ("Oranges\t77\t86\t93\t30\t=SUM(B3:E3)");
    }
}
