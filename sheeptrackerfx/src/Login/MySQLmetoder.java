/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;


import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MySQLmetoder {

    private static final String TILKOBLINGSSTRENG = "jdbc:mysql://mysql.stud.ntnu.no/magnne_bonder";
    private static final String USERNAME = "magnne_it1901";
    private static final String PASSWORD = "gruppe12";
    private static Connection conn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    
    
    //Metode for å validere validere epost og passord fra bruker ved login.
    protected static int ValidateUser(String email, String password) {
        try {
            // Gjør klar connection til database og sql-query
            conn = DriverManager.getConnection(TILKOBLINGSSTRENG, USERNAME, PASSWORD);
            String sql = "SELECT ID FROM Users WHERE Email=? AND Password = SHA1(?)";
            ps = conn.prepareStatement(sql);

            // Gjør klar parameterne som skal sendes inn
            ps.setString(1, email);
            String pass = password;
            ps.setString(2, pass);

            // Eksekverer statementet og resultatet legges i ResultSet
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("ID");
            } else {
                return -1;
            }
        } catch (Exception e) {
            return -1;
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (Exception e) {
            }
        }
    }

    
    //Metode for å registrere bruker.
    protected static boolean registerUser(String firstName, String lastName, String email, String phone, String password, String buddy, String buddyPhone, double latitude, double longitude) {
        try {
            // Gjør klar connection til databasen og sql-query
            conn = DriverManager.getConnection(TILKOBLINGSSTRENG, USERNAME, PASSWORD);
            String sql = "INSERT INTO Users (FirstName, LastName, Email, Phonenumber, Password, buddy, buddyPhonenumber, latitude, longitude) VALUES (?, ?, ?, ?, SHA1(?), ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);

            // Setter stor forbokstav i navn
            String tempFirstName = firstName.replaceFirst("" + firstName.charAt(0), "" + firstName.toUpperCase().charAt(0));
            String tempLastName = lastName.replaceFirst("" + lastName.charAt(0), "" + lastName.toUpperCase().charAt(0));
            
            if(buddyPhone.equals("")){
                buddyPhone = "0";
            }
            
            // Gjør klar parametrene som skal sendes inn
            ps.setString(1, tempFirstName);
            ps.setString(2, tempLastName);
            ps.setString(3, email);
            ps.setInt(4, Integer.parseInt(phone));
            ps.setString(5, password);
            ps.setString(6, buddy);
            ps.setInt(7, Integer.parseInt(buddyPhone));
            ps.setDouble(8, latitude);
            ps.setDouble(9, longitude);

            ps.execute();
        } catch (Exception e) {
            if (e.getMessage().contains("Duplicate entry")) {
                JOptionPane.showMessageDialog(null, "Kunne ikke legge til bruker.\n" + "Eposten er allerede registrert");
            } else if (e.getMessage().contains("index out of range")) {
            } else {
                JOptionPane.showMessageDialog(null, "Kunne ikke legge til bruker.\n " + e.getMessage());
            }
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                }
            }
        }
        return true;
    }

    
    //Metode for å hente informasjon til en bonde og oppretter en ny bonde-instans
    protected static Bonde getFarmer(int userID) {
        try {
            // Gjør klar connection til databasen og sql-query
            conn = DriverManager.getConnection(TILKOBLINGSSTRENG, USERNAME, PASSWORD);
            String sql = "SELECT * FROM Users WHERE ID = ?";
            ps = conn.prepareStatement(sql);

            // Gjør klar parametrene som skal sendes inn
            ps.setInt(1, userID);
            rs = ps.executeQuery();

            if (rs.next()) {
                try {
                    // Opprette en ny instans av klassen Bonde, og returner den.
                    Bonde bonde = new Bonde(rs.getInt("ID"));
                    bonde.setEmail(rs.getString("Email"));
                    bonde.setFirstName(rs.getString("FirstName"));
                    bonde.setLastName(rs.getString("LastName"));
                    bonde.setLoginTime(rs.getString("LastLogin"));
                    bonde.setPassword(rs.getString("Password"));
                    bonde.setLatitude(rs.getDouble("Latitude"));
                    bonde.setLongitude(rs.getDouble("Longitude"));
                    bonde.setBuddyEmail(rs.getString("buddy"));
                    bonde.setPhonenumber(rs.getInt("Phonenumber"));
                    bonde.setBuddyPhonenumber(rs.getInt("buddyPhonenumber"));

                    return bonde;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return null;
                }
            } else {
                System.out.println("ERROR");
                return null;
            }
        } catch (Exception e) {
            return null;
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e) {
            }

            rs = null;
        }
    }

    
    //Metode som tar inn et Bonde-objekt, og oppdaterer den gitte bonden i databasen
    protected static void updateFarmer(Bonde bonde) {
        try {
            // Hent et tilkoblingsobjekt
            conn = DriverManager.getConnection(TILKOBLINGSSTRENG, USERNAME, PASSWORD);

            // Generer spørringen
            String sql = "UPDATE Users SET FirstName = ?, LastName = ?, Phonenumber = ?, Email = ?, Password = ?, buddy = ?, buddyPhonenumber = ?, latitude = ?, longitude = ? WHERE ID = ?;";

            // Bruk PreparedStatement for å utføre spørring
            ps = conn.prepareStatement(sql);
            ps.setString(1, bonde.getFirstName());
            ps.setString(2, bonde.getLastName());
            ps.setInt(3, bonde.getPhonenumber());
            ps.setString(4, bonde.getEmail());
            ps.setString(5, bonde.getPassword());
            ps.setString(6, bonde.getBuddyEmail());
            ps.setInt(7, bonde.getBuddyPhonenumber());
            ps.setDouble(8, bonde.getLatitude());
            ps.setDouble(9, bonde.getLongitude());
            ps.setInt(10, bonde.getID());

            // Utfør spørringen
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Kunne ikke oppdater bonden: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    //Metode for å regisrtere en sau
    protected static boolean registerSheep(int farmerID, String name, double weight, double health,
            String bDay, boolean attack, double latitude, double longitude) {

        try {
            // Gjør klar connection til databasen og sql-query
            conn = DriverManager.getConnection(TILKOBLINGSSTRENG, USERNAME, PASSWORD);
            String sql = "INSERT INTO Sheeps (UserID,Name, BirthDay, Helsetilstand, UnderAngrep, Weight, latitude, longitude) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);


            //Setter stor forbokstav 
            String tempName = name.replaceFirst("" + name.charAt(0), "" + name.toUpperCase().charAt(0));

            // Gjør klar parametrene som skal sendes inn i spørringen
            ps.setInt(1, farmerID);
            ps.setString(2, tempName);
            ps.setString(3, bDay);
            ps.setDouble(4, health);
            ps.setBoolean(5, attack);
            ps.setDouble(6, weight);
            ps.setDouble(7, latitude);
            ps.setDouble(8, longitude);

            //Utfører spørringen
            ps.execute();

        } catch (SQLException e) {

            if (e.getMessage().contains("Duplicate entry")) {
                JOptionPane.showMessageDialog(null, "Kunne ikke legge til sau.\n" + "Sauen finnes allerede i databasen");
            } else {
                JOptionPane.showMessageDialog(null, "Kunne ikke legge til sau.\n " + e.getMessage());
            }
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }

        return true;
    }
    
    
    //Metode for å oppdatere til en gitt sau.
    protected static void updateSheepLatLng(Sau sheep, double lat, double lng) {
        try {
            // Gjør klar connection til databasen og sql-query
            conn = DriverManager.getConnection(TILKOBLINGSSTRENG, USERNAME, PASSWORD);
            String sql = "UPDATE Sheeps SET latitude = ?, longitude = ? WHERE ID = ?";
            ps = conn.prepareStatement(sql);

            // Gjør klar parametrene som skal sendes inn i spørringen
            ps.setDouble(1, lat);
            ps.setDouble(2, lng);
            ps.setInt(3, sheep.getID());

            //Utfører spørringen
            ps.execute();

        } catch (SQLException e) {
            e.getMessage();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    
    //Metode for å oppdatere helsetilstand til sau.
    protected static void updateSheepHealth(Sau sheep, double health) {
        try {
            // Gjør klar connection til databasen og sql-query
            conn = DriverManager.getConnection(TILKOBLINGSSTRENG, USERNAME, PASSWORD);
            String sql = "UPDATE Sheeps SET Helsetilstand = ? WHERE ID = ?";
            ps = conn.prepareStatement(sql);

            // Gjør klar parametrene som skal sendes inn i spørringen
            ps.setDouble(1, health);
            ps.setInt(2, sheep.getID());

            //Utfører spørringen
            ps.execute();
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }

    }

    
    //Metode for å oppdatere vekten til en gitt sau.
    protected static void updateSheepWeight(int sheepID, double weight){
         try {
            // Gjør klar connection til databasen og sql-query
            conn = DriverManager.getConnection(TILKOBLINGSSTRENG, USERNAME, PASSWORD);
            String sql = "UPDATE Sheeps SET Weight = ? WHERE ID = ?";
            ps = conn.prepareStatement(sql);

            // Gjør klar parametrene som skal sendes inn i spørringen
            ps.setDouble(1, weight);
            ps.setInt(2, sheepID);

            //Utfører spørringen
            ps.execute();

        } catch (SQLException e) {
            e.getMessage();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
        
        
    }
    
    
    
    //Metode som setter angrepstatus i databasen.
    protected static void updateAttack(Sau sheep, boolean attack) {
        try {
            // Gjør klar connection til databasen og sql-query
            conn = DriverManager.getConnection(TILKOBLINGSSTRENG, USERNAME, PASSWORD);
            String sql = "UPDATE Sheeps SET UnderAngrep = ? WHERE ID = ?";
            ps = conn.prepareStatement(sql);

            // Gjør klar parametrene som skal sendes inn i spørringen
            ps.setBoolean(1, attack);
            ps.setInt(2, sheep.getID());

            //Utfører spørringen
            ps.execute();

        } catch (SQLException e) {
            e.getMessage();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }
    
    
    //Metode for å hente alle  sauer til en gitt bonde og returnerer en liste av alle sauer.
    protected static ArrayList<Sau> getSheepList(int userID) {
        ArrayList<Sau> sauer = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(TILKOBLINGSSTRENG, USERNAME, PASSWORD);
            String sql = "SELECT * FROM Sheeps WHERE UserID=?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);

            rs = ps.executeQuery();

            Sau sau;
            while (rs.next()) {
                sau = new Sau(rs.getInt("ID"));
                sau.setName(rs.getString("Name"));
                sau.setWeight(rs.getDouble("Weight"));
                sau.setLatitude(rs.getDouble("latitude"));
                sau.setLongitude(rs.getDouble("longitude"));
                sau.setBday(rs.getDate("Birthday"));
                sau.setHealth(rs.getDouble("Helsetilstand"));
                sauer.add(sau);
            }
        } catch (java.sql.SQLException ex) {
            return null;
        } finally {
            // Frigjør ressurser
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (java.sql.SQLException sqlEx) {
            } // Ignorere alle SQL-exceptions her
            rs = null;
        }
        return sauer;
    }

    
    //Metode for å hente en sau med saueIDen fra databasen.
    protected static Sau getSheep(int sheepID) {
        Sau sau;
        try {
            conn = DriverManager.getConnection(TILKOBLINGSSTRENG, USERNAME, PASSWORD);
            String sql = "SELECT * FROM Sheeps WHERE ID=?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, sheepID);

            rs = ps.executeQuery();

            if (rs.next()) {
                sau = new Sau(rs.getInt("ID"));
                sau.setName(rs.getString("Name"));
                sau.setWeight(rs.getDouble("Weight"));
                sau.setLatitude(rs.getDouble("latitude"));
                sau.setLongitude(rs.getDouble("longitude"));
                sau.setBday(rs.getDate("Birthday"));
                sau.setHealth(rs.getDouble("Helsetilstand"));
                return sau;
            } else {
                return null;
            }


        } catch (java.sql.SQLException ex) {
            return null;
        } finally {
            // Frigjør ressurser
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (java.sql.SQLException sqlEx) {
            } // Ignorere alle SQL-exceptions her
            rs = null;
        }
    }

    
    //Metode for å slette loggen.
    protected static void deleteLog(int userID) {
        try {
            conn = DriverManager.getConnection(TILKOBLINGSSTRENG, USERNAME, PASSWORD);
            String sql = "DELETE FROM Logg WHERE UserID = ?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Kunne ikke slette loggen.\n " + e.getMessage());
            return;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Loggen ble slettet");
        return;
    }
    
    
    //Metode for å slette en sau.
    protected static boolean deleteSheep(int userID, int sheepID) {
        try {
            conn = DriverManager.getConnection(TILKOBLINGSSTRENG, USERNAME, PASSWORD);
            String sql = "DELETE FROM Sheeps WHERE UserID = ? AND ID = ?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.setInt(2, sheepID);
            ps.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Kunne ikke slette sau.\n " + e.getMessage());
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
        return true;
    }

    
    
    //Metode for å slette GPSLoggen til en sau når man sletter en sau
    protected static boolean deleteGPSLog(int sheepID) {
        try {
            conn = DriverManager.getConnection(TILKOBLINGSSTRENG, USERNAME, PASSWORD);
            String sql = "DELETE FROM GPSData WHERE sheepID = ?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, sheepID);
            ps.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Kunne ikke slette sau.\n " + e.getMessage());
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
        return true;
    }
    
    
    //Metode for å hente loggen til en gitt bonde
    protected static ArrayList<String> getLog(int userID) {
        ArrayList<String> meldinger = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(TILKOBLINGSSTRENG, USERNAME, PASSWORD);
            String sql = "SELECT * FROM Logg WHERE UserID=? ORDER BY ID DESC";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);

            rs = ps.executeQuery();
            String mld;

            while (rs.next()) {
                String temp = "" + rs.getTimestamp("Timestamp");
                mld = temp.substring(0, 19) + ":   " + rs.getString("Logg");
                meldinger.add(mld);
            }

        } catch (java.sql.SQLException ex) {
            return null;
        } finally {
            // Frigjør ressurser
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (java.sql.SQLException sqlEx) {
            } // Ignorere alle SQL-exceptions her
            rs = null;
        }
        return meldinger;
    }

    
    
    //Metode for å legge til info i loggen
    protected static void insertLog(int userID, String melding) {

        try {
            // Gjør klar connection til databasen og sql-query
            conn = DriverManager.getConnection(TILKOBLINGSSTRENG, USERNAME, PASSWORD);
            String sql = "INSERT INTO Logg (UserID,Logg) VALUES (?, ?)";
            ps = conn.prepareStatement(sql);

            // Gjør klar parametrene som skal sendes inn i spørringen
            ps.setInt(1, userID);
            ps.setString(2, melding);

            ps.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Kunne ikke loggføre handlingen.\n" + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }

    }
    
    
    
    //Metode for å hente GPSLogg for en gitt sau.
    protected static ArrayList<GPSData> getGPSLog(int sheepID) {
         ArrayList<GPSData> gpsData = new ArrayList<>();
        try{
            conn = DriverManager.getConnection(TILKOBLINGSSTRENG, USERNAME, PASSWORD);
            String sql = "SELECT * FROM GPSData WHERE sheepID=? ORDER BY ID DESC";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, sheepID);

            rs = ps.executeQuery();
            GPSData gps;
            
            while (rs.next()) 
            {
                gps = new GPSData(rs.getInt("ID"));
                gps.setSheepID(rs.getInt("sheepID"));
                gps.setDate(rs.getTimestamp("Tid"));
                gps.setLatitude(rs.getDouble("Latitude"));
                gps.setLongitude(rs.getDouble("Longitude"));
                gpsData.add(gps);
            }
            
        }
        catch (java.sql.SQLException ex) 
        {
            return null;
        }
        finally 
        {
            // Frigjør ressurser
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (java.sql.SQLException sqlEx) { } // Ignorere alle SQL-exceptions her
            rs = null;
        }
        return gpsData;
    }
    
    
    //Metode for å sette inn verdier i GPSLoggen for en gitt sau
    protected static void insertSheepGPSLog(Sau sheep, double lat, double lng){
        try {
            // Gjør klar connection til databasen og sql-query
            conn = DriverManager.getConnection(TILKOBLINGSSTRENG, USERNAME, PASSWORD);
            String sql = "INSERT INTO GPSData (sheepID, Latitude, Longitude) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(sql);

            // Gjør klar parametrene som skal sendes inn i spørringen
            ps.setInt(1, sheep.getID());
            ps.setDouble(2, lat);
            ps.setDouble(3, lng);
            

            //Utfører spørringen
            ps.execute();
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }
}
