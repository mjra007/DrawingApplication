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
import GUI.Views.DrawingOptions;
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
public class DrawingOptionsController {

    private View view;
    private EntitiesModel entitiesModel;
    private OptionsModel guiOptions;

    public DrawingOptionsController(EntitiesModel m, OptionsModel guiOptions) {
        entitiesModel = m;
        this.guiOptions = guiOptions;

    }

    public void addView(View view) {
        this.view = view;
        setupActionListeners();
        setupFocusListener();
        setupKeyListener();
    }

    public DrawingOptions getView() {
        return (DrawingOptions) view;
    }

    public void setupActionListeners() {
        getView().getmenuImportJSON().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                importJSON(evt);
            }
        });

        getView().getmenuImportXML().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                importXML(evt);
            }
        });

        getView().getmenuExportJSON().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                importXML(evt);
            }
        });
        getView().getmenuLeft().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                rotateLeft(evt);
            }
        });
        getView().getmenuRight().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                rotateRight(evt);
            }
        });
        getView().getmenuClear().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                clear(evt);
            }
        });
        getView().getmenuReset().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                reset(evt);
            }
        });
        getView().getmenuRectangle().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                radShapeActionPerformed(evt);
            }
        });
        getView().getmenuOval().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                radShapeActionPerformed(evt);
            }
        });
        getView().getmenuLine().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                radShapeActionPerformed(evt);
            }
        });
        getView().getmenuTriangle().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                radShapeActionPerformed(evt);
            }
        });
        getView().getmenuShapes().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuShapes(evt);
            }
        });
    }

    public void setupFocusListener() {
        /*       getView()..addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtThicknessFocusLost(evt);
            }
        });*/
    }

    public void setupKeyListener() {
        /*   DrawerMainView view = (DrawerMainView) this.view;
        view.getTxtThickness().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtThicknessKeyReleased(evt);
            }
        });*/
    }

    public void importJSON(ActionEvent evt) {

    }

    public void importXML(ActionEvent evt) {

    }

    public void exportJSON(ActionEvent evt) {

    }

    public void exportXML(ActionEvent evt) {

    }

    public void clearDisplay() {
        // Empty the ArrayList and clear the display.
        entitiesModel.getEntityList().clear();
        view.refresh();
    }

    /* rotate the drawing 90 degrees to the left */
    private void rotateLeft(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLeftActionPerformed
        guiOptions.rotate(-90);
    }//GEN-LAST:event_btnLeftActionPerformed

    /* clear the drawing */
    private void clear(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearDisplay();
    }

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
    }

    /* rotate the drawing 90 degrees to the right */
    private void rotateRight(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRightActionPerformed
        guiOptions.rotate(90);
    }

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
        setShapeSelection(guiOptions.getEntityTypeSelected());
        if (getView().getmenuLine().isSelected()) {
            guiOptions.setCurrentShapeType(Entity.EntityType.LINE);
            clearShapeSelection();
            setShapeSelection(Entity.EntityType.LINE);

            return;
        }
        if (getView().getmenuOval().isSelected()) {
            guiOptions.setCurrentShapeType(Entity.EntityType.OVAL);
            clearShapeSelection();
            setShapeSelection(Entity.EntityType.OVAL);

            return;
        }
        if (getView().getmenuRectangle().isSelected()) {
            guiOptions.setCurrentShapeType(Entity.EntityType.RECTANGLE);
            clearShapeSelection();
               setShapeSelection(Entity.EntityType.RECTANGLE);

            return;
        }
        if (getView().getmenuTriangle().isSelected()) {
            guiOptions.setCurrentShapeType(Entity.EntityType.TRIANGLE);
           clearShapeSelection();
            setShapeSelection(Entity.EntityType.TRIANGLE);
            return;
        }

    }

    public void clearShapeSelection() {
        getView().getmenuTriangle().setSelected(false);
        getView().getmenuOval().setSelected(false);
        getView().getmenuRectangle().setSelected(false);
        getView().getmenuLine().setSelected(false);
    }

    public void setShapeSelection(Entity.EntityType entity) {
        switch (entity) {
            case LINE:
                getView().getmenuLine().setSelected(true);
                break;
            case OVAL:
                getView().getmenuOval().setSelected(true);
                break;
            case TRIANGLE:
                getView().getmenuTriangle().setSelected(true);
                break;
            case RECTANGLE:
                getView().getmenuRectangle().setSelected(true);
            default:
                break;
        }
    }

    public void menuShapes(ActionEvent evt) {
        System.out.println("sds");
        setShapeSelection(guiOptions.getEntityTypeSelected());
    }

    private void reset(ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        reset();
    }

    private void btnChangeBackground(java.awt.event.ActionEvent evt) {
        getView().getCanvas().setBackground(this.guiOptions.getCurrentColor());
    }

    public void reset() {
        getView().getScrollBlue().setValue(0);
        getView().getScrollGreen().setValue(0);
        getView().getScrollRed().setValue(0);
        clearDisplay();
        getView().getDrawingPanel().setBackground(Color.white);
        getView().getlblBrightness().setBackground(new java.awt.Color(126, 126, 126));
        getView().getLblColor().setBackground(new java.awt.Color(0, 0, 0));
        getView().getTxtThickness().setText("5");
        getView().getRadRectangleShape().doClick();
        getView().getScrollBrightness().setValue(50);
        getView().getTxtBlue().setText("0");
        getView().getTxtRed().setText("0");
        getView().getTxtGreen().setText("0");
    }

    public int getCurrentRotation() {
        return guiOptions.getCurrentRotation();
    }

    /* action whatever change has been made to the line thickness */
    private void handleThickness() {
        int thickness = guiOptions.getCurrentThickness();
        /* only allow thicknesses in the range 1 to 40 */
        thickness = thickness < 1 || thickness > 40 ? 5 : thickness;
        guiOptions.setCurrentThickness(thickness);
    }

}
