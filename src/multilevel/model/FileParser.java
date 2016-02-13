/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Spiros
 */
public final class FileParser {
    
    String fileName;
    private Layer layer;
    private Map<Integer, Layer> layerList;
    private Map<Integer, Vertex> vertices;
    private Map<Integer, Edge> singleLayerEdges;
    private Map<Integer, MultiLevelEdge> multiLayerEdges;
    
    private int EVLayer;
    int edgeKey;
    int mledgeKey;
    private MultilevelSparseMultigraph multilevelGraph;
    private boolean multiplex;

    /**
     * @return the layerList
     */
    public Map<Integer, Layer> getLayerList() {
        return layerList;
    }

    /**
     * @param layerList the layerList to set
     */
    public void setLayerList(Map<Integer, Layer> layerList) {
        this.layerList = layerList;
    }

    /**
     * @return the multiLayerEdges
     */
    public Map<Integer, MultiLevelEdge> getMultiLayerEdges() {
        return multiLayerEdges;
    }

    /**
     * @param multiLayerEdges the multiLayerEdges to set
     */
    public void setMultiLayerEdges(Map<Integer, MultiLevelEdge> multiLayerEdges) {
        this.multiLayerEdges = multiLayerEdges;
    }

    /**
     * @return the multilevelGraph
     */
    public MultilevelSparseMultigraph getMultilevelGraph() {
        return multilevelGraph;
    }

    /**
     * @param multilevelGraph the multilevelGraph to set
     */
    public void setMultilevelGraph(MultilevelSparseMultigraph multilevelGraph) {
        this.multilevelGraph = multilevelGraph;
    }

    /**
     * @return the multiplex
     */
    public boolean isMultiplex() {
        return multiplex;
    }
    private enum GraphProperty{ LAYER, EDGE, VERTEX, ARC, MULTIEDGE, EDGELIST, OTHER}
    GraphProperty gp;
    //private List<String> arcs;
    //private List<String> multiLayerEdges;
    
    public FileParser(String fileName) throws FileNotFoundException, IOException, Exception{
        this.fileName = fileName;
        this.edgeKey = 0;
        this.mledgeKey = 0;
        this.EVLayer = -1;
        processFile(this.fileName);
        //this.multilevelGraph = multilevelGraph;
    }
    
    public FileParser(){
        this.edgeKey = 0;
        this.mledgeKey = 0;
    }
    
    public void processFile(String fileName) throws FileNotFoundException, IOException, Exception{
       try (Scanner scanner =  new Scanner(new File(fileName), "UTF-8")){
           setLayerList(new HashMap());
           multiplex = false;
           multilevelGraph = new MultilevelSparseMultigraph();
           
            while (scanner.hasNextLine()){
                processLine(scanner.nextLine());                
                //processLayer
                //processVertex
                //processEdge
                //processMultilayerEdge
                //processEdgeList
            }      
        }
        
    }
    
