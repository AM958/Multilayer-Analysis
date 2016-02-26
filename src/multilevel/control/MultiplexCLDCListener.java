/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import multilevel.model.MultilayerCLDC;
import multilevel.model.MultilevelSparseMultigraph;
import multilevel.model.MultiplexCLDC;
import multilevel.view.MainUI;
import multilevel.view.SideTabbedPane;

/**
 *
 * @author Spiros
 */
public class MultiplexCLDCListener implements ActionListener{

    private MultilevelSparseMultigraph mg;
    private final MainUI parentUI;
    private final SideTabbedPane stp;
    private boolean inOrOut;
    private boolean undirected;
    
    public MultiplexCLDCListener(MainUI parent){
        this.parentUI = parent;
        this.stp = parent.getTabbedPane11();
        this.inOrOut = false;
        this.undirected = false;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            this.mg = parentUI.getMg();
            
            if(parentUI.getMg().getLayerList().isEmpty()){
               throw new Exception("Error! No graph found, open file or check if the opened file has the proper syntax.\n");              
            }
            
            switch (ae.getActionCommand()) {
                case "C-L In-Degree":
                    inOrOut = true;
                    undirected = false;
                    setComboBoxModel();
                    break;
                case "C-L Out-Degree":
                    inOrOut = false;
                    undirected = false;
                    setComboBoxModel();
                    break;
                case "Undirected C-L Degree":
                    undirected = true;
                    setComboBoxModel();
                    break;
                case "OK":
                    parentUI.getCldcDialog().dispose();
                    if(parentUI.getMg().getMultiEdges().isEmpty()){
                        MultiplexCLDC degreeC = new MultiplexCLDC(parentUI.getMg(), parentUI.getCldcComboBox().getSelectedIndex() , parentUI.getCldcNormalize().isSelected());
                        degreeC.setInOrOut(inOrOut);
                        degreeC.calc();
                        
                        String type = getDegType();
                        
                        String tableTitle = (parentUI.getCldcComboBox().getSelectedItem()) + "-layer " + type + "Degree";
                        stp.setCentralityTab(degreeC.getCldc(), tableTitle);
                    }
                    else{
                        MultilayerCLDC degreeC = new MultilayerCLDC(parentUI.getMg(), parentUI.getCldcComboBox().getSelectedIndex() , parentUI.getCldcNormalize().isSelected());
                        degreeC.setInOrOut(inOrOut);
                        degreeC.calc();
                        String type = getDegType();
                        String tableTitle = (parentUI.getCldcComboBox().getSelectedItem()) + "-layer " + type + "Degree";
                        stp.setCentralityTab(degreeC.getCldc(), tableTitle);
                    }   
                    if(inOrOut == true){
                        stp.getjLabel25().setText("Cross Layer In-Degree Centrality");
                        parentUI.getLogTxtArea1().append("Calculated Cross-Layer In-Degree Centrality for each Vertex.\n");
                    }
                    else{
                        if(undirected){
                            stp.getjLabel25().setText("Cross Layer Undirected Degree Centrality");
                            parentUI.getLogTxtArea1().append("Calculated Cross-Layer Undirected Degree Centrality for each Vertex.\n");
                        }
                        else{
                            stp.getjLabel25().setText("Cross Layer Out-Degree Centrality");
                            parentUI.getLogTxtArea1().append("Calculated Cross-Layer Out-Degree Centrality for each Vertex.\n");
                        }
                    }   parentUI.getMenuItem(15).setEnabled(true);
                    parentUI.getCldcOK().removeActionListener(this);
                    parentUI.getCldcCancel().removeActionListener(this);
                    break;
                case "Cancel":
                    parentUI.getCldcDialog().dispose();
                    parentUI.getCldcOK().removeActionListener(this);
                    parentUI.getCldcCancel().removeActionListener(this);
                    break;
            }

        } catch (Exception ex) {
            parentUI.getErrorDialog().setVisible(true);
            parentUI.setEnabled(false);
            parentUI.getCldcOK().removeActionListener(this);
            parentUI.getCldcCancel().removeActionListener(this);
            parentUI.getLogTxtArea1().appendError(ex.getMessage());
        }
    }
    
    public final void setComboBoxModel(){
        parentUI.getCldcOK().addActionListener(this);
        parentUI.getCldcCancel().addActionListener(this);
        DefaultComboBoxModel cldcModel = new DefaultComboBoxModel();

        for(int ln = 1; ln <= mg.getLayerList().size(); ln++){
            cldcModel.addElement(ln);
        }
        parentUI.getCldcComboBox().setModel(cldcModel);
        parentUI.getCldcDialog().setVisible(true);
    }
    
    public final String getDegType(){
        if(undirected){
            return "Undir-";
        }
        else if(inOrOut == true){
            return "In-";
        }
        return "Out-";
    }
    
}
