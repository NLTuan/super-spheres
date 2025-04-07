package edu.vanier.template.sim;

import edu.vanier.template.helpers.BuildInBodies;
import edu.vanier.template.helpers.RotationClass;
import edu.vanier.template.math.Vector3D;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class SolarSystemAssets {

    private List<Planet> solarSystemBodies = new ArrayList<>();


    public void loadAssets(Group rootNode, BodyHandler bodyHandler) {

        Planet sun = new Planet(
                new Vector3D(0, 0, 0),
                new Vector3D(0, 0, 0),
                1000,
                600
        );
        BuildInBodies.applyTextures(sun, "Sun");

        // Mercury (closest to sun)
        Planet mercury = new Planet(
                new Vector3D(-800, 0, 0),
                new Vector3D(0, 0, -15),
                10,    // Mass
                20     // Size
        );
        BuildInBodies.applyTextures(mercury, "Mercury");

        // Venus
        Planet venus = new Planet(
                new Vector3D(-1200, 0, 0),
                new Vector3D(0, 0, -12),
                20,    // Mass
                40     // Size
        );
        BuildInBodies.applyTextures(venus, "Venus");

        // Earth
        Planet earth = new Planet(
                new Vector3D(-1600, 0, 0),
                new Vector3D(0, 0, -10),
                20,    // Mass
                42     // Size
        );
        BuildInBodies.applyTextures(earth, "Earth");

        // Mars
        Planet mars = new Planet(
                new Vector3D(-2200, 0, 0),
                new Vector3D(0, 0, -8),
                10,    // Mass
                30     // Size
        );
        BuildInBodies.applyTextures(mars, "Mars");

        // Jupiter
        Planet jupiter = new Planet(
                new Vector3D(-3200, 0, 0),
                new Vector3D(0, 0, -6),
                200,   // Mass
                180    // Size
        );
        BuildInBodies.applyTextures(jupiter, "Jupiter");

        // Saturn
        Planet saturn = new Planet(
                new Vector3D(-4200, 0, 0),
                new Vector3D(0, 0, -5),
                150,   // Mass
                150    // Size
        );
        // No texture applied - will use default

        // Uranus
        Planet uranus = new Planet(
                new Vector3D(-5200, 0, 0),
                new Vector3D(0, 0, -4),
                100,   // Mass
                80     // Size
        );
        // No texture applied - will use default

        // Neptune
        Planet neptune = new Planet(
                new Vector3D(-6200, 0, 0),
                new Vector3D(0, 0, -3.5),
                100,   // Mass
                75     // Size
        );
        // No texture applied - will use default

        // Pluto
        Planet pluto = new Planet(
                new Vector3D(-7200, 0, 0),
                new Vector3D(0, 0, -3),
                1,     // Mass
                10     // Size
        );


        // Add all bodies to lists
        solarSystemBodies.addAll(List.of(
                sun, mercury, venus, earth, mars,
                jupiter, saturn, uranus, neptune, pluto
        ));

        // Add to scene and physics
        rootNode.getChildren().addAll(solarSystemBodies);
        bodyHandler.addAll(sun, mercury, venus, earth, mars,
                jupiter, saturn, uranus, neptune, pluto);

        RotationClass rotationClass = new RotationClass();
        rotationClass.addBody(sun, 50);
        rotationClass.addBody(mercury, 120);
        rotationClass.addBody(venus, 90);
        rotationClass.addBody(earth, 80);
        rotationClass.addBody(mars, 70);
       rotationClass.addBody(jupiter, 50);
       rotationClass.addBody(saturn, 40);
        rotationClass.addBody(uranus, 30);
        rotationClass.addBody(neptune, 30);
        rotationClass.addBody(pluto, 20);


    }
}
