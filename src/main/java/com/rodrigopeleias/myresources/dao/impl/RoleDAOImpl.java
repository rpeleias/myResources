package com.rodrigopeleias.myresources.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rodrigopeleias.myresources.dao.RoleDAO;
import com.rodrigopeleias.myresources.model.Role;

@Repository
public class RoleDAOImpl implements RoleDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addRole(Role role) {
		getSession().persist(role);
	}

	@Override
	public void updateRole(Role role) {
		getSession().update(role);
	}

	@Override
	public Role getRoleByName(String rolename) {
		Query query = getSession().createQuery("from Role where name = :name");
		query.setString("name", rolename);
		Role role = (Role)query.uniqueResult();
		return role;
	}

	@Override
	public void removeRole(long id) {
		Role role = (Role)getSession().load(Role.class, new Long(id));
		if (role != null) {
			getSession().delete(role);
		}

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> listRoles() {
		return getSession().createQuery("from Role").list();		
	}
	
	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
}
