package com.learn.servlet;

//import com.edw.bean.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.learn.model.User;
import org.apache.log4j.Logger;
 
public class VelocityServlet extends HttpServlet {
 
    private Logger logger = Logger.getLogger(getClass());
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {       
        try {
            // simulate a database query
            List<User> users = new ArrayList<User>();
            for (int i = 0; i < 5; i++) {
                User user = new User("name "+i, "Address "+i);
                users.add(user);
            }
            // set values
            request.setAttribute("users", users);
 
            // get UI
            RequestDispatcher requestDispatcher =  request.getRequestDispatcher("index.vm");
            requestDispatcher.forward(request, response);
        } catch(Exception ex){
            logger.error(ex);
        }
    }
}