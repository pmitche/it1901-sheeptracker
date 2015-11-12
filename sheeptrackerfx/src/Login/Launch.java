package Login;


import java.util.regex.*;
import javax.swing.*;

public class Launch extends javax.swing.JFrame {

    private final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private Pattern pattern;
    private Matcher matcher;
    private String[] args;

    // Lager et nytt JPanel Form
    public Launch() {
        // initComponents() autogenererer all kode fra drag-and-drop design
        initComponents();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginTab = new javax.swing.JTabbedPane();
        Login = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        loginButton = new javax.swing.JButton();
        usernameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Registrer = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        firstNameField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lastNameField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        registerButton = new javax.swing.JButton();
        passwordRegField = new javax.swing.JPasswordField();
        jLabel10 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel11 = new javax.swing.JLabel();
        chkBox_buddy = new javax.swing.JCheckBox();
        buddyField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        latitude = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        longitude = new javax.swing.JTextField();
        jToggleButton1 = new javax.swing.JToggleButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        phonenumberField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        buddyFieldTelefon = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SheepTracker Login v0.1");
        setBackground(new java.awt.Color(204, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        loginTab.setBackground(new java.awt.Color(204, 255, 255));

        Login.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Login", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12), new java.awt.Color(51, 153, 255))); // NOI18N
        jPanel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        loginButton.setText("Login");
        loginButton.setFocusable(false);
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        usernameField.setToolTipText("");

        jLabel1.setText("E-mail");

        jLabel2.setText("Password");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(usernameField)
                        .addComponent(passwordField)
                        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(1, 1, 1)
                .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(1, 1, 1)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loginButton)
                .addGap(19, 19, 19))
        );

