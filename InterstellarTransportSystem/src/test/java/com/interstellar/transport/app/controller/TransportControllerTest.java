package com.interstellar.transport.app.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hsqldb.server.Server;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.interstellar.transport.app.entity.DistanceBoundRouteImpl;
import com.interstellar.transport.app.entity.PlanetImpl;
import com.interstellar.transport.app.entity.TimeBoundRouteImpl;
import com.interstellar.transport.app.service.UploadService;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TransportControllerTest {
	private MockMvc mockMvc;
	private static final Server sv = new Server();
	private TestDataUtill utill = new TestDataUtill();
	static {
		//TestDataUtill.startHSQLDB();
	}
	
	@Autowired
	  private WebApplicationContext webApplicationContext;
	
	@Autowired
	UploadService uploadService;
	
	@Before
	  public void prepareMockMvc() {
		
	    this.mockMvc = webAppContextSetup(this.webApplicationContext).build();
	    
	    try {
	    	URL resource = TransportControllerTest.class.getResource("/GalaxyMap.xlsx");
	    	FileInputStream file = new FileInputStream(Paths.get(resource.toURI()).toFile());
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet planetsSheet = workbook.getSheetAt(0);
            
            XSSFSheet routeSheet = workbook.getSheetAt(1);
            XSSFSheet trafficSheet = workbook.getSheetAt(2);
            Map<String, PlanetImpl> planetMap = utill.extractExcelTabToVertexEntity(file, planetsSheet);
            List<DistanceBoundRouteImpl> routes = utill.extractExcelTabToRouteEntity(file, routeSheet, planetMap);
            List<TimeBoundRouteImpl> traffics = utill.extractExcelTabToTrafficEntity(file, trafficSheet, planetMap);
            file.close();

            uploadService.saveUploadDataToDB(new ArrayList<>(planetMap.values()), routes, traffics);
            
	    } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
	    	
	    }
	    
	  }
	
	@Test
	public void showShortestRoute_Response_OK() throws Exception {
	    this.mockMvc.perform(post("/Transport/shortestRoute").contentType(MediaType.APPLICATION_JSON)
	  .content("{\"source\":\"A\",\"destination\":\"F\"}")).andExpect(status().isOk());
	
	    
	}
	
	@Test
	public void showShortestRoute_Response() throws Exception {
	    this.mockMvc.perform(post("/Transport/shortestRoute").contentType(MediaType.APPLICATION_JSON)
	  .content("{\"source\":\"A\",\"destination\":\"I\"}")).andExpect(content().string("Earth==>Moon==>Mars"));
	
	    
	}
	
	@AfterClass
	public static void tearDown() {
		TestDataUtill.stopHSQLDB();
	}
}
