package globalRain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

//NOTE: Added [ID] Mutator and Accessor to Rescue Animal Class.

public class Driver {

	// Create list of Dogs, Monkeys, and of valid entries
	private static ArrayList<Dog> dogList = new ArrayList<Dog>();
	private static ArrayList<Monkey> monkeyList = new ArrayList<Monkey>();
	private static ArrayList<String> valueList = new ArrayList<String>();
	private static ArrayList<String> trainingStates = new ArrayList<String>();

	// Create list to return found dog object and boolean.
	private static List<Object> dogValues = new ArrayList<Object>();

	// Create list to return found monkey object and boolean.
	private static List<Object> monkeyValues = new ArrayList<Object>();

	// Create Loop variables.
	private static Boolean appRunning = false;
	private static Boolean isValid = false;

	// Main Method
	public static void main(String[] args) {

		// Create list of dogs and list of monkeys.
		if (dogList.isEmpty() && monkeyList.isEmpty()) {
			initializeDogList();
			initializeMonkeyList();
			initializeAppLists();
		}

		while (!appRunning) {

			// Create means of grabbing user input
			Scanner scnr = new Scanner(System.in);

			// Introduction text
			System.out.println("\n\n \t\tWelcome to Grazioso Salvare's Rescue Training Facility.");

			// Call to menu method.
			displayMenu();

			// Set switch variable to empty string
			String switchVar = "";

			// Get user input
			switchVar = scnr.nextLine();

			// Get User input and Set incoming values to uppercase regardless of user input
			// to avoid required case of 'q'.
			switchVar = switchVar.toUpperCase();

			// Check incoming value to assure it is within the list of existing values.
			isValid = checkUserInput(switchVar);

			// Handle invalid options
			while (!isValid) {

				// Provide user with valid inputs
				System.out.println("Please input a valid option. Valid Options: 1, 2, 3, 4, 5, 6, q.");

				// Get User input and Set incoming values to uppercase regardless of user input
				// to avoid required case of 'q'.
				switchVar = scnr.nextLine().toUpperCase();

				// Check input again.
				isValid = checkUserInput(switchVar);

			}

			// Change boolean to activate menu navigation below.
			if (isValid) {

				// Set app running to true to start next while loop.
				appRunning = true;

			}

			// Create while loop to sustain menu loop.
			while (isValid && appRunning) {

				// Create list objects for monkeys and dogs.
				List<Object> monkeyFound = new ArrayList<Object>();
				List<Object> dogFound = new ArrayList<Object>();

				// Boolean to assist with check dog and check monkey polymorphism.
				Boolean creatingAnimal = false;

				// String to handle print methods
				String animalType = "";

				// Handle menu functionality.
				switch (switchVar) {

				// Dog Intake.
				case "1":

					// Boolean to allow polymorphism for checkDog method.
					creatingAnimal = true;

					// Check if dog exists, pass in list of objects to return list of objects
					// populated with dog object and boolean value.
					dogFound = checkDog(dogValues, scnr, creatingAnimal);

					// Get Dog Object back by casting first value in list as Dog object.
					Dog dogObject = (Dog) dogFound.get(0);

					// Check second index within list, i.e., boolean value, to see if it has return
					// true.
					if (dogFound.get(1).equals(true)) {

						// State existence to user.
						System.out.println(dogObject.getName() + " is already in our system!\n\n");

						// Reset Loop Variables.
						resetLoopVar(dogValues);

						// If dog was created, not found.
					} else if (dogFound.get(1).equals(false)) {
						// User Message
						System.out.println(dogObject.getName() + " has been registered. Have a wonderful day!");

						// Reset Loop Variables.
						resetLoopVar(dogValues);
					}

					// Break out of loop.
					break;

				// Monkey Intake.
				case "2":

					// Boolean to allow polymorphism for checkMonkey method.
					creatingAnimal = true;

					// Check if Monkey exists, pass in list of objects to return list of objects
					// populated with Monkey object and boolean value.
					monkeyFound = checkMonkey(monkeyValues, scnr, creatingAnimal);

					// Get monkey Object back by casting first value in list as monkey object.
					Monkey monkeyObject = (Monkey) monkeyFound.get(0);

					// Check second index within list, i.e., boolean value, to see if it has return
					// true.
					if (monkeyFound.get(1).equals(true)) {

						// State existence to user.
						System.out.println(monkeyObject.getName() + " is already in our system!\n\n");

						// Reset Loop Variables.
						resetLoopVar(monkeyValues);

						// If monkey was not found, but created.
					} else if (monkeyFound.get(1).equals(false)) {

						// User Message
						System.out.println(monkeyObject.getName() + " has been registered. Have a wonderful day!");

						// Reset Loop Variables.
						resetLoopVar(monkeyValues);
					}

					// Break out of loop.
					break;

				// Animal Reserve
				case "3":
					// Create empty string for while loop.
					String reserveVar = "";

					// While reserveVar does not have a value.
					while (reserveVar == "") {

						// User Ouput
						System.out.println("Which animal type would you like to reserve? Ex: Dog, Monkey, Q.");

						// Get Type
						reserveVar = scnr.nextLine();

						// Create regex string to ensure user cannot input a string of invalid
						// characters, i.e., non-alphanumerical characters.
						String regex = "^[a-zA-Z]*$";

						// User input validation
						if (reserveVar.length() > 0 && reserveVar.matches(regex) && !reserveVar.equals(" ")) {

							// Reserve animal: Dog
							if (reserveVar.equalsIgnoreCase("Dog")) {

								// Check if dog exists
								dogFound = checkDog(dogValues, scnr, creatingAnimal);

								// If boolean returning is false, then check dog was unable to find animal to
								// reserve of specified name.
								if (dogValues.get(0).equals(false)) {

									// Reset loop variable.
									reserveVar = "";

									// Reset Loop Variables.
									resetLoopVar(dogValues);

								} else {
									// Get Dog Object back by casting first value in list as Dog object.
									dogObject = (Dog) dogFound.get(0);

									// Call to reserve method
									reserveAnimal(dogObject);

									// Reset Loop Variables.
									resetLoopVar(dogValues);

								}

								// Reserve animal: Monkey
							} else if (reserveVar.equalsIgnoreCase("Monkey")) {

								// Check if monkey exists
								monkeyFound = checkMonkey(monkeyValues, scnr, creatingAnimal);

								// If boolean returning is false, then check monkey was unable to find animal to
								// reserve of specified name.
								if (monkeyValues.get(0).equals(false)) {

									// Reset loop variable.
									reserveVar = "";

									// Reset Loop Variables.
									resetLoopVar(monkeyValues);

								} else {
									// Get Monkey Object back by casting first value in list as Monkey object.
									monkeyObject = (Monkey) monkeyFound.get(0);

									// Call to reserve method
									reserveAnimal(monkeyObject);

									// Reset Loop Variables.
									resetLoopVar(monkeyValues);
								}

							} else if (reserveVar.equalsIgnoreCase("q")) {

								// Break out of loop
								break;

							} else {

								// User Input validation for incorrect input.
								System.out.println("Invalid input. Please input: Dog, Monkey, Q.");

								// Reset text variable to reset loop.
								reserveVar = "";
							}

						} else {

							// User Input valildation for incorrect input.
							System.out.println("Invalid input. Please input: Dog, Monkey, Q.");

							// Reset text variable to reset loop.
							reserveVar = "";
						}
					}

					// Reset Loop Variables
					resetLoopVar();

					// Break out of loop.
					break;

				// Print All Dogs
				case "4":

					// Set String to Dog
					animalType = "Dog";

					// Call to print animals method
					printAnimals(animalType);

					// Reset Loop Variables
					resetLoopVar();

					// Break out of loop.
					break;

				// Print All Monkeys
				case "5":

					// Set String to monkey
					animalType = "Monkey";

					// Call to print animals method
					printAnimals(animalType);

					// Reset Loop Variables
					resetLoopVar();

					// Break out of loop.
					break;

				// Print All non-reserved animals
				case "6":

					// Set String to All
					animalType = "All";

					// Call to print animals method
					printAnimals(animalType);

					// Reset Loop Variables
					resetLoopVar();

					// Break out of loop.
					break;

				}
			}

			// Quit Functionality
			if (switchVar == "Q") {

				// Close scannner resource.
				scnr.close();

				// User Output.
				System.out.println("Have a wonderful day!");

				// Close application.
				System.exit(0);
			}
		}
	}

