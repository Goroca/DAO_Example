package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmploymentDAOImpl;
import models.Employment;
import models.Employment.Gender;

/**
 * Servlet implementation class SaveUpdateController
 */
@WebServlet("/SaveUpdateController")
public class SaveUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name ;
		int id;
		int departmentId;
		Gender gender;
		
		name =	request.getParameter("name");
		departmentId =	Integer.parseInt(request.getParameter("departmentId"));
		gender  =	Gender.valueOf(request.getParameter("gender"));

		
		id = request.getParameter("id") != "" ?
				Integer.parseInt(request.getParameter("id")) : 0;
		
		
		if (id>0){
			//Edit Employer
			List<Employment> employments;
			try {
				employments = new EmploymentDAOImpl().getEmployments();
				Employment editEmployer = employments.get(id);
				editEmployer.setName(name);
				editEmployer.setGender(gender);
				editEmployer.setDepartmentId(departmentId);
				new EmploymentDAOImpl().update(editEmployer);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Edit Employer");
			
			
		}else{
			//New Employer
			Employment addEmployer = new Employment(id,name,departmentId,gender);		
			new EmploymentDAOImpl().save(addEmployer);
			System.out.println("New Employer");

		}
		
	}

}
