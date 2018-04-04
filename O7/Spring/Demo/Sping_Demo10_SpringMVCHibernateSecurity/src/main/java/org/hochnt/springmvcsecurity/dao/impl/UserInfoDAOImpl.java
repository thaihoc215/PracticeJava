package org.hochnt.springmvcsecurity.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hochnt.springmvcsecurity.dao.UserInfoDAO;
import org.hochnt.springmvcsecurity.entity.User;
import org.hochnt.springmvcsecurity.entity.UserRole;
import org.hochnt.springmvcsecurity.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserInfoDAOImpl implements UserInfoDAO {
 
    @Autowired
    private SessionFactory sessionFactory;
    public UserInfoDAOImpl() {
    }
  
 
    @Override
    public UserInfo findUserInfo(String userName) {
    	String sql = "Select new " + UserInfo.class.getName() + "(u.username,u.password) "//
                + " from " + User.class.getName() + " u where u.username = :username ";
 
        Session session = sessionFactory.getCurrentSession();
 
        Query query = session.createQuery(sql);
        query.setParameter("username", userName);
         
        return (UserInfo) query.uniqueResult();
    }
 
 
    @Override
    public List<String> getUserRoles(String userName) {
    	String sql = "Select r.userRole "//
                + " from " + UserRole.class.getName() + " r where r.user.username = :username ";
 
        Session session = sessionFactory.getCurrentSession();
 
        Query query = session.createQuery(sql);
        query.setParameter("username", userName);
         
        List<String> roles = query.list();
 
        return roles;
    }
     
}
