package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojo.Furniture;
import com.revature.service.FurnitureService;
import com.revature.service.UserServiceImpl;

/**
 * Servlet implementation class FurnitureServlet
 */
public class FurnitureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static FurnitureService furnitureService = new FurnitureService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FurnitureServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String body = request.getReader().readLine();
		System.out.println(body);
		ObjectMapper om = new ObjectMapper();
		Furniture furniture = om.readValue(body, Furniture.class);
		System.out.println(furniture);
		furnitureService.makeFurniture(furniture);
		System.out.println(furnitureService.findAllFurnitureForUser(new UserServiceImpl().loginUser("user", "pass")));
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
