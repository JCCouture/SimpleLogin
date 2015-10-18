package com.spring.SimpleLogin.account;

import java.util.List;

import javax.persistence.*;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class AccountRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
		
	@Transactional
	public Account save(Account account)
	{
		account.setPassword(account.getPassword());
		entityManager.persist(account);
		return account;
	}
	
	public Account findByEmail(String email)
	{
		try 
		{
			return entityManager.createNamedQuery(Account.FIND_BY_EMAIL, Account.class)
					.setParameter("email", email)
					.getSingleResult();
		} 
		catch (PersistenceException e)
		{
			return null;
		}
	}
	
	public List<Account> findAll()
	{
		return entityManager.createNamedQuery(Account.FIND_ALL,Account.class).getResultList();
	}

	
}
