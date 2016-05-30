package ue9;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/Test")
public class RestfullTest {
	PersonHandler personHandler;
	
	public RestfullTest() {
		personHandler = new PersonHandler();
	}
	@GET
	@Path("/T")
	@Produces({"text/xml","application/json"})
	public String test() {
		
		return personHandler.getAllPersons();
	}
	@GET
	@Path("/Get/vorname/{parameter}")
	@Produces({"text/xml","application/json"})
	public String get(@PathParam("parameter") String parameter) {
		return personHandler.getPersonByParameter((person)->parameter.equals(person.getFirstname()));
	}
	
	@GET
	@Path("/Get/name/{parameter}")
	@Produces({"text/xml","application/json"})
	public String get2(@PathParam("parameter") String parameter) {
		return personHandler.getPersonByParameter((person)->parameter.equals(person.getLastname()));
	}
	
	@GET
	@Path("/Get/ort/{parameter}")
	@Produces({"text/xml","application/json"})
	public String get3(@PathParam("parameter") String parameter) {
		return personHandler.getPersonByParameter((person)->parameter.equals(person.getResidence()));
	}
	
	@GET
	@Path("/Get/namebyort/{parameter}")
	@Produces({"text/xml","application/json"})
	public String get4(@PathParam("parameter") String parameter) {
		return personHandler.getNamesByParamter((person)->parameter.equals(person.getResidence()));
	}
	
	@GET
	@Path("/Get/namen")
	@Produces({"text/plain"})
	public String get5(@PathParam("parameter") String parameter) {
		return personHandler.getNamesByParamter((person)->true);
	}
}
