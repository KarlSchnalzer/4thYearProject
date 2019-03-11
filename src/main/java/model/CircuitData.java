package model;

import com.sun.org.apache.xpath.internal.operations.And;

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
        if(logicGates.get(id).getConnectionOneId()!=null){
            logicGates.get(logicGates.get(id).getConnectionOneId()).disconnectOutput();
        }
        if(logicGates.get(id).getConnectionTwoId()!=null){
            logicGates.get(logicGates.get(id).getConnectionTwoId()).disconnectOutput();
        }
        if(logicGates.get(id).getOutputId()!=null){
            for(int key: logicGates.keySet()){
                if(logicGates.get(key).getConnectionOneId()==logicGates.get(id).getOutputId()){
                    logicGates.get(key).disconnectC1();
                }
                else if (logicGates.get(key).getConnectionTwoId()==logicGates.get(id).getOutputId()){
                    logicGates.get(key).disconnectC2();
                }
            }
        }
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
    public int getGateInput(int id){
        for(int key: logicGates.keySet()){
            if(key==id){
                return logicGates.get(key).getInput1();
            }
        }
        return 0;
    }

    /**
     * Sets the input value of the selected gate, also sets  the connection id of the gate and the output id
     * of the gate providing the input
     * @param id - ID of the gate which is receiving the input
     * @param i1ID - ID of the Gate providing the input
     */
    public void setInput(int id, int i1ID){
        for(int key: logicGates.keySet()){
            if(key==id){
                logicGates.get(key).setInput(logicGates.get(i1ID).getOutput());
                logicGates.get(key).setConnectionId(i1ID);
                logicGates.get(i1ID).setOutputId(key);
            }
        }
    }
    public void setInput2(int id, int i2ID){
        for(int key: logicGates.keySet()){
            if(key==id){
                logicGates.get(key).setInput2(logicGates.get(i2ID).getOutput());
                logicGates.get(key).setConnectionId(i2ID);
                logicGates.get(i2ID).setOutputId(key);
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
        CircuitData data = new CircuitData();
        Input i1 = new Input(1,1);
        Input i2 = new Input(1,2);
        Input i3 = new Input(1,3);
        Input i4 = new Input(1,4);
        i1.setOutput(1);
        i2.setOutput(1);
        i3.setOutput(1);
        i4.setOutput(1);
        AndGate ag = new AndGate(2,1);
        AndGate ag2 = new AndGate(2,2);
        AndGate ag3 = new AndGate(3,1);
        Output o = new Output(4,1);
        data.addGate(1,i1);
        data.addGate(2,i2);
        data.addGate(3,i3);
        data.addGate(4,i4);
        data.addGate(5,ag);
        data.addGate(6,ag2);
        data.addGate(7,ag3);
        data.addGate(8,o);
        data.setInput(5,1);
        data.setInput2(5,2);
        data.setInput(6,3);
        data.setInput2(6,4);
        data.setInput(7,5);
        data.setInput2(7,6);
        data.setInput(8,7);
        System.out.println(data.getGateInput(8));
    }
}


