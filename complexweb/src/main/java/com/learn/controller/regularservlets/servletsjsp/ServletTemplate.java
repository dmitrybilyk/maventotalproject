package com.learn.controller.regularservlets.servletsjsp;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/** Servlet template.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages 2nd Edition
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2003 Marty Hall; may be freely used or adapted.
 */

public class ServletTemplate extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
      
    // Use "request" to read incoming HTTP headers
    // (e.g., cookies) and query data from HTML forms.
    
    // Use "response" to specify the HTTP response status
    // code and headers (e.g., the content type, cookies).
    
    PrintWriter out = response.getWriter();
    // Use "out" to send content to browser
  }
}
