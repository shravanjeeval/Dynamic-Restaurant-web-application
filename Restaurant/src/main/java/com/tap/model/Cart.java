package com.tap.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private Map<Integer ,CartItem> items;

   public Cart() {
	this.items  = new HashMap<>();
}
   

    public void addItem(CartItem item) {
    	 int itemId = item.getItemId();
    	    if (items.containsKey(itemId)) {
    	        // If item already exists, increase the quantity
    	        CartItem existingItem = items.get(itemId);
    	        existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
    	    } else {
    	        // If item is new, add to cart
    	        items.put(itemId, item);
    	    }
       
    }

    public void updateItem(int itemId, int quantity) {
        if (items.containsKey(itemId)) {
            if (quantity <= 0) {
                items.remove(itemId);
            } else {
                items.get(itemId).setQuantity(quantity);
            }
        }
    }
 // Remove an item from the cart
    public void removeItem(int itemId) {
    	  items.remove(itemId);
    }
    
    public Collection<CartItem> getItems() {
        return items.values();  // returns List-like collection of CartItem
    }

    public int getTotal() {
        int total = 0;
        for (CartItem item : items.values()) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }
    public int getTotalCost() {
        int total = 0;
        for (CartItem item : items.values()) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }
    @Override
    public String toString() {
        return "Cart{" + "items=" + items + '}';
    }
}
