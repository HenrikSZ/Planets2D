package de.zi_family.planets2D;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ItemEvent;

/**
 * @author Henrik Zimmermann
 * January 2019
 */

class DisplayPanel extends JPanel {
    DisplayPanel(PlanetGraphics planetGraphics) {
        super();

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JCheckBox showMaxMin = new JCheckBox("Zeige Minimal- Maximalwerte");
        showMaxMin.addItemListener((ItemEvent e) -> planetGraphics.setShowMaxMin(e.getStateChange() == ItemEvent.SELECTED));

        //------------------------------------------Init of JDliders----------------------------------------------------------------

        JSlider enlargement = new JSlider();
        JSlider xDiff = new JSlider();
        JSlider yDiff = new JSlider();

        enlargement.setMinimum(1);
        enlargement.setMaximum(15);
        enlargement.setMinorTickSpacing(1);
        enlargement.setMajorTickSpacing(5);
        enlargement.setPaintTicks(true);
        enlargement.setPaintLabels(true);
        enlargement.setPaintTrack(true);
        enlargement.setValue(1);

        xDiff.setMinimum(-200);
        xDiff.setMaximum(200);
        xDiff.setMinorTickSpacing(10);
        xDiff.setMajorTickSpacing(40);
        xDiff.setPaintTicks(true);
        xDiff.setPaintTrack(true);
        xDiff.setPaintLabels(true);
        xDiff.setValue(0);

        yDiff.setMinimum(-200);
        yDiff.setMaximum(200);
        yDiff.setMinorTickSpacing(10);
        yDiff.setMajorTickSpacing(40);
        yDiff.setPaintTicks(true);
        yDiff.setPaintTrack(true);
        yDiff.setPaintLabels(true);
        yDiff.setValue(0);

        enlargement.addChangeListener((ChangeEvent e) ->
                planetGraphics.setEnlargementFactor(enlargement.getValue()));

        xDiff.addChangeListener((ChangeEvent e) ->
                planetGraphics.setXDifference(xDiff.getValue()));

        yDiff.addChangeListener((ChangeEvent e) ->
                planetGraphics.setYDifference(yDiff.getValue()));


        //-------------------------------------------------Init of translation Pane------------------------------------------------

        JPanel translationPanel = new JPanel();

        translationPanel.setLayout(new GridBagLayout());

        translationPanel.setBorder(BorderFactory.createTitledBorder("Verschiebungen"));

        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.insets = new Insets(2, 2, 2, 2);

        constraints.gridx = 0;
        constraints.gridy = 0;
        translationPanel.add(new JLabel("x-Achse)"), constraints);

        constraints.gridy = 1;
        constraints.insets = new Insets(2, 2, 20, 2);
        translationPanel.add(xDiff, constraints);

        constraints.insets = new Insets(2, 2, 2, 2);
        constraints.gridy = 2;
        translationPanel.add(new JLabel("y-Achse"), constraints);

        constraints.gridy = 3;
        translationPanel.add(yDiff, constraints);

        //-------------------------------------------------Adding Componentss to JFrame--------------------------------------------

        constraints.fill = GridBagConstraints.BOTH;

        constraints.weightx = 0.5;
        constraints.weighty = 0.5;

        constraints.insets = new Insets(2, 2, 2, 2);

        constraints.gridx = 0;
        constraints.gridy = 0;

        add(new JLabel("Vergrößerung:"), constraints);

        constraints.gridy = 1;
        add(enlargement, constraints);

        constraints.gridy = 2;
        add(showMaxMin, constraints);

        constraints.gridheight = 3;
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(translationPanel, constraints);
    }
}
