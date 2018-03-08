/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.Models.EntitiesModel;
import GUI.Controllers.DrawingOptionsController;
import GUI.Controllers.CanvasController;
import GUI.Models.OptionsModel;
import GUI.Views.DrawerMainView;
import GUI.Views.Canvas;
import GUI.Views.DrawingOptions;
import javax.swing.UIManager;

/**
 *
 * @author micae
 */
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
        //starting models
        EntitiesModel entitiesModel = new EntitiesModel();
        OptionsModel optionsModel = new OptionsModel();
        //starting controllers
        CanvasController canvasController = new CanvasController(entitiesModel, optionsModel);
        DrawingOptionsController drawingOptionsController = new DrawingOptionsController(entitiesModel,optionsModel);
        //now views
        Canvas canvas = new Canvas();
        canvas.addController(canvasController);
        canvasController.addView(canvas);
        DrawingOptions drawingOptions = new DrawingOptions(canvas);
        drawingOptionsController.addView(drawingOptions);
        drawingOptions.addController(drawingOptionsController);
        drawingOptions.setVisible(true);
    }
}
