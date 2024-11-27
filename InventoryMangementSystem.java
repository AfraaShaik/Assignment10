import java.util.*;

public class InventoryMangementSystem {
    static class Item {
        int id;
        String name;
        String category;
        int quantity;

        public Item(int id, String name, String category, int quantity) {
            this.id = id;
            this.name = name;
            this.category = category;
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", category='" + category + '\'' +
                    ", quantity=" + quantity +
                    '}';
        }
    }
    
    
    private final Map<Integer, Item> inventoryMap = new HashMap<>();
    private final Map<String, PriorityQueue<Item>> categoryMap = new HashMap<>();
    private final int restockThreshold = 10;
    private int nextId = 1;

    // Add or update an item
    public void addOrUpdateItem(String name, String category, int quantity, Integer id) {
        if (id == null) {
            id = nextId++;
        } else {
            removeItem(id);
        }

        Item item = new Item(id, name, category, quantity);
        inventoryMap.put(id, item);

        categoryMap.putIfAbsent(category, new PriorityQueue<>((a, b) -> b.quantity - a.quantity));
        categoryMap.get(category).add(item);

        if (quantity < restockThreshold) {
            System.out.println("Restock Notification: " + item);
        }

        System.out.println("Item added/updated successfully.");
    }

    // Remove an item Completely
    public void removeItem(int id) {
        Item item = inventoryMap.remove(id);
        if (item != null) {
            categoryMap.get(item.category).remove(item);
            System.out.println("Item with ID " + id + " removed successfully.");
        } else {
            System.out.println("Item with ID " + id + " not found.");
        }
    }

    // Remove specified quantity from an item
    public void removeItemQuantity(int id, int quantityToRemove) {
        Item item = inventoryMap.get(id);

        if (item == null) {
            System.out.println("Item with ID " + id + " not found.");
            return;
        }

        if (quantityToRemove > item.quantity) {
            System.out.println("Error: Cannot remove more quantity than available.");
            return;
        }

        item.quantity -= quantityToRemove;

        if (item.quantity == 0) {
            removeItem(id);
            System.out.println("Item with ID " + id + " has been completely removed.");
        } else {
            System.out.println("Quantity updated successfully. Remaining quantity: " + item.quantity);
        }
    }

    // Display all items by category
    public void displayItemsByCategory(String category) {
        PriorityQueue<Item> items = categoryMap.get(category);
        if (items != null && !items.isEmpty()) {
            System.out.println("Items in category: " + category);
            items.stream().sorted((a, b) -> b.quantity - a.quantity).forEach(System.out::println);
        } else {
            System.out.println("No items found in category: " + category);
        }
    }


    // Display all items in inventory
    public void displayAllItems() {
        if (inventoryMap.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("All Items in Inventory:");
        for (Item item : inventoryMap.values()) {
            System.out.println(item);
        }
    }

    // Display top k items with the highest quantity
    public void displayTopKItems(int k) {
        if (inventoryMap.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        PriorityQueue<Item> pq = new PriorityQueue<>(Comparator.comparingInt(item -> item.quantity));
        for (Item item : inventoryMap.values()) {
            pq.offer(item);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        List<Item> topKItems = new ArrayList<>(pq);
        topKItems.sort((a, b) -> b.quantity - a.quantity);

        System.out.println("Top " + k + " items with the highest quantity:");
        for (Item item : topKItems) {
            System.out.println(item);
        }
    }

    public static void main(String[] args) {
        InventoryMangementSystem inventory = new InventoryMangementSystem();
        Scanner scanner = new Scanner(System.in);
        int command;

        System.out.println("\n**** Welcome to the Inventory Management System ****\n");
        System.out.println("Commands: \n1.Add Item\n2.Update Item\n3.Delete Item Completely\n4.Delete Some quantity of an Item\n5.Display All Items\n6.Display Items Of Particular Category\n7.Topk Items\n8.Exit");

        while (true) {
            System.out.print("\nPlease Choose a Command: ");
            command = scanner.nextInt();
            scanner.nextLine();

            switch (command) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantity = Integer.parseInt(scanner.nextLine());
                    inventory.addOrUpdateItem(name, category, quantity, null);
                    break;

                case 2:
                    System.out.print("Enter ID to update: ");
                    int updateId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new category: ");
                    String newCategory = scanner.nextLine();
                    System.out.print("Enter new quantity: ");
                    int newQuantity = Integer.parseInt(scanner.nextLine());
                    inventory.addOrUpdateItem(newName, newCategory, newQuantity, updateId);
                    break;

                case 3:
                    System.out.print("Enter ID to delete: ");
                    int deleteId = Integer.parseInt(scanner.nextLine());
                    inventory.removeItem(deleteId);
                    break;
                case 4:
                    System.out.print("Enter ID of the item to remove quantity from: ");
                    int deleteItemId = scanner.nextInt();
                    System.out.print("Enter quantity to remove: ");
                    int quantityToRemove = scanner.nextInt();
                    scanner.nextLine(); 
                    inventory.removeItemQuantity(deleteItemId, quantityToRemove);
                    break;

                case 5:
                    inventory.displayAllItems();
                    break;

                case 6:
                    System.out.print("Enter category: ");
                    String categoryName = scanner.nextLine();
                    inventory.displayItemsByCategory(categoryName);
                    break;

                case 7:
                    System.out.print("Enter the value of k: ");
                    int k = Integer.parseInt(scanner.nextLine());
                    inventory.displayTopKItems(k);
                    break;

                case 8:
                    System.out.println("Exiting Inventory Management System.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid command. Try again.");
                    break;
            }
        }
    }
}
