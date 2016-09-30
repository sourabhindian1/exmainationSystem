

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Result extends HttpServlet
{ 
 
     ArrayList user_answers;
     ArrayList original_answers;
     int i=0, count=0;
    
 @Override
 public void service(HttpServletRequest request, HttpServletResponse response) throws IOException
 {
     try
     {
      HttpSession hs = request.getSession();
      PrintWriter out= response.getWriter();
      out.println("Name: "+(String)hs.getAttribute("loginwala")+"<br>");
      
      user_answers=(ArrayList)hs.getAttribute("userAnswers");
      original_answers=(ArrayList)hs.getAttribute("originalAnswers");
      
      for(i=0;i<original_answers.size();i++)
      {
          if(!(user_answers.get(i+1) == null))
          {
             if(user_answers.get(i+1).equals(original_answers.get(i)))
             count++;
          }
          out.println(user_answers.get(i+1)+"     ");
          out.println(original_answers.get(i));
          out.println("<br>"); 
      }
      out.println("Total Correct answers: "+count);
      hs.invalidate();
     }
     catch(Throwable t)
     {
         PrintWriter out = response.getWriter();
         out.println(t);
     }
 }
}
