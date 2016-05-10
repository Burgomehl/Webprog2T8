package ue4;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class PersonsToWork {
	private Persons person;

	public PersonsToWork() {
		File file = new File("D:/Uni/WebProg2T8/WebprojektTomcat8/src/ue4/persons.xml");

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			person = (Persons) unmarshaller.unmarshal(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// person = new Persons();
		// Person e = new Person("p1", "Peter", "Lustig", "Schillerstraﬂe",
		// null);
		// person.getPersons().add(e);
	}

	public Person getPersonByName(String firstname) {
		try {
			return person.getPersons().stream().filter(a -> firstname.equals(a.getFirstname())).findFirst().get();
		} catch (Exception e) {
			System.out.println("Niemanden gefunden");
			return null;
		}
	}

	public List<Person> getPersons() {
		// person = new Persons();
		// Person e = new Person("p1", "Peter", "Lustig", "Schillerstraﬂe",
		// null);
		// List<Person> persons = new ArrayList<Person>();
		// persons.add(e);
		// person.setPersons(persons);
		return person.getPersons();
	}

}
