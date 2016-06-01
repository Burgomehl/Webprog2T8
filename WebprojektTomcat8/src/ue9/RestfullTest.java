package ue9;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/Test")
public class RestfullTest {
	PersonHandler personHandler;
	
	public RestfullTest() {
		personHandler = new PersonHandler();
	}
	@GET
	@Path("/T")
	@Produces({"text/xml","application/json"})
	public Persons test() {
		return personHandler.getAllPersons();
	}
	@GET
	@Path("/Get/vorname/{parameter}")
	@Produces({"text/xml","application/json"})
	public Persons get(@PathParam("parameter") String parameter) {
		return personHandler.getPersonByParameter((person)->parameter.equals(person.getFirstname()));
	}
	
	@GET
	@Path("/Get/name/{parameter}")
	@Produces({"text/xml","application/json"})
	public Persons get2(@PathParam("parameter") String parameter) {
		return personHandler.getPersonByParameter((person)->parameter.equals(person.getLastname()));
	}
	
	@GET
	@Path("/Get/ort/{parameter}")
	@Produces({"text/xml","application/json"})
	public Persons get3(@PathParam("parameter") String parameter) {
		return personHandler.getPersonByParameter((person)->parameter.equals(person.getResidence()));
	}
	
	@GET
	@Path("/Get/namebyort/{parameter}")
	@Produces({"text/xml","application/json"})
	public Response get4(@PathParam("parameter") String parameter) {
		return Response.ok(personHandler.getNamesByParamter((person)->parameter.equals(person.getResidence()))).build();
	}
	
	@GET
	@Path("/Get/namen")
	@Produces({"text/plain"})
	public Response get5(@PathParam("parameter") String parameter) {
		return Response.ok(personHandler.getNamesByParamter((person)->true)).build();
	}
	
	@PUT
	@Path("/Put/{firstname}/{name}/{residence}/{childof}")
	@Consumes( MediaType.TEXT_PLAIN )
	public String put(@PathParam("firstname") String firstname, //
			@PathParam("name")String name, //
			@PathParam("residence")String residence,
			@PathParam("childof")String childof){
		try {
			personHandler.addPerson(firstname, name, residence, childof);
		} catch (Exception e) {
			System.out.println("Fehler");
			return e.getMessage();
		}
		System.out.println("ok");
		return "ok";
	}
	
	@DELETE
	@Path("{firstname}")
	public void delete(@PathParam("firstname") String firstname){
		System.out.println("Person wurde gelöscht: "+personHandler.deletePerson(firstname));
	}
}
