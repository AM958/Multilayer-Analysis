/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.control;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            System.out.println("hihoi");
            
            MultilplexBetweennessCentrality mbc = new MultilplexBetweennessCentrality(parentUI.getMg());
            stp.setCentralityTab(mbc.getBetweenness(), "Betweenness Centrality");
            //stp.getjLabel21().setText("Average Vertex Degree: " + degreeC.getAverageDegree());
            stp.getjLabel25().setText("Betweenness Centrality");
            parentUI.getLogTxtArea1().append("Calculated Betweenness Centrality for each Vertex.\n");
        } catch (Exception ex) {
            parentUI.getErrorDialog().setVisible(true);
            parentUI.setEnabled(false);
            SimpleAttributeSet red = new SimpleAttributeSet();
            StyleConstants.setForeground(red, Color.RED);
            ex.printStackTrace();
            parentUI.getLogTxtArea1().append(ex.getMessage(), red);
            
        }
    }
    
}
