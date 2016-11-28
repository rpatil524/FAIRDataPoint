/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.dtls.fairdatapoint.service.impl;

import nl.dtls.fairdatapoint.api.config.RestApiTestContext;
import nl.dtls.fairdatapoint.service.FairMetaDataService;
import nl.dtls.fairdatapoint.service.FairMetadataServiceException;
import nl.dtls.fairdatapoint.utils.TestConstants;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openrdf.rio.RDFFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * FairMetaDataServiceImpl class unit tests
 * 
 * @author Rajaram Kaliyaperumal
 * @since 2016-02-08
 * @version 0.3
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {RestApiTestContext.class})
@DirtiesContext
public class FairMetaDataServiceImplTest { 
    
    @Autowired
    private FairMetaDataService fairMetaDataService;
    
    /**
     * The RDFFormat can't be NULL, this test is excepted to throw 
     * IllegalArgumentException exception 
     */
    @Test(expected = NullPointerException.class) 
    public void nullRDFFormat(){
        try {
            this.fairMetaDataService.retrieveFDPMetaData(TestConstants.FDP_URI, null);
        } catch (FairMetadataServiceException ex) {
            String errorMsg = "The test is excepted to throw "
                    + "NullPointerException";
            fail(errorMsg);
        }
    }
    
    /**
     * Test to retrieve FDP metadata, this test is excepted to pass
     */
    @Test
    public void retrieveFDPMetaData(){
        try {
            String actual = this.fairMetaDataService.retrieveFDPMetaData(TestConstants.FDP_URI, RDFFormat.TURTLE);
            assertNotNull(actual);
        } catch (FairMetadataServiceException ex) {
            String errorMsg = "The test is excepted to throw "
                    + "FairMetadataServiceException";
            fail(errorMsg);
        }
    }
    
    /**
     * Test to retrieve catalog metadata with NULL catalogID, 
     * this test is excepted to throw NullPointerException exception 
     */
    @Test(expected = NullPointerException.class) 
    public void nullCatalogID(){
        String errorMsg = "This test is excepeted to throw "
                + "NullPointerException";
        try {
            String baseURL = TestConstants.FDP_URI + "/" + 
                    TestConstants.EXAMPLE_CATALOG_ID;
            this.fairMetaDataService.retrieveCatalogMetaData(baseURL, null, 
                    RDFFormat.TURTLE);
            fail(errorMsg); 
        } catch (FairMetadataServiceException ex) {
            fail(errorMsg);
        }
    }
    
    /**
     * Test to retrieve catalog metadata with empty catalogID, 
     * this test is excepted to throw IllegalArgumentException exception 
     */
    @Test(expected = IllegalArgumentException.class)  
    public void emptyCatalogID(){
        String errorMsg = "This test is excepeted to throw "
                + "IllegalArgumentException";
        try {
            String catalogID = "";
            String baseURL = TestConstants.FDP_URI + "/" + 
                    TestConstants.EXAMPLE_CATALOG_ID;
            this.fairMetaDataService.retrieveCatalogMetaData(baseURL, catalogID, 
                    RDFFormat.TURTLE);
            fail(errorMsg); 
        } catch (FairMetadataServiceException ex) {
            fail(errorMsg);
        }
    }
    
    /**
     * Test to retrieve catalog metadata with empty catalogID, 
     * this test is excepted to throw NullPointerException exception 
     */
    @Test(expected = NullPointerException.class) 
    public void nullRDFFormatToRetrieveCatalogMetaData(){
        String errorMsg = "This test is excepeted to throw "
                + "NullPointerException";
        try {
            String baseURL = TestConstants.FDP_URI + "/" + 
                    TestConstants.EXAMPLE_CATALOG_ID;
            this.fairMetaDataService.retrieveCatalogMetaData(baseURL,
                    TestConstants.EXAMPLE_CATALOG_ID, null);
            fail(errorMsg); 
        } catch (FairMetadataServiceException ex) {
            fail(errorMsg);
        }
    }
    
