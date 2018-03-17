package simpledrawer.GUI;

import simpledrawer.GUI.Models.Canvas;
import simpledrawer.GUI.Controllers.CanvasOptionsController;
import simpledrawer.GUI.Controllers.CanvasController;
import simpledrawer.GUI.Controllers.RightMenuController;
import simpledrawer.GUI.Models.CanvasOptions;
import simpledrawer.GUI.Views.CanvasView;
import simpledrawer.GUI.Views.CanvasOptionsView;
import simpledrawer.GUI.Views.RightMenuView;
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
        Canvas canvas = new Canvas(canvasSettings);
        // Starting controllers
        CanvasController canvasController = new CanvasController(canvas);
        CanvasOptionsController canvasSettingsController = new CanvasOptionsController(canvas, canvasSettings);
        RightMenuController rightMenuController = new RightMenuController(canvas);
        // Now views
        RightMenuView rightMenu = new RightMenuView(rightMenuController);
        CanvasView canvasView = new CanvasView();
        rightMenuController.addView(rightMenu,canvasView);
        canvasView.addController(canvasController);
        canvasController.addView(canvasView,rightMenu);
        CanvasOptionsView drawingOptions = new CanvasOptionsView(canvasView);
        canvasSettingsController.addView(drawingOptions);
        drawingOptions.addController(canvasSettingsController);
        drawingOptions.setVisible(true);
    }
}
