package ue4;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class Controller {
	PersonsToWork personsToWork;
	private List<Person> persons;

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
		persons = personsToWork.getPersons();
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

}