    /**
     * Test to retrieve NonExiting catalog metadata, this test is excepted \
     * to pass
     */
    @Test
    public void retrieveNonExitingCatalogMetaData(){ 
        String errorMsg = "The test is excepted not to throw "
                + "FairMetadataServiceException";
        try {            
            String baseURL = TestConstants.FDP_URI + "/" + 
                    TestConstants.EXAMPLE_CATALOG_ID;
            String actual = this.fairMetaDataService.retrieveCatalogMetaData(
                   baseURL, "dummpID676", RDFFormat.TURTLE);
            assertNull(actual);
        } catch (FairMetadataServiceException ex) {
            fail(errorMsg);
        }
    }
    
    /**
     * Test to retrieve catalog metadata, this test is excepted to pass
     */
    @Test
    public void retrieveCatalogMetaData(){
        try {
            String baseURL = TestConstants.FDP_URI + "/" + 
                    TestConstants.EXAMPLE_CATALOG_ID;
            String actual = this.fairMetaDataService.retrieveCatalogMetaData(baseURL, TestConstants.EXAMPLE_CATALOG_ID, 
                    RDFFormat.TURTLE);
            assertNotNull(actual);
        } catch (FairMetadataServiceException ex) {
            String errorMsg = "The test is excepted not to throw "
                    + "FairMetadataServiceException";
            fail(errorMsg);
        }
    }
    
    /**
     * Test to retrieve dataset metadata with NULL catalog or dataset IDs, 
     * this test is excepted to throw NullPointerException exception 
     * 
     */
    @Test(expected = NullPointerException.class) 
    public void checkNullCatalogDatasetIDs() {     
        String errorMsg = "The test is excepted to throw "
                    + "NullPointerException";
        try {
            String baseURL = TestConstants.FDP_URI + "/" + 
                    TestConstants.EXAMPLE_CATALOG_ID + "/" + 
                    TestConstants.EXAMPLE_DATASET_ID;
            this.fairMetaDataService.retrieveDatasetMetaData(
                    baseURL, null, 
                    RDFFormat.TURTLE);
            fail(errorMsg); 
        } 
        catch (FairMetadataServiceException ex) {            
            fail(errorMsg);
        }
    }
    
    /**
     * Test to retrieve dataset metadata with EMPTY catalog or dataset IDs, 
     * this test is excepted to throw IllegalArgumentException exception 
     * 
     */
    @Test(expected = IllegalArgumentException.class) 
    public void checkEmptyDatasetIDs() {
        String errorMsg = "The test is excepted to throw "
                    + "IllegalArgumentException";
        try {
            String baseURL = TestConstants.FDP_URI + "/" + 
                    TestConstants.EXAMPLE_CATALOG_ID + "/" + 
                    TestConstants.EXAMPLE_DATASET_ID;
            this.fairMetaDataService.retrieveDatasetMetaData(baseURL, "", 
                    RDFFormat.TURTLE);
            fail(errorMsg); 
        } 
        catch (FairMetadataServiceException ex) {            
            fail(errorMsg);
        }
    }
    
    /**
     * Test to retrieve dataset metadata with empty datasetID, 
     * this test is excepted to throw NullPointerException exception 
     */
    @Test(expected = NullPointerException.class) 
    public void nullRDFFormatToRetrieveDatasetMetaData(){
        String errorMsg = "The test is excepted to throw "
                    + "NullPointerException";
        try {
            String baseURL = TestConstants.FDP_URI + "/" + 
                    TestConstants.EXAMPLE_CATALOG_ID + "/" + 
                    TestConstants.EXAMPLE_DATASET_ID;
            this.fairMetaDataService.retrieveDatasetMetaData(baseURL, 
                    TestConstants.EXAMPLE_DATASET_ID, null);
            fail(errorMsg);            
        } catch (FairMetadataServiceException ex) {            
            fail(errorMsg);
        }
    }
    
    /**
     * Test to retrieve NonExiting dataset metadata, this test is excepted 
     * to pass
     */
    @Test
    public void retrieveNonExitingdDatasetMetaData(){
        try {
            String baseURL = TestConstants.FDP_URI + "/" + 
                    TestConstants.EXAMPLE_CATALOG_ID + "/" + 
                    TestConstants.EXAMPLE_DATASET_ID;
            String actual = this.fairMetaDataService.retrieveDatasetMetaData(
                    baseURL, "dummpID7549", RDFFormat.TURTLE);
            assertNull(actual);
        } catch (FairMetadataServiceException ex) {
            String errorMsg = "The test is not excepted to throw "
                    + "FairMetadataServiceException";
            fail(errorMsg);
        }
    }
    
