package model;

public class NewChange {
    public static CircuitData CIRCUIT_DATA;
    private Integer eventId;
    private Integer elementID;
    private double x;
    private double y;
    private String type;
    private Integer input;
    private Integer output;
    private String state;

    public void setEventId(Integer e){
        this.eventId = e;
    }

    public void setElementID(Integer id){
        this.elementID  = id;
    }
    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }

    public void setInput(Integer input) {
        this.input = input;
    }

    public void setOutput(Integer output) {
        this.output = output;
    }
    public void setType(String t){
        this.type = t;
    }
    public void setState(String state) {
        this.state = state;
    }
    public Integer getEventId(){
        return eventId;
    }
    public Integer getElementID(){
        return elementID;
    }
    public double getX(){return x;}
    public double getY(){return y;}
    public String getType(){return type;}
    public Integer getInput(){return input;}
    public Integer getOutput(){return output;}
    public String getState(){return state;}

    public void update() {
        switch(eventId){
            case 1:
                if(getType().equals("AND")){
                AndGate ag = new AndGate(getX(),getY()); // create new AND gate with the fields provided in a JSON file
                    CIRCUIT_DATA.addGate(getElementID(),ag);
                }
                else if(getType().equals("OR")){
                OrGate og = new OrGate(getX(),getY()); // create new OR gate with the fields provided in JSON file
                    CIRCUIT_DATA.addGate(getElementID(),og);
                 }
                else if(getType().equals("XOR")){
                XorGate xg = new XorGate(getX(),getY()); // create new XOR gate with the fields provided in JSON file
                    CIRCUIT_DATA.addGate(getElementID(),xg);
                }
                else if(getType().equals("NOT")){
                NotGate ng = new NotGate(getX(),getY()); // create new NOT gate with the fields provided in JSON file
                    CIRCUIT_DATA.addGate(getElementID(), ng);
                }
                else if(getType().equals("INP")){
                Input i = new Input(getX(),getY()); // create new INPUT gate with the fields provided in JSON file
                    CIRCUIT_DATA.addGate(getElementID(), i);
                }
                else if(getType().equals("OUT")){
                Output o = new Output(getX(),getY()); // create new OUTPUT gate with the fields provided in JSON file
                    CIRCUIT_DATA.addGate(getElementID(), o);
                }
                return;
            case 2:
                CIRCUIT_DATA.setX(getElementID(),getX());
                CIRCUIT_DATA.setY(getElementID(),getY());
                return;
            case 3:
                CIRCUIT_DATA.removeGate(getElementID());
                return;
            case 4:
                if(CIRCUIT_DATA.getGate(getOutput()).getConnectionOneId()== null){
                    CIRCUIT_DATA.setInput(getInput(), getOutput());
                    return;
                }
                else if(CIRCUIT_DATA.getGate(getOutput()).getConnectionTwoId()== null){
                    CIRCUIT_DATA.setInput2(getInput(), getOutput());
                    return;
                }
            case 5:
                if(CIRCUIT_DATA.getGate(getInput()).getConnectionOneId()==CIRCUIT_DATA.getGate(getOutput()).getOutputId()){
                    CIRCUIT_DATA.getGate(getOutput()).disconnectOutput();
                    CIRCUIT_DATA.getGate(getInput()).disconnectC1();
                }else if(CIRCUIT_DATA.getGate(getInput()).getConnectionTwoId()==CIRCUIT_DATA.getGate(getOutput()).getOutputId()){
                    CIRCUIT_DATA.getGate(getOutput()).disconnectOutput();
                    CIRCUIT_DATA.getGate(getInput()).disconnectC2();
                }
                return;
            case 6:
                CIRCUIT_DATA.turnOnOrOff(getElementID());
        }

    }
}