	// Create lists for validation checking
	private static void initializeAppLists() {

		// Add valid values to value list.
		valueList.add("1");
		valueList.add("2");
		valueList.add("3");
		valueList.add("4");
		valueList.add("5");
		valueList.add("6");
		valueList.add("Q");

		// Add training states to training states list.
		trainingStates.add("Intake");
		trainingStates.add("Phase I");
		trainingStates.add("Phase II");
		trainingStates.add("Phase III");
		trainingStates.add("Phase IV");
		trainingStates.add("Phase V");
		trainingStates.add("In-Service");
		trainingStates.add("farm");

	}

	// Reset Loop Variables
	private static void resetLoopVar() {

		// Reset loop variables to put user back at the main menu.
		isValid = false;
		appRunning = false;

	}

	// Overloaded - Reset Loop Variables Method - Clear out List Array.
	private static void resetLoopVar(List<Object> dogValues) {

		// Reset loop variables to put user back at the main menu.
		isValid = false;
		appRunning = false;

		// Clear array list to be ready for next usage.
		dogValues.clear();

	}

	// Monkey Validation

	// Monkey Validation Method
	private static List<Object> checkMonkey(List<Object> monkeyValues, Scanner scan, Boolean creatingMonkey) {

		// Boolean to check if monkey was found.
		Boolean wasMonkeyFound = false;

		// Create placeholder string
		String name = "";

		// Create regex string to ensure user cannot input a string of invalid
		// characters, i.e., non-alphanumerical characters.
		String regex = "^[a-zA-Z]*$";

		// Grab new instance of scanner
		while (name.equals("")) {

			// Display text to user requesting name.
			System.out.println("What is your monkey's name?");

			// Get user Input.
			name = scan.nextLine();

			// Ensure monkey name matches regex pattern and is NOT empty.
			if (name.matches(regex) && !name.isEmpty()) {

				// Iterate through each monkey in the list.
				for (Monkey monkey : monkeyList) {

					// Check if dog exists in list.
					if (monkey.getName().equalsIgnoreCase(name)) {

						// Call to monkey constructor to get all values and populate them into new
						// object.
						Monkey foundMonkey = new Monkey(monkey.getID(), monkey.getName(), monkey.getGender(),
								monkey.getAge(), monkey.getWeight(), monkey.getAcquisitionDate(),
								monkey.getAcquisitionLocation(), monkey.getTrainingStatus(), monkey.getReserved(),
								monkey.getInServiceLocation(), monkey.getTailLength(), monkey.getHeight(),
								monkey.getBodyLength(), monkey.getSpecies());

						// Set returning boolean to true; a monkey was found.
						wasMonkeyFound = true;

						// Add monkey object and boolean to list of objects.
						monkeyValues.add(foundMonkey);
						monkeyValues.add(wasMonkeyFound);

						// Return found monkey object
						return monkeyValues;
					}
				}

			} else {
				// Output for user validation
				System.out.println("Invalid name input. Please input alphabetical values only.");

				// Set name back to empty string to trigger loop.
				name = "";
			}
		}

		// If monkey was not found, then start process of monkey creation.
		if (!wasMonkeyFound) {
			if (creatingMonkey) {

				// Create empty monkey object
				Monkey monkeyObject = new Monkey();

				// Set Monkey object's first value: Name.
				monkeyObject.setName(name);

				// Create monkey
				wasMonkeyFound = intakeMonkey(monkeyObject, scan);

				// Add created monkey and boolean to return list
				monkeyValues.add(monkeyObject);
				monkeyValues.add(wasMonkeyFound);

			} else {

				// User input validation
				System.out.println(name + " does not exist in our system.");

				// Add boolean to list of objects.
				monkeyValues.add(wasMonkeyFound = false);
			}
		}

		// Return list of objects
		return monkeyValues;
	}

