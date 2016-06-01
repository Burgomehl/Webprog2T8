package ue9;

import java.io.File;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class PersonHandler {
	Persons person;
	private int id = 9;

	public PersonHandler() {
		File file = new File("C:/Users/Benjamin Byl/git/Webprog2T8/WebprojektTomcat8/src/ue9/persons.xml");
		System.out.println(System.getenv("user_dir"));
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			person = (Persons) unmarshaller.unmarshal(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Persons getAllPersons() {
		return person;
	}

	public Persons getPersonByParameter(Predicate<Person> func) {
		List<Person> persons = person.getPersons();
		Persons tempPerson = new Persons();
		for (Person p : persons) {
			if (func.test(p)) {
				tempPerson.addPerson(p);
			}
		}
		return tempPerson;
	}

	public List<String> getNamesByParamter(Predicate<Person> func) {
//		List<Person> persons = person.getPersons();
////		StringWriter stringWriter = new StringWriter();
//		for (Person p : persons) {
//			if (func.test(p)) {
////				stringWriter.append(p.getFirstname() + (p.getLastname() != null?"_" + p.getLastname():"") + "\n\r");
//			}
//		}
////		return stringWriter.toString();
		return person.getPersons().stream().map(p->p.getFirstname()).collect(Collectors.toList());
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
