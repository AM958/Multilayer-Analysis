/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import multilevel.model.MultilevelSparseMultigraph;
import multilevel.model.MultiplexCLDC;
import multilevel.model.MultiplexDegreeCentrality;
import multilevel.view.MainUI;
import multilevel.view.SideTabbedPane;

/**
 *
 * @author Spiros
 */
public class MultiplexCLDCListener implements ActionListener{

    MultilevelSparseMultigraph mg;
    MainUI parentUI;
    SideTabbedPane stp;
    
    public MultiplexCLDCListener(MainUI parent){
        this.parentUI = parent;
        this.stp = parent.getTabbedPane11();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            this.mg = parentUI.getMg();
            if(parentUI.getMg().getLayerList().isEmpty()){
               throw new Exception("Error! No graph found, open file or check if the opened file has the proper syntax.\n");              
            }
            if(!parentUI.getMg().getMultiEdges().isEmpty()){                
                throw new Exception("The graph is not a multiplex.\n");
            }
            if(ae.getActionCommand().equals("Multiplex Undirected C-L Degree")){
                
                
                DefaultComboBoxModel cldcModel = new DefaultComboBoxModel();

                for(int ln = 1; ln <= mg.getLayerList().size(); ln++){
                    
                    cldcModel.addElement(ln);
                }
                parentUI.getCldcComboBox().setModel(cldcModel);
                parentUI.getCldcDialog().setVisible(true);
            }
            else if(ae.getActionCommand().equals("OK")){
                parentUI.getCldcDialog().dispose();
                MultiplexCLDC degreeC = new MultiplexCLDC(parentUI.getMg(), parentUI.getCldcComboBox().getSelectedIndex() , parentUI.getCldcNormalize().isSelected());
                if(ae.getActionCommand().equals("Multiplex In-Degree")){
                    degreeC.setInOrOut(true);
                }
                else{
                    degreeC.setInOrOut(false);
                }
                degreeC.calc();
                //stp.setDegreeCentralityTab(degreeC.getDegreeCentrality(), degreeC.getAverageDegree());
                stp.setCentralityTab(degreeC.getCldc(), "Cross Layer Degree");
                //stp.getjLabel21().setText("Average Vertex Degree: " + degreeC.getAverageDegree());
                stp.getjLabel25().setText("Cross Layer Degree Centrality");
                parentUI.getMenuItem(15).setEnabled(true);
                parentUI.getLogTxtArea1().append("Calculated Degree Centrality for each Vertex.\n");
            }
            else if(ae.getActionCommand().equals("Cancel")){
                parentUI.getCldcDialog().dispose();
            }

        } catch (Exception ex) {
            parentUI.getErrorDialog().setVisible(true);
            parentUI.setEnabled(false);
            parentUI.getLogTxtArea1().appendError(ex.getMessage());
        }
    }
    
}
