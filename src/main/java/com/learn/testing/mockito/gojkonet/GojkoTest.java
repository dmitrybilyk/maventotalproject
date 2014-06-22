package com.learn.testing.mockito.gojkonet;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Test;
/**
 * Created by dmitry.bilyk on 4/8/14.
 */
public class GojkoTest {

    @Test
    public void iterator_will_return_hello_world(){
        //arrange
        Iterator i=mock(Iterator.class);
        when(i.next()).thenReturn("Hello").thenReturn("World");
        //act
        String result=i.next()+" "+i.next();
        //assert
        assertEquals("Hello World", result);
    }

    @Test
    public void with_arguments(){
        Comparable c=mock(Comparable.class);
        when(c.compareTo("Test")).thenReturn(1);
        assertEquals(1,c.compareTo("Test"));
    }

    @Test
    public void with_unspecified_arguments(){
        Comparable c=mock(Comparable.class);
//        when(c.compareTo(anyInt())).thenReturn(-1);
//        alternative way
        doReturn(-1).when(c).compareTo(anyInt());
        assertEquals(-1,c.compareTo(5));
    }

    //        Instead of returning, you can also use .thenThrow() or doThrow() for void methods
    @Test(expected=IOException.class)
    public void OutputStreamWriter_rethrows_an_exception_from_OutputStream()
            throws IOException{
        OutputStream mock=mock(OutputStream.class);
        OutputStreamWriter osw=new OutputStreamWriter(mock);
        doThrow(new IOException()).when(mock).close();
        osw.close();
    }

//    To verify actual calls to underlying objects (typical com.cgi.pacoshop.mock object usage),
// we can use verify(mock_object).method_call; For example:

    @Test
    public void OutputStreamWriter_Closes_OutputStream_on_Close()
            throws IOException{
        OutputStream mock=mock(OutputStream.class);
        OutputStreamWriter osw=new OutputStreamWriter(mock);
        osw.close();
        verify(mock).close();
    }


    @Test
    public void OutputStreamWriter_Buffers_And_Forwards_To_OutputStream()
            throws IOException{
        OutputStream mock=mock(OutputStream.class);
        OutputStreamWriter osw=new OutputStreamWriter(mock);
        osw.write('a');
        osw.flush();
        // can't do this as we don't know how long the array is going to be
        // verify(com.cgi.pacoshop.mock).write(new byte[]{'a'},0,1);

        BaseMatcher arrayStartingWithA=new BaseMatcher(){
            @Override
            public void describeTo(Description description) {
                // nothing
            }
            // check that first character is A
            @Override
            public boolean matches(Object item) {
                byte[] actual=(byte[]) item;
                return actual[0]=='a';
            }
        };
        // check that first character of the array is A, and that the other two arguments are 0 and 1
        verify(mock).write((byte[]) argThat(arrayStartingWithA), eq(0),eq(1));
    }

}
