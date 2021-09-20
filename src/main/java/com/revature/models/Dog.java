package com.revature.models;

public class Dog {
	
	int dog_id;
	String name;
	String breed;
	int age;
	boolean small;
	int owner_fk;
	
	public Dog() {
		super();
	}

	public Dog(String name, String breed, int age, boolean small, int owner_fk) {
		super();
		this.name = name;
		this.breed = breed;
		this.age = age;
		this.small = small;
		this.owner_fk = owner_fk;
	}

	public Dog(int dog_id, String name, String breed, int age, boolean small, int owner_fk) {
		super();
		this.dog_id = dog_id;
		this.name = name;
		this.breed = breed;
		this.age = age;
		this.small = small;
		this.owner_fk = owner_fk;
	}

	public int getDog_id() {
		return dog_id;
	}

	public void setDog_id(int dog_id) {
		this.dog_id = dog_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isSmall() {
		return small;
	}

	public void setSmall(boolean small) {
		this.small = small;
	}

	public int getOwner_fk() {
		return owner_fk;
	}

	public void setOwner_fk(int owner_fk) {
		this.owner_fk = owner_fk;
	}

	@Override
	public String toString() {
		return "Dog [dog_id=" + dog_id + ", name=" + name + ", breed=" + breed + ", age=" + age + ", small=" + small
				+ ", owner_fk=" + owner_fk + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((breed == null) ? 0 : breed.hashCode());
		result = prime * result + dog_id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + owner_fk;
		result = prime * result + (small ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dog other = (Dog) obj;
		if (age != other.age)
			return false;
		if (breed == null) {
			if (other.breed != null)
				return false;
		} else if (!breed.equals(other.breed))
			return false;
		if (dog_id != other.dog_id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (owner_fk != other.owner_fk)
			return false;
		if (small != other.small)
			return false;
		return true;
	}
	
	
}
