package de.zi_family.planets2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Henrik Zimmermann
 * January 2019
 */

class StartPointPanel extends JPanel {
    private JTextField vxPane, vyPane, xPane, yPane;
    private JComboBox<String> presets;

    StartPointPanel(Main main) {
        super();

        //----------------------------------Init of single Components and whole Panel----------------------------
        vxPane = new JTextField();
        vyPane = new JTextField();

        xPane = new JTextField();
        yPane = new JTextField();

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(2, 3, 2, 3);

        //---------------------------------Init of speedsPane-----------------------------------------------------

        JPanel speedsPane = new JPanel();
        speedsPane.setBorder(BorderFactory.createTitledBorder("Geschwindigkeit"));
        speedsPane.setLayout(new GridBagLayout());

        constraints.gridx = 0;
        constraints.gridy = 0;

        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

        constraints.fill = GridBagConstraints.BOTH;

        speedsPane.add(new JLabel("vx (in m/s)"), constraints);

        constraints.gridx = 1;
        speedsPane.add(new JLabel("vy (in m/s)"), constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        speedsPane.add(vxPane, constraints);

        constraints.gridx = 1;
        speedsPane.add(vyPane, constraints);

        //--------------------------------Init of positionPane-------------------------------------------------------

        JPanel postionPane = new JPanel();
        postionPane.setBorder(BorderFactory.createTitledBorder("Position"));
        postionPane.setLayout(new GridBagLayout());

        constraints.gridx = 0;
        constraints.gridy = 0;

        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

        constraints.fill = GridBagConstraints.BOTH;

        postionPane.add(new JLabel("x (in m)"), constraints);

        constraints.gridx = 1;
        postionPane.add(new JLabel("y (in m)"), constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;

        postionPane.add(xPane, constraints);

        constraints.gridx = 1;
        postionPane.add(yPane, constraints);

        //--------------------------------Init of presets------------------------------------------------------------

        presets = new JComboBox<>();
        presets.addItem("Erde um Sonne");
        presets.addItem("Mond um Erde");
        presets.addItem("Satellit um Mond");
        presets.addItem("ISS um Erde");
        presets.addActionListener((ActionEvent e) -> main.setPreset(presets.getSelectedIndex()));

        //--------------------------------Init of refreshButton------------------------------------------------------

        JButton refreshButton = new JButton("Neu berechnen");
        refreshButton.addActionListener((ActionEvent e) -> main.updatePlanetGraphics());

        //---------------------------------Adding Components to pane------------------------------------------------

        constraints.fill = GridBagConstraints.BOTH;

        constraints.gridwidth = 2;
        constraints.gridheight = 2;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(speedsPane, constraints);

        constraints.gridx = 2;
        add(postionPane, constraints);

        constraints.gridheight = 1;

        constraints.gridx = 4;
        constraints.gridwidth = 1;
        add(new JLabel("Voreinstellungen:"), constraints);

        constraints.gridx = 5;
        add(presets, constraints);

        constraints.gridwidth = 2;
        constraints.gridx = 4;
        constraints.gridy = 1;
        add(refreshButton, constraints);
    }

    int getXSpd() {
        return Integer.parseInt(vxPane.getText());
    }

    int getYSpd() {
        return Integer.parseInt(vyPane.getText());
    }

    double getPositionX() {
        return Double.parseDouble(xPane.getText());
    }

    double getPositionY() {
        return Double.parseDouble(yPane.getText());
    }

    void setPanelTexts(int vx, int vy, double x, double y) {
        vxPane.setText(Integer.toString(vx));
        vyPane.setText(Integer.toString(vy));

        xPane.setText(Double.toString(x));
        yPane.setText(Double.toString(y));
    }
}