	// Dog Validation Method
	private static List<Object> checkDog(List<Object> dogValues, Scanner scan, Boolean creatingDog) {

		// Boolean to check if dog was found.
		Boolean wasDogFound = false;

		// Create placeholder string
		String name = "";

		// Create regex string to ensure user cannot input a string of invalid
		// characters, i.e., non-alphanumerical characters.
		String regex = "^[a-zA-Z]*$";

		while (name.equals("")) {

			// Display text to user requesting name.
			System.out.println("What is your dog's name?");

			// Get user Input.
			name = scan.nextLine();

			// Ensure dog name matches regex pattern and is NOT empty.
			if (name.matches(regex) && !name.isEmpty()) {

				// Iterate through each dog in the list.
				for (Dog dog : dogList) {

					// Check if dog exists in list.
					if (dog.getName().equalsIgnoreCase(name)) {

						// Call to dog constructor to get all values and populate them into new object.
						Dog foundDog = new Dog(dog.getID(), dog.getName(), dog.getBreed(), dog.getGender(),
								dog.getAge(), dog.getWeight(), dog.getAcquisitionDate(), dog.getAcquisitionLocation(),
								dog.getTrainingStatus(), dog.getReserved(), dog.getInServiceLocation());

						// Set returning boolean to true; a monkey was found.
						wasDogFound = true;

						// Add dog object and boolean to list of objects.
						dogValues.add(foundDog);
						dogValues.add(wasDogFound);

						// Return found dog object
						return dogValues;
					}
				}

			} else {
				// Output for user validation
				System.out.println("Invalid name input. Please input alphabetical values only.");

				// Set name back to empty string to trigger loop.
				name = "";
			}
		}

		// If dog was not found, then start process of dog creation.
		if (!wasDogFound) {

			// Check if being called from the intake method.
			if (creatingDog) {
				// Create empty dog object
				Dog dogObject = new Dog();

				// Set Dog object's first value: Name.
				dogObject.setName(name);

				// Create Dog
				wasDogFound = intakeDog(dogObject, scan);

				// Add created dog and boolean to return list
				dogValues.add(dogObject);
				dogValues.add(wasDogFound);

			} else {

				// User input validation
				System.out.println(name + " does not exist in our system.");

				// Add boolean to list of objects.
				dogValues.add(wasDogFound = false);
			}
		}

		// Return list of objects
		return dogValues;
	}

