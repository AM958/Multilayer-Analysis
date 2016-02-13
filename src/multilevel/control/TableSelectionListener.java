/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.control;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.picking.PickedState;
import java.awt.Color;
import java.util.List;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import multilevel.view.MainUI;
import java.awt.Paint;
import multilevel.model.MultiLevelEdge;
import multilevel.model.MultilevelSparseMultigraph;
import multilevel.model.Vertex;
import org.apache.commons.collections15.Transformer;


/**
 *
 * @author Spiros
 */
public class TableSelectionListener implements ListSelectionListener{

    JTable table;
    List<VisualizationViewer<Integer, String>> vv;
    MultilevelSparseMultigraph mg;
    MainUI ui;
    
    public TableSelectionListener(MainUI parentUI, JTable table){
        this.table = table;
        this.vv = parentUI.getGraphFoundationPanel1().getVv();
        this.ui = parentUI;
        
        
    }
    
    @Override
    public void valueChanged(ListSelectionEvent lse) {

        
        this.mg = ui.getMg();
        
        if(vv!= null && table.getSelectedRow() >= 0){
            
            for (final VisualizationViewer gvv1 : vv) {               
                
                final PickedState<Object> pickedState = gvv1.getPickedVertexState();
                
                pickedState.clear();
                final Object t1 = table.getValueAt(table.getSelectedRow(), 1);
                final Object l1 = table.getValueAt(table.getSelectedRow(), 0);
                final Object t2;
                final Object l2;
                if(table.getColumnCount() > 2){
                    t2 = table.getValueAt(table.getSelectedRow(), 3);
                    l2 = table.getValueAt(table.getSelectedRow(), 2);
                }
                else{
                    t2 = table.getValueAt(table.getSelectedRow(), 0);
                    l2 = null;
                }
                
                // Transformer maps the vertex number to a vertex property
                Transformer<Object,Paint> vertexColor;

                vertexColor = new Transformer<Object,Paint>() {
                    @Override
                    public Paint transform(Object i) {
                        if(gvv1.getPickedVertexState().isPicked(i)){
                            if (i.equals(t1) || i.equals(t2)) {
                                return Color.GREEN;
                            }
                            else{
                                return Color.YELLOW;
                            }
                        }
                        return Color.RED;
                    }
                };
                
                Transformer<Object,Paint> vertexColor2;

                vertexColor2 = new Transformer<Object,Paint>() {
                    @Override
                    public Paint transform(Object i) {
                        if(gvv1.getPickedVertexState().isPicked(i)){
                            return Color.YELLOW;
                        }
                        return Color.RED;
                    }
                };           
                               
                if(table.getColumnCount() > 2){
                    
                    if(gvv1.getName().equals(l1.toString()))
                        pickedState.pick( t1, true);
                    
                    if(gvv1.getName().equals(l2.toString()))
                        pickedState.pick( t2, true);
                    if(!l1.toString().equals(l2.toString())){
                        gvv1.getRenderContext().setVertexFillPaintTransformer(vertexColor);
                    }
                    else{
                        gvv1.getRenderContext().setVertexFillPaintTransformer(vertexColor2);
                    }
                    
                    
                }
                else{

                    pickedState.pick( t2, true);
                    gvv1.getRenderContext().setVertexFillPaintTransformer(vertexColor);
                    
                    if(!mg.getMultiEdges().isEmpty()){
                        
                        for(MultiLevelEdge mle : mg.getMultiEdges()){
                            if(mle.getVertex1().toString().equals(t2.toString())){
                               
                                pickedState.pick(mle.getVertex2(), true);
                            }
                            else if(mle.getVertex2().toString().equals(t2.toString())){
                                
                                pickedState.pick(mle.getVertex1(), true);
                            }
                        }
                    }
                }
                if(table.getValueAt(table.getSelectedRow(), 0).toString().equalsIgnoreCase("All Layers")){
                    pickedState.pick( t1, true);
                    gvv1.getRenderContext().setVertexFillPaintTransformer(vertexColor);
                }

            }
            
        }
        
    }
    
}
