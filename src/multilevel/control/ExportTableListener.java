/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.control;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import multilevel.model.ExportToTxt;
import multilevel.view.MainUI;

/**
 *
 * @author Spiros
 */
public class ExportTableListener implements ActionListener{

    private MainUI parent;
    private JTable table;
    
    public ExportTableListener(MainUI parent){
        this.parent = parent;
        this.table = parent.getTabbedPane11().getjTable4();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand().equals("Export")){
            try
            {
                Date now = new Date();
                String time = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(now);
                String filename = "Exported" + File.separator + "data_" + time + ".csv";
                File f = new File(filename);
                f.getParentFile().mkdirs(); 
                f.createNewFile();
                try (FileWriter writer = new FileWriter(filename)) {

                    writer.append("Vertex," + parent.getTabbedPane11().getjLabel25().getText() + "\n");
                    for(int row = 0; row < table.getRowCount(); row ++){
                        writer.append(table.getValueAt(row, 0) + "," + table.getValueAt(row, 1) + "\n");
                    }
                    
                    
                    writer.flush();
                }
                parent.getLogTxtArea1().append("Data exported to file \"" + System.getProperty("user.dir") + File.separator + filename + "\".\n");
                
            }
            catch(IOException e)
            {
                parent.getErrorDialog().setVisible(true);
                parent.setEnabled(false);
                SimpleAttributeSet red = new SimpleAttributeSet();
                StyleConstants.setForeground(red, Color.RED);
                parent.getLogTxtArea1().append(e.getMessage(), red);
            } 
        }
    }
    
    
}