	// User Input Validation Method
	private static Boolean checkUserInput(String switchVar) {

		// Iterate through list to check if the switch variable is valid
		for (String value : valueList) {
			if (switchVar.equals(value)) {
				return true;
			}
		}

		// If iteration cannot find the value, then return false.
		return false;
	}

	// Introduction Text
	public static void displayMenu() {
		System.out.println("\n");
		System.out.println("\t\t\t\tRescue Animal System Menu");
		System.out.println("[1] Intake a new dog");
		System.out.println("[2] Intake a new monkey");
		System.out.println("[3] Reserve an animal");
		System.out.println("[4] Print a list of all dogs");
		System.out.println("[5] Print a list of all monkeys");
		System.out.println("[6] Print a list of all animals that are not reserved");
		System.out.println("[q] Quit application");
		System.out.println();
		System.out.println("Enter a menu selection");
	}

	// Populate Dog List Method
	public static void initializeDogList() {

		// Create new dogs
		Dog dog1 = new Dog(0, "Spot", "German Shepherd", "male", "1", "25.6", "05-12-2019", "United States", "Intake",
				false, "United States");
		Dog dog2 = new Dog(1, "Rex", "Great Dane", "male", "3", "35.2", "02-03-2020", "United States", "Phase I", false,
				"United States");
		Dog dog3 = new Dog(2, "Bella", "Chihuahua", "female", "4", "25.6", "12-12-2019", "Canada", "In-Service", false,
				"Canada");

		// Add dogs to list.
		dogList.add(dog1);
		dogList.add(dog2);
		dogList.add(dog3);
	}

	// Populate Monkey List Method
	// Populate Monkey List Method
	public static void initializeMonkeyList() {

		// Create new monkeys
		Monkey monkey1 = new Monkey(0, "Kong", "Male", "1000", "500", "06-24-1992", "Skull Island", "Phase V", false,
				"United States", "10", "50", "30", Monkey.monkeySpecies.Marmoset);

		Monkey monkey2 = new Monkey(1, "Diddy", "Male", "500", "300", "01-01-1900", "Banana Island", "In-Service",
				false, "Japan", "10", "50", "30", Monkey.monkeySpecies.Capuchin);

		Monkey monkey3 = new Monkey(2, "Bobo", "Male", "100", "200", "02-24-1976", "Rex Island", "Phase III", false,
				"United States", "10", "50", "30", Monkey.monkeySpecies.Guenon);

		// Add monkeys to list.
		monkeyList.add(monkey1);
		monkeyList.add(monkey2);
		monkeyList.add(monkey3);
	}

