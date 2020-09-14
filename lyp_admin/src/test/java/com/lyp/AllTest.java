package com.lyp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        GuavaTest.class,
        ShiroTest.class,
        WebSocketTest.class
})
public class AllTest {

}
