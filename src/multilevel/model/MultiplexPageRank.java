/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.model;

import edu.uci.ics.jung.graph.Graph;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author spiros
 */
public class MultiplexPageRank {
    
    MultilevelSparseMultigraph mg;
    private final double alpha;
    private final double beta;
    private final double gamma;
    private int vertexCount;
    private Graph<Vertex, Edge> G; 
    private Map<Vertex, Float> X;
    private Map<Vertex, Float> X_prev;
    private double avgX;

    
    public MultiplexPageRank(MultilevelSparseMultigraph mg, double alpha, double beta, double gamma) throws Exception{
        
        this.mg = mg;
        if(mg.getLayerList().isEmpty()){
            throw new Exception("Error! No graph found, open file or check if the opened file has the proper syntax.");
        }
        if(alpha < 0 || beta < 0 || gamma < 0){
            throw new Exception("Error! PageRank parameters should be > 0.");
        }
        this.alpha = alpha;
        this.beta = beta;
        this.gamma = gamma;
        this.X = new HashMap();
        this.X_prev = new HashMap();
        this.vertexCount = mg.getLayerList().get(1).getVertexCount();
        initPR();
        calc();

    }
    
    public final void initPR(){
        
        //for(int layerKey : mg.getLayerList().keySet()){
        G = mg.getLayerList().get(1);
        for(Vertex v: G.getVertices()){
            float xl;
            xl = 1/G.getVertexCount();
            X.put(v, xl);
            X_prev.put(v, (float) 1.0);
        }
        //}
          
    }
    
    public final void calc(){
        
        //adjacencyMatrix = new ArrayList[mg.getLayerList().get(1).getVertexCount()][mg.getLayerList().get(1).getVertexCount()];
        float sum;// = 0;
        for(int layerKey : mg.getLayerList().keySet()){
            G = mg.getLayerList().get(layerKey);
            
            for(Object i: G.getVertices()){
                Vertex ii = (Vertex) i;
                sum = 0;
                for(Object j: G.getNeighbors(ii)){
                    Vertex jj = (Vertex) j;
                    
                    sum += Math.pow(X_prev.get(jj), beta) * G.findEdge(ii, jj).getWeight() 
                            * (X.get(jj)/calcGj(jj));
                }
                float x;
                double sumAvg = 0;
                for(Vertex xx : X_prev.keySet()){
                    sumAvg += X_prev.get(xx);
                }
                avgX = sumAvg / vertexCount;
                x = (float) (alpha * sum + (1 - alpha) * (Math.pow(X_prev.get(ii), gamma) / (vertexCount * Math.pow(avgX, gamma))));
                //X_prev.put(ii.getId(), X.get(ii.getId()));
                X.put(ii, x);
            }
            X_prev.clear();
            X_prev.putAll(X);
        }
        
        
    }
    
    public final double calcGj(Vertex j) {
    
        double Gj = 1.0;
        double sum = 0;
        for(Vertex r: G.getNeighbors(j)){
            if((G.findEdge(r, j)) != null){
                sum += G.findEdge(r, j).getWeight() * Math.pow(X_prev.get(r), beta);
            }
        }
        if(sum > 0)
            Gj = sum;
        
        return Gj;
        
    }

    /**
     * @return the X
     */
    public Map<Vertex, Float> getX() {
        return X;
    }

    /**
     * @return the avgX
     */
    public double getAvgX() {
        double sumAvg = 0;
        for(Vertex xx : X.keySet()){
            sumAvg += X.get(xx);
        }
        avgX = sumAvg / vertexCount;
        return avgX;
    }
    
}
