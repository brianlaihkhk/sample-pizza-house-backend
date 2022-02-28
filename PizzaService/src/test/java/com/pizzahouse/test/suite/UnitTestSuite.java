package com.pizzahouse.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)

@Suite.SuiteClasses({
   UserServiceTest.class,
   OrderServiceTest.class,
   IntegrationTest.class,
   PizzaMapTest.class
})

public class UnitTestSuite {

}
