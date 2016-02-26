/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.model;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Spiros
 */
public class MultiplexCLDC {
    private final MultilevelSparseMultigraph mg;
    private Graph<Vertex, Edge> G;
    private final Map<Vertex, Float> cldc;
    private final int layersToCount;
    private boolean inOrOut;
    private final boolean normalized;
    
    public MultiplexCLDC(MultilevelSparseMultigraph mg, int layersToCount, boolean normalized) throws Exception{
        this.mg = mg;
        this.layersToCount = layersToCount;
        this.normalized = normalized;
        this.cldc = new HashMap();
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
        
        for(Vertex v: G.getVertices()){
            List<Float> layerDegrees = new ArrayList<>();
            for(int layerKey: mg.getLayerList().keySet()){
                if(directed == EdgeType.UNDIRECTED){
                    layerDegrees.add((float)mg.getLayerList().get(layerKey).degree(v));
                }
                else{
                    if(inOrOut == true)
                        layerDegrees.add((float)mg.getLayerList().get(layerKey).inDegree(v));
                    else
                        layerDegrees.add((float)mg.getLayerList().get(layerKey).outDegree(v));
                }
            }
            Collections.sort(layerDegrees);
            Collections.reverse(layerDegrees);
            System.out.println(layerDegrees);
            float cldcDeg = layerDegrees.get(layersToCount);
            cldc.put(v, cldcDeg);
        }
        
        if(normalized){
            normalize();
        }
        
    }
    
    public final void normalize(){
        for(Vertex v : cldc.keySet()){
            float val = cldc.get(v);
            val /= ((mg.getLayerList().get(1).getVertexCount() - 1) * mg.getLayerList().size());
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
