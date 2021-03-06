/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        if(ae.getActionCommand().equals("Multiplex Page Rank")){
           
            ui.getjDialog2().setVisible(true);
        }
        if(ae.getActionCommand().equals("Cancel")){
            
            ui.getjDialog2().dispose();
            
        }
        if(ae.getActionCommand().equals("OK")){
            
            
            MultilevelSparseMultigraph mg = ui.getMg();
            double beta = Integer.parseInt(ui.getPageRankOptions2().getBetaValueBox().getSelectedItem().toString());
            double gamma = Integer.parseInt(ui.getPageRankOptions2().getGammaValueBox().getSelectedItem().toString());
            try {
                if(mg.getLayerList().isEmpty()){
                    throw new Exception("Error! No graph found, open file or check if the opened file has the proper syntax.\n");              
                }
                if(!mg.getMultiEdges().isEmpty()){                
                    throw new Exception("The graph is not a multiplex.\n");
                }
                if(ui.getPageRankOptions2().getAlphaValueField().getText().equals("")){
                    throw new Exception("Error! PageRank parameters should be > 0.");
                }
                double alpha = Double.parseDouble(ui.getPageRankOptions2().getAlphaValueField().getText());
                MultiplexPageRank mpr = new MultiplexPageRank(mg, alpha, beta, gamma);
                stp.setCentralityTab(mpr.getX(), "Page Rank");
                stp.getjLabel21().setText("Average Vertex PR: " + mpr.getAvgX());
                stp.getjLabel22().setText("alpha (α) = " + alpha);
                stp.getjLabel23().setText("beta (β) = " + beta);
                stp.getjLabel24().setText("gamma (γ) = " + gamma);
                stp.getjLabel25().setText("Page Rank");
                
                ui.getMenuItem(15).setEnabled(true);
                ui.getLogTxtArea1().append("Calculated Page Rank (a: " + alpha + ", β: " + beta + ", γ: " + gamma + ").\n");
            } catch (Exception ex) {
                ui.getErrorDialog().setVisible(true);
                ui.setEnabled(false);                
                ui.getLogTxtArea1().appendError(ex.getMessage());
            }
            ui.getjDialog2().dispose();
            
        }
    }
    
}
