package de.zi_family.planets2D;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Henrik Zimmermann
 * January 2019
 */

@Deprecated
class CalculatorBigDecimal {
    private static final BigDecimal gamma = new BigDecimal("6.672E-11");

    private static double minXVal, maxXVal, minYVal, maxYVal;

    static List<Coordinate> calc(double dist, double vx, double vy, double mass) {
        List<Coordinate> points = new ArrayList<>();

        BigDecimal r, ax, ay, xNew, yNew, bvx, bvy, bmass;
        double dXNew, dYNew;

        System.out.println("Vx: " + vx);
        System.out.println("Vy: " + vy);

        minYVal = 0;
        minXVal = 0;
        maxXVal = 0;
        maxYVal = 0;

        bvx = new BigDecimal(Double.toString(vx), MathContext.DECIMAL64);
        bvy = new BigDecimal(Double.toString(vy), MathContext.DECIMAL64);

        bmass = new BigDecimal(Double.toString(mass), MathContext.DECIMAL64);

        xNew = new BigDecimal(Double.toString(dist), MathContext.DECIMAL64);
        yNew = new BigDecimal(Double.toString(0), MathContext.DECIMAL64);

        BigDecimal startSpeed = bvx.pow(2, MathContext.DECIMAL64).add(bvy.pow(2, MathContext.DECIMAL64)).sqrt(MathContext.DECIMAL64);
        int numCalculations = new BigDecimal(2 * Math.PI * dist).divide(startSpeed, MathContext.DECIMAL64).intValue();

        System.out.println("Number of calculations: " + numCalculations);
        System.out.println("Start Speed: " + startSpeed);

        boolean exitFlag = false;

        while (true) {
            minXVal = Math.min(minXVal, xNew.doubleValue());
            minYVal = Math.min(minYVal, yNew.doubleValue());

            maxXVal = Math.max(maxXVal, xNew.doubleValue());
            maxYVal = Math.max(maxYVal, yNew.doubleValue());

            points.add(new Coordinate(xNew, yNew));

            r = xNew.pow(2).add(yNew.pow(2)).sqrt(MathContext.DECIMAL64);

            ax = gamma.negate().multiply(bmass.divide(r.pow(3, MathContext.DECIMAL64), MathContext.DECIMAL64)).multiply(xNew, MathContext.DECIMAL64);
            bvx = bvx.add(ax);

            ay = gamma.negate().multiply(bmass.divide(r.pow(3, MathContext.DECIMAL64), MathContext.DECIMAL64)).multiply(yNew, MathContext.DECIMAL64);
            bvy = bvy.add(ay);

            xNew = xNew.add(bvx);
            yNew = yNew.add(bvy);

            dXNew = xNew.doubleValue();
            dYNew = yNew.doubleValue();

            if ((dXNew < dist + 1000000 && dXNew > 0) && (dYNew < 1000 && dYNew > -1000)) {
                if (exitFlag) {
                    break;
                }
            } else if (!exitFlag) {
                System.out.println("exitFlag = " + exitFlag);
                exitFlag = true;
            }
        }

        System.out.println("FINITO" + points.size());

        return points;
    }

    static double divisorFor(int width) {
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

    static class Coordinate {
        private BigDecimal x, y;

        private Coordinate(BigDecimal x, BigDecimal y) {
            this.x = x;
            this.y = y;
        }

        double getX() {
            return x.doubleValue();
        }

        double getY() {
            return y.doubleValue();
        }
    }
}

