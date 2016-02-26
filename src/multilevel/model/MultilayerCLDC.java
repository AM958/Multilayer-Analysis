/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.model;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.graph.util.Pair;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Spiros
 */
public class MultilayerCLDC {
    private final MultilevelSparseMultigraph mg;
    private Graph<Vertex, Edge> G;
    private final Map<Vertex, Float> cldc;
    private final int layersToCount;
    private boolean inOrOut;
    private final boolean normalized;
    private int vertexCount;
    
    public MultilayerCLDC(MultilevelSparseMultigraph mg, int layersToCount, boolean normalized) throws Exception{
        this.mg = mg;
        this.layersToCount = layersToCount;
        this.normalized = normalized;
        this.cldc = new HashMap();
        this.inOrOut = false;
        calc();
        
    }
    
    public final void calc(){
        EdgeType directed;
        //for(int layerKey: mg.getLayerList().keySet()){
        G = mg.getLayerList().get(1);
        directed = G.getDefaultEdgeType();
        for (Edge e: G.getEdges()){
            directed = G.getEdgeType(e);
            break;
        }
        
        for(int layerKey: mg.getLayerList().keySet()){
            G = mg.getLayerList().get(layerKey);
            for(Vertex v : G.getVertices()){
                vertexCount++;
                List<Float> layerDegrees = new ArrayList<>();
                //layerDegrees.add((float)G.degree(v));
                for(int layerKey2: mg.getLayerList().keySet()){ 
                    float degree = 0;
                    for(MultiLevelEdge mle : mg.getMultiEdges()){
                        if(directed == EdgeType.UNDIRECTED || inOrOut == true){
                            if((mle.getVertex2().toString().equals(v.toString()) && mle.getLayer1().equals(mg.getGraphName(layerKey2)))){
                                degree++;
                            }
                        }
                        if(directed == EdgeType.UNDIRECTED || inOrOut == false){
                            if((mle.getVertex1().toString().equals(v.toString()) && mle.getLayer2().equals(mg.getGraphName(layerKey2)))){
                                degree++;
                            }
                        }
                    }
                    if(mg.getLayerList().get(layerKey2).containsVertex(v)){
                        if(inOrOut == true)
                            degree = mg.getLayerList().get(layerKey2).inDegree(v);
                        else if(directed == EdgeType.UNDIRECTED || inOrOut == false)
                            degree = mg.getLayerList().get(layerKey2).outDegree(v);
                    }
                    layerDegrees.add(degree);

                }
                Collections.sort(layerDegrees);
                Collections.reverse(layerDegrees);
            
                float cldcDeg = layerDegrees.get(layersToCount);
                cldc.put(v, cldcDeg);
            }  
        }
        
        if(normalized){
            normalize();
        }
        
    }
    
    public final void normalize(){
        for(Vertex v : cldc.keySet()){
            float val = cldc.get(v);
            val /= ((vertexCount - 1) * mg.getLayerList().size());
            cldc.put(v, val);
        }
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

    /**
     * @return the cldc
     */
    public Map<Vertex, Float> getCldc() {
        return cldc;
    }
            
            
    
        
}
