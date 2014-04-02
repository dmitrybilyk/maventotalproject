package com.learn.testing.easymock;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 21.03.13
 * Time: 21:17
 * To change this template use File | Settings | File Templates.
 */
public class IncomeCalculator {
    private ICalcMethod calcMethod;
    private Position position;

    public void setCalcMethod(ICalcMethod calcMethod) {
        this.calcMethod = calcMethod;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public double calc() {
        if (calcMethod == null) {
            throw new RuntimeException("CalcMethod not yet maintained");
        }
        if (position == null) {
            throw new RuntimeException("Position not yet maintained");
        }
        return calcMethod.calc(position);
    }
}
