/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.control;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import multilevel.model.MultilevelSparseMultigraph;
import multilevel.model.MultilplexBetweennessCentrality;
import multilevel.view.MainUI;
import multilevel.view.SideTabbedPane;

/**
 *
 * @author spiros
 */
public class MultiplexBetweennessListener implements ActionListener{

    MultilevelSparseMultigraph mg;
    MainUI parentUI;
    SideTabbedPane stp;
    
    public MultiplexBetweennessListener(MainUI parent){
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
            MultilplexBetweennessCentrality mbc = new MultilplexBetweennessCentrality(parentUI.getMg());
            stp.setCentralityTab(mbc.getBetweenness(), "Betweenness Centrality");
            stp.getjLabel25().setText("Betweenness Centrality");
            parentUI.getLogTxtArea1().append("Calculated Betweenness Centrality for each Vertex.\n");
        } catch (Exception ex) {
            parentUI.getErrorDialog().setVisible(true);
            parentUI.setEnabled(false);
            parentUI.getLogTxtArea1().appendError(ex.getMessage());
            
        }
    }
    
}
