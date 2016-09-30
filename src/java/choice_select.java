

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class choice_select extends HttpServlet
{
 
  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
     try
     { 
       String selection=request.getParameter("choice");
       RequestDispatcher rd;
       if(selection.equals("C"))
       {
          rd=request.getRequestDispatcher("Starting_C");
          rd.forward(request, response);
       }
       else
       {
          rd=request.getRequestDispatcher("Starting_Java");
          rd.forward(request, response);
       } 
     }
     catch(Throwable t)
     {
         PrintWriter out = response.getWriter();
         out.println("Please select one of the option");
     }
  }
 
}
