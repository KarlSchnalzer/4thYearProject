package model;

/**
 *
 * @author Karl Schnalzer
 * Class NotGate is a class representing an NOT logic gate
 */
public class NotGate extends LogicGate{

    /**
     * Constructor for a NOT gate
     * @param xc - the x-coordinate of the gate
     * @param yc - the y-coordinate of the gate
     */
    public NotGate(double xc, double yc){
        super(xc,yc);
    }

    /**
     * Default constructor for a NOT gate
     */
    public NotGate(){
        this(0,1.0);
    }


    /**
     * Computes the output of the NOT gate using boolean logic for the inputs
     * @return - the output of the gate
     */
    public int getOutput(){
        return Math.abs(getInput1()-1);
    }

}

