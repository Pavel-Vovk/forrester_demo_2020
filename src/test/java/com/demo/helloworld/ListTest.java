
// ListTest.java --
//
// ListTest.java is part of CloudBees Flow.
//
// Copyright (c) 2020 CloudBees, Inc.
// All rights reserved.
//

package com.demo.helloworld;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeUnit;
public class ListTest
{

    //~ Static fields/initializers ---------------------------------------------

    private static int timeWaitMin = 7;
    private static int timeWaitMax = 25;
    private static double probabilityTrue = 0.7;
    private static List<String> USERS = Arrays.asList("joe", "john", "mickey");
    
    public static boolean getRandomBoolean(double probabilityTrue) {
        return Math.random() < probabilityTrue;
        // I tried another approaches here, still the same result
    }
    private static int getRandomNumberInRange(int min, int max) throws Exception {

	    if (min >= max) {
		    throw new IllegalArgumentException("max must be greater than min");
	    }

	    Random r = new Random();
	    return r.nextInt((max - min) + 1) + min;
    }
    

    //~ Instance fields --------------------------------------------------------

    private List<String> m_list = new ArrayList<>();

    //~ Methods ----------------------------------------------------------------

    @Test public void addExisting()
    {
        m_list.add("joe");
        assertTrue(m_list.contains("joe"));
    }

    @Test public void contains()
    {
        m_list.add("katie");
        assertTrue(m_list.contains("katie"));
    }

    @Ignore @Test public void ignored_test()
    {
        assertFalse(m_list.contains("matthew"));
    }

    @Test public void missing()
    {
        assertFalse(m_list.contains("matthew"));
    }

    //the test for Random status, butt jenkins can't finish the Pipeline in this case.
    @Test public void random_status_test()
    {
        assertTrue(getRandomBoolean(probabilityTrue));
    }
    
    @Test public void long_test() throws Exception
    {
        m_list.add("joe");
        try{
            TimeUnit.SECONDS.sleep(getRandomNumberInRange(timeWaitMin, timeWaitMax));
            assertTrue(m_list.contains("joe"));
        }catch(Exception e){
             throw new Exception("Can't sleep...  :( ");
        }
    }

}

