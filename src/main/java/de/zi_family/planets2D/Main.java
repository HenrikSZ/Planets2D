package de.zi_family.planets2D;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;


/**
 * @author Henrik Zimmermann
 * January 2019
 */

public class Main extends JFrame {
    private CentralBodyPanel centralBodyPanel;
    private CalcPanel calcPanel;

    private static List<Calculator.Coordinate> points;
    private static final int EARTH_AND_SUN = 0, MOON_AND_EARTH = 1, SAT_AND_MOON = 2, SAT_AND_EARTH = 3;

    private final StartPointPanel startPointPanel;
    private final PlanetGraphics planetGraphics;

    private final Calculator calculator;

    private Main() {
        calculator = new Calculator();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setTitle("Planetenbahnenrechner");

        try {
            URL icon = getClass().getResource("/favimage.gif");
            if (icon != null)
                setIconImage(ImageIO.read(icon));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //------------------------------------Init of Tabs of lower bar navigation---------------------------------

        planetGraphics = new PlanetGraphics(calculator);
        DisplayPanel displayPanel = new DisplayPanel(planetGraphics);
        startPointPanel = new StartPointPanel(this);
        centralBodyPanel = new CentralBodyPanel();
        calcPanel = new CalcPanel();

        //-----------------------------------Adding Tabs to TabbedPane Component-------------------------------------

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Startparameter", null, startPointPanel);
        tabbedPane.addTab("Zentralkörper", null, centralBodyPanel);
        tabbedPane.addTab("Weitere Rechenoptionen", null, calcPanel);
        tabbedPane.addTab("Anzeige", null, displayPanel);
        tabbedPane.addTab("Ausgabe", null, new OutputPanel());

        //----------------------------------Adding all Components of front end to JFrame------------------------------

        add(planetGraphics, BorderLayout.CENTER);
        add(tabbedPane, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }


    //--------------------------------------------Called to recalculate a circular path----------------------------------------

    void updatePlanetGraphics() {
        points = calculator.calc(startPointPanel.getPositionX(), startPointPanel.getPositionY(), startPointPanel.getXSpd(),
                startPointPanel.getYSpd(), centralBodyPanel.getRadius(), centralBodyPanel.getMass(), calcPanel.getResolution());
        planetGraphics.updatePlanets(centralBodyPanel.getRadius());
    }


    //--------------------------------------------Called to load a preset of values into the TextFields-------------------------

    void setPreset(int preset) {
        switch (preset) {
            case EARTH_AND_SUN:
                startPointPanel.setPanelTexts(0, 30000, 1.5 * Math.pow(10, 11), 0);
                centralBodyPanel.setPanelTexts(1.989 * Math.pow(10, 30), 6.959 * Math.pow(10, 8));
                calcPanel.setPanelTexts(1000);
                break;
            case MOON_AND_EARTH:
                startPointPanel.setPanelTexts(0, 1022, 3.84 * Math.pow(10, 8), 0);
                centralBodyPanel.setPanelTexts(5.972 * Math.pow(10, 24), 6.371 * Math.pow(10, 6));
                calcPanel.setPanelTexts(500);
                break;
            case SAT_AND_MOON:
                startPointPanel.setPanelTexts(0, 200, 5.5 * Math.pow(10, 7), 0);
                centralBodyPanel.setPanelTexts(7.348 * Math.pow(10, 22), 1.737 * Math.pow(10, 6));
                calcPanel.setPanelTexts(1);
                break;
            case SAT_AND_EARTH:
                startPointPanel.setPanelTexts(0, 7667, 6.779 * Math.pow(10, 6), 0);
                centralBodyPanel.setPanelTexts(5.972 * Math.pow(10, 24), 6.371 * Math.pow(10, 6));
                calcPanel.setPanelTexts(1);
                break;
        }
    }

    static List<Calculator.Coordinate> getPoints() {
        return points;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        new Main();
    }
}
