package com.pizzahouse.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)

@Suite.SuiteClasses({
   OrderServiceTest.class,
   IntegrationTest.class,
   UserServiceTest.class
})

public class UnitTestSuite {

}
