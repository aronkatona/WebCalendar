package com.aronkatona.service;

import java.util.List;

import com.aronkatona.model.RegImage;

public interface RegImageService {

	public void saveRegImage(RegImage rImg);
	public void updateRegImage(RegImage rImg);
	public List<RegImage> getRegImages();
	public RegImage getRegImageById(int id);
	public void removeRegImage(int id);
} 
