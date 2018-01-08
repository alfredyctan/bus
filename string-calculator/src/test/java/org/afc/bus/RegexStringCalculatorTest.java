package org.afc.bus;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.fail;

import org.afc.util.JUnit4Util;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.JUnit4;

public class RegexStringCalculatorTest {

	private RegexStringCalculator calculator;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		calculator = new RegexStringCalculator();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testEmpty() {
		JUnit4Util.startCurrentTest(getClass());
		
		int expect = JUnit4Util.expect(0);
		int actual = JUnit4Util.actual(calculator.add(""));
		
		assertThat("should be zero", actual, is(expect));
		
		JUnit4Util.endCurrentTest(getClass());
	}

	@Test
	public void testOne() {
		JUnit4Util.startCurrentTest(getClass());
		
		int expect = JUnit4Util.expect(1);
		int actual = JUnit4Util.actual(calculator.add("1"));
		
		assertThat("should be 1", actual, is(expect));
		
		JUnit4Util.endCurrentTest(getClass());
	}

	@Test
	public void testOneTwo() {
		JUnit4Util.startCurrentTest(getClass());
		
		int expect = JUnit4Util.expect(3);
		int actual = JUnit4Util.actual(calculator.add("1,2"));
		
		assertThat("should be 3", actual, is(expect));
		
		JUnit4Util.endCurrentTest(getClass());
	}

	@Test
	public void testMany() {
		JUnit4Util.startCurrentTest(getClass());
		
		int expect = JUnit4Util.expect(55);
		int actual = JUnit4Util.actual(calculator.add("1,2,3,4,5,6,7,8,9,10"));
		
		assertThat("should be 55", actual, is(expect));
		
		JUnit4Util.endCurrentTest(getClass());
	}

	@Test
	public void testNewlineOneTwoThree() {
		JUnit4Util.startCurrentTest(getClass());
		
		int expect = JUnit4Util.expect(6);
		int actual = JUnit4Util.actual(calculator.add("1\n2,3"));
		
		assertThat("should be 6", actual, is(expect));
		
		JUnit4Util.endCurrentTest(getClass());
	}

	@Test
	public void testDelimiterOneTwoThree() {
		JUnit4Util.startCurrentTest(getClass());
		
		int expect = JUnit4Util.expect(6);
		int actual = JUnit4Util.actual(calculator.add("//;\n1;2;3"));
		
		assertThat("should be 6", actual, is(expect));
		
		JUnit4Util.endCurrentTest(getClass());
	}

	@Test
	public void testDelimiterAnyLength() {
		JUnit4Util.startCurrentTest(getClass());
		
		int expect = JUnit4Util.expect(6);
		int actual = JUnit4Util.actual(calculator.add("//***\n1***2***3"));
		
		assertThat("should be 6", actual, is(expect));
		
		JUnit4Util.endCurrentTest(getClass());
	}

	@Test
	public void testMultiDelimiter() {
		JUnit4Util.startCurrentTest(getClass());
		
		int expect = JUnit4Util.expect(15);
		int actual = JUnit4Util.actual(calculator.add("//*|%|~|?\n1*2%3~4?5"));
		
		assertThat("should be 15", actual, is(expect));
		
		JUnit4Util.endCurrentTest(getClass());
	}

	@Test
	public void testMultiDelimiterLongLength() {
		JUnit4Util.startCurrentTest(getClass());
		
		int expect = JUnit4Util.expect(15);
		int actual = JUnit4Util.actual(calculator.add("//**|%%|~~|??\n1**2%%3~~4??5"));
		
		assertThat("should be 15", actual, is(expect));
		
		JUnit4Util.endCurrentTest(getClass());
	}

	@Test
	public void testBigNumber() {
		JUnit4Util.startCurrentTest(getClass());
		
		int expect = JUnit4Util.expect(3);
		int actual = JUnit4Util.actual(calculator.add("0,1001,2000,3"));
		
		assertThat("should be 3", actual, is(expect));
		
		JUnit4Util.endCurrentTest(getClass());
	}

//  try catch and print is more easy to read in eclipse, sorry I am using eclipse not intellij	
//	@Test(expected = ArithmeticException.class)
	@Test
	public void testNegativeNumberNotAllow() {
		JUnit4Util.startCurrentTest(getClass());
		try {
			int actual = JUnit4Util.actual(calculator.add("0,-1,-2,3"));
			fail("ArithmeticException is expected");
		} catch (ArithmeticException e) {
			e.printStackTrace();
			System.out.println("ArithmeticException is expected in test");
		}
		
		JUnit4Util.endCurrentTest(getClass());
	}
}
