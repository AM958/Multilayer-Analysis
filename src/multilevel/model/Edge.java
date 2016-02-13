/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.model;

/**
 *
 * @author Spiros
 */
public class Edge {
    
    private String edgeName;
    private int weight;
    private int capacity;
    
    /**
     * An edge constructor
     * @param edgeName
     */
    public Edge(String edgeName){
        this.edgeName = edgeName;
        this.weight = 1;
    }
    
    /**
     * An edge constructor
     * @param edgeName
     * @param weight
     */
    public Edge(String edgeName, int weight){
        this.edgeName = edgeName;
        this.weight = weight;
    }
    
     /**
     * An edge constructor
     * @param edgeName
     * @param weight
     * @param capacity
     */
    public Edge(String edgeName, int weight, int capacity){
        this.edgeName = edgeName;
        this.weight = weight;
        this.capacity = capacity;
    }
    
    /**
     *
     */
    public Edge(){
        
    }

    /**
     * @return the edgeName
     */
    public String getEdgeName() {
        return edgeName;
    }

    /**
     * @param edgeName the edgeName to set
     */
    public void setEdgeName(String edgeName) {
        this.edgeName = edgeName;
    }

    /**
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString(){
        return getEdgeName();
    }
    
}
