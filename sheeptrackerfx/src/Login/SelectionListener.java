/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.awt.Color;
import java.util.Calendar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SelectionListener implements ListSelectionListener {
    //Klasse for å lytte på endringer i selection av tabellrad i tabell sheep
    //Returnerer sau basert på ID som står i valgt rad
    //Setter korrekte labels for å vise mer informasjon om valgt sau
    //Returnerer riktig row-index basert på om det hentes fra View eller Model (se JTable javadoc)

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) // mouse button not released yet
        {
            return;
        }
        int viewRow = Index.tbl_sheep.getSelectedRow();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        if (viewRow < 0) {
            return;
        } else {
            int rowID = (int) Index.tbl_sheep.getValueAt(viewRow, 0);
            Sau sheepDetails = MySQLmetoder.getSheep(rowID);
            Index.sokIDLabelVariable.setText(Integer.toString(sheepDetails.getID()));
            Index.sokNameLabelVariable.setText(sheepDetails.getName());
            Index.sokWeightLabelVariable.setText(Double.toString(sheepDetails.getWeight()) + " kg");
            Index.sokLatitudeLabelVariable.setText(Double.toString(sheepDetails.getLatitude()));
            Index.sokLongditudeLabelVariable.setText(Double.toString(sheepDetails.getLongitude()));
            Index.sokBdayLabelVariable.setText("" + (year - Integer.parseInt(sheepDetails.getBday().toString().substring(0, 4))) + "år");
            changeColorHealthLabel(sheepDetails);
            Index.gpsTable.removeAll();
            Index.LoadGPSTableWithSheep(rowID);
        }

    }

    //Setter riktig farge på helsetilstand label
    //0.00 - 0.15: rød
    //0.15 - 0.50: oransj
    //0.50 - 0.70: gul
    //0.70 - 1.00: grønn
    public static void changeColorHealthLabel(Sau sau) {
        if (sau.getHealth() >= 0.70) {
            Index.sokHealthLabelVariable.setText("God");
            Index.sokHealthLabelVariable.setForeground(Color.GREEN);
        } else if (sau.getHealth() >= 0.50 && sau.getHealth() < 0.70) {
            Index.sokHealthLabelVariable.setText("Nokså god");
            Index.sokHealthLabelVariable.setForeground(Color.YELLOW);
        } else if (sau.getHealth() >= 0.15 && sau.getHealth() < 0.50) {
            Index.sokHealthLabelVariable.setText("Trenger tilsyn");
            Index.sokHealthLabelVariable.setForeground(Color.ORANGE);
        } else if (sau.getHealth() < 0.15) {
            Index.sokHealthLabelVariable.setText("KRITISK");
            Index.sokHealthLabelVariable.setForeground(Color.RED);
        }
    }
}
