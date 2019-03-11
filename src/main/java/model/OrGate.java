package model;

/**
 *
 * @author Karl Schnalzer
 * Class AndGate is a subclass of LogicGate representing an OR logic gate
 */
public class OrGate extends LogicGate{
    /**
     * Constructor for an OR gate
     * @param xc - the x-coordinate of the gate
     * @param yc - the y-coordinate of the gate
     */
    public OrGate(double xc, double yc){
        super(xc,yc);
    }

    /**
     * Default constructor for an OR gate
     */
    public OrGate(){
        this(1.0,1.0);
    }

    /**
     * Computes the output of the OR gate using boolean logic for the inputs
     * @return - the output of the gate
     */
    public Integer getOutput(){
        if(getInput1()==1 || getInput2()==1){
            return 1;
        }
        else{
            return 0;
        }

    }
}


