/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilevel.model;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Spiros
 */
public class MultiLayerPCI {
    private final MultilevelSparseMultigraph mg;
    private int vertexCount;
    private Graph<Vertex, Edge> G; //acyclic graph
    private Map<Vertex, List<Pair>> perLayerDegree;
    private Map<Vertex, Float> pci;
    private double averageDegree;
    private boolean inOrOut; // true = in, false = out for directed degree
    
    private int layers;
    private pciType type;
    
    public MultiLayerPCI(MultilevelSparseMultigraph mg, pciType type, int layers) throws Exception{
        
        this.mg = mg;
        this.type = type;
        this.layers = layers;
        
        if(mg.getLayerList().isEmpty()){
            throw new Exception("Error! No graph found, open file or check if the opened file has the proper syntax.\n");
        }
        pci = new HashMap();
        perLayerDegree = new HashMap();
        perLayerDegree();
        switch(type){
            case ORIGINAL:
                originalCalc();
                break;
            case AGNOSTIC:
                agnosticPCIcalc();
                break;
            case ALL:
                allCalc();
                break;
            case SYMMETRIC:
                symmetricCalc2(mg.getLayerList().size());
                break;
            case MINIMAL:
                //symmetricCalc(layers);
                minimalCalc();
                break;
            case SCA:
                scaCalc();
                break;
            default:
                throw new Exception("No PCI method selected!");
                
        }
        
        
    }
    
    private final void minimalCalc(){
        Map<Vertex, Float> tempPCI = new HashMap();
        float count;
        for(int i = 1; i <= mg.getLayerList().size(); i++){
            
            
            if(i == 1){
                originalCalc();
            }
            else{
                symmetricCalc(i);
            }

            for(Vertex val: pci.keySet()){
                //count = (float) ((Math.pow(2, i-1) * pci.get(val)) + tempPCI.get(val));
                float tpci = 0;
                if(tempPCI.get(val) != null)
                    tpci = tempPCI.get(val);
                tempPCI.put(val, pci.get(val) + tpci);
            }
            
            
            //tempPCI = pci;
        }
        pci = tempPCI;
    }
    
    private final void scaCalc(){
        Map<Vertex, Float> tempPCI = new HashMap();
        float count;
        for(int i = 1; i <= mg.getLayerList().size(); i++){
            
            if(i == 1){
                originalCalc();
            }
            else{
                symmetricCalc(i);
            }

            for(Vertex val: pci.keySet()){
                //count = (float) ((Math.pow(2, i-1) * pci.get(val)) + tempPCI.get(val));
                float tpci = 0;
                if(tempPCI.get(val) != null)
                    tpci = tempPCI.get(val);
                tempPCI.put(val, (float) (Math.pow(2, i-1) * pci.get(val)) + tpci);
            }
            
            
            //tempPCI = pci;
        }
        pci = tempPCI;
    }    
    
    /**
     * Calculate original power community index
     */
    public final void originalCalc(){
        for(int layerKey: mg.getLayerList().keySet()){
            G = mg.getLayerList().get(layerKey);
            for(Vertex v: G.getVertices()){
                System.out.println("entries" + perLayerDegree.get(v) + "for vertex " + v);
                float maxdegree = -1;
                float sum = 0;
                for(Vertex n: G.getNeighbors(v)){
                    if(G.degree(n) > maxdegree){
                        maxdegree = G.degree(n);
                    }
                }
                //sum++;
                maxdegree++;
                while(maxdegree > sum){
                    maxdegree--;
                    for(Vertex n: G.getNeighbors(v)){
                        if(G.degree(n) == maxdegree)
                            sum++;
                    }
                    
                }
                pci.put(v, maxdegree);                
            }
        }
    }
    
    /**
     * Calculate layer-agnostic power community index
     * @throws Exception
     */
    public final void agnosticPCIcalc() throws Exception{
        MultiLayerDegreeCentrality degreeC = new MultiLayerDegreeCentrality(mg);
        //degreeC.setInOrOut(false);
        degreeC.calcDegree();
        for(int layerKey: mg.getLayerList().keySet()){
            G = mg.getLayerList().get(layerKey);
            for(Vertex v: G.getVertices()){
                
                float maxdegree = -1;
                float sum = 0;
                for(Vertex n: G.getNeighbors(v)){
                    if(degreeC.getDegreeCentrality().get(n) > maxdegree){
                        maxdegree = degreeC.getDegreeCentrality().get(n);
                        
                    }
                }
                //sum++;
                maxdegree++;
                while(maxdegree > sum){
                    maxdegree--;
                    for(Vertex n: G.getNeighbors(v)){
                        if(degreeC.getDegreeCentrality().get(n) == maxdegree)
                            sum++;
                    }
                    
                }
                pci.put(v, maxdegree);                
            }
        }
    }
    
    /**
     * Calculate all layers power community index
     */
    public final void allCalc(){
        for(int layerKey: mg.getLayerList().keySet()){
            G = mg.getLayerList().get(layerKey);
            for(Vertex v: G.getVertices()){
                
                float maxdegree = -1;
                float sum = 0;
                for(Vertex n: G.getNeighbors(v)){
                    
                    if(minLinksToAllLayers(n) > maxdegree){
                        maxdegree = minLinksToAllLayers(n);
                    }
                }
                //sum++;
                maxdegree++;
                while(maxdegree > sum){
                    maxdegree--;
                    for(Vertex n: G.getNeighbors(v)){
                        if(minLinksToAllLayers(n) == maxdegree)
                            sum++;
                    }
                    
                }
                pci.put(v, maxdegree);                
            }
        }
    }
    
