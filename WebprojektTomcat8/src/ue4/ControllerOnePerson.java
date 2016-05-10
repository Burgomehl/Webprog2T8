package ue4;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class ControllerOnePerson {
	private PersonsToWork personsToWork;
	private Person person;
	@ManagedProperty("#{param.firstname}")
	private String firstname;
	
	public ControllerOnePerson() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	private void init() {
		FacesContext context = FacesContext.getCurrentInstance();
		Object object = context.getExternalContext().getApplicationMap().get("persons");
		if (object == null) {
			personsToWork = ProjektFactory.getPersonsData();
			context.getExternalContext().getApplicationMap().put("persons", personsToWork);
		} else {
			personsToWork = (PersonsToWork) object;
		}
		person = personsToWork.getPersonByName(firstname);
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
}
