package org.hochnt.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
/*
 * là một annotation,
 * nó được chú thích trên một class để nói với Spring rằng 
 * class này là một Spring BEAN. 
 * */
public class MyComponent {
    
    @Autowired
    private MyRepository repository;
 
    public void showAppInfo() {
        System.out.println("Now is: "+ repository.getSystemDateTime());
        System.out.println("App Name: "+ repository.getAppName());
    }
 
}
