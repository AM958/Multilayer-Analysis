/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import multilevel.view.MainUI;

/**
 *
 * @author Spiros
 */
public class SelectLayoutDialogListener implements ActionListener{

    private final MainUI parent;
    
    public SelectLayoutDialogListener(MainUI parent){
        this.parent = parent;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand().equals("Select Layout")){
            parent.getLayoutSelectionDialog().setVisible(true);
            
        }

    }
    
}
