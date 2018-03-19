package simpledrawer.GUI.Controllers;

import drawingpanel.DrawableI;
import drawingpanel.DrawingPanel;
import simpledrawer.GUI.Models.CanvasOptions;
import simpledrawer.GUI.Views.CanvasOptionsView;
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
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import simpledrawer.ContainerSpawnAnimation;
import simpledrawer.shapes.DrawableEntity;
import simpledrawer.Readers.JSONShapeReader;
import simpledrawer.shapes.Container.Container;
import simpledrawer.shapes.Shape;
import simpledrawer.shapes.Container.ContainerI;

public class CanvasOptionsController {

    private CanvasOptionsView canvasOptionsView;
    private DrawingPanel canvas;
    private CanvasOptions canvasOptions;

    public CanvasOptionsController(CanvasOptions guiOptions) {
        this.canvasOptions = guiOptions;
    }

    public void addViews(CanvasOptionsView view, DrawingPanel view2) {
        this.canvasOptionsView = view;
        this.canvas = view2;
        setupActionListeners();
        setupFocusListener();
        setupKeyListener();
        getView().getTxtThickness().setText("2");
        getView().getmenuRectangle().doClick();
        getView().getFinalColor().setBackground(canvasOptions.getCurrentColor());
    }

    public CanvasOptionsView getView() {
        return canvasOptionsView;
    }

    public void setupActionListeners() {
        getView().getmenuImportJSON().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                importJSON(evt);
            }
        });

        getView().getmenuExportJSON().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                exportJSON(evt);
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

        getView().getColorMixer().addPropertyChangeListener("colormix", new PropertyChangeListener() {
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
        canvas.setbetterGraphics(getView().getBetterGraphicsButton().isSelected());
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
        canvasOptions.setCurrentColor(c);
        getView().getFinalColor().setBackground(c);
    }

    public void getShapesFromJSON(String path) {
        try {
            JSONShapeReader shapeReader = new JSONShapeReader();
            shapeReader.getShapesFromFile(path,canvas);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CanvasOptionsView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void importJSON(ActionEvent evt) {
        JFileChooser fileChooser = getView().getFileChooser();
        fileChooser.setDialogTitle("Choose the drawing you wish to import...");
        FileFilter JSON = new FileNameExtensionFilter("JSON FILE", "JSON");
        fileChooser.setFileFilter(JSON);
        int state = fileChooser.showOpenDialog(getView().getmenuImportJSON());
        if (state == JFileChooser.APPROVE_OPTION) {
            getShapesFromJSON(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }

    public void saveShapesJSON(String path, HashMap<Integer, DrawableI> drawings) {
        JSONShapeReader shapeReader = new JSONShapeReader();
        shapeReader.saveJSON(path, drawings);
    }

    public Color getCurrentColor() {
        return canvasOptions.getCurrentColor();
    }

    public void exportJSON(ActionEvent evt) {
        JFileChooser fileChooser = getView().getFileChooser();
        fileChooser.setDialogTitle("Create a file to export your drawing...");
        FileFilter JSON = new FileNameExtensionFilter("JSON FILE", "JSON");
        fileChooser.setFileFilter(JSON);
        int state = fileChooser.showOpenDialog(getView().getmenuExportJSON());
        if (state == JFileChooser.APPROVE_OPTION) {
            if(canvas.getDrawings().size()==0){
                System.out.println("nothing");
            }
            saveShapesJSON(fileChooser.getSelectedFile().getAbsolutePath(), canvas.getDrawings());
        }
    }

    public void clearDisplay() {
        // Empty the ArrayList and clear the display.
        canvas.setBackground(Color.white);
        getView().getCanvas().setBackground(Color.white);
        canvas.getDrawings().clear();
        canvas.refresh();
    }

    /* rotate the drawing 90 degrees to the left */
    private void rotateLeft(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLeftActionPerformed
        canvas.setRotation(-90);
    }

    /* clear the drawing */
    private void clear(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearDisplay();
    }

    /* user pressed return in the thickness field */
    private void txtThicknessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThicknessActionPerformedhandl
        handleThickness();
    }

    /* user has clicked away from the thickness field */
    private void txtThicknessFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtThicknessFocusLost
        handleThickness();
    }

    /* user has types somethinginto thickness field */
    private void txtThicknessKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtThicknessKeyReleased
        handleThickness();
    }

    /* rotate the drawing 90 degrees to the right */
    private void rotateRight(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRightActionPerformed
        canvas.setRotation(90);

    }

    private void colorMixerMenu(PropertyChangeEvent evt) {
        setFinalColor(getView().getColorMixer().getMix());
    }

    public void menuTriangle(ActionEvent evt) {
        canvasOptions.setEntityTypeSelected(DrawableEntity.EntityType.TRIANGLE);
        getView().getmenuTriangle().setSelected(true);
        getView().getmenuOval().setSelected(false);
        getView().getmenuRectangle().setSelected(false);
        getView().getmenuLine().setSelected(false);
    }

    public void menuOval(ActionEvent evt) {
        canvasOptions.setEntityTypeSelected(DrawableEntity.EntityType.OVAL);
        getView().getmenuTriangle().setSelected(false);
        getView().getmenuOval().setSelected(true);
        getView().getmenuRectangle().setSelected(false);
        getView().getmenuLine().setSelected(false);
    }

    public void menuRectangle(ActionEvent evt) {
        canvasOptions.setEntityTypeSelected(DrawableEntity.EntityType.RECTANGLE);
        getView().getmenuTriangle().setSelected(false);
        getView().getmenuOval().setSelected(false);
        getView().getmenuRectangle().setSelected(true);
        getView().getmenuLine().setSelected(false);
    }

    public void menuLine(ActionEvent evt) {
        canvasOptions.setEntityTypeSelected(DrawableEntity.EntityType.LINE);
        getView().getmenuTriangle().setSelected(false);
        getView().getmenuOval().setSelected(false);
        getView().getmenuRectangle().setSelected(false);
        getView().getmenuLine().setSelected(true);
    }

    private void reset(ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        reset();
    }

    public void reset() {
        clearDisplay();
        canvas.setBackground(Color.white);
        getView().getCanvas().setBackground(Color.white);
        canvasOptions.setCurrentColor(new java.awt.Color(0, 0, 0));
        getView().getTxtThickness().setText("5");
        getView().getmenuRectangle().doClick();
    }

    public int getCurrentRotation() {
        return canvas.getRotation();
    }

    /* action whatever change has been made to the line thickness */
    private void handleThickness() {
        try {
            int thickness = Integer.valueOf(getView().getTxtThickness().getText());
            /* only allow thicknesses in the range 1 to 40 */
            thickness = thickness < 1 || thickness > 40 ? 40 : thickness;
            canvasOptions.setCurrentThickness(thickness);
            canvasOptionsView.getTxtThickness().setText(canvasOptions.getCurrentThickness() + "");
        } catch (NumberFormatException ex) {
            canvasOptionsView.getTxtThickness().setText(canvasOptions.getCurrentThickness() + "");
        }
    }

}
