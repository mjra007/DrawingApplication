package GUI;

import GUI.Models.EntitiesModel;
import GUI.Controllers.DrawingOptionsController;
import GUI.Controllers.CanvasController;
import GUI.Models.OptionsModel;
import GUI.Views.Canvas;
import GUI.Views.DrawingOptions;
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
        EntitiesModel entitiesModel = new EntitiesModel();
        OptionsModel optionsModel = new OptionsModel();
        // Starting controllers
        CanvasController canvasController = new CanvasController(entitiesModel, optionsModel);
        DrawingOptionsController drawingOptionsController = new DrawingOptionsController(entitiesModel, optionsModel);
        // Now views
        Canvas canvas = new Canvas();
        canvas.addController(canvasController);
        canvasController.addView(canvas);
        DrawingOptions drawingOptions = new DrawingOptions(canvas);
        drawingOptionsController.addView(drawingOptions);
        drawingOptions.addController(drawingOptionsController);
        drawingOptions.setVisible(true);
    }
}
