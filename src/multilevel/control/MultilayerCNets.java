/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.control;

import com.jtattoo.plaf.aero.AeroLookAndFeel;
import java.util.Properties;
import multilevel.model.MultilevelSparseMultigraph;
import multilevel.view.MainUI;

/**
 *
 * @author Spiros
 */
public class MultilayerCNets {
    
        /**
     * @param args the command line arguments
     */
    @SuppressWarnings("Convert2Diamond")
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            boolean osIsWindows = false;
            
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    osIsWindows = true;

                    break;
                }
            }
            if(osIsWindows){
                Properties props = new Properties();
                props.put("logoString", "");
                AeroLookAndFeel.setCurrentTheme(props);
                javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
            }
            else{
                javax.swing.UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>

        
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                //LogTextArea logTextA;
                MainUI ui = new MainUI();
                MultilevelSparseMultigraph mg = new MultilevelSparseMultigraph();
                ui.setMg(mg);
                ui.setVisible(true);
                initListeners(ui);
                //logTextA = ui.getLogTxtArea1();
                
                //FileParser fp = new FileParser();
                //Map <Integer, multilevel.model.Layer> layerList = new HashMap();

                
            }
        });
    }
    
    public static void initListeners(MainUI ui){
        ui.getMenuItem(1).addActionListener(new MenuActionListener(ui));
        ui.getMenuItem(2).addActionListener(new MenuActionListener(ui));
        ui.getMenuItem(3).addActionListener(new MenuActionListener(ui));
        ui.getInterfaceOpenButton().addActionListener(new MenuActionListener(ui));
        //ui.getNewButton().addActionListener(new MenuActionListener(ui));
        ui.getTabbedPane11().getjTable3().getSelectionModel().addListSelectionListener(new TableSelectionListener(ui, ui.getTabbedPane11().getjTable3()));
        ui.getTabbedPane11().getjTable2().getSelectionModel().addListSelectionListener(new TableSelectionListener(ui, ui.getTabbedPane11().getjTable2()));
        ui.getTabbedPane11().getjTable4().getSelectionModel().addListSelectionListener(new TableSelectionListener(ui, ui.getTabbedPane11().getjTable4()));
        ui.getGraphFoundationPanel1().getOpenButton().addActionListener(new MenuActionListener(ui));
        ui.getMenuItem(4).addActionListener(new MultiplexBetweennessListener(ui));
        ui.getMenuItem(6).addActionListener(new MultiplexDegreeListener(ui));
        ui.getMenuItem(7).addActionListener(new MultiplexDegreeListener(ui));
        ui.getMenuItem(8).addActionListener(new MultiplexDegreeListener(ui));
        ui.getMenuItem(11).addActionListener(new MultiLayerDegreeListener(ui));
        ui.getMenuItem(12).addActionListener(new MultiLayerDegreeListener(ui));
        ui.getMenuItem(13).addActionListener(new MultiLayerDegreeListener(ui));
        ui.getMenuItem(14).addActionListener(new MultilayerPCIListener(ui));
        ui.getMenuItem(5).addActionListener(new MultiplexPageRankOptionsListener(ui) );
        ui.getMenuItem(10).addActionListener(new ExportGraphImageListener(ui));
        ui.getMenuItem(16).addActionListener(new MultiplexCLDCListener(ui));
        ui.getMenuItem(17).addActionListener(new MultiplexCLDCListener(ui));
        ui.getMenuItem(18).addActionListener(new MultiplexCLDCListener(ui));
        ui.getMenuItem(19).addActionListener(new MultiplexCLDCListener(ui));
        ui.getMenuItem(20).addActionListener(new MultiplexCLDCListener(ui));
        ui.getMenuItem(21).addActionListener(new MultiplexCLDCListener(ui));
        ui.getMenuItem(22).addActionListener(new AboutMenuListener(ui));
        ui.getMenuItem(23).addActionListener(new AboutMenuListener(ui));
        ui.getSelectLayoutOKButton().addActionListener(new SelectLayoutListener(ui));
        ui.getSelectLayoutCancelButton().addActionListener(new SelectLayoutListener(ui));
        ui.getPciOpt().getPciOkButton().addActionListener(new MultilayerPCIListener(ui));
        ui.getPciOpt().getPciCancelButton().addActionListener(new MultilayerPCIListener(ui));
        ui.getNewButton().addActionListener(new newFileListener(ui));
        //ui.getCldcOK().addActionListener(new MultiplexCLDCListener(ui));
        //ui.getCldcCancel().addActionListener(new MultiplexCLDCListener(ui));
        ui.getPciOpt().getPciMethodComboBox().addActionListener(new pciTypeComboBoxListener(ui));
        ui.getMenuItem(9).addActionListener(new SelectLayoutDialogListener(ui));
        ui.getPageRankOptions2().getjButton2().addActionListener(new MultiplexPageRankOptionsListener(ui) );
        ui.getPageRankOptions2().getjButton1().addActionListener(new MultiplexPageRankOptionsListener(ui) );
        ui.getTabbedPane11().getExportButton().addActionListener(new ExportTableListener(ui));
        ui.getMenuItem(15).addActionListener(new ExportTableListener(ui));
    }
    
}
