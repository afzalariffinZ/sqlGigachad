import java.util.Scanner;

public class Prompt {
    Scanner scan = new Scanner(System.in);
    public void promptRegister(String[] tempData) {
        // prompting user to enter email username and password

        System.out.println("Insert your email:");
        tempData[0] = scan.nextLine();
        System.out.println("Insert your username:");
        tempData[1] = scan.nextLine();
        System.out.println("Insert your password:");
        tempData[2] = scan.nextLine();
    }

    public void promptLogin(String[] tempData) {
        // prompting user to enter email username and password

        System.out.println("Insert your email:");
        tempData[0] = scan.nextLine();
        System.out.println("Insert your username:");
        tempData[1] = scan.nextLine();
        System.out.println("Insert your password:");
        tempData[2] = scan.nextLine();
    }
}
