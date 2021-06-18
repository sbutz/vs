package de.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import de.api.CallbackIF;
import de.api.FrueherkennungIF;
import de.entity.*;

class Main
{

    public static void main(String[] args)
    {
        System.out.println("hi");
        
        Registry r;
		try {
			r = LocateRegistry.getRegistry("localhost", 1099);
	        FrueherkennungIF fe = (FrueherkennungIF) r.lookup("Frueherkennung");
	        Roentgenbild rb = new Roentgenbild();
	        CallbackIF c = new CallbackIF() {
				@Override
				public void setBericht(Bericht b) throws RemoteException {
					System.out.println(b);
				}
			};
	        CallbackIF stub = (CallbackIF) UnicastRemoteObject.exportObject(c, 0);
	        fe.analysieren(rb, stub);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
