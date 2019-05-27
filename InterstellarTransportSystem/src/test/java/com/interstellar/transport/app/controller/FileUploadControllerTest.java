package com.interstellar.transport.app.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.hsqldb.server.Server;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.WebApplicationContext;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class FileUploadControllerTest {
	
	private MockMvc mockMvc;
	private static final Server sv = new Server();
	private TestDataUtill utill = new TestDataUtill();
	String UPLOADED_FOLDER = System.getProperty("java.io.tmpdir");
	static {
		String[] arr = {};
		sv.main(arr);
	}
	
	@Autowired
	  private WebApplicationContext webApplicationContext;
	
	@Before
	public void prepareMockMvc() {
		this.mockMvc = webAppContextSetup(this.webApplicationContext).build();
	}
	
	@Test
	public void singleFileUpload_Response_OK() throws Exception {
		
		Path path = Paths.get(UPLOADED_FOLDER + "GalaxyMap.xlsx");
		File uploadedFile = new File(path.toString());
		uploadedFile.delete();
		
		URL resource = TransportControllerTest.class.getResource("/GalaxyMap.xlsx");
		File file = Paths.get(resource.toURI()).toFile();
		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		byte[] contents = FileCopyUtils.copyToByteArray(inputStream);
		MockMultipartFile mockMultipartFile = new MockMultipartFile("file","GalaxyMap.xlsx",
	              "text/plain", contents);
		
	      
		mockMvc.perform(MockMvcRequestBuilders.multipart("/RouteUpload/ExcelUpload")
                .file(mockMultipartFile))
            .andExpect(status().is3xxRedirection());
		
		Assert.assertTrue(uploadedFile.exists());
		
	}
	
	@AfterClass
	public static void aa() {
		sv.shutdown();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
