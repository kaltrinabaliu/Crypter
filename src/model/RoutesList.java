package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class RoutesList implements Serializable {
	
	private RouteNode firstRouteNode;
	
	public RoutesList() {
		firstRouteNode=null;
	}

	public void add(String route, boolean type, String text){
		LocalDateTime time= LocalDateTime.now();
		RouteNode node= new RouteNode(route,time, type, text);
		if(firstRouteNode!=null) {
			RouteNode aux = firstRouteNode;
			RouteNode current = searchLast(aux);
			current.setNext(node);
			current.getNext().setPrev(current);
		}else {
			firstRouteNode=node;
		}
	}
	
	private RouteNode searchLast(RouteNode current) {
		if(current.getNext()!=null) {
			current = current.getNext();
			return searchLast(current);
		}else {
			return current;
		}
	}
	
	public String search(String route) {
		if(firstRouteNode!=null) {
			return search(firstRouteNode, route);
		}else {
			return null;
		}
	}
	
	public String searchRoute(String data) {
		if(firstRouteNode!=null) {
			return searchRoute(firstRouteNode, data);
		}else {
			return null;
		}
	}
	
	private String search(RouteNode current, String route) {
		if(current.getRoute().equals(route)) {
			return current.getText();
		}else{
			if(current.getNext()!=null)
			return search(current.getNext(), route);
		}
		return null;
	}
	
	private String searchRoute(RouteNode current, String data) {
		if(current.toString().equals(data)) {
			return current.getRoute();
		}else{
			if(current.getNext()!=null)
			return searchRoute(current.getNext(), data);
		}
		return null;
	}
	
	public RouteNode delete(String route) {
		if(firstRouteNode!=null) {
			return delete(firstRouteNode, route);
		}else {
			return null;
		}
	}
	
	private RouteNode delete(RouteNode current, String route) {
		if(current.getRoute().equals(route) ) {

			//////When current is the only node 
			if(current.getNext()==null && current.getPrev()==null) {
				firstRouteNode=null;
				
			/////When current is the first node	
			}else if(current.getPrev()==null) {
				current.getNext().setPrev(null);
				firstRouteNode = firstRouteNode.getNext();
				
			/////When current is the last one 
			}else if(current.getNext()==null){
				current.getPrev().setNext(null);
				
			/////When current is in the middle
			}else {
				current.getPrev().setNext(current.getNext());
				current.getNext().setPrev(current.getPrev());
			}
			
			return current;
		}else {
			if(current.getNext()!=null)
			return delete(current.getNext(), route);
		}
		return null;
	}
	
	
	public ArrayList <String> getRoutes(boolean type, String attribute) {
		if(firstRouteNode!=null) {
			ArrayList <String> list= new ArrayList <String>();
			if(attribute == null) {
				return getRoutes(firstRouteNode, type, list, attribute);
			}else if(attribute.equals(RouteManager.ATTRIBUTE_SORT_1)) {
				return getRoutes(firstRouteNode, type, list, attribute);
			}else if(attribute.equals(RouteManager.ATTRIBUTE_SORT_2)) {
				return getRoutes(firstRouteNode, type, list, attribute);
			}
		}
		
		return null;
	}
	
	private ArrayList <String> getRoutes(RouteNode current, boolean type, ArrayList <String> list, String attribute){
		if(current.getType()==type) {
			if(attribute==null) {
				list.add(current.toString());
			}else if(attribute.equals(RouteManager.ATTRIBUTE_SORT_1)) {
				list.add(current.getName());
			}else if(attribute.equals(RouteManager.ATTRIBUTE_SORT_2)) {
				list.add(current.getDate().toString());
			}
		}
		
		if(current.getNext()!=null) 
			return getRoutes(current.getNext(), type,list, attribute);
		else
			return list;
	}
	
	public ArrayList<RouteNode> getRoute(boolean type){
		
		if(firstRouteNode!=null) {
			ArrayList <RouteNode> list= new ArrayList <RouteNode>();
			return getRoutes(firstRouteNode, type, list);
		}
		
		return null;
	}
	
	private ArrayList <RouteNode> getRoutes(RouteNode current, boolean type, ArrayList <RouteNode> list){
		if(current.getType()==type) {
			list.add(current);
		}
		
		if(current.getNext()!=null) 
			return getRoutes(current.getNext(), type,list);
		else
			return list;
	}
		
	
	public RouteNode getFirst() {
		return firstRouteNode;
	}
}
