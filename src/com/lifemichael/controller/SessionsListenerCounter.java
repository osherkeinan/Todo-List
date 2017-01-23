package com.lifemichael.controller;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionsListenerCounter implements HttpSessionListener {

  private static int totalActiveSessions = 0; 
  private static String SessionsLog = "";

  public static int getTotalActiveSession(){
	return totalActiveSessions;
  }
  
  public static String getSessionsLog(){
	return SessionsLog;
  }

  @Override
  public void sessionCreated(HttpSessionEvent arg0) {
	totalActiveSessions++;
	SessionsLog += "<br>sessionCreated - add one session into counter total";
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent arg0) {
	  if(totalActiveSessions > 0)
	  {
		  totalActiveSessions--;
		  SessionsLog += "<br>sessionDestroyed - deduct one session from counter";
	  }
  }
}