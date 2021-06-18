package de.entity;

import java.rmi.RemoteException;
import java.util.Date;

public class Bericht implements BerichtIF {
	
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

	public Date getDatum() throws RemoteException {
		return datum;
	}

	public void setDatum(Date date) {
		this.datum = date;
	}

	public String getDiagnose() throws RemoteException {
		return diagnose;
	}

	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}

	public String getWeiteresVorgehen() throws RemoteException {
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
