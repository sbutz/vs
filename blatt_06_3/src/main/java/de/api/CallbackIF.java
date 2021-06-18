package de.api;

import de.entity.Bericht;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CallbackIF extends Remote {
    void setBericht(Bericht b) throws RemoteException;
}
