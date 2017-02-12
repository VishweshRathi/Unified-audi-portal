import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

public class Student_Profile extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {

   		try{
				ServletContext ctx=getServletContext();
				Connection con=(Connection)ctx.getAttribute("mycon");

   	    	response.setContentType("text/html");
       		PrintWriter out = response.getWriter();

       		HttpSession session=request.getSession(false);
			if(session!=null){
				String name=(String)session.getAttribute("name");

				PreparedStatement ps1=con.prepareStatement("select * from student where prn=?");
				ps1.setString(1,name);
				ResultSet rs1=ps1.executeQuery();
				rs1.next();

				RequestDispatcher rd=request.getRequestDispatcher("Student_Profile.html");
							rd.include(request,response);
				out.println("<div id=wb_Text2 style=position:absolute;left:682px;top:349px;width:297px;height:29px;z-index:15;text-align:left;><span style=color:#000000;font-family:Arial;font-size:24px;><strong>"+name+"</strong></span></div>");
				out.println("<div id=wb_Text4 style=position:absolute;left:682px;top:400px;width:424px;height:29px;z-index:17;text-align:left;><span style=color:#000000;font-family:Arial;font-size:24px;><strong>"+rs1.getString(2)+"</strong></span></div>");
				out.println("<div id=wb_Text6 style=position:absolute;left:682px;top:455px;width:250px;height:29px;z-index:19;text-align:left;><span style=color:#000000;font-family:Arial;font-size:24px;><strong>"+rs1.getString(4)+"</strong></span></div>");
				out.println("<div id=wb_Text8 style=position:absolute;left:682px;top:507px;width:250px;height:29px;z-index:21;text-align:left;><span style=color:#000000;font-family:Arial;font-size:24px;><strong>"+rs1.getString(5)+"</strong></span></div>");
				out.println("<div id=wb_Text10 style=position:absolute;left:682px;top:559px;width:250px;height:29px;z-index:23;text-align:left;><span style=color:#000000;font-family:Arial;font-size:24px;><strong>"+rs1.getString(6)+"</strong></span></div>");
				out.println("<div id=wb_Text16 style=position:absolute;left:682px;top:613px;width:424px;height:29px;z-index:0;text-align:left;><span style=color:#000000;font-family:Arial;font-size:24px;><strong>"+rs1.getString(7)+"</strong></span></div>");


			}
			else{System.out.println("Invalid session");}

		}catch(Exception e){e.printStackTrace();}
	}
}


