/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.control;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.DefaultCaret;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import multilevel.view.MainUI;

/**
 *
 * @author Spiros
 */
public class AboutMenuListener implements ActionListener{

    private MainUI ui;
    
    public AboutMenuListener(MainUI parent){
        this.ui = parent;
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand().equals("about")){
            //Font font = new Font("Serif", Font.BOLD, 20);
            //ui.getAboutTextPane().setFont(font);
            
            ui.getHelpDialog().setTitle("About");
            //ui.getAboutTextPane().setText("Multilayer Complex Network Analysis Software\n\n");
            
        
            StyledDocument doc = ui.getAboutTextPane().getStyledDocument();
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            StyleConstants.setBold(center, true);
            StyleConstants.setFontSize(center, 20);
            StyleConstants.setFontFamily(center, "Serif");
            doc.setParagraphAttributes(0, doc.getLength(), center, false);
        
            ui.getAboutTextPane().setText("Multilayer Complex Network Analysis Software\n ------------------------------------ \n"
                    + "Copyright © 2016\n ------------------------------------ \nSpiros Skafidas");
            
        }
        else{
            String s = "Input File Rules\n\n---------\n\nMultiplex: *multiplex\n\nMultilayer: *multilayer\n\n"
                    + "Layers: *layers\nid layer_name\n\n"
                    + "Vertices: *vertices layer_id (don't include layer_id for multiplex)\nid vertex_name\n\n"
                    + "Undirected Edges: *edges layer_id\nvertex_1 vertex_2 weight capacity\n\n"
                    + "Directed Edges: *arcs layer_id\nsorce_vertex_id destination_vertex_id weight capacity\n\n"
                    + "Multilayer Edges: *mutilayer\nlayer_1 vertex_1 layer_2 vertex_2\n\n---------\n\n"
                    + "Example\n\n---------\n\n"
                    + "*multilayer\n"
                    + "*layers\n"
                    + "1 Layer1\n"
                    + "2 Layer2\n"
                    + "Vertices 1\n"
                    + "1 one\n"
                    + "2 two\n"
                    + "Edges 1\n"
                    + "1 2 15\n"
                    + "Vertices 2\n"
                    + "3 three\n"
                    + "4 four\n"
                    + "Edges 2\n"
                    + "3 4 11\n"
                    + "*multilayer\n"
                    + "1 1 2 4\n";
            StyledDocument doc = ui.getAboutTextPane().getStyledDocument();
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_LEFT);
            StyleConstants.setBold(center, true);
            StyleConstants.setBold(center, false);
            StyleConstants.setFontSize(center, 12);
            StyleConstants.setFontFamily(center, "Serif");
            doc.setParagraphAttributes(0, doc.getLength(), center, false);
            ui.getHelpDialog().setTitle("Help");
            DefaultCaret caret = (DefaultCaret) ui.getAboutTextPane().getCaret();
            caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
            ui.getAboutTextPane().setText(s);
        }
        ui.getHelpDialog().setVisible(true);
    }
    
}
