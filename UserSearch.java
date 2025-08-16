import java.io.*;
import java.util.*;

/*Code from an exam in my "Fundamentals of Programming 2" course in Spring 2025.
 This code allows users to search for a vehicle by make, model, year, and mileage.
 */

public class UserSearch {

    static class Car {
        String make;
        String model;
        int year;
        String vin;
        int mileage;

        public Car(String make, String model, int year, String vin, int mileage) {
            this.make = make;
            this.model = model;
            this.year = year;
            this.vin = vin;
            this.mileage = mileage;
        }

        public int mileageDifference(int targetMileage) {
            return Math.abs(this.mileage - targetMileage);
        }

        @Override
        public String toString() {
            return String.format("%s %s %d\nVIN: %s\nMileage: %d", make, model, year, vin, mileage);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter target make: ");
        String targetMake = input.nextLine().trim().toLowerCase();

        System.out.print("Enter target model: ");
        String targetModel = input.nextLine().trim().toLowerCase();

        System.out.print("Enter target mileage: ");
        int targetMileage = Integer.parseInt(input.nextLine().trim());

        List<Car> carList = readCarList("car-list-with-mileage.txt");

        Car bestMatch = null;

        for (Car car : carList) {
            if (car.make.equalsIgnoreCase(targetMake) && car.model.equalsIgnoreCase(targetModel)) {
                if (bestMatch == null ||
                        car.mileageDifference(targetMileage) < bestMatch.mileageDifference(targetMileage) ||
                        (car.mileageDifference(targetMileage) == bestMatch.mileageDifference(targetMileage)
                                && car.year > bestMatch.year)) {
                    bestMatch = car;
                }
            }
        }

        if (bestMatch != null) {
            System.out.println("Best match:");
            System.out.println(bestMatch);
        } else {
            System.out.println("No matching car found.");
        }
    }

    private static List<Car> readCarList(String filename) {
        List<Car> cars = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String make = parts[0].trim();
                    String model = parts[1].trim();
                    int year = Integer.parseInt(parts[2].trim());
                    String vin = parts[3].trim();
                    int mileage = Integer.parseInt(parts[4].trim());

                    cars.add(new Car(make, model, year, vin, mileage));
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading car list: " + e.getMessage());
        }

        return cars;
    }
}