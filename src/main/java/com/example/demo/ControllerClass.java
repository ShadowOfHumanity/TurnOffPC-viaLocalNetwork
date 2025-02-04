package com.example.demo;

import java.util.regex.*;
import com.sun.jna.*;
import com.sun.jna.win32.*;
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
        } else if (Pattern.compile("^222\\d*").matcher(""+DIGITS).matches()) {
            String newDigits = String.valueOf(DIGITS).substring(3);
            isSuccess = ExecuteChangeSound(Integer.parseInt(newDigits));

        } else {
            isSuccess = executeShutdown(String.valueOf(DIGITS));
        }
        return isSuccess ;
    }

    private String ExecuteChangeSound(int soundPercent){




        // Ensure the reduced volume is not below 0
        if (soundPercent < 0) {
            soundPercent = 0;
        }

        // Set the new volume level
        VolumeControl.setVolume(soundPercent);

        System.out.println("Volume changed to: " + soundPercent);



        return "Success";
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
