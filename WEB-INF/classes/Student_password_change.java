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


public class Student_password_change extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		try{

				ServletContext ctx=getServletContext();
				Connection con=(Connection)ctx.getAttribute("mycon");

		    	response.setContentType("text/html");
    			PrintWriter out = response.getWriter();

    			String cpassword = request.getParameter("cpassword");
    			String npassword1 = request.getParameter("npassword1");
    			String npassword2 = request.getParameter("npassword2");

    			HttpSession session=request.getSession(false);
				if(session!=null){
					String name=(String)session.getAttribute("name");
				    PreparedStatement ps1=con.prepareStatement("select password from student where prn=?");
				    ps1.setString(1,name);
				    ResultSet rs1=ps1.executeQuery();
					rs1.next();
					String userpassword=rs1.getString(1);
					if(userpassword.equals(cpassword)){
						PreparedStatement ps=con.prepareStatement("UPDATE student SET password=? where prn=?");
						ps.setString(1,npassword2);
						ps.setString(2,name);

						int i = ps.executeUpdate();
						if (i > 0){
							out.println("<div id=wb_Text style=position:absolute;left:586px;top:350px;width:316px;height:19px;z-index:22;text-align:left;><span style=color:#000000;font-family:Arial;font-size:16px;><strong>Password changed successfully....</strong></span></div>");
							RequestDispatcher rd=request.getRequestDispatcher("Student_password_change.html");
							rd.include(request,response);
						}
					}

					else{
						out.println("<div id=wb_Text style=position:absolute;left:586px;top:350px;width:316px;height:19px;z-index:22;text-align:left;><span style=color:#800000;font-family:Arial;font-size:16px;><strong>Current password is incorrect.</strong></span></div>");
						RequestDispatcher rd1=request.getRequestDispatcher("Student_password_change.html");
						rd1.include(request,response);
					}

				}
				else{
				    out.print("<div id=wb_Text1 style=position:absolute;left:560px;top:344px;width:299px;height:19px;z-index:20;text-align:center;><span style=color:#800000;font-family:Arial;font-size:16px;><strong>Please Login First...</strong></span></div>");
				    request.getRequestDispatcher("Student_Login.html").include(request, response);

				}

       			out.close();
			 }catch(Exception e){e.printStackTrace();}
	 }
 }


