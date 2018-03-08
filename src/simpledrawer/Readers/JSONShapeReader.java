/*
 * JSONShapeREader.java
 *
 * @author Gill Windall
 *
 * Used to read shapes from a file stored in JSON format.  Uses the Gson
 * library to convert the JSON from the file into Java objects in memory.
 * You can read more about Gson at https://code.google.com/p/google-gson/
 *
 */
package simpledrawer.Readers;

import simpledrawer.shapes.SOval;
import simpledrawer.shapes.SLine;
import com.google.gson.*;
import java.awt.Color;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import simpledrawer.shapes.Entity;


public class JSONShapeReader {

    private static class listOfShape {
        List<Entity.EntityType> listOfShape;
    }

    private listOfShape shapesList;
    private List<Entity> lShapes;
    private Gson gson; // gson object used to "parse" the JSON

    public JSONShapeReader() {
        gson = new Gson();
        lShapes = new ArrayList<>();
    }

    /**
     * Read the shapes in JSON format from file. The shapes are initially read
     * into a single list (listOfShapeEvents) and then split into separate lists
     * according to type of shape.
     *
     * @param file the file from which to read the JSON
     * @throws FileNotFoundException
     */
    public void getShapesFromFile(String file) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(
                new FileReader(file));
        shapesList = gson.fromJson(br, listOfShape.class); // load the shapes
        storeShapes(); // store in separate lists according to type
    }

    /**
     * Read through the list of shapes that have been loaded from file and
     * create an appropriate shape object according to type and store it in the
     * relevant list.
     */
    private void storeShapes() {

        for (Entity.EntityType se : shapesList.listOfShape) {
            switch (se) {
                case LINE:  // store the line
              //      SLine sl = new SLine(new Point(se.getVertice(0).x, se.getVertice(0).y), new Point(se.getVertice(1).x, se.getVertice(1).y), se.getColour(), se.getThickness(), ShapeType.LINE);
                //    lShapes.add(sl);
                    break;
                case OVAL:  // store the oval
                //    SOval ol = new SOval(new Point(se.getVertice(0).x, se.getVertice(0).y), new Point(se.getVertice(1).x, se.getVertice(1).y), se.getColour(), se.getThickness(), ShapeType.OVAL);
                //    lShapes.add(ol); */
                    break;
                case TRIANGLE:  // store the Triangle
                    List<Point> list= new ArrayList<>();
      /*              list.add(new Point(se.getVertice(0).x, se.getVertice(0).y));
                    list.add(new Point(se.getVertice(1).x, se.getVertice(1).y));      
                    list.add(new Point(se.getVertice(2).x, se.getVertice(2).y));  
                    STriangle st = new STriangle(list, se.getColour(), se.getThickness(), ShapeType.TRIANGLE);
                  lShapes.add(st);*/ 
                    break;
                case RECTANGLE:  // store the rECTANGLE
              /*      List<Point> list2= new ArrayList<>();
                    list2.add(new Point(se.getVertice(0).x, se.getVertice(0).y));
                    list2.add(new Point(se.getVertice(1).x, se.getVertice(1).y));    
                    SRectangle rec = new SRectangle(list2, se.getColour(), se.getThickness(), ShapeType.RECTANGLE); */
                //    lShapes.add(rec);
                    break;
            }
        }
    }

    /**
     *
     * @return the list of line shapes
     */
    public List<Entity> getShapes() {
        return this.lShapes;
    }

    /**
     * This method is used to create some initial data in the JSON file which
     * can then be loaded later
     *
     * @param file the file into which to write the JSON
     */
    private static void generateTestJSON(String file) {
        List<Entity> list = new ArrayList<>();
        // load in some hard-coded shapes
      /*  list.add(new DrawableI(new Point(20, 40),new Point(30, 0), Color.red, 5, ShapeType.LINE));
        list.add(new DrawableI(new Point(20, 40),new Point(70, 90), Color.blue, 5, ShapeType.OVAL));
        list.add(new DrawableI(new Point(80, 95),new Point(70, 45), Color.green, 5, ShapeType.LINE)); */
        listOfShape lS= new listOfShape();
    //    lS.listOfShape = list;
        Gson gson = new Gson();
        String json = gson.toJson(list); // convert the object to a JSON string

        try {
            FileWriter writer = new FileWriter(file);
            writer.write(json); // write the JSON string to file
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main method is just for testing in standalone mode
     *
     * @param args unused
     */
    public static void main(String[] args) throws FileNotFoundException {

//        generateTestJSON("stored_shapes.json"); // uncomment if you wish to 
        // create a file of JSON
        
        // Read the JSON from file and output number of lines and number
        // of ovals read.
        JSONShapeReader me = new JSONShapeReader();
        me.getShapesFromFile("stored_shapes.json");
        System.out.println("Lines loaded = " + me.lShapes.size());
        System.out.println("Ovals loaded = " + me.lShapes.size());

    }


}
