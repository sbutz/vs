package de.entity;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FrueherkennungIF extends Remote {

	public Bericht analysieren(Roentgenbild rb) throws RemoteException;
}
