import java.util.Scanner;
import java.util.ArrayList; // required for using ArrayList

public class Main {

    // Declaring a static ArrayList to store strings
    static ArrayList<String> myArrList = new ArrayList<>();

    // Declaring a static Scanner to take input from the user
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        boolean quit = false; // Flag to control the loop

        // Loop until the user decides to quit
        while (!quit) {
            // Print the menu and get user input
            print(false); // Print the list before showing the menu
            String menuPick = SafeInput.getRegExString(in, "Menu: \n Add new item (A) \n Delete item (D) \n Print list (P) \n Quit (Q) \n", "[AaDdPpQq]").toUpperCase();

            // Switch statement to perform actions based on user input
            switch (menuPick) {
                case "A":
                    add(); // Add a new item
                    break;
                case "D":
                    print(true); // Print the list with item numbers before deleting
                    delete(); // Delete an item
                    break;
                case "P":
                    break; // Do nothing, continue to next iteration of the loop where the list will be printed at the beginning
                case "Q":
                    quit = quit(); // Quit the program
                    break;
                default:
                    System.out.print("Something went wrong"); // Error message for invalid input (should never happen)
            }
        }
    }

    /**
     * Method to add a new item to the list
     */
    private static void add() {
        myArrList.add(SafeInput.getNonZeroLenString(in, "Please enter an item "));
    }

    /** Method to delete an item from the list
     * First checks if the list is empty
     * Then prompts user for item number to delete and remove it from the list
     */
    private static void delete() {

        if (myArrList.isEmpty()) {
            System.out.println("Error, cannot delete an empty list");
        } else {
            myArrList.remove(SafeInput.getRangedInt(in, "Enter an item number to delete", 0, myArrList.size()) - 1);
        }
    }

    /** Method to print the list
     * Checks to see if the list should be printed with item numbers
     * @param numbered Checks to see if the list should be numbered
     */
    private static void print(Boolean numbered) {
        if (numbered) {
            System.out.println("List:");
            // Iterate over the list and print each item with its number
            for (int i = 0; i < myArrList.size(); i++) {
                System.out.println(" " + (1 + i) + ". " + myArrList.get(i));
            }
        } else {
            // Print the list without item numbers
            System.out.println("List:");
            for (String s : myArrList) {
                System.out.println(" " + s);
            }
        }
    }

    /** Method to confirm if the user wants to quit
     *
     * @return true or false based on SafeInput Y/N confim
     */
    private static boolean quit() {
        return SafeInput.getYNConfirm(in, "Are you sure you want to quit? (y/n)");
    }
}
