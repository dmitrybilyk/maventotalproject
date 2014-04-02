package com.learn.spring.aop.service;

import com.learn.spring.aop.model.Circle;
import com.learn.spring.aop.model.Triangle;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 04.07.12
 * Time: 16:44
 * To change this template use File | Settings | File Templates.
 */

public class ShapeService {
    private Circle circle;
    private Triangle triangle;

    public Circle getCircle() {
        return circle;
    }

    @Autowired
    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public Triangle getTriangle() {
        return triangle;
    }

    @Autowired
    public void setTriangle(Triangle triangle) {
        this.triangle = triangle;
    }
}
