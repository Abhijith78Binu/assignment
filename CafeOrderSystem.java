import java.util.Scanner; 
// Class representing a menu item with an ID, name, and price.
class MenuItem {  
    int id; 
    String name; 
    double price; 

    // Constructor to initialize a new MenuItem object with given id, name, and price.
    
    public MenuItem(int id, String name, double price) {
        this.id = id;
        this.name = name; 
        this.price = price; 
    }
}
// Class representing an order item, which is a specific menu item along with its quantity.
class OrderItem { 
    int orderNumber; // Identifier for the order this item belongs to
    int tableNumber; // The table number where the order was placed
    MenuItem item;// The menu item that was ordered
    int quantity; // The quantity of the menu item ordered

    // Constructor to initialize a new OrderItem with given order number, table number, menu item, and quantity.
    public OrderItem(int orderNumber, int tableNumber, MenuItem item, int quantity) {
        this.orderNumber = orderNumber; 
        this.tableNumber = tableNumber; 
        this.item = item; 
        this.quantity = quantity;
    }
}

// Main class for the café order system.

public class CafeOrderSystem {
    private static MenuItem[] menuItems = {
        new MenuItem(11, "Sweet corn", 1.99),
        new MenuItem(12, "Sweet corn w/ chicken", 2.35),
        new MenuItem(13, "Cream of Tomato/Mushroom", 2.10),
        new MenuItem(14, "Veg soup", 2.25),
        //Baked Potato w/ filling
        new MenuItem(21, "Baked Potato w/ Baked Beans", 3.20),
        new MenuItem(22, "Baked Potato w/ Cheese", 2.50),
        new MenuItem(23, "Baked Potato w/ Tuna", 3.75),
        new MenuItem(24, "Baked Potato w/ Coleslaw", 2.75),
        //Toasties
        new MenuItem(31, "Toastie Cheese", 2.25),
        new MenuItem(32, "Toastie Ham & Cheese", 3.20),
        new MenuItem(33, "Toastie Bacon Egg Cheese", 3.75),
        new MenuItem(34, "Toastie Sausage Egg Cheese", 3.55),
        //Baguette
        new MenuItem(41, "Baguette Ham&Brie", 3.75),
        new MenuItem(42, "Baguette Grilled Chicken Mayo", 3.25),
        new MenuItem(43, "Baguette Prawn salad", 3.95),
        new MenuItem(45, "Baguette Egg May", 2.95),
        new MenuItem(46, "Baguette Tuna sweetcorn", 3.29),
        //Burgers
        new MenuItem(47, "Burger Chicken", 5.65),
        new MenuItem(48, "Burger Hamburger", 5.75),
        new MenuItem(49, "Burger Veggie", 4.95),
        //Main Courses
        new MenuItem(51, "Steak & Ale Pie", 6.95),
        new MenuItem(52, "Fish & chips", 7.95),
        new MenuItem(53, "Breaded Scampi w/ fries", 6.95),
        new MenuItem(54, "Chicken pasta", 6.79),
        new MenuItem(55, "Lasagne", 6.95),
        new MenuItem(56, "Spaghetti Bolognese", 5.99),
        new MenuItem(57, "Hotdog w/ fries", 3.50),
        //Kids Menu
        new MenuItem(60, "Kids Menu Chicken nuggets", 2.55),
        new MenuItem(61, "Kids Menu Macaroni Cheese", 2.20),
        new MenuItem(62, "Kids Menu Pigs in a blanket", 2.95),
        //Sides
        new MenuItem(71, "Side French fries (S)", 2.50),
        new MenuItem(71, "Side French fries (L)", 3.50),
        new MenuItem(72, "Side Onion rings (S)", 2.99),
        new MenuItem(72, "Side Onion rings (L)", 3.99),
        new MenuItem(73, "Side Garlic bread", 2.99),
        new MenuItem(74, "Side Nachos cheese/guac/salsa", 2.25),
        //Desserts
        new MenuItem(81, "Dessert Chocolate cookie", 2.10),
        new MenuItem(81, "Dessert Brownie", 2.55),
        new MenuItem(82, "Dessert Cheesecake (Vanilla)", 3.99),
        new MenuItem(82, "Dessert Cheesecake (Lemon)", 3.99),
        new MenuItem(83, "Dessert Pie (Apple)", 2.25),
        new MenuItem(83, "Dessert Pie (Cherry)", 2.25),
        new MenuItem(84, "Dessert Pancakes (2)", 2.99),
        //Drinks
        new MenuItem(91, "Drink Tea cup", 2.10),
        new MenuItem(91, "Drink Tea pot", 2.99),
        new MenuItem(92, "Drink Coffee", 2.50),
        new MenuItem(93, "Drink Cappuccino", 2.75),
        new MenuItem(94, "Drink Hot Chocolate", 2.95),
        new MenuItem(95, "Drink Pop Fanta/Pepsi/Cola/Lemonade", 2.35),
        new MenuItem(96, "Drink Sparkling Water", 1.75),

       // additional entries
        new MenuItem(101, "Iced Latte", 3.50),
        new MenuItem(102, "Espresso", 2.10),
        new MenuItem(103, "Turkey & Swiss Sandwich", 4.50),
        new MenuItem(104, "Club Sandwich", 4.75),
        new MenuItem(105, "Chicken Caesar Wrap", 4.95),
        new MenuItem(106, "Veggie Hummus Wrap", 4.50),
        new MenuItem(107, "Croissant", 2.50),
        new MenuItem(108, "Cinnamon Roll", 2.75),
    };

