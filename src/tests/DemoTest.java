package tests;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DemoTest {

    @Test
    public void TestingMockedMethodCalls()
    {
        List mockedList = mock(List.class);

        mockedList.add("one");
        mockedList.clear();

        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    @Test
    public void TestingMockedReturns()
    {
        LinkedList mockedList = mock(LinkedList.class);

        when(mockedList.get(0)).thenReturn(19);

        Assert.assertEquals(19, mockedList.get(0));
    }
}
