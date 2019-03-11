package model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InterfaceController {
    private CircuitData data = new CircuitData();
    private long counter = 1;

    @RequestMapping("/getNewElementID")
    public NewElementJsonFile getNewElementID() {
        return new NewElementJsonFile(counter, true);
        counter = counter+1;
    }

    @RequestMapping("/newChange")
    public void newChange( ) {
        if(fl.getMsgType()==1){
            if(fl.getType().equals("AND")){
                AndGate ag = new AndGate(fl.getX(),fl.getY());
                data.addGate(counter,ag);
            }
            else if(fl.getType().equals("OR")){
                OrGate og = new OrGate(fl.getX(),fl.getY());
                data.addGate(counter,og);
            }
            else if(fl.getType().equals("XOR")){
                XorGate xg = new XorGate(fl.getX(),fl.getY());
                data.addGate(counter,xg);
            }
            else if(fl.getType().equals("NOT")){
                NotGate ng = new NotGate(fl.getX(),fl.getY());
                data.addGate(counter, ng);
            }
            else if(fl.getType().equals("INP")){
                Input i = new Input(fl.getX(),fl.getY());
                data.addGate(counter, i);
            }
            else if(fl.getType().equals("OUT")){
                Output o = new Output(fl.getX(),fl.getY());
                data.addGate(counter, o);
            }
        }
        else if(fl.getMsgType()==2){
            data.setX(fl.getId(),fl.getX());
            data.setY(fl.getId(),fl.getY());
        }
        else if(fl.getMsgType()==3){
            if(data.get(fl.getId()).getConnectionOneId()!=null){
                data.get(fl.getId().getConnectionOneId()).disconnectC1();
            }
            if(data.get(fl.getId()).getConnectionTwoId()!=null){
                data.get(fl.getId().getConnectionTwoId()).disconnectC2();
            }
            if(data.get(fl.getId()).getOutputId()!=null){
                data.get(fl.getId().getOutputId()).disconnectOutput();
            }
            data.removeGate(fl.getId());
        }
        else if(fl.getMsgType()==4){
//    		data.get(fl.getId()).setOutputId(fl.getID2());
//    		data.get(fl.getID2()).setConnectionId(fl.getId());
            data.get(fl.getID2()).setInput(data.get(fl.getId()).getOutput);
        }
        else if(fl.getMsgType()==5){
            if(data.get(fl.getId()).getConnectionOneId()==data.get(fl.getId2()).getOutputId()){
                data.get(fl.getId2()).disconnectOutput();
                data.get(fl.getId()).disconnectC1();
            }else if(data.get(fl.getId()).getConnectionTwoId()==data.get(fl.getId2()).getOutputId()){
                data.get(fl.getId2()).disconnectOutput();
                data.get(fl.getId()).disconnectC2();
            }
        }
        else if(fl.getMsgType()==6){

        }
    }

}


