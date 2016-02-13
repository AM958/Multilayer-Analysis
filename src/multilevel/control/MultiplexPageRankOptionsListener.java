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
import multilevel.model.MultiplexPageRank;
import multilevel.view.MainUI;
import multilevel.view.SideTabbedPane;

/**
 *
 * @author Spiros
 */
public class MultiplexPageRankOptionsListener implements ActionListener{

    MainUI ui;
    SideTabbedPane stp;
    
    public MultiplexPageRankOptionsListener(MainUI parentUI){
        this.ui = parentUI;
        this.stp = parentUI.getTabbedPane11();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        //ui.getPageRankOptions2().getjButton2().addActionListener(this);
        //ui.setEnabled(false);
        if(ae.getActionCommand().equals("Multiplex Page Rank")){
            ui.setEnabled(false);
            ui.getjDialog2().setVisible(true);
        }
        if(ae.getActionCommand().equals("Cancel")){
            ui.setEnabled(true);
            ui.getjDialog2().dispose();
            
        }
        if(ae.getActionCommand().equals("OK")){
            ui.setEnabled(true);
            MultilevelSparseMultigraph mg = ui.getMg();
            double beta = Integer.parseInt(ui.getPageRankOptions2().getBetaValueBox().getSelectedItem().toString());
            double gamma = Integer.parseInt(ui.getPageRankOptions2().getGammaValueBox().getSelectedItem().toString());
            try {
                double alpha = Double.parseDouble(ui.getPageRankOptions2().getAlphaValueField().getText());
                
                
                MultiplexPageRank mpr = new MultiplexPageRank(mg, alpha, beta, gamma);
                stp.setCentralityTab(mpr.getX(), "Page Rank");
                stp.getjLabel21().setText("Average Vertex PR: " + mpr.getAvgX());
                stp.getjLabel22().setText("alpha (α) = " + alpha);
                stp.getjLabel23().setText("beta (β) = " + beta);
                stp.getjLabel24().setText("gamma (γ) = " + gamma);
                stp.getjLabel25().setText("Page Rank");
                ui.getjDialog2().dispose();
                
                ui.getLogTxtArea1().append("Calculated Page Rank (a: " + alpha + ", β: " + beta + ", γ: " + gamma + ").\n");
            } catch (Exception ex) {
                SimpleAttributeSet red = new SimpleAttributeSet();
                StyleConstants.setForeground(red, Color.RED);
                ui.setEnabled(true);
                ui.getLogTxtArea1().append(ex.getMessage() + "\n", red);
            }
            
            
        }
    }
    
}
