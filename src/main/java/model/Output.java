package model;

public class Output extends LogicGate{
    /**
     * Constructor for a Input "gate"
     * @param xc - the x-coordinate of the gate
     * @param yc - the y-coordinate of the gate
     */
    public Output(double xc, double yc){
        super(xc,yc);
        this.setInput(0);
    }

    /**
     * Default constructor for a Input "gate"
     */
    public Output(){
        this(0,1.0);
        this.setInput(0);
    }

}
