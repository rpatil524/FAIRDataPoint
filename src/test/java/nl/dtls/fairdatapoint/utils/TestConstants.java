/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.dtls.fairdatapoint.utils;

import org.openrdf.rio.RDFFormat;

/**
 * Contains references to the example metadata rdf files.  
 * 
 * @author Rajaram Kaliyaperumal
 * @since 2016-11-28
 * @version 0.1
 */
public class TestConstants {    
    
    public static final String EXAMPLE_CATALOG_ID = "comparativeGenomics";
    public static final String EXAMPLE_DATASET_ID = "goNlSvR5";
    public static final String EXAMPLE_DISTRIBUTION_ID = "goNlSvR5-html";
    public static final RDFFormat FILES_RDF_FORMAT = RDFFormat.TURTLE;
    public final static String EXAMPLE_FILES_BASE_URI = 
            "http://www.dtls.nl/";
    public final static String FDP_URI = "http://www.dtls.nl/fdp";
}
