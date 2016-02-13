/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.view;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import java.awt.Color;
import java.awt.Cursor;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import multilevel.model.MultiLevelEdge;
import multilevel.model.DrawGraph;
import multilevel.model.FileParser;
import multilevel.model.MultilevelSparseMultigraph;

/**
 *
 * @author Spiros
 */
public class NetFileChooser {
    
    private GraphFoundationPanel p;
    private FileParser fp;
    private LogTextArea lta;
    private Map <Integer, multilevel.model.Layer> layerList;
    private MultilevelSparseMultigraph mlsmg;
    private MainUI ui;
    SideTabbedPane tabPane;
    Graph<String, String>[] g1;
    VisualizationViewer<Integer, String>[]  g2;
    int i;
    DrawGraph[] tempG;
    
    public NetFileChooser(MainUI parentUI){
        this.p = parentUI.getGraphFoundationPanel1();
        this.lta = parentUI.getLogTxtArea1();
        this.tabPane = parentUI.getTabbedPane11();
        this.ui = parentUI;
        parentUI.setMg(null);
        initComponents();
        parentUI.setMg(mlsmg);
        
    }
    
    private final void initComponents(){
        
        JFileChooser c = new JFileChooser();
        Cursor tempCursor = p.getCursor();
        // Demonstrate "Open" dialog:
        int rVal = c.showOpenDialog(p);
        SimpleAttributeSet red = new SimpleAttributeSet();
        StyleConstants.setForeground(red, Color.RED);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            try {
                String fileName = c.getSelectedFile().getName();
                setFp(new FileParser(c.getSelectedFile().getAbsolutePath()));

                //mlsmg.resetAll();
                mlsmg = getFp().getMultilevelGraph();
                
                if(mlsmg.getLayerList().isEmpty()) throw new Exception(fileName);
                ui.getTabbedPane11().resetTabPane();
                ui.setMg(mlsmg);
                
                
                lta.append("Opened file: \"" + fileName + "\" \n");    

                Thread[] t = new Thread[mlsmg.getLayerList().size()];
                g2 = new VisualizationViewer[mlsmg.getLayerList().size()];

                i=0;

                p.removeAll();
                
                long startTime = System.nanoTime();
  
                for (final int layerKey: mlsmg.getLayerList().keySet()){       
                    t[layerKey - 1] = new Thread( new Runnable() {
                        @Override
                        public void run() {
                            p.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                            g2[layerKey - 1] = new DrawGraphPane().drawGraphZoomScrollPane(mlsmg.getLayerList().get(layerKey), mlsmg.getGraphName(layerKey));
                            p.repaint();
                            p.revalidate();
                        }
                    } );
                    t[layerKey - 1].start();

                }

                long stopTime = System.nanoTime();
                long elapsedTime = stopTime - startTime;
                System.out.println("vv " + elapsedTime);
                Map<Integer, MultiLevelEdge> multiLayerEdges = new HashMap();
                if(fp.getMultiLayerEdges() != null){
                    multiLayerEdges = fp.getMultiLayerEdges();
                }
                tabPane.setLayerInfo(mlsmg, fp.isMultiplex());
                //sleep(3000);
                startTime = System.nanoTime();
                for (final int layerKey: mlsmg.getLayerList().keySet()){
                    t[layerKey - 1].join();
                }
                p.setGraph(g2);
                stopTime = System.nanoTime();
                elapsedTime = stopTime - startTime;
                System.out.println("setgraph" + elapsedTime);
                
                p.setCursor(tempCursor);
                lta.append("Graphs from file \"" + fileName + "\" were generated.\n" );                

            } catch (IOException ex) {
                ui.getErrorDialog().setVisible(true);
                ui.setEnabled(false);
                lta.append(ex.toString(), red);
                
            } catch (Exception ex){
                lta.append("ERROR: Graphs from file \"" + ex.getMessage() + "\" could not be generated.\n", red);
                String tip = "Make sure you opened the correct file and it has the correct encoding.\n";
                ui.getErrorDialog().setVisible(true);
                ui.setEnabled(false);
                lta.append(tip, red); 
            }
            

            p.setEnabled(false);
        }
    }
    
    /**
     * @return the fp
     */
    public FileParser getFp() {
        return fp;
    }

    /**
     * @param fp the fp to set
     */
    public void setFp(FileParser fp) {
        this.fp = fp;
    }

    /**
     * @return the layerList
     */
    public Map <Integer, multilevel.model.Layer> getLayerList1() {
        return layerList;
    }

    /**
     * @param layerList the layerList to set
     */
    public void setLayerList1(Map <Integer, multilevel.model.Layer> layerList) {
        this.layerList = layerList;
    }

    /**
     * @return the mlsmg
     */
    public MultilevelSparseMultigraph getMlsmg() {
        return mlsmg;
    }

    /**
     * @param mlsmg the mlsmg to set
     */
    public void setMlsmg(MultilevelSparseMultigraph mlsmg) {
        this.mlsmg = mlsmg;
    }
    
}
