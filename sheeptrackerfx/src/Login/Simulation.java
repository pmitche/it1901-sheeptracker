/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.JOptionPane;

/**
 *
 * @author Magnus
 */
public class Simulation {

    private static Bonde innloggetBonde;
    private static ArrayList<Sau> sheeps;
    private static Random random;
    private static double chance;
    private static int teller = 0;
    private static int teller2 = 0;
    private static SendEmail mail;
    private static String[] args;
    private static String farmerEmail;
    private static String buddyEmail;
    private static boolean status;
    private static Timer timer;

    public Simulation(Bonde innloggetBonde) {

        this.innloggetBonde = innloggetBonde;
        sheeps = MySQLmetoder.getSheepList(this.innloggetBonde.getID());
        farmerEmail = this.innloggetBonde.getEmail();
        buddyEmail = this.innloggetBonde.getBuddyEmail();
        this.status = false;
        Index.LoadLogTableWithMessages();
    }

    private static void updatePosition() {

        double rangeMinLat = -0.003;
        double rangeMaxLat = 0.003;
        double rangeMinLng = -0.0077;
        double rangeMaxLng = 0.0077;
        sheeps = MySQLmetoder.getSheepList(Simulation.innloggetBonde.getID());


        for (int i = 0; i < sheeps.size(); i++) {
            System.out.println(sheeps.get(i).getName());
            random = new Random();
            chance = Math.random();
            System.out.println(chance);
            double tempLatitude = innloggetBonde.getLatitude();
            double tempLongitude = innloggetBonde.getLongitude();
            double randomLatitude = rangeMinLat + (rangeMaxLat - rangeMinLat) * random.nextDouble() + tempLatitude;
            double randomLongitude = rangeMinLng + (rangeMaxLng - rangeMinLng) * random.nextDouble() + tempLongitude;

            attackRandomSheep(sheeps.get(i), chance);
            updateHealthRandomSheep(sheeps.get(i), random.nextDouble());

            MySQLmetoder.updateSheepLatLng(sheeps.get(i), randomLatitude, randomLongitude);
            MySQLmetoder.insertSheepGPSLog(sheeps.get(i), randomLatitude, randomLongitude);

        }

    }

    
    //Denne metoden tar inn en sau og en sjansevariabel som utfører et angrep på
    //sauen dersom 
    private static void attackRandomSheep(Sau sheep, double chance) {
        if (chance < 0.05) {
            System.out.println(sheep.getName());
            if (teller == 0) {

                MySQLmetoder.updateAttack(sheep, true);
                MySQLmetoder.insertLog(innloggetBonde.getID(), "Sauen " + sheep.getName() + " har blitt angrepet");
                MySQLmetoder.updateSheepHealth(sheep, 0.1);
                Index.LoadLogTableWithMessages();
                JOptionPane.showMessageDialog(null, "Sauen " + sheep.getName() + " har blitt utsatt for et angrep!"
                        + "\nEn SMS og Epost er sendt!");
                mail = new SendEmail(sheep, farmerEmail, buddyEmail);
                mail.main(args);
                teller++;
                teller2++;
            }

        }

    }

    private static void updateHealthRandomSheep(Sau sheep, double random) {
        if (chance < 0.1 && teller2 != 1) {
            SelectionListener.changeColorHealthLabel(sheep);
            String first = Index.sokHealthLabelVariable.getText();
            MySQLmetoder.updateSheepHealth(sheep, random);
            sheep.setHealth(random);
            SelectionListener.changeColorHealthLabel(sheep);
            String last = Index.sokHealthLabelVariable.getText();
            if(!first.equals(last)){
                MySQLmetoder.insertLog(innloggetBonde.getID(), "Helsetilstanden til " 
                    + sheep.getName() + " har blitt endret fra " + first + " til " + last + "!" );
                Index.LoadLogTableWithMessages();
            }
        }
        teller2 ++;
    }

    public static void runSheepSimulation(boolean status) {
        if (status) {
            MySQLmetoder.insertLog(innloggetBonde.getID(), "SIMULERING STARTET!");
            Index.LoadLogTableWithMessages();
            timer = new Timer();
            timer.schedule(new MyTimer(), 0, 30000);
        } else {
            MySQLmetoder.insertLog(innloggetBonde.getID(), "SIMULERING AVSLUTTET!");
            Index.LoadLogTableWithMessages();
            timer.cancel();
        }
    }

    private static class MyTimer extends TimerTask {

        public void run() {
            teller = 0;
            teller2 = 0;
            updatePosition();
        }
    }
}