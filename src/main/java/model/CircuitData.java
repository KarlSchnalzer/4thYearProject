package model;

import com.sun.org.apache.xpath.internal.operations.And;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;


/**
 *
 * @author Karl Schnalzer
 * Class CircuitData is a java class which keeps track of all circuit
 * data(connections, input/ outputs, etc.)
 */
public class CircuitData {
    private HashMap<Integer,LogicGate> logicGates;
    private ArrayList<NewChange> list; //Queue for NewChange JSON files being passed to client
    //private long id;
    //private static Socket socket;

    public CircuitData(){
        logicGates = new HashMap<Integer, LogicGate>();
        list = new ArrayList<NewChange>();
        //id = 1;
    }
    /**
     * Adds the type of logic gate to the "Gates map"
     * @param lg - the logic gate being added to the map
     */
    public void addGate(int id,LogicGate lg){
        logicGates.put( id,lg);
        //id++;
    }
    public void addNewChange(NewChange nc){
        list.add(nc);
    }
    /**
     *
     * @param id - ID of the gate the user wishes to return
     * @return returns the Logic Gate with the corresponding ID
     */
    public LogicGate getGate(int id){
        return logicGates.get(id);
    }

    /**
     *
     * @return - returns the NewChange JSON file at the front of the queue,
     * if there is nothing in the queue, the method returns a message with the eventID 0
     * meaning there are no new updates.
     */
    public NewChange getNewChange(){
        if(list.isEmpty()){
            NewChange nc = new NewChange(0,0,false);
            return nc;
        }else {
            NewChange nc = list.remove(0);
            return nc;
        }
    }
    /**
     * Removes the selected logic gate from the hash map along with removing its connection id
     * from any gates that were possibly connected to it
     * @param id - id of the gate being removed
     */
    public void removeGate(int id){
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
                if(logicGates.get(logicGates.get(id).getOutputId()).getConnectionOneId()== null){
                    logicGates.get(id).setInput(logicGates.get(i1ID).getOutput());
                    logicGates.get(id).setConnectionOneId(i1ID);
                    logicGates.get(i1ID).setOutputId(id);
                }
                else if(logicGates.get(logicGates.get(id).getOutputId()).getConnectionTwoId()== null){
                    logicGates.get(id).setInput2(logicGates.get(i1ID).getOutput());
                    logicGates.get(id).setConnectionTwoId(i1ID);
                    logicGates.get(i1ID).setOutputId(id);
                }
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
    public void turnOnOrOff(int id){
        for(int key: logicGates.keySet()){
            if(key==id) {
                logicGates.get(key).turnOnOrOff();
                //If gates output is 1, a message will be created to be sent to the client to update that the
                //gate is "ON", else (0) the gate will be "OFF"
                if (logicGates.get(key).getOutput() == 1) {
                    NewChange nc = new NewChange(6, logicGates.get(key).getOutputId(), true);
                    list.add(nc);
                } else {
                    NewChange nc = new NewChange(6, logicGates.get(key).getOutputId(), false);
                    list.add(nc);
                }

                //When a switch is turned on/off every gate that is affected by this input must change their
                //output as well therefore, while there is an outputId, that gate must be updated as well
                for (Integer i = key; logicGates.get(i).getOutputId() != null; i = logicGates.get(i).getOutputId()) {
                    if (logicGates.get(logicGates.get(i).getOutputId()).getConnectionOneId() == i) {
                        logicGates.get(logicGates.get(i).getOutputId()).setInput(logicGates.get(i).getOutput());
                        //If gates output is 1, a message will be created to be sent to the client to update that the
                        //gate is "ON", else (0) the gate will be "OFF"
                        createNewChange6(i);
                    } else if (logicGates.get(logicGates.get(i).getOutputId()).getConnectionTwoId() == i) {
                        logicGates.get(logicGates.get(i).getOutputId()).setInput2(logicGates.get(i).getOutput());
                        createNewChange6(i);
                    }
                }

            }
        }

    }

    private void createNewChange6(int i) {
        if (logicGates.get(logicGates.get(i).getOutputId()).getOutput() == null || logicGates.get(logicGates.get(i).getOutputId()).getOutput() == 1) {
            NewChange nc = new NewChange(6, logicGates.get(i).getOutputId(), true);
            list.add(nc);
        }
        else{
            NewChange nc = new NewChange(6, logicGates.get(i).getOutputId(), false);
            list.add(nc);
        }
    }

    public static void main(String[] args){
        CircuitData data = new CircuitData();
        Input i1 = new Input(1,1);
        Input i2 = new Input(1,2);
        Input i3 = new Input(1,3);
        Input i4 = new Input(1,4);
        XorGate xg = new XorGate(2,1);
        AndGate ag = new AndGate(2,2);
        AndGate ag2 = new AndGate(3,1);
        Output o = new Output(4,1);
        data.addGate(1,i1);
        data.addGate(2,i2);
        data.addGate(3,i3);
        data.addGate(4,i4);
        data.addGate(5,xg);
        data.addGate(6,ag);
        data.addGate(7,ag2);
        data.addGate(8,o);
        data.setInput(5,1);
        data.setInput2(5,2);
        data.setInput(6,3);
        data.setInput2(6,4);
        data.setInput(7,5);
        data.setInput2(7,6);
        data.setInput(8,7);
        data.turnOnOrOff(2);
        data.turnOnOrOff(3);
        data.turnOnOrOff(4);
        System.out.println(data.getGateInput(8));
    }
}


