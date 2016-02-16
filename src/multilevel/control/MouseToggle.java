/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.control;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import multilevel.view.MainUI;

/**
 *
 * @author Spiros
 */
public class MouseToggle implements ActionListener{

   DefaultModalGraphMouse gm;
   List<VisualizationViewer<Integer, String>> vv;
   JButton tog;
    
    private boolean pickOrTranslate; //true = pick, false = translate
    
    public MouseToggle(List<VisualizationViewer<Integer, String>> vv, JButton tog){    
        pickOrTranslate = true;
        this.vv = vv;
        this.tog = tog;
    }
    
    public MouseToggle(MainUI parent){
        pickOrTranslate = false;
        this.vv = parent.getLogGraphSplitPane1().getGraphPanel().getVv();
        this.tog = parent.getjButton7();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        pickOrTranslate = !pickOrTranslate;
        gm = new DefaultModalGraphMouse();
        Cursor mCursor; 
        tog = (JButton)ae.getSource();
        if(pickOrTranslate == true){
            mCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
            gm.setMode(ModalGraphMouse.Mode.PICKING);
            
            tog.setToolTipText("Change to Transform mode");
            tog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconarchive/Cursor-Move-icon.png")));
        }
        else{
            mCursor = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
            gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
            tog.setToolTipText("Change to Picking mode");
            tog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconarchive/Hands-One-Finger-icon.png")));
        }
        for( VisualizationViewer<Integer, String> v : vv ) { 
            v.setGraphMouse(gm);
            v.setCursor(mCursor);
        }
    }
    
    

}
