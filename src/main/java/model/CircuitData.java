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
    public LogicGate getGate(long id){
        return logicGates.get(id);
    }

    /**
     * Removes the selected logic gate from the hash map along with removing its connection id
     * from any gates that were possibly connected to it
     * @param id - id of the gate being removed
     */
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

    /**
     *
     * @param id - id of the gate being selected
     * @return returns the output of the selected gate
     */
    public int getGateOutput(int id){
        for(int key: logicGates.keySet()){
            if(key==id){
                return logicGates.get(key).getOutput();
            }
        }
        return 0;
    }

    /**
     *
     * @param id - id of the gate being selected
     * @return returns the first input of the gate, this is the method used for gates with only one input
     */
    public int getGateInput(int id){
        for(int key: logicGates.keySet()){
            if(key==id){
                return logicGates.get(key).getInput1();
            }
        }
        return 0;
    }

    /**
     * Sets the input value of the selected gate, also sets the connection id of the gate and the output id
     * of the gate providing the input, this is the method used for gates with only one input
     * @param id - ID of the gate which is receiving the input
     * @param i1ID - ID of the Gate providing the input
     */
    public void setInput(int id, int i1ID){
        for(int key: logicGates.keySet()){
            if(key==id){
                logicGates.get(key).setInput(logicGates.get(i1ID).getOutput());
                logicGates.get(key).setConnectionOneId(i1ID);
                logicGates.get(i1ID).setOutputId(key);
            }
        }
    }
    /**
     * Sets the second input value of the selected gate, also sets the connection id of the gate and the output id
     * of the gate providing the input
     * @param id - ID of the gate which is receiving the input
     * @param i2ID - ID of the Gate providing the input
     */
    public void setInput2(int id, int i2ID){
        for(int key: logicGates.keySet()){
            if(key==id){
                logicGates.get(key).setInput2(logicGates.get(i2ID).getOutput());
                logicGates.get(key).setConnectionTwoId(i2ID);
                logicGates.get(i2ID).setOutputId(key);
            }
        }
    }

    /**
     * Sets the x-coordinate of the selected gate
     * @param id - the id of the gate being selected
     * @param xc - the new x-coordinate
     */
    public void setX(long id, double xc){
        for(int key: logicGates.keySet()){
            if(key==id){
                logicGates.get(key).setX(xc);
            }
        }
    }
    /**
     * Sets the y-coordinate of the selected gate
     * @param id - the id of the gate being selected
     * @param yc - the new y-coordinate
     */
    public void setY(long id, double yc){
        for(int key: logicGates.keySet()){
            if(key==id){
                logicGates.get(key).setY(yc);
            }
        }
    }
    /**
     * turns the input switch on or off depending on its current state,
     * on has a value of 1, off has a value of 0
     * @param id - the id of the gate being selected
     */
    public void turnOnOrOff(long id){
        for(int key: logicGates.keySet()){
            if(key==id){
                logicGates.get(key).turnOnOrOff();
            }
            //When a switch is turned on/off every gate that is affected by this input must change their
            //output as well therefore, while there is an outputId, that gate must be updated as well
            for(int i = key; logicGates.get(i).getOutputId() != null; i = logicGates.get(i).getOutputId()){
                if(logicGates.get(logicGates.get(i).getOutputId()).getConnectionOneId() == i){
                    logicGates.get(logicGates.get(i).getOutputId()).setInput(logicGates.get(i).getOutput());
                }
                else if(logicGates.get(logicGates.get(i).getOutputId()).getConnectionTwoId() == i){
                    logicGates.get(logicGates.get(i).getOutputId()).setInput2(logicGates.get(i).getOutput());
                }
            }
        }

    }
    public static void main(String[] args){
    }
}


