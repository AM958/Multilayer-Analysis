/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.control;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;
import static edu.uci.ics.jung.graph.util.EdgeType.DIRECTED;
import static edu.uci.ics.jung.graph.util.EdgeType.UNDIRECTED;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import multilevel.model.DrawGraph;
import multilevel.model.MultilevelSparseMultigraph;
import multilevel.view.MainUI;
import multilevel.view.DrawGraphPane;
import multilevel.view.GraphFoundationPanel;
import multilevel.view.LogTextArea;
import multilevel.view.NetFileChooser;
import multilevel.view.SideTabbedPane;

/**
 *
 * @author Spiros
 */
public class MenuActionListener implements ActionListener{

    private GraphFoundationPanel gFP;
    private LogTextArea lTA;
    private JComboBox jCB;
    private SideTabbedPane tabPane;
    private MainUI ui;
    
    public MenuActionListener(){
        super();
    }

    public MenuActionListener(MainUI ui){
        super();
        this.ui = ui;
        this.gFP = ui.getGraphFoundationPanel1();
        this.lTA = ui.getLogTxtArea1();
        this.jCB = ui.getjComboBox1();
        this.tabPane = ui.getTabbedPane11();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        //tabPane.getjTable3().getSelectionModel().clearSelection();
        switch (ae.getActionCommand()) {
            case "Exit":
                System.exit(0);
            case "Random Graph (U)":
                //drawGraph[] g = new DrawGraph[4];
                ui.getTabbedPane11().resetTabPane();
                graphGeneration(UNDIRECTED);
                lTA.append("New Undirected Random Graphs were generated.\n");
                break;
            case "Random Graph (D)":
                ui.getTabbedPane11().resetTabPane();
                graphGeneration(DIRECTED);
                lTA.append("New Directed Random Graphs were generated.\n");
                break;
            case "New":
                ui.getErrorDialog().setVisible(true);
                ui.setEnabled(false);
                lTA.appendError("Not implemented yet!\n");
                break;
            case "Open":
            case "Click here to open new graph...":
                NetFileChooser nFC = new NetFileChooser(ui);
                
                DefaultComboBoxModel model = new DefaultComboBoxModel();
                for(int i=0;i<gFP.getVv().size();i++ ){
                    System.out.println("\n" + i + "\n");
                    model.addElement("Graph " +(i+1));
                }
                jCB.setModel(model);
                jCB.setEnabled(false);
                break;
        }
    }

    public final void graphGeneration(EdgeType et){
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        Random rand = new Random();
        
                int  n = rand.nextInt(10) + 1;
                Graph<String, String> g1;
                VisualizationViewer<Integer, String> [] g2 = new VisualizationViewer[n];
                MultilevelSparseMultigraph mg = new MultilevelSparseMultigraph();
                for(int i=0;i<n;i++){
                    
                    gFP.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DrawGraph tempG = new DrawGraph(et);
                    g1 = tempG.randomGraph();
                    mg.addLayer(i, g1, tempG.getGraphName());
                    g2[i] = new DrawGraphPane().drawGraphZoomScrollPane(g1, tempG.getGraphName());
                }
                gFP.setGraphCardLayout(null);
                gFP.setGraph(g2);
                gFP.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                for(int i=0;i<gFP.getVv().size();i++ ){
                    model.addElement("Graph " +(i+1));
                }
                jCB.setModel(model);
                jCB.setEnabled(false);
                
                tabPane.setLayerInfo(mg, true);

                
    }
    
}
