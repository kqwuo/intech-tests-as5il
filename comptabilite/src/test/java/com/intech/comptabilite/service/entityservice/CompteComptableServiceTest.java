package com.intech.comptabilite.service.entityservice;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.intech.comptabilite.model.CompteComptable;

@SpringBootTest
public class CompteComptableServiceTest {

	@Autowired
	private CompteComptableService compteComptableService;
	
	@Test
	public void getByNumeroTest() {
		ArrayList<CompteComptable> list = new ArrayList<CompteComptable>();
		CompteComptable elmToFind = new CompteComptable(3, "nb3");
		list.add(new CompteComptable(1, "nb1"));
		list.add(new CompteComptable(2, "nb2"));
		list.add(elmToFind);
		list.add(new CompteComptable(4, "nb4"));
		
		CompteComptable result = compteComptableService.getByNumero(list, elmToFind.getNumero());
		
		Assertions.assertEquals(result, elmToFind);
		
	}
}
