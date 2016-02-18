/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.view;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.picking.PickedState;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import org.apache.commons.collections15.functors.ConstantTransformer;

/**
 *
 * @author Spiros
 */
public class RedrawGraphPaneWithLayout {
    
    private DefaultModalGraphMouse gm;
    private VisualizationViewer<Integer, String> vv;
    private Graph<String, String> g;
    private String gName;
    private Layout<Integer, String> layout11;
    
    public RedrawGraphPaneWithLayout(){
        layout11 = new CircleLayout(g);
    }
    
    public RedrawGraphPaneWithLayout(String l) throws Exception{
        switch (l) {
            case "Circle":
                layout11 = new CircleLayout(g);
                break;
            case "FR":
                layout11 = new FRLayout(g);
                break;
            case "KK":
                layout11 = new KKLayout(g);
                break;
            case "Spring":
                layout11 = new SpringLayout(g);
                break;
            case "ISOM":
                layout11 = new ISOMLayout(g);
                break;
            default:
                throw new Exception("Wrong layout chosen!\n");
        }
    }
    
    public VisualizationViewer<Integer, String> drawGraphZoomScrollPane(Graph<String, String> g, String gName){

        layout11.setSize(new Dimension(320,240)); // sets the initial size of the layout space

        vv = new VisualizationViewer<>(layout11);
        vv.setName(gName);
        JLabel jl = new JLabel();
        jl.setPreferredSize(new Dimension(300, 20));
        jl.setHorizontalAlignment(SwingConstants.TRAILING);

        jl.setText("" + gName);
        jl.setForeground(Color.darkGray);
        vv.add(jl);

        vv.setPreferredSize(new Dimension(320, 240));
        vv.setBackground(new java.awt.Color(191, 209, 227));
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        
        gm = new DefaultModalGraphMouse();

        gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
        vv.setGraphMouse(gm);
        vv.getRenderContext().setVertexStrokeTransformer(new ConstantTransformer(new BasicStroke(0.1f)));
        vv.getRenderContext().setEdgeStrokeTransformer(new ConstantTransformer(new BasicStroke(0.8f)));
        vv.getRenderContext().setEdgeArrowStrokeTransformer(new ConstantTransformer(new BasicStroke(0.1f)));
       
        final PickedState<Integer> pickedState = vv.getPickedVertexState();
        return vv;
    }
    
    
}