    /**
     *
     * @param line
     * @throws java.io.IOException
     */
    public final void processLine(String line) throws IOException, Exception{
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter("\\s\"|\"|\\s");
        
        
        OUTER:
        while (scanner.hasNext()) {
            String scn = scanner.next();
            switch (scn.toUpperCase()) {
                case "*LAYERS":
                    gp = GraphProperty.LAYER;
                    break OUTER;
                case "*VERTICES":
                    gp = GraphProperty.VERTEX;
                    vertices = new HashMap();
                    if(!multiplex)
                        EVLayer = Integer.parseInt(scanner.next());
                    break OUTER;                     
                case "*EDGES":
                    gp = GraphProperty.EDGE;
                    singleLayerEdges = new HashMap();
                    EVLayer = Integer.parseInt(scanner.next());
                    break OUTER;
                case "*ARCS":
                    gp = GraphProperty.ARC;
                    EVLayer = Integer.parseInt(scanner.next());
                    break OUTER;
                case "*EDGELIST":
                    //gp = graphProperty.edgelist;
                   
                    break OUTER;
                case "*MULTILAYER":
                    gp = GraphProperty.MULTIEDGE;
                    setMultiLayerEdges((Map<Integer, MultiLevelEdge>) new HashMap());
                    break OUTER;
                case "*MULTIPLEX":
                    multiplex = true;
                case " ":
                    //gp = graphProperty.other;
                    break OUTER;
            }
            if(gp == GraphProperty.LAYER){
                String param2 = scanner.next();
                //layer= new Layer(Integer.parseInt(scn), param2);
                //getLayerList().put(Integer.parseInt(scn), layer);
                multilevelGraph.addLayer(Integer.parseInt(scn), param2);
                //System.out.println(multilevelGraph.getLayerList());
                
            }
            else if(gp == GraphProperty.VERTEX){
                
                if(multilevelGraph.getLayerList().containsKey(EVLayer) || multiplex){
                    String tName = scanner.next();
                    Vertex tVertex = new Vertex(Integer.parseInt(scn), tName);
                    vertices.put(Integer.parseInt(scn), tVertex);
                    multilevelGraph.getVerticesMap().put(Integer.parseInt(scn), tVertex);
                    
                    System.out.println("vertex map" + multilevelGraph.getVerticesMap().toString());
                    if(multiplex){
                        multilevelGraph.addVertex(vertices.get(Integer.parseInt(scn)));
                    }
                    else{
                        multilevelGraph.addVertex(EVLayer, vertices.get(Integer.parseInt(scn)));
                    }
                }
                else{
                    throw new IOException("Error: Wrong input file.\n");
                }
            }
            else if(gp == GraphProperty.EDGE){
                //multilevelGraph.setVerticesMap(vertices);
                if(multilevelGraph.getLayerList().containsKey(EVLayer)){
                    
                    edgeKey++;
                    String v2 = scanner.next();
                    String w;
                    if(v2 == null){
                        throw new IOException("Error: No 2nd vertex of edge! Check your input file.\n");
                    }
                    
                    if(!scanner.hasNext()){ 
                        
                        multilevelGraph.addUnweightedEdge(EVLayer, "Edge"+edgeKey, vertices.get(Integer.parseInt(scn)), vertices.get(Integer.parseInt(v2)));
                    }
                    else{
                        
                        w = scanner.next();
                        multilevelGraph.addWeightedEdge(EVLayer, "Edge"+edgeKey, vertices.get(Integer.parseInt(scn)), vertices.get(Integer.parseInt(v2)), Integer.parseInt(w));
                    }

                }
                else{
                    throw new IOException("Error: Wrong input file.\n");
                }
            }
            else if(gp == GraphProperty.ARC){
                multilevelGraph.setVerticesMap(vertices);
                if(multilevelGraph.getLayerList().containsKey(EVLayer)){
                    
                    edgeKey++;
                    String v2 = scanner.next();
                    String w;
                    if(v2 == null){
                        throw new IOException("Error: No 2nd vertex of edge! Check your input file.\n");
                    }
                    
                    if(!scanner.hasNext()){ 
                        
                        multilevelGraph.addUnweightedArc(EVLayer, "Edge"+edgeKey, vertices.get(Integer.parseInt(scn)), vertices.get(Integer.parseInt(v2)));
                    }
                    else{
                        
                        w = scanner.next();
                        multilevelGraph.addWeightedArc(EVLayer, "Edge"+edgeKey, vertices.get(Integer.parseInt(scn)), vertices.get(Integer.parseInt(v2)), Integer.parseInt(w));
                    }

                }
                else{
                    throw new IOException("Error: Wrong input file.\n");
                }
            }
            else if(gp == GraphProperty.MULTIEDGE){
                mledgeKey++;
                MultiLevelEdge tempME;
                String mlEdgeName = "M/LEdge"+mledgeKey;
                String layer1 = multilevelGraph.getGraphName(Integer.parseInt(scn));
                Vertex vertex1 = multilevelGraph.getVerticesMap().get(Integer.parseInt(scanner.next()));
                String layer2 = multilevelGraph.getGraphName(Integer.parseInt(scanner.next()));
                Vertex vertex2 = multilevelGraph.getVerticesMap().get(Integer.parseInt(scanner.next()));
                tempME = new MultiLevelEdge(mlEdgeName, layer1, vertex1, layer2, vertex2);
                if (scanner.hasNext()) tempME.setWeight(Integer.parseInt(scanner.next()));
                multilevelGraph.addMultiedge(tempME);
                System.out.println(layer1 + " " + vertex1 + " " + vertex2);
                
            }
            else{
                throw new IOException("File: \"" + fileName + "\" doesn't seem to have the proper syntax!\n");
            }
        }
        
    }


}
