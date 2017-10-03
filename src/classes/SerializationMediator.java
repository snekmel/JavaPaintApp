package classes;

import interfaces.PersistencyMediator;

import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class SerializationMediator implements PersistencyMediator {
    Properties props = new Properties();


    public SerializationMediator() {
        try{
            FileInputStream input = new FileInputStream("src/app.prop");
            props.load(input);

        }catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public Drawing load(String nameDrawing) {
       Drawing returnDrawing = null;
       try{
            FileInputStream fileIn = new FileInputStream(props.getProperty("exportLocation")+ nameDrawing + ".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            returnDrawing = (Drawing) in.readObject();
            in.close();
            fileIn.close();
        }catch(Exception e){
            System.out.println(e);
        }

        return returnDrawing;
    }

    @Override
    public ArrayList<Drawing> loadAll() {
        ArrayList<Drawing> returnList = new ArrayList<>();

        File folder = new File(props.getProperty("exportLocation"));
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {


                try{
                    FileInputStream fileIn = new FileInputStream(file);
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    returnList.add( (Drawing) in.readObject());
                    in.close();
                    fileIn.close();

                }catch(Exception e){

                }

            }
        }
        return returnList;
    }

    @Override
    public boolean save(Drawing drawing) {

        try{
            String outputLocation = props.getProperty("exportLocation")+ drawing.getName() + ".ser";
            FileOutputStream fileOut = new FileOutputStream(outputLocation);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(drawing);
            out.close();
            fileOut.close();
            System.out.println("Opgeslagen in /" + outputLocation);
            return true;

        }catch(Exception e){
            return false;
        }

    }

    @Override
    public boolean init(Properties props) {
        return false;
    }
}
