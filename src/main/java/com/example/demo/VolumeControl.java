package com.example.demo;

import java.io.IOException;

public class VolumeControl {

    public static void setVolume(int percentage) {
        try {
            // Ensure the percentage is between 0 and 100
            if (percentage < 0) percentage = 0;
            if (percentage > 100) percentage = 100;

            // Convert the percentage to the appropriate volume value (0-65535)
            int volume = (percentage * 65535 / 100);

            // Build the command to set the volume using NirCmd
            String command = "\"C:\\Users\\daria.THE_FLASH\\Downloads\\nircmd\\nircmd.exe\" setsysvolume " + volume;

            // Execute the command
            Process process = new ProcessBuilder("cmd.exe", "/c", command).start();

            // Wait for the command to finish
            process.waitFor();

            System.out.println("Volume set to " + percentage + "%");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}