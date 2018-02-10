/*
 * XMLShapeREader.java
 *
 * @author Gill Windall
 *
 * Used to read shapes from a file stored in XML format using 
 * the class ShapeEventGeneratorFromXML
 *
 */
package simpledrawer.Readers;

import simpledrawer.shapes.SOval;
import simpledrawer.shapes.SLine;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import simpledrawer.Entity;
import simpledrawer.shapes.SRectangle;
import simpledrawer.shapes.ShapeType;
import simpledrawer.shapes.STriangle;

public class XMLShapeReader {

    private ShapeEventGeneratorFromXML segfx; // used to read the data

    private List<Entity> shapeList; // list of lines

    public XMLShapeReader() throws ParserConfigurationException, SAXException {

        try {
            segfx = new ShapeEventGeneratorFromXML();
        } catch (ParserConfigurationException | SAXException ex) {
            Logger.getLogger(XMLShapeReader.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

        // Create a listerner to receive callbacks from the
        // ShapeEventGeneratorFromXML object each time a Shape has
        // been read from file.
        ShapeEventListener sel = new ShapeEventListener() {
            /**
             *
             * @param originator unused
             * @param se the shape event object containing details of the shape
             * that has just been read from file.
             */
            @Override
            public void processShapeEvent(Object originator, Entity se) {
                storeShape(se);
            }
        };
        segfx.registerShapeEventListener("SHAPE", sel);
        shapeList = new ArrayList<>();
        shapeList = new ArrayList<>();
    }

    /**
     * Called to initiate the processing of the XML file containing shapes.
     *
     * @param file the name of the XML file to process
     * @throws IOException
     * @throws SAXException
     */
    public void getShapesFromFile(String file) throws IOException, SAXException {
        segfx.processScriptFile(file);
    }

    /**
     * Called each time a shape has been read. The shape is stored in the
     * appropriate list according to type.
     *
     * @param se the ShapeEvent object containing details of the shape to be
     * stored.
     */
    private void storeShape(Entity se) {
        switch (se.getShapeType()) {
            case LINE: // store the line
           //     SLine sl = new SLine(new Point(se.getVertice(0).x, se.getVertice(0).y), new Point(se.getVertice(1).x, se.getVertice(1).y), se.getColour(), se.getThickness(), ShapeType.LINE);
            //    shapeList.add(sl);
                break;
            case OVAL: // store the oval
         //       SOval ol = new SOval(new Point(se.getVertice(0).x, se.getVertice(0).y), new Point(se.getVertice(1).x, se.getVertice(1).y), se.getColour(), se.getThickness(), ShapeType.OVAL);
         //       shapeList.add(ol);
                break;
            case TRIANGLE:  // store the Triangle
           /*     List<Point> list = new ArrayList<>();
                list.add(new Point(se.getVertice(0).x, se.getVertice(0).y));
                list.add(new Point(se.getVertice(1).x, se.getVertice(1).y));
                list.add(new Point(se.getVertice(2).x, se.getVertice(2).y));
                STriangle st = new STriangle(list, se.getColour(), se.getThickness(), ShapeType.TRIANGLE);
                shapeList.add(st);*/
                break;
            case RECTANGLE:  // store the rECTANGLE
           /*     List<Point> list2 = new ArrayList<>();
                list2.add(new Point(se.getVertice(0).x, se.getVertice(0).y));
                list2.add(new Point(se.getVertice(1).x, se.getVertice(1).y));
                SRectangle rec = new SRectangle(list2, se.getColour(), se.getThickness(), ShapeType.RECTANGLE);
                shapeList.add(rec);*/
                break;
        }
    }

    /**
     *
     * @return the list of line shapes
     */
    public List<Entity> getSlList() {
        return shapeList;
    }

    /**
     * Main method is just for testing in standalone mode
     *
     * @param args unused
     */
    public static void main(String[] args) {
        try {
            XMLShapeReader me = new XMLShapeReader();

            me.getShapesFromFile("stored_shapes.xml");
            System.out.println("number of lines: " + me.shapeList.size());
            System.out.println("number of ovals: " + me.shapeList.size());

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(XMLShapeReader.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
