package model;

/**
 *
 * @author Karl Schnalzer
 * Class LogicGate is superclass representing a logic gate with two inputs
 */
public class LogicGate {
    private Integer input1; // the first input of the gate
    private Integer input2; // the second input of the gate
    private double x; // the x-coordinate of the gate on the circuit canvas
    private double y; // the y-coordinate of the gate on the circuit canvas
    private Integer output;
    private Integer connectionOneId; //the id of the gate connected to the first input of the logic gate
    private Integer connectionTwoId; //the id of the gate connected to the second input of the logic gate
    private Integer outputId;
    /**
     * Constructor for a logic gate
     * @param xc - the x-coordinate of the gate
     * @param yc - the y-coordinate of the gate
     */
    public LogicGate(double xc, double yc){
        //input1, input2, output, connectionOneId, connection2, output and outputId all set to null originally
        // since there is no connection upon placement
        input1 = null;
        x = xc;
        y = yc;
        output = null;
        connectionOneId = null;
        connectionTwoId = null;
        outputId = null;
    }

    /**
     * Default constructor for a Logic gate
     */
    public LogicGate(){
        this(1,1);
    }


    /**
     * sets the x-coordinate of the gate
     * @param xc - the x-coordinate the user wishes to set
     */
    public void setX(double xc){
        x = xc;
    }

    /**
     * sets the y-coordinate of the gate
     * @param yc - the y-coordinate the user wishes to set
     */
    public void setY(double yc){
        y = yc;
    }

    /**
     * sets the first input of the AND gate
     * @param i1 - the value the user wishes to set the input to
     */
    public void setInput(int i1){
        input1 = i1;
    }

    /**
     * sets the second input of the AND gate
     * @param i2 - the value the user wishes to set the input to
     */
    public void setInput2(int i2){
        input2 = i2;
    }
    /**
     * sets a logic gate to be connected to the first input of the current gate
     * @param cId - the id of the first connectee logic gate
     */
    public void setConnectionOneId(Integer cId){
            connectionOneId = cId;
    }
    /**
     * sets a logic gate to be connected to the first input of the current gate
     * @param cId - the id of the second connectee logic gate
     */
    public void setConnectionTwoId(Integer cId){
            connectionTwoId = cId;
    }
    /**
     * sets the first connection to null in case of a disconnection
     */
    public void disconnectC1(){
        connectionOneId = null;
    }
    /**
     * sets the second connection to null in case of a disconnection
     */
    public void disconnectC2(){
        connectionTwoId = null;
    }
    /**
     * sets the output connection to null in case of a disconnection
     */
    public void disconnectOutput(){
        outputId = null;
    }
    /**
     * sets the id of the element receiving the signal from the gate
     * @param oId - the id of the gate sending the signal (or output)
     */
    public void setOutputId(Integer oId){
        outputId = oId;
    }
    public void setOutput(int o){
        output = o;
    }
    /**
     * Method used only for input switches, turns the input switch on or off depending on its current state,
     * on has a value of 1, off has a value of 0
     */
    public void turnOnOrOff(){
        this.setOutput(Math.abs(getOutput()-1));
    }
    /**
     *
     * @return - the x-coordinate of the gate
     */
    public double getX(){
        return x;
    }

    /**
     * @return - the y-coordinate of the gate
     */
    public double getY(){
        return y;
    }

    /**
     * @return - the first input of the gate
     */
    public Integer getInput1(){
        return input1;
    }

    /**
     * @return - the second input of the gate
     */
    public Integer getInput2(){
        return input2;
    }
    /**
     *
     * @return - the id of the first input connected gate
     */
    public Integer getConnectionOneId(){
        return connectionOneId;
    }
    /**
     *
     * @return - the id of the second input connected gate
     */
    public Integer getConnectionTwoId(){
        return connectionTwoId;
    }
    public Integer getOutputId(){
        return outputId;
    }
    /**
     *
     * @return the output of the logic gate
     */
    public Integer getOutput(){
        return output;
    }
}


