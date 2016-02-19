/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import multilevel.model.MultilevelSparseMultigraph;
import multilevel.model.MultiplexDegreeCentrality;
import multilevel.view.MainUI;
import multilevel.view.SideTabbedPane;

/**
 *
 * @author Spiros
 */
public class MultiplexDegreeListener implements ActionListener{

    MultilevelSparseMultigraph mg;
    MainUI parentUI;
    SideTabbedPane stp;
    
    public MultiplexDegreeListener(MainUI parent){
        this.parentUI = parent;
        this.stp = parent.getTabbedPane11();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            if(parentUI.getMg().getLayerList().isEmpty()){
               throw new Exception("Error! No graph found, open file or check if the opened file has the proper syntax.\n");              
            }
            if(!parentUI.getMg().getMultiEdges().isEmpty()){                
                throw new Exception("The graph is not a multiplex.\n");
            }
            
            MultiplexDegreeCentrality degreeC = new MultiplexDegreeCentrality(parentUI.getMg());
            if(ae.getActionCommand().equals("Multiplex In-Degree")){
                degreeC.setInOrOut(true);
            }
            else{
                degreeC.setInOrOut(false);
            }
            degreeC.calcDegree();
            //stp.setDegreeCentralityTab(degreeC.getDegreeCentrality(), degreeC.getAverageDegree());
            stp.setCentralityTab(degreeC.getDegreeCentrality(), "Degree");
            stp.getjLabel21().setText("Average Vertex Degree: " + degreeC.getAverageDegree());
            stp.getjLabel25().setText("Degree Centrality");
            parentUI.getMenuItem(15).setEnabled(true);
            parentUI.getLogTxtArea1().append("Calculated Degree Centrality for each Vertex.\n");
        } catch (Exception ex) {
            parentUI.getErrorDialog().setVisible(true);
            parentUI.setEnabled(false);
            parentUI.getLogTxtArea1().appendError(ex.getMessage());
        }
    }
    
}
