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
public class MultiLevelEdge extends Edge{
    
    //private String edgeName;
    private Vertex vertex1;
    private Vertex vertex2;
    private String layer1;
    private String layer2;
    //private int weight;
    
    public MultiLevelEdge(String edgeName, String layer1, Vertex vertex1,String layer2, Vertex vertex2){
        
        super(edgeName);
        this.layer1 = layer1;
        this.layer2 = layer2;
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;

        
    }
    
    public MultiLevelEdge(String edgeName, String layer1, Vertex vertex1,String layer2, Vertex vertex2, int weight){       
        
        super(edgeName, weight);        
        this.layer1 = layer1;
        this.vertex1 = vertex1;
        this.layer2 = layer2;
        this.vertex2 = vertex2;
        
    }
    
    public MultiLevelEdge(){
           
    }

    /**
     * @return the layer1
     */
    public String getLayer1() {
        return layer1;
    }

    /**
     * @param layer1 the layer1 to set
     */
    public void setLayer1(String layer1) {
        this.layer1 = layer1;
    }

    /**
     * @return the layer2
     */
    public String getLayer2() {
        return layer2;
    }

    /**
     * @param layer2 the layer2 to set
     */
    public void setLayer2(String layer2) {
        this.layer2 = layer2;
    }

    /**
     * @return the vertex2
     */
    public Vertex getVertex2() {
        return vertex2;
    }

    /**
     * @param vertex2 the vertex2 to set
     */
    public void setVertex2(Vertex vertex2) {
        this.vertex2 = vertex2;
    }

    /**
     * @return the vertex1
     */
    public Vertex getVertex1() {
        return vertex1;
    }

    /**
     * @param vertex1 the vertex1 to set
     */
    public void setVertex1(Vertex vertex1) {
        this.vertex1 = vertex1;
    }
    
}
