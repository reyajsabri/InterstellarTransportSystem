package com.interstellar.transport.app.controller;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import com.interstellar.transport.app.entity.GalaxyImpl;

public class TestJaxbConversion {

	public static void main1(String[] args) {
		
		try
		{
			URL xmlResource = FileUploadController.class.getResource("/sample/GalaxyMap.xml");
			File savedXmlFile = Paths.get(xmlResource.toURI()).toFile();
			
			JAXBContext jaxbContext = JAXBContext.newInstance(GalaxyImpl.class);             
		 
		    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		 
		    GalaxyImpl galaxyImpl = (GalaxyImpl) jaxbUnmarshaller.unmarshal(savedXmlFile);
		     
		    System.out.println(galaxyImpl);
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
		

	}
	
	
	public static void main(String[] args) {
		try {
			URL schemaResource = FileUploadController.class.getResource("/sample/GalaxyMap.xsd");
			File schemaFile = Paths.get(schemaResource.toURI()).toFile();
			
			URL xmlResource = FileUploadController.class.getResource("/sample/GalaxyMap.xml");
			File savedXmlFile = Paths.get(xmlResource.toURI()).toFile();
			
	        //Get JAXBContext
	        JAXBContext jaxbContext = JAXBContext.newInstance(GalaxyImpl.class);
	         
	        //Create Unmarshaller
	        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	         
	        //Setup schema validator
	        SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
	        Schema galaxySchema = schemaFactory.newSchema(schemaFile);
	        jaxbUnmarshaller.setSchema(galaxySchema);
	        
	      //Unmarshal xml file
            GalaxyImpl galaxyImpl = (GalaxyImpl) jaxbUnmarshaller.unmarshal(savedXmlFile);
            galaxyImpl.getPlanets().forEach(planet -> {
            	
            });
		}catch (Exception e) {
            e.printStackTrace();
        }
	}

}
