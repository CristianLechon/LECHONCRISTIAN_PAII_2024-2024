package models;


public abstract class Person {

	private int id;
	private String name;
	private String lastname;
	private int age;

	public Person(int id, String name, String lastname, int age) {
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.age = age;
	}

	public Person() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "[ id = " + getId() + ", name = " + getName() + ", lastName = " + getLastname() + ", age = " + getAge() + " ]";
	}

}
