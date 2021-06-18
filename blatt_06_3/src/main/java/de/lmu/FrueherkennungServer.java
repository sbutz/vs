package de.lmu;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import de.api.CallbackIF;
import de.api.FrueherkennungIF;
import de.entity.*;

class FrueherkennungServer implements FrueherkennungIF
{

    public static void main(String args[])
    {
        System.out.println("hi server");
        
        FrueherkennungIF fe = new FrueherkennungServer();
        try {
			FrueherkennungIF stub = (FrueherkennungIF) UnicastRemoteObject.exportObject(fe, 0);
			Registry r = LocateRegistry.createRegistry(1099);
			r.bind("Frueherkennung", stub);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
    }
    
    @Override
	public void analysieren(Roentgenbild rb, CallbackIF ref) throws RemoteException {
		// TODO Auto-generated method stub
		Bericht b = new Bericht();
		b.setDate(rb.getAufnahmeVom());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ref.setBericht(b);
	}
}
