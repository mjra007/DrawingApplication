/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers;

import GUI.DrawingState;
import GUI.Models.EntitiesModel;
import GUI.Models.OptionsModel;
import GUI.View;
import GUI.Views.DrawerMainView;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import simpledrawer.shapes.Entity;
import simpledrawer.Readers.JSONShapeReader;

/**
 *
 * @author micae
 */
public class DrawingMainController{

    private View view;
    private EntitiesModel entitiesModel;
    private OptionsModel guiOptions;

    public DrawingMainController(View v,EntitiesModel m, OptionsModel guiOptions) {
        entitiesModel = m;
        view=v;
        this.guiOptions = guiOptions;
        setupActionListeners();
        setupFocusListener();
        setupKeyListener();
    }

    public DrawerMainView getDrawingMainView() {
        return (DrawerMainView) view;
    }

    public void setupActionListeners() {
        getDrawingMainView().getbtnLoadJSON().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnLoadJSONActionPerformed(evt);
            }
        });
        getDrawingMainView().getbtnChange().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnChangeBackground(evt);
            }
        });
        getDrawingMainView().getbtnClear().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        getDrawingMainView().getbtnLeft().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnLeftActionPerformed(evt);
            }
        });
        getDrawingMainView().getbtnReset().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnReset(evt);
            }
        });
        getDrawingMainView().getbtnLoadXML().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnLoadXMLActionPerformed(evt);
            }
        });
        getDrawingMainView().getbtnRight().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnRightActionPerformed(evt);
            }
        });
        getDrawingMainView().getRadLineShape().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                radShapeActionPerformed(evt);
            }
        });
        getDrawingMainView().getRadOvalShape().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                radShapeActionPerformed(evt);
            }
        });
        getDrawingMainView().getRadTriangleShape().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                radShapeActionPerformed(evt);
            }
        });
        getDrawingMainView().getRadRectangleShape().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                radShapeActionPerformed(evt);
            }
        });
        getDrawingMainView().getTxtThickness().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThicknessActionPerformed(evt);
            }
        });

    }

    public void setupAdjustmentListener() {
        getDrawingMainView().getScrollBrightness().addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                scrBrightnessAdjustmentValueChanged(evt);
            }
        });
        getDrawingMainView().getScrollBlue().addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                scrColourAdjustmentHandler(evt);
            }
        });
        getDrawingMainView().getScrollRed().addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                scrColourAdjustmentHandler(evt);
            }
        });
        getDrawingMainView().getScrollGreen().addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                scrColourAdjustmentHandler(evt);
            }
        });
    }

    public void setupFocusListener() {
        DrawerMainView view = (DrawerMainView)this.view;
        view.getTxtThickness().addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtThicknessFocusLost(evt);
            }
        });
    }

    public void setupKeyListener() {
        DrawerMainView view = (DrawerMainView)this.view;
        view.getTxtThickness().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtThicknessKeyReleased(evt);
            }
        });
    }



   

    public void clearDisplay() {
        // Empty the ArrayList and clear the display.
        entitiesModel.getEntityList().clear();
        view.refresh();
    }



    /* rotate the drawing 90 degrees to the left */
    private void btnLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLeftActionPerformed
        guiOptions.rotate(-90);
    }//GEN-LAST:event_btnLeftActionPerformed

    /* clear the drawing */
    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearDisplay();
    }//GEN-LAST:event_btnClearActionPerformed

    /* set the drawing colours */
    private void scrColourAdjustmentHandler(java.awt.event.AdjustmentEvent evt) {//GEN-FIRST:event_scrColourAdjustmentHandler
        int currentRed, currentGreen, currentBlue;
        currentRed = getDrawingMainView().getScrollRed().getValue();
        currentGreen = getDrawingMainView().getScrollGreen().getValue();
        currentBlue = getDrawingMainView().getScrollBlue().getValue();
        getDrawingMainView().getTxtRed().setText(currentRed + "");
        getDrawingMainView().getTxtGreen().setText(currentGreen + "");
        getDrawingMainView().getTxtBlue().setText(currentBlue + "");
        getDrawingMainView().getLblColor().setBackground(new Color(currentRed, currentGreen, currentBlue));
        guiOptions.setCurrentColor(new Color(currentRed, currentGreen, currentBlue));
    }//GEN-LAST:event_scrColourAdjustmentHandler

    /* user pressed return in the thickness field */
    private void txtThicknessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThicknessActionPerformed
        handleThickness();
    }//GEN-LAST:event_txtThicknessActionPerformed

    /* user has clicked away from the thickness field */
    private void txtThicknessFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtThicknessFocusLost
        handleThickness();
    }//GEN-LAST:event_txtThicknessFocusLost

    /* user has types somethinginto thickness field */
    private void txtThicknessKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtThicknessKeyReleased
        handleThickness();
    }//GEN-LAST:event_txtThicknessKeyReleased

    /* rotate the drawing 90 degrees to the right */
    private void btnRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRightActionPerformed
        guiOptions.rotate(90);
    }//GEN-LAST:event_btnRightActionPerformed

    private void scrBrightnessAdjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {//GEN-FIRST:event_scrBrightnessAdjustmentValueChanged
        float currentBrightness = getDrawingMainView().getScrollBrightness().getValue();
        int colourValue = (int) (255 * ((100 - currentBrightness) / 100));
        getDrawingMainView().getlblBrightness().setBackground(new Color(colourValue, colourValue, colourValue));
        guiOptions.setCurrentBrightness((100 - currentBrightness) / 100);

    }//GEN-LAST:event_scrBrightnessAdjustmentValueChanged

    private void btnLoadXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadXMLActionPerformed

    }//GEN-LAST:event_btnLoadXMLActionPerformed

    private void btnLoadJSONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadJSONActionPerformed

        try {
            JSONShapeReader shapeReader = new JSONShapeReader();
            shapeReader.getShapesFromFile("stored_shapes.json");
            List listOfShapes = shapeReader.getShapes();
            //entitiesModel.setEntityList(listOfShapes);
            view.refresh();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DrawerMainView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnLoadJSONActionPerformed

    private void radShapeActionPerformed(java.awt.event.ActionEvent evt) {
        if (getDrawingMainView().getRadLineShape().isSelected()) {
            guiOptions.setCurrentShapeType(Entity.EntityType.LINE);
            return;
        }
        if (getDrawingMainView().getRadOvalShape().isSelected()) {
            guiOptions.setCurrentShapeType(Entity.EntityType.OVAL);
            return;
        }

        if (getDrawingMainView().getRadRectangleShape().isSelected()) {
            guiOptions.setCurrentShapeType(Entity.EntityType.RECTANGLE);
            return;
        }
        if (getDrawingMainView().getRadTriangleShape().isSelected()) {
            guiOptions.setCurrentShapeType(Entity.EntityType.TRIANGLE);
            return;
        }
    }

    
    private void btnReset(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        reset();
    }

    private void btnChangeBackground(java.awt.event.ActionEvent evt) {
        getDrawingMainView().getDrawingPanel().setBackground(this.guiOptions.getCurrentColor());
    }

    public void reset() {
        getDrawingMainView().getScrollBlue().setValue(0);
        getDrawingMainView().getScrollGreen().setValue(0);
        getDrawingMainView().getScrollRed().setValue(0);
        clearDisplay();
        getDrawingMainView().getDrawingPanel().setBackground(Color.white);
        getDrawingMainView().getlblBrightness().setBackground(new java.awt.Color(126, 126, 126));
        getDrawingMainView().getLblColor().setBackground(new java.awt.Color(0, 0, 0));
        getDrawingMainView().getTxtThickness().setText("5");
        getDrawingMainView().getRadRectangleShape().doClick();
        getDrawingMainView().getScrollBrightness().setValue(50);
        getDrawingMainView().getTxtBlue().setText("0");
        getDrawingMainView().getTxtRed().setText("0");
        getDrawingMainView().getTxtGreen().setText("0");
    }

    public int getCurrentRotation(){
        return guiOptions.getCurrentRotation();
    }
    /* action whatever change has been made to the line thickness */
    private void handleThickness() {
        int thickness = Integer.parseInt(getDrawingMainView().getTxtThickness().getText());
        /* only allow thicknesses in the range 1 to 40 */
        thickness = thickness < 1 || thickness > 40 ? 5 : thickness;
        guiOptions.setCurrentThickness(thickness);
    }

}
