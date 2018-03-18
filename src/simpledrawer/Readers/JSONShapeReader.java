package simpledrawer.Readers;

import com.google.gson.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import simpledrawer.DrawableI;
import simpledrawer.shapes.Container.Container;
import simpledrawer.shapes.ShapeFactory;
import simpledrawer.shapes.Shape;

public class JSONShapeReader {

    private static class listOfShapes {

        List<ShapeEvent> listOfShapes;
    }

    private listOfShapes shapesList;
    private List<Shape> lshapes;
    private Gson gson; // gson object used to "parse" the JSON

    public JSONShapeReader() {
        gson = new Gson();
        lshapes = new ArrayList<>();
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
        shapesList = gson.fromJson(br, listOfShapes.class); // load the shapes
        storeShapes(); // store in separate lists according to type
    }

    /**
     * Read through the list of shapes that have been loaded from file and
     * create an appropriate shape object according to type and store it in the
     * relevant list.
     */
    private void storeShapes() {
        //add a null pointer try an catch !!!!URGENT!!!!!!
        for (ShapeEvent se : shapesList.listOfShapes) {
            lshapes.add(ShapeFactory.createShape(
                    new Shape.Builder()
                    .setOrigin(se.getOrigin())
                    .setHeight(se.getHeight())
                    .setWidth(se.getWidth())
                    .setFilledColor(se.getFilledColor())
                    .setBorderThickness(se.getThickness())
                    .setType(se.type())
                    .build()));

        }
    }

    /**
     * @return the list of line shapes
     */
    public List<Shape> getShapes() {
        return this.lshapes;
    }

    /**
     * This method is used to create some initial data in the JSON file which
     * can then be loaded later
     *
     * @param file the file into which to write the JSON
     * @param drawings
     */
    public void saveJSON(String file, HashMap<Integer, DrawableI> drawings) {
        List<ShapeEvent> list = new ArrayList<>();
        System.out.println("" + drawings.size());
        for (int i = 0; i < drawings.size(); i++) {
            if (drawings.get(i) instanceof Container) {
                Container container = (Container) drawings.get(i);
                list.add(new ShapeEvent((Shape) container.getContained()));
            }
        }
        listOfShapes lS = new listOfShapes();
        lS.listOfShapes = list;

        Gson gson = new Gson();
        String json = gson.toJson(lS); // convert the object to a JSON string

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
        /*
         * generateTestJSON("stored_shapes.json"); // uncomment if you wish to 
         * Create a file of JSON
         * Read the JSON from file and output number of lines and number
         * of ovals read.
         */
        JSONShapeReader me = new JSONShapeReader();
        me.getShapesFromFile("stored_shapes.json");
        System.out.println("Lines loaded = " + me.lshapes.size());
        System.out.println("Ovals loaded = " + me.lshapes.size());

    }

}
