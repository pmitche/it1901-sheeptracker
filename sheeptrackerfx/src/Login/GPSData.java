/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;


//Klasse for å behandle GPS-informasjon til hver enkelt sau
public class GPSData {
    
    private java.sql.Timestamp date;
    private double latitude, longitude;
    private int ID, sheepID;
    
    public GPSData(int ID){
        this.sheepID = 0;
        this.ID = 0;
        this.date = null;
        this.latitude = 0;
        this.longitude = 0;
    }
    
    public java.sql.Timestamp getDate(){
        return this.date;
    }    
    
    public void setDate(java.sql.Timestamp date){
        this.date = date;
    }
    
    public double getLatitude(){
        return this.latitude;
    }
    
    public void setLatitude(double latitude){
        this.latitude = latitude;
    }
    
    public double getLongitude(){
        return this.longitude;
    }
    
    public void setLongitude(double longitude){
        this.longitude = longitude;
    }
    
    public int getID(){
        return this.ID;
    }
    
    public void setID(int ID){
        this.ID = ID;
    }
    
    public int getSheepID(){
        return this.sheepID;
    }
    
    public void setSheepID(int sheepID){
        this.sheepID = sheepID;
    }
}
