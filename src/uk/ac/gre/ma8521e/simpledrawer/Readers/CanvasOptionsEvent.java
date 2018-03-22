/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.gre.ma8521e.simpledrawer.Readers;

import java.awt.Color;

/**
 *
 * @author micae
 */
public class CanvasOptionsEvent {

    private Color background;

    public CanvasOptionsEvent(Color color) {
        this.background = color;
    }

    public Color getBackground() {
        return this.background;
    }
}
