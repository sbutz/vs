package de.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import de.entity.*;

class Main
{

    public static void main(String args[])
    {
        System.out.println("hi");
        
        Registry r;
		try {
			r = LocateRegistry.getRegistry("localhost", 1099);
	        FrueherkennungIF fe = (FrueherkennungIF) r.lookup("Frueherkennung");
	        Bericht antwort = fe.analysieren(null);
	        System.out.println(antwort);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
