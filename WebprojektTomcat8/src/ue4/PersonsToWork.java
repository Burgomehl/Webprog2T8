package ue4;

import java.util.List;

public class PersonsToWork {
	private Persons person;
	
	public PersonsToWork() {
//		File file = new File("persons.xml");
//
//		try {
//			JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
//			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//			data = (Persons) unmarshaller.unmarshal(file);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println(data);
		person = new Persons();
		Person e = new Person("p1", "Peter", "Lustig", "Schillerstraße", null);
		person.getPersons().add(e);
	}
	
	public Person getPersonByName(String firstname){
		return null;//person.getPersons().stream().filter(a -> firstname.equals(a.getFirstname())).findFirst().get();
	}
	
	public List<Person> getPersons(){
		person = new Persons();
		Person e = new Person("p1", "Peter", "Lustig", "Schillerstraße", null);
		person.getPersons().add(e);
		return person.getPersons();
	}
}
