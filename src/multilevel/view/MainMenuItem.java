/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.view;
import javax.swing.*;
/**
 *
 * @author Spiros
 */
public class MainMenuItem extends JMenuItem{
        
    public MainMenuItem(String text, Icon icon, String tooltipText){
        super.setText(text);
        super.setIcon(icon);
        super.setToolTipText(tooltipText);
    }
    
    public MainMenuItem(String text, String tooltipText){
        super.setText(text);
        super.setToolTipText(tooltipText);
    }
    
    public MainMenuItem(String text, Icon icon){
        super.setText(text);
        super.setIcon(icon);
    }
}
