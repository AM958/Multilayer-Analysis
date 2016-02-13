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
            parentUI.getLogTxtArea1().append("Calculated Degree Centrality for each Vertex.\n");
        } catch (Exception ex) {
            SimpleAttributeSet red = new SimpleAttributeSet();
            StyleConstants.setForeground(red, Color.RED);
            parentUI.getLogTxtArea1().append(ex.getMessage(), red);
        }
    }
    
}
