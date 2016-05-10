package ue4;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class ControllerAddPerson {
	private String firstname = null;
	private String lastname = null;
	private String residence = null;
	private String children;
	private PersonsToWork personsToWork;
	private List<Person> persons;

	public ControllerAddPerson() {
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
		persons = personsToWork.getPersons();

	}

	public String validateFirstname(FacesContext ctx, UIComponent toValidate, Object object) {
		final String name = (String) object;
		Person personByName = null;
		try {
			personByName = personsToWork.getPersonByName(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (personByName != null) {
			((UIInput) (toValidate)).setValid(false);
			FacesMessage eMessage = new FacesMessage("Person existiert schon");
			eMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			ctx.addMessage(toValidate.getClientId(ctx), eMessage);
			System.out.println("Error");
		}
		System.out.println("ok");
		return null;
	}

	public String validateChildren(FacesContext ctx, UIComponent toValidate, Object object) {
		final String name = (String) object;
		String[] split = name.split(",");
		for (String string : split) {
			Person personByName = null;
			try {
				personByName = personsToWork.getPersonByName(string);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (personByName == null) {
				((UIInput) (toValidate)).setValid(false);
				FacesMessage eMessage = new FacesMessage("Kind existiert nicht");
				eMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				ctx.addMessage(toValidate.getClientId(ctx), eMessage);
				System.out.println("Error");
				return null;
			}
		}

		System.out.println("ok");
		return null;
	}

	public String addUser() {
		String[] split = children.split(",");
		List<Person> childrenL = new ArrayList<>();
		for (String string : split) {

			try {
				Person personByName = personsToWork.getPersonByName(string);
				if (personByName != null) {
					childrenL.add(personByName);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Person p = new Person("", firstname, lastname, residence, childrenL);
		persons.add(p);
		System.out.println("hinzugefügt");
		return "Startseite";
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getResidence() {
		return residence;
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}

	public String getChildren() {
		return children;
	}

	public void setChildren(String children) {
		this.children = children;
	}

}
