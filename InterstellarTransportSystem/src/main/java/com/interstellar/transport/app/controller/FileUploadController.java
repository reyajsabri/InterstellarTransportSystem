package com.interstellar.transport.app.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.interstellar.transport.app.entity.DistanceBoundRouteImpl;
import com.interstellar.transport.app.entity.GalaxyImpl;
import com.interstellar.transport.app.entity.PlanetImpl;
import com.interstellar.transport.app.entity.TimeBoundRouteImpl;
import com.interstellar.transport.app.service.UploadService;

@Controller
@RequestMapping("/RouteUpload")
public class FileUploadController {
	
	@Autowired
	UploadService uploadService;

	//Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = System.getProperty("java.io.tmpdir");

    @GetMapping("/")
    public String index() {
        return "Upload";
    }

    @PostMapping("/XMLUpload")
    public String singleFileXmlUpload(@RequestParam("file") MultipartFile xmlFile,
                                   RedirectAttributes redirectAttributes) {
    	
    	if(true) {
    		return "redirect:/RouteUpload/UploadStatus?message="+"XML  Upload functionality is in-progress ";
    	}
    	
    	if (xmlFile.isEmpty()) {
            return "redirect:/RouteUpload/UploadStatus?message="+"Error: Please select a file to upload";
        }
    	try {
    		
    		URL schemaResource = FileUploadController.class.getResource("/sample/GalaxyMap.xsd");
    		File schemaFile = Paths.get(schemaResource.toURI()).toFile();
    		
    		// Get the file and save it somewhere
            byte[] bytes = xmlFile.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + xmlFile.getOriginalFilename());
            Files.write(path, bytes);
            
            File savedXmlFile = new File(path.toString());
            
            //Get JAXBContext
            JAXBContext jaxbContext = JAXBContext.newInstance(GalaxyImpl.class);
             
            //Create Unmarshaller
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
             
            //Setup schema validator
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.XML_NS_URI);
            Schema galaxySchema = schemaFactory.newSchema(schemaFile);
            jaxbUnmarshaller.setSchema(galaxySchema);
            
            
    	}catch (Exception e) {
            e.printStackTrace();
        }
    	
    	return "redirect:/RouteUpload/UploadStatus?message="+"You have successfully uploaded '" + xmlFile.getOriginalFilename() + "'";
    }
    @PostMapping("/ExcelUpload")
    public String singleFileUpload(@RequestParam("file") MultipartFile excelFile,
                                   RedirectAttributes redirectAttributes) {

        if (excelFile.isEmpty()) {
            return "redirect:/RouteUpload/UploadStatus?message="+"Error: Please select a file to upload";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = excelFile.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + excelFile.getOriginalFilename());
            Files.write(path, bytes);
            FileInputStream file = new FileInputStream(new File(path.toString()));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet planetsSheet = workbook.getSheetAt(0);
            
            XSSFSheet routeSheet = workbook.getSheetAt(1);
            XSSFSheet trafficSheet = workbook.getSheetAt(2);
            Map<String, PlanetImpl> planetMap = extractExcelTabToVertexEntity(file, planetsSheet);
            List<DistanceBoundRouteImpl> routes = extractExcelTabToRouteEntity(file, routeSheet, planetMap);
            List<TimeBoundRouteImpl> traffics = extractExcelTabToTrafficEntity(file, trafficSheet, planetMap);
            file.close();

            uploadService.saveUploadDataToDB(new ArrayList<>(planetMap.values()), routes, traffics);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + excelFile.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/RouteUpload/UploadStatus?message="+"Error: Some Exception Need to Handle!";
        }

        return "redirect:/RouteUpload/UploadStatus?message="+"You have successfully uploaded '" + excelFile.getOriginalFilename() + "'";
    }

	private Map<String, PlanetImpl> extractExcelTabToVertexEntity(FileInputStream file, XSSFSheet sheet) throws IOException {
		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();
		Map<String, PlanetImpl> planetsMap = new HashMap<>();
		while(rowIterator.hasNext()) {
			PlanetImpl planet = new PlanetImpl();
		    Row row = rowIterator.next();
		    Iterator<Cell> cellIterator = row.cellIterator();
		    while(cellIterator.hasNext()) {
		        Cell cell = cellIterator.next();
		        switch(cell.getColumnIndex()) {
		            case 0:
		                System.out.println("Node Id:  ===>>>"+ cell.getStringCellValue() + "\t");
		                planet.setId(cell.getStringCellValue());
		                break;
		            case 1:
		                System.out.println("Node Name: ===>>>"+cell.getStringCellValue() + "\t");
		                planet.setName(cell.getStringCellValue());
		                break;
		           
		        }
		    }
		    planetsMap.put(planet.getId(), planet);
		}
		return planetsMap;
	}
	
	private List<DistanceBoundRouteImpl> extractExcelTabToRouteEntity(FileInputStream file, XSSFSheet sheet, Map<String, PlanetImpl> nodesMap) throws IOException {
		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();
		List<DistanceBoundRouteImpl> routes = new ArrayList<>();
		while(rowIterator.hasNext()) {
			DistanceBoundRouteImpl route = new DistanceBoundRouteImpl();
		    Row row = rowIterator.next();
		    Iterator<Cell> cellIterator = row.cellIterator();
		    while(cellIterator.hasNext()) {
		        Cell cell = cellIterator.next();
		        switch(cell.getColumnIndex()) {
		            case 0:
		                System.out.println("Edge Id:  ===>>>"+ (int)cell.getNumericCellValue() + "\t");
		                route.setId(""+(int)cell.getNumericCellValue());
		                break;
		            case 1:
		                System.out.println("Edge Source: ===>>>"+cell.getStringCellValue() + "\t");
		                route.setSource(nodesMap.get(cell.getStringCellValue()));
		                break;
		            case 2:
		                System.out.println("Edge Destination: ===>>>"+cell.getStringCellValue() + "\t");
		                route.setDestination(nodesMap.get(cell.getStringCellValue()));
		                break;
		            case 3:
		                System.out.println("Edge Distance: ===>>>"+cell.getNumericCellValue() + "\t");
		                route.setWeight(cell.getNumericCellValue());
		                break;
		           
		        }
		    }
		    routes.add(route);
		}
		return routes;
	}
	
	private List<TimeBoundRouteImpl> extractExcelTabToTrafficEntity(FileInputStream file, XSSFSheet sheet, Map<String, PlanetImpl> nodesMap) throws IOException {
		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();
		List<TimeBoundRouteImpl> routes = new ArrayList<>();
		while(rowIterator.hasNext()) {
			TimeBoundRouteImpl route = new TimeBoundRouteImpl();
		    Row row = rowIterator.next();
		    Iterator<Cell> cellIterator = row.cellIterator();
		    while(cellIterator.hasNext()) {
		        Cell cell = cellIterator.next();
		        switch(cell.getColumnIndex()) {
		            case 0:
		                System.out.println("Edge Id:  ===>>>"+ (int)cell.getNumericCellValue() + "\t");
		                route.setId(""+(int)cell.getNumericCellValue());
		                break;
		            case 1:
		                System.out.println("Edge Source: ===>>>"+cell.getStringCellValue() + "\t");
		                route.setSource(nodesMap.get(cell.getStringCellValue()));
		                break;
		            case 2:
		                System.out.println("Edge Destination: ===>>>"+cell.getStringCellValue() + "\t");
		                route.setDestination(nodesMap.get(cell.getStringCellValue()));
		                break;
		            case 3:
		                System.out.println("Edge Distance: ===>>>"+cell.getNumericCellValue() + "\t");
		                route.setWeight(cell.getNumericCellValue());
		                break;
		           
		        }
		    }
		    routes.add(route);
		}
		return routes;
	}

    @GetMapping("/UploadStatus")
    public ModelAndView UploadStatus(@RequestParam("message") String message) {
    	ModelAndView model = new ModelAndView();
    	model.addObject("message", message);
    	model.setStatus(HttpStatus.OK);
    	model.setViewName("UploadStatus");
        return model;
    }
}
