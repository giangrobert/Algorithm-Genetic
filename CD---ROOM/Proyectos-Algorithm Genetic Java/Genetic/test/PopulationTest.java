/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gian
 */
public class PopulationTest {
    
    public PopulationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of initializePopulation method, of class Population.
     */
    @Test
    public void testInitializePopulation() {
        System.out.println("initializePopulation");
        int size = 0;
        Population instance = new Population();
        instance.initializePopulation(size);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFittest method, of class Population.
     */
    @Test
    public void testGetFittest() {
        System.out.println("getFittest");
        Population instance = new Population();
        Individual expResult = null;
        Individual result = instance.getFittest();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSecondFittest method, of class Population.
     */
    @Test
    public void testGetSecondFittest() {
        System.out.println("getSecondFittest");
        Population instance = new Population();
        Individual expResult = null;
        Individual result = instance.getSecondFittest();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLeastFittestIndex method, of class Population.
     */
    @Test
    public void testGetLeastFittestIndex() {
        System.out.println("getLeastFittestIndex");
        Population instance = new Population();
        int expResult = 0;
        int result = instance.getLeastFittestIndex();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateFitness method, of class Population.
     */
    @Test
    public void testCalculateFitness() {
        System.out.println("calculateFitness");
        Population instance = new Population();
        instance.calculateFitness();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
