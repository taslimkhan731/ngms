package com.stl.ngms.admin.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class FoodItem {
    private String itemId;
    @NotNull(message = "Item Title cannot be null.")
    private String itemTitle;
    @NotNull(message = "Unit Price cannot be null.")
    @Pattern(regexp = "^[0-9]+(\\.[0-9]+)?$", message = "Unit price must be a positive number.")
    private String unitPrice;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "FootItem{" +
                "itemId='" + itemId + '\'' +
                ", itemTitle='" + itemTitle + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                '}';
    }
}
