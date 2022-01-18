package de.zi_family.planets2D;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

/**
 * @author Henrik Zimmermann
 * January 2019
 */

public class PlanetGraphics extends JPanel {
    private boolean showMaxMin;
    private NumberFormat nf;
    private final Color blue, yellow;
    private double centralR;

    private final Calculator calculator;
    private float enlargementFactor = 1.0f;

    private int xDifference = 0, yDifference = 0;

    PlanetGraphics(Calculator calculator) {
        super();

        this.calculator = calculator;
        showMaxMin = false;

        nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(3);

        blue = new Color(4, 20, 206);
        yellow = new Color(201, 201, 4);
    }

    @Override
    protected void paintComponent(Graphics g) {
        //Graphics2D g2d = (Graphics2D) g

        super.paintComponent(g);
        g.setColor(Color.BLACK);

        System.out.println("Width:\t" + getWidth());
        System.out.println("Height:\t" + getHeight());

        double viewDivisor = calculator.divisorFor(Math.min(getWidth(), getHeight()) - 80) / enlargementFactor;
        int viewCenterX = (getWidth() / 2) + xDifference;
        int viewCenterY = ((getHeight() - 40) / 2) - yDifference;

        int gCentralR = (int) (centralR / viewDivisor);
        g.fillOval(viewCenterX - gCentralR, viewCenterY - gCentralR, (gCentralR * 2), (gCentralR * 2));

        System.out.println("Calculated view factor: " + viewDivisor);

        if (Main.getPoints() != null) {
            for (int i = 0; i < Main.getPoints().size(); i++) {
                Calculator.Coordinate c = Main.getPoints().get(i);
                g.drawRect((int) (viewCenterX + (c.getX() / viewDivisor)), (int) (viewCenterY - (c.getY() / viewDivisor)), 1, 1);
            }

            if (showMaxMin) {
                g.setColor(Color.RED);

                Calculator.Coordinate c = calculator.getMaxR();

                if (c != null) {
                    g.setColor(blue);
                    g.fillOval((int) (viewCenterX + (c.getX() / viewDivisor) - 5), (int) (viewCenterY - (c.getY() / viewDivisor) - 5), 10, 10);
                    System.out.println("Added max at:\t" + c.getX() / viewDivisor + "/" + c.getY() / viewDivisor);

                    g.setFont(getFont().deriveFont(20.0f));
                    g.drawString("rmax = " + nf.format(c.getR()) + " m", getWidth() - 350, getHeight() - 70);
                }

                c = calculator.getMinR();

                if (c != null) {
                    g.setColor(yellow);
                    g.fillOval((int) (viewCenterX + (c.getX() / viewDivisor) - 5), (int) (viewCenterY - (c.getY() / viewDivisor) - 5), 10, 10);
                    System.out.println("Added min at:\t" + c.getX() / viewDivisor + "/" + c.getY() / viewDivisor);

                    g.setFont(getFont().deriveFont(20.0f));
                    g.drawString("rmin = " + nf.format(c.getR()) + " m", getWidth() - 350, getHeight() - 50);
                }

                if (calculator.getMaxR() == calculator.getMinR()) {
                    System.out.println("ERROR");
                }
            }

            if (calculator.isColliding()) {
                g.setColor(Color.RED);
                g.setFont(getFont().deriveFont(20.0f));
                System.out.println("Planet is colliding");
                g.drawString("Himmelskörper kollidiert mit Zentralkörper", getWidth() / 2, getHeight() - 30);
            }

            if (calculator.isEscaping()) {
                g.setColor(Color.RED);
                g.setFont(getFont().deriveFont(20.0f));
                System.out.println("Planet is fleeing");
                g.drawString("Himmelskörper übersteigt Fluchtgeschwindigkeit", getWidth() / 2, getHeight() - 10);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(880, 880);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(280, 280);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(2880, 2880);
    }

    void setShowMaxMin(boolean showMaxMin) {
        System.out.println("Show min max enabled");
        this.showMaxMin = showMaxMin;
        repaint();
    }

    void updatePlanets(double centralR) {
        this.centralR = centralR;
        repaint();
    }

    void setEnlargementFactor(float factor) {
        this.enlargementFactor = factor;
        repaint();
    }

    void setXDifference(int diff) {
        this.xDifference = diff;
        repaint();
    }

    void setYDifference(int diff) {
        this.yDifference = diff;
        repaint();
    }
}
