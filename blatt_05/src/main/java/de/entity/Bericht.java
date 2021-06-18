package de.entity;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Bericht implements Serializable {
	
    private Date datum;
    private String diagnose;
    private String weiteresVorgehen;
    
    public Bericht() {
    	this.datum = new Date();
    }
    
    public Bericht(String diagnose, String weiteresVorgehen) {
    	this.diagnose = diagnose;
    	this.weiteresVorgehen = weiteresVorgehen;
    }
    
    public Bericht(Date date, String diagnose,
    		String weiteresVorgehen) {
    	this.datum = date;
    	this.diagnose = diagnose;
    	this.weiteresVorgehen = weiteresVorgehen;
    }

	public Date getDate() {
		return datum;
	}

	public void setDate(Date date) {
		this.datum = date;
	}

	public String getDiagnose() {
		return diagnose;
	}

	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}

	public String getWeiteresVorgehen() {
		return weiteresVorgehen;
	}

	public void setWeiteresVorgehen(String weiteresVorgehen) {
		this.weiteresVorgehen = weiteresVorgehen;
	}
	
	public String toString() {
		return "Bericht{" +
				"datum=" + this.datum +
				", diagnose=" + this.diagnose +
				", weiteresVorgehen=" + this.weiteresVorgehen +
				"}";
	}
}
