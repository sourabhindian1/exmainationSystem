

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Starting_C extends HttpServlet
{
 
    String driver="oracle.jdbc.OracleDriver";
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
       s = c.createStatement();
     }
     catch(ClassNotFoundException | SQLException e){}
 }
 
 @Override
 public void service(HttpServletRequest request, HttpServletResponse response) throws IOException
 {
     PrintWriter out = response.getWriter();
         out.println("Hello");
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
