package com.learn.controller.regularservlets.servletsjsp.cookies;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/** Servlet that displays an HTML form to collect user's
 *  first name, last name, and email address. Uses cookies
 *  to determine the initial values of each of those
 *  form fields.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages 2nd Edition
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2003 Marty Hall; may be freely used or adapted.
 */

public class RegistrationForm extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String actionURL =
      "/servlet/servletsandjsp/cookies.RegistrationServlet";
    String firstName =
      CookieUtilities.getCookieValue(request, "firstName", "");
    String lastName =
      CookieUtilities.getCookieValue(request, "lastName", "");
    String emailAddress =
      CookieUtilities.getCookieValue(request, "emailAddress",
                                     "");
    String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
    String title = "Please Register";
    out.println
      (docType +
       "<HTML>\n" +
       "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
       "<BODY BGCOLOR=\"#FDF5E6\">\n" +
       "<CENTER>\n" +
       "<H1>" + title + "</H1>\n" +
       "<FORM ACTION=\"" + actionURL + "\">\n" +
       "First Name:\n" +
       "  <INPUT TYPE=\"TEXT\" NAME=\"firstName\" " +
                 "VALUE=\"" + firstName + "\"><BR>\n" +
       "Last Name:\n" +
       "  <INPUT TYPE=\"TEXT\" NAME=\"lastName\" " +
                        "VALUE=\"" + lastName + "\"><BR>\n" +
       "Email Address: \n" +
       "  <INPUT TYPE=\"TEXT\" NAME=\"emailAddress\" " +
                 "VALUE=\"" + emailAddress + "\"><P>\n" +
       "<INPUT TYPE=\"SUBMIT\" VALUE=\"Register\">\n" +
       "</FORM></CENTER></BODY></HTML>");
  }
}
