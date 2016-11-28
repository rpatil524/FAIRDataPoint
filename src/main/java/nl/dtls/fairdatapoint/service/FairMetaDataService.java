/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.dtls.fairdatapoint.service;

import org.openrdf.rio.RDFFormat;

/**
 *
 * @author Rajaram Kaliyaperumal
 * @since 2015-11-23
 * @version 0.2
 */
public interface FairMetaDataService {   
    /**
     * Get FDP server metadata
     * @param baseURI
     * @param format RDFFormat serialization formats
     * @return  String object  
     * @throws FairMetadataServiceException  
     */
    String retrieveFDPMetaData(String baseURI, RDFFormat format) 
            throws FairMetadataServiceException;  

    /**
     * Get catalog metadata
     * @param baseURI
     * @param catalogID Unique catalog ID
     * @param format RDFFormat serialization formats
     * @return  String object
     * @throws FairMetadataServiceException
     */
    String retrieveCatalogMetaData
        (String baseURI, String catalogID, RDFFormat format) 
                throws FairMetadataServiceException;

    /**
     * Get dataset metadata
     * @param baseURI
     * @param datasetID Unique dataset ID
     * @param format RDFFormat serialization formats
     * @return  String object
     * @throws FairMetadataServiceException
     */
    String retrieveDatasetMetaData
        (String baseURI, String datasetID, RDFFormat format) 
                throws FairMetadataServiceException;
        
    /**
     * Get datarecord metadata
     * @param baseURI
     * @param dataRecordID Unique dataRecordID ID
     * @param format RDFFormat serialization formats
     * @return  String object
     * @throws FairMetadataServiceException
     */
    String retrieveDataRecordMetaData
        (String baseURI, String dataRecordID, RDFFormat format) 
                throws FairMetadataServiceException;
        
    /**
     * Get dataset distribution
     * 
     * @param baseURI
     * @param distributionID
     * @param format
     * @return  String object  
     * @throws nl.dtls.fairdatapoint.service.FairMetadataServiceException  
     */
    String retrieveDatasetDistribution(String baseURI, String distributionID, 
            RDFFormat format) 
            throws FairMetadataServiceException;      
        
}
