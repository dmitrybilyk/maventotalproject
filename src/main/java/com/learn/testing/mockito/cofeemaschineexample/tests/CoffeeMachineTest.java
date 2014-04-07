package com.learn.testing.mockito.cofeemaschineexample.tests;

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


@RunWith(MockitoJUnitRunner.class)
public class CoffeeMachineTest {

    ICoffeeMachine coffeeMachine;

    @Mock
    IContainer coffeeContainer;
    @Mock
    IContainer waterContainer;

    @Before
    public void setUp() {
        coffeeMachine = new CoffeeMachine(coffeeContainer, waterContainer);
    }

    @After
    public void tearDown() {
        coffeeContainer = null;
        waterContainer = null;
        coffeeMachine = null;
    }

    @Test
    public void testMakeCoffe() throws NotEnoughException {

        when(coffeeContainer.getPortion(Portion.LARGE)).thenReturn(true);
        when(waterContainer.getPortion(Portion.LARGE)).thenReturn(true);

        assertTrue(coffeeMachine.makeCoffee(Portion.LARGE));
    }

    @Test
    public void testNotEnoughException() throws NotEnoughException {

        when(coffeeContainer.getPortion(Portion.SMALL)).thenReturn(false);
        when(waterContainer.getPortion(Portion.SMALL)).thenReturn(true);

        assertFalse(coffeeMachine.makeCoffee(Portion.SMALL));

    }

}