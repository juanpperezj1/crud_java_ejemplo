package com.javaguides.faqmanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaguides.faqmanagement.dao.FaqDAO;
import com.javaguides.faqmanagement.model.Faq;
@WebServlet("/")
public class FaqServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FaqDAO faqDAO;
	
	public void init() {
		faqDAO = new FaqDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertFaq(request, response);
				break;
			case "/delete":
				deleteFaq(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateFaq(request, response);
				break;
			default:
				listFaq(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	private void listFaq(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Faq> listFaq = faqDAO.selectAllFaqs();
                String usuario = "Usuario editor";
                request.setAttribute("usuario", usuario);
		request.setAttribute("listFaq", listFaq);
		RequestDispatcher dispatcher = request.getRequestDispatcher("faqs-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("faqs-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Faq existingFaq = faqDAO.selectFaq(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("faqs-form.jsp");
		request.setAttribute("faq", existingFaq);
		dispatcher.forward(request, response);

	}

	private void insertFaq(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String pregunta = request.getParameter("pregunta");
		String respuesta = request.getParameter("respuesta");
		Faq newFaq = new Faq(pregunta, respuesta);
		faqDAO.insertFaq(newFaq);
		response.sendRedirect("list");
	}

	private void updateFaq(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String pregunta = request.getParameter("pregunta");
		String respuesta = request.getParameter("respuesta");
		Faq faq = new Faq(id, pregunta, respuesta);
		faqDAO.updateFaq(faq);
		response.sendRedirect("list");
	}

	private void deleteFaq(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		faqDAO.deleteFaq(id);
		response.sendRedirect("list");

	}
}
