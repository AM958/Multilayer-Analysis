/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.view;
import javax.swing.*;
import multilevel.control.MenuActionListener;
/**
 *
 * @author Spiros
 */
public class MainMenuBar extends JMenuBar{
    
    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenu generateMenu;
    private JMenu centralitiesMenu;
    private JMenu multiplexMenu;
    private JMenu multilayerMenu;
    //private JMenu pciMenu;
    
    private MainMenuItem exitMenuItem;
    private MainMenuItem randDirectedGraphMenuItem;
    private MainMenuItem optionsMenuItem;
    private MainMenuItem openMenuItem;
    private MainMenuItem exportGraphsMenuItem;
    private MainMenuItem exportResultsMenuItem;
    private MainMenuItem randUndirectedGraphMenuItem;
    private MainMenuItem multiplexBetweenness;
    private MainMenuItem multiplexPageRank;
    private MainMenuItem multiplexUndirDegree;
    private MainMenuItem multiplexInDegree;
    private MainMenuItem multiplexOutDegree;
    private MainMenuItem multilayerUndirDegree;
    private MainMenuItem multilayerInDegree;
    private MainMenuItem multilayerOutDegree;
    private MainMenuItem multilayerPCI;
    
    
    public MainMenuBar(){
        super();
        initMenu();
        
    }

    public void addMenu(JMenu men){
        super.add(men);
        
    }
    
    public JMenuItem getMenu2(){
        return randDirectedGraphMenuItem;
    }
    
    public JMenuItem getMenu3(){
        return randUndirectedGraphMenuItem;
    }
    private void initMenu(){
        
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        generateMenu = new JMenu("Generate");
        centralitiesMenu = new JMenu("Centralities");
        multiplexMenu = new JMenu("Multiplex Network");
        multilayerMenu = new JMenu ("Multi-layer Network");
        
        
        exitMenuItem = new MainMenuItem("Exit", new javax.swing.ImageIcon(getClass().getResource("/img/process-stop.png")), "Exit");
        randDirectedGraphMenuItem = new MainMenuItem("Random Graph (D)", new javax.swing.ImageIcon(getClass().getResource("/img/view-refresh.png")), "Generate Random Directed Graph");
        randUndirectedGraphMenuItem = new MainMenuItem("Random Graph (U)", new javax.swing.ImageIcon(getClass().getResource("/img/view-refresh.png")), "Generate Random Undirected Graph");
        optionsMenuItem = new MainMenuItem("Change Layout", new javax.swing.ImageIcon(getClass().getResource("/img/emblem-system.png")));
        openMenuItem = new MainMenuItem("Open", new javax.swing.ImageIcon(getClass().getResource("/img/document-open.png")));
        exportGraphsMenuItem = new MainMenuItem("Export Graph", new javax.swing.ImageIcon(getClass().getResource("/img/image-x-generic.png")));
        exportResultsMenuItem = new MainMenuItem("Export Results", new javax.swing.ImageIcon(getClass().getResource("/img/flaticon/csv1.png")));
        exportResultsMenuItem.setEnabled(false);
        
        multiplexBetweenness = new MainMenuItem("Multiplex Betweenness", "Calculate Multiplex Betweenness Centrality");
        multiplexPageRank = new MainMenuItem("Multiplex Page Rank", "Calculate Multiplex Page Rank Centrality");
        multiplexUndirDegree = new MainMenuItem("Multiplex Undirected Degree", "Calculate Multiplex Undirected Degree Centrality");
        multiplexOutDegree = new MainMenuItem("Multiplex Out-Degree", "Calculate Multiplex Out-Degree Centrality");
        multiplexInDegree = new MainMenuItem("Multiplex In-Degree", "Calculate Multiplex In-Degree Centrality");
        
        multilayerUndirDegree = new MainMenuItem("Multi-layer Undirected Degree", "Calculate Multiplex Undirected Degree Centrality");
        multilayerOutDegree = new MainMenuItem("Multi-layer Out-Degree", "Calculate Multiplex Out-Degree Centrality");
        multilayerInDegree = new MainMenuItem("Multi-layer In-Degree", "Calculate Multiplex In-Degree Centrality");

        multilayerPCI = new MainMenuItem("Power Community Index", "Calculate Power Community Index");

        fileMenu.add(openMenuItem);
        fileMenu.add(new javax.swing.JToolBar.Separator());
        fileMenu.add(exportGraphsMenuItem);
        fileMenu.add(exportResultsMenuItem);
        fileMenu.add(new javax.swing.JToolBar.Separator());
        exitMenuItem.addActionListener(new MenuActionListener());
        fileMenu.add(exitMenuItem);
        addMenu(fileMenu);

        editMenu.add(optionsMenuItem);
        addMenu(editMenu);

        generateMenu.setText("Generate");
        generateMenu.add(randDirectedGraphMenuItem);
        generateMenu.add(randUndirectedGraphMenuItem);
        addMenu(generateMenu);

        multiplexMenu.add(multiplexBetweenness);
        multiplexMenu.add(multiplexPageRank);
        multiplexMenu.add(new javax.swing.JToolBar.Separator());    
        multiplexMenu.add(multiplexInDegree);
        multiplexMenu.add(multiplexOutDegree);
        multiplexMenu.add(new javax.swing.JToolBar.Separator());
        multiplexMenu.add(multiplexUndirDegree);
        centralitiesMenu.add(multiplexMenu);
        addMenu(centralitiesMenu);
        
        multilayerMenu.add(multilayerInDegree);
        multilayerMenu.add(multilayerOutDegree);
        multilayerMenu.add(new javax.swing.JToolBar.Separator());
        multilayerMenu.add(multilayerUndirDegree);
        
        multilayerMenu.add(new javax.swing.JToolBar.Separator());
        multilayerPCI.setActionCommand("PCI");
        multilayerMenu.add(multilayerPCI);
        
        centralitiesMenu.add(multilayerMenu);
        addMenu(centralitiesMenu);
        
        
        
    }  

