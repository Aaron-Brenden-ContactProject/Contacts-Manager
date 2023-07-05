import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import util.Input;


public class ContactsManager {

//    list from the contact.txt file

    private static final Path file = Paths.get("data", "contacts.txt");
    private static final Input input = new Input();

//    method for showing the contacts form the file

    public static void showContacts() throws IOException {
        List<String> contactList = Files.readAllLines(file);
        if (contactList.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            System.out.println("Name | Phone Number");
            System.out.println("-------------------");
            for (int i = 0; i < contactList.size(); i += 1) {
                System.out.println((i + 1) + ": " + contactList.get(i));
            }
        }
        System.out.println();
    }

// method for adding a contact to the list

    public static void addContact() throws IOException {
        System.out.println("Enter a name:");
        String name = input.getString();
        while(name.equals("")){
            System.out.println("Enter a name:");
            name = input.getString();
        }
        System.out.println("Enter a contact number:");
        String phone = input.getString();
        boolean test = true;
        boolean length = false;
        if(phone.length() == 7){
            test = false;
            length = true;
        }
        if(!length) {
            if(phone.length() == 10){
                test = false;
                length = true;
            }
        }
        while(test){
            System.out.println("Please Enter a Proper phone number!");
            phone = input.getString();
        }
        Contact contact = new Contact(name, phone);
        String contacts = contact.getName() + " | " + contact.getPhone();
        Files.write(
                Paths.get("data", "contacts.txt"),
                Arrays.asList(contacts), // list with one item
                StandardOpenOption.APPEND
        );
        showContacts();
    }

// method to remove contact from the list and rewrite the list file

    public static void removeContact() throws IOException {
        System.out.println("Enter a name or contact number:");
        String nameOrNumber = input.getString().toLowerCase();
        while(nameOrNumber.equals("")){
            System.out.println("Enter a name or contact number:");
            nameOrNumber = input.getString().toLowerCase();
        }
        List<String> lines = Files.readAllLines(file);
        List<String> newList = new ArrayList<>();
        for (String line : lines) {
            if (line.toLowerCase().contains(nameOrNumber)) {
                newList.remove(line);
                continue;
            }
            newList.add(line);
        }
        Files.write(Paths.get("data", "contacts.txt"), newList);
        showContacts();
    }

// method to show the searched contact in the list

    public static void oneContact() throws IOException {
        System.out.println("Enter a name or contact number to search:");
        String nameOrNumber = input.getString().toLowerCase();
        while(nameOrNumber.equals("")){
            System.out.println("Enter a name or contact number to search:");
            nameOrNumber = input.getString().toLowerCase();
        }
        List<String> lines = Files.readAllLines(file);
        for (String line : lines) {
            if (line.toLowerCase().contains(nameOrNumber)) {
                System.out.println(line);
                System.out.println();
            }
        }
    }

// the method that runs the app

    public static void contactsManagerApp() throws IOException {
        boolean exit = true;
        while (exit) {
            System.out.println("""
                    Main Menu:\s
                    1. View Contact List\s
                    2. Add New Contact\s
                    3. Search Contact\s
                    4. Delete a Contact\s
                    5. Exit\s
                    """);
            System.out.println("Enter an Option 1-5: ");
            int userInput = input.getInt();
            switch (userInput) {
                case 1 -> showContacts();
                case 2 -> addContact();
                case 3 -> oneContact();
                case 4 -> removeContact();
                case 5 -> {
                    System.out.println("Exiting...");
                    exit = false;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("""
                               
                ░██╗░░░░░░░██╗███████╗██╗░░░░░░█████╗░░█████╗░███╗░░░███╗███████╗
                ░██║░░██╗░░██║██╔════╝██║░░░░░██╔══██╗██╔══██╗████╗░████║██╔════╝
                ░╚██╗████╗██╔╝█████╗░░██║░░░░░██║░░╚═╝██║░░██║██╔████╔██║█████╗░░
                ░░████╔═████║░██╔══╝░░██║░░░░░██║░░██╗██║░░██║██║╚██╔╝██║██╔══╝░░
                ░░╚██╔╝░╚██╔╝░███████╗███████╗╚█████╔╝╚█████╔╝██║░╚═╝░██║███████╗
                ░░░╚═╝░░░╚═╝░░╚══════╝╚══════╝░╚════╝░░╚════╝░╚═╝░░░░░╚═╝╚══════╝
                """);
        contactsManagerApp();
    }
}
