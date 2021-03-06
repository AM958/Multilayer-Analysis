/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import multilevel.model.MultiLayerPCI;
import multilevel.model.MultilevelSparseMultigraph;
import multilevel.model.pciType;
import multilevel.view.MainUI;
import multilevel.view.PCIOptions;
import multilevel.view.SideTabbedPane;

/**
 *
 * @author Spiros
 */
public class MultilayerPCIListener implements ActionListener{
    
    MultilevelSparseMultigraph mg;
    MainUI parentUI;
    SideTabbedPane stp;
    PCIOptions pciOpt;
    pciType type;
    
    public MultilayerPCIListener(MainUI parent){
        this.parentUI = parent;
        this.stp = parent.getTabbedPane11();
        this.pciOpt = parent.getPciOpt();
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        try{
            this.mg = parentUI.getMg();
            if(mg.getLayerList().isEmpty()){
               throw new Exception("Error! No graph found, open file or check if the opened file has the proper syntax.\n");
            }
            
            if(ae.getActionCommand().equals("PCI")){
                
                DefaultComboBoxModel pciModel = new DefaultComboBoxModel();

                for(int ln = 1; ln <= mg.getLayerList().size(); ln++){
                    
                    pciModel.addElement(ln);
                }
                pciOpt.getNumberOfLayersComboBox().setModel(pciModel);
                parentUI.getjDialog1().setVisible(true);
            }
            if(ae.getActionCommand().equals("OK")){
                parentUI.getjDialog1().setVisible(false);
                
                getType();
                int layers = (int) pciOpt.getNumberOfLayersComboBox().getSelectedItem();
                MultiLayerPCI pci = new MultiLayerPCI(mg, type, layers);
                String method = parentUI.getPciOpt().getPciMethodComboBox().getSelectedItem().toString();
                stp.setCentralityTab(pci.getPci(), method);
                stp.getjLabel25().setText(method);
                parentUI.getMenuItem(15).setEnabled(true);
                parentUI.getLogTxtArea1().append("Calculated PCI with method: " + method + "\n");
            }
            if(ae.getActionCommand().equals("Cancel")){
                parentUI.getjDialog1().setVisible(false);
            }
            
            
        }
        catch(Exception ex){
            parentUI.getErrorDialog().setVisible(true);
            parentUI.setEnabled(false);
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
