package multilevel.view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Cursor;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
/**
 *
 * @author Spiros
 */
public class LogTextArea extends JTextPane{
    
    JPopupMenu pop;
    final JMenuItem copy;
    final LogTextArea l;
    
    public LogTextArea(){
        super();
        this.l = this;
        this.copy = new JMenuItem("Copy      CTRL+C");
        this.pop = new JPopupMenu();
        this.setEditable(false);
        //this.setColumns(20);
        this.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        //this.setRows(5);
        
        Date now = new Date();
        String time = new SimpleDateFormat("[dd/MM/yyyy HH:mm:ss]").format(now);
        super.setText(time + ": Command Line Area Initiated.\n");
        this.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        
        //credits http://tiebing.blogspot.gr/2013/10/add-context-menu-copypaste-to-java.html
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(final MouseEvent e) {
                if (e.isPopupTrigger()) {
                    final JPopupMenu menu = new JPopupMenu();
                    JMenuItem item;
                    item = new JMenuItem(new DefaultEditorKit.CopyAction());
                    item.setText("Copy      CTRL+C");
                    item.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit-copy.png")));
                    item.setEnabled(l.getSelectionStart() != l.getSelectionEnd());
                    menu.add(item);
                    menu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    
    }
    
    @Override
    public String getText() {
        return super.getText();
    }
    
    @Override
    public void setText(String s) {
        super.setText(s);
    }
    
    
    //credits https://tips4java.wordpress.com/2009/01/25/no-wrap-text-pane/
    @Override
    public boolean getScrollableTracksViewportWidth()
    {
        return getUI().getPreferredSize(this).width 
            <= getParent().getSize().width;
    }
    
    public void append(String s) {
        Date now = new Date();
        String time = new SimpleDateFormat("[dd/MM/yyyy HH:mm:ss]").format(now);
        StyledDocument document = (StyledDocument) this.getDocument();
        String txt = time + ": " + s;
        try {
            document.insertString(document.getLength(), txt , null);
            //super.
            //super.append(time + ": " + s);
        } catch (BadLocationException ex) {
            Logger.getLogger(LogTextArea.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void append(String s, SimpleAttributeSet style) {
        Date now = new Date();
        String time = new SimpleDateFormat("[dd/MM/yyyy HH:mm:ss]").format(now);
        StyledDocument document = (StyledDocument) this.getDocument();
        String txt = time + ": " + s;
        try {
            document.insertString(document.getLength(), txt , style);
            //super.
            //super.append(time + ": " + s);
        } catch (BadLocationException ex) {
            Logger.getLogger(LogTextArea.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *
     * @param s
     * @param style
     */
    public void appendError(String s) {
        Date now = new Date();
        String time = new SimpleDateFormat("[dd/MM/yyyy HH:mm:ss]").format(now);
        StyledDocument document = (StyledDocument) this.getDocument();
        //String txt = time + ": " + s;
        String txt = s;
        SimpleAttributeSet red = new SimpleAttributeSet();
        StyleConstants.setForeground(red, Color.RED);
        try {
            document.insertString(document.getLength(), time + ": ", null);
            document.insertString(document.getLength(), txt , red);
            //super.
            //super.append(time + ": " + s);
        } catch (BadLocationException ex) {
            Logger.getLogger(LogTextArea.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
