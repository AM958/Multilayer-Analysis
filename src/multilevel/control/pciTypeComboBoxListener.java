/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import multilevel.model.MultilevelSparseMultigraph;
import multilevel.model.pciType;
import multilevel.view.MainUI;
import multilevel.view.PCIOptions;

/**
 *
 * @author Spiros
 */
public class pciTypeComboBoxListener implements ActionListener{
    MultilevelSparseMultigraph mg;
    MainUI parentUI;
    PCIOptions pciOpt;
    pciType type;
    
    public pciTypeComboBoxListener(MainUI parent){
        this.parentUI = parent;
        this.pciOpt = parent.getPciOpt();
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try{
            getType();
            if(type == pciType.MINIMAL || type == pciType.SCA){
                pciOpt.getNumberOfLayersComboBox().setEnabled(true);
            }
            else{
                pciOpt.getNumberOfLayersComboBox().setEnabled(false);
            }
        }
        catch(Exception ex){
            parentUI.getLogTxtArea1().appendError(ex.getMessage());
        }
    }
    
    public final void getType(){
        switch(parentUI.getPciOpt().getPciMethodComboBox().getSelectedIndex()){

                    case 1:
                        type = pciType.AGNOSTIC;
                        break;
                    case 2:
                        type = pciType.ALL;
                        break;
                    case 3:
                        type = pciType.SYMMETRIC;
                        break;
                    case 4:
                        type = pciType.MINIMAL;
                        break;
                    case 5:
                        type = pciType.SCA;
                        break;
                    case 0:
                    default:
                        type = pciType.ORIGINAL;
                        break;
                           
                }
    }
}
