package ueu;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="Test")
@ViewScoped
public class Test {
	private Tester tester;
	private String text;
	
	public Test() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	private void init() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		Object obj = ctx.getExternalContext().getApplicationMap().get("testy");
		if (obj == null) {
			tester = new Tester();
			ctx.getExternalContext().getApplicationMap().put("testy", tester);
		} else {
			tester = (Tester) obj;
		}
		text = tester.getTest();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
