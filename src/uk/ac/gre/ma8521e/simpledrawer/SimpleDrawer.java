package uk.ac.gre.ma8521e.simpledrawer;

import drawingpanel.DrawingPanel;
import uk.ac.gre.ma8521e.simpledrawer.GUI.Controllers.CanvasOptionsController;
import uk.ac.gre.ma8521e.simpledrawer.GUI.Controllers.CanvasController;
import uk.ac.gre.ma8521e.simpledrawer.GUI.Controllers.RightMenuController;
import uk.ac.gre.ma8521e.simpledrawer.GUI.Models.CanvasOptions;
import uk.ac.gre.ma8521e.simpledrawer.GUI.Views.CanvasOptionsView;
import uk.ac.gre.ma8521e.simpledrawer.GUI.Views.RightMenuView;
import javax.swing.UIManager;

public class SimpleDrawer {

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        // Starting models
        CanvasOptions canvasSettings = new CanvasOptions();
        // Starting controllers
        CanvasController canvasController = new CanvasController(canvasSettings);
        CanvasOptionsController canvasSettingsController = new CanvasOptionsController(canvasSettings);
        RightMenuController rightMenuController = new RightMenuController(canvasSettings);
        // Now views
        CanvasOptionsView drawingOptions = new CanvasOptionsView();
        RightMenuView rightMenu = new RightMenuView(rightMenuController);
        rightMenuController.addView(rightMenu, drawingOptions.getCanvas());
        drawingOptions.getCanvas().addMouseListener(canvasController);
        drawingOptions.getCanvas().addMouseMotionListener(canvasController);
        drawingOptions.getCanvas().addMouseWheelListener(canvasController);
        canvasController.addView(drawingOptions.getCanvas(), rightMenu);
        canvasSettingsController.addViews(drawingOptions, drawingOptions.getCanvas());
        drawingOptions.addController(canvasSettingsController);
        drawingOptions.setVisible(true);
    }
}