    /**
     * @return the openMenuItem
     */
    public JMenuItem getOpenMenuItem() {
        return openMenuItem;
    }

    /**
     * @return the multiplexBetweenness
     */
    public JMenuItem getMultiplexBetweenness() {
        return multiplexBetweenness;
    }

    /**
     * @return the multiplexPageRank
     */
    public JMenuItem getMultiplexPageRank() {
        return multiplexPageRank;
    }

    /**
     * @return the multiplexUndirDegree
     */
    public JMenuItem getMultiplexUndirDegree() {
        return multiplexUndirDegree;
    }

    /**
     * @return the multiplexInDegree
     */
    public JMenuItem getMultiplexInDegree() {
        return multiplexInDegree;
    }

    /**
     * @return the multiplexOutDegree
     */
    public JMenuItem getMultiplexOutDegree() {
        return multiplexOutDegree;
    }

    /**
     * @return the optionsMenuItem
     */
    public JMenuItem getOptionsMenuItem() {
        return optionsMenuItem;
    }

    /**
     * @return the exportGraphsMenuItem
     */
    public JMenuItem getExportGraphsMenuItem() {
        return exportGraphsMenuItem;
    }

    /**
     * @return the multilayerUndirDegree
     */
    public MainMenuItem getMultilayerUndirDegree() {
        return multilayerUndirDegree;
    }

    /**
     * @return the multilayerInDegree
     */
    public MainMenuItem getMultilayerInDegree() {
        return multilayerInDegree;
    }

    /**
     * @return the multilayerOutDegree
     */
    public MainMenuItem getMultilayerOutDegree() {
        return multilayerOutDegree;
    }

    /**
     * @return the singleLayerPCI
     */
    public MainMenuItem getMultilayerPCI() {
        return multilayerPCI;
    }
    
}
