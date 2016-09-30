

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Start extends HttpServlet
{
 
    String driver="oracle.jdbc.OracleDriver";
    String url="jdbc:oracle:thin:@localhost:1521:xe";
    String username="system";
    String password_oracle="user";
    
     Connection c;
     Statement s;
     ResultSet rs;
     
     int flag=0;
     
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
     String tester=request.getParameter("user");
     String tester_pass=request.getParameter("pass");
     try
     {
        String query="select * from student_record";
        rs=s.executeQuery(query);
        while(rs.next())
        {
          String name=rs.getString(1);
          String pass_record=rs.getString(2);
          if(tester.equals(name) && tester_pass.equals(pass_record))
          {
            flag=1;
            break;
          }
        }
        
        if(flag==0)
        {
          RequestDispatcher rd= request.getRequestDispatcher("/wrong_entry.html");
          rd.forward(request, response); 
        }
        else
        {
         HttpSession hs = request.getSession();
         hs.setAttribute("loginwala",tester);
         PrintWriter out = response.getWriter();
         out.println("<form action=choice_select method=get>"
                 + "<input type=radio name=choice value=C ><b>C</b><br>"
                 + "<input type=radio name=choice value=Java> <b>Java</b> <br>"
                 + "<input type=submit value=Start /><br>"
                 + "</form>");
        }
     }
     catch(Throwable t)
     {
         PrintWriter out = response.getWriter();
         out.println(t);
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
