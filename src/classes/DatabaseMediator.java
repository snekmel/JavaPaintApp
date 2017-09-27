package classes;

import interfaces.PersistencyMediator;

import java.awt.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DatabaseMediator implements PersistencyMediator {
    Properties prop = new Properties();
    Connection con;

    public DatabaseMediator() {
        this.initConnection();
        try{
            FileInputStream input = new FileInputStream("src/app.prop");
            prop.load(input);
            System.out.println(prop);
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
        String connectionUrl = prop.getProperty("connectionUrl");
       try{
           this.con = DriverManager.getConnection(connectionUrl);
       }catch(Exception e){
           System.out.println(e);
       }
    }
    @Override
    public Drawing load(String nameDrawing)
    {


        return null;
    }

    @Override
    public ArrayList<Drawing> loadAll() {
        ArrayList<Drawing> returnList = new ArrayList<>();
        try {
            Class.forName(prop.getProperty("dbDriver"));
            this.initConnection();
            PreparedStatement stmt1 =con.prepareStatement("Select * From Drawing");
            ResultSet rs = stmt1.executeQuery();
            while (rs.next()) {
                Drawing d = new Drawing(rs.getString("Name"));
                d.setId(rs.getInt("Id"));
                for (Oval o: loadOvalById(d.getId())
                     ) {
                    d.AddDrawingItem(o);
                }
                for (PaintedText pt: loadTextById(d.getId())){
                    d.AddDrawingItem(pt);
                }

                returnList.add(d);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return returnList;
    }

    @Override
    public boolean save(Drawing drawing) {
        try {
            Class.forName(prop.getProperty("dbDriver"));
            this.initConnection();
            int drawingDbId = 0;

            PreparedStatement stmt1 =con.prepareStatement("INSERT INTO Drawing (Name) VALUES (?);SELECT SCOPE_IDENTITY() AS [Result];");
            stmt1.setString(1, drawing.getName());
            ResultSet rs = stmt1.executeQuery();
            while (rs.next()) {

                drawingDbId = rs.getInt("Result");
            }

            for (DrawingItem d: drawing.getItems()
                    ) {

                if (d instanceof Oval){
                    Oval o = (Oval)d;
                 PreparedStatement stmt =con.prepareStatement("INSERT INTO Oval (X ,Y ,Width ,Height ,Weight,DrawingId) VALUES ( ?,?,?,?,?,?)");
                    stmt.setDouble(1, o.getAnchor().getX());
                    stmt.setDouble(2, o.getAnchor().getY());
                    stmt.setDouble(3, o.getWidth());
                    stmt.setDouble(4, o.getHeight());
                    stmt.setDouble(5, o.getWeight());
                    stmt.setInt(6, drawingDbId);
                    stmt.executeUpdate();

                }

                if (d instanceof PaintedText){
                    PaintedText pt = (PaintedText) d;
                    PreparedStatement stmt =con.prepareStatement("INSERT INTO Text (Text ,FontName ,X ,Y ,Width ,Height,DrawingId) VALUES (?,?,?,?,?,?,?)");
                    stmt.setString(1, pt.getContent());
                    stmt.setString(2, pt.getFontName());
                    stmt.setDouble(3, pt.getAnchor().getX());
                    stmt.setDouble(4, pt.getAnchor().getY());
                    stmt.setDouble(5, pt.getWidth());
                    stmt.setDouble(6, pt.getHeight());
                    stmt.setInt(7, drawingDbId);
                    stmt.executeUpdate();
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        this.closeConnection();

    return true;

}

public ArrayList<PaintedText> loadTextById(int id){

    ArrayList<PaintedText> returnLijst = new ArrayList<>();

    try{
        Class.forName(prop.getProperty("dbDriver"));
        this.initConnection();

        PreparedStatement stmt1 =con.prepareStatement("Select * From Text where DrawingId = ?");
        stmt1.setInt(1, id);
        ResultSet rs = stmt1.executeQuery();
        while (rs.next()) {
           Point p = new Point(rs.getDouble("X"),rs.getDouble("Y"));
           PaintedText pt = new PaintedText(Color.BLACK,rs.getString("Text"),rs.getString("FontName"),p,rs.getDouble("Width"),rs.getDouble("Height"));
           returnLijst.add(pt);

        }

        closeConnection();
    }catch(Exception e){
        System.out.println(e);
    }

    return returnLijst;
}

public ArrayList<Oval> loadOvalById(int id){
        ArrayList<Oval> returnLijst = new ArrayList<>();
 try{
     Class.forName(prop.getProperty("dbDriver"));
     this.initConnection();
     PreparedStatement stmt1 =con.prepareStatement("Select * From Oval where DrawingId = ?");
     stmt1.setInt(1, id);
     ResultSet rs = stmt1.executeQuery();
     while (rs.next()) {
         Point p = new Point(rs.getDouble("X"),rs.getDouble("Y"));
         Oval newOval = new Oval(Color.BLACK,p,rs.getDouble("Width"),rs.getDouble("Height"),rs.getDouble("Weight"));
         returnLijst.add(newOval);

     }

closeConnection();
 }catch(Exception e){
     System.out.println(e);
 }
 return  returnLijst;

}
    @Override
    public boolean init(Properties props) {
        return false;
    }
}
