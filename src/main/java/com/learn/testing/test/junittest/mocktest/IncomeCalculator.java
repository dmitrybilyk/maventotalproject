package com.learn.testing.test.junittest.mocktest;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 26.07.12
 * Time: 18:18
 * To change this template use File | Settings | File Templates.
 */
public class IncomeCalculator{

    private ICalcMethod calcMethod;
    private Position position;

    public void setCalcMethod(ICalcMethod calcMethod){
        this.calcMethod = calcMethod;
    }
    public void setPosition(Position position){
        this.position = position;
    }
    public double calc (){
        if (calcMethod==null){
            throw new CalcMethodException("CalcMethod not yet maintained");
        }
        if (position==null){
            throw new PositionException("Position not yet maintained");
        }
        return calcMethod.calc(position);
    }
}
