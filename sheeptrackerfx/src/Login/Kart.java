/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.embed.swing.JFXPanel;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import netscape.javascript.JSObject;

/**
 *
 * @author Magnus
 */
public class Kart extends JApplet {

    private static final int JFXPANEL_WIDTH_INT = 660;
    private static final int JFXPANEL_HEIGHT_INT = 480;
    private static JFXPanel fxContainer;
    private static WebEngine webEngine;
    protected static double lat = 65.403445;
    protected static double lng = 14.194336;
    protected static double latSheep = 0.0;
    protected static double lngSheep = 0.0;
    private static JSObject jsobj;
    private static JApplet applet;
    protected static JFrame frame;
    protected static ArrayList<Sau> data;
    protected static String sheepName;

    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Exception e) {
                }

                frame = new JFrame("Kart");
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

                applet = new Kart();
                applet.init();

                frame.setContentPane(applet.getContentPane());

                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                applet.start();




            }
        });
    }

    
    
    
    @Override
    public void init() {
        fxContainer = new JFXPanel();

        fxContainer.setPreferredSize(new Dimension(JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT));

        JButton knapp = new JButton();
        knapp.setText("Lukk vinduet");
        knapp.setBounds(7, 445, 100, 30);

        knapp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent ae) {
                latSheep = 0.0;
                lngSheep = 0.0;
                frame.setVisible(false);
            }
        });

        fxContainer.add(knapp);

        add(fxContainer, BorderLayout.CENTER);

        // Lager et JavaFX-runnable vindu
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                createScene();
                if (lat != 65.403445 || lng != 14.194336) {
                    updateMap();

                    if (latSheep != 0.0 || lngSheep != 0.0) {
                        updateMapWithSheep();

                    } else if (data.size() > 1) {
                        showAllSheeps(data);
                        
                    }
                }

                sendBackToJavascript();
            }
        });
    }

    
    //Bygger selve nettleseren og laster inn HTML-fil med javascript
    private void createScene() {
        WebView webView = new WebView();
        webEngine = webView.getEngine();
       
        StackPane root = new StackPane();
        fxContainer.setScene(new Scene(root));

        URL urlGoogleMaps = getClass().getResource("googlemaps.html");
        webEngine.load(urlGoogleMaps.toExternalForm());

        root.getChildren().add(webView);
    }

    
    //Metode som tar inn alle sauer som en liste, og ittererer gjennom og plasserer 
    //sauene som individuelle markører på kartet.
    public static void showAllSheeps(final ArrayList<Sau> data) {

        sendBackToJavascript();

        webEngine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> ov, Worker.State oldState, Worker.State newState) {
                if (newState == Worker.State.SUCCEEDED) {
                    webEngine.executeScript("updateMap(' " + lat + " ',' " + lng + " ')");
                    webEngine.executeScript("placeMarker(' " + lat + " ',' " + lng + " ')");
                    for (int i = 0; i < data.size(); i++) {
                        double dataLat = data.get(i).getLatitude();
                        double dataLng = data.get(i).getLongitude();
                        webEngine.executeScript("placeAllSheeps(' " + dataLat + " ',' " + dataLng + " ',' " + data.get(i).getName() + " ')");

                    }
                }
            }
        });
    }

    
    //Metode for å oppdatere kartet dersom en bonde allerede har redistrert posisjonen på gården
    public void updateMap() {
        // Legger til et javacallback objekt til det webEngine-objektet som er åpent.

        sendBackToJavascript();

        webEngine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> ov, Worker.State oldState, Worker.State newState) {
                if (newState == Worker.State.SUCCEEDED) {
                    webEngine.executeScript("updateMap(' " + lat + " ',' " + lng + " ')");
                    webEngine.executeScript("placeMarker(' " + lat + " ',' " + lng + " ')");
                }
            }
        });

    }

    
    
    //Metode for å kommunisere mellom java-applet og javascript i HTML-filen som er lastet inn i webEngine.
    public static void sendBackToJavascript() {

        webEngine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> ov, Worker.State oldState, Worker.State newState2) {
                if (newState2 == Worker.State.SUCCEEDED) {
                    jsobj = (JSObject) webEngine.executeScript("window");
                    jsobj.setMember("java", new Bridge());
                }



            }
        });
    }

    
    //Metode for å vise en valgt sau på kartet.
    public void updateMapWithSheep() {
        // Legger til et javacallback objekt til det webEngine-objektet som er åpent.
        sendBackToJavascript();

        webEngine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> ov, Worker.State oldState, Worker.State newState) {
                if (newState == Worker.State.SUCCEEDED) {
                    webEngine.executeScript("updateMap(' " + lat + " ',' " + lng + " ')");
                    webEngine.executeScript("placeMarker(' " + lat + " ',' " + lng + " ')");
                    webEngine.executeScript("placeMarkerForSheep(' " + latSheep + " ',' " + lngSheep + " ',' " + sheepName + " ')");
                }
            }
        });

    }

    /**
     *Klasse for å koble sammen java og javascript.
     */
    public static class Bridge {

        public void setLatLngReg(double lat, double lng) {
            Login.Launch.setLatLng(lat, lng);
            Index.changePos(lat, lng);

        }
    }
}