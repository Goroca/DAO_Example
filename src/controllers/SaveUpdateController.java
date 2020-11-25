package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmploymentDAOImpl;
import models.Employment;
import models.Employment.Gender;
import models.PhoneNumber;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("idEmplUpdate"));

		Employment employer = new EmploymentDAOImpl().seachById(id);
		request.setAttribute("employer", employer);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("newAndUpdate") != null) {
			// TODO Auto-generated method stub
			String name;
			int id;
			int departmentId;
			Gender gender;
			String phoneNumber;
			List<PhoneNumber> phoneNumberList = new ArrayList<PhoneNumber>();

			name = request.getParameter("name");
			departmentId = Integer.parseInt(request.getParameter("departmentId"));
			gender = Gender.valueOf(request.getParameter("gender"));
			phoneNumber = request.getParameter("phoneNumber");
			id = request.getParameter("id") != "" ? Integer.parseInt(request.getParameter("id")) : 0;

			List<String> numberList = Arrays.asList(phoneNumber.split(";"));
			for (String string : numberList) {
				phoneNumberList.add(new PhoneNumber(0, id, string));
			}

			if (id > 0) {
				// Edit Employer
				try {
					Employment editEmployer = new Employment(id, name, departmentId, gender, phoneNumberList);
					new EmploymentDAOImpl().update(editEmployer);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Edit Employer");

			} else {
				// New Employer
				Employment addEmployer = new Employment(id, name, departmentId, gender, phoneNumberList);
				new EmploymentDAOImpl().save(addEmployer);
				System.out.println("New Employer");

			}
			
		} else if (request.getParameter("delete") != null) {
			int id = request.getParameter("id") != "" ? Integer.parseInt(request.getParameter("id")) : 0;

			if (id < 1)
				return;
			
			new EmploymentDAOImpl().delete(id);

		}
		response.sendRedirect("index.jsp");
	}

}
