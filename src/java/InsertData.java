

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class InsertData extends HttpServlet
{
 
    String driver="oracle.jdbc.driver.OracleDriver";
    String url="jdbc:oracle:thin:@localhost:1521:xe";
    String username="system";
    String password_oracle="user";
    
     Connection c;
     Statement s;
     
    @Override
 public void init()
 {
     try
     {
       Class.forName(driver);
       c = DriverManager.getConnection(url,username,password_oracle);
       s=c.createStatement();
     }
     catch(ClassNotFoundException | SQLException e){}
 }
 
 @Override
 public void service(HttpServletRequest request, HttpServletResponse response) throws IOException
 {
   String name=request.getParameter("name");
   String pass=request.getParameter("password");
   String mobile=request.getParameter("mobile");
   String email=request.getParameter("email");
   String city=request.getParameter("city");
   
   RequestDispatcher rd;
   
  if(name.isEmpty() || pass.isEmpty() || mobile.isEmpty() || email.isEmpty() || city.isEmpty())
   {
       try
       {
        rd=request.getRequestDispatcher("/unsuccessful.html");
        rd.forward(request, response);    
       }
       catch(Throwable t)
       {
         PrintWriter out = response.getWriter();
         out.println(t);
       }
   }
   else
   { 
      String query="insert into student_record values('"+name+"','"+pass+"','"+mobile+"','"+email+"','"+city+"')";
       try
       {
           s.executeUpdate(query);
           rd=request.getRequestDispatcher("/success_register.html");
           rd.forward(request, response);    
       }
       catch(Throwable t)
       {
         PrintWriter out = response.getWriter();
         out.println(t);
       }
   }
}
 
 @Override
 public void destroy()
 {
     try
     {
        c.close();
     }
     catch(SQLException e){}
 }
 
}
