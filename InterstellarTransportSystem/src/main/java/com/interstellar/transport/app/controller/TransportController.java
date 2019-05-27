package com.interstellar.transport.app.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.interstellar.transport.app.entity.GalaxyImpl;
import com.interstellar.transport.app.service.TransportService;
import com.interstellar.transport.app.vo.TransportVO;
import com.interstellar.transport.core.Galaxy;
import com.interstellar.transport.core.Planet;
import com.interstellar.transport.core.routefinder.RouteFinder;
import com.interstellar.transport.core.routefinder.TransportRouteFinder;

@Controller
@RequestMapping("/Transport")
public class TransportController {
	@Autowired
	TransportService routeService;
	
	@PostMapping(path = "/shortestRoute", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String showShortestRoute(@RequestBody @Valid TransportVO transportVo){
		Galaxy galaxy = new GalaxyImpl();
		//associate all route to respective source
		routeService.getAllRoutes().forEach(route ->{
			route.getSource().addNeighbourPlanet(route);
		});
		// add planets to galaxy
		routeService.getAllPlanet().forEach(planet -> {
			galaxy.addPlanet(planet);
		});
		
		Planet source = routeService.getPlanetById(transportVo.getSource());
		Planet target = routeService.getPlanetById(transportVo.getDestination());
		RouteFinder pathFinder = new TransportRouteFinder(galaxy);
		List<Planet> shortestRoute = pathFinder.getShortestRoute(source, target);
		
		StringBuilder stringRoute = new StringBuilder();
		shortestRoute.forEach(planet -> {
			if(stringRoute.length() > 0)
				stringRoute.append("==>");
			stringRoute.append(planet.getName());
		});
        return stringRoute.toString();
    }
	
	@PostMapping(path = "/fastestRoute", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String showFastestRoute(@RequestBody @Valid TransportVO transportVo){
		Galaxy galaxy = new GalaxyImpl();
		//associate all route to respective source
		routeService.getAllTraffic().forEach(route ->{
			route.getSource().addNeighbourPlanet(route);
		});
		// add planets to galaxy
		routeService.getAllPlanet().forEach(planet -> {
			galaxy.addPlanet(planet);
		});
		
		Planet source = routeService.getPlanetById(transportVo.getSource());
		Planet target = routeService.getPlanetById(transportVo.getDestination());
		RouteFinder pathFinder = new TransportRouteFinder(galaxy);
		List<Planet> shortestRoute = pathFinder.getShortestRoute(source, target);
		
		StringBuilder stringRoute = new StringBuilder();
		shortestRoute.forEach(planet -> {
			if(stringRoute.length() > 0)
				stringRoute.append("==>");
			stringRoute.append(planet.getName()+"["+planet.getId()+"]");
		});
		stringRoute.append("==>"+target.getName()+"["+target.getId()+"]");
        return stringRoute.toString();
    }
	
	@GetMapping(path = "/", produces = "text/html")
    public String home(Map<String, Object> model) {
        model.put("message", "Please enter travell details  !!");
        return "Index";
    }
}
