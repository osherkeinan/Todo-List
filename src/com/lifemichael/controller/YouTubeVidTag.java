package com.lifemichael.controller;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport; ///

public class YouTubeVidTag extends SimpleTagSupport {

    private String src;
    
    public void setSrc(String src) {
        this.src = src;
    }

    public void doTag() throws JspException, IOException
    {
    JspWriter out = getJspContext().getOut();
    	out.print("<iframe width='420' height='315' src=" + src + " allowfullscreen></iframe>");
    }
}
