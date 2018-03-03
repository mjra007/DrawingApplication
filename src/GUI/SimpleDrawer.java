/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.Models.EntitiesModel;
import GUI.Controllers.DrawingMainController;
import GUI.Controllers.DrawingPanelController;
import GUI.Models.OptionsModel;
import GUI.Views.DrawerMainView;
import GUI.Views.DrawingPanelView;
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
        EntitiesModel entityModel = new EntitiesModel();
        OptionsModel optionsModel= new OptionsModel();
        DrawingPanelView dpv= new DrawingPanelView();
        DrawerMainView dmv = new DrawerMainView(dpv);
        DrawingMainController dmc = new DrawingMainController(dmv,entityModel,optionsModel);
        DrawingPanelController dpc = new DrawingPanelController(dpv,entityModel,optionsModel);
        dpv.addController(dpc);
        dmv.addController(dmc);
        dmv.setVisible(true);
    }
}
