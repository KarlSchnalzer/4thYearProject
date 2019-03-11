package model;

/**
 *
 * @author Karl Schnalzer
 * Class AndGate is a subclass of LogicGate representing an XOR logic gate
 */
public class XorGate extends LogicGate{

    /**
     * Constructor for an XOR gate
     * @param xc - the x-coordinate of the gate
     * @param yc - the y-coordinate of the gate
     */
    public XorGate(double xc, double yc){
        super(xc,yc);
    }

    /**
     * Default constructor for an XOR gate
     */
    public XorGate(){
        this(1,1);
    }

    /**
     * Computes the output of the XOR gate using boolean logic for the inputs
     * @return - the output of the gate
     */
    public Integer getOutput(){
        if(getInput1()==getInput2()){
            return 0;
        }
        else{
            return 1;
        }

    }
}


