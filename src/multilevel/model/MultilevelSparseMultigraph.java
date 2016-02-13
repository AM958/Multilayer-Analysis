/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.model;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Spiros
 */
public class MultilevelSparseMultigraph{
    
    private Map<Integer, Graph> multilayerGraphList;
    private Map<Integer, String> graphName;
    private Map<Integer, Vertex> verticesMap;
    private List<MultiLevelEdge> multiEdges;
    
    public MultilevelSparseMultigraph(){
        resetAll();
        
    }

    public void resetAll(){
        this.multilayerGraphList = new HashMap();
        this.graphName = new HashMap();
        this.verticesMap = new HashMap();
        multiEdges = new ArrayList<>();
    }
    
    /**
     * @return the multilayerGraph
     */
    public Map<Integer, Graph> getLayerList() {
        return multilayerGraphList;
    }

    /**
     * @param layerList the multilayerGraph to set
     */
    public void setLayerList(Map<Integer, Graph> layerList) {
        this.multilayerGraphList = layerList;
    }
    
    /**
     *
     * @param id
     * @param g
     * @param name
     */
    public void addLayer(int id, Graph g, String name){
        
        graphName.put(id, name);
        
        multilayerGraphList.put(id, g);
        
    }
    
    /**
     *
     * @param id
     * @param name
     */
    public void addLayer(int id, String name){
        Graph<String, String> g = new SparseMultigraph<>();
        graphName.put(id, name);
        multilayerGraphList.put(id, g);       
        
    }
    
    /**
     *
     * @param id
     */
    public void addLayer(int id){
        Graph<String, String> g = new SparseMultigraph<>();
        
        multilayerGraphList.put(id, g);
    }

    /**
     * @param layerID
     * @return the graphName
     */
    public String getGraphName(int layerID) {
        return graphName.get(layerID);
    }

    /**
     * @param layerID
     * @param name
     */
    public void setGraphName(int layerID, String name) {
        graphName.put(layerID, name);
    }
    
    /**
     *
     * @param layerID
     * @param name
     * @param v1
     * @param v2
     */
    public void addUnweightedEdge(int layerID, String name, Vertex v1, Vertex v2){
        multilayerGraphList.get(layerID).addEdge(new Edge(name), v1, v2);
    }
    
    /**
     *
     * @param layerID
     * @param name
     * @param v1
     * @param v2
     * @param weight
     */
    public void addWeightedEdge(int layerID, String name, Vertex v1, Vertex v2, int weight){
        multilayerGraphList.get(layerID).addEdge(new Edge(name, weight), v1, v2);
    }
    
        /**
     *
     * @param layerID
     * @param name
     * @param v1
     * @param v2
     */
    public void addUnweightedArc(int layerID, String name, Vertex v1, Vertex v2){
        multilayerGraphList.get(layerID).addEdge(new Edge(name), v1, v2, EdgeType.DIRECTED);
    }
    
    /**
     *
     * @param layerID
     * @param name
     * @param v1
     * @param v2
     * @param weight
     */
    public void addWeightedArc(int layerID, String name, Vertex v1, Vertex v2, int weight){
        multilayerGraphList.get(layerID).addEdge(new Edge(name, weight), v1, v2, EdgeType.DIRECTED);
    }
    
    /**
     *
     * @param layerID
     * @param VertexID
     */
    public void addVertex(int layerID, int VertexID){
        multilayerGraphList.get(layerID).addVertex(verticesMap.get(VertexID));                
    }
    
    /**
     *
     * @param layerID
     * @param VertexName
     */
    public void addVertex(int layerID, String VertexName){
        multilayerGraphList.get(layerID).addVertex(VertexName); 
        

    }
    
    /**
     *
     * @param layerID
     * @param newVertex
     */
    public void addVertex(int layerID, Vertex newVertex){
        multilayerGraphList.get(layerID).addVertex(newVertex); 
        

    }    

    /**
     * Vertex for multiplex graph
     * @param newVertex
     */
    public void addVertex(Vertex newVertex){
        for(int layerKey: multilayerGraphList.keySet()){
            multilayerGraphList.get(layerKey).addVertex(newVertex); 
            System.out.println(multilayerGraphList.get(layerKey).getVertices());
        }
        

    } 
    
    /**
     * @return the verticesMap
     */
    public Map<Integer, Vertex> getVerticesMap() {
        return verticesMap;
    }

    /**
     * @param vertices the verticesMap to set
     */
    public void setVerticesMap(Map<Integer, Vertex> vertices) {
        this.verticesMap = vertices;
        
    }
    
    public void addMultiedge(MultiLevelEdge me){
        multiEdges.add(me);
    }
    
    public void addUnweightedMultiedge(String name, String layer1, Vertex vertex1, String layer2, Vertex vertex2){
        multiEdges.add(new MultiLevelEdge(name, layer1, vertex1, layer2, vertex2));
    }
    
    public void addWeightedMultiedge(String name, String layer1, Vertex vertex1, String layer2, Vertex vertex2, int weight){
        multiEdges.add(new MultiLevelEdge(name, layer1, vertex1, layer2, vertex2, weight));
    }

    /**
     * @return the multiEdges
     */
    public List<MultiLevelEdge> getMultiEdges() {
        return multiEdges;
    }

    /**
     * @param multiEdges the multiEdges to set
     */
    public void setMultiEdges(List<MultiLevelEdge> multiEdges) {
        this.multiEdges = multiEdges;
    }
    
}
