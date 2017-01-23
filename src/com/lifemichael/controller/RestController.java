package com.lifemichael.controller;


import java.util.Iterator;
import java.util.List;
import javax.ws.rs.*;
import org.json.simple.*;
import com.lifemichael.model.HibernateTodoListDAO;
import com.lifemichael.model.TodoListException;
import com.lifemichael.model.User;

@Path("/User")
public class RestController {
	@Path("/{username}")
    @GET
    @Produces("text/html")
    public String getUser(@PathParam("username") String username) {
    	HibernateTodoListDAO todoListDao = HibernateTodoListDAO.getInstance();
    	User user = null;
		try {
			user = todoListDao.findUser(username);
		} catch (TodoListException e) {
			e.printStackTrace();
		}
    	return "details about user " + user.toString();
    }
   
    	@Path("/UsersList")
        @GET
        @Produces("text/html")
        public String getUsersList() {
        	HibernateTodoListDAO todoListDao = HibernateTodoListDAO.getInstance();
        	String result = "Users List [";
        	List<User> usersList = null;
    		try {
	    			usersList = todoListDao.getUsersList();
	    			Iterator<User> iterator = usersList.iterator();
	    			while(iterator.hasNext())
	    			{
	    				result += "USERNAME: " + iterator.next().getUsername();
	    				if(iterator.hasNext())
	    				{
	    					result += " --> ";
	    				}
	    				else
	    				{
	    					result += "]";
	    				}
	    			}
    			}
    			catch (TodoListException e)
    			{
    				e.printStackTrace();
    			}

        	 return result.toString();
        }
}
