package de.zi_family.planets2D;

import javax.swing.*;
import java.awt.*;

/**
 * @author Henrik Zimmermann
 * January 2019
 */

class CentralBodyPanel extends JPanel {
    private JTextField rField, mField;

    CentralBodyPanel() {
        super();
        setLayout(new GridBagLayout());

        rField = new JTextField();
        mField = new JTextField();

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;

        constraints.insets = new Insets(2, 2, 2, 2);

        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

        add(new JLabel("m (in kg)"), constraints);

        constraints.gridx = 1;
        add(new JLabel("r (in m)"));

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(mField, constraints);

        constraints.gridx = 1;
        add(rField, constraints);
    }

    double getMass() {
        return Double.parseDouble(mField.getText());
    }

    double getRadius() {
        return Double.parseDouble(rField.getText());
    }

    void setPanelTexts(double mass, double radius) {
        mField.setText(Double.toString(mass));
        rField.setText(Double.toString(radius));
    }
}
