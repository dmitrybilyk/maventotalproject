package com.learn.rmi;

import java.rmi.*;

public interface Hello extends java.rmi.Remote
		{
			String sayHello() throws RemoteException;
		}