package de.zi_family.planets2D;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * @author Henrik Zimmermann
 * January 2019
 */

public class OutputPanel extends JPanel implements ActionListener {
    private JTextField resolutionField;

    OutputPanel() {
        super();

        GridBagConstraints constraints = new GridBagConstraints();

        //--------------------------Init of CSV Output Panel----------------------------------

        JPanel csvOutputPanel = new JPanel();
        csvOutputPanel.setLayout(new GridBagLayout());
        csvOutputPanel.setBorder(BorderFactory.createTitledBorder("CSV Datei"));

        resolutionField = new JTextField();
        resolutionField.setText("1");

        constraints.insets = new Insets(2, 2, 2, 2);

        constraints.gridx = 0;
        constraints.gridy = 0;

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

        csvOutputPanel.add(new JLabel("Auflösung"), constraints);

        constraints.gridy = 1;

        csvOutputPanel.add(resolutionField, constraints);

        constraints.gridheight = 2;
        constraints.gridx = 1;
        constraints.gridy = 0;

        JButton outputAsCsvButton = new JButton("Als CSV exportieren");
        outputAsCsvButton.addActionListener(this);

        csvOutputPanel.add(outputAsCsvButton, constraints);

        //---------------------------Adding components to Panel--------------------------------

        add(csvOutputPanel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JFileChooser fChooser = new JFileChooser();
        fChooser.setFileFilter(new FileNameExtensionFilter("CSV file", "csv"));
        int resCode = fChooser.showSaveDialog(getRootPane());

        if (resCode == JFileChooser.APPROVE_OPTION) {
            File f = fChooser.getSelectedFile();
            String str = f.getPath();
            if (!str.toLowerCase().endsWith(".csv"))
                f = new File(str + ".csv");

            try (PrintStream os = new PrintStream(f)) {
                os.println("Ausgabe des Planetenbahnrechners in Excel,,,");
                os.println(",,,");
                os.println("x,y, r, v");
                if (Main.getPoints() != null) {
                    for (int i = 0; i < Main.getPoints().size(); i += Integer.parseInt(resolutionField.getText())) {
                        Calculator.Coordinate c = Main.getPoints().get(i);
                        os.println(c.getX() + "," + c.getY() + "," + c.getR() + "," + c.getSpeed());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
