package ueu;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class Test {
	private Tester tester;
	@PostConstruct
	private void init(){
		FacesContext ctx = FacesContext.getCurrentInstance();
		Object obj = ctx.getExternalContext().getApplicationMap().get("testy");
		if(obj==null){
			tester = new Tester();
			ctx.getExternalContext().getApplicationMap().put("testy",tester);
		}else{
			tester = (Tester)obj;
		}
	}
}
