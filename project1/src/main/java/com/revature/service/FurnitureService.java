package com.revature.service;

import java.util.List;

import com.revature.dao.FurnitureDao;
import com.revature.dao.FurnitureDaoFake;
import com.revature.pojo.Furniture;
import com.revature.pojo.User;

public class FurnitureService {
	
	private static FurnitureDao furnitureDao = new FurnitureDaoFake();
	
	public List<Furniture> findAllFurnitureForUser(User user) {
		return furnitureDao.getByOwner(user);
	}

	public void makeFurniture(Furniture furniture) {
		furnitureDao.createFurniture(furniture);
	}
	
}
