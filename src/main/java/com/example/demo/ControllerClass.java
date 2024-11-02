package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ControllerClass {

    @GetMapping("/api/data")
    public String getData() {
        return "{\"data\": \"Hello from Java back-end!\"}";
    }

    // New endpoint to handle button click
    @PostMapping("/api/event")
    public String handleEvent(@RequestBody int DIGITS) {
        String isSuccess;
        if (DIGITS == 111){
            isSuccess = removeShutdown();
        } else {
            isSuccess = executeShutdown(String.valueOf(DIGITS));
        }
        return isSuccess ;
    }

    private String executeShutdown(String timeInSeconds) {
        try {
            // Construct the shutdown command
            String command = "shutdown -s -t " + timeInSeconds;
            // Execute the command
            Runtime.getRuntime().exec(command);
            System.out.println("Executed command: " + command);
            return "Success";
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    private String removeShutdown() {
        try {
            // Construct the shutdown command
            String command = "shutdown -a";
            // Execute the command
            Runtime.getRuntime().exec(command);
            System.out.println("Executed command: " + command);
            return "Success";
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}
