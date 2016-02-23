/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.model;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;
import static edu.uci.ics.jung.graph.util.EdgeType.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Spiros
 */
public class MultiLayerDegreeCentrality {
    
    private final MultilevelSparseMultigraph mg;
    private int vertexCount;
    private Graph<Vertex, Edge> G; //acyclic graph
    private final Map<Vertex, Float> degreeCentrality;
    private double averageDegree;
    private boolean inOrOut; // true = in, false = out for directed degree
    
    public MultiLayerDegreeCentrality(MultilevelSparseMultigraph mg) throws Exception{
        this.mg = mg;
        if(mg.getLayerList().isEmpty()){
            throw new Exception("Error! No graph found, open file or check if the opened file has the proper syntax.\n");
        }
        degreeCentrality = new HashMap();
        averageDegree = 0;
        
    }
    
    public void calcDegree(){
        int cnt = 0;
        EdgeType directed;
        for(int layerKey: mg.getLayerList().keySet()){
            G = mg.getLayerList().get(layerKey);
            directed = G.getDefaultEdgeType();
            for (Edge e: G.getEdges()){
                directed = G.getEdgeType(e);
                break;
            }
            float degree;
            int currentVertexDegree;
            for(Vertex v: G.getVertices()){

                if(directed == DIRECTED){
                    if(inOrOut == true)
                        currentVertexDegree = G.inDegree(v);
                    else
                        currentVertexDegree = G.outDegree(v);
                }
                else{
                    currentVertexDegree = G.degree(v);
                }
                
                if(degreeCentrality.get(v) == null){
                    
                    degree = currentVertexDegree;
                }
                else{
                    degree = degreeCentrality.get(v) + currentVertexDegree;
                }
                degreeCentrality.put(v, degree);
                averageDegree += degree;        
            }

            cnt++;
        }
        for(MultiLevelEdge mle : mg.getMultiEdges()){
            float mldegree = degreeCentrality.get(mle.getVertex1());
            mldegree++;
            degreeCentrality.put(mle.getVertex1(), mldegree++);
            mldegree = degreeCentrality.get(mle.getVertex2());
            mldegree++;
            degreeCentrality.put(mle.getVertex2(), mldegree++);
                     
        }
        averageDegree = averageDegree / (mg.getLayerList().get(1).getVertexCount() * cnt);
    }

    /**
     * @return the degreeCentrality
     */
    public Map<Vertex, Float> getDegreeCentrality() {
        return degreeCentrality;
    }

    /**
     * @return the averageDegree
     */
    public double getAverageDegree() {
        return averageDegree;
    }

    /**
     * @return the inOrOut
     */
    public boolean isInOrOut() {
        return inOrOut;
    }

    /**
     * @param inOrOut the inOrOut to set
     */
    public void setInOrOut(boolean inOrOut) {
        this.inOrOut = inOrOut;
    }
    
    
}
