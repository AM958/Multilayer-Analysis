/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.control;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import multilevel.view.MainUI;

/**
 *
 * @author Spiros
 */
public class newFileListener implements ActionListener{

    MainUI ui;
    
            
    public newFileListener(MainUI parent){
        this.ui = parent;
        

    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {

        try {
            Date now = new Date();
            String time = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(now);
            String path = "Graphs" + File.separator;
            String filename = path + "graph_" + time + ".txt";
            final File pathF = new File(path);
            final File newF = new File(filename);
            pathF.mkdirs();
            newF.createNewFile();
            Desktop desktop = Desktop.getDesktop();
            desktop.edit(newF);
        } catch (Exception ex) {
            ui.getLogTxtArea1().appendError(ex.getMessage());
        }
    }
    
}