        Login.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(176, 106, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/Sheep1.png"))); // NOI18N
        jLabel7.setText("jLabel7");
        Login.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 170, 420, 480));

        loginTab.addTab("Login", Login);

        Registrer.setPreferredSize(new java.awt.Dimension(695, 550));
        Registrer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("Fornavn");
        Registrer.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, -1, -1));
        Registrer.add(firstNameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 36, 400, -1));

        jLabel4.setText("Etternavn");
        Registrer.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 65, -1, -1));
        Registrer.add(lastNameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 82, 400, -1));

        jLabel8.setText("Epost");
        Registrer.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 40, -1));
        Registrer.add(emailField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 240, -1));

        jLabel9.setText("Passord");
        Registrer.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 175, -1, -1));

        registerButton.setText("Register");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });
        Registrer.add(registerButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, -1, -1));
        Registrer.add(passwordRegField, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 193, 400, -1));

        jLabel10.setText("Skriv inn passord igjen");
        Registrer.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 222, -1, -1));
        Registrer.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 245, 400, -1));

        jLabel11.setText("Vil du legge til en person til varsling dersom sauer er under angrep?");
        Registrer.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 450, 20));

        chkBox_buddy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBox_buddyActionPerformed(evt);
            }
        });
        Registrer.add(chkBox_buddy, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 370, 20));

        buddyField.setEnabled(false);
        buddyField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buddyFieldActionPerformed(evt);
            }
        });
        Registrer.add(buddyField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 230, -1));

        jLabel5.setText("Breddegrad");
        Registrer.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, -1, -1));

        latitude.setEditable(false);
        Registrer.add(latitude, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 400, -1));

        jLabel6.setText("Lengdegrad");
        Registrer.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, -1, -1));

        longitude.setEditable(false);
        longitude.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                longitudeActionPerformed(evt);
            }
        });
        Registrer.add(longitude, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 400, -1));

        jToggleButton1.setText("Hente posisjonen til gården");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        Registrer.add(jToggleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 520, -1, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/Sheep1.png"))); // NOI18N
        jLabel12.setText("jLabel7");
        Registrer.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 170, 420, 480));

        jLabel13.setText("Mobilnummer");
        Registrer.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, -1, -1));

        phonenumberField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phonenumberFieldActionPerformed(evt);
            }
        });
        Registrer.add(phonenumberField, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 140, -1));

        jLabel14.setText("Epost til venn");
        Registrer.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        jLabel15.setText("Mobilnummer");
        Registrer.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 310, -1, -1));

        buddyFieldTelefon.setEnabled(false);
        buddyFieldTelefon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buddyFieldTelefonActionPerformed(evt);
            }
        });
        Registrer.add(buddyFieldTelefon, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 330, 150, -1));

        loginTab.addTab("Register", Registrer);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(loginTab)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(loginTab)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(718, 653));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Når man klikker på loginknappen
    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        int userID = MySQLmetoder.ValidateUser(usernameField.getText(), passwordField.getText());

        if (userID != -1) {
            // Lukker dette vinduet
            this.dispose();

            // Åpner Index-vinduet
            Index mainWindow = new Index(userID);
            mainWindow.setVisible(true);
            Index.runTableOnLaunch();
        } else {
            JOptionPane.showMessageDialog(this, "Feil brukernavn/passord", null, JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_loginButtonActionPerformed

    
    // Når man klikker på registrerknappen
    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed

        if (!hasEmptyFields()) {
            JOptionPane.showMessageDialog(null, "Registrering feilet.\nEtt eller flere felter er tomme", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!hasEqualPasswords()) {
            JOptionPane.showMessageDialog(null, "Registrering feilet\nPassordene er ulike", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!hasValidEmailFormat()) {
            JOptionPane.showMessageDialog(null, "Registrering feilet.\nEpost må være på format ola@nordmann.no", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!hasValidPhonenumber(phonenumberField.getText(), buddyFieldTelefon.getText())) {
            JOptionPane.showMessageDialog(null, "Telefonnummer må bestå av 8 siffer!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (MySQLmetoder.registerUser(firstNameField.getText(), lastNameField.getText(),
                emailField.getText(), phonenumberField.getText(), passwordRegField.getText(), buddyField.getText(), buddyFieldTelefon.getText(), Double.parseDouble(latitude.getText()),
                Double.parseDouble(longitude.getText()))) {
            
            JOptionPane.showMessageDialog(this, "En ny bruker er nå lagt til.");
            // Blank ut alle feltene
            clearFields();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid details.\nKunne ikke legge til bruker.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_registerButtonActionPerformed

    
    //Viser felt for buddy dersom checkboxen er checked
    private void chkBox_buddyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBox_buddyActionPerformed
        if (chkBox_buddy.isSelected()) {
            buddyField.setEnabled(true);
            buddyFieldTelefon.setEnabled(true);

        } else {
            buddyField.setEnabled(false);
            buddyFieldTelefon.setEnabled(false);
        }
    }//GEN-LAST:event_chkBox_buddyActionPerformed

    //Viser kartet
    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        Kart kart = new Kart();
        kart.main(args);
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void buddyFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buddyFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buddyFieldActionPerformed

    private void longitudeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_longitudeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_longitudeActionPerformed

    private void phonenumberFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phonenumberFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phonenumberFieldActionPerformed

    private void buddyFieldTelefonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buddyFieldTelefonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buddyFieldTelefonActionPerformed

    
    //Sjekker etter tomme felter
    private boolean hasEmptyFields() {
        if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() || emailField.getText().isEmpty()
                || passwordRegField.getText().isEmpty() || latitude.getText().isEmpty() || longitude.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    
    //Sjekker at passordene stemmer med hverandre
    private boolean hasEqualPasswords() {
        String pass1 = new String(passwordRegField.getPassword());
        String pass2 = new String(jPasswordField1.getPassword());
        if (!pass1.equals(pass2)) {
            return false;
        }
        return true;
    }

    
    //Sjekker at eposten er av riktig format
    private boolean hasValidEmailFormat() {
        String mail = emailField.getText();
        pattern = Pattern.compile(EMAIL_REGEX);
        matcher = pattern.matcher(mail);
        return matcher.matches();
    }

    
    //Metode for å tømme skjemaet
    private void clearFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");
        passwordRegField.setText("");
        jPasswordField1.setText("");
        buddyField.setText("");
        chkBox_buddy.setSelected(false);
        latitude.setText("");
        longitude.setText("");
        phonenumberField.setText("");
        buddyFieldTelefon.setText("");
    }

    
    //Metode som mottar koordinater fra javascript via klassen Kart, og setter lengde og breddegrad til bonden ved registrering.
    public static void setLatLng(double lat, double lng) {
        latitude.setText("" + lat);
        longitude.setText("" + lng);

    }

    
    //Sjekker at postnummer er riktig format.
    private boolean hasValidPhonenumber(String phone, String buddy) {
        try {
            if(chkBox_buddy.isSelected() && phone.length() ==  8 && buddy.length() == 8){
                Integer.parseInt(phone);
                Integer.parseInt(buddy);
            }
            else if (phone.length() > 0 && phone.length() < 9) {
                Integer.parseInt(phone);
            }
            
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Launch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Launch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Launch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Launch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Launch().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Login;
    private javax.swing.JPanel Registrer;
    private javax.swing.JTextField buddyField;
    private javax.swing.JTextField buddyFieldTelefon;
    private static javax.swing.JCheckBox chkBox_buddy;
    private javax.swing.JTextField emailField;
    private javax.swing.JTextField firstNameField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTextField lastNameField;
    private static javax.swing.JTextField latitude;
    private javax.swing.JButton loginButton;
    private javax.swing.JTabbedPane loginTab;
    private static javax.swing.JTextField longitude;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JPasswordField passwordRegField;
    private javax.swing.JTextField phonenumberField;
    private javax.swing.JButton registerButton;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables


}
