package org.hochnt.spring.bean;

import org.hochnt.spring.lang.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/* service là một annotation, nó được sử dụng để chú thích trên
 * một class để nói với Spring rằng class đó là một Spring BEAN.
 */
@Service
public class GreetingService {
	/*
	 * được chú thích trên một trường (field) để nói với Spring rằng hãy tiêm
	 * (inject) giá trị vào cho trường đó. Chú ý: Từ tiêm ở đây có ý giống với gán
	 * giá trị cho trường đó.
	 */
	@Autowired
	private Language language;

	public GreetingService() {

	}

	public void sayGreeting() {

		String greeting = language.getGreeting();

		System.out.println("Greeting: " + greeting);
	}
}
