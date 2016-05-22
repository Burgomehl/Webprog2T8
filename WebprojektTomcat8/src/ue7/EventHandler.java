package ue7;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class EventHandler {
	private String visible ="hidden";

	public void setVisible(String visible) {
		this.visible = visible;
	}

	public String getVisible() {
		return visible.equals("hidden")?"visible":"hidden";
	}
	
	
	
}
