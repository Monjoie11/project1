package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import com.revature.pojo.Furniture;
import com.revature.pojo.User;

public class FurnitureDaoFake implements FurnitureDao {

	List<Furniture> furnitureRepository;

	{
		furnitureRepository = new ArrayList<Furniture>();

		furnitureRepository.add(new Furniture("furniture", "you sit on it", "Flat Surface", 9,
				new User("user", "pass", "My User"), 1000.00));
		furnitureRepository.add(
				new Furniture("any name", "any kind", "any make", 10, new User("bmoney1", "cookie", "Brian M"), 2.00));
		furnitureRepository.add(new Furniture("lounger", "comfortable", "chipotle", 3,
				new User("cmendez", "burrito", "Carlos M"), 7.89));
	}

	public Furniture getFurniture(String name) {

		Furniture ret = null;

		for (Furniture u : furnitureRepository) {
			if (u.getName().equals(name)) {
				ret = u;
			}
		}

		return ret;
	}

	public void createFurniture(Furniture furniture) {
		furnitureRepository.add(furniture);
	}

	public List<Furniture> getAllFurnitures() {
		return null;
	}

	public void updateFurniture(Furniture furniture) {

	}

	public void deleteFurniture(Furniture furniture) {

	}

	public List<Furniture> getByOwner(User user) {

		List<Furniture> ret = new ArrayList<Furniture>();

		for (Furniture u : furnitureRepository) {
			if (u.getOwner().equals(user)) {
				ret.add(u);
			}
		}

		return ret;

	}

}