	// Intake Dog
	// Dog intake method
	// Create Dog Method
	public static Boolean intakeDog(Dog dogObject, Scanner scan) {

		// Create string placeholder
		String userInput = "";

		// Create regex string to ensure user cannot input a string of invalid
		// characters, i.e., non-alphanumerical characters.
		String regex = "^[a-zA-Z\s]*$";

		// Create regex string to ensure user cannot input anything but ints.
		String intRegex = "^[0-9]*$";

		// Get current date and location.
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		Locale currentLocale = Locale.getDefault();

		// Dog Breed
		while (dogObject.getBreed() == null) {

			// User message output
			System.out.println("What is " + dogObject.getName() + "'s breed?");

			// Get User Input
			userInput = scan.nextLine();

			// User input validation
			if (userInput.length() > 0 && userInput.matches(regex) && !userInput.equals(" ")) {

				// Add User input to dog object.
				dogObject.setBreed(userInput);

			} else {

				// User Output
				System.out.println("Invalid Input. Please input " + dogObject.getName() + "'s breed.");
			}
		}

		// Dog Gender
		while (dogObject.getGender() == null) {

			// User message output
			System.out.println("What is " + dogObject.getName() + "'s gender?");

			// Get User Input
			userInput = scan.nextLine();

			// User input validation
			if (userInput.length() > 0 && userInput.matches(regex) && !userInput.equals(" ")) {

				// User input validation to ensure either male or female has been input; also
				// checking for single values, i.e., m or f.
				if (userInput.equalsIgnoreCase("Male") || userInput.equalsIgnoreCase("m")) {

					// Add user input to dog object value: gender
					dogObject.setGender("Male");

				} else if (userInput.equalsIgnoreCase("Female") || userInput.equalsIgnoreCase("f")) {

					// Add user input to dog object value: gender
					dogObject.setGender("Female");

				} else {
					// User Output
					System.out.println(
							"Invalid Input. Please input " + dogObject.getName() + "'s gender. Ex: Male or Female.");
				}

			} else {

				// User Output
				System.out.println("Invalid Input. Please input " + dogObject.getName() + "'s gender.");
			}
		}

		// Dog Age
		while (dogObject.getAge() == null) {

			// User message output
			System.out.println("What is " + dogObject.getName() + "'s age?");

			// Get User Input
			userInput = scan.nextLine();

			// User input validation
			if (userInput.length() > 0 && userInput.matches(intRegex) && !userInput.equals(" ")) {

				// Add User input to dog object.
				dogObject.setAge(userInput);

			} else {

				// User Output
				System.out.println("Invalid Input. Please input " + dogObject.getName() + "'s age.");
			}
		}

		// Dog Weight
		while (dogObject.getWeight() == null) {

			// User message output
			System.out.println("What is " + dogObject.getName() + "'s weight?");

			// Get User Input
			userInput = scan.nextLine();

			// User input validation
			if (userInput.length() > 0 && userInput.matches(intRegex) && !userInput.equals(" ")) {

				// Add User input to dog object.
				dogObject.setWeight(userInput);

				// Reset User Input for Boolean Below.
				userInput = "";

			} else {

				// User Output
				System.out.println("Invalid Input. Please input " + dogObject.getName() + "'s weight.");
				userInput = "";
			}
		}

		// Aquisition Date - Set date to current date.
		dogObject.setAcquisitionDate(date.format(formatter));

		// Aquisition Country - Set location according to where the user is accessing
		// the application.
		dogObject.setAcquisitionLocation(currentLocale.getDisplayCountry());

		// Training Status - Set training state to intake upon creation.
		dogObject.setTrainingStatus(trainingStates.get(0));

		// Reserved
		while (userInput == "") {

			// User output
			System.out.println("Will " + dogObject.getName() + " be reserved for training?");

			// Get User Input
			userInput = scan.nextLine();

			// User validation for Y, Yes, N, No valid options.
			if (userInput.equalsIgnoreCase("Yes") || userInput.equalsIgnoreCase("Y")) {

				// Set Dog object - reserved to true.
				dogObject.setReserved(true);

			} else if (userInput.equalsIgnoreCase("No") || userInput.equalsIgnoreCase("N")) {

				// Set Dog object - reserved to false.
				dogObject.setReserved(false);

			} else {

				// User output invalid inputs
				System.out.println("Invalid Input. Please state whether " + dogObject.getName()
						+ " will be reserved. (Yes / Y OR No / N)");

				// Reset loop variable back to empty.
				userInput = "";
			}
		}

		// Service Country
		while (dogObject.getInServiceLocation() == null) {

			// User message output
			System.out.println("Which country will " + dogObject.getName() + " be in service?");

			// Get User Input
			userInput = scan.nextLine();

			// User input validation
			if (userInput.length() > 0 && userInput.matches(regex) && !userInput.equals(" ")) {

				// Add User input to dog object.
				dogObject.setInServiceCountry(userInput);

			} else {

				// User Output
				System.out.println("Invalid Input. Please input " + dogObject.getName() + "'s service country.");
			}
		}

		// Add Dog object to Dog List
		dogList.add(dogObject);

		// Return boolean value
		return false;

	}

