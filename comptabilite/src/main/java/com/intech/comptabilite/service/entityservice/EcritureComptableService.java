package com.intech.comptabilite.service.entityservice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intech.comptabilite.model.EcritureComptable;
import com.intech.comptabilite.model.LigneEcritureComptable;
import com.intech.comptabilite.repositories.EcritureComptableRepository;
import com.intech.comptabilite.service.exceptions.NotFoundException;

@Service
public class EcritureComptableService {

	@Autowired
	private EcritureComptableRepository repository;

	public EcritureComptable insertEcritureComptable(EcritureComptable ecriture) {
		return repository.save(ecriture);
	}

	public EcritureComptable updateEcritureComptable(EcritureComptable ecriture) {
		return repository.save(ecriture);
	}

	public void deleteEcritureComptable(Integer ecritureId) {
		repository.deleteById(ecritureId);
	}

	public List<EcritureComptable> getListEcritureComptable() {
		List<EcritureComptable> result = new ArrayList<EcritureComptable>();
		repository.findAll().forEach(result::add);
		return result;
	}

	public EcritureComptable getEcritureComptableByRef(String reference) throws NotFoundException {
		Optional<EcritureComptable> result = repository.getByReference(reference);
		if (result.isEmpty()) {
			throw new NotFoundException();
		} else {
			return result.get();
		}
	}

	/**
	 * Calcul et renvoie le total des montants au débit des lignes d'écriture
	 *
	 * @return {@link BigDecimal}, {@link BigDecimal#ZERO} si aucun montant au débit
	 */
	public BigDecimal getTotalDebit(EcritureComptable ecriture) {
		BigDecimal vRetour = BigDecimal.ZERO;
		for (LigneEcritureComptable vLigneEcritureComptable : ecriture.getListLigneEcriture()) {
			if (vLigneEcritureComptable.getDebit() != null) {
				vRetour = vRetour.add(vLigneEcritureComptable.getDebit());
			}
		}
		return vRetour;
	}

	/**
	 * Calcul et renvoie le total des montants au crédit des lignes d'écriture
	 *
	 * @return {@link BigDecimal}, {@link BigDecimal#ZERO} si aucun montant au
	 *         crédit
	 */
	public BigDecimal getTotalCredit(EcritureComptable ecriture) {
		BigDecimal vRetour = BigDecimal.ZERO;
		for (LigneEcritureComptable vLigneEcritureComptable : ecriture.getListLigneEcriture()) {
			if (vLigneEcritureComptable.getCredit() != null) {
				vRetour = vRetour.add(vLigneEcritureComptable.getCredit());
			}
		}
		return vRetour;
	}

	/**
	 * Renvoie si l'écriture est équilibrée (TotalDebit = TotalCrédit)
	 * 
	 * @return boolean
	 */
	public boolean isEquilibree(EcritureComptable ecriture) {
		boolean vRetour = this.getTotalDebit(ecriture).compareTo(getTotalCredit(ecriture)) == 0;
		return vRetour;
	}

}