    public final float minLinksToAllLayers(Vertex v){
        List<Pair> p = perLayerDegree.get(v);
        float minLinks = Float.MAX_VALUE;
        for (Pair pp : p) {
            
            if((float)pp.getSecond() < minLinks){
                minLinks = (float)pp.getSecond();
            }
        }
        if(minLinks == Float.MAX_VALUE)
            minLinks = 0;
        System.out.println("min link " + minLinks + "for vertex " + v);
        return minLinks;
    }
    
    /**
     * Calculate layer-symmetric power community index
     * @param layers
     */
    public final void symmetricCalc(int layers){
        for(int layerKey: mg.getLayerList().keySet()){
            G = mg.getLayerList().get(layerKey);
            for(Vertex v: G.getVertices()){
                
                float maxdegree = -1;
                float sum = 0;
                for(Vertex n: G.getNeighbors(v)){
                    
                    if(maxLinksToAnyLayers(n, layers) > maxdegree){
                        maxdegree = maxLinksToAnyLayers(n, layers);
                        if(v.toString().equals("P"))
                            System.out.println("max is " + maxdegree + "ver " + n + "lay" + layers);
                    }
                }
                //sum++;
                maxdegree++;
                while(maxdegree > sum){
                    maxdegree--;
                    for(Vertex n: G.getNeighbors(v)){
                        if(maxLinksToAnyLayers(n, layers) == maxdegree)
                            sum++;
                    }
                    if(v.toString().equals("P"))
                            System.out.println("max is " + maxdegree + "sum is " + sum);
                }
                pci.put(v, maxdegree);                
            }
        }
     }
     
     public final float maxLinksToAnyLayers(Vertex v, int maxLayers){
        List<Pair> p = perLayerDegree.get(v);
        float maxLinks = -1;
        int sum = 0;
        for (Pair pp : p) {
            
            if((float)pp.getSecond() > maxLinks){
                maxLinks = (float)pp.getSecond();
            }
        }

        maxLinks++;
        while(sum < maxLinks || sum < maxLayers){
            
            maxLinks--;
            for (Pair pp : p) {
                if((float)pp.getSecond() == maxLinks){
                    sum++;
                }
            }
        }

        return maxLinks;
    }
    

       public final void symmetricCalc2(int layers){
        for(int layerKey: mg.getLayerList().keySet()){
            G = mg.getLayerList().get(layerKey);
            for(Vertex v: G.getVertices()){
                
                float maxdegree = -1;
                float sum = 0;
                for(Vertex n: G.getNeighbors(v)){
                    
                    if(maxLinksToAnyLayers2(n, layers) > maxdegree){
                        maxdegree = maxLinksToAnyLayers2(n, layers);
                    }
                }
                //sum++;
                maxdegree++;
                while(maxdegree > sum){
                    maxdegree--;
                    for(Vertex n: G.getNeighbors(v)){
                        if(maxLinksToAnyLayers2(n, layers) == maxdegree)
                            sum++;
                    }
                    
                }
                pci.put(v, maxdegree);                
            }
        }
     }
     
     public final float maxLinksToAnyLayers2(Vertex v, int maxLayers){
        List<Pair> p = perLayerDegree.get(v);
        float maxLinks = -1;
        int sum = 0;
        for (Pair pp : p) {
            
            if((float)pp.getSecond() > maxLinks){
                maxLinks = (float)pp.getSecond();
            }
        }
        maxLinks++;
        while(sum < maxLinks && sum < maxLayers){
            maxLinks--;
            for (Pair pp : p) {
                if((float)pp.getSecond() == maxLinks){
                    sum++;
                }
            }
        }

        return maxLinks;
    }  
     
    public final void perLayerDegree(){
 
        for(int layerKey: mg.getLayerList().keySet()){
            String g = mg.getGraphName(layerKey);
            G = mg.getLayerList().get(layerKey);
            for(Vertex v : G.getVertices()){  
                List<Pair> pairs = new ArrayList();
                for(int layerKey2: mg.getLayerList().keySet()){ 
                    float degree = 0;
                    for(MultiLevelEdge mle : mg.getMultiEdges()){
                        if((mle.getVertex1().toString().equals(v.toString()) && mle.getLayer2().equals(mg.getGraphName(layerKey2)))
                                || (mle.getVertex2().toString().equals(v.toString()) && mle.getLayer1().equals(mg.getGraphName(layerKey2)))){

                            degree++;
                        }
                    }
                    if(mg.getLayerList().get(layerKey2).containsVertex(v)){
                        degree = mg.getLayerList().get(layerKey2).degree(v);
                    }
                    Pair pl = new Pair(layerKey2, degree);
                    pairs.add(pl);
                }
                perLayerDegree.put(v, pairs);
            }  
        }
    }

    /**
     * @return the power community index
     */
    public Map<Vertex, Float> getPci() {
        return pci;
    }
}
