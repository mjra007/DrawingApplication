/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpledrawer.shapes.Container;

import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.Container.Container;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.InteractiveShape;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.DrawableEntity;

/**
 *
 * @author micae
 */
public class ContainerTest extends TestCase {

    public ContainerTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    /**
     * Test of getOrigin method, of class Container.
     */
    public void testGetOrigin() {
        System.out.println("getOrigin");
        Container instance = new Container(new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(100)
                .setWidth(400)
                .setType(DrawableEntity.EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(10, 10), new Point(10 + 400, 10 - 200)))
                .build());
        Point expResult = new Point(5, 5);
        Point result = instance.getOrigin();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWidth method, of class Container.
     */
    public void testGetWidth() {
        System.out.println("getWidth");
        Container instance = new Container(new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(100)
                .setWidth(400)
                .setType(DrawableEntity.EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(10, 10), new Point(10 + 400, 10 - 200)))
                .build());
        int expResult = 400 + 10;
        int result = instance.getWidth();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHeight method, of class Container.
     */
    public void testGetHeight() {
        System.out.println("getHeight");
        Container instance = new Container(new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(100)
                .setWidth(400)
                .setType(DrawableEntity.EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(10, 10), new Point(10 + 400, 10 - 200)))
                .build());
        int expResult = 100 + 10;
        int result = instance.getHeight();
        assertEquals(expResult, result);
    }

    /**
     * Test of setHeight method, of class Container.
     */
    public void testSetHeight() {
        System.out.println("setHeight");
        int height = 100 + 10;
        Container instance = new Container(new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(100)
                .setWidth(400)
                .setType(DrawableEntity.EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(10, 10), new Point(10 + 400, 10 - 200)))
                .build());
        instance.setHeight(height);
        assertEquals(height, instance.getHeight());
    }

    /**
     * Test of setWidth method, of class Container.
     */
    public void testSetWidth() {
        System.out.println("setWidth");
        int width = 200 + 10;
        Container instance = new Container(new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(100)
                .setWidth(400)
                .setType(DrawableEntity.EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(10, 10), new Point(10 + 400, 10 - 200)))
                .build());
        instance.setWidth(width);
        assertEquals(width, instance.getWidth());
    }

    /**
     * Test of getEndPoint method, of class Container.
     */
    public void testGetEndPoint() {
        System.out.println("getEndPoint");
        Container instance = new Container(new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(100)
                .setWidth(400)
                .setType(DrawableEntity.EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(10, 10), new Point(10 + 400, 10 +100)))
                .build());
        Point expResult = new Point((10 + 400)+5, (100 + 10)+5);
        Point result = instance.getEndPoint();
        assertEquals(expResult, result);
    }

    /**
     * Test of contains method, of class Container.
     */
    public void testContains_Point() {
        System.out.println("contains");
        Point p = null;
        Container instance = null;
        boolean expResult = false;
        boolean result = instance.contains(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateLocation method, of class Container.
     */
    public void testUpdateLocation() {
        System.out.println("updateLocation");
        Point old = null;
        Point offset = null;
        Container instance = null;
        Container expResult = null;
        Container result = instance.updateLocation(old, offset);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of contains method, of class Container.
     */
    public void testContains_Point_Rectangle() {
        System.out.println("contains");
        Point p = null;
        Rectangle r = null;
        Container instance = null;
        boolean expResult = false;
        boolean result = instance.contains(p, r);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rotate method, of class Container.
     */
    public void testRotate_List_Point() {
        System.out.println("rotate");
        List<Point> old = null;
        Point offset = null;
        Container instance = null;
        Container expResult = null;
        Container result = instance.rotate(old, offset);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rightSideContains method, of class Container.
     */
    public void testRightSideContains() {
        System.out.println("rightSideContains");
        Point p = null;
        Container instance = null;
        boolean expResult = false;
        boolean result = instance.rightSideContains(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of bottomSideContains method, of class Container.
     */
    public void testBottomSideContains() {
        System.out.println("bottomSideContains");
        Point p = null;
        Container instance = null;
        boolean expResult = false;
        boolean result = instance.bottomSideContains(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of topRightCornerContains method, of class Container.
     */
    public void testTopRightCornerContains() {
        System.out.println("topRightCornerContains");
        Point p = null;
        Container instance = null;
        boolean expResult = false;
        boolean result = instance.topRightCornerContains(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of topLeftCornerContains method, of class Container.
     */
    public void testTopLeftCornerContains() {
        System.out.println("topLeftCornerContains");
        Point p = null;
        Container instance = null;
        boolean expResult = false;
        boolean result = instance.topLeftCornerContains(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of bottomRightCornerContains method, of class Container.
     */
    public void testBottomRightCornerContains() {
        System.out.println("bottomRightCornerContains");
        Point p = null;
        Container instance = null;
        boolean expResult = false;
        boolean result = instance.bottomRightCornerContains(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of bottomLeftCornerContains method, of class Container.
     */
    public void testBottomLeftCornerContains() {
        System.out.println("bottomLeftCornerContains");
        Point p = null;
        Container instance = null;
        boolean expResult = false;
        boolean result = instance.bottomLeftCornerContains(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of linearResizing method, of class Container.
     */
    public void testLinearResizing() {
        System.out.println("linearResizing");
        float amount = 0.0F;
        Container instance = null;
        Container expResult = null;
        Container result = instance.linearResizing(amount);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resize method, of class Container.
     */
    public void testResize() {
        System.out.println("resize");
        Dimension old = null;
        Point offset = null;
        InteractiveShape.SelectedPart part = null;
        Container instance = null;
        Container expResult = null;
        Container result = instance.resize(old, offset, part);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStructuralPoints method, of class Container.
     */
    public void testGetStructuralPoints() {
        System.out.println("getStructuralPoints");
        Container instance = null;
        List<Point> expResult = null;
        List<Point> result = instance.getStructuralPoints();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rotate method, of class Container.
     */
    public void testRotate_float() {
        System.out.println("rotate");
        float amount = 0.0F;
        Container instance = null;
        Container expResult = null;
        Container result = instance.rotate(amount);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOrigin method, of class Container.
     */
    public void testSetOrigin() {
        System.out.println("setOrigin");
        Point p = null;
        Container instance = null;
        instance.setOrigin(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContained method, of class Container.
     */
    public void testGetContained() {
        System.out.println("getContained");
        Container instance = null;
        DrawableEntity expResult = null;
        DrawableEntity result = instance.getContained();
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
     * Test of setSelect method, of class Container.
     */
    public void testSetSelect() {
        System.out.println("setSelect");
        Boolean b = null;
        Container instance = null;
        instance.setSelect(b);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deSelect method, of class Container.
     */
    public void testDeSelect() {
        System.out.println("deSelect");
        Container instance = null;
        instance.deSelect();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of SelectCorners method, of class Container.
     */
    public void testSelectCorners() {
        System.out.println("SelectCorners");
        Container instance = null;
        instance.SelectCorners();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deSelectCorners method, of class Container.
     */
    public void testDeSelectCorners() {
        System.out.println("deSelectCorners");
        Container instance = null;
        instance.deSelectCorners();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isSelected method, of class Container.
     */
    public void testIsSelected() {
        System.out.println("isSelected");
        Container instance = null;
        boolean expResult = false;
        boolean result = instance.isSelected();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of areCornersSelected method, of class Container.
     */
    public void testAreCornersSelected() {
        System.out.println("areCornersSelected");
        Container instance = null;
        boolean expResult = false;
        boolean result = instance.areCornersSelected();
        assertEquals(expResult, result);
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
     * Test of draw method, of class Container.
     */
    public void testDrawShape() {
        System.out.println("drawShape");
        Graphics2D g2d = null;
        Container instance = null;
        instance.draw(g2d);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class Container.
     */
    public void testClone() {
        System.out.println("clone");
        Container instance = null;
        Object expResult = null;
        Object result = instance.clone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
