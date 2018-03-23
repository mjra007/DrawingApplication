package uk.ac.gre.ma8521e.simpledrawer.Readers;

import com.google.gson.*;
import drawingpanel.DrawableI;
import drawingpanel.DrawingPanel;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.Container.Container;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.DrawableEntity;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.Shapes.ShapeFactory;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.Shapes.Shape;

public class JSONShapeReader {

    private static class listData {

        List<ShapeEvent> listOfShapes;
        List<CanvasOptionsEvent> listOfOptions;
    }

    private listData listData;
    private Gson gson; // gson object used to "parse" the JSON
    private List<DrawableI> drawables;
    private Color background;

    public JSONShapeReader() {
        gson = new Gson();
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
            listData = gson.fromJson(br, listData.class); // load the shapes
            br.close();
            storeData(); // store in separate lists according to type
        } catch (IOException ex) {
            Logger.getLogger(JSONShapeReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Read through the list of shapes that have been loaded from file and
     * create an appropriate shape object according to type and store it in the
     * relevant list.
     */
    private void storeData() {
        //add a null pointer try an catch !!!!URGENT!!!!!!
        this.drawables = new ArrayList<>();
        for (ShapeEvent se : listData.listOfShapes) {
            Shape shape = ShapeFactory.createShape(
                    new Shape.Builder()
                            .setOrigin(se.getOrigin())
                            .setHeight(se.getHeight())
                            .setWidth(se.getWidth())
                            .setColor(se.getColor())
                            .setFilled(se.getFilled())
                            .setFilledColor(se.getFilledColor())
                            .setBorderThickness(se.getThickness())
                            .setType(se.type())
                            .build());
            drawables.add(shape.contain(shape));
        }
        this.background = listData.listOfOptions.get(0).getBackground();
    }

    /**
     * @return the list of line shapes
     */
    public List<DrawableI> getDrawings() {
        return this.drawables;
    }

    public Color getBackground() {
        return this.background;
    }

    public void saveJSON(String file, DrawingPanel dp) {
        List<ShapeEvent> shapeList = new ArrayList<>();
        List<CanvasOptionsEvent> optionsList = new ArrayList<>();
        if(dp.getDrawings().isEmpty()){
            System.out.println("it is empty");    
        }
        System.out.println(""+dp.getDrawings().toString());
        for (DrawableI drawings: dp.getDrawings().values()) {
            if(drawings instanceof Container){
                Container container = (Container) drawings;
                DrawableEntity de = (DrawableEntity) container.getContained();
                shapeList.add(new ShapeEvent((Shape) de));
            }
        }
        optionsList.add(new CanvasOptionsEvent(dp.getBackground()));
        listData dataList = new listData();
        dataList.listOfShapes = shapeList;
        dataList.listOfOptions = optionsList;

        Gson gson = new Gson();
        String json = gson.toJson(dataList); // convert the object to a JSON string
        
        try {
            File filePath = new File(file);
            filePath.delete();
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(file));
            writer.write(toPrettyFormat(json)); // write the JSON string to file
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    /**
     * Credits to https://coderwall.com/p/ab5qha/convert-json-string-to-pretty-print-java-gson
     * @param jsonString
     * @return 
     */
    public String toPrettyFormat(String jsonString) {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(jsonString).getAsJsonObject();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(json);

        return prettyJson;
    }

}
