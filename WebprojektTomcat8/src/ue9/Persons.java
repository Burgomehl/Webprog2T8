package ue9;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="persons", namespace="")
public class Persons implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "person")
	private List<Person> persons = new ArrayList<>();
	
	public Persons(){
		
	}
	
	public Persons(List<Person> persons){
		this.persons = persons;
	}

	public List<Person> getPersons() {
		return persons;
	}
	
	public void addPerson(Person person){
		persons.add(person);
	}
}
