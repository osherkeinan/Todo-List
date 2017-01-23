package com.lifemichael.model;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
/**
 * IToDoListDAO
 * @author osher keinan <a href="mailto:osherkeinan@gmail.com">osherkeinan@gmail.com</a> and nir bonofiel <a href="mailto:nirbono10@gmail.com">nirbono10@gmail.com</a>
 *
 */

public class HibernateTodoListDAO implements IToDoListDAO {
	
	private static HibernateTodoListDAO instance = null;
	private static SessionFactory factory = null;
	private Session currentSession;

	private HibernateTodoListDAO(){};
	
	/**
	 * singelton pattern, creat's instance of TodoListDAO
	 * @return instance of TodoListDAO
	 */
	public static HibernateTodoListDAO getInstance() // Singleton pattern
	{
		if(instance == null && factory == null)
		{
			instance = new HibernateTodoListDAO();
			factory = new AnnotationConfiguration().configure().buildSessionFactory();
		}
		
		return instance;
	}
	
	private static Session getSession()throws TodoListException {
			Session session = factory.openSession();
			try
			{
				session.beginTransaction();
			}
			catch(HibernateException e) 
			{
				throw new TodoListException("hibernate error.", e);
			}
		return session;
	}
	
	private static void commitTransaction(Session currentSession) {
		try
		{
			currentSession.getTransaction().commit();
		}
		catch (HibernateException e) 
		{
			if(currentSession.getTransaction() != null)
			{
				currentSession.getTransaction().rollback();
			}
		}
		finally
		{
			currentSession.close();
		}
	}
	
	/**
	 * adds item to the todo list
	 * @param item the item to add
	 * @throws TodoListException if username not exist or item is NULL
	 */
	@Override
	public void addItem(Item item) throws TodoListException {
		if(item == null)
		{
			throw new TodoListException("null item");
		}
		if((item.getToDoDescription() == "") || (item.getToDoDescription() == null))
		{
			throw new TodoListException("Item description cant be empty");
		}
		currentSession = getSession();
		Object user1 = currentSession.get(User.class, item.getUsername());
		if(user1 != null)
		{
			currentSession.save(item);
			commitTransaction(currentSession);
		}
		else
		{
			throw new TodoListException("Username NOT EXIST.");
		}
	}
	/**
	 * returns items list 
	 * @return itemsList 
	 * @throws TodoListException if there was hibernate error
	 * @return returns items list 
	 */
	@Override
	public List<Item> getItemsList() throws TodoListException {
		currentSession = getSession();
		List itemsList = null;
		itemsList = currentSession.createQuery("from Item").list();
		commitTransaction(currentSession);
		return itemsList;
	}
	
	/**
	 * returns users list 
	 * @return usersList 
	 * @throws TodoListException if there was hibernate error
	 * @return returns users list 
	 */
	
	public List<User> getUsersList() throws TodoListException {
		currentSession = getSession();
		List usersList = null;
		usersList = currentSession.createQuery("from User").list();
		commitTransaction(currentSession);
		return usersList;
	}

	/**
	 * finds item by id, returns the item if such, or null if not
	 * @param id the id of item
	 * @throws TodoListException if item not exist
	 * @return returns the item if such, or null if not
	 */
	@Override
	public Item findItem(long id) throws TodoListException {
		currentSession = getSession();
		Item item = null;
		item = (Item)currentSession.get(Item.class, id);
		if(item == null)
		{
			throw new TodoListException("Item NOT EXIST");
		}
		commitTransaction(currentSession);
		return item;
	}
	
	/**
	 * deletes item from the todo list items table
	 * @param id the id of the item to delete
	 * @throws TodoListException if there was hibernate error
	 */
	@Override
	public void deleteItem(long id)throws TodoListException{
		Item item= findItem(id);
		currentSession = getSession();
		currentSession.delete(item);
		commitTransaction(currentSession);
	}
	
	/**
	 * deletes user from the todo list user tables
	 * @param username the username of the user to delete
	 * @throws TodoListException if there was hibernate error or finduser exception
	 */
	@Override
	public void deleteUser(String username)throws TodoListException{
		if(username == null)
		{
			throw new TodoListException("null string");
		}
		User user= findUser(username);
		currentSession = getSession();
		currentSession.delete(user);
		commitTransaction(currentSession);
		List<Item> itemsList = getItemsList();
		for (Item item : itemsList) 
		{
			if(item.getUsername().equals(user.getUsername()))
			{
				   deleteItem(item.getId());
			}
		}
	}

