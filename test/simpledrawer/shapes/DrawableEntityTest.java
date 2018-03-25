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
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.EntityType;

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
        DrawableEntity instance = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(100)
                .setWidth(400)
                .setType(EntityType.LINE)
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
        DrawableEntity instance = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(100)
                .setWidth(400)
                .setType(EntityType.LINE)
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
        DrawableEntity instance = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(200)
                .setWidth(400)
                .setType(EntityType.LINE)
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
        DrawableEntity instance = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(200)
                .setWidth(400)
                .setType(EntityType.LINE)
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
        DrawableEntity instance = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(200)
                .setWidth(400)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(2, 2), new Point(2 + 400, 2 - 200)))
                .build();
        Point expResult = new Point(2, 2);
        Point result = instance.getOrigin();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrigin method, of class DrawableEntity.
     */
    public void testGetOrigin2() {
        System.out.println("getOrigin2");
        DrawableEntity instance = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(200)
                .setWidth(400)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(2, 2), new Point(2 + 400, 2 - 200)))
                .build();
        List<Point> newList = Arrays.asList(new Point(1, 1));
        instance.setStructuralPoints(newList);
        Point result = instance.getOrigin();
        assertEquals(newList.get(0), result);
    }

    /**
     * Test of setOrigin method, of class DrawableEntity.
     */
    public void testSetOrigin() {
        System.out.println("setOrigin");
        Point newPoint = new Point(3, 0);
        DrawableEntity instance = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(200)
                .setWidth(400)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(2, 2), new Point(2 + 400, 2 - 200)))
                .build();
        instance.setOrigin(newPoint);
        assertEquals(newPoint, instance.getOrigin());
    }

    /**
     * Test of setOrigin2 method, of class DrawableEntity.
     */
    public void testSetOrigin2() {
        System.out.println("setOrigin2");
        List<Point> newList = Arrays.asList(new Point(1, 1));
        DrawableEntity instance = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(200)
                .setWidth(400)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(2, 2), new Point(2 + 400, 2 - 200)))
                .build();
        instance.setStructuralPoints(newList);
        instance.setOrigin(newList.get(0));
        assertEquals(newList.get(0), instance.getOrigin());
    }

    /**
     * Test of getX method, of class DrawableEntity.
     */
    public void testGetX() {
        System.out.println("getX");
        DrawableEntity instance = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(200)
                .setWidth(400)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(2, 2), new Point(2 + 400, 2 - 200)))
                .build();
        int expResult = 2;
        int result = instance.getX();
        assertEquals(expResult, result);
    }

    /**
     * Test of getX2 method, of class DrawableEntity.
     */
    public void testGetX2() {
        System.out.println("getX2");
        List<Point> newPoints = Arrays.asList(new Point(1, 1));
        DrawableEntity instance = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(200)
                .setWidth(400)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(2, 2), new Point(2 + 400, 2 - 200)))
                .build();
        instance.setStructuralPoints(newPoints);
        int result = instance.getX();
        assertEquals(newPoints.get(0).x, result);
    }

    /**
     * Test of getY method, of class DrawableEntity.
     */
    public void testGetY() {
        System.out.println("getY");
        List<Point> newPoints = Arrays.asList(new Point(1, 1));
        DrawableEntity instance = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(200)
                .setWidth(400)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(2, 2), new Point(2 + 400, 2 - 200)))
                .build();
        instance.setStructuralPoints(newPoints);
        int result = instance.getY();
        assertEquals(newPoints.get(0).y, result);
    }

    /**
     * Test of getY method, of class DrawableEntity.
     */
    public void testGetY2() {
        System.out.println("getY2");
        DrawableEntity instance = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(200)
                .setWidth(400)
                .setType(EntityType.LINE)
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
        DrawableEntity instance = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(200)
                .setWidth(400)
                .setType(EntityType.LINE)
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
        DrawableEntity instance = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(200)
                .setWidth(400)
                .setType(EntityType.LINE)
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
        DrawableEntity instance = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(200)
                .setWidth(400)
                .setType(EntityType.LINE)
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
        DrawableEntity instance = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(200)
                .setWidth(400)
                .setType(EntityType.LINE)
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
        DrawableEntity instance = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(200)
                .setWidth(400)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(3, 3), new Point(3 + 400, 3 - 200)))
                .build();
        EntityType expResult = EntityType.LINE;
        EntityType result = instance.getEntityType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getThickness method, of class DrawableEntity.
     */
    public void testGetThickness() {
        System.out.println("getThickness");
        int expResult = 12;
        DrawableEntity instance = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(200)
                .setWidth(400)
                .setType(EntityType.LINE)
                .setBorderThickness(expResult)
                .setStructuralPoints(Arrays.asList(new Point(3, 3), new Point(3 + 400, 3 - 200)))
                .build();
        int result = instance.getThickness();
        assertEquals(expResult, result);
    }

    /**
     * Test of setThickness method, of class DrawableEntity.
     */
    public void testSetThickness() {
        System.out.println("setThickness");
        int thickness = 30;
        DrawableEntity instance = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(200)
                .setWidth(400)
                .setType(EntityType.LINE)
                .setBorderThickness(12)
                .setStructuralPoints(Arrays.asList(new Point(3, 3), new Point(3 + 400, 3 - 200)))
                .build();
        instance.setThickness(thickness);
        assertEquals(thickness, instance.getThickness());
    }

    /**
     * Test of isItFilled method, of class DrawableEntity.
     */
    public void testIsItFilled() {
        System.out.println("isItFilled");
        boolean expResult = false;
        DrawableEntity instance = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(200)
                .setWidth(400)
                .setType(EntityType.LINE)
                .setBorderThickness(12)
                .setFilled(expResult)
                .setFilledColor(Color.red)
                .setStructuralPoints(Arrays.asList(new Point(3, 3), new Point(3 + 400, 3 - 200)))
                .build();
        boolean result = instance.isItFilled();
        assertEquals(expResult, result);
    }

    /**
     * Test of isItFilled method, of class DrawableEntity.
     */
    public void testIsItFilled2() {
        System.out.println("isItFilled2");
        boolean expResult = true;
        DrawableEntity instance = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(200)
                .setWidth(400)
                .setType(EntityType.LINE)
                .setBorderThickness(12)
                .setFilled(expResult)
                .setFilledColor(Color.red)
                .setStructuralPoints(Arrays.asList(new Point(3, 3), new Point(3 + 400, 3 - 200)))
                .build();
        boolean result = instance.isItFilled();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFilledColor method, of class DrawableEntity.
     */
    public void testGetFilledColor() {
        System.out.println("getFilledColor");
        Color expResult = Color.PINK;
        DrawableEntity instance = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(200)
                .setWidth(400)
                .setType(EntityType.LINE)
                .setBorderThickness(12)
                .setFilled(true)
                .setFilledColor(expResult)
                .setStructuralPoints(Arrays.asList(new Point(3, 3), new Point(3 + 400, 3 - 200)))
                .build();
        Color result = instance.getFilledColor();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFilledColor method, of class DrawableEntity.
     */
    public void testSetFilledColor() {
        System.out.println("setFilledColor");
        Color c = Color.PINK;
        DrawableEntity instance = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(200)
                .setWidth(400)
                .setType(EntityType.LINE)
                .setBorderThickness(12)
                .setFilled(true)
                .setFilledColor(Color.red)
                .setStructuralPoints(Arrays.asList(new Point(3, 3), new Point(3 + 400, 3 - 200)))
                .build();
        instance.setFilledColor(c);
        assertEquals(c , instance.getFilledColor());
    }

}
