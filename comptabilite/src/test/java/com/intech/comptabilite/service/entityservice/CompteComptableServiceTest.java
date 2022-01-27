package com.intech.comptabilite.service.entityservice;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.intech.comptabilite.model.CompteComptable;
import com.intech.comptabilite.repositories.CompteComptableRepository;

@SpringBootTest
public class CompteComptableServiceTest {
	
	@MockBean
	private CompteComptableRepository repository;

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
	
	@Test
	public void getListCompteComptableTest() {
		ArrayList<CompteComptable> list = new ArrayList<CompteComptable>();
		list.add(new CompteComptable(1, "nb1"));
		list.add(new CompteComptable(2, "nb2"));
		list.add(new CompteComptable(3, "nb3"));
		list.add(new CompteComptable(4, "nb4"));
		when(repository.findAll()).thenReturn(list);
		
		List<CompteComptable> result = compteComptableService.getListCompteComptable();
		
		Assertions.assertTrue(result.size() == list.size());
		for(int i = 0; i < result.size(); i++) {
			Assertions.assertTrue(result.get(i).getLibelle() == list.get(i).getLibelle());
			Assertions.assertTrue(result.get(i).getNumero() == list.get(i).getNumero());
		}
		
		verify(repository).findAll();
	}
}
