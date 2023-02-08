package globalRain;

//Dog Class
public class Dog extends RescueAnimal {

    // Instance variable
    private String breed;
    
    //Default Dog Constructor
    public Dog () {
    	setAnimalType("Dog");
    }

    //Constructor
    public Dog(int ID, String name, String breed, String gender, String age,
    String weight, String acquisitionDate, String acquisitionCountry,
	String trainingStatus, boolean reserved, String inServiceCountry) {
    	setID(ID);
        setName(name);
        setAnimalType("Dog");
        setBreed(breed);
        setGender(gender);
        setAge(age);
        setWeight(weight);
        setAcquisitionDate(acquisitionDate);
        setAcquisitionLocation(acquisitionCountry);
        setTrainingStatus(trainingStatus);
        setReserved(reserved);
        setInServiceCountry(inServiceCountry);

    }

    // Accessor Method
    public String getBreed() {
        return breed;
    }

    // Mutator Method
    public void setBreed(String dogBreed) {
		breed = dogBreed;
	}

}
