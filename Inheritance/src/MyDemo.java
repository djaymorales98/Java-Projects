import java.util.Scanner;

public class MyDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("New number creation tool");
        System.out.println("Enter name");
        String name = scanner.nextLine();

        System.out.println("Enter phone number");
        long phoneNumber = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Enter email");
        String email = scanner.nextLine();

        SmartPhone userPhone = new SmartPhone(name, phoneNumber, email);

        System.out.print(userPhone);
    }
}