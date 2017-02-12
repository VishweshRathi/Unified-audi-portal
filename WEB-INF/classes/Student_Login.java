//Student Login

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;


public class Student_Login extends HttpServlet {
public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
	boolean status=false;
	try{

			ServletContext ctx=getServletContext();
			Connection con=(Connection)ctx.getAttribute("mycon");

    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();

    		String name=request.getParameter("Username");
    		String pass=request.getParameter("Password");

   			PreparedStatement ps=con.prepareStatement("select * from student where prn=? and password=?");
			ps.setString(1,name);
			ps.setString(2,pass);

			ResultSet rs=ps.executeQuery();
			status=rs.next();
   			if(status==true){
       			PreparedStatement ps1=con.prepareStatement("select name from student where prn=?");
				ps1.setString(1,name);
				ResultSet rs1=ps.executeQuery();
				rs1.next();
				HttpSession session=request.getSession();
        		session.setAttribute("name",name);
    			out.println("<html>");
				out.println("<body>");
				out.println("<div id=wb_Text18 style=position:absolute;left:300px;top:200px;width:700px;height:19px;z-index:44;text-align:left;><span style=color:#000000;font-family:Arial;font-size:16px;><strong>Welcome "+(rs1.getString(2))+" ( PRN no : "+ name +")"+" </strong></span></div>");



				out.println("</body>");
		        out.println("</html>");
		        RequestDispatcher rd=request.getRequestDispatcher("Student_certi_info.html");
			    rd.include(request,response);


  			 }
   			 else{
       				out.println("<html>");
		            out.println("<body>");
		            out.println("<div id=wb_Text1 style=position:absolute;left:570px;top:344px;width:299px;height:19px;z-index:20;text-align:left;><span style=color:#800000;font-family:Arial;font-size:16px;><strong>Sorry, Invalid PRN or password....</strong></span></div>");
		            out.println("</body>");
		            out.println("</html>");
		            RequestDispatcher rd=request.getRequestDispatcher("Student_Login.html");
       				rd.include(request,response);


    		}
			out.close();
		}catch (Exception e){e.printStackTrace();}
    }
}



/*PreparedStatement ps1=con.prepareStatement("select name from student where prn=?");
										ps1.setString(1,name);
										ResultSet rs1=ps.executeQuery();
								rs1.next();*/
