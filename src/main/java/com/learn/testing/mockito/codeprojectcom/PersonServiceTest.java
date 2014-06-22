package com.learn.testing.mockito.codeprojectcom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
/**
 * @author Phat (Phillip) H. VU <vuhongphat@hotmail.com>
 * 
 */
public class PersonServiceTest
{
    @Mock
    private PersonDao personDAO;
    private PersonService personService;
    @Before
    public void setUp()
        throws Exception
    {
        MockitoAnnotations.initMocks( this );
        personService = new PersonService( personDAO );
    }
    @Test
    public void shouldUpdatePersonName()
    {
        Person person = new Person( 1, "Phillip" );
        when( personDAO.fetchPerson( 1 ) ).thenReturn( person );
        boolean updated = personService.update( 1, "David" );
        assertTrue( updated );
        verify( personDAO ).fetchPerson( 1 );
        ArgumentCaptor<Person> personCaptor = ArgumentCaptor.forClass( Person.class );
        verify( personDAO ).update( personCaptor.capture() );
        Person updatedPerson = personCaptor.getValue();
        assertEquals( "David", updatedPerson.getPersonName() );
        // asserts that during the test, there are no other calls to the com.cgi.pacoshop.mock object.
        verifyNoMoreInteractions( personDAO );
    }
    @Test
    public void shouldNotUpdateIfPersonNotFound()
    {
        Person person = new Person( 1, "Phillip" );
        when( personDAO.fetchPerson( 1 ) ).thenReturn( person );
        boolean updated = personService.update( 1, "David" );
        assertFalse( updated );
        verify( personDAO ).fetchPerson( 1 );
        verifyZeroInteractions( personDAO );
        verifyNoMoreInteractions( personDAO );
    }
} 