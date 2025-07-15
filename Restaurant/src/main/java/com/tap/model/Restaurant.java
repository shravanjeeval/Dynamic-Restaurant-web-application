package com.tap.model;

public class Restaurant {
	
    	 private int restaurantId;
	    private String name;
	    private String cuisineType;
	    private int deliveryTime;
	    private String address;
	    private int adminUserId;
	    private double rating;
	    private byte[] imageData;
	    
	    public Restaurant() {
			// TODO Auto-generated constructor stub
		}

		public Restaurant(int restaurantId, String name, String cuisineType, int deliveryTime, int adminUserId,
				double rating,byte[] imageData , String address) {
			super();
			this.restaurantId = restaurantId;
			this.name = name;
			this.address = address;
			this.cuisineType = cuisineType;
			this.deliveryTime = deliveryTime;
			this.adminUserId = adminUserId;
			this.rating = rating;
			
			this.imageData = imageData;
		}
	


		public int getRestaurantId() {
			return restaurantId;   
		}

		public void setRestaurantId(int restaurantId) {
			this.restaurantId = restaurantId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCuisineType() {
			return cuisineType;
		}

		public void setCuisineType(String cuisineType) {
			this.cuisineType = cuisineType;
		}

		public int getDeliveryTime() {
			return deliveryTime;
		}

		public void setDeliveryTime(int deliveryTime) {
			this.deliveryTime = deliveryTime;
		}

		public int getAdminUserId() {
			return adminUserId;
		}

		public void setAdminUserId(int adminUserId) {
			this.adminUserId = adminUserId;
		}

		public double getRating() {
			return rating;
		}

		public void setRating(double rating) {
			this.rating = rating;
		}

	

		public byte[] getImageData() {
			return imageData;
		}

		public void setImageData(byte[] imageData) {
			this.imageData = imageData;
		}

		

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		@Override
		public String toString() {
			return "Restaurant [restaurantId=" + restaurantId + ", name=" + name + ", cuisineType=" + cuisineType
					+ ", deliveryTime=" + deliveryTime + ", address=" + address + ", adminUserId=" + adminUserId
					+ ", rating=" + rating + ", imagePath=" + imageData + "]";
		}


		
	    
	    

}