    /**
     * Test to retrieve dataset metadata, this test is excepted to pass
     */
    @Test
    public void retrieveDatasetMetaData(){
        try {
            String baseURL = TestConstants.FDP_URI + "/" + 
                    TestConstants.EXAMPLE_CATALOG_ID + "/" + 
                    TestConstants.EXAMPLE_DATASET_ID;
            String actual = this.fairMetaDataService.retrieveDatasetMetaData(baseURL, TestConstants.EXAMPLE_DATASET_ID, 
                    RDFFormat.TURTLE);
            assertNotNull(actual);
        } catch (FairMetadataServiceException ex) {
            String errorMsg = "The test is not excepted to throw "
                    + "FairMetadataServiceException";
            fail(errorMsg);
        }
    }
    
    
    /**
     * To test NULL IDs parameters, this test is excepted to 
     * NullPointerException
     * 
     */
    @Test(expected = NullPointerException.class) 
    public void checkNullDatasetDistributionID() { 
        String errorMsg = "The test is excepted to throw "
                + "NullPointerException";
        String baseURL = TestConstants.FDP_URI + "/" + 
                    TestConstants.EXAMPLE_CATALOG_ID + "/" + 
                    TestConstants.EXAMPLE_DATASET_ID + "/" + 
                TestConstants.EXAMPLE_DISTRIBUTION_ID;
        try {
            this.fairMetaDataService.retrieveDatasetDistribution(
                    baseURL, null, RDFFormat.TURTLE);
            fail(errorMsg);
        }
        catch (FairMetadataServiceException e) {      
            fail(errorMsg);
        }    
    }
    
    /**
     * To test empty IDs parameters, this test is excepted to 
     * IllegalArgumentException
     * 
     */
    @Test(expected = IllegalArgumentException.class) 
    public void checkEmptyDatasetDistributionID() { 
        
        String errorMsg = "The test is excepted to throw "
                + "IllegalArgumentException";
        try {
            String baseURL = TestConstants.FDP_URI + "/" + 
                    TestConstants.EXAMPLE_CATALOG_ID + "/" + 
                    TestConstants.EXAMPLE_DATASET_ID + "/" + 
                TestConstants.EXAMPLE_DISTRIBUTION_ID;
            this.fairMetaDataService.retrieveDatasetDistribution(baseURL, "", 
                    RDFFormat.TURTLE);
            fail(errorMsg);
        }
        catch (FairMetadataServiceException e) {         
            fail(errorMsg);
        }    
    }
    
    /**
     * Test to retrieve non exiting distribution metadata, this test is 
     * excepted to pass
     */
    @Test
    public void retrieveNonExitingDatasetDistribution(){
        try {
            String baseURL = TestConstants.FDP_URI + "/" + 
                    TestConstants.EXAMPLE_CATALOG_ID + "/" + 
                    TestConstants.EXAMPLE_DATASET_ID + "/" + 
                TestConstants.EXAMPLE_DISTRIBUTION_ID;
            String actual = this.fairMetaDataService.
                    retrieveDatasetDistribution(baseURL, "dummpyID5645",                     
                            RDFFormat.TURTLE);
            assertNull(actual);
        } catch (FairMetadataServiceException ex) {
            String errorMsg = "The test is not excepted to throw "
                    + "FairMetadataServiceException";
            fail(errorMsg);
        }
    }

    /**
     * Test to retrieve distribution metadata, this test is excepted to pass
     */
    @Test
    public void retrieveDatasetDistribution(){
        try {
            String baseURL = TestConstants.FDP_URI + "/" + 
                    TestConstants.EXAMPLE_CATALOG_ID + "/" + 
                    TestConstants.EXAMPLE_DATASET_ID + "/" + 
                TestConstants.EXAMPLE_DISTRIBUTION_ID;
            String actual = this.fairMetaDataService.
                    retrieveDatasetDistribution(baseURL,                   
                            TestConstants.EXAMPLE_DISTRIBUTION_ID,                     
                            RDFFormat.TURTLE);
            assertNotNull(actual);
        } catch (FairMetadataServiceException ex) {
            String errorMsg = "The test is not excepted to throw "
                    + "FairMetadataServiceException";
            fail(errorMsg);
        }
    }
    
}
