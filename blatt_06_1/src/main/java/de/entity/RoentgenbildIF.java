package de.entity;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface RoentgenbildIF extends Remote {
	public Date getAufnahmeVom() throws RemoteException;
	public String getPatientenName() throws RemoteException;
	public byte[] getRawData() throws RemoteException;
}
