/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.view;
import javax.swing.*;
/**
 *
 * @author Spiros
 */
public class LogGraphSplitPane extends JSplitPane{
    
    private GraphFoundationPanel graphPanel;
    private LogTextArea logTxtArea;
    private JScrollPane jScrollPaneLog;
    private JScrollPane jScrollPaneGraph;
    //private MainUI ui;

    public LogGraphSplitPane(){
        super();
        //this.graphPanel = new GraphFoundationPanel();
        //this.logTxtArea = new LogTextArea();
        
        
    }
    
    public void setUI(MainUI parentUI){
        //this.ui = parentUI;
        this.graphPanel = parentUI.getGraphFoundationPanel1();
        this.logTxtArea = parentUI.getLogTxtArea1();
        initComponents();
    }
    
    /*public logGraphSplitPane(MainUI parentUI){
        super();
        this.graphPanel = parentUI.getGraphFoundationPanel1();
        this.logTxtArea = parentUI.getLogTxtArea1();
        initComponents();
        
    }*/

    public final void initComponents(){
        

        createPanel();

        jScrollPaneLog = new JScrollPane();
        jScrollPaneLog.setViewportView(logTxtArea);
        
        jScrollPaneGraph = new JScrollPane();
        jScrollPaneGraph.setViewportView(graphPanel);
        
        super.setDividerLocation(560);
        super.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        super.setResizeWeight(0.5);
        
        super.setBottomComponent(jScrollPaneLog);
        super.setTopComponent(jScrollPaneGraph);
    }
    
    public void createPanel(){
        
        JPanel foundationPanel = new JPanel();
        foundationPanel.setMinimumSize(new java.awt.Dimension(640, 480));
        GroupLayout jPanel8Layout = new javax.swing.GroupLayout(foundationPanel);
        foundationPanel.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(this, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(this, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
        );
        
    }
    
    /**
     * @return the graphPanel
     */
    public GraphFoundationPanel getGraphPanel() {
        return graphPanel;
    }

    /**
     * @param graphPanel the graphPanel to set
     */
    public void setGraphPanel(GraphFoundationPanel graphPanel) {
        this.graphPanel = graphPanel;
    }

    /**
     * @return the logTxtArea
     */
    public LogTextArea getLogTxtArea() {
        return logTxtArea;
    }

    /**
     * @param logTxtArea the logTxtArea to set
     */
    public void setLogTxtArea(LogTextArea logTxtArea) {
        this.logTxtArea = logTxtArea;
    }
    
}
