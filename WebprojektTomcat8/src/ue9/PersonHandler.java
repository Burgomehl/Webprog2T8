package ue9;

import java.io.File;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import ue9.Person;
import ue9.Persons;

public class PersonHandler {
	Persons person;

	public PersonHandler() {
		File file = new File("D:/Uni/WebProg2T8/WebprojektTomcat8/src/ue4/persons.xml");

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			person = (Persons) unmarshaller.unmarshal(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getAllPersons() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
			Marshaller createMarshaller = jaxbContext.createMarshaller();
			StringWriter stringWriter = new StringWriter();
			createMarshaller.marshal(person, stringWriter);
			return stringWriter.toString();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getPersonByParameter(IBool<Person> func) {
		List<Person> persons = person.getPersons();
		StringWriter stringWriter = new StringWriter();
		Persons tempPerson = new Persons();
		for (Person p : persons) {
			if (func.function(p)) {
				tempPerson.addPerson(p);
			}
		}

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
			Marshaller createMarshaller = jaxbContext.createMarshaller();
			createMarshaller.marshal(tempPerson, stringWriter);
			stringWriter.toString();
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return stringWriter.toString();
	}

	public String getNamesByParamter(IBool<Person> func) {
		List<Person> persons = person.getPersons();
		StringWriter stringWriter = new StringWriter();
		for (Person p : persons) {
			if (func.function(p)) {
				stringWriter.append(p.getFirstname()+"_"+p.getLastname()+"\n\r");
			}
		}
		return stringWriter.toString();
	}

}
