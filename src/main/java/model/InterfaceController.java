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
    private long counter = 1;

    @RequestMapping("/getNewElementID")
    public NewElementJsonFile getNewElementID() {
        NewElementJsonFile retVal = new NewElementJsonFile(counter, true);
        counter = counter+1;
        return retVal;

    }

    @RequestMapping("/newChange")
    public void newChange(@RequestBody NewChange nc ) {
        nc.update();
    }

    @RequestMapping("/getNewChanges")
    public NewChange getNewChanges(){
        NewChange nc = NewChange.CIRCUIT_DATA.getNewChange();
        return nc;
    }

}


