/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.Container;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.DrawableEntity;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.EntityType;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.InteractiveShapeI;

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
        ContainerI drawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(100)
                .setWidth(400)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(10, 10), new Point(10 + 400, 10 - 200)))
                .build();
        Container instance = drawable.contain();
        Point expResult = new Point(5, 5);
        Point result = instance.getOrigin();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrigin method, of class Container.
     */
    public void testGetOrigin2() {
        System.out.println("getOrigin2");
        Point expResult = new Point(-15, -15);
        ContainerI drawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(100)
                .setWidth(400)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(-10, -10), new Point(-10 + 400, -10 - 200)))
                .build();
        Container instance = drawable.contain();
        Point result = instance.getOrigin();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWidth method, of class Container.
     */
    public void testGetWidth() {
        System.out.println("getWidth");
        ContainerI drawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(100)
                .setWidth(400)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(-10, -10), new Point(-10 + 400, -10 - 200)))
                .build();
        Container instance = drawable.contain();
        int expResult = 400 + 10;
        int result = instance.getWidth();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWidth method, of class Container.
     */
    public void testGetWidth2() {
        System.out.println("getWidth2");
        ContainerI drawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(100)
                .setWidth(-400)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(-10, -10), new Point(-10 + -400, -10 - 200)))
                .build();
        Container instance = drawable.contain();
        int expResult = -400 + 10;
        int result = instance.getWidth();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHeight method, of class Container.
     */
    public void testGetHeight() {
        System.out.println("getHeight");
        ContainerI drawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(100)
                .setWidth(-400)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(-10, -10), new Point(-10 + -400, -10 - 200)))
                .build();
        Container instance = drawable.contain();
        int expResult = 100 + 10;
        int result = instance.getHeight();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHeight method, of class Container.
     */
    public void testGetHeight2() {
        System.out.println("getHeight");
        ContainerI drawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(-100)
                .setWidth(-400)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(-10, -10), new Point(-10 + -400, -10 - 100)))
                .build();
        Container instance = drawable.contain();
        int expResult = -100 + 10;
        int result = instance.getHeight();
        assertEquals(expResult, result);
    }

    /**
     * Test of setHeight method, of class Container.
     */
    public void testSetHeight() {
        System.out.println("setHeight");
        int height = 100 + 10;
        ContainerI drawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(100)
                .setWidth(400)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(10, 10), new Point(10 + 400, 10 - 200)))
                .build();
        Container instance = drawable.contain();
        instance.setHeight(height);
        assertEquals(height - 10, instance.getContained().getHeight());
        assertEquals(height, instance.getHeight());
    }

    /**
     * Test of setHeight method, of class Container.
     */
    public void testSetHeight2() {
        System.out.println("setHeight2");
        int height = -100 + 10;
        ContainerI drawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(-100)
                .setWidth(400)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(10, 10), new Point(10 + 400, 10 - (-100))))
                .build();
        Container instance = drawable.contain();
        instance.setHeight(height);
        assertEquals(height - 10, instance.getContained().getHeight());
        assertEquals(height, instance.getHeight());
    }

    /**
     * Test of setWidth method, of class Container.
     */
    public void testSetWidth() {
        System.out.println("setWidth");
        int width = -200 + 10;
        ContainerI drawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(100)
                .setWidth(200)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(10, 10), new Point(10 + 200, 10 - (100))))
                .build();
        Container instance = drawable.contain();
        instance.setWidth(width);
        assertEquals(width, instance.getWidth());
    }

    /**
     * Test of setWidth method, of class Container.
     */
    public void testSetWidth2() {
        System.out.println("setWidth");
        int width = 200 + 10;
        ContainerI drawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(100)
                .setWidth(200)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(10, 10), new Point(10 + 200, 10 - 100)))
                .build();
        Container instance = drawable.contain();
        instance.setWidth(width);
        assertEquals(width, instance.getWidth());
    }

    /**
     * Test of getEndPoint method, of class Container.
     */
    public void testGetEndPoint() {
        System.out.println("getEndPoint");
        ContainerI drawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(100)
                .setWidth(400)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(10, 10), new Point(10 + 200, 10 - 100)))
                .build();
        Container instance = drawable.contain();
        Point expResult = new Point((10 + 400) + 5, (100 + 10) + 5);
        Point result = instance.getEndPoint();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEndPoint method, of class Container.
     */
    public void testGetEndPoint2() {
        System.out.println("getEndPoint2");
        ContainerI drawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(-100)
                .setWidth(-400)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(10, 10), new Point(10 + (-400), 10 + (-100))))
                .build();
        Container instance = drawable.contain();
        Point expResult = new Point((10 + (-400)) + 5, ((-100) + 10) + 5);
        Point result = instance.getEndPoint();
        assertEquals(expResult, result);
    }

    /**
     * Test of contains method, of class Container.
     */
    public void testContains_Point() {
        System.out.println("contains");
        Point p = new Point(12, 8);
        ContainerI drawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(-100)
                .setWidth(-400)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(10, 10), new Point(10 + (-400), 10 + (-100))))
                .build();
        Container instance = drawable.contain();
        boolean expResult = false;
        boolean result = instance.contains(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of contains method, of class Container.
     */
    public void testContains_Point2() {
        System.out.println("contains2");
        Point p = new Point(-11, -8);
        ContainerI drawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(-100)
                .setWidth(-400)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(10, 10), new Point(10 + (-400), 10 + (-100))))
                .build();
        Container instance = drawable.contain();
        boolean expResult = true;
        boolean result = instance.contains(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateLocation method, of class Container.
     */
    public void testUpdateLocation() {
        System.out.println("updateLocation");
        ContainerI drawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(-100)
                .setWidth(-400)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(10, 10), new Point(10 + (-400), 10 + (-100))))
                .build();
        Container instance = drawable.contain();
        ContainerI finalDrawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(-100)
                .setWidth(-400)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(20, 20), new Point(20 + (-400), 20 + (-100))))
                .build();
        Point old = new Point(10, 10);
        Point offset = new Point(10, 10);
        Point expResult = finalDrawable.contain().getOrigin();
        Point result = instance.updateLocation(old, offset).getOrigin();
        assertEquals(expResult, result);
    }

    /**
     * Test of updateLocation method, of class Container.
     */
    public void testUpdateLocation2() {
        System.out.println("updateLocation2");
        ContainerI drawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(-100)
                .setWidth(-400)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(-10, -10), new Point(-10 + (-400), -10 + (-100))))
                .build();
        Container instance = drawable.contain();
        ContainerI finalDrawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(-100)
                .setWidth(-400)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(20, 20), new Point(20 + (-400), 20 + (-100))))
                .build();
        Point old = new Point(-10, -10);
        Point offset = new Point(30, 30);
        Point expResult = finalDrawable.contain().getOrigin();
        Point result = instance.updateLocation(old, offset).getOrigin();
        assertEquals(expResult, result);
    }

    /**
     * Test of contains method, of class Container.
     */
    public void testContains_Point_Rectangle() {
        System.out.println("contains");
        Point p = new Point(25, 145);
        Rectangle r = new Rectangle();
        r.setBounds(20, 10, 450, 150);
        ContainerI drawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(150)
                .setWidth(450)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(20, 10), new Point(20 + (450), 10 + (150))))
                .build();
        Container instance = drawable.contain();
        boolean expResult = true;
        boolean result = instance.contains(p, r);
        assertEquals(expResult, result);
    }

    /**
     * Test of contains method, of class Container.
     */
    public void testContains_Point_Rectangle2() {
        System.out.println("contains2");
        Point p = new Point(-130, -150);
        Rectangle r = new Rectangle();
        r.setBounds(-20, -10, -450, -150);
        ContainerI drawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(150)
                .setWidth(450)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(-20, -10), new Point(-10 + (-450), -10 + (-150))))
                .build();
        Container instance = drawable.contain();
        boolean expResult = false;
        boolean result = instance.contains(p, r);
        assertEquals(expResult, result);
    }

    /**
     * Test of contains method, of class Container.
     */
    public void testContains_Point_Rectangle3() {
        System.out.println("contains3");
        Point p = new Point(-100, -12222);
        Rectangle r = new Rectangle();
        r.setBounds(-20, -10, -450, -150);
        ContainerI drawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(-150)
                .setWidth(-450)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(-20, -10), new Point(-20 + (-450), -10 + (-150))))
                .build();
        Container instance = drawable.contain();
        boolean expResult = false;
        boolean result = instance.contains(p, r);
        assertEquals(expResult, result);
    }

    /**
     * Test of rotate method, of class Container.
     */
    public void testRotate_List_Point() {
        System.out.println("rotate");
        System.out.println("Rotate wasnt implemented yet");
    }

    /**
     * Test of rightSideContains method, of class Container.
     */
    public void testRightSideContains() {
        System.out.println("rightSideContains");
        Point p = new Point(20 + 452, 125);
        ContainerI drawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(150)
                .setWidth(450)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(20, 10), new Point(20 + (450), 10 + (150))))
                .build();
        Container instance = drawable.contain();
        boolean expResult = true;
        boolean result = instance.rightSideContains(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of rightSideContains method, of class Container.
     */
    public void testRightSideContains2() {
        System.out.println("rightSideContains2");
        Point p = new Point(20 + -452, -125);
        ContainerI drawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(-150)
                .setWidth(-450)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(20, 10), new Point(20 + (450), 10 + (150))))
                .build();
        Container instance = drawable.contain();
        boolean expResult = false;
        boolean result = instance.rightSideContains(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of rightSideContains method, of class Container.
     */
    public void testRightSideContains3() {
        System.out.println("rightSideContains3");
        Point p = new Point(-470, -120);
        ContainerI drawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(-150)
                .setWidth(-450)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(-20, -10), new Point(-20 + (-450), -10 + (-150))))
                .build();
        Container instance = drawable.contain();
        boolean expResult = true;
        boolean result = instance.rightSideContains(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of bottomSideContains method, of class Container.
     */
    public void testBottomSideContains() {
        System.out.println("bottomSideContains");
        Point p = new Point(20, 160);
        ContainerI drawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(150)
                .setWidth(450)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(20, 10), new Point(20 + (450), 10 + (150))))
                .build();
        Container instance = drawable.contain();
        boolean expResult = true;
        boolean result = instance.bottomSideContains(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of bottomSideContains method, of class Container.
     */
    public void testBottomSideContains2() {
        System.out.println("bottomSideContains2");
        Point p = new Point(-20, 160);
        ContainerI drawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(150)
                .setWidth(450)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(20, 10), new Point(20 + (450), 10 + (150))))
                .build();
        Container instance = drawable.contain();
        boolean expResult = false;
        boolean result = instance.bottomSideContains(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of bottomSideContains method, of class Container.
     */
    public void testBottomSideContains3() {
        System.out.println("bottomSideContains3");
        Point p = new Point(-20, 160);
        ContainerI drawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(150)
                .setWidth(450)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(-20, 10), new Point(-20 + (450), 10 + (150))))
                .build();
        Container instance = drawable.contain();
        boolean expResult = true;
        boolean result = instance.bottomSideContains(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of bottomSideContains method, of class Container.
     */
    public void testBottomSideContains4() {
        System.out.println("bottomSideContains4");
        Point p = new Point(-20, -150);
        ContainerI drawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(-150)
                .setWidth(-450)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(-20, -10), new Point(-20 + (-450), -10 + (-150))))
                .build();
        Container instance = drawable.contain();
        boolean expResult = false;
        boolean result = instance.bottomSideContains(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of linearResizing method, of class Container.
     */
    public void testLinearResizing() {
        System.out.println("linearResizing");
        float amount = 2.1F;
        ContainerI drawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(150)
                .setWidth(450)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(20, 10), new Point(20 + (450), 10 + (150))))
                .build();
        Container instance = drawable.contain();
        ContainerI finalDrawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(150 + 2)
                .setWidth(450 + 2)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(20, 10), new Point(20 + (452), 10 + (152))))
                .build();
        Container expResult = finalDrawable.contain();
        Container result = instance.linearResizing(amount);
        int expectedWidth = expResult.getWidth();
        int expectedHeight = expResult.getHeight();
        assertEquals(expectedWidth, result.getWidth());
        assertEquals(expectedHeight, result.getHeight());
    }

    /**
     * Test of linearResizing method, of class Container.
     */
    public void testLinearResizing2() {
        System.out.println("linearResizing2");
        float amount = -2.1F;
        ContainerI drawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(150)
                .setWidth(450)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(20, 10), new Point(20 + (450), 10 + (150))))
                .build();
        Container instance = drawable.contain();
        ContainerI finalDrawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight((int) (150 - 2.1))
                .setWidth((int) (450 - 2.1))
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(20, 10), new Point(20 + (int) (450 - 2.1), 10 + (int) (150 - 2.1))))
                .build();
        Container expResult = finalDrawable.contain();
        Container result = instance.linearResizing(amount);
        int expectedWidth = expResult.getWidth();
        int expectedHeight = expResult.getHeight();
        assertEquals(expectedWidth, result.getWidth());
        assertEquals(expectedHeight, result.getHeight());
    }

    /**
     * Test of linearResizing method, of class Container.
     */
    public void testLinearResizing3() {
        System.out.println("linearResizing3");
        float amount = -146.1F;
        ContainerI drawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(150)
                .setWidth(450)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(20, 10), new Point(20 + (450), 10 + (150))))
                .build();
        Container instance = drawable.contain();
        ContainerI finalDrawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight((int) (150))
                .setWidth((int) (450))
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(20, 10), new Point(20 + (int) (450), 10 + (int) (150))))
                .build();
        Container expResult = finalDrawable.contain();
        Container result = instance.linearResizing(amount);
        int expectedWidth = expResult.getWidth();
        int expectedHeight = expResult.getHeight();
        assertEquals(expectedWidth, result.getWidth());
        assertEquals(expectedHeight, result.getHeight());
    }

    /**
     * Test of resize method, of class Container.
     */
    public void testResize() {
        System.out.println("resize");
        Dimension old = new Dimension();
        old.setSize(450, 150);
        Point offset = new Point(0, 20);
        InteractiveShapeI.SelectedPart part = InteractiveShapeI.SelectedPart.BOTTOMSIDE;
        ContainerI drawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(150)
                .setWidth(450)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(20, 10), new Point(20 + (450), 10 + (150))))
                .build();
        Container instance = drawable.contain();
        ContainerI finalDrawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(150 + 20)
                .setWidth(450)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(20, 10), new Point(20 + (450), 10 + (150 + 20))))
                .build();
        Container expResult = finalDrawable.contain();
        Container result = instance.resize(old, offset, part);
        assertEquals(expResult.getHeight(), result.getHeight());
    }

    /**
     * Test of resize method, of class Container.
     */
    public void testResize2() {
        System.out.println("resize2");
        Dimension old = new Dimension();
        old.setSize(450, -150);
        Point offset = new Point(0, -20);
        InteractiveShapeI.SelectedPart part = InteractiveShapeI.SelectedPart.BOTTOMSIDE;
        ContainerI drawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(150)
                .setWidth(450)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(20, 10), new Point(20 + (450), 10 + (150))))
                .build();
        Container instance = drawable.contain();
        ContainerI finalDrawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(150 - 20)
                .setWidth(450)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(20, 10), new Point(20 + (450), 10 + (150 - 20))))
                .build();
        Container expResult = finalDrawable.contain();
        Container result = instance.resize(old, offset, part);
        assertEquals(expResult.getWidth(), result.getWidth());
    }

    /**
     * Test of resize method, of class Container.
     */
    public void testResize3() {
        System.out.println("resize3");
        Dimension old = new Dimension();
        old.setSize(450, -150);
        Point offset = new Point(-20, 0);
        InteractiveShapeI.SelectedPart part = InteractiveShapeI.SelectedPart.RIGHTSIDE;
        ContainerI drawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(150)
                .setWidth(450)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(20, 10), new Point(20 + (450), 10 + (150))))
                .build();
        Container instance = drawable.contain();
        ContainerI finalDrawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(150)
                .setWidth(450 - 20)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(20, 10), new Point(20 + (450 - 20), 10 + (150))))
                .build();
        Container expResult = finalDrawable.contain();
        Container result = instance.resize(old, offset, part);
        assertEquals(expResult.getHeight(), result.getHeight());
    }

    /**
     * Test of resize method, of class Container.
     */
    public void testResize4() {
        System.out.println("resize4");
        Dimension old = new Dimension();
        old.setSize(450, -150);
        Point offset = new Point(20, 0);
        InteractiveShapeI.SelectedPart part = InteractiveShapeI.SelectedPart.RIGHTSIDE;
        ContainerI drawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(150)
                .setWidth(450)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(20, 10), new Point(20 + (450), 10 + (150))))
                .build();
        Container instance = drawable.contain();
        ContainerI finalDrawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(150)
                .setWidth(450 + 20)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(20, 10), new Point(20 + (450 + 20), 10 + (150))))
                .build();
        Container expResult = finalDrawable.contain();
        Container result = instance.resize(old, offset, part);
        assertEquals(expResult.getHeight(), result.getHeight());
    }

    /**
     * Test of getStructuralPoints method, of class Container.
     */
    public void testGetStructuralPoints() {
        System.out.println("getStructuralPoints");
        ContainerI finalDrawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(150)
                .setWidth(450)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(20, 10), new Point(20 + (450), 10 + (150))))
                .build();
        Container instance = finalDrawable.contain();
        List<Point> expResult = Arrays.asList(new Point(15, 5), new Point(470, 160));
        List<Point> result = instance.getStructuralPoints();
        assertEquals(expResult, result);
    }

    /**
     * Test of rotate method, of class Container.
     */
    public void testRotate_float() {
        System.out.println("rotate");
        System.out.println("Rotate wasnt implemented yet");
    }

    /**
     * Test of setOrigin method, of class Container.
     */
    public void testSetOrigin() {
        System.out.println("setOrigin");
        Point p = new Point(1, 1);
        ContainerI finalDrawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(150)
                .setWidth(450)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(20, 10), new Point(20 + (450), 10 + (150))))
                .build();
        Container instance = finalDrawable.contain();
        instance.setOrigin(p);
        assertEquals(p, instance.getOrigin());
    }

    /**
     * Test of setOrigin method, of class Container.
     */
    public void testSetOrigin2() {
        System.out.println("setOrigin2");
        Point p = new Point(-1, -1);
        ContainerI finalDrawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(150)
                .setWidth(450)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(20, 10), new Point(20 + (450), 10 + (150))))
                .build();
        Container instance = finalDrawable.contain();
        instance.setOrigin(p);
        assertEquals(p, instance.getOrigin());
    }

    /**
     * Test of getContained method, of class Container.
     */
    public void testGetContained() {
        System.out.println("getContained");
        ContainerI finalDrawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(150)
                .setWidth(450)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(20, 10), new Point(20 + (450), 10 + (150))))
                .build();
        Container instance = finalDrawable.contain();
        ContainerI expectedDrawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(150)
                .setWidth(450)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(20, 10), new Point(20 + (450), 10 + (150))))
                .build();
        Container expectedContainer = expectedDrawable.contain();
        DrawableEntity expResult = expectedContainer.getContained();
        DrawableEntity result = instance.getContained();
        assertEquals(expResult.toString(), result.toString());
    }

    /**
     * Test of setSelect method, of class Container.
     */
    public void testSetSelect() {
        System.out.println("setSelect");
        Boolean b = true;
        ContainerI finalDrawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(150)
                .setWidth(450)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(20, 10), new Point(20 + (450), 10 + (150))))
                .build();
        Container instance = finalDrawable.contain();
        instance.setSelect(b);
        assertEquals(b, (Boolean) instance.isSelected());
    }

    /**
     * Test of setSelect method, of class Container.
     */
    public void testSetSelect2() {
        System.out.println("setSelect2");
        Boolean b = false;
        ContainerI finalDrawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(150)
                .setWidth(450)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(20, 10), new Point(20 + (450), 10 + (150))))
                .build();
        Container instance = finalDrawable.contain();
        instance.setSelect(b);
        assertEquals(b, (Boolean) instance.isSelected());
    }

    /**
     * Test of isSelected method, of class Container.
     */
    public void testIsSelected() {
        System.out.println("isSelected");
        ContainerI finalDrawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(150)
                .setWidth(450)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(20, 10), new Point(20 + (450), 10 + (150))))
                .build();
        Container instance = finalDrawable.contain();
        boolean expResult = false;
        instance.setSelect(expResult);
        boolean result = instance.isSelected();
        assertEquals(expResult, result);
    }

    /**
     * Test of isSelected method, of class Container.
     */
    public void testIsSelected2() {
        System.out.println("isSelected");
        ContainerI finalDrawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(150)
                .setWidth(450)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(20, 10), new Point(20 + (450), 10 + (150))))
                .build();
        Container instance = finalDrawable.contain();
        boolean expResult = true;
        instance.setSelect(expResult);
        boolean result = instance.isSelected();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDrawableContainer method, of class Container.
     */
    public void testGetDrawableContainer() {
        System.out.println("getDrawableContainer");
        ContainerI finalDrawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(150)
                .setWidth(450)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(20, 10), new Point(20 + (450), 10 + (150))))
                .build();
        Container instance = finalDrawable.contain();
        Rectangle expResult = new Rectangle();
        expResult.setLocation(20 - 5, 10 - 5);
        expResult.height = 150 + 10;
        expResult.width = 450 + 10;
        Rectangle result = instance.getDrawableContainer();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDrawableContainer method, of class Container.
     */
    public void testGetDrawableContainer2() {
        System.out.println("getDrawableContainer2");
        ContainerI finalDrawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(-150)
                .setWidth(-450)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(-20, -10), new Point(20 + (450), 10 + (150))))
                .build();
        Container instance = finalDrawable.contain();
        Rectangle expResult = new Rectangle();
        expResult.setLocation(-20 - 5, -10 - 5);
        expResult.height = -150 + 10;
        expResult.width = -450 + 10;
        Rectangle result = instance.getDrawableContainer();
        assertEquals(expResult, result);
    }

    /**
     * Test of clone method, of class Container.
     */
    public void testClone() {
        System.out.println("clone");
        ContainerI finalDrawable = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(-150)
                .setWidth(-450)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(-20, -10), new Point(20 + (450), 10 + (150))))
                .build();
        Container instance = finalDrawable.contain();
        ContainerI finalDrawable2 = new DrawableEntity.Builder()
                .setColor(Color.yellow)
                .setHeight(-150)
                .setWidth(-450)
                .setType(EntityType.LINE)
                .setStructuralPoints(Arrays.asList(new Point(-20, -10), new Point(20 + (450), 10 + (150))))
                .build();
        Container instance2 = finalDrawable2.contain();
        Object expResult = instance2.clone();
        Object result = instance.getCopy();
        assertEquals(result.toString(), result.toString());
    }

}
