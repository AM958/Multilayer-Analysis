/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.model;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Pair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author spiros
 */
public class MultilplexBetweennessCentrality {
    
    MultilevelSparseMultigraph mg;
    Graph<String, String> G; //acyclic graph
    private Map<Vertex, Float> betweenness;
    
    public MultilplexBetweennessCentrality(MultilevelSparseMultigraph mg) throws Exception{
        this.mg = mg;
        this.betweenness = new HashMap();
        calc();
    }
    
    public final void calc() throws Exception{
        
        Stack<Pair> S;    //stack of vertices
        ArrayList<Pair>[] P; //shortest path acyclic graph
        float[] sigma; //# of s.p that go through vertex[index]
        float[] sigmaM;
        float[] d; //distance in layer
        float[] dM; //distance in multiplex
        List<Pair>[] vOrder; //first time access
        Queue<Pair> Q;
        
        if(mg.getLayerList().get(1).getVertexCount() == 0)
            throw new Exception("The graph has no vertices!\n");
        
        int N = mg.getLayerList().get(1).getVertexCount();
        int L = mg.getLayerList().size();
        int NL = N*L;
        Collection<Vertex> vertices;

        vertices = mg.getLayerList().get(1).getVertices();
        
        
        //Collection<Vertex> W = new ();
        
        float [] CB = new float[N];
        //float[] delta = new float[NL];
        
        
        int idx;
        
        for (Vertex s : vertices) {
            idx = s.getId();
            
            
            S = new Stack();
            P = (ArrayList<Pair>[]) new ArrayList[NL];
            sigma = new float[NL];
            sigmaM = new float[N];
            d = new float[NL];
            dM = new float[NL];
            vOrder = new ArrayList[NL];
            Q = new LinkedList<>();
            
            for(int j=0;j<NL;j++){
                if(j%N == idx-1){
                    d[j] = 0;
                    dM[j] = 0;
                    sigma[j] = 1;
                }
                else{
                    d[j] = -1;
                    dM[j] = -1;
                }
                P[j] = new ArrayList<>();
                vOrder[j] = new ArrayList<>();
            }
            
            //pair of layer, vertex, if vertex = source layer we take the ertex from the 1st layer since it is present in any layer
            Q.add(new Pair(1, s));
            
            int vIdx;
            while(!Q.isEmpty()){
               
                Pair p = Q.remove();
                Vertex v = (Vertex) p.getSecond(); //dequeue
                int layer = (int) p.getFirst();
                S.push(p);
                vIdx = v.getId() + (layer - 1) * N - 1;
                
                Collection<Pair> W = new ArrayList();
                //Collection<Vertex> W = mg.getLayerList().get(1).getNeighbors(v);
                //System.out.println(v + "W" + W);
                Vertex tempV = null;
                
                if(v.toString().equals(s.toString())){
                    for(int layerKey: mg.getLayerList().keySet()){
                        for(Object vertex : mg.getLayerList().get(layerKey).getVertices()){
                            if(v.toString().equals(vertex.toString())){
                                tempV = (Vertex) vertex;
                            }
                        }
                        //Collection<Vertex> cvw;
                        if(mg.getLayerList().get(layerKey).getNeighbors(tempV) != null){
                            //adding a tuple containing the layer number and the neighbours
                            Collection<Vertex> cvw = new ArrayList();
                                    
                            //cvw.add(tempV);
                            cvw.addAll(mg.getLayerList().get(layerKey).getNeighbors(tempV));
                            W.add(new Pair(layerKey, cvw));
                        }
                    }                  
                }
                else{
                    // W will have only one tuple in this case
                    W.add(new Pair(layer ,mg.getLayerList().get(layer).getNeighbors(v)));
                }
               
                for(Pair lv : W){
                    Collection<Vertex> ww = (Collection<Vertex>)lv.getSecond();
                    for(Object neighbour : ww){
                        Vertex w = (Vertex) neighbour;
                        
                        int layerID = (int) lv.getFirst();
                        int wIdx = w.getId() + (layerID - 1) * N - 1;
                        
                        if( d[wIdx] < 0){
                            Q.add(new Pair(layerID, w));
                            d[wIdx] = d[vIdx] + 1;
                            for(int k=0;k<NL;k++){
                                if((k % N) == (w.getId() - 1)){
                                    if(dM[k] < 0 || dM[k] == d[wIdx]){
                                        dM[k] = d[wIdx];
                                        //d[k] = d[wIdx];
                                        vOrder[k%N].add(new Pair(layerID, w));
                                        
                                    }
                                }
                            }
                            //vOrder[w.getId() - 1].add(new Pair(layerID, w));
                        }
                        
                        if(d[wIdx] == d[vIdx] + 1 ){
                            sigma[wIdx] += sigma[vIdx]; 
                            //CHANGED!!!!!!!!!!!!!!!!!!!!!!!!!!!
                            /*for(int k=0;k<NL;k++){
                                if((k % N) == (w.getId() - 1)){
                                    P[k].add(new Pair(layer, v));
                                }
                            }*/
                            P[wIdx].add(new Pair(layer, v));
                            
                        }
                        
                    }
                }
            }//end queue
            System.out.println("all p" +Arrays.toString(P));
            for(int w = 0;w < N;w++){
                sigmaM[w] = 0;
                for(Pair p: vOrder[w]){
                    int l1 = (int) p.getFirst();
                    Vertex v1 = (Vertex) p.getSecond();
                    sigmaM[w] = sigmaM[w] + sigma[v1.getId() + (l1 - 1) * N - 1];
                   
                }
                //System.out.println(vOrder[w]);
            }//end #sp indep layer
            float[] delta = new float[NL];
            
            
            /*Stack<Pair> reversedStack = new Stack<Pair>();
            while (!S.empty())
            {
                reversedStack.push(S.pop());
            }
            S = reversedStack;*/
            

            while(!S.empty()){
                Pair stackPair = S.pop();
                int stackLayer = (int) stackPair.getFirst();
                Vertex stackVertex = (Vertex) stackPair.getSecond();
                int pid = stackVertex.getId() + (stackLayer - 1) * N - 1;

                for(Pair v: P[pid]){
                    
                    int vLayer = (int) v.getFirst();
                    Vertex vVertex = (Vertex) v.getSecond();
                    int vid = vVertex.getId() + (vLayer - 1) * N - 1;
                    if(vOrder[stackVertex.getId() - 1].contains(stackPair)){
                        delta[vid] = delta[vid] + (sigma[vid]/sigma[pid]) 
                                    * ((sigma[pid]/sigmaM[pid % N] + delta[pid])); 

                    }
                    else{
                        delta[vid] = delta[vid] + (sigma[vid]/sigma[pid]) * delta[pid];
                        
                    }

                }//end for P[w]
                if(!stackVertex.toString().equals(s.toString())){
                        CB[stackVertex.getId()-1] += delta[pid];
                        betweenness.put(stackVertex, CB[stackVertex.getId()-1]);
                        
                    }
            }//end while stack not empty
             
        }//end for s
       
        //System.out.println(Arrays.toString(CB));
        
        
    }

    /**
     * @return the betweenness
     */
    public Map<Vertex, Float> getBetweenness() {
        return betweenness;
    }
}
