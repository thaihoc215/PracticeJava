package org.hochnt.spring.lang.impl;

import org.hochnt.spring.lang.Language;

public class English implements Language {
	 
	  @Override
	  public String getGreeting() {
	      return "Hello";
	  }
	 
	  @Override
	  public String getBye() {
	      return "Bye bye";
	  }

}
