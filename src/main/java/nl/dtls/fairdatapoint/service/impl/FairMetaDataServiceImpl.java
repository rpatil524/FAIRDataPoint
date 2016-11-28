/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.dtls.fairdatapoint.service.impl;

import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.List;
import nl.dtls.fairdatapoint.metadata.RDFFiles;
import nl.dtls.fairdatapoint.service.FairMetaDataService;
import nl.dtls.fairdatapoint.service.FairMetadataServiceException;
import nl.dtls.fairdatapoint.service.impl.utils.RDFUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openrdf.model.Statement;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFHandlerException;
import org.openrdf.rio.RDFParseException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rajaram Kaliyaperumal
 * @since 2015-12-17
 * @version 0.2
 */
@Service("fairMetaDataServiceImpl")
public class FairMetaDataServiceImpl implements FairMetaDataService {
    private final static Logger LOGGER 
            = LogManager.getLogger(FairMetaDataServiceImpl.class);    
    
    @Override
    public String retrieveFDPMetaData(@Nonnull String baseURI, 
            @Nonnull RDFFormat format) throws FairMetadataServiceException { 
        Preconditions.checkNotNull(baseURI, "Base URI must not be null.");
        Preconditions.checkArgument(!baseURI.isEmpty(), 
                "Base URI must not be empty.");
        Preconditions.checkNotNull(format, "RDF format must not be null."); 
        String fileAbsURL = RDFFiles.FDP_METADATA_FILE;
        String fdpMetadata = null;
        try {
            fdpMetadata = getMetadata(fileAbsURL, baseURI, format);
        } catch (NullPointerException ex) {
            String errMsg = "Error getting file " + ex;
            LOGGER.error(errMsg);
            throw(new FairMetadataServiceException(errMsg));
        }
        return fdpMetadata;
    }

    @Override
    public String retrieveCatalogMetaData(@Nonnull String baseURI,
            @Nonnull String catalogID, @Nonnull RDFFormat format) 
            throws FairMetadataServiceException {
        
        Preconditions.checkNotNull(baseURI, "Base URI must not be null.");
        Preconditions.checkArgument(!baseURI.isEmpty(), 
                "Base URI must not be empty.");
        Preconditions.checkNotNull(format, "RDF format must not be null.");
        Preconditions.checkArgument(!catalogID.isEmpty(), 
                "catalogID must not be empty.");
        Preconditions.checkNotNull(catalogID, "catalogID must not be null."); 
        String fileName =  catalogID + "." + RDFFiles.DEFAULT_FORMAT.
                getFileExtensions().get(0);
        String fileAbsURL = RDFFiles.CATALOG_METADATA_DIR + fileName;
        String catalogMetadata = null;
        try {
            catalogMetadata = getMetadata(fileAbsURL, baseURI, format);
        } catch (NullPointerException ex) {
            String errMsg = "Requesed file not fount " + ex;
            LOGGER.debug(errMsg);
        }
        return catalogMetadata;
    }

    @Override
    public String retrieveDatasetMetaData(@Nonnull String baseURI,
            @Nonnull String datasetID, @Nonnull RDFFormat format) 
            throws FairMetadataServiceException {        
        Preconditions.checkNotNull(baseURI, "Base URI must not be null.");
        Preconditions.checkArgument(!baseURI.isEmpty(), 
                "Base URI must not be empty.");
        Preconditions.checkNotNull(format, "RDF format must not be null.");
        Preconditions.checkArgument(!datasetID.isEmpty(), 
                "datasetID must not be empty.");
        Preconditions.checkNotNull(datasetID, "datasetID must not be null."); 
        String fileName =  datasetID + "." + RDFFiles.DEFAULT_FORMAT.
                getFileExtensions().get(0);
        String fileAbsURL = RDFFiles.DATASET_METADATA_DIR + fileName;
        String datasetMetadata = null;
        try {
            datasetMetadata = getMetadata(fileAbsURL, baseURI, format);
        } catch (NullPointerException ex) {
            String errMsg = "Requesed file not fount " + ex;
            LOGGER.debug(errMsg);
        }
        return datasetMetadata;        
    }     	

    @Override
    public String retrieveDataRecordMetaData(@Nonnull String baseURI, 
            @Nonnull String dataRecordID, @Nonnull RDFFormat format) 
            throws FairMetadataServiceException {
        
        Preconditions.checkNotNull(baseURI, "Base URI must not be null.");
        Preconditions.checkArgument(!baseURI.isEmpty(), 
                "Base URI must not be empty.");
        Preconditions.checkNotNull(format, "RDF format must not be null.");
        Preconditions.checkArgument(!dataRecordID.isEmpty(), 
                "dataRecordID must not be empty.");
        Preconditions.checkNotNull(dataRecordID, 
                "dataRecordID must not be null."); 
        
        String fileName =  dataRecordID + "." + RDFFiles.DEFAULT_FORMAT.
                getFileExtensions().get(0);
        String fileAbsURL = RDFFiles.DATASET_METADATA_DIR + fileName;
        String dataRecordMetadata = null;
        try {
            dataRecordMetadata = getMetadata(fileAbsURL, baseURI, format);
        } catch (NullPointerException ex) {
            String errMsg = "Requesed file not fount " + ex;
            LOGGER.debug(errMsg);
        }
        return dataRecordMetadata;
    }
    
    @Override
    public String retrieveDatasetDistribution(@Nonnull String baseURI, 
            @Nonnull String distributionID, @Nonnull RDFFormat format) 
            throws FairMetadataServiceException { 
        
        Preconditions.checkNotNull(baseURI, "Base URI must not be null.");
        Preconditions.checkArgument(!baseURI.isEmpty(), 
                "Base URI must not be empty.");
        Preconditions.checkNotNull(format, "RDF format must not be null.");
        Preconditions.checkArgument(!distributionID.isEmpty(), 
                "distributionID must not be empty.");
        Preconditions.checkNotNull(distributionID,
                "distributionID must not be null."); 
        
        String fileName =  distributionID + "." + RDFFiles.DEFAULT_FORMAT.
                getFileExtensions().get(0);
        String fileAbsURL = RDFFiles.DISTRIBUTION_METADATA_DIR + fileName;
        String datasetDistribution = null;
        try{
            datasetDistribution = getMetadata(fileAbsURL, baseURI, format);
        } catch (NullPointerException ex) {
            String errMsg = "Requesed file not fount " + ex;
            LOGGER.debug(errMsg);
        }
        return datasetDistribution;        
    } 
    
    private String getMetadata(String fileURL, String baseURI, 
            RDFFormat format) throws FairMetadataServiceException{
        String metadata = null;
        try {
            String content = RDFFiles.getFileContent(fileURL);
            Preconditions.checkNotNull(content,
                "RDF content string must not be null.");
            LOGGER.info(content);
            List<Statement> statements = RDFFiles.getStatements(content, 
                    baseURI, RDFFiles.DEFAULT_FORMAT);
            if(!statements.isEmpty()) {
                metadata = RDFUtils.writeToString(statements, format);
            }            
            return metadata; 
        } catch (RDFParseException ex) {                
            String errMsg = "Error parsing rdf file " + ex;
            LOGGER.error(errMsg);
            throw(new FairMetadataServiceException(errMsg));
        } catch (RepositoryException | RDFHandlerException ex) {
            String errMsg = "Error converting rdf statements " + ex;
            LOGGER.error(errMsg);
            throw(new FairMetadataServiceException(errMsg));
        } catch (IOException ex) {
            String errMsg = "Error getting file " + ex;
            LOGGER.error(errMsg);
            throw(new FairMetadataServiceException(errMsg));
        }
    }
}
