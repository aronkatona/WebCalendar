package com.aronkatona.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.aronkatona.model.RegImage;
import com.aronkatona.model.User;

public class RegImageDAOImpl implements RegImageDAO {

private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void saveRegImage(RegImage rImg) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(rImg);		
	}

	@Override
	public void updateRegImage(RegImage rImg) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(rImg);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegImage> getRegImages() {
		Session session = this.sessionFactory.getCurrentSession();
		List<RegImage> rImgList = session.createQuery("from RegImage").list();
		return rImgList;
	}

	@Override
	public RegImage getRegImageById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		RegImage rImg = (RegImage) session.get(RegImage.class, new Integer(id));
		return rImg;
	}

	@Override
	public void removeRegImage(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		RegImage rImg = (RegImage) session.get(RegImage.class, new Integer(id));
		if(rImg != null){
			session.delete(rImg);
		}
		
	}
	
}
