package com.learn.spring.springtest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by IntelliJ IDEA.
 * User: Flash
 * Date: 26.06.12
 * Time: 23:32
 * To change this template use File | Settings | File Templates.
 */
public class Triangle implements Shape, ApplicationContextAware, BeanNameAware{
      private Point pointA;
      private Point pointB;
      private Point pointC;
    private ApplicationContext applicationContext;
    private String beanName;

    public Point getPointA() {
        return pointA;
    }

    public void setPointA(Point pointA) {
        this.pointA = pointA;
    }

    public Point getPointB() {
        return pointB;
    }

    public void setPointB(Point pointB) {
        this.pointB = pointB;
    }

    public Point getPointC() {
        return pointC;
    }

    public void setPointC(Point pointC) {
        this.pointC = pointC;
    }

//    private List<Point> points;
//
//    public List<Point> getPoints() {
//        return points;
//    }
//
//    public void setPoints(List<Point> points) {
//        this.points = points;
//    }

//    public void draw() {
//        for (Point point : points) {
//            System.out.println(point.getX()+" "+point.getY());
//        }
//    }

    public void draw() {
        System.out.println(pointA.getX()+" "+pointB.getX()+" "+pointC.getX());
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void setBeanName(String name) {
        System.out.println("fsdfasd"+name);
    }
}
