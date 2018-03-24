/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpledrawer.shapes;

import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.DrawableEntity;
import com.rits.cloning.Cloner;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.DrawableEntity.EntityType;

/**
 *
 * @author micae
 */
public class DrawableEntityTest extends TestCase {

    public DrawableEntityTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    /**
     * Test of getWidth method, of class DrawableEntity.
     */
    public void testGetWidth() {
        System.out.println("getWidth");
        DrawableEntity instance = new DrawableEntity.Builder(structuralPoints, height, width, color, filledColor, borderThickness, filled, type)
                .setColor(Color.yellow)
                .setHeight(100)
                .setWidth(400)
                .setType(DrawableEntity.EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(2, 2)))
                .build();
        int expResult = 400;
        int result = instance.getWidth();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHeight method, of class DrawableEntity.
     */
    public void testGetHeight() {
        System.out.println("getHeight");
        DrawableEntity instance = new DrawableEntity.Builder(structuralPoints, height, width, color, filledColor, borderThickness, filled, type)
                .setColor(Color.yellow)
                .setHeight(100)
                .setWidth(400)
                .setType(DrawableEntity.EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(2, 2)))
                .build();
        int expResult = 100;
        int result = instance.getHeight();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setWidth method, of class DrawableEntity.
     */
    public void testSetWidth() {
        System.out.println("setWidth");
        int newWidth = 300;
        DrawableEntity instance = new DrawableEntity.Builder(structuralPoints, height, width, color, filledColor, borderThickness, filled, type)
                .setColor(Color.yellow)
                .setHeight(200)
                .setWidth(400)
                .setType(DrawableEntity.EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(2, 2), new Point(2 + 400, 2 - 200)))
                .build();
        instance.setWidth(newWidth);
        assertEquals(newWidth, instance.getWidth());
    }

    /**
     * Test of setHeight method, of class DrawableEntity.
     */
    public void testSetHeight() {
        System.out.println("setHeight");
        int newHeight = 2;
        DrawableEntity instance = new DrawableEntity.Builder(structuralPoints, height, width, color, filledColor, borderThickness, filled, type)
                .setColor(Color.yellow)
                .setHeight(200)
                .setWidth(400)
                .setType(DrawableEntity.EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(2, 2), new Point(2 + 400, 2 - 200)))
                .build();
        instance.setHeight(newHeight);
        assertEquals(newHeight, instance.getHeight());
    }

    /**
     * Test of getOrigin method, of class DrawableEntity.
     */
    public void testGetOrigin() {
        System.out.println("getOrigin");
        DrawableEntity instance = new DrawableEntity.Builder(structuralPoints, height, width, color, filledColor, borderThickness, filled, type)
                .setColor(Color.yellow)
                .setHeight(200)
                .setWidth(400)
                .setType(DrawableEntity.EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(2, 2), new Point(2 + 400, 2 - 200)))
                .build();
        Point expResult = new Point(2, 2);
        Point result = instance.getOrigin();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOrigin method, of class DrawableEntity.
     */
    public void testSetOrigin() {
        System.out.println("setOrigin");
        Point newPoint = new Point(3, 0);
        DrawableEntity instance = new DrawableEntity.Builder(structuralPoints, height, width, color, filledColor, borderThickness, filled, type)
                .setColor(Color.yellow)
                .setHeight(200)
                .setWidth(400)
                .setType(DrawableEntity.EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(2, 2), new Point(2 + 400, 2 - 200)))
                .build();
        instance.setOrigin(newPoint);
        assertEquals(newPoint, instance.getOrigin());
    }

    /**
     * Test of getX method, of class DrawableEntity.
     */
    public void testGetX() {
        System.out.println("getX");
        DrawableEntity instance = new DrawableEntity.Builder(structuralPoints, height, width, color, filledColor, borderThickness, filled, type)
                .setColor(Color.yellow)
                .setHeight(200)
                .setWidth(400)
                .setType(DrawableEntity.EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(2, 2), new Point(2 + 400, 2 - 200)))
                .build();
        int expResult = 2;
        int result = instance.getX();
        assertEquals(expResult, result);
    }

    /**
     * Test of getY method, of class DrawableEntity.
     */
    public void testGetY() {
        System.out.println("getY");
        DrawableEntity instance = new DrawableEntity.Builder(structuralPoints, height, width, color, filledColor, borderThickness, filled, type)
                .setColor(Color.yellow)
                .setHeight(200)
                .setWidth(400)
                .setType(DrawableEntity.EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(2, 2), new Point(2 + 400, 2 - 200)))
                .build();
        int expResult = 2;
        int result = instance.getY();
        assertEquals(expResult, result);
    }

    /**
     * Test of getColor method, of class DrawableEntity.
     */
    public void testGetColor() {
        System.out.println("getColor");
        DrawableEntity instance = new DrawableEntity.Builder(structuralPoints, height, width, color, filledColor, borderThickness, filled, type)
                .setColor(Color.yellow)
                .setHeight(200)
                .setWidth(400)
                .setType(DrawableEntity.EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(2, 2), new Point(2 + 400, 2 - 200)))
                .build();
        Color expResult = Color.yellow;
        Color result = instance.getColor();
        assertEquals(expResult, result);
    }

    /**
     * Test of setColor method, of class DrawableEntity.
     */
    public void testSetColor() {
        System.out.println("setColor");
        Color newColor = Color.BLACK;
        DrawableEntity instance = new DrawableEntity.Builder(structuralPoints, height, width, color, filledColor, borderThickness, filled, type)
                .setColor(Color.yellow)
                .setHeight(200)
                .setWidth(400)
                .setType(DrawableEntity.EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(2, 2), new Point(2 + 400, 2 - 200)))
                .build();
        instance.setColor(newColor);
        assertEquals(newColor, instance.getColor());
    }

    /**
     * Test of setStructuralPoints method, of class DrawableEntity.
     */
    public void testSetStructuralPoints() {
        System.out.println("setStructuralPoints");
        List<Point> list = Arrays.asList(new Point(3, 3), new Point(3 + 400, 3 - 200));
        DrawableEntity instance = new DrawableEntity.Builder(structuralPoints, height, width, color, filledColor, borderThickness, filled, type)
                .setColor(Color.yellow)
                .setHeight(200)
                .setWidth(400)
                .setType(DrawableEntity.EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(2, 2), new Point(2 + 400, 2 - 200)))
                .build();
        instance.setStructuralPoints(list);
        assertEquals(list, instance.getStructuralPoints());
    }

    /**
     * Test of getStructuralPoints method, of class DrawableEntity.
     */
    public void testGetStructuralPoints() {
        System.out.println("getStructuralPoints");
        DrawableEntity instance = new DrawableEntity.Builder(structuralPoints, height, width, color, filledColor, borderThickness, filled, type)
                .setColor(Color.yellow)
                .setHeight(200)
                .setWidth(400)
                .setType(DrawableEntity.EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(3, 3), new Point(3 + 400, 3 - 200)))
                .build();
        List<Point> expResult = Arrays.asList(new Point(3, 3), new Point(3 + 400, 3 - 200));;
        List<Point> result = instance.getStructuralPoints();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEntityType method, of class DrawableEntity.
     */
    public void testGetEntityType() {
        System.out.println("getEntityType");
        System.out.println("getStructuralPoints");
        DrawableEntity instance = new DrawableEntity.Builder(structuralPoints, height, width, color, filledColor, borderThickness, filled, type)
                .setColor(Color.yellow)
                .setHeight(200)
                .setWidth(400)
                .setType(DrawableEntity.EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(3, 3), new Point(3 + 400, 3 - 200)))
                .build();
        DrawableEntity.EntityType expResult = EntityType.LINE;
        DrawableEntity.EntityType result = instance.getEntityType();
        assertEquals(expResult, result);
    }

}