      // Array to hold the orders placed.
    private static OrderItem[] orders = new OrderItem[100];
    private static int orderCount = 0; // Counter for the number of orders placed
    private static int nextOrderNumber = 1;// Counter for the next order number to be assigned

    // Array mapping table numbers to staff names.
    private static String[] staffByTable = {
        "Kiran", // Staff for Table 0 (Takeaway)
        "sam",   // Staff for Table 1
        "Jill",  // Staff for Table 2
        "Jill",  // Staff for Table 3
        "Sam",   // Staff for Table 4
        "Jill",  // Staff for Table 5
        "Sam",   // Staff for Table 6
        "Sam",   // Staff for Table 7
        "Jill"   // Staff for Table 8
    };
    private static boolean[] tableStatus = new boolean[9]; // Array to track table status (0-8)

   // Main method - entry point of the program.
    public static void main(String[] args) { // The main method, which is the entry point of the program. It accepts an array of strings as arguments from the command line.

        Scanner scanner = new Scanner(System.in); // read user input
    
        boolean running = true; // to control the main menu loop
    
        // Main menu loop
        while (running) { // Starts a while loop that continues as long as 'running' is true. This loop represents the main menu of the program.
        // Display the main menu options
            System.out.println("Main Menu:"); // Prints the title of the main menu to the console.
            System.out.println("1. Place Order"); // Prints the option to place an order.
            System.out.println("2. Display Invoice"); // Prints the option to display an invoice.
            System.out.println("3. Exit"); // Prints the option to exit the program.
            System.out.print("Choose an option: "); // Prompts the user to choose an option from the main menu.
    
            int choice = scanner.nextInt(); // Reads the user's choice 
    // Handle the user's menu choice
            switch (choice) { 
                case 1: 
                    placeOrder(scanner); 
                    break; 
                case 2: 
                    displayInvoice(scanner); 
                    break; 
                case 3: 
                    running = false; 
                    System.out.println("Exiting program.");
                    break; 
                default: 
                    System.out.println("Invalid choice, please try again.");
            }
        }
    
        scanner.close(); // Close the scanner when the program ends
    }
    

  
 // Method to place a new order
private static void placeOrder(Scanner scanner) {
    System.out.println("Enter table number (1-8 for Dine In, 0 for Take Away): ");
    int tableNumber = scanner.nextInt();


    if (tableNumber < 0 || tableNumber > 8) { // Validate the table number. It must be between 0 and 8, inclusive.
        System.out.println("Invalid table number.");
        return;  //Exit when invalid
    }
    
    if (!tableStatus[tableNumber]) {
        // Table is free
        System.out.println("Table " + tableNumber + " is free. You can place an order.");
    } else {
        // Table is occupied
        System.out.println("Table " + tableNumber + " is already occupied. Please choose another table.");
        return;
    }

    boolean addingItems = true; //control the loop for adding items to the order
     
    while (addingItems && orderCount < orders.length) { // Loop to add items to the order until the user decides to finish or the order array is full
        System.out.println("Enter item ID and quantity (enter 0 for item ID to finish): ");
        int itemId = scanner.nextInt();
        
        // Check if the user wants to finish adding items to the order
        if (itemId == 0) {
            addingItems = false;
            break;
        }
        int quantity = scanner.nextInt();

        MenuItem item = findMenuItemById(itemId);
        if (item != null && quantity > 0) {
            orders[orderCount++] = new OrderItem(nextOrderNumber, tableNumber, item, quantity);
            System.out.println("Item added to order.");
        } else {
            System.out.println("Invalid item ID or quantity.");
        }
    }

    if (addingItems) {
        System.out.println("Order limit reached. No more items can be added.");
    } else {
        System.out.println("Order placed successfully. Order number is " + nextOrderNumber);
        nextOrderNumber++;
    }
}

