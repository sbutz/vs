package de.api;

import de.entity.Bericht;
import de.entity.Roentgenbild;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FrueherkennungIF extends Remote {

	public void analysieren(Roentgenbild rb, CallbackIF ref) throws RemoteException;
}
