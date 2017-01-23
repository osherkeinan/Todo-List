package com.lifemichael.model;

import java.util.*;

public interface IToDoListDAO
{
	public void addItem(Item item)throws TodoListException;
	public void register(User user)throws TodoListException;
	public List<Item> getItemsList()throws TodoListException;
	public List<Item> getUserItemsList(String username)throws TodoListException;
	public Item findItem(long id)throws TodoListException;
	public User findUser(String username)throws TodoListException;
	public void deleteItem(long id)throws TodoListException;
	public void deleteUser(String username)throws TodoListException;
	public Boolean login(String username,String password)throws TodoListException;
	public void updateItem(Item item)throws TodoListException;
	public void updateUserPassword(String username ,String password)throws TodoListException;
	
}
