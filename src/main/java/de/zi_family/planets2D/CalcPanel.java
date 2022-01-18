package de.zi_family.planets2D;

import javax.swing.*;
import java.awt.*;

/**
 * @author Henrik Zimmermann
 * January 2019
 */

class CalcPanel extends JPanel {
    private JTextField resolutionField;

    CalcPanel() {
        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        resolutionField = new JTextField();

        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(new JLabel("Auflösung:"), constraints);

        constraints.gridy = 1;
        add(resolutionField, constraints);
    }

    int getResolution() {
        return Integer.parseInt(resolutionField.getText());
    }

    void setPanelTexts(int resolution) {
        resolutionField.setText(Integer.toString(resolution));
    }
}
