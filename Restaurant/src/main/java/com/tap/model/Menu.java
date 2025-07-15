package com.tap.model;

public class Menu {
	
	 private int menuId;
	    private int restaurantId;
	    private String itemName;
	    private String description;
	    private double price;
	    private boolean isAvailable;
	    private byte[] imagePath; // Store image as byte array (BLOB in DB)
	    
	    
	    public Menu() {
			// TODO Auto-generated constructor stub
		}


		public Menu(int menuId, int restaurantId, String itemName, String description, double price,
				boolean isAvailable, byte[] imagePath) {
			super();
			this.menuId = menuId;
			this.restaurantId = restaurantId;
			this.itemName = itemName;
			this.description = description;
			this.price = price;
			this.isAvailable = isAvailable;
			this.imagePath = imagePath;
		}


		public int getMenuId() {
			return menuId;
		}


		public void setMenuId(int menuId) {
			this.menuId = menuId;
		}


		public int getRestaurantId() {
			return restaurantId;
		}


		public void setRestaurantId(int restaurantId) {
			this.restaurantId = restaurantId;
		}


		public String getItemName() {
			return itemName;
		}


		public void setItemName(String itemName) {
			this.itemName = itemName;
		}


		public String getDescription() {
			return description;
		}


		public void setDescription(String description) {
			this.description = description;
		}


		public double getPrice() {
			return price;
		}


		public void setPrice(double price) {
			this.price = price;
		}


		public boolean isAvailable() {
			return isAvailable;
		}


		public void setAvailable(boolean isAvailable) {
			this.isAvailable = isAvailable;
		}


		public byte[] getImagePath() {
			return imagePath;
		}


		public void setImagePath(byte[] imagePath) {
			this.imagePath = imagePath;
		}


		@Override
		public String toString() {
			return "Menu [menuId=" + menuId + ", restaurantId=" + restaurantId + ", itemName=" + itemName
					+ ", description=" + description + ", price=" + price + ", isAvailable=" + isAvailable
					+ ", imagePath=" + imagePath + "]";
		}
	    
	    

}
