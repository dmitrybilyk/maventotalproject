package com.learn.spring.springinjection.autowire.mkyongtutorial.common;

import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 30.08.13
 * Time: 9:52
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SingleApprovalService {
    public void approve(){
        System.out.println("approving");
    }
}
