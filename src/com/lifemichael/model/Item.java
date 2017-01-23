package com.lifemichael.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;	
/**
 * Item
 * @author osher keinan <a href="mailto:osherkeinan@gmail.com">osherkeinan@gmail.com</a> and nir bonofiel <a href="mailto:nirbono10@gmail.com">nirbono10@gmail.com</a>
 *
 */
@Entity
@Table(name = "ITEMS")
public class Item 
{
	private String toDoDescription;
	private String username;
	private long id;
	
/**
 * present item's status
 */
	public enum Status
	{
		PENDING, INPROGRESS, DONE;
	}
	private Status eStatus;
	
	/**
	 * default constructor
	 */
	public Item()
	{
		super();
	}
	/**
	 * creating item with values (id is auto generated)
	 * @param toDoDescription description about the item
	 * @param username  of item's owner
	 * @param eStatus unique id of the item
	 */
	public Item(String toDoDescription, String username, Status eStatus)
	{
		super();
		setToDoDescription(toDoDescription);
		setUsername(username);
		seteStatus(eStatus);
	}
	/**
	 * returns the item's id
	 * @return id
	 */
    @Id
    @Column(name = "id")
    @GeneratedValue
	public long getId() 
	{
		return id;
	}
    /**
     * set id
     * @param id
     */
	public void setId(long id) 
	{
		this.id = id;
	}
	/**
	 * returns the item's description
	 * @return description
	 */
	@Column(name = "description")
	public String getToDoDescription()
	{
		return toDoDescription;
	}
	/**
	 * set description
	 * @param toDoDescription
	 */
	public void setToDoDescription(String toDoDescription) 
	{
		if((toDoDescription != null) && (toDoDescription != "")){
		this.toDoDescription = toDoDescription;
		}
	}
	/**
	 * returns the item's username
	 * @return username
	 */
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
	 * returns the item's status
	 * @return status
	 */
	@Column(name = "status")
	public Status geteStatus()
	{
		return eStatus;
	}
	/**
	 * set status
	 * @param eStatus
	 */
	public void seteStatus(Status eStatus) {
		if (eStatus != null) {
			this.eStatus = eStatus;
		}
	}
	/**
	 * this method return string with all details about item
	 */
	@Override
	public String toString()
	{
		return "[" + id + ", " + toDoDescription + ", " + "username:" + username +", "+ "status:" + eStatus.toString() + "]";
	}
	
}
