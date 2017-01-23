package com.lifemichael.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lifemichael.model.*;

@WebServlet("/controller/*")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HibernateTodoListDAO todoListDao = HibernateTodoListDAO.getInstance();
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();
		RequestDispatcher dispatcher = null;
		String username;
		Cookie[] cookies;
		switch (path) {
		case "/about":
			dispatcher = getServletContext().getRequestDispatcher("/about.jsp");
			dispatcher.forward(request, response);
			break;
		case "/admin":
			dispatcher = getServletContext().getRequestDispatcher("/admin.jsp");
			dispatcher.forward(request, response);
			break;
		case "/register":
			dispatcher = getServletContext().getRequestDispatcher("/register.jsp");	
			dispatcher.forward(request, response);
			break;
			
		case "/login":
			username = null;
			cookies = request.getCookies();
			if(cookies != null)
			{
				for(Cookie cookie: cookies)  // checks if user have cookie.
				{
					if(cookie.getName().equals("username"))
					{
						username = cookie.getValue();
						break;
					}
				}
			}
			if(username != null)
			{
					request.getSession().setAttribute("username", username);
					try {
						request.setAttribute("items", todoListDao.getUserItemsList(username));
					} catch (TodoListException e) {
						e.printStackTrace();
					}
					dispatcher = getServletContext().getRequestDispatcher("/userItems.jsp");	// straight into todo List.
					dispatcher.forward(request, response);
					break;
			}
			else
			{
				dispatcher = getServletContext().getRequestDispatcher("/login.jsp");	
				dispatcher.forward(request, response);
				break;
			}
		case "/logout":
			dispatcher = getServletContext().getRequestDispatcher("/login.jsp");	
			request.getSession().invalidate();
			dispatcher.forward(request, response);
			break;
		case "/addItem":
			username = (String)request.getSession().getAttribute("username");
			try
			{
				Item newItem= (Item) request.getAttribute("newItem");
				newItem.setUsername(username);
				todoListDao.addItem(newItem);
				request.setAttribute("items", todoListDao.getUserItemsList(username));
				dispatcher = request.getRequestDispatcher("/userItems.jsp");	
				dispatcher.forward(request, response);
		} catch (TodoListException e) {
			request.setAttribute("error", e.getMessage());
			dispatcher = getServletContext().getRequestDispatcher("/ShowErrorMsg.jsp");
    		dispatcher.forward(request,response);
		}
			break;
		case "/updateItem":
			try
			{
				List items = todoListDao.getUserItemsList((String)request.getSession().getAttribute("username"));
				Item toUpdateItem= (Item) items.get(Integer.parseInt(request.getParameter("index")));
				if((request.getParameter("description") == "") || (request.getParameter("description") == null))
				{
					throw new TodoListException("Item description cant be empty");
				}
				toUpdateItem.setToDoDescription(request.getParameter("description"));
				toUpdateItem.seteStatus(Item.Status.valueOf(request.getParameter("status")));
				todoListDao.updateItem(toUpdateItem);
				dispatcher = getServletContext().getRequestDispatcher("/userItems.jsp");	
				request.setAttribute("items", todoListDao.getUserItemsList((String)request.getSession().getAttribute("username")));
				dispatcher.forward(request, response);
		} catch (TodoListException e) {
			request.setAttribute("error", e.getMessage());
			dispatcher = getServletContext().getRequestDispatcher("/ShowErrorMsg.jsp");
    		dispatcher.forward(request,response);
		}
			break;
		case "/deleteItemOrToUpdate":
			if(((String)request.getParameter("actionOrindex")).equals("DELETE"))
			{
				try
				{
					username = (String)request.getSession().getAttribute("username");
					List items = todoListDao.getUserItemsList(username);
					for(Item item: (List<Item>)items)
					{
						long id;
						if(request.getParameter(String.valueOf(items.indexOf(item))) != null)
						{
							id= Long.parseLong(request.getParameter(String.valueOf(items.indexOf(item))));
							todoListDao.deleteItem(id);
						}
	
					}
					dispatcher = getServletContext().getRequestDispatcher("/userItems.jsp");	
					request.setAttribute("items", todoListDao.getUserItemsList((String)request.getSession().getAttribute("username")));
					dispatcher.forward(request, response);
					
				} catch (TodoListException e) {
					request.setAttribute("error", e.getMessage());
					dispatcher = getServletContext().getRequestDispatcher("/ShowErrorMsg.jsp");
		    		dispatcher.forward(request,response);
					}
			}else
			{
				try
				{
					Boolean toUPDATE = true;
					request.setAttribute("toUPDATE", toUPDATE);
					request.setAttribute("itemIndex",request.getParameter("actionOrindex"));
					request.setAttribute("items", todoListDao.getUserItemsList((String)request.getSession().getAttribute("username")));
					dispatcher = getServletContext().getRequestDispatcher("/userItems.jsp");
		    		dispatcher.forward(request,response);
				} catch (TodoListException e) {
				request.setAttribute("error", e.getMessage());
				dispatcher = getServletContext().getRequestDispatcher("/ShowErrorMsg.jsp");
	    		dispatcher.forward(request,response);
			}
			}
			break;
		case "/toAdd":
			try
			{
				Boolean toADD = true;
				request.setAttribute("toADD", toADD);
				request.setAttribute("items", todoListDao.getUserItemsList((String)request.getSession().getAttribute("username")));
				dispatcher = getServletContext().getRequestDispatcher("/userItems.jsp");
	    		dispatcher.forward(request,response);
			} catch (TodoListException e) {
			request.setAttribute("error", e.getMessage());
			dispatcher = getServletContext().getRequestDispatcher("/ShowErrorMsg.jsp");
    		dispatcher.forward(request,response);
		}
			break;
		case "/toUpdatePassword":
				dispatcher = getServletContext().getRequestDispatcher("/updatePassword.jsp");
	    		dispatcher.forward(request,response);

			break;
		case "/home":
			username = (String)request.getSession().getAttribute("username");
			if(username == null)
			{
				dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
	    		dispatcher.forward(request,response);
			}
			try {
					request.getSession().setAttribute("username", username);
					request.setAttribute("items", todoListDao.getUserItemsList(username));
					dispatcher = getServletContext().getRequestDispatcher("/userItems.jsp");
		    		dispatcher.forward(request,response);
				
			} catch (TodoListException e) {
				request.setAttribute("error", e.getMessage());
				dispatcher = getServletContext().getRequestDispatcher("/ShowErrorMsg.jsp");
	    		dispatcher.forward(request,response);
			}
			break;
		case "/deleteUser":
			if(request.getSession().getAttribute("username") != null)
			{
					try {
							todoListDao.deleteUser((String)request.getSession().getAttribute("username"));
					} catch (TodoListException e) {
						e.printStackTrace();
					}
			}
			dispatcher = getServletContext().getRequestDispatcher("/login.jsp");	
			request.getSession().invalidate();
			dispatcher.forward(request, response);
			break;
		default:
			username = null;
			cookies = request.getCookies();
			if(cookies != null)
			{
				for(Cookie cookie: cookies)
				{
					if(cookie.getName().equals("username"))
					{
						username = cookie.getValue();
						break;
					}
				}
			}
			if(username != null)
			{
					request.getSession().setAttribute("username", username);
					try {
						request.setAttribute("items", todoListDao.getUserItemsList(username));
					} catch (TodoListException e) {
						e.printStackTrace();
					}
					dispatcher = getServletContext().getRequestDispatcher("/userItems.jsp");	
					dispatcher.forward(request, response);
					break;
			}
			else
			{
				dispatcher = getServletContext().getRequestDispatcher("/login.jsp");	
				dispatcher.forward(request, response);
				break;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();
		RequestDispatcher dispatcher = null;
		String username;
		switch (path) {
			case "/checkLogin":
				username = request.getParameter("username");
				try {
					if(todoListDao.login(username, request.getParameter("password")))
					{
						if(request.getParameter("remember") != null)
						{
							Cookie cookie = new Cookie("username", username);
							cookie.setMaxAge(1000);
							response.addCookie(cookie);
						}
						request.getSession().setAttribute("username", username);
						request.setAttribute("items", todoListDao.getUserItemsList(username));
						dispatcher = getServletContext().getRequestDispatcher("/userItems.jsp");
			    		dispatcher.forward(request,response);
					}
				} catch (TodoListException e) {
					request.setAttribute("error", e.getMessage());
					dispatcher = getServletContext().getRequestDispatcher("/ShowErrorMsg.jsp");
		    		dispatcher.forward(request,response);
				}
				break;
			case "/checkRegister":
				username = request.getParameter("username");
				String password = request.getParameter("password");
				if(username != null && password != null)
				{
				    try
				    {
				    	User user = new User(username, password);
				    	todoListDao.register(user);
				    	request.getSession().setAttribute("username", username);
				    	request.setAttribute("items", todoListDao.getUserItemsList(username));
						dispatcher = getServletContext().getRequestDispatcher("/userItems.jsp");
						dispatcher.forward(request,response);
				    }
				    catch(TodoListException e)
				    {
						request.setAttribute("error", e.getMessage());
						dispatcher = getServletContext().getRequestDispatcher("/ShowErrorMsg.jsp");
			    		dispatcher.forward(request,response);
				    }
				}
				break;
			case "/updatePassword":
				try
				{
					String newPassword= request.getParameter("newPassword");
					todoListDao.updateUserPassword((String)request.getSession().getAttribute("username"),newPassword);
					request.setAttribute("items", todoListDao.getUserItemsList((String)request.getSession().getAttribute("username")));
					dispatcher = getServletContext().getRequestDispatcher("/userItems.jsp");
					dispatcher.forward(request, response);
			} catch (TodoListException e) {
				request.setAttribute("error", e.getMessage());
				dispatcher = getServletContext().getRequestDispatcher("/ShowErrorMsg.jsp");
	    		dispatcher.forward(request,response);
			}
				break;
	}
	}
}



