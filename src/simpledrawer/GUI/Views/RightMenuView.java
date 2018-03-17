/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpledrawer.GUI.Views;


import simpledrawer.GUI.Controllers.RightMenuController;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;



public class RightMenuView extends JPopupMenu {

    private JMenuItem fill = new JMenuItem("Fill");
    private JMenuItem delete = new JMenuItem("Delete");
    private JMenuItem copy = new JMenuItem("Copy");
    private JMenuItem paste = new JMenuItem("Paste");
    private JMenuItem cut = new JMenuItem("Cut");
    private JMenuItem backgroundMenu = new JMenuItem("Change Background");
    RightMenuController rmc;
    public RightMenuView(RightMenuController rmc) {
        this.rmc = rmc;
    }
    public JMenuItem getDelete() {
        return this.delete;
    }

    public JPopupMenu buildPopupMenu(){
        return rmc.buildMenu();
    }
    
    public JMenuItem getFilled() {
        return this.fill;
    }

    public JMenuItem getCopy() {
        return this.copy;
    }

    public JMenuItem getCut() {
        return this.cut;
    }

    public JMenuItem getPaste() {
        return this.paste;
    }

    public JMenuItem getBackgroundMenu() {
        return this.backgroundMenu;
    }

}
