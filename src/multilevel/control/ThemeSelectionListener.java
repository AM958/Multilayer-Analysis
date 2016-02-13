/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;
import multilevel.view.MainUI;

/**
 *
 * @author Spiros
 */
public class ThemeSelectionListener implements ActionListener{

    private MainUI parent;
    
    public ThemeSelectionListener(MainUI parent){
        this.parent = parent;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            javax.swing.UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
            javax.swing.SwingUtilities.updateComponentTreeUI(parent);
            parent.pack();
            parent.setExtendedState(JFrame.MAXIMIZED_BOTH);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ThemeSelectionListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
