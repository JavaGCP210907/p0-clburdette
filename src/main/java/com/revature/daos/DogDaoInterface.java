package com.revature.daos;

import java.util.List;

import com.revature.models.Dog;

public interface DogDaoInterface {
	
	public List <Dog> getDogs();
	public List <Dog> getDogsByName(String name);
	public List <Dog> getDogsByBreed(String breed);
	public List <Dog> getDogsByAge(int startAge, int endAge);
	public List <Dog> getDogsBySize(String size);
	public void addDog(Dog dog);

}
