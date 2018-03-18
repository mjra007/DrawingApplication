/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpledrawer.GUI.Controllers;

import simpledrawer.GUI.Models.Canvas;
import simpledrawer.GUI.Views.CanvasView;
import simpledrawer.GUI.Views.RightMenuView;
import com.rits.cloning.Cloner;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPopupMenu;
import simpledrawer.CopyPasteCutI;
import simpledrawer.DrawableI;
import simpledrawer.shapes.Container.Container;
import simpledrawer.shapes.Shape;

/**
 *
 * @author ma8521e
 */
public class RightMenuController {

    RightMenuView popupMenu;
    CanvasView canvasView;
    Canvas canvas;

    public RightMenuController(Canvas canvasModel) {
        canvas = canvasModel;
    }

    public void addView(RightMenuView view, CanvasView canvasView) {
        this.popupMenu = view;
        this.canvasView = canvasView;
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
        canvas.getSettings().setBackground(canvas.getSettings().getCurrentColor());
        this.canvasView.refresh();
    }

    public void copy() {
        DrawableI drawable = canvas.getDrawings().get(canvas.getSelectedDrawingIndex());
        if (drawable instanceof CopyPasteCutI) {
            CopyPasteCutI copyIitem = (CopyPasteCutI) drawable;
            canvas.getSettings().setCopy((CopyPasteCutI) copyIitem.clone());
        }
    }

    public void pasteCopy(Point p) {
        CopyPasteCutI copyIitem = new Cloner().deepClone(canvas.getSettings().getDrawableCopied());
        copyIitem.setOrigin(p);
        canvas.addDrawing((DrawableI) copyIitem);
        if (canvas.getSettings().hasCutBeenPerformed()) {
            canvas.getSettings().setCopy(null);
            canvas.getSettings().setCut(false);
        }
        canvasView.refresh();
    }

    public void cut() {
        DrawableI drawable = canvas.getDrawings().get(canvas.getSelectedDrawingIndex());
        if (drawable instanceof CopyPasteCutI) {
            CopyPasteCutI copyIitem = (CopyPasteCutI) drawable;
            CopyPasteCutI copied = (CopyPasteCutI) copyIitem.clone();
            canvas.getSettings().setCopy(copied);
        }
        canvas.getSettings().setCut(true);
        canvas.removeSelectedDrawing();
        canvasView.refresh();

    }

    private void cutButton(ActionEvent evt) {
        cut();

    }

    private void pasteButton(ActionEvent evt) {
        if (canvas.getSettings().getDrawableCopied() != null) {
            pasteCopy(canvas.getSettings().getLastClick());
        }
    }

    private void copyButton(ActionEvent evt) {
        copy();
    }

    private void delteButton(ActionEvent evt) {
        canvas.removeSelectedDrawing();
    }

    private void fillShape(ActionEvent evt) {
        Container container = (Container) this.canvas.getSelectedDrawing();
        Shape shape = (Shape) container.getContained();
        shape.setFilledColor(canvas.getSettings().getCurrentColor());
        canvasView.refresh();
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
