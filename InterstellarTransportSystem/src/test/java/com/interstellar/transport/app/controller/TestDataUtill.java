package com.interstellar.transport.app.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.interstellar.transport.app.entity.DistanceBoundRouteImpl;
import com.interstellar.transport.app.entity.PlanetImpl;
import com.interstellar.transport.app.entity.TimeBoundRouteImpl;

public class TestDataUtill {

	public Map<String, PlanetImpl> extractExcelTabToVertexEntity(FileInputStream file, XSSFSheet sheet) throws IOException {
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
	
	public List<DistanceBoundRouteImpl> extractExcelTabToRouteEntity(FileInputStream file, XSSFSheet sheet, Map<String, PlanetImpl> nodesMap) throws IOException {
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
	
	public List<TimeBoundRouteImpl> extractExcelTabToTrafficEntity(FileInputStream file, XSSFSheet sheet, Map<String, PlanetImpl> nodesMap) throws IOException {
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
}