  // Method to find a menu item by its ID
private static MenuItem findMenuItemById(int id) {
    for (MenuItem menuItem : menuItems) {
         
        if (menuItem.id == id) {  // Check if the current menu item's ID matches the ID we're looking for
            return menuItem;
        }
    }
    return null;
}
// Method to display an invoice for a given order
private static void displayInvoice(Scanner scanner) {
    System.out.println("Enter order number for the invoice: ");
    int orderNumber = scanner.nextInt();

    Invoice invoice = null;

    for (OrderItem orderItem : orders) { // Iterate through all the orders to find and compile the items for the given order number

        if (orderItem != null && orderItem.orderNumber == orderNumber) {   // Check if the current order item is not null and matches the order number provided
            if (invoice == null) { // If this is the first item found for the order, initialize the Invoice with details
                int tableNumber = orderItem.tableNumber; // Get the table number from the order item
                String staffName = staffByTable[tableNumber]; // Get the staff name for the table number
                invoice = new Invoice(orderNumber, tableNumber, staffName, 20);// Create a new Invoice object with the order number, table number, staff name, and max item count
            }
           
            invoice.addItem(orderItem); // Add the current order item to the Invoice
        }
    }

    if (invoice != null && invoice.itemCount > 0) {
        invoice.finalizeInvoice(); // Finalize the invoice calculations (totals, VAT, etc.)
        invoice.display(); // Display the finalized invoice details
    } else {
       
        System.out.println("No items found for this order number."); // If no items were found for the given order number, inform the user
    }
}


// Nested static class representing an invoice for an order.
static class Invoice {
    int orderNumber;
    int tableNumber;
    String staffName;
    OrderItem[] items;
    int itemCount;
    double total;
    double vat;
    double netAmount;

    // Constructor to initialize an Invoice object
    public Invoice(int orderNumber, int tableNumber, String staffName, int maxItems) {
        this.orderNumber = orderNumber; // Set the order number
        this.tableNumber = tableNumber; // Set the table number
        this.staffName = staffName; // Set the staff name
        this.items = new OrderItem[maxItems]; // Initialize the items array with the maximum number of items allowed
        this.itemCount = 0; // Set the initial item count to 0
    }

// Method to add an order item to the invoice
public void addItem(OrderItem item) {
   
    if (itemCount < items.length) { // Ensure there is space in the invoice for additional items
        items[itemCount++] = item; // Add the item and increment the item count
        total += item.item.price * item.quantity; // Update the running total with the item's cost
    } else {
        
        System.out.println("Invoice item limit reached. Cannot add more items.");// If there is no space left, inform the user that the invoice item limit has been reached
    }
}
 // Method to finalize the invoice calculations
    public void finalizeInvoice() {
        vat = total * 0.2; // Calculate the VAT based on a fixed rate (20% in this case)
        netAmount = total + vat; // Calculate the net amount to be paid
    }
// Method to display the invoice details
    public void display() {
        System.out.println("Invoice for Order Number: " + orderNumber);
        System.out.println("Table Number: " + tableNumber);
        System.out.println("Staff: " + staffName);
       
        for (int i = 0; i < itemCount; i++) { // Loop through the items and display each one with its quantity and cost
            OrderItem orderItem = items[i];
            System.out.println(orderItem.item.name + " x " + orderItem.quantity + " = " + (orderItem.item.price * orderItem.quantity));
        }
          // Display the total cost, VAT, and net amount
        System.out.println("Total: " + total);
        System.out.println("VAT: " + vat);
        System.out.println("Net Amount: " + netAmount);
    }
}
}

//End of the program.

