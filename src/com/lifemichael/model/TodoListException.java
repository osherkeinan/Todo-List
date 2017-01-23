package com.lifemichael.model;
/**
 * The class TodoListException and its subclasses are a form of Throwable that indicates conditions that a reasonable application might want to catch.
 * @author osher keinan <a href="mailto:osherkeinan@gmail.com">osherkeinan@gmail.com</a> and nir bonofiel <a href="mailto:nirbono10@gmail.com">nirbono10@gmail.com</a>
 */
public class TodoListException extends Exception {
/**
 * Constructs a new exception with the specified detail message.
 * @param msg  - message with exception description 
 */
	public TodoListException(String msg) {
		super(msg);
	}
/**
 * Constructs a new exception with the specified detail message and throwable with the primary exception.
 * @param msg
 * @param thr
 */
	public TodoListException(String msg, Throwable thr) {
		super(msg, thr);
	}
}
