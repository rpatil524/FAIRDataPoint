/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.dtls.fairdatapoint.metadata;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author rajaram
 */
public class RDFFilesTest {
    
    public RDFFilesTest() {
    }
    
    /**
     * Test of getFileContent method, of class RDFFiles.
     */
    @Test
    @Ignore
    public void testGetFileContent() {
        System.out.println("getFileContent");
        String fileName = RDFFiles.CATALOG_METADATA_DIR + "comparativeGenomics.ttl";
        String expResult = "";
        String result = RDFFiles.getFileContent(fileName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
