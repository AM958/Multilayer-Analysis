/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.control;

import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.List;
import multilevel.view.ExportGraphPopUp;
import multilevel.view.MainUI;
import org.apache.commons.collections15.functors.ConstantTransformer;
import org.freehep.graphics2d.VectorGraphics;
import org.freehep.graphicsio.ImageGraphics2D;
import org.freehep.graphicsio.svg.SVGGraphics2D;

/**
 *
 * @author Spiros
 */
public class ExportGraphImageListener implements ActionListener{
    
    private final MainUI parent;
    private final List<VisualizationViewer<Integer, String>> vv;
    private ExportGraphPopUp exportPop;
    
    public ExportGraphImageListener(MainUI parent){
        this.parent = parent;
        this.vv = parent.getGraphFoundationPanel1().getVv();
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {

        if(ae.getActionCommand().equals("Export Graph")){
            exportPop = new ExportGraphPopUp();
            exportPop.setVisible(true);
            exportPop.getOkBtn().addActionListener(this);
            exportPop.getCancelBtn().addActionListener(this);
            exportPop.setLocationRelativeTo(parent);
        }
        if(ae.getActionCommand().equals("OK")){
            if(vv.isEmpty()){
                parent.getErrorDialog().setVisible(true);
                parent.setEnabled(false);
                parent.getLogTxtArea1().appendError("There is no graph to export.\n");
            } else {
                String log = null;
                for (final VisualizationViewer gvv1 : vv) {
                    try {

                        VisualizationImageServer<Integer, String> vis =
                                new VisualizationImageServer<>(gvv1.getGraphLayout(),
                                        new Dimension(640, 480));
                        ScalingControl scaler = new CrossoverScalingControl();
                        scaler.scale(vis, (float) 1.9, new Point2D.Double(0, 0));
                        vis.setBackground(Color.WHITE);
                        vis.getRenderContext().setVertexStrokeTransformer(new ConstantTransformer(new BasicStroke(0.1f)));
                        vis.getRenderContext().setEdgeStrokeTransformer(new ConstantTransformer(new BasicStroke(0.8f)));
                        vis.getRenderContext().setEdgeArrowStrokeTransformer(new ConstantTransformer(new BasicStroke(0.1f)));
                        vis.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Integer>());
                        vis.getRenderer().getVertexLabelRenderer().setPosition(Position.S);
                        
                        
                        VectorGraphics g;
                        String path = "Exported" + File.separator + gvv1.getName();
                        String fileExtension;
                        if(exportPop.getSvgBtn().isSelected()){
                            fileExtension = ".svg";                            
                            File f = new File(path + fileExtension);
                            f.getParentFile().mkdirs(); 
                            f.createNewFile();
                            g = new SVGGraphics2D(f, vis);
                        }
                        else{
                            fileExtension = ".png";
                            File f = new File(path + fileExtension);
                            f.getParentFile().mkdirs(); 
                            f.createNewFile();
                            g = new ImageGraphics2D(f, vis, "png");
                        }
                        
                        g.startExport();
                        vis.print(g); 
                        g.endExport();
                        log = "Graph images have been exported to: \"" + System.getProperty("user.dir") + File.separator + "Exported" 
                                + File.separator + "\"" + " as \"" + fileExtension + "\".\n";

                    } catch (IOException e) {
                        parent.getErrorDialog().setVisible(true);
                        parent.setEnabled(false);
                        parent.getLogTxtArea1().appendError(e.getMessage());
                    }
                }
                try {
                    Desktop.getDesktop().open(new File("Exported" + File.separator));
                    parent.getLogTxtArea1().append(log);
                } catch (IOException e) {
                    parent.getErrorDialog().setVisible(true);
                    parent.setEnabled(false);
                    parent.getLogTxtArea1().appendError(e.getMessage());
                }
            }
            exportPop.dispose();
        }
        if(ae.getActionCommand().equals("Cancel")){
            exportPop.dispose();
        }
    }
    
}
