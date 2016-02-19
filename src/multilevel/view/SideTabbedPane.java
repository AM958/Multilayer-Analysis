/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.view;

import edu.uci.ics.jung.graph.util.Pair;
import java.util.Collection;
import java.util.Comparator;
import multilevel.model.Layer;
import java.util.Map;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import multilevel.model.Edge;
import multilevel.model.MultiLevelEdge;
import multilevel.model.MultilevelSparseMultigraph;
import multilevel.model.Vertex;

/**
 *
 * @author Spiros
 */
public class SideTabbedPane extends javax.swing.JPanel {

    /**
     * Creates new form tabbedPane1
     */
    public SideTabbedPane() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        ExportButton = new javax.swing.JButton();

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.RIGHT);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Layer 1", "Vertex 1", "Layer 2", "Vertex 2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane2.setVisible(false);

        jTabbedPane1.addTab("<html> I<br>n<br>f<br>o</html>", jPanel3);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Layer 1", "Vertex 1", "Layer 2", "Vertex 2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane3.setVisible(false);

        jTabbedPane1.addTab("<html> E<br>d<br>g<br>e<br>s</html>", jPanel4);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vertex", "Centrality"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable4);

        ExportButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/document-save.png"))); // NOI18N
        ExportButton.setText("Export Values");
        ExportButton.setToolTipText("Export Values");
        ExportButton.setActionCommand("Export");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(ExportButton)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ExportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane4.setVisible(false);

        jTabbedPane1.addTab("<html> C<br>e<br>n<br>t<br></html>", jPanel5);
        jPanel5.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("");
        jTabbedPane1.removeTabAt(2);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExportButton;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the jTable2
     */
    public javax.swing.JTable getjTable2() {
        return jTable2;
    }

    /**
     * @param jTable2 the jTable2 to set
     */
    public void setjTable2(javax.swing.JTable jTable2) {
        this.jTable2 = jTable2;
    }
    
        /**
     * @return the jTable2
     */
    public javax.swing.JTable getjTable3() {
        return jTable3;
    }

    /**
     * @param jTable3 the jTable2 to set
     */
    public void setjTable3(javax.swing.JTable jTable3) {
        this.jTable3 = jTable3;
    }

    /**
     * @return the jLabel10
     */
    public javax.swing.JLabel getjLabel10() {
        return jLabel10;
    }

    /**
     * @param txt
     */
    public void setjLabel10txt(String txt) {
        this.jLabel10.setText(txt);
    }
    
    public void setLayerInfo(MultilevelSparseMultigraph multilevelGraph, boolean multiplex){
        
        DefaultTableModel model3 = (DefaultTableModel) jTable3.getModel();
        model3.setRowCount(0);
        DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();
        model2.setRowCount(0);
        jTable2.clearSelection();
        jTable3.clearSelection();

        
        RowSorter<TableModel> sorter3 = new TableRowSorter<>(jTable3.getModel());
        RowSorter<TableModel> sorter2 = new TableRowSorter<>(jTable2.getModel());

        jTable2.setRowSorter(sorter2);
        jTable3.setRowSorter(sorter3);
        
        jLabel7.setText("Layers: " + multilevelGraph.getLayerList().size() );
        jLabel13.setText("Layers: " + multilevelGraph.getLayerList().size() );
        
        int vnum = 0;
        int ednum = 0;
        int mlednum = 0;
        
        for(int layerKey: multilevelGraph.getLayerList().keySet()){
            
            
            
            String gName = multilevelGraph.getGraphName(layerKey);
            //String ln = multilevelGraph.getLayerList().get(layerKey).getVertices().toString();
           
            vnum += multilevelGraph.getLayerList().get(layerKey).getVertexCount();
            for(Object vertexKey: multilevelGraph.getLayerList().get(layerKey).getEdges()){
                model3.addRow(new Object[]{"Column 1", "Column 2", "Column 3", "Column4"});
                Pair vp = multilevelGraph.getLayerList().get(layerKey).getEndpoints(vertexKey);
                System.out.println(vp.getFirst().getClass());
                jTable3.setValueAt(gName, ednum, 0);
                jTable3.setValueAt(vp.getFirst(), ednum, 1);
                jTable3.setValueAt(gName, ednum, 2);
                jTable3.setValueAt(vp.getSecond(), ednum, 3);
               
                ednum++;
            }
            
            if(multiplex){
                for(Object vertex: multilevelGraph.getLayerList().get(layerKey).getVertices()){
                    
                    if(layerKey == 1){
                        model2.addRow(new Object[]{"Column 1", "Column 2", "", ""});
                            jTable2.setValueAt("All Layers", mlednum, 0);
                            jTable2.setValueAt(vertex, mlednum, 1);
                            mlednum++;
                    }
                    /*else{
                        int mle = mlednum;
                        for(int i = 0; i < model2.getRowCount(); i++){
                            if(jTable2.getValueAt(i, 1).equals(vertex.toString()))
                                break;
                            if(i == model2.getRowCount()-1){
                                model2.addRow(new Object[]{"Column 1", "Column 2", "", ""});
                                jTable2.setValueAt("All Layers", mlednum, 0);
                                jTable2.setValueAt(vertex, mlednum, 1);
                                mlednum++;
                                break;
                            }
                            
                        }
                        
                    }*/
                    
                            
                }
            }
        }
        
        
        for(MultiLevelEdge mlKey: multilevelGraph.getMultiEdges()){
            
            model2.addRow(new Object[]{"Column 1", "Column 2", "Column 3", "Column4"});
            jTable2.setValueAt(mlKey.getLayer1(), mlednum, 0);
            jTable2.setValueAt(mlKey.getVertex1(), mlednum, 1);
            jTable2.setValueAt(mlKey.getLayer2(), mlednum, 2);
            jTable2.setValueAt(mlKey.getVertex2(), mlednum, 3);
            mlednum++;
            
        }
        //if(multiedges != null){
        /*for(int mledgeKey: multiedges.keySet()){   
            //vnum += layerList.get(layerKey).getVertices().size();
            //for (int  Ekey : layerList.get(layerKey).getEdges().keySet()) {
                
            
            model2.addRow(new Object[]{"Column 1", "Column 2", "Column 3", "Column4"});
            MultiLevelEdge tempME = multiedges.get(mledgeKey);
                System.out.println(tempME);    
                //Edge e = layerList.get(layerKey).getEdges().get(Ekey);
                //String ln = layerList.get(layerKey).getLayerName();
            jTable2.setValueAt(tempME.getLayer1(), mlednum, 0);
            jTable2.setValueAt(tempME.getVertex1(), mlednum, 1);
            jTable2.setValueAt(tempME.getLayer2(), mlednum, 2);
            jTable2.setValueAt(tempME.getVertex2(), mlednum, 3);
                //
                //getG().addEdge(e.getEdgeName(), e.getVertex1(), e.getVertex2());
            
            mlednum++;
        }*/
        //}
        
        jLabel8.setText("Vertices: " + vnum);
        jLabel14.setText("Vertices: " + vnum);
        jLabel18.setText("Edges");
        jLabel12.setText("Multilayer Edges");
        
        jScrollPane3.setVisible(true);
        jScrollPane2.setVisible(true);

    }

    /**
     * @return the jTable4
     */
    public javax.swing.JTable getjTable4() {
        return jTable4;
    }

    /**
     * @param jTable4 the jTable4 to set
     */
    public void setjTable4(javax.swing.JTable jTable4) {
        this.jTable4 = jTable4;
    }
    
    
    public void setCentralityTab(Map<Vertex, Float> Centrality, String centralityType){
        
        resetTabPane();
        
        DefaultTableModel model4 = (DefaultTableModel) jTable4.getModel();
        model4.setRowCount(0);

        jTable4.clearSelection();
        
        jTable4.getColumnModel().getColumn(1).setHeaderValue(centralityType);
        
        //RowSorter<TableModel> sorter4 = new TableRowSorter<>(jTable4.getModel());
        //jTable4.setRowSorter(sorter4);

        ////////////////////////
        jTable4.setAutoCreateRowSorter(true);
        TableRowSorter<DefaultTableModel> rowSorter = (TableRowSorter<DefaultTableModel>)jTable4.getRowSorter();
        rowSorter.setComparator(1, new Comparator<Object>() {

            @Override
            public int compare(Object o1, Object o2) {
            
            Float int1 = (Float) o1;
            Float int2 = (Float) o2;
            return int1.compareTo(int2);
            }

            @Override
            public boolean equals(Object o2) {
                return this.equals(o2);
            }
        });
        
        /////////////////////////

       
        jLabel19.setText(jLabel7.getText());
        jLabel20.setText(jLabel14.getText());

        int num = 0;
        for(Vertex v: Centrality.keySet()){
            model4.addRow(new Object[]{"Column 1", "Column 2"});
            jTable4.setValueAt(v, num, 0);
            jTable4.setValueAt(Centrality.get(v), num, 1);
   
            num++;
        }
        jTabbedPane1.addTab("<html> C<br>e<br>n<br>t<br>r</html>", jPanel5);
        //jTabbedPane1.addTab("Centrality", jPanel5);
        jPanel5.setVisible(true);
        jScrollPane4.setVisible(true);
        jTabbedPane1.setSelectedComponent(jPanel5);
    }
    
    public void resetTabPane(){
        //this.repaint();
        
        //this.repaint();
        jTabbedPane1.remove(jPanel5);
        jLabel21.setText("");
        jLabel22.setText("");
        jLabel23.setText("");
        jLabel24.setText("");
        jLabel25.setText("");
                
        //initComponents();  
    }

    /**
     * @return the jLabel21
     */
    public javax.swing.JLabel getjLabel21() {
        return jLabel21;
    }

    /**
     * @return the jLabel22
     */
    public javax.swing.JLabel getjLabel22() {
        return jLabel22;
    }

    /**
     * @return the jLabel23
     */
    public javax.swing.JLabel getjLabel23() {
        return jLabel23;
    }

    /**
     * @return the jLabel24
     */
    public javax.swing.JLabel getjLabel24() {
        return jLabel24;
    }

    /**
     * @return the jLabel25
     */
    public javax.swing.JLabel getjLabel25() {
        return jLabel25;
    }

    /**
     * @return the ExportButton
     */
    public javax.swing.JButton getExportButton() {
        return ExportButton;
    }

}
