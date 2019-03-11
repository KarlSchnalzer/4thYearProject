package model;

import java.util.HashMap;


/**
 *
 * @author Karl Schnalzer
 * Class CircuitData is a java class which keeps track of all circuit
 * data(connections, input/ outputs, etc.)
 */
public class CircuitData {
    private HashMap<Integer,LogicGate> logicGates;
    //private long id;
    //private static Socket socket;

    public CircuitData(){
        logicGates = new HashMap<Integer, LogicGate>();
        //id = 1;
    }
    /**
     * Adds the type of logic gate to the "Gates map"
     * @param lg - the logic gate being added to the map
     */
    public void addGate(long id,LogicGate lg){
        logicGates.put((int) id,lg);
        //id++;
    }
    public void removeGate(long id){
        logicGates.remove(id);
    }

    public int getGateOutput(int id){
        for(int key: logicGates.keySet()){
            if(key==id){
                return logicGates.get(key).getOutput();
            }
        }
        return 0;
    }

    public void setInput(int id, int i1){
        for(int key: logicGates.keySet()){
            if(key==id){
                logicGates.get(key).setInput(i1);
            }
        }
    }
    public void setInput2(int id, int i2){
        for(int key: logicGates.keySet()){
            if(key==id){
                logicGates.get(key).setInput2(i2);
            }
        }
    }
    public void setX(long id, double xc){
        for(int key: logicGates.keySet()){
            if(key==id){
                logicGates.get(key).setX(xc);
            }
        }
    }
    public void setY(long id, double yc){
        for(int key: logicGates.keySet()){
            if(key==id){
                logicGates.get(key).setY(yc);
            }
        }
    }
    public static void main(String[] args){
    }
}


