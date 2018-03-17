package GUI;

import GUI.Models.Canvas;
import GUI.Controllers.CanvasOptionsController;
import GUI.Controllers.CanvasController;
import GUI.Models.CanvasOptions;
import GUI.Views.CanvasView;
import GUI.Views.CanvasOptionsView;
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
        // Now views
        CanvasView canvasView = new CanvasView();
        canvasView.addController(canvasController);
        canvasController.addView(canvasView);
        CanvasOptionsView drawingOptions = new CanvasOptionsView(canvasView);
        canvasSettingsController.addView(drawingOptions);
        drawingOptions.addController(canvasSettingsController);
        drawingOptions.setVisible(true);
    }
}
