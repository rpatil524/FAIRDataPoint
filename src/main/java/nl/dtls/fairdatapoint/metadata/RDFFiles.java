/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.dtls.fairdatapoint.metadata;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import nl.dtls.fairdatapoint.utils.ExampleTurtleFiles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openrdf.model.Model;
import org.openrdf.model.Statement;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParseException;
import org.openrdf.rio.Rio;
import javax.annotation.Nonnull;

/**
 * @author Rajaram Kaliyaperumal
 * @since 2016-11-28
 * @version 0.1
 */
class RDFFiles {
    
    private final static Logger LOGGER = LogManager.getLogger(
            ExampleTurtleFiles.class.getName());
    
    public static final String FDP_METADATA_FILE = "fdp.ttl";
    public static final String CATALOG_METADATA_DIR = "catalog/";
    public static final String DATASET_METADATA_DIR = "dataset/";
    public static final String DISTRIBUTION_METADATA_DIR = "distribution/";
    public static final String BASE_URI_PLACEHOULDER = "BASE_URI";
    
    /**
     * Method to read the content of a turtle file
     * 
     * @param fileName Turtle file name
     * @return File content as a string
     */
    public static String getFileContent(String fileName)  {        
        String content = "";  
        try {
            URL fileURL = RDFFiles.class.getResource(fileName);
            content = Resources.toString(fileURL, Charsets.UTF_8);
        } catch (IOException ex) {
            LOGGER.error("Error getting turle file",ex);          
        }        
        return content;
    }
    
    public static List<Statement> getStatements(@Nonnull String content,
            @Nonnull String baseURI, @Nonnull RDFFormat format) 
            throws IOException, RDFParseException {
        Preconditions.checkNotNull(content,
                "RDF content string must not be null.");
        Preconditions.checkNotNull(baseURI, "Base URI must not be null.");
        Preconditions.checkArgument(!baseURI.isEmpty(), 
                "Base URI must not be empty.");
        Preconditions.checkNotNull(format, "RDF format must not be null.");
        
        content = content.replace(BASE_URI_PLACEHOULDER, baseURI);        
        Model modelCatalog = Rio.parse(new StringReader(content), "", format);            
        Iterator<Statement> it = modelCatalog.iterator();            
        List<Statement> statements = ImmutableList.copyOf(it);
        return statements;
    }
    
}
