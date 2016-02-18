/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.control;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import multilevel.model.MultilevelSparseMultigraph;
import multilevel.view.GraphFoundationPanel;
import multilevel.view.MainUI;
import multilevel.view.RedrawGraphPaneWithLayout;

/**
 *
 * @author Spiros
 */
public class SelectLayoutListener implements ActionListener{

    private MainUI parent;
    
    public SelectLayoutListener(MainUI parent){
        this.parent = parent;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            if(ae.getActionCommand().equals("OK")){

                GraphFoundationPanel p = parent.getGraphFoundationPanel1();
                MultilevelSparseMultigraph mlsmg = parent.getMg();

                if(mlsmg.getLayerList().isEmpty()){
                    throw new Exception("Error: Graph is empty!\n");
                }

                p.removeAll();
                VisualizationViewer<Integer, String>[]  g2 = new VisualizationViewer[mlsmg.getLayerList().size()];
                Graph<String, String> g;
                String gName;
                RedrawGraphPaneWithLayout rgpl;
                for (final int layerKey: mlsmg.getLayerList().keySet()){ 

                    g = mlsmg.getLayerList().get(layerKey);
                    gName = mlsmg.getGraphName(layerKey);
                    rgpl = new RedrawGraphPaneWithLayout(parent.getSelectLayoutComboBox().getSelectedIndex(), g);
                    g2[layerKey - 1] = rgpl.drawGraphZoomScrollPane(g, gName);
                    p.repaint();
                    p.revalidate();
                }

                p.setGraph(g2);
                parent.getLogTxtArea1().append("Layout changed to " + parent.getSelectLayoutComboBox().getSelectedItem().toString() + "\n");
                parent.getLayoutSelectionDialog().dispose();

            }
            else if(ae.getActionCommand().equals("Cancel")){
                parent.getLayoutSelectionDialog().dispose();
            }
        } catch (Exception ex) {
            parent.getLayoutSelectionDialog().dispose();
            parent.getErrorDialog().setVisible(true);
            parent.setEnabled(false);
            parent.getLogTxtArea1().appendError(ex.getMessage());
        }
       
           
    }
    
}
