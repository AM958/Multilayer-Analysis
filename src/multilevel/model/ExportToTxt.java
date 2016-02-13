/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.model;

import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTable;

/**
 *
 * @author Spiros
 */
public class ExportToTxt {
    
    //private MainUI;
    
    public ExportToTxt(){
        try
	{
	    FileWriter writer = new FileWriter("data.txt");
		 
			
	    //generate whatever data you want
			
	    writer.flush();
	    writer.close();
	}
	catch(IOException e)
	{
	     e.printStackTrace();
	} 
    }
    
    
}
