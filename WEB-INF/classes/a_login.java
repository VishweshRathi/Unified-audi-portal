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


public class a_login extends HttpServlet {
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

   			PreparedStatement ps=con.prepareStatement("select * from admin where user_name=? and password=?");
			ps.setString(1,name);
			ps.setString(2,pass);

			ResultSet rs=ps.executeQuery();
			status=rs.next();
   			if(status==true){
       			PreparedStatement ps1=con.prepareStatement("select name from admin where user_name=?");
				ps1.setString(1,name);
				ResultSet rs1=ps.executeQuery();
				rs1.next();
				HttpSession session=request.getSession();
        		session.setAttribute("name","Admin");
    			out.println("<html>");
				out.println("<body>");

				out.println("<div id=wb_Text1 style=position:absolute;left:304px;top:203px;width:250px;height:19px;z-index:12;text-align:left;><span style=color:#000000;font-family:Arial;font-size:16px;><strong>Logged in as "+rs1.getString(1)+"</strong></span></div>");
				out.println("</body>");
		        out.println("</html>");
		        RequestDispatcher rd=request.getRequestDispatcher("a_home.html");
			    rd.include(request,response);


  			 }
   			 else{
       				out.println("<html>");
		            out.println("<body>");
		            out.println("<div id=wb_Text1 style=position:absolute;left:540px;top:342px;width:347px;height:19px;z-index:19;text-align:left;><span style=color:#800000;font-family:Arial;font-size:16px;><strong>Sorry, Invalid Username or password....</strong></span></div>");
		            out.println("</body>");
		            out.println("</html>");
		            RequestDispatcher rd=request.getRequestDispatcher("Admin_login.html");
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