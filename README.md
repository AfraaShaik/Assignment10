# Inventory Management System

## Introduction

The **Inventory Management System** is a simple Java-based application that allows users to manage an inventory of items. The system can add, update, and delete items, display inventory items by category, and display the top items with the highest quantity. It also notifies the user if the quantity of an item falls below a predefined restock threshold.

---

## Features

1. **Add or Update Items:**  
   Add a new item to the inventory or update an existing item by its ID.
   
2. **Delete Items:**  
   Remove an item completely from the inventory or reduce its quantity.

3. **Display Items:**  
   - Display all items in the inventory.
   - Display items filtered by category.
   - Display the top `k` items with the highest quantity.

4. **Restock Notification:**  
   If the quantity of any item falls below a predefined threshold, a restock notification is displayed.

---

## Program Menu

The program provides the following commands:

| Command | Description                                             |
|---------|---------------------------------------------------------|
| 1       | Add a new item to the inventory                          |
| 2       | Update an existing item by its ID                        |
| 3       | Delete an item completely by its ID                      |
| 4       | Remove a specific quantity from an item                  |
| 5       | Display all items in the inventory                       |
| 6       | Display items in a particular category                   |
| 7       | Display the top `k` items with the highest quantity      |
| 8       | Exit the Inventory Management System                     |

---
### 1. Add a New Item
- Select **1** from the main menu.
- Enter the item's name, category, and quantity.
- The system will display a success message and notify if restocking is needed.

### 2. Update an Existing Item
- Select **2** from the main menu.
- Enter the ID of the item to update.
- Provide the new name, category, and quantity.
- The system will update the item and display a success message.

### 3. Delete an Item
- Select **3** from the main menu.
- Enter the ID of the item to delete.
- The system will remove the item and display a success message.

### 4. Remove Quantity from an Item
- Select **4** from the main menu.
- Enter the ID of the item and the quantity to remove.
- If the quantity becomes zero, the item will be deleted.

### 5. Display All Items
- Select **5** to display all items in the inventory.

### 6. Display Items by Category
- Select **6** from the main menu.
- Enter the category name to display all items in that category.

### 7. Display Top `k` Items
- Select **7** from the main menu.
- Enter the value of `k` to display the top `k` items with the highest quantity.

### 8. Exit
- Select **8** to exit the system.
  
### Sample Output

![image](https://github.com/user-attachments/assets/ca009530-b25c-4c3a-bdf6-f54c128dabe9)

