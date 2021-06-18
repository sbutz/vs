package de.entity;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface BerichtIF extends Remote {
	public Date getDatum() throws RemoteException;
	public String getDiagnose() throws RemoteException;
	public String getWeiteresVorgehen() throws RemoteException;
}
