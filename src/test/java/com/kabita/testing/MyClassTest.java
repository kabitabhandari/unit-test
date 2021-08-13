package com.kabita.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.mockito.Mock;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;




@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class MyClassTest {
    private static final int goodId = 1;
    private static final int badId = 0;
    private static final String name = "mock-name";
    @Mock
    private  ServiceClient serviceClient;

//    @InjectMocks
    private MyClass myClass;

   String mockName = "mock-name";

    // An alternate option for writing annotation @InjectMocks (line no 25 only) is this:
    @BeforeEach
    public void setup(){
        this.serviceClient = Mockito.mock(ServiceClient.class);
         myClass = new MyClass(serviceClient);
    }

    @Test
    public void getMember_zeroId_shouldThrowException(){
        Assertions.assertThrows(RuntimeException.class, () -> myClass.getMemberName(badId));
    }

    @Test
    public void getMemberName_positiveId_shouldSucceed(){
        when(serviceClient.fetchMemberName(goodId)).thenReturn("Kabita");
         String actualName = myClass.getMemberName(goodId);
         Assertions.assertEquals( "Kabita", actualName);
    }

    //unit testing a void method
    @Test
    public void updateMemberName_positiveId_shouldSucceed(){
        serviceClient.updateMemberName(goodId, mockName);
        Mockito.verify(serviceClient, Mockito.times(1)).updateMemberName(goodId, mockName);
    }

    //Setting up multiple mocks on a method
    @Test
    public void getMemberNames_validIds_shouldReturnNameList(){
        when(serviceClient.fetchMemberName(1)).thenReturn("Kabita");
        when(serviceClient.fetchMemberName(2)).thenReturn("Milan dai");
        when(serviceClient.fetchMemberName(3)).thenReturn("Milan dai sathi-1");
        when(serviceClient.fetchMemberName(4)).thenReturn("Milan dai sathi-2");
        when(serviceClient.fetchMemberName(5)).thenReturn("Milan dai sathi-3");

        List<String> listOfMemberNames = myClass.getMemberNames(1,2,3,4,5)  ;
        Assertions.assertEquals("Milan dai", listOfMemberNames.get(1));
        Assertions.assertEquals(5, listOfMemberNames.size());
    }
}
