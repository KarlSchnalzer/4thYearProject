package model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Queue;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        NewChange.CIRCUIT_DATA = new CircuitData();
        SpringApplication.run(Application.class, args);
    }
}
