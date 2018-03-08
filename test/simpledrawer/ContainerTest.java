/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpledrawer;

import simpledrawer.shapes.Container;
import simpledrawer.shapes.DrawableI;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;
import simpledrawer.shapes.SLine;
import simpledrawer.shapes.SOval;
import simpledrawer.shapes.SRectangle;
import simpledrawer.shapes.STriangle;

/**
 *
 * @author micae
 */
public class ContainerTest extends TestCase {

    List<Point> listofPoints1 = Arrays.asList(new Point(5, 2), new Point(2, 0));
    List<Point> listofPoints2 = Arrays.asList(new Point(2, 2), new Point(5, 1));
    SRectangle rectangle = new SRectangle(listofPoints1, new Color(0, 0, 0), 2);
    SOval oval = new SOval(listofPoints1, new Color(0, 0, 0), 1);
    SLine line = new SLine(listofPoints1, new Color(0, 0, 0), 1);
    STriangle triangle = new STriangle(listofPoints1, new Color(0, 0, 0), 1);

    public ContainerTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    /**
     * Test of getReorganizedCoords method, of class Container.
     */
    public void testGetReorganizedCoords() {
        System.out.println("getReorganizedCoords");
        //list for the entity contained
        List<Point> expResult = new ArrayList<Point>();
        expResult.add(new Point(2, 0));
        expResult.add(new Point(5, 2));
        Container instance = new Container(rectangle);
        List<Point> result = instance.getReorganizedCoords();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrigin method, of class Container.
     */
    public void testGetOrigin() {
        System.out.println("getOrigin");
        Container instance = new Container(rectangle);
        //2-5=-3 0-5=-5
        Point expResult = new Point(-3, -5);
        Point result = instance.getOrigin();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWidth method, of class Container.
     */
    public void testGetWidth() {
        System.out.println("getWidth");
        Container instance = new Container(oval);
        //{first x 5- second x 2}entity contained width + 10 extra space for containeir
        int expResult = 3 + 10;
        int result = instance.getWidth();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHeight method, of class Container.
     */
    public void testGetHeight() {
        System.out.println("getHeight");
        Container instance = new Container(oval);
        //{first x 2- second x 0}entity contained width + 10 extra space for containeir
        int expResult = 2 + 10;
        int result = instance.getHeight();
        assertEquals(expResult, result);

    }

    /**
     * Test of getEndPoint method, of class Container.
     */
    public void testGetEndPoint() {
        System.out.println("getEndPoint");
        Container instance = new Container(line);
        Point expResult = new Point(-3+instance.getWidth(),-5+instance.getHeight());
        Point result = instance.getEndPoint();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of contains method, of class Container.
     */
    public void testContains() {
        System.out.println("contains");
        Point p = new Point(3,1);
        Container instance = new Container(rectangle);
        boolean expResult = true;
        boolean result = instance.contains(p);
        assertEquals(expResult, result);
    }
    
    public void testContains2() {
        System.out.println("contains");
        Point p = new Point(5,2);
        Container instance = new Container(rectangle);
        boolean expResult = true;
        boolean result = instance.contains(p);
        assertEquals(expResult, result);
    }
    /**
     * Test of contains method, of class Container.
     */
    public void testContains3() {
        System.out.println("contains");
        Point p = new Point(7,8);
        Container instance = new Container(rectangle);
        boolean expResult = false;
        boolean result = instance.contains(p);
        assertEquals(expResult, result);
    }
    /**
     * Test of translate method, of class Container.
     */
    public void testTranslate() {
        System.out.println("translate");
        List<Point> old = null;
        Point offset = null;
        Container instance = null;
        Container expResult = null;
        Container result = instance.updateLocation(old, offset);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resize method, of class Container.
     */
    public void testResize() {
        System.out.println("resize");
        List<Point> old = null;
        Point offSet = null;
        Container instance = null;
        Container expResult = null;
        Container result = instance.resize(old, offSet);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsBottomRightCorner method, of class Container.
     */
    public void testContainsBottomRightCorner() {
        System.out.println("containsBottomRightCorner");
        Point p = new Point(4,1);
        Container instance = new Container(rectangle);
        boolean expResult = true;
        boolean result = instance.containsBottomRightCorner(p);
        assertEquals(expResult, result);
    }
    /**
     * Test of containsBottomRightCorner method, of class Container.
     */
    public void testContainsBottomRightCorner2() {
        System.out.println("containsBottomRightCorner");
        Point p = new Point(7,22);
        Container instance = new Container(rectangle);
        boolean expResult = false;
        boolean result = instance.containsBottomRightCorner(p);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of containsBottomLeftCorner method, of class Container.
     */
    public void testContainsBottomLeftCorner() {
        System.out.println("containsBottomLeftCorner");
        Point p = new Point(2,8);
        Container instance = null;
        boolean expResult = false;
        boolean result = instance.containsBottomLeftCorner(p);
        assertEquals(expResult, result);
    }

        /**
     * Test of containsBottomLeftCorner method, of class Container.
     */
    public void testContainsBottomLeftCorner2() {
        System.out.println("containsBottomLeftCorner");
        Point p = new Point(8,0);
        Container instance = null;
        boolean expResult = false;
        boolean result = instance.containsBottomLeftCorner(p);
        assertEquals(expResult, result);
    }

    
    /**
     * Test of containsTopRightCorner method, of class Container.
     */
    public void testContainsTopRightCorner() {
        System.out.println("containsTopRightCorner");
        Point p = new Point(-10,2);
        Container instance = new Container(rectangle);
        boolean expResult = false;
        boolean result = instance.containsTopRightCorner(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of containsTopLeftCorner method, of class Container.
     */
    public void testContainsTopLeftCorner() {
        System.out.println("containsTopLeftCorner");
        Point p = null ;
        Container instance = null;
        boolean expResult = false;
        boolean result = instance.containsTopLeftCorner(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of contain method, of class Container.
     */
    public void testContain() {
        System.out.println("contain");
        DrawableI entity = null;
        Container instance = null;
        instance.contain(entity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContained method, of class Container.
     */
    public void testGetContained() {
        System.out.println("getContained");
        Container instance = null;
        DrawableI expResult = null;
        DrawableI result = instance.getContained();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Select method, of class Container.
     */
    public void testSelect() {
        System.out.println("Select");
        Container instance = null;
        boolean expResult = false;
        boolean result = instance.Select();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deSelect method, of class Container.
     */
    public void testDeSelect() {
        System.out.println("deSelect");
        Container instance = null;
        boolean expResult = false;
        boolean result = instance.deSelect();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of SelectCorners method, of class Container.
     */
    public void testSelectCorners() {
        System.out.println("SelectCorners");
        Container instance = null;
        boolean expResult = false;
        boolean result = instance.SelectCorners();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deSelectCorners method, of class Container.
     */
    public void testDeSelectCorners() {
        System.out.println("deSelectCorners");
        Container instance = null;
        boolean expResult = false;
        boolean result = instance.deSelectCorners();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of drawResizeIndicator method, of class Container.
     */
    public void testDrawResizeIndicator() {
        System.out.println("drawResizeIndicator");
        Graphics2D g2d = null;
        Point point = null;
        Container instance = null;
        instance.drawResizeIndicator(g2d, point);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDrawableContainer method, of class Container.
     */
    public void testGetDrawableContainer() {
        System.out.println("getDrawableContainer");
        Container instance = null;
        Rectangle expResult = null;
        Rectangle result = instance.getDrawableContainer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of drawShape method, of class Container.
     */
    public void testDrawShape() {
        System.out.println("drawShape");
        Graphics2D g2d = null;
        float currentBrightness = 0.0F;
        Container instance = null;
        instance.drawShape(g2d, currentBrightness);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
