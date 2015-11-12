/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//Klasse for å lytte på endringer i selection av tabellrad i tabell GPSData
//Returnerer GPS-objekt basert på ID som står i valgt rad
//Setter korrekte labels for å vise mer informasjon om valgt sau
//Returnerer riktig row-index basert på om det hentes fra View eller Model (se JTable javadoc)
public class SelectionListener2 implements ListSelectionListener{
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getValueIsAdjusting()) // mouse button not released yet
            return;
        int viewRow = Index.gpsTable.getSelectedRow();
        if (viewRow < 0)
            return;
        else{
            String temp = (String) Index.gpsTable.getValueAt(viewRow, 0);
            temp = temp.substring(45, temp.length());
            String[] parts = temp.split(",");
            String latitude = parts[0]; 
            String longitude = parts[1];
            
            Index.sokLatitudeLabelVariable.setText(latitude);
            Index.sokLongditudeLabelVariable.setText(longitude);
        }

    }
    
}
