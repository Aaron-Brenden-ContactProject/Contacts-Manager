import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class ContactsManager {
    private static final Path file = Paths.get("data", "contacts.txt");

    public static void showContacts() throws IOException {
        List<String> contactList = Files.readAllLines(file);
        if(contactList.isEmpty()){
            System.out.println("No contacts found.");
        } else {
            for (int i = 0; i < contactList.size(); i += 1) {
                System.out.println((i + 1) + ": " + contactList.get(i));
            }
        }
        System.out.println();
    }

    public static void addContact() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a name:");
        String name = scanner.nextLine();
        System.out.println("Enter a contact number:");
        String phone = scanner.nextLine();
        String contact = name + " | " + phone;
        Files.write(
                Paths.get("data", "contacts.txt"),
                Arrays.asList(contact), // list with one item
                StandardOpenOption.APPEND
        );
        showContacts();
    }

    public static void removeContact() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a name or contact number:");
        String nameOrNumber = scanner.nextLine();
        List<String> lines = Files.readAllLines(file);
        List<String> newList = new ArrayList<>();
        for (String line : lines) {
            if (line.contains(nameOrNumber)) {
                newList.remove(line);
                continue;
            }
            newList.add(line);
        }
        Files.write(Paths.get("data", "contacts.txt"), newList);
        showContacts();
    }

    public static void oneContact() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a name or contact number to search:");
        String nameOrNumber = scanner.nextLine();
        List<String> lines = Files.readAllLines(file);
        for(String line: lines){
            if(line.contains(nameOrNumber)){
                System.out.println(line);
            }
        }
    }


    public static void main(String[] args) throws IOException {

        showContacts();
//        addContact();
//        removeContact();
        oneContact();
    }
}
