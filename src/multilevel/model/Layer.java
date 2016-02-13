/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.model;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Spiros
 */
public class Layer {
    
    private Map<Integer, Edge> edges;
    private Map<Integer, String> vertices;
    private String layerName;
    private int id;
    
    /**
     *
     * @param layerName
     * @param edges
     * @param vertices
     * @param id
     */
    public Layer(String layerName, Map<Integer, Edge> edges, Map vertices, int id){
        this.edges = edges;
        this.vertices = vertices;
        this.layerName = layerName;
        this.id = id;
        
    }
    
    /**
     *
     * @param id
     * @param layerName
     */
    public Layer(int id, String layerName){
        this.layerName = layerName;
        this.id = id;
    }

    /**
     * @return the edges
     */
    public Map<Integer, Edge> getEdges() {
        return edges;
    }

    /**
     * @param edges the edges to set
     */
    public void setEdges(Map<Integer, Edge> edges) {
        this.edges = edges;
    }

    /**
     * @return the vertices
     */
    public Map<Integer, String> getVertices() {
        return vertices;
    }

    /**
     * @param vertices the vertices to set
     */
    public void setVertices(Map<Integer, String> vertices) {
        this.vertices = vertices;
    }

    /**
     * @return the layerName
     */
    public String getLayerName() {
        return layerName;
    }

    /**
     * @param layerName the layerName to set
     */
    public void setLayerName(String layerName) {
        this.layerName = layerName;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
}
