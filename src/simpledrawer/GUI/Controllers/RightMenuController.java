/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpledrawer.GUI.Controllers;

import simpledrawer.GUI.Views.RightMenuView;
import com.rits.cloning.Cloner;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPopupMenu;
import simpledrawer.CopyPasteCutI;
import drawingpanel.DrawableI;
import drawingpanel.DrawingPanel;
import simpledrawer.GUI.Models.CanvasOptions;
import simpledrawer.shapes.Container.Container;
import simpledrawer.shapes.Shape;

/**
 *
 * @author ma8521e
 */
public class RightMenuController {

    RightMenuView popupMenu;
    DrawingPanel canvas;
    CanvasOptions canvasOptions;

    public RightMenuController(CanvasOptions canvasModel) {
        canvasOptions = canvasModel;
    }

    public void addView(RightMenuView view, DrawingPanel canvas) {
        this.popupMenu = view;
        this.canvas = canvas;
        setupListener();
    }

    public void setupListener() {
        popupMenu.getFilled().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                fillShape(evt);
            }
        });
        popupMenu.getDelete().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                delteButton(evt);
            }
        });
        popupMenu.getCopy().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                copyButton(evt);
            }
        });
        popupMenu.getCut().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cutButton(evt);
            }
        });
        popupMenu.getPaste().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                pasteButton(evt);
            }
        });
        popupMenu.getBackgroundMenu().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                changeBackground(evt);
            }
        });
    }

    public void changeBackground(ActionEvent evt) {
        canvas.setBackground(canvasOptions.getCurrentColor());
    }

    public void copy() {
        DrawableI drawable = canvas.getDrawings().get(canvas.getSelectedDrawingIndex());
        if (drawable instanceof CopyPasteCutI) {
            CopyPasteCutI copyIitem = (CopyPasteCutI) drawable;
            canvasOptions.setCopy((CopyPasteCutI) copyIitem.clone());
        }
    }

    public void pasteCopy(Point p) {
        CopyPasteCutI copyIitem = new Cloner().deepClone(canvasOptions.getDrawableCopied());
        copyIitem.setOrigin(p);
        canvas.addDrawing((DrawableI) copyIitem);
        if (canvasOptions.hasCutBeenPerformed()) {
            canvasOptions.setCopy(null);
            canvasOptions.setCut(false);
        }
    }

    public void cut() {
        DrawableI drawable = canvas.getDrawings().get(canvas.getSelectedDrawingIndex());
        if (drawable instanceof CopyPasteCutI) {
            CopyPasteCutI copyIitem = (CopyPasteCutI) drawable;
            CopyPasteCutI copied = (CopyPasteCutI) copyIitem.clone();
            canvasOptions.setCopy(copied);
        }
        canvasOptions.setCut(true);
        canvas.removeSelectedDrawing();

    }

    private void cutButton(ActionEvent evt) {
        cut();

    }

    private void pasteButton(ActionEvent evt) {
        if (canvasOptions.getDrawableCopied() != null) {
            pasteCopy(canvasOptions.getLastClick());
        }
    }

    private void copyButton(ActionEvent evt) {
        copy();
    }

    private void delteButton(ActionEvent evt) {
        canvas.removeSelectedDrawing();
    }

    private void fillShape(ActionEvent evt) {
        Container container = (Container) canvas.getSelectedDrawing();
        Shape shape = (Shape) container.getContained();
        shape.setFilledColor(canvasOptions.getCurrentColor());
        canvas.refresh();;
    }

    public boolean implementsCutCopyPaste(DrawableI drawable) {
        return drawable instanceof CopyPasteCutI;
    }

    public JPopupMenu buildMenu() {
        JPopupMenu newMenu = new JPopupMenu();
        if (canvas.getSelectedDrawing() == null) {
            newMenu.add(popupMenu.getPaste());
            newMenu.add(popupMenu.getBackgroundMenu());
        } else if (implementsCutCopyPaste(canvas.getSelectedDrawing())) {
            newMenu.add(popupMenu.getFilled());
            newMenu.add(popupMenu.getDelete());
            newMenu.add(popupMenu.getCopy());
            newMenu.add(popupMenu.getPaste());
            newMenu.add(popupMenu.getCut());
        }
        return newMenu;
    }

}
