//DAVID WAGNER + ERIC TOSSELL

//CREATED 2/13/2018
//FINISH v.10 2/16/2018

//AGE GROUP SERVLET THAT RETURNS LIST OF AGE GROUPS INFO


package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBHandler;
import model.AgeGroup;

/**
 * Servlet implementation class AgeGroupServlet
 */
@WebServlet("/AgeGroupServlet")
public class AgeGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgeGroupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    //GET
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DBHandler db = new DBHandler();
		Connection conn;
		try {
			conn = db.createConnection();
			List <AgeGroup> ageGroupList = DBHandler.getAgeGroupList(conn);
			for(AgeGroup element : ageGroupList)
			{
				if(element.getCensusYear().equals("1"))
				{
					element.setCensusYear("2016");
				}
				else if (element.getCensusYear().equals("2"))
				{
					element.setCensusYear("2011");
				}
				
				
				
			}
						
			request.setAttribute("ageGroups", ageGroupList);
			

		} catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		RequestDispatcher rd= request.getRequestDispatcher("./ageGroupList.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
