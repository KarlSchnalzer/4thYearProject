package model;

/**
 *
 * @author Karl Schnalzer
 * Class AndGate is a subclass of LogicGate representing an AND logic gate
 */
public class AndGate extends LogicGate{

    /**
     * Constructor for a gate
     * @param xc - the x-coordinate of the gate
     * @param yc - the y-coordinate of the gate
     */
    public AndGate(double xc, double yc){
        super(xc,yc);
    }

    /**
     * Default constructor for an AND gate
     */
    public AndGate(){
        this(1,1);
    }

    /**
     * Computes the output of the AND gate using boolean logic for the inputs
     * @return - the output of the gate
     */
    public int getOutput(){
        if(getInput1()==1 && getInput2()==1){
            return 1;
        }
        else{
            return 0;
        }
    }
}


