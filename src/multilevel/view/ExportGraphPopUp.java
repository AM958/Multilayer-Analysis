/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.view;

import com.alee.utils.SystemUtils;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;

/**
 *
 * @author Spiros
 */
public class ExportGraphPopUp extends JDialog{
    
    private JRadioButton svgBtn;
    private JRadioButton pngBtn;
    private JButton okBtn;
    private JButton cancelBtn;
    
    public ExportGraphPopUp(){
        super();
        
        initiate();
        
        
    }
    
    public final void initiate(){
        
        this.setSize(150, 150);
        ButtonGroup exportButtonGroup = new ButtonGroup();
        this.setTitle("Export Graphs");
        this.setResizable(false);
        
        svgBtn = new JRadioButton();
        pngBtn = new JRadioButton();
        okBtn = new JButton();
        cancelBtn = new JButton();
        
        exportButtonGroup.add(svgBtn);
        exportButtonGroup.add(pngBtn);
        
        JLabel exportLabel = new JLabel();
        exportLabel.setText(" Export as...");
        
        Icon svgIcon = new javax.swing.ImageIcon(getClass().getResource("/img/flaticon/svg2.png"));
        String html = "<html><body><img src='" + svgIcon.toString() +"'>";
        svgBtn.setText(html);
        svgBtn.setSelected(true);
        
        Icon pngIcon = new javax.swing.ImageIcon(getClass().getResource("/img/flaticon/png4.png"));
        html = "<html><body><img src='" + pngIcon.toString() +"'>";
        pngBtn.setText(html);

        okBtn.setText("OK");
        cancelBtn.setText("Cancel");
        GridLayout exportLayout = new GridLayout(5,2);
        
        exportLayout.setHgap(10);
        
        this.setLayout(exportLayout);
        this.add(exportLabel);
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(svgBtn);
        this.add(pngBtn);
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(okBtn);
        this.add(cancelBtn);
        
        if(SystemUtils.isUnix()){
            pngBtn.setEnabled(false);
            
        }
        
    }

    /**
     * @return the svgBtn
     */
    public JRadioButton getSvgBtn() {
        return svgBtn;
    }

    /**
     * @return the pngBtn
     */
    public JRadioButton getPngBtn() {
        return pngBtn;
    }

    /**
     * @return the okBtn
     */
    public JButton getOkBtn() {
        return okBtn;
    }

    /**
     * @return the cancelBtn
     */
    public JButton getCancelBtn() {
        return cancelBtn;
    }
}