	/**
	 * update the item in the todo list items table
	 * @param item the item to update
	 * @throws TodoListException if there was hibernate error or finduser exception
	 */
	@Override
	public void updateItem(Item item) throws TodoListException {
		if(item == null)
		{
			throw new TodoListException("null item");
		}
		if((item.getToDoDescription() == "") || (item.getToDoDescription() == null))
		{
			throw new TodoListException("Item description cant be empty");
		}
		Item itemInTable = findItem(item.getId());
			if( itemInTable != null)
			{
				if(findUser(item.getUsername()) != null)
				{
					if(item.getUsername().equals(itemInTable.getUsername()))
					{
						currentSession = getSession();
						currentSession.update(item);
						commitTransaction(currentSession);
					}
					else
					{
						throw new TodoListException("Wrong username.");
					}
				}
				else
				{
					throw new TodoListException("item's username NOT EXIST.");
				}
			}
			else
			{
				throw new TodoListException("item NOT EXIST.");
			}
	}

	/**
	 * add new user to todo list users table
	 * @param user the user to add
	 * @throws TodoListException if there was hibernate error or username already exist
	 */
	@Override
	public void register(User user) throws TodoListException {
		Object user1 = null;
		if(user == null)
		{
			throw new TodoListException("null user");
		}
		try
		{
			user1 = findUser(user.getUsername());
		}
		catch(Exception e)
		{;}
		if(user1 == null)
		{
			currentSession = getSession();
			currentSession.save(user);
			commitTransaction(currentSession);
		}
		else
		{
				throw new TodoListException("Username already EXIST.");
		}
	}
/**
 * finds the user in the todo list user table, returns the user if such, null if not
 * @param username the username to search
 * @throws TodoListException if there was hibernate error or wrong/empty username
 * @return returns the user if such, null if not
 */
	@Override
	public User findUser(String username) throws TodoListException {
		if(username == null)
		{
			throw new TodoListException("Enter username and password");
		}
		currentSession = getSession();
		User user = null;
		user = (User)currentSession.get(User.class, username);
		if(user == null)
		{
			throw new TodoListException("Wrong or empty username");
		}
		commitTransaction(currentSession);
		return user;
	}
	
	/**
	 * finds the user in the todo list user table, returns true if such, false if not
	 * @param username the username to search
	 * @param password the password of the user
	 * @throws TodoListException if there was hibernate error or wrong/empty username/password
	 * @return returns true if such user, false if not
	 */
	@Override
	public Boolean login(String username,String password) throws TodoListException {
		if(username == null || password == null)
		{
			throw new TodoListException("Enter username and password");
		}
		User user=findUser(username);
		boolean answer=false;
		if((user.getPassword().equals(password))){
			answer=true;
		} 
		else
		{
			throw new TodoListException("Wrong or empty password.");
		}
		return answer;
	}
	
	/**
	 * updates the user in the todo list user table
	 * @param username the username to update
	 * @throws TodoListException if there was hibernate error or findUser exception
	 */
	@Override
	public void updateUserPassword(String username ,String password) throws TodoListException {
		if(username == null || password == null)
		{
			throw new TodoListException("null string");
		}
		User user=findUser(username);
		if(user != null)
		{
			user.setPassword(password);
			currentSession = getSession();
			currentSession.update(user);
			commitTransaction(currentSession);
		}
		else
		{
			throw new TodoListException("Username not EXIST.");
		}
		
	}
	
	/**
	 * returns list of all the item's of the user by username
	 * @param username the username to search
	 * @throws TodoListException if there was hibernate error
	 * @return List of user items
	 */
	@Override
	public List<Item> getUserItemsList(String username) throws TodoListException {
		List<Item> itemsList = getItemsList(), userItemsList = new LinkedList<Item>();
		for (Item item : itemsList) 
		{
			if(item.getUsername().equals(username))
			{
				userItemsList.add(item);
			}
		}
		return userItemsList;
	}

}
