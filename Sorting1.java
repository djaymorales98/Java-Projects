import java.io.*;
import java.util.*;

/*
This is sample code to showcase my ability to use:
    OOP, File I/O, String manipulation/parsing, collections & generics,
    sorting, user interaction, and error handling.
 */


class Car {
    String make;
    String model;
    int year;
    String vin;

    //Constructor to create a Car object
    public Car(String make, String model, int year, String vin) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.vin = vin;
    }

    //Convert the car to a formatted string for display
    @Override
    public String toString() {
        return String.format("%-15s%-25s%-5d%-18s", make, model, year, vin);
    }

    //Comparator for sorting cars first by year and then by VIN
    public static Comparator<Car> comparator = new Comparator<Car>() {
        @Override
        public int compare(Car car1, Car car2) {
            if (car1.year != car2.year) {
                return Integer.compare(car1.year, car2.year);  //Compare by year
            }
            return car1.vin.compareTo(car2.vin);  //If years are the same, compare by VIN
        }
    };
}

public class Sorting1 {
    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();

        //Read car data from the external file car-list.txt
        try {
            File file = new File("car-list.txt");
            Scanner scanner = new Scanner(file);

            //Read each line from the file and split into make, model, year, and VIN
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;  // Skip empty lines

                //Split by spaces, but only get first four parts
                String[] parts = line.split(" ");

                //We need at least 4 parts: make, model, year, and VIN
                if (parts.length >= 4) {
                    String make = parts[0];
                    String model = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length - 2));  //Combine the model part
                    int year = 0;
                    try {
                        year = Integer.parseInt(parts[parts.length - 2]);  //Year is the second to last part
                    } catch (NumberFormatException e) {
                        continue;  //Skip line if year is invalid
                    }
                    String vin = parts[parts.length - 1];  //VIN is always the last part
                    cars.add(new Car(make, model, year, vin));  //Add the car object to the list
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
            return;
        }

        //Ask user for car make
        Scanner input = new Scanner(System.in);
        System.out.print("What car make are you looking for?\n");
        String searchMake = input.nextLine().trim();  // Get the car make from user

        //Debugging
        System.out.println("User input (after trimming): " + searchMake);

        //Filter list by make
        List<Car> filteredCars = new ArrayList<>();
        for (Car car : cars) {
            if (car.make.equalsIgnoreCase(searchMake)) {
                filteredCars.add(car);  // Add matching cars to filtered list
            }
        }

        //Notify user if car is not found
        if (filteredCars.isEmpty()) {
            System.out.println("No cars found for " + searchMake + ".");
        } else {
            //Sort filtered cars
            Collections.sort(filteredCars, Car.comparator);

            //Get the oldest and newest cars
            Car oldestCar = filteredCars.get(0);
            Car newestCar = filteredCars.get(filteredCars.size() - 1);

            //Display oldest car
            System.out.println("Oldest " + searchMake);
            System.out.println(oldestCar);

            //Display newest car
            System.out.println("Newest " + searchMake);
            System.out.println(newestCar);
        }

        input.close();  //Close Scanner
    }
}