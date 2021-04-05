package com.zte.auth.hello.service.impl;

import com.zte.auth.service.impl.IHelloServiceImpl;
import com.zte.auth.service.impl.IOtherServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class IHelloServiceImplTest {

    @InjectMocks
    private IHelloServiceImpl helloService;

    @Mock
    private IOtherServiceImpl iOtherServiceImpl;

    @Before
    public void setUp() throws Exception {
    }
    @Test
    public void sayHelloTest() throws Exception{
        IHelloServiceImpl spy = Mockito.spy(IHelloServiceImpl.class);
        Mockito.when(spy.b()).thenReturn(2L);
        String str = spy.sayHello();
        Assert.assertEquals("Hello World!1",str);
    }

    @Test
    public void bTest() throws Exception{
        long i = helloService.b();
        Assert.assertEquals(1L,i);
    }

}
