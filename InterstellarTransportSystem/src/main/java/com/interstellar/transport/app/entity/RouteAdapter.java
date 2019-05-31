package com.interstellar.transport.app.entity;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class RouteAdapter extends XmlAdapter<AdaptedRoute, AbstractRoute> {
	 
	   public AdaptedRoute marshal(AbstractRoute route) throws Exception {
		   AdaptedRoute common = new AdaptedRoute();
		   common.setWeight(route.getWeight());
		   populateRoute(route, common);
	      return common;
	   }
	 
	   public AbstractRoute unmarshal(AdaptedRoute route) throws Exception {
		   if("distance".equals(route.getType())) {
			   DistanceBoundRouteImpl distancedRoute = new DistanceBoundRouteImpl();
			   populateRoute(route, distancedRoute);
			   distancedRoute.setWeight(route.getWeight());
			   return distancedRoute;
		   }else if("traffic".equals(route.getType())) {
			   TimeBoundRouteImpl trafficRoute = new TimeBoundRouteImpl();
			   populateRoute(route, trafficRoute);
			   trafficRoute.setWeight(route.getWeight());
			   return trafficRoute;
		   }else {
			   throw new RuntimeException("Unknown route type: "+route.getType());
		   }
	   }

		private void populateRoute(AbstractRoute route, AbstractRoute targetRoute) {
			targetRoute.setDestination((PlanetImpl)route.getDestination());
			   targetRoute.setSource((PlanetImpl)route.getSource());
			   targetRoute.setId(route.getId());
			   targetRoute.setType(route.getType());
		}
		
	 
	}
