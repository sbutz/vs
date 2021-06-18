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
	        Roentgenbild rb = new Roentgenbild();
	        BerichtIF antwort = fe.analysieren(rb);
	        System.out.println(antwort.getDatum());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
