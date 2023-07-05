package util;
import java.util.Scanner;

public class Input {
    private Scanner scanner;
    public Input (){
        this.scanner = new Scanner(System.in);
    }
    public String getString(){
        return this.scanner.nextLine();
    }
    public Integer getInt(){
        while (true) {
            try {
                String userInput = getString();
                return Integer.valueOf(userInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

}

