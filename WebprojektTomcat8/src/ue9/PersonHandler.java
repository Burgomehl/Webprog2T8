package ue9;

import java.io.File;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import jdk.nashorn.internal.runtime.ECMAException;
import ue9.Person;
import ue9.Persons;

public class PersonHandler {
	Persons person;
	private int id = 9;

	public PersonHandler() {
		File file = new File("D:/Uni/WebProg2T8/WebprojektTomcat8/src/ue9/persons.xml");
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			person = (Persons) unmarshaller.unmarshal(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getAllPersons() {
		StringWriter stringWriter = new StringWriter();
		getXmlContent(stringWriter, person);
		return stringWriter.toString();
	}

	public String getPersonByParameter(Predicate<Person> func) {
		List<Person> persons = person.getPersons();
		StringWriter stringWriter = new StringWriter();
		Persons tempPerson = new Persons();
		for (Person p : persons) {
			if (func.test(p)) {
				tempPerson.addPerson(p);
			}
		}
		getXmlContent(stringWriter, tempPerson);

		return stringWriter.toString();
	}

	public String getNamesByParamter(Predicate<Person> func) {
		List<Person> persons = person.getPersons();
		StringWriter stringWriter = new StringWriter();
		for (Person p : persons) {
			if (func.test(p)) {
				stringWriter.append(p.getFirstname() + (p.getLastname() != null?"_" + p.getLastname():"") + "\n\r");
			}
		}
		return stringWriter.toString();
	}
	
	public void addPerson(String firstname, String name, String residence, String childof) throws Exception{
		Person newPerson = new Person("p-"+id, firstname, name, residence, null);
		Person tempParent = null;
		for(Person p : person.getPersons()){
			if(p.getFirstname().equals(childof)){
				if(tempParent==null){
					tempParent = p;
				}else{
					throw new Exception("Parentname exists more then one time");
				}
			}
			if(tempParent == null){
				throw new Exception("Parent not found");
			}
		}
		List<Person> children = tempParent.getChildren();
		children.add(newPerson);
		tempParent.setChildren(children);
	}
	
	public boolean deletePerson(String firstname){
		for(Person p: person.getPersons()){
			if(p.getFirstname().equals(firstname)){
				person.getPersons().remove(p);
				return true;
			}
		}
		return false;
	}

	private void getXmlContent(StringWriter stringWriter, Persons tempPerson) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
			Marshaller createMarshaller = jaxbContext.createMarshaller();
			createMarshaller.marshal(tempPerson, stringWriter);
			stringWriter.toString();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
