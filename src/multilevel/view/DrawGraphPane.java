/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.view;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.picking.PickedState;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;


import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import org.apache.commons.collections15.functors.ConstantTransformer;

/**
 *
 * @author Spiros
 */
public class DrawGraphPane implements Runnable{
    
    private DefaultModalGraphMouse gm;
    private VisualizationViewer<Integer, String> vv;
    private Graph<String, String> g;
    private String gName;
    
    public DrawGraphPane(){
        
    }
    
    public DrawGraphPane(Graph<String, String> g, String gName){
        this.g = g;
        this.gName = gName;
        
    }
    
    public VisualizationViewer<Integer, String> drawGraphZoomScrollPane(Graph<String, String> g, String gName){
        //SimpleGraphView sgv = new SimpleGraphView(); //We create our graph in here
        // The Layout<V, E> is parameterized by the vertex and edge types
        Layout<Integer, String> layout11 = new CircleLayout(g);
        layout11.setSize(new Dimension(320,240)); // sets the initial size of the layout space
        
        // The BasicVisualizationServer<V,E> is parameterized by the vertex and edge types
        setVv(new VisualizationViewer<>(layout11));
        vv.setName(gName);
        JLabel jl = new JLabel();
        jl.setPreferredSize(new Dimension(300, 20));
        jl.setHorizontalAlignment(SwingConstants.TRAILING);
        //jl.setHorizontalTextPosition(SwingConstants.RIGHT);
        jl.setText("" + gName);
        jl.setForeground(Color.darkGray);
        vv.add(jl);
        //getVv().add(jl);
        vv.setPreferredSize(new Dimension(320, 240));
        vv.setBackground(new java.awt.Color(191, 209, 227));
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        
        setGm(new DefaultModalGraphMouse());
        //gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
        //vv.setGraphMouse(gm);
        
        getGm().setMode(ModalGraphMouse.Mode.TRANSFORMING);
        vv.setGraphMouse(getGm());
        vv.getRenderContext().setVertexStrokeTransformer(new ConstantTransformer(new BasicStroke(0.1f)));
        //getVv().getRenderContext().setEdgeDrawPaintTransformer(new ConstantTransformer(Color.WHITE));
        vv.getRenderContext().setEdgeStrokeTransformer(new ConstantTransformer(new BasicStroke(0.8f)));
        vv.getRenderContext().setEdgeArrowStrokeTransformer(new ConstantTransformer(new BasicStroke(0.1f)));
        //getGm().setMode(ModalGraphMouse.Mode.TRANSFORMING);
        //vv.getRenderer().getVertexLabelRenderer().setPosition(Position.S);
        
        final PickedState<Integer> pickedState = getVv().getPickedVertexState();
        // Attach the listener that will print when the vertices selection changes.
        /*pickedState.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                Object subject = e.getItem();
                // The graph uses Integers for vertices.
                if (subject instanceof Integer) {
                    Integer vertex = (Integer) subject;
                    if (pickedState.isPicked(vertex)) {
                        System.out.println("Vertex " + vertex
                            + " is now selected");
                    } else {
                        System.out.println("Vertex " + vertex
                            + " no longer selected");
                    }
                }
            }
        });*/
        
        
        //GraphZoomScrollPane scrollPane = new GraphZoomScrollPane(getVv());
        return vv;
    }
    
        /**
     * @return the gm
     */
    public DefaultModalGraphMouse getGm() {
        return gm;
    }

    /**
     * @param gm the gm to set
     */
    public void setGm(DefaultModalGraphMouse gm) {
        this.gm = gm;
    }

    /**
     * @return the vv
     */
    public VisualizationViewer<Integer, String> getVv() {
        return vv;
    }

    /**
     * @param vv the vv to set
     */
    public void setVv(VisualizationViewer<Integer, String> vv) {
        this.vv = vv;
    }

    @Override
    public void run() {
        vv = drawGraphZoomScrollPane(g, gName);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
