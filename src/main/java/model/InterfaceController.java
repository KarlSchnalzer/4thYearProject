package model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InterfaceController {
    //private CircuitData data = new CircuitData();
    private int counter = 1;

    @RequestMapping("/getNewElementID")
    public NewElementJsonFile getNewElementID() {
        NewElementJsonFile retVal = new NewElementJsonFile(counter, true);
        counter++;
        return retVal;

    }

    @RequestMapping("/newChange")
    public void newChange(@RequestBody NewChange nc ) {
        nc.update();
    }

    @RequestMapping("/random")
    public NewChange random(){
        NewChange nc = new NewChange(1,1,NewChange.CIRCUIT_DATA.getGate(1).getState());
        return nc;
    }

    @RequestMapping("/getNewChanges")
    public NewChange getNewChanges(){
        NewChange nc = NewChange.CIRCUIT_DATA.getNewChange();
        return nc;
    }

}


