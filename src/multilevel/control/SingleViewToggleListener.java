/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import multilevel.view.MainUI;
import multilevel.view.GraphFoundationPanel;

/**
 *
 * @author Spiros
 */
public class SingleViewToggleListener  implements ActionListener{

    GraphFoundationPanel gFP;
    JComboBox jCB;
    
    public SingleViewToggleListener(MainUI parent){
        this.gFP = parent.getGraphFoundationPanel1();
        this.jCB = parent.getjComboBox1();
    }
    
    /*public SingleViewToggleListener(graphFoundationPanel gFP, JComboBox jCB){
        this.gFP = gFP;
        this.jCB= jCB;
    }*/
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand().equals("Single View")){
            gFP.setGraphCardLayout(null);
            gFP.updateCardLayout("Graph 1");
            jCB.setEnabled(true);
            jCB.setSelectedIndex(0);
        }
        else if(ae.getActionCommand().equals("Multi View")){
            gFP.updateGridLayout();
            jCB.setEnabled(false);
        }
        else{
            gFP.updateCardLayout(jCB.getSelectedItem().toString());
        }
    }
    
}
