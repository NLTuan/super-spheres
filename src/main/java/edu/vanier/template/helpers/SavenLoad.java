package edu.vanier.template.helpers;
import edu.vanier.template.sim.Body;
import edu.vanier.template.sim.Planet;
import edu.vanier.template.sim.BodyHandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;



public class SavenLoad {

    public static void writePlanetsToFile(ArrayList<Body> planets, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Body planet : planets) {
                writer.write(planet.toString());
                writer.newLine(); // Add a newline after each planet
            }
        }
    }
}

