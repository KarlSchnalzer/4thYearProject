package model;

public class NewChange {
    public static CircuitData CIRCUIT_DATA;
   // public static Queue<NewChange> que;
    private int eventID;
    private int element;
    private double x;
    private double y;
    private String type;
    private int input;
    private int output;
    private boolean state;

    public NewChange(){

    }
    public NewChange(int eventId, int elementId, boolean state){
        this.eventID = eventId;
        this.element = elementId;
        this.state = state;
    }

    public void setEventID(int e){
        this.eventID = e;
    }

    public void setElement(int id){
        this.element = id;
    }
    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }

    public void setInput(int input) {
        this.input = input;
    }

    public void setOutput(int output) {
        this.output = output;
    }
    public void setType(String t){
        this.type = t;
    }
    public void setState(boolean state) {
        this.state = state;
    }
    public int getEventID(){
        return eventID;
    }
    public int getElement(){
        return element;
    }
    public double getX(){return x;}
    public double getY(){return y;}
    public String getType(){return type;}
    public int getInput(){return input;}
    public int getOutput(){return output;}
    public boolean getState(){return state;}

    public void update() {
        switch(eventID){
            case 1:
                if(getType().equals("AND")){
                    AndGate ag = new AndGate(getX(),getY()); // create new AND gate with the fields provided in a JSON file
                    CIRCUIT_DATA.addGate(getElement(),ag);
                }
                else if(getType().equals("OR")){
                    OrGate og = new OrGate(getX(),getY()); // create new OR gate with the fields provided in JSON file
                    CIRCUIT_DATA.addGate(getElement(),og);
                 }
                else if(getType().equals("XOR")){
                    XorGate xg = new XorGate(getX(),getY()); // create new XOR gate with the fields provided in JSON file
                    CIRCUIT_DATA.addGate(getElement(),xg);
                }
                else if(getType().equals("NOT")){
                    NotGate ng = new NotGate(getX(),getY()); // create new NOT gate with the fields provided in JSON file
                    CIRCUIT_DATA.addGate(getElement(), ng);
                }
                else if(getType().equals("INP")){
                    Input i = new Input(getX(),getY()); // create new INPUT gate with the fields provided in JSON file
                    CIRCUIT_DATA.addGate(getElement(), i);
                }
                else if(getType().equals("OUT")){
                    Output o = new Output(getX(),getY()); // create new OUTPUT gate with the fields provided in JSON file
                    CIRCUIT_DATA.addGate(getElement(), o);
                }
                break;
            case 2:
                CIRCUIT_DATA.setX(getElement(),getX());
                CIRCUIT_DATA.setY(getElement(),getY());
                break;
            case 3:
                CIRCUIT_DATA.removeGate(getElement());
                break;
            case 4:
                CIRCUIT_DATA.setInput(getInput(), getOutput());
                break;
            case 5:
                if(CIRCUIT_DATA.getGate(getInput()).getConnectionOneId()==CIRCUIT_DATA.getGate(getOutput()).getOutputId()){
                    CIRCUIT_DATA.getGate(getOutput()).disconnectOutput();
                    CIRCUIT_DATA.getGate(getInput()).disconnectC1();
                }else if(CIRCUIT_DATA.getGate(getInput()).getConnectionTwoId()==CIRCUIT_DATA.getGate(getOutput()).getOutputId()){
                    CIRCUIT_DATA.getGate(getOutput()).disconnectOutput();
                    CIRCUIT_DATA.getGate(getInput()).disconnectC2();
                }
                break;
            case 6:
                CIRCUIT_DATA.turnOnOrOff(getElement());
                break;
        }

    }
//    public NewChange release(){
//       return CIRCUIT_DATA.getNewChange();
//    }
}
