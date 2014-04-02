package com.learn.testing.jmock;

import com.learn.testing.easymock.SkuNotFoundException;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

@RunWith(JMock.class)
public class PricingServiceTest {

    private static final String SKU = "3283947";
    private static final String BAD_SKU = "-9999993434";

    private PricingService systemUnderTest;
    private DataAccess mockedDependency;
    private Mockery mockingContext;

    @Before
    public void doBeforeEachTestCase() {
        mockingContext = new JUnit4Mockery();
        systemUnderTest = new PricingServiceImpl();
        mockedDependency = mockingContext.mock(DataAccess.class);
        systemUnderTest.setDataAccess(mockedDependency);
    }

    @Test
    public void getPrice() throws SkuNotFoundException {
        mockingContext.checking(new Expectations() {
            {
                one(mockedDependency).getPriceBySku(SKU);
                will(returnValue(new BigDecimal(100)));
            }
        });
        final BigDecimal testPrice = systemUnderTest.getPrice(SKU);
    }

    @Test(expected = SkuNotFoundException.class)
    public void getPriceNonExistentSkuThrowsException() throws SkuNotFoundException {
        mockingContext.checking(new Expectations() {
            {
                one(mockedDependency).getPriceBySku(BAD_SKU);
                will(returnValue(null));
            }
        });
        final BigDecimal price = systemUnderTest.getPrice(BAD_SKU);
    }

    @Test(expected = RuntimeException.class)
    public void getPriceDataAccessThrowsRuntimeException() throws SkuNotFoundException {
        mockingContext.checking(new Expectations() {
            {
                allowing(mockedDependency).getPriceBySku(with(any(String.class)));
                will(throwException(new RuntimeException("Fatal data access exception.")));
            }
        });
        final BigDecimal price = systemUnderTest.getPrice(SKU);
    }

}