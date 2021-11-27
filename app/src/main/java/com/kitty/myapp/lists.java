package com.kitty.myapp;

public class lists {
        // Variable to store data corresponding
        // to firstname keyword in database
        private String title;

        // Variable to store data corresponding
        // to lastname keyword in database
        private String type;

        // Variable to store data corresponding
        // to age keyword in database
        private String quantity;

        // Mandatory empty constructor
        // for use of FirebaseUI
        public lists() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