	// Monkey Intake
	// Create Monkey Method
	public static Boolean intakeMonkey(Monkey monkeyObject, Scanner scan) {

		// Create variables
		String userInput = "";

		// Create regex string to ensure user cannot input a string of invalid
		// characters, i.e., non-alphanumerical characters.
		String regex = "^[a-zA-Z\s]*$";

		// Create regex string to ensure user cannot input anything but ints.
		String intRegex = "^[0-9]*$";

		// Get current date and location.
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		Locale currentLocale = Locale.getDefault();

		// Monkey Species
		while (monkeyObject.getSpecies() == null) {

			// User message output
			System.out.println("What is " + monkeyObject.getName()
					+ "'s breed? Values: Capuchin, Guenon, Macaque, Marmoset, Squirrel_Monkey, Tamarin");

			// Get User Input
			userInput = scan.nextLine();

			// User input validation
			if (userInput.length() > 0 && userInput.matches(regex) && !userInput.equals(" ")) {

				// For each monkey in the enum, check if the user's input is equal.
				for (Monkey.monkeySpecies monkey : Monkey.monkeySpecies.values()) {
					if (userInput.equalsIgnoreCase(monkey.toString())) {

						// Set monkey species to found monkey in enum.
						monkeyObject.setSpecies(monkey);
						break;
					}
				}

				// If for loop was unable to find a monkey that matches the user's input.
				if (monkeyObject.getSpecies() == null) {

					// User Output
					System.out.println("Invalid Input. Please input " + monkeyObject.getName()
							+ "'s breed. Values: Capuchin, Guenon, Macaque, Marmoset, Squirrel_Monkey, Tamarin.");
				}

			}
		}

		// Monkey Gender
		while (monkeyObject.getGender() == null) {

			// User message output
			System.out.println("What is " + monkeyObject.getName() + "'s gender?");

			// Get User Input
			userInput = scan.nextLine();

			// User input validation
			if (userInput.length() > 0 && userInput.matches(regex) && !userInput.equals(" ")) {

				// User input validation to ensure either male or female has been input; also
				// checking for single values, i.e., m or f.
				if (userInput.equalsIgnoreCase("Male") || userInput.equalsIgnoreCase("m")) {

					// Add user input to dog object value: gender
					monkeyObject.setGender("Male");

				} else if (userInput.equalsIgnoreCase("Female") || userInput.equalsIgnoreCase("f")) {

					// Add user input to dog object value: gender
					monkeyObject.setGender("Female");

				} else {
					// User Output
					System.out.println(
							"Invalid Input. Please input " + monkeyObject.getName() + "'s gender. Ex: Male or Female.");
				}

			} else {

				// User Output
				System.out.println(
						"Invalid Input. Please input " + monkeyObject.getName() + "'s gender. Ex: Male or Female.");
			}
		}

		// Monkey Age
		while (monkeyObject.getAge() == null) {

			// User message output
			System.out.println("What is " + monkeyObject.getName() + "'s age?");

			// Get User Input
			userInput = scan.nextLine();

			// User input validation
			if (userInput.length() > 0 && userInput.matches(intRegex) && !userInput.equals(" ")) {

				// Add User input to Monkey object.
				monkeyObject.setAge(userInput);

			} else {

				// User Output
				System.out.println("Invalid Input. Please input " + monkeyObject.getName() + "'s age.");
			}
		}

		// Monkey Weight
		while (monkeyObject.getWeight() == null) {

			// User message output
			System.out.println("What is " + monkeyObject.getName() + "'s weight?");

			// Get User Input
			userInput = scan.nextLine();

			// User input validation
			if (userInput.length() > 0 && userInput.matches(intRegex) && !userInput.equals(" ")) {

				// Add User input to Monkey object.
				monkeyObject.setWeight(userInput);

				// Reset User Input for Boolean Below.
				userInput = "";

			} else {

				// User Output
				System.out.println("Invalid Input. Please input " + monkeyObject.getName() + "'s weight.");

				// Reset User Input for Boolean Below.
				userInput = "";
			}
		}

		// Aquisition Date - Set date to current date.
		monkeyObject.setAcquisitionDate(date.format(formatter));

		// Aquisition Country - Set location according to where the user is accessing
		// the application.
		monkeyObject.setAcquisitionLocation(currentLocale.getDisplayCountry());

		// Training Status - Set training state to intake upon creation.
		monkeyObject.setTrainingStatus(trainingStates.get(0));

		// Reserved
		while (userInput == "") {

			// User output
			System.out.println("Will " + monkeyObject.getName() + " be reserved for training?");

			// Get users input
			userInput = scan.nextLine().toUpperCase();

			// User validation for Y, Yes, N, No valid options.
			if (userInput.equalsIgnoreCase("Yes") || userInput.equalsIgnoreCase("Y")) {

				// Set Monkey object - reserved to true.
				monkeyObject.setReserved(true);

			} else if (userInput.equalsIgnoreCase("No") || userInput.equalsIgnoreCase("N")) {

				// Set Monkey object - reserved to false.
				monkeyObject.setReserved(false);

			} else {

				// User output invalid inputs.
				System.out.println("Invalid Input. Please state whether " + monkeyObject.getName()
						+ " will be reserved. (Yes / Y OR No / N)");

				// Reset User Input
				userInput = "";
			}
		}

		// Service Country
		while (monkeyObject.getInServiceLocation() == null) {

			// User message output
			System.out.println("Which country will " + monkeyObject.getName() + " be in service?");

			// Get User Input
			userInput = scan.nextLine();

			// User input validation
			if (userInput.length() > 0 && userInput.matches(regex) && !userInput.equals(" ")) {

				// Add User input to Monkey object.
				monkeyObject.setInServiceCountry(userInput);

			} else {

				// User Output
				System.out.println("Invalid Input. Please input " + monkeyObject.getName() + "'s service country.");
			}
		}

		// Tail Length
		while (monkeyObject.getTailLength() == null) {

			// User message output
			System.out.println("What is " + monkeyObject.getName() + "'s tail length?");

			// Get User Input
			userInput = scan.nextLine();

			// User input validation
			if (userInput.length() > 0 && userInput.matches(intRegex) && !userInput.equals(" ")) {

				// Add User input to Monkey object.
				monkeyObject.setTailLength(userInput);

			} else {

				// User Output
				System.out.println("Invalid Input. Please input " + monkeyObject.getName() + "'s tail length.");
			}
		}

		// Height
		while (monkeyObject.getHeight() == null) {

			// User message output
			System.out.println("What is " + monkeyObject.getName() + "'s height?");

			// Get User Input
			userInput = scan.nextLine();

			// User input validation
			if (userInput.length() > 0 && userInput.matches(intRegex) && !userInput.equals(" ")) {

				// Add User input to Monkey object.
				monkeyObject.setHeight(userInput);

			} else {

				// User Output
				System.out.println("Invalid Input. Please input " + monkeyObject.getName() + "'s height.");
			}
		}

		// Body Length
		while (monkeyObject.getBodyLength() == null) {

			// User message output
			System.out.println("What is " + monkeyObject.getName() + "'s body length?");

			// Get User Input
			userInput = scan.nextLine();

			// User input validation
			if (userInput.length() > 0 && userInput.matches(intRegex) && !userInput.equals(" ")) {

				// Add User input to Monkey object.
				monkeyObject.setBodyLength(userInput);

			} else {

				// User Output
				System.out.println("Invalid Input. Please input " + monkeyObject.getName() + "'s body length.");
			}
		}

		// Add Monkey object to Monkey List
		monkeyList.add(monkeyObject);

		// Return boolean value of false (monkey was created not found).
		return false;

	}

