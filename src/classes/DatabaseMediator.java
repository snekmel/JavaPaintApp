package classes;

import interfaces.PersistencyMediator;

import java.awt.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DatabaseMediator implements PersistencyMediator {
    Properties prop = new Properties();
    Connection con;

    public DatabaseMediator() {

        try{
            FileInputStream input = new FileInputStream("src/app.prop");
            prop.load(input);
            this.initConnection();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    void closeConnection() {
        try {
            this.con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    void initConnection() {

       try{
           Class.forName("com.mysql.jdbc.Driver");
           String connectionUrl = prop.getProperty("connection");
           this.con = DriverManager.getConnection(connectionUrl,prop.getProperty("user"),prop.getProperty("password"));
       }catch(Exception e){
           System.out.println(e);
       }
    }
    @Override
    public Drawing load(String id)
    {
        Drawing returnDrawing = null;

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT object FROM drawings WHERE id = " + id + ";");
            while (rs.next()) {
                byte[] st = (byte[]) rs.getObject(1);
                ByteArrayInputStream baip = new ByteArrayInputStream(st);
                ObjectInputStream ois = new ObjectInputStream(baip);

                returnDrawing =  (Drawing) ois.readObject();
            }
            stmt.close();
            rs.close();
            this.closeConnection();

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        return returnDrawing;
    }

    @Override
    public ArrayList<Drawing> loadAll() {

        ArrayList<Drawing> returnList = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT object FROM drawings;");
            while (rs.next()) {
                byte[] st = (byte[]) rs.getObject(1);
                ByteArrayInputStream baip = new ByteArrayInputStream(st);
                ObjectInputStream ois = new ObjectInputStream(baip);

               returnList.add((Drawing) ois.readObject());
            }
            stmt.close();
            rs.close();
            this.closeConnection();

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return returnList;
    }

    @Override
    public boolean save(Drawing drawing) {

         try {
             ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos);
             oos.writeObject(drawing);
             byte[] drawingToBytes = baos.toByteArray();
             PreparedStatement pstmt = con.prepareStatement("INSERT INTO drawings (object) VALUES(?)");
             ByteArrayInputStream bais = new ByteArrayInputStream(drawingToBytes);
             pstmt.setBinaryStream(1, bais, drawingToBytes.length);
             pstmt.executeUpdate();
             pstmt.close();
         } catch (Exception ex) {
             System.out.println(ex.toString());
         }


    return true;

}


    @Override
    public boolean init(Properties props) {
        return false;
    }
}
