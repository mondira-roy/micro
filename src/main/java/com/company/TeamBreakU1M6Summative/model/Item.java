package com.company.TeamBreakU1M6Summative.model;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Objects;

public class Item {

    private int itemId;
    @NotEmpty(message = "You must enter a value for name.")
    private String name;

    private String description;
    @NotEmpty(message = "You must enter a value for dailyRate.")
    private double dailyRate;



    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return getItemId() == item.getItemId() &&
                getDailyRate() == item.getDailyRate() &&
                getName().equals(item.getName()) &&
                Objects.equals(getDescription(), item.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemId(), getName(), getDescription(), getDailyRate());
    }
}
