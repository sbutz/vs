package de.entity;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Date;

@SuppressWarnings("serial")
public class Roentgenbild implements RoentgenbildIF, Serializable {
	
	private Date aufnahmeVom;
	private transient String patientenName;
	private byte[] rawData;
	
	public Roentgenbild() {
		this.aufnahmeVom = new Date();
		this.rawData = new byte[] {};
	}
	
	public Roentgenbild(Date aufnahmeVom, String patientenName,
			byte[] rawData) {
		this.aufnahmeVom = aufnahmeVom;
		this.patientenName = patientenName;
		this.rawData = rawData;
	}
	
	public Date getAufnahmeVom() throws RemoteException {
		return aufnahmeVom;
	}
	
	public void setAufnahmeVom(Date aufnahmeVom) {
		this.aufnahmeVom = aufnahmeVom;
	}
	
	public String getPatientenName() throws RemoteException {
		return patientenName;
	}
	
	public void setPatientenName(String patientenName) {
		this.patientenName = patientenName;
	}
	
	public byte[] getRawData() throws RemoteException {
		return rawData;
	}
	
	public void setRawData(byte[] rawData) {
		this.rawData = rawData;
	}
	
	public String toString() {
		return "Roentgenbild{" +
				"aufnahmeVom=" + this.aufnahmeVom +
				", patientenName=" + this.patientenName +
				"}";
	}
}
