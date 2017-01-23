package com.lifemichael.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;	
/**
 * User
 * @author osher keinan <a href="mailto:osherkeinan@gmail.com">osherkeinan@gmail.com</a> and nir bonofiel <a href="mailto:nirbono10@gmail.com">nirbono10@gmail.com</a>
 *
 */
@Entity
@Table(name = "USERS")
public class User
{
	private String username;
	private String password;
/**
 * default constructor	
 */
	public User() {
		super();
	}
	/**
	 * creating user with values
	 * @param username of  user
	 * @param password password of user
	 */
	public User(String username, String password)
	{
		super();
		setUsername(username);
		setPassword(password);
	}
	
	/**
	 * returns user username
	 * @return username
	 */
	@Id
	@Column(name = "username")
	public String getUsername() 
	{
		return username;
	}
	/**
	 * set username
	 * @param username
	 */
	public void setUsername(String username) 
	{
		if(username != null){
		this.username = username;
		}
	}
	
	/**
	 * returns user password
	 * @return password
	 */
	@Column(name = "password")
	public String getPassword() 
	{
		return password;
	}
	/**
	 * set password
	 * @param password
	 */
	public void setPassword(String password) 
	{
		if(password != null){
		this.password = password;
		}
	}
	/**
	 * this method return string with all details about user
	 */
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	
	
}
