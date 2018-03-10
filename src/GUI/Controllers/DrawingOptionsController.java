package GUI.Controllers;

import GUI.Models.EntitiesModel;
import GUI.Models.OptionsModel;
import GUI.View;
import GUI.Views.DrawingOptions;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import simpledrawer.shapes.Entity;
import simpledrawer.Readers.JSONShapeReader;

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
        getView().getmenuRectangle().doClick();
        getView().getFinalColor().setBackground(guiOptions.getCurrentColor());
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
                menuRectangle(evt);
            }
        });
        getView().getmenuOval().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuOval(evt);
            }
        });
        getView().getmenuLine().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuLine(evt);
            }
        });
        getView().getmenuTriangle().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuTriangle(evt);
            }
        });
        getView().getTxtThickness().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                txtThicknessActionPerformed(evt);
            }
        });
        getView().getbtnBackground().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnChangeBackground(evt);
            }
        });
        getView().getColorMixer().addPropertyChangeListener("mix", new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                colorMixerMenu(evt);
            }

        });
        getView().getRedLabel().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                redLabelClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        getView().getRedLabel().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                redLabelClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        getView().getYellowLabel().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                yellowLabelClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        getView().getOrangeLabel().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                orangeLabelClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        getView().getBlueLabel().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                blueLabelClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        getView().getGreenLabel().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                greenLabelClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        getView().getPinkLabel().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pinkLabelClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        getView().getBlackLabel().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                blackLabelClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        getView().getWhiteLabel().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                whiteLabelClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        getView().getBetterGraphicsButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                betterGraphicsClicked(evt);
            }
        });
    }

    public void setupFocusListener() {
        getView().getTxtThickness().addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtThicknessFocusLost(evt);
            }
        });
    }

    public void setupKeyListener() {
        getView().getTxtThickness().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtThicknessKeyReleased(evt);
            }
        });
    }
    public void betterGraphicsClicked(ActionEvent evt) {
        guiOptions.setbetterGraphics(getView().getBetterGraphicsButton().isSelected());
    }
    
    
    public void redLabelClicked(MouseEvent evt) {
        setFinalColor(getView().getRedLabel().getBackground());
    }

    public void yellowLabelClicked(MouseEvent evt) {
        setFinalColor(getView().getYellowLabel().getBackground());
    }

    public void orangeLabelClicked(MouseEvent evt) {
        setFinalColor(getView().getOrangeLabel().getBackground());
    }

    public void blueLabelClicked(MouseEvent evt) {
        setFinalColor(getView().getBlueLabel().getBackground());
    }

    public void greenLabelClicked(MouseEvent evt) {
        setFinalColor(getView().getGreenLabel().getBackground());
    }

    public void pinkLabelClicked(MouseEvent evt) {
        setFinalColor(getView().getPinkLabel().getBackground());
    }

    public void blackLabelClicked(MouseEvent evt) {
        setFinalColor(getView().getBlackLabel().getBackground());
    }

    public void whiteLabelClicked(MouseEvent evt) {
        setFinalColor(getView().getWhiteLabel().getBackground());
    }

    public void setFinalColor(Color c) {
        guiOptions.setCurrentColor(c);
        getView().getFinalColor().setBackground(c);
    }

    public void importJSON(ActionEvent evt) {
        try {
            JSONShapeReader shapeReader = new JSONShapeReader();
            shapeReader.getShapesFromFile("stored_shapes.json");
            List listOfShapes = shapeReader.getShapes();
            //entitiesModel.setEntityList(listOfShapes);
            view.refresh();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DrawingOptions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Color getCurrentColor() {
        return guiOptions.getCurrentColor();
    }

    public void importXML(ActionEvent evt) {

    }

    public void exportJSON(ActionEvent evt) {

    }

    public void exportXML(ActionEvent evt) {

    }

    public void clearDisplay() {
        // Empty the ArrayList and clear the display.
        guiOptions.setBackground(Color.white);
        getView().getCanvas().setBackground(Color.white);
        entitiesModel.getEntityList().clear();
        view.refresh();
    }

    /* rotate the drawing 90 degrees to the left */
    private void rotateLeft(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLeftActionPerformed
        guiOptions.rotate(-90);
        getView().getCanvas().repaint();
    }//GEN-LAST:event_btnLeftActionPerformed

    /* clear the drawing */
    private void clear(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearDisplay();
    }

    /* user pressed return in the thickness field */
    private void txtThicknessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThicknessActionPerformed
        handleThickness();
    }

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
        getView().getCanvas().repaint();

    }

    private void colorMixerMenu(PropertyChangeEvent evt) {
        guiOptions.setCurrentColor((Color) evt.getNewValue());
    }

    public void menuTriangle(ActionEvent evt) {
        guiOptions.setEntityTypeSelected(Entity.EntityType.TRIANGLE);
        getView().getmenuTriangle().setSelected(true);
        getView().getmenuOval().setSelected(false);
        getView().getmenuRectangle().setSelected(false);
        getView().getmenuLine().setSelected(false);
    }

    public void menuOval(ActionEvent evt) {
        guiOptions.setEntityTypeSelected(Entity.EntityType.OVAL);
        getView().getmenuTriangle().setSelected(false);
        getView().getmenuOval().setSelected(true);
        getView().getmenuRectangle().setSelected(false);
        getView().getmenuLine().setSelected(false);
    }

    public void menuRectangle(ActionEvent evt) {
        guiOptions.setEntityTypeSelected(Entity.EntityType.RECTANGLE);
        getView().getmenuTriangle().setSelected(false);
        getView().getmenuOval().setSelected(false);
        getView().getmenuRectangle().setSelected(true);
        getView().getmenuLine().setSelected(false);
    }

    public void menuLine(ActionEvent evt) {
        guiOptions.setEntityTypeSelected(Entity.EntityType.LINE);
        getView().getmenuTriangle().setSelected(false);
        getView().getmenuOval().setSelected(false);
        getView().getmenuRectangle().setSelected(false);
        getView().getmenuLine().setSelected(true);
    }

    private void reset(ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        reset();
    }

    private void btnChangeBackground(java.awt.event.ActionEvent evt) {
        getView().getCanvas().setBackground(this.guiOptions.getCurrentColor());
    }

    public void reset() {
        clearDisplay();
        guiOptions.setBackground(Color.white);
        getView().getCanvas().setBackground(Color.white);
        guiOptions.setCurrentColor(new java.awt.Color(0, 0, 0));
        getView().getTxtThickness().setText("5");
        getView().getmenuRectangle().doClick();
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
