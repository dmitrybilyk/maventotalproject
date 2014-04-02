package com.learn.testing.easymock;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: buh
 * Date: 30.09.12
 * Time: 13:33
 * To change this template use File | Settings | File Templates.
 */
public class PricingServiceTests {

     private static final String SKU = "3283947";
     private static final String BAD_SKU = "-9999993434";

     private PricingService systemUnderTest;
     private DataAccess mockedDependency;

     @Before
     public void doBeforeEachTestCase() {
         systemUnderTest = new PricingServiceImpl();
         mockedDependency = createMock(DataAccess.class);
         systemUnderTest.setDataAccess(mockedDependency);
     }

     @Test
     public void getPrice() throws SkuNotFoundException {


         // Set expectations on mocks.
         expect(mockedDependency.getPriceBySku(SKU)).andReturn(new BigDecimal(100));

         // Set mocks into testing mode.
         replay(mockedDependency);

         final BigDecimal price = systemUnderTest.getPrice(SKU);
         assertNotNull(price);

         // Verify behavior.
         verify(mockedDependency);
     }

     @Test(expected = SkuNotFoundException.class)
     public void getPriceNonExistentSkuThrowsException() throws SkuNotFoundException {


         // Set expectations on mocks.
         expect(mockedDependency.getPriceBySku(BAD_SKU)).andReturn(null);

         // Set mocks into testing mode.
         replay(mockedDependency);

         final BigDecimal price = systemUnderTest.getPrice(BAD_SKU);
     }

     @Test(expected = RuntimeException.class)
     public void getPriceDataAccessThrowsRuntimeException() throws SkuNotFoundException {


         // Set expectations on mocks.
         expect(mockedDependency.getPriceBySku(SKU)).andThrow(new RuntimeException("Fatal data access exception."));

         // Set mocks into testing mode.
         replay(mockedDependency);

         final BigDecimal price = systemUnderTest.getPrice(SKU);
     }
 }
