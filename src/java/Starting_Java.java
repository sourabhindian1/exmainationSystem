

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Starting_Java extends HttpServlet
{
 
    String driver="oracle.jdbc.OracleDriver";
    String url="jdbc:oracle:thin:@localhost:1521:xe";
    String username="system";
    String password_oracle="user";
    
     Connection c;
     Statement s;
     ResultSet rs;
     
     ArrayList question_id=new ArrayList();
     int i=0, count=0;
     ArrayList user_answers=new ArrayList();
     ArrayList original_answers=new ArrayList();
     
    @Override
 public void init()
 {
     try
     {
       Class.forName(driver);
       c = DriverManager.getConnection(url,username,password_oracle);
       s = c.createStatement();
       String query1="select * from j_question_bank";
       rs=s.executeQuery(query1);
       while(rs.next())
       {
        question_id.add(rs.getInt(7));
       }
       Collections.shuffle(question_id);
     }
     catch(ClassNotFoundException | SQLException e){}
 }
 
 @Override
 public void service(HttpServletRequest request, HttpServletResponse response) throws IOException
 {
     try
     {
         
         HttpSession hs=request.getSession();
         PrintWriter out = response.getWriter();
         
         if(!hs.isNew())
         {
           //String ans = request.getParameter("fill");
           //if(ans.equals(null))
          // ans="blank";
          user_answers.add(request.getParameter("fill"));
             //  user_answers.add(ans);
         }
         
          if(i==question_id.size())
          {
           hs.setAttribute("userAnswers",user_answers);
           hs.setAttribute("originalAnswers",original_answers);
           
           RequestDispatcher rd= request.getRequestDispatcher("Result");
           rd.forward(request, response); 
          }
          
         String query2="select * from j_question_bank where q_id='"+question_id.get(i)+"'";
         i++;
         count++;
         rs=s.executeQuery(query2);
         out.println(i +".<br>");
         while(rs.next())
         {
           original_answers.add(rs.getString(6));
           out.println(rs.getString(1)+"<br><br>");
           out.println("<form action=Starting_Java method=get>"
                   + "<input type='radio' name='fill' value='a'>" +rs.getString(2)
                   + "<br><input type='radio' name='fill' value='b'>" +rs.getString(3)
                   + "<br><input type='radio' name='fill' value='c'>" +rs.getString(4)
                   + "<br><input type='radio' name='fill' value='d'>" +rs.getString(5));
         }
         
         if(count!=question_id.size())
         {
             out.println("<br><input type='submit' value='Next'>"
               + "</form>"); 
         }   
         else
         {
             out.println("<br><input type='submit' value='Submit'>"
               + "</form>");
         }
         /*
         else
         {
           hs.setAttribute("userAnswers",user_answers);
           hs.setAttribute("originalAnswers",original_answers);
           out.println("<form action=Result method=get>"
                   + "<br><input type='submit' value='Submit'>"
                   + "</form>");
         }
         */
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
