package com.aronkatona.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.aronkatona.dao.RegImageDAO;
import com.aronkatona.model.RegImage;

public class RegImageServiceImpl implements RegImageService {

	private RegImageDAO regImageDAO;

	public void setRegImageDAO(RegImageDAO regImageDAO) {
		this.regImageDAO = regImageDAO;
	}
	
	@Override
	@Transactional
	public void saveRegImage(RegImage rImg) {
		this.regImageDAO.saveRegImage(rImg);		
	}

	@Override
	@Transactional
	public void updateRegImage(RegImage rImg) {
		this.regImageDAO.updateRegImage(rImg);		
	}

	@Override
	@Transactional
	public List<RegImage> getRegImages() {
		return this.regImageDAO.getRegImages();
	}

	@Override
	@Transactional
	public RegImage getRegImageById(int id) {
		return this.regImageDAO.getRegImageById(id);
	}

	@Override
	@Transactional
	public void removeRegImage(int id) {
		this.regImageDAO.removeRegImage(id);
	}

}
