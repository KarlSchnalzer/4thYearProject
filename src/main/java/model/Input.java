package model;
/**
 *
 * @author Karl Schnalzer
 * Class Input is a subclass of LogicGate representing an input switch for a logic circuit
 */
public class Input extends LogicGate{
    /**
     * Constructor for a Input "gate"
     * @param xc - the x-coordinate of the gate
     * @param yc - the y-coordinate of the gate
     */
    public Input(double xc, double yc){
        super(xc,yc);
        this.setOutput(0);
    }

    /**
     * Default constructor for a Input "gate"
     */
    public Input(){
        this(0,1.0);
        this.setOutput(0);
    }




}
