//Student_Signup.java
//For Unified AUDI Portal
//Copy-Right SSBT COET Jalgaon


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class Student_Signup extends HttpServlet{

	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{

		response.setContentType("text/html");
		String prn=request.getParameter("PRN");
		String name=request.getParameter("Name");
		String password=request.getParameter("Password");
		String password2=request.getParameter("Password2");
		String dept_name=request.getParameter("Department");				//getting information from html form
		String date=request.getParameter("Date");
		String month=request.getParameter("Month");
		String year=request.getParameter("Year");
		String mobile=request.getParameter("Mobile_no");
		String email=request.getParameter("Email_id");
		String finaldate =(year+"-"+month+"-"+date);
		String isdiplomate;
		String temp=("a"+request.getParameter("isdiplomate"));

		if(temp.equals("ayes"))
			isdiplomate="yes";
		else
			isdiplomate="no";

		try{


			ServletContext ctx=getServletContext();
			Connection con=(Connection)ctx.getAttribute("mycon");			//getting Connection object from "mycon" attribute of ServletContext

			PreparedStatement ps=con.prepareStatement("insert into student values(?,?,?,?,?,?,?,?)");
			PrintWriter out=response.getWriter();
			ps.setString(1,prn);
			ps.setString(2,name);
			ps.setString(3,password);
			ps.setString(4,dept_name);
			ps.setString(5,finaldate);										//Setting the PreparedStatement parameters
			ps.setString(6,mobile);
			ps.setString(7,email);
			ps.setString(8,isdiplomate);
			int i = ps.executeUpdate();
            if (i > 0)
				response.sendRedirect("reg_success.html"); 				 //Successfull registered and redirect to reg_success.html

			}catch(com.mysql.jdbc.MysqlDataTruncation e){				//Date Format Incorrect Exception.
				//System.out.println(e);
				PrintWriter out=response.getWriter();
				out.println("<html>");
		        out.println("<body>");
		        out.println("<div id=wb_Text1 style=position:absolute;left:600px;top:291px;width:283px;height:19px;z-index:11;text-align:left;><span style=color:#800000;font-family:Arial;font-size:16px;><strong>Date format is incorrect.</strong></span></div>");
			    out.println("</body>");
		        out.println("</html>");

				RequestDispatcher rd=request.getRequestDispatcher("Student_Signup.html");
        		rd.include(request,response);
			}catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e1){
				System.out.println(e1);
				PrintWriter out=response.getWriter();									//user already exits error and returns to signup page.
				out.println("<html>");
		        out.println("<body>");
		        out.println("<div id=wb_Text1 style=position:absolute;left:581px;top:291px;width:283px;height:19px;z-index:11;text-align:left;><span style=color:#800000;font-family:Arial;font-size:16px;><strong>User with this PRN already exists...</strong></span></div>");
			    out.println("</body>");
		        out.println("</html>");

				RequestDispatcher rd=request.getRequestDispatcher("Student_Signup.html");
        		rd.include(request,response);
			}catch(Exception e2){
				System.out.println(e2);												//Other Exception.
				PrintWriter out=response.getWriter();
				out.println("<html>");
				out.println("<body>");
				out.println("<div id=wb_Text1 style=position:absolute;left:600px;top:291px;width:283px;height:19px;z-index:11;text-align:left;><span style=color:#800000;font-family:Arial;font-size:16px;><strong>Bad request.....</strong></span></div>");
				out.println("</body>");
				out.println("</html>");

				RequestDispatcher rd=request.getRequestDispatcher("Student_Signup.html");    //user already exits error and returns to signup page.
			    rd.include(request,response);
			}
	}
}

