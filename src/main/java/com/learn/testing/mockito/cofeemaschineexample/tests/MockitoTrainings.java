package com.learn.testing.mockito.cofeemaschineexample.tests;

/**
 * Created by dmitry.bilyk on 4/7/14.
 */

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.learn.testing.mockito.cofeemaschineexample.data.Portion;
import com.learn.testing.mockito.cofeemaschineexample.exceptions.NotEnoughException;
import com.learn.testing.mockito.cofeemaschineexample.mechanism.CoffeeMachine;
import com.learn.testing.mockito.cofeemaschineexample.mechanism.interfaces.ICoffeeMachine;
import com.learn.testing.mockito.cofeemaschineexample.mechanism.interfaces.IContainer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MockitoTrainings {

    @Test
    public void test(){

//        You can verify interactions:
//        List mockedList = com.cgi.pacoshop.mock(List.class);
//        mockedList.add("one");
//        mockedList.clear();
//        verify(mockedList).add("one");
//        verify(mockedList).clear();

//        Or stub method calls
        // you can com.cgi.pacoshop.mock concrete class, not only interfaces
        LinkedList mockedList = mock(LinkedList.class);
        // stubbing - before execution
                when(mockedList.get(0)).thenReturn("first");

//         following prints "first"
                System.out.println(mockedList.get(0));
        // following prints "null" because get(999) was not stubbed.
                System.out.println(mockedList.get(999));
    }

}
