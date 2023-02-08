package globalRain;

import java.lang.String;

public class RescueAnimal {

	// Instance variables
	private String name;
	private String animalType;
	private String gender;
	private String age;
	private String weight;
	private String acquisitionDate;
	private String acquisitionCountry;
	private String trainingStatus;
	private boolean reserved;
	private String inServiceCountry;
	private int ID;

	// Constructor
	public RescueAnimal() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	//Mutator added to give each animal a line number, i.e., index value, to ensure list persistence while app is running.
	public void setID(int ID) {
		this.ID = ID;
	}
	
	//Accessor added to give each animal a line number, i.e., index value, to ensure list persistence while app is running.
	public int getID () {
		return this.ID;
	}

	public String getAnimalType() {
		return animalType;
	}

	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getAcquisitionDate() {
		return acquisitionDate;
	}

	public void setAcquisitionDate(String acquisitionDate) {
		this.acquisitionDate = acquisitionDate;
	}

	public String getAcquisitionLocation() {
		return acquisitionCountry;
	}

	public void setAcquisitionLocation(String acquisitionCountry) {
		this.acquisitionCountry = acquisitionCountry;
	}

	public boolean getReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	public String getInServiceLocation() {
		return inServiceCountry;
	}

	public void setInServiceCountry(String inServiceCountry) {
		this.inServiceCountry = inServiceCountry;
	}

	public String getTrainingStatus() {
		return trainingStatus;
	}

	public void setTrainingStatus(String trainingStatus) {
		this.trainingStatus = trainingStatus;
	}
}
