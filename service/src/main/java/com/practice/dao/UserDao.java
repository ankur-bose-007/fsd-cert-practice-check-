package com.practice.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.practice.entity.User;
@Repository
@Transactional
public class UserDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	public User signup(User user){
		Query query=entityManager.createQuery("from User u where u.email=?1").setParameter(1, user.getEmail());
		if(query.getResultList().size()!=0)
			user=null;
		else
			entityManager.persist(user);
		return user;
	}
	public User findByUsername(String email){
		Query query=entityManager.createQuery("from User u where u.email=?1").setParameter(1, email);
		try{
		return (User) query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
}