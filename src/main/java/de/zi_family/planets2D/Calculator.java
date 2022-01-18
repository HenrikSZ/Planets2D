package de.zi_family.planets2D;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Henrik Zimmermann
 * January 2019
 */

class Calculator {
    private static final double GAMMA = (6.672 * Math.pow(10, -11));

    private double minXVal, maxXVal, minYVal, maxYVal;
    private Coordinate maxR, minR;

    private boolean colliding = false, escaping = false;

    List<Coordinate> calc(double x, double y, double vx, double vy, double centralR, double centralM, int resolution) {
        int numberOfCalculations = 0;

        List<Coordinate> points = new ArrayList<>();

        colliding = false;

        maxR = new Coordinate(0, 0, 0, 0);
        minR = new Coordinate(0, 0, Double.MAX_VALUE, 0);

        double r, ax, ay, xNew, yNew, rExp3;

        System.out.println("Vx:\t" + vx);
        System.out.println("Vy:\t" + vy);

        xNew = x + 1;
        yNew = y;

        minYVal = 0;
        minXVal = 0;
        maxXVal = 0;
        maxYVal = 0;


        double startSpeed = Math.sqrt((vx * vx) + (vy * vy));

        System.out.println("Start Speed:\t" + startSpeed);

        boolean exitFlag = false, flightSpeedReached = false;

        int calcStop = 0;
        int stopOfFlightSpeed = Integer.MAX_VALUE;
        double exitPrecision = x / 100000;
        escaping = false;

        long time = System.currentTimeMillis();
        while (true) {
            calcStop++;
            minXVal = Math.min(minXVal, xNew);
            minYVal = Math.min(minYVal, yNew);

            maxXVal = Math.max(maxXVal, xNew);
            maxYVal = Math.max(maxYVal, yNew);

            r = Math.sqrt((xNew * xNew) + (yNew * yNew));

            double speed = Math.sqrt((vx * vx) + (vy * vy));
            double recessionSpeed = Math.sqrt((2 * GAMMA * centralM) / r);

            Coordinate coordinate = new Coordinate(xNew, yNew, r, speed);
            points.add(coordinate);

            if (coordinate.getR() > maxR.getR()) {
                maxR = coordinate;
            }

            if (coordinate.getR() < minR.getR()) {
                minR = coordinate;
            }

            rExp3 = -GAMMA * (centralM / Math.pow(r, 3));

            ax = rExp3 * xNew;
            vx = vx + (ax * resolution);

            ay = rExp3 * yNew;
            vy = vy + (ay * resolution);

            xNew = xNew + (vx * resolution);
            yNew = yNew + (vy * resolution);

            if (!flightSpeedReached && speed > recessionSpeed) {
                stopOfFlightSpeed = Math.max(3 * numberOfCalculations, 200000 / resolution);
                flightSpeedReached = true;
                escaping = true;
            }

            if ((xNew < (x + exitPrecision) && xNew > (x - exitPrecision)) && (yNew < (y + exitPrecision) && yNew > (y - exitPrecision))) {
                if (exitFlag) {
                    break;
                }
            } else if (!exitFlag) {
                exitFlag = true;
            }

            if (calcStop > 5000000) {
                System.out.println("Stopped! Too many calculations needed!");
                break;
            }

            if (numberOfCalculations >= stopOfFlightSpeed) {
                break;
            }

            numberOfCalculations++;
        }

        System.out.println("Calculating time: " + (System.currentTimeMillis() - time));
        System.out.println(numberOfCalculations);

        colliding = minR.getR() < centralR;

        System.out.println("Colldiing: " + colliding);
        System.out.println("Minimal Radius: " + minR.getR());
        System.out.println("At Coordinates: (" + minR.getX() + "|" + minR.getY() + ")");
        System.out.println("Central Body Radius: " + centralR);

        System.out.println("Points calculated:\t" + points.size());

        return points;
    }

    double divisorFor(int width) {
        width = width / 2;

        if (minXVal < 0) {
            minXVal = -minXVal;
        }

        if (minYVal < 0) {
            minYVal = -minYVal;
        }

        double[] divisors = new double[4];
        divisors[0] = maxXVal / width;
        divisors[1] = maxYVal / width;
        divisors[2] = minXVal / width;
        divisors[3] = minYVal / width;

        double returnDivisor = 0;

        for (double d : divisors) {
            returnDivisor = Math.max(returnDivisor, d);
        }

        return returnDivisor;
    }

    Coordinate getMaxR() {
        return maxR;
    }

    Coordinate getMinR() {
        return minR;
    }

    boolean isColliding() {
        return colliding;
    }

    boolean isEscaping() {
        return escaping;
    }

    static class Coordinate {
        private double x, y, r, speed;

        private Coordinate(double x, double y, double r, double speed) {
            this.x = x;
            this.y = y;
            this.r = r;
            this.speed = speed;
        }

        double getX() {
            return x;
        }

        double getY() {
            return y;
        }

        double getR() {
            return r;
        }

        double getSpeed() {
            return speed;
        }
    }
}
