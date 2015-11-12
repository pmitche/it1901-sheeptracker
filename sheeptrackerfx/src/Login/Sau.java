/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.util.ArrayList;

public class Sau {

    //Felt for sau
    private int ID, age, farmerID;
    private double weight, health;
    private String name, farmerName;
    private boolean attack;
    private ArrayList healthLog;
    private java.sql.Date bYear;
    private double latitude, longitude;

    //Konstruktør for sau
    public Sau(int ID) {
        this.ID = ID;
        this.age = 0;
        this.weight = 0.0;
        this.name = "";
        this.attack = false;
        this.farmerID = 0;
        this.farmerName = "";
        this.healthLog = new ArrayList();
        this.bYear = null;
        this.health = 1;

    }

    //Getter- og settermetoder for felt
    public int getID() {
        return ID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFarmer() {
        return farmerID;
    }

    public void setFarmer(int farmerID) {
        this.farmerID = farmerID;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public boolean isHealthy() {
        return attack;
    }

    public void setHealthy(boolean healthy) {
        this.attack = healthy;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public java.sql.Date getBday() {
        return this.bYear;
    }

    public void setBday(java.sql.Date bYear) {
        this.bYear = bYear;
    }

    public double getHealth() {
        return this.health;
    }

    public void setHealth(double health) {
        this.health = health;
    }
}