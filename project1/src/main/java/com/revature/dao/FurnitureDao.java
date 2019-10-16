package com.revature.dao;

import java.util.List;

import com.revature.pojo.Furniture;
import com.revature.pojo.User;

public interface FurnitureDao {
	
	public Furniture getFurniture(String name);
	
	public void createFurniture(Furniture furniture);
	
	public List<Furniture> getAllFurnitures();
	
	public void updateFurniture(Furniture furniture);
	
	public void deleteFurniture(Furniture furniture);
	
	public List<Furniture> getByOwner(User user);

}
