/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Bonde {

    private String firstName, lastName, email, buddyEmail, password, loginTime;
    private int ID, phonenumber, buddyPhonenumber;
    private double latitude, longitude;
    

    public Bonde(int ID) {
        this.ID = ID;
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.phonenumber = 0;
        this.password = "";
        this.latitude = 33.678476;
        this.longitude = -116.242568;
        this.buddyEmail = "";
        this.buddyPhonenumber = 0;
    }

    // Set-metoder er foreløpig uten verifikasjon
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public int getPhonenumber() {
        return this.phonenumber;
    }
    
    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }
    
    public int getBuddyPhonenumber() {
        return this.buddyPhonenumber;
    }
    
    public void setBuddyPhonenumber(int phonenumber) {
        this.buddyPhonenumber = phonenumber;
    }
    
    public int getID() {
        return this.ID;
    }

    public void setLoginTime(String timestamp) {
        this.loginTime = timestamp;
    }

    public String getLoginTime() {
        return loginTime.toString().substring(0, 19);
    }

    public String getPassword() {
        return this.password;
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

    public void setBuddyEmail(String buddyEmail) {
        this.buddyEmail = buddyEmail;
    }

    public String getBuddyEmail() {
        return buddyEmail;
    }

    //Hashing av passord
    public String makeSHA1Hash(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.reset();
        byte[] buffer = input.getBytes();
        md.update(buffer);
        byte[] digest = md.digest();

        String hexStr = "";
        for (int i = 0; i < digest.length; i++) {
            hexStr += Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1);
        }
        return hexStr;
    }
}