	// Method to Reserve animal
	public static void reserveAnimal(Object animalObject) {

		// If animal object is a dog object
		if (animalObject.toString().contains("Dog")) {

			// Get Dog Object back by casting first value in list as Dog object.
			Dog dogObject = (Dog) animalObject;

			// Check if Dog is already reserved
			if (dogObject.getReserved()) {

				// User Output
				System.out.println("Our apologies, but it seems " + dogObject.getName() + " is already reserved.");

			} else {

				// User Output
				System.out.println("You have reserved " + dogObject.getName() + ", have a wonderful day!");

				// Update dog list with new dog object at dog object's index
				for (Dog doggo : dogList) {

					// If dog within dog list matches the dog object's name.
					if (doggo.getName() == dogObject.getName()) {

						// Set dog to reserved; send user back to main menu.
						dogObject.setReserved(true);

						// Update list element with updated dog object values.
						dogList.set(dogObject.getID(), dogObject);

						// Return to main loop.
						return;

					}
				}
			}

			// If animal object is a monkey object
		} else if (animalObject.toString().contains("Monkey")) {

			// Get Monkey Object back by casting first value in list as Monkey object.
			Monkey monkeyObject = (Monkey) animalObject;

			// Check if Monkey is already reserved
			if (monkeyObject.getReserved()) {

				// User Output
				System.out.println("Our apologies, but it seems " + monkeyObject.getName() + " is already reserved.");

			} else {

				// User Output
				System.out.println("You have reserved " + monkeyObject.getName() + ", have a wonderful day!");

				// Update monkey list with new dog object at dog object's index
				for (Monkey monke : monkeyList) {

					// If monkey within monkey list matches the monkey object's name.
					if (monke.getName() == monkeyObject.getName()) {

						// Set monkey to reserved; send user back to main menu.
						monkeyObject.setReserved(true);

						// Update list item with new object.
						monkeyList.set(monkeyObject.getID(), monkeyObject);

						// Return to main loop.
						return;

					}
				}
			}
		}
	}

	// Print animals method
	public static void printAnimals(String animalType) {

		// Depending upon incoming string value, call to different prints.
		switch (animalType) {

		// Print all dogs
		case "Dog":

			// For each dog within the dog list, print associated values.
			for (Dog doggo : dogList) {
				System.out.println("\n[-- ID: " + doggo.getID() + " ------ Name: " + doggo.getName() + " --------]");
				System.out.println("Breed: " + doggo.getBreed());
				System.out.println("Gender: " + doggo.getGender());
				System.out.println("Age: " + doggo.getAge());
				System.out.println("Weight: " + doggo.getWeight() + " lbs");
				System.out.println("Aquisition Date: " + doggo.getAcquisitionDate());

				// If dog is reserved, show user friendly value of Yes instead of true.
				if (doggo.getReserved()) {
					System.out.println("Reserved: Yes");
					System.out.println("Aquisition Location: " + doggo.getAcquisitionLocation());
					System.out.println("Training Status: " + doggo.getTrainingStatus());

				}

				System.out.println("Service Location: " + doggo.getInServiceLocation());
				System.out.println("[------------------------------------]\n");
			}

			// Break out of loop.
			break;

		// Print all monkeys
		case "Monkey":

			// For each monkey within the monkey list, print associated values.
			for (Monkey monke : monkeyList) {
				System.out.println("\n[-- ID: " + monke.getID() + " ------ Name: " + monke.getName() + " --------]");
				System.out.println("Breed: " + monke.getSpecies());
				System.out.println("Gender: " + monke.getGender());
				System.out.println("Age: " + monke.getAge());
				System.out.println("Weight: " + monke.getWeight() + " lbs");
				System.out.println("Aquisition Date: " + monke.getAcquisitionDate());

				// If monkey is reserved, show user friendly value of Yes instead of true.
				if (monke.getReserved()) {
					System.out.println("Reserved: Yes");
					System.out.println("Aquisition Location: " + monke.getAcquisitionLocation());
					System.out.println("Training Status: " + monke.getTrainingStatus());

				}

				System.out.println("Service Location: " + monke.getInServiceLocation());
				System.out.println("Tail Length: " + monke.getTailLength() + " ft");
				System.out.println("Height: " + monke.getHeight() + " ft");
				System.out.println("Body Length: " + monke.getBodyLength() + " ft");
				System.out.println("[------------------------------------]\n");
			}

			// Break out of loop.
			break;

		case "All":
			System.out.println("\n[--------------- List of Unreserved Dogs ---------------]\n");

			for (Dog doggo : dogList) {
				if (!doggo.getReserved() && doggo.getTrainingStatus() == "In-Service") {
					System.out.println("\n[-- ID: " + doggo.getID() + " ------ Name: " + doggo.getName() + " --------]");
					System.out.println("Breed: " + doggo.getBreed());
					System.out.println("Gender: " + doggo.getGender());
					System.out.println("Age: " + doggo.getAge());
					System.out.println("Weight: " + doggo.getWeight() + " lbs");
					System.out.println("Aquisition Date: " + doggo.getAcquisitionDate());
					System.out.println("Service Location: " + doggo.getInServiceLocation());
					System.out.println("[------------------------------------]\n");
				}
			}

			System.out.println("\n[--------------- List of Unreserved Monkies ---------------]\n");

			for (Monkey monke : monkeyList) {
				if (!monke.getReserved() && monke.getTrainingStatus() == "In-Service") {
					System.out.println("\n[-- ID: " + monke.getID() + " ------ Name: " + monke.getName() + " --------]");
					System.out.println("Breed: " + monke.getSpecies());
					System.out.println("Gender: " + monke.getGender());
					System.out.println("Age: " + monke.getAge());
					System.out.println("Weight: " + monke.getWeight() + " lbs");
					System.out.println("Aquisition Date: " + monke.getAcquisitionDate());
					System.out.println("Service Location: " + monke.getInServiceLocation());
					System.out.println("Tail Length: " + monke.getTailLength() + " ft");
					System.out.println("Height: " + monke.getHeight() + " ft");
					System.out.println("Body Length: " + monke.getBodyLength() + " ft");
					System.out.println("[------------------------------------]\n");
				}
			}

			// Break out of loop.
			break;

		}
	}
}
