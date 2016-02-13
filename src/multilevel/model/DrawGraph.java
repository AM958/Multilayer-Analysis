/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.model;


import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;

import java.util.Random;

/**
 *
 * @author Spiros
 */
public class DrawGraph {
    

    private Graph<String, String> g;
    private String graphName;
    private final EdgeType et;
    
    public DrawGraph(EdgeType et){
        this.et = et;
    }
    
    public Graph<String, String> randomGraph(){
        
        
        // Graph<V, E> where V is the type of the vertices and E is the type of the edges
        setG(new SparseMultigraph<String, String>());
        // Add some vertices. From above we defined these to be type Integer.
        Random rand = new Random();
        setGraphName("G"+g.hashCode());
        int  n = rand.nextInt(20) + 1;
        for(int i=0; i < n; i++){
            getG().addVertex("V"+i);
            getG().addVertex("V"+(i+1));

            if(i%3 == 0)
                getG().addEdge("Edge-A"+i,"V"+i,"V"+(i+1),et);
            else
                getG().addEdge("Edge-A"+i,"V"+(i+1),"V"+i,et);
            if(i>4 && i%5 != 0){
                getG().addEdge("Edge-B"+i, "V"+i, "V"+(i-(rand.nextInt(4) + 2)), et);

            }

        }
        return getG();
    }

    /**
     * Get a layer and draw graph from its edges and vertices
     * @param layer
     * @return
     */
    /*public Graph<String, String> createGraph(Layer layer){
        
        setG(new SparseMultigraph<String, String>());
        setGraphName(layer.getLayerName());

        
        for(int Vkey: layer.getVertices().keySet()){
            getG().addVertex(layer.getVertices().get(Vkey));
        }
        int i = 0;
        for (int  Ekey : layer.getEdges().keySet()) {
            Edge e = layer.getEdges().get(Ekey);
            getG().addEdge(e.getEdgeName(), e.getVertex1(), e.getVertex2());
            
        }
        
        
        return getG();
        
    }*/
    



    /**
     * @return the g
     */
    public Graph<String, String> getG() {
        return g;
    }

    /**
     * @param g the g to set
     */
    public void setG(Graph<String, String> g) {
        this.g = g;
    }

    /**
     * @return the graphName
     */
    public String getGraphName() {
        return graphName;
    }

    /**
     * @param graphName the graphName to set
     */
    public void setGraphName(String graphName) {
        this.graphName = graphName;
    }
    
}
