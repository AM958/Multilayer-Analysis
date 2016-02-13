/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.view;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
/**
 *
 * @author Spiros
 */
public class GraphFoundationPanel extends JPanel{
    
    private GraphZoomScrollPane gZSP;
    private Graph<Integer, String> g;
    private List<VisualizationViewer<Integer, String>> vv;
    private CardLayout graphCardLayout;
    private GridLayout graphGridLayout;
    private JButton openButton;
    private String fileName;
    /**
     *
     */
    public GraphFoundationPanel(){
        super();
        this.vv = new ArrayList<VisualizationViewer<Integer, String>>();
        initComponents();
        //jPanel7 = new JPanel();
        /*g = new drawGraph().randomGraph();
        drawGraphPane g1 = new drawGraphPane();
        GraphZoomScrollPane scrollPane1 = new GraphZoomScrollPane(g1.drawGraphZoomScrollPane(g));
        scrollPane1.setBackground(new java.awt.Color(127, 135, 92));
        GraphZoomScrollPane scrollPane2 = new GraphZoomScrollPane(g1.drawGraphZoomScrollPane(g));
        scrollPane2.setBackground(new java.awt.Color(127, 135, 92));
        super.add(scrollPane1);
        super.add(scrollPane2);

        /*JButton btn = new JButton();
        btn.setText("Open...");
        super.add(btn);*/



        // Code of sub-components and layout - not shown here

        //jSplitPane2.setTopComponent(jPanel7);
    }

    public void initComponents(){
        
        this.setVisible(true);
        this.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        setOpenButton(new JButton());
        getOpenButton().setContentAreaFilled(false);
        getOpenButton().setText("Click here to open new graph...");
        getOpenButton().setIcon(new ImageIcon(getClass().getResource("/img/32x32/document-open.png")));

        this.setLayout(new BorderLayout());
        this.add(getOpenButton(), BorderLayout.CENTER);
        
    }
    
    public GraphZoomScrollPane getGraph(){
        return gZSP;
    }
    
    /**
     *
     * @param gvv
     */
    public void setGraph(VisualizationViewer[] gvv){
       
        this.removeAll();
        if(graphGridLayout == null)
            graphGridLayout = new GridLayout(2, 2);
        if(graphCardLayout != null)
            graphCardLayout = null;
                
        this.setLayout(graphGridLayout);
        getVv().clear();
        for (VisualizationViewer gvv1 : gvv) {
            getVv().add(gvv1);
            GraphZoomScrollPane scrollPane = new GraphZoomScrollPane(gvv1);
            this.add(scrollPane);
        }
        this.repaint();
        this.revalidate();

    }
    
    public void setGraph(VisualizationViewer gvv){

        if(graphGridLayout == null){
            graphGridLayout = new GridLayout(2, 2);
            this.setLayout(graphGridLayout);
            this.vv = new ArrayList<>();
        }

        getVv().add(gvv);
        GraphZoomScrollPane scrollPane = new GraphZoomScrollPane(gvv);
        this.add(scrollPane);

    }
    
    public void updateCardLayout(String visibleGraph){
        this.removeAll();
        
        if(graphCardLayout == null){
            graphCardLayout = new CardLayout();
            graphGridLayout = null;
            this.setLayout(graphCardLayout);
            scaleGraph(2);
            //ScalingControl scaler = new CrossoverScalingControl();
            //for (VisualizationViewer gvv1 : vv) {
                //scaler.scale(gvv1, 2, new Point2D.Double(0, 0));
                //gvv1.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW).setScale(1.1f, 1.1f, new Point2D.Double(0, 0));

            //}
            
        }
        int counter = 0;
        for (VisualizationViewer gvv1 : vv){
            
            GraphZoomScrollPane scrollPane = new GraphZoomScrollPane(gvv1);
            this.add("Graph " + ++counter, scrollPane);
        } 
            
        
        this.setLayout(graphCardLayout);
        graphCardLayout.show(this, visibleGraph);
        this.setVisible(true);
        this.repaint();
        this.revalidate();
    }

    public void updateGridLayout(){
        this.removeAll();
        if(graphGridLayout == null){
            graphGridLayout = new GridLayout(2, 2);
            graphCardLayout = null;
            scaleGraph(0.5f);
        }
        
        this.setLayout(graphGridLayout);
         for (VisualizationViewer gvv1 : vv) {
            GraphZoomScrollPane scrollPane = new GraphZoomScrollPane(gvv1);
            super.add(scrollPane);
        }
        this.setVisible(true);
        this.repaint();
        this.revalidate();
        
    }
    
    public void scaleGraph(float amount){
        ScalingControl scaler = new CrossoverScalingControl();
            for (VisualizationViewer gvv1 : vv) {
                scaler.scale(gvv1, amount, new Point2D.Double(0, 0));
                //gvv1.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW).setScale(amount, amount, new Point2D.Double(0, 0));

            }
    }
    
    
    /**
     * @return the VisualizationViewer
     */
    public List<VisualizationViewer<Integer, String>> getVv() {
        return vv;
    }

    /**
     * @param vv the VisualizationViewer to set
     */
    public void setVv(List<VisualizationViewer<Integer, String>> vv) {
        this.vv = vv;
    }

    /**
     * @return the openButton
     */
    public JButton getOpenButton() {
        return openButton;
    }

    /**
     * @param openButton the openButton to set
     */
    public void setOpenButton(JButton openButton) {
        this.openButton = openButton;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the graphCardLayout
     */
    public CardLayout getGraphCardLayout() {
        return graphCardLayout;
    }

    /**
     * @param graphCardLayout the graphCardLayout to set
     */
    public void setGraphCardLayout(CardLayout graphCardLayout) {
        this.graphCardLayout = graphCardLayout;
    }

    /**
     * @return the graphGridLayout
     */
    public GridLayout getGraphGridLayout() {
        return graphGridLayout;
    }

    /**
     * @param graphGridLayout the graphGridLayout to set
     */
    public void setGraphGridLayout(GridLayout graphGridLayout) {
        this.graphGridLayout = graphGridLayout;
    }


    
}
