/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.gre.ma8521e.simpledrawer.GUI;

import java.util.HashMap;
import uk.ac.gre.ma8521e.simpledrawer.GUI.Views.CanvasOptionsView;
import uk.ac.gre.ma8521e.simpledrawer.GUI.Views.RightMenuView;


public class ViewsFactory {
   
    static final HashMap<String, Class> VIEWS = new HashMap<>();

    static {
        VIEWS.put("Canvas Options", CanvasOptionsView.class);
        VIEWS.put("Right Click Menu", RightMenuView.class);
    }

    public static View createView(String type) {
        try {
            if (VIEWS.containsKey(type)) {
                Class theClass = (Class) VIEWS.get(type);
                return (View) theClass.newInstance();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
 
}
