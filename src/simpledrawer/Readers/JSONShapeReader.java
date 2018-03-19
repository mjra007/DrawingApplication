package simpledrawer.Readers;

import com.google.gson.*;
import drawingpanel.DrawableI;
import drawingpanel.DrawingPanel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import simpledrawer.shapes.Container.Container;
import simpledrawer.shapes.DrawableEntity;
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
    public void getShapesFromFile(String file, DrawingPanel canvas) throws FileNotFoundException {

        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(file));
            System.out.println("" + file);
            shapesList = gson.fromJson(br, listOfShapes.class); // load the shapes
            br.close();
            storeShapes(canvas); // store in separate lists according to type

        } catch (IOException ex) {
            Logger.getLogger(JSONShapeReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Read through the list of shapes that have been loaded from file and
     * create an appropriate shape object according to type and store it in the
     * relevant list.
     */
    private void storeShapes(DrawingPanel canvas) {
        //add a null pointer try an catch !!!!URGENT!!!!!!
        for (ShapeEvent se : shapesList.listOfShapes) {
             canvas.addDrawing(ShapeFactory.createShape(
                    new Shape.Builder()
                            .setOrigin(se.getOrigin())
                            .setHeight(se.getHeight())
                            .setWidth(se.getWidth())
                            .setColor(se.getColor())
                            .setFilled(se.getFilled())
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

    public void saveJSON(String file, HashMap<Integer, DrawableI> drawings) {
        List<ShapeEvent> list = new ArrayList<>();
        for (int i = 0; i < drawings.size(); i++) {
            if (drawings.get(i) instanceof Container) {
                Container container = (Container) drawings.get(i);
                DrawableEntity de = (DrawableEntity) container.getContained();
                System.out.println("" + i);
                list.add(new ShapeEvent((Shape) de));
            }
        }
        System.out.println("list:"+list.size());
        listOfShapes lS = new listOfShapes();
        lS.listOfShapes = list;

        Gson gson = new Gson();
        String json = gson.toJson(lS); // convert the object to a JSON string
        //System.out.println(""+json);
        try {
            File filePath = new File(file);
            filePath.delete();
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(file));
            writer.write(json); // write the JSON string to file
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
