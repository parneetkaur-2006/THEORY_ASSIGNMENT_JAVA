import java.io.*;
import java.util.*;

class Contact {
    private String name;
    private String phoneNumber;
    private String email;

    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber + ", Email: " + email;
    }

    public String toFileString() {
        return name + "," + phoneNumber + "," + email;
    }

    public static Contact fromFileString(String data) {
        String[] parts = data.split(",");
        return new Contact(parts[0], parts[1], parts[2]);
    }
}

class ContactManager {
    private HashMap<String, Contact> contacts = new HashMap<>();

    public void addContact(Contact contact) {
        contacts.put(contact.getName(), contact);
        System.out.println("Contact added successfully.");
    }

    public void removeContact(String name) {
        if (contacts.remove(name) != null)
            System.out.println("Contact removed successfully.");
        else
            System.out.println("Contact not found.");
    }

    public void searchContact(String name) {
        Contact contact = contacts.get(name);
        if (contact != null)
            System.out.println(contact);
        else
            System.out.println("Contact not found.");
    }

    public void displayAllContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts available.");
            return;
        }
        System.out.println("------ All Contacts ------");
        for (Contact contact : contacts.values()) {
            System.out.println(contact);
        }
    }

    public void saveContactsToFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Contact contact : contacts.values()) {
                bw.write(contact.toFileString());
                bw.newLine();
            }
            System.out.println("Contacts saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving contacts: " + e.getMessage());
        }
    }

    public void loadContactsFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            contacts.clear();
            String line;
            while ((line = br.readLine()) != null) {
                contacts.put(Contact.fromFileString(line).getName(), Contact.fromFileString(line));
            }
            System.out.println("Contacts loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("Error loading contacts: " + e.getMessage());
        }
    }
}

public class ContactListApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ContactManager manager = new ContactManager();
        int choice;

        System.out.println("Welcome to the Contact List Application!");

        do {
            System.out.println("\n1. Add Contact");
            System.out.println("2. Remove Contact");
            System.out.println("3. Search Contact");
            System.out.println("4. Display All Contacts");
            System.out.println("5. Save to File");
            System.out.println("6. Load from File");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter contact name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter phone number: ");
                    String phone = sc.nextLine();
                    System.out.print("Enter email: ");
                    String email = sc.nextLine();
                    manager.addContact(new Contact(name, phone, email));
                    break;

                case 2:
                    System.out.print("Enter the name to remove: ");
                    manager.removeContact(sc.nextLine());
                    break;

                case 3:
                    System.out.print("Enter the name to search: ");
                    manager.searchContact(sc.nextLine());
                    break;

                case 4:
                    manager.displayAllContacts();
                    break;

                case 5:
                    manager.saveContactsToFile("contacts.txt");
                    break;

                case 6:
                    manager.loadContactsFromFile("contacts.txt");
                    break;

                case 7:
                    System.out.println("Exiting Application... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 7);

        sc.close();
    }
}
