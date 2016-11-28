/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.dtls.fairdatapoint.api.controller;

import javax.servlet.http.HttpServletResponse;
import nl.dtls.fairdatapoint.api.config.RestApiTestContext;
import nl.dtls.fairdatapoint.utils.TestConstants;
import org.apache.http.HttpHeaders;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * MetadataController class unit tests
 * 
 * @author Rajaram Kaliyaperumal
 * @since 2016-02-11
 * @version 0.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {RestApiTestContext.class})
@DirtiesContext
public class MetadataControllerTest {
    
    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    
    /**
     * Check unsupported accept header.
     * 
     * @throws Exception 
     */    
    @Test(expected = Exception.class)    
    public void unsupportedAcceptHeader() throws Exception{  
        MockHttpServletRequest request;
        MockHttpServletResponse response;         
        Object handler;  
        
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        request.setMethod("GET");
        request.addHeader(HttpHeaders.ACCEPT, "application/trig");
        String url = "/" + TestConstants.EXAMPLE_CATALOG_ID;
        request.setRequestURI(url);      
        handler = handlerMapping.getHandler(request).getHandler();
        handlerAdapter.handle(request, response, handler);          
        assertEquals(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, 
                response.getStatus());    
    }    
    /**
     * The default content type is text/turtle, when the accept header is not
     * set the default content type is served. This test is excepted to pass.
     * 
     * @throws Exception 
     */    
    @Test    
    public void noAcceptHeader() throws Exception{        
        MockHttpServletRequest request;
        MockHttpServletResponse response;         
        Object handler;  
        
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        request.setMethod("GET");
        String url = "/" + TestConstants.EXAMPLE_CATALOG_ID;
        request.setRequestURI(url);      
        handler = handlerMapping.getHandler(request).getHandler();
        handlerAdapter.handle(request, response, handler);          
        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
    }
    
    /**
     * Check supported accept headers.
     * 
     * @throws Exception 
     */    
    @Test    
    public void supportedAcceptHeaders() throws Exception{
        
        MockHttpServletRequest request;
        MockHttpServletResponse response;         
        Object handler;  
        
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        request.setMethod("GET");
        request.addHeader(HttpHeaders.ACCEPT, "text/turtle");
        String url = "/" + TestConstants.EXAMPLE_CATALOG_ID;
        request.setRequestURI(url);      
        handler = handlerMapping.getHandler(request).getHandler();
        handlerAdapter.handle(request, response, handler);          
        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
        
        request.addHeader(HttpHeaders.ACCEPT, "text/n3");      
        handler = handlerMapping.getHandler(request).getHandler();
        handlerAdapter.handle(request, response, handler);          
        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
        
        url = url + "/" + TestConstants.EXAMPLE_DATASET_ID;
        request.setRequestURI(url);
        request.addHeader(HttpHeaders.ACCEPT, "application/ld+json");      
        handler = handlerMapping.getHandler(request).getHandler();
        handlerAdapter.handle(request, response, handler);          
        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
        
        url = url + "/" + TestConstants.EXAMPLE_DISTRIBUTION_ID;
        request.setRequestURI(url);         
        request.addHeader(HttpHeaders.ACCEPT, "application/rdf+xml");      
        handler = handlerMapping.getHandler(request).getHandler();
        handlerAdapter.handle(request, response, handler);          
        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
    }
    
    /**
     * Check non existing catalog.
     * 
     * @throws Exception 
     */    
    @Test    
    public void nonExistingCatalog() throws Exception{
        
        MockHttpServletRequest request;
        MockHttpServletResponse response;         
        Object handler;  
        
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        request.setMethod("GET");
        request.addHeader(HttpHeaders.ACCEPT, "text/turtle");
        request.setRequestURI(
                "/dumpy");      
        handler = handlerMapping.getHandler(request).getHandler();
        handlerAdapter.handle(request, response, handler);          
        assertEquals(HttpServletResponse.SC_NOT_FOUND, response.getStatus());
    }
    
    /**
     * Check existing catalog.
     * 
     * @throws Exception 
     */    
    @Test    
    public void existingCatalog() throws Exception{
        
        MockHttpServletRequest request;
        MockHttpServletResponse response;         
        Object handler;  
        
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        request.setMethod("GET");
        request.addHeader(HttpHeaders.ACCEPT, "text/turtle");
        String url = "/" + TestConstants.EXAMPLE_CATALOG_ID;
        request.setRequestURI(url);      
        handler = handlerMapping.getHandler(request).getHandler();
        handlerAdapter.handle(request, response, handler);          
        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
    }
    
    /**
     * Check non existing dataset.
     * 
     * @throws Exception 
     */    
    @Test    
    public void nonExistingDataset() throws Exception{
        
        MockHttpServletRequest request;
        MockHttpServletResponse response;         
        Object handler;  
        
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        request.setMethod("GET");
        request.addHeader(HttpHeaders.ACCEPT, "text/turtle");
        String url = "/" + TestConstants.EXAMPLE_CATALOG_ID + "/dummpy";
        request.setRequestURI(url);      
        handler = handlerMapping.getHandler(request).getHandler();
        handlerAdapter.handle(request, response, handler);          
        assertEquals(HttpServletResponse.SC_NOT_FOUND, response.getStatus());
    }
    
    /**
     * Check existing Dataset.
     * 
     * @throws Exception 
     */    
    @Test    
    public void existingDataset() throws Exception{
        
        MockHttpServletRequest request;
        MockHttpServletResponse response;         
        Object handler;  
        
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        request.setMethod("GET");
        request.addHeader(HttpHeaders.ACCEPT, "text/turtle");
        String url = "/" + TestConstants.EXAMPLE_CATALOG_ID;
        url = url + "/" + TestConstants.EXAMPLE_DATASET_ID;
        request.setRequestURI(url);      
        handler = handlerMapping.getHandler(request).getHandler();
        handlerAdapter.handle(request, response, handler);          
        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
    } 
    
    
    /**
     * Check non existing distribution Content.
     * 
     * @throws Exception 
     */    
    @Test    
    public void nonExistingDistribution() throws Exception{
        
        MockHttpServletRequest request;
        MockHttpServletResponse response;         
        Object handler;  
        
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        request.setMethod("GET");
        request.addHeader(HttpHeaders.ACCEPT, "text/turtle");
        String url = "/" + TestConstants.EXAMPLE_CATALOG_ID;
        url = url + "/" + TestConstants.EXAMPLE_DATASET_ID + "/dummpu";
        request.setRequestURI(url);      
        handler = handlerMapping.getHandler(request).getHandler();
        handlerAdapter.handle(request, response, handler);          
        assertEquals(HttpServletResponse.SC_NOT_FOUND, response.getStatus());
    }
    
    /**
     * Check existing distribution Content.
     * 
     * @throws Exception 
     */    
    @Test    
    public void existingDistribution() throws Exception{
        
        MockHttpServletRequest request;
        MockHttpServletResponse response;         
        Object handler;  
        
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        request.setMethod("GET");
        request.addHeader(HttpHeaders.ACCEPT, "text/turtle");
        String url = "/" + TestConstants.EXAMPLE_CATALOG_ID;
        url = url + "/" + TestConstants.EXAMPLE_DATASET_ID + "/" + 
                TestConstants.EXAMPLE_DISTRIBUTION_ID;
        request.setRequestURI(url);       
        handler = handlerMapping.getHandler(request).getHandler();
        handlerAdapter.handle(request, response, handler);          
        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
    }
    
}
