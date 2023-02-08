package globalRain;

//Monkey Class
public class Monkey extends RescueAnimal {

	// Monkey Attributes
	private String tailLength;
	private String height;
	private String bodyLength;

	public enum monkeySpecies {
		Capuchin, Guenon, Macaque, Marmoset, Squirrel_Monkey, Tamarin
	};

	private monkeySpecies monkey;
	
	/*
	 * Default Constructor - Added Set Animal Type to indicate that on default
	 * creation of this object, it becomes a monkey.
	 */
	public Monkey() {
		setAnimalType("Monkey");
	}

	// Attribute Constructor
	public Monkey(int ID, String name, String gender, String age, String weight, String acquisitionDate,
			String acquisitionCountry, String trainingStatus, boolean reserved, String inServiceCountry, String tailLen,
			String height, String bodyLen, monkeySpecies monkey) {

		// Attributes inherited from Rescue Animal
		setID(ID);
		setName(name);
		setAnimalType("Monkey");
		setGender(gender);
		setAge(age);
		setWeight(weight);
		setAcquisitionDate(acquisitionDate);
		setAcquisitionLocation(acquisitionCountry);
		setTrainingStatus(trainingStatus);
		setReserved(reserved);
		setInServiceCountry(inServiceCountry);

		// Attributes of Monkey Specific
		this.tailLength = tailLen;
		this.height = height;
		this.bodyLength = bodyLen;

		// Breed / Species
		this.monkey = monkey;

	}

	// Accessors and Mutators for Tail Length
	public void setTailLength(String tailLen) {
		this.tailLength = tailLen;
	}

	public String getTailLength() {
		return this.tailLength;
	}

	// Accessors and Mutators for Monkey Height
	public void setHeight(String monkeyHeight) {
		this.height = monkeyHeight;

	}

	public String getHeight() {
		return this.height;
	}

	// Accessors and Mutators for Monkey Body Length
	public void setBodyLength(String monkeyBodyLen) {
		this.bodyLength = monkeyBodyLen;
	}

	public String getBodyLength() {
		return this.bodyLength;
	}

	// Accessors and Mutators for Monkey Species
	public void setSpecies(monkeySpecies monkey) {
		this.monkey = monkey;
	}

	public monkeySpecies getSpecies() {
		return monkey;
	}
}
