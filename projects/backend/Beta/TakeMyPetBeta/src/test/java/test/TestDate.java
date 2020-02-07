package test;

import java.util.ArrayList;
import java.util.List;

import business.AdminManager;

public class TestDate {
	
	

	public static void main(String[] args) {
		List<Object> lista = new ArrayList<>();
		AdminManager am = new AdminManager();
		
		lista = am.statUtentiRegistratiDay();
		
		for(Object o : lista)
		System.out.println();
		

	}

}
