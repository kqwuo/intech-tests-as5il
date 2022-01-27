package com.intech.comptabilite.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ecriture_comptable")
public class EcritureComptable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "journalCode")
	@NotNull
	private JournalComptable journal;

	@Pattern(regexp = "^([A-Z]{2})-([0-9]{4})/([0-9]{5})")
	private String reference;

	@NotNull
	private Date date;

	@NotNull
	@Size(min = 1, max = 200)
	private String libelle;

	@OneToMany(
			cascade = CascadeType.ALL, 
			orphanRemoval = true, 
			fetch = FetchType.EAGER)
	@JoinColumn(name = "ecritureId")
	@Valid
	@Size(min = 2)
	private final List<LigneEcritureComptable> listLigneEcriture = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer pId) {
		id = pId;
	}

	public JournalComptable getJournal() {
		return journal;
	}

	public void setJournal(JournalComptable pJournal) {
		journal = pJournal;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String pReference) {
		reference = pReference;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date pDate) {
		date = pDate;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String pLibelle) {
		libelle = pLibelle;
	}

	public List<LigneEcritureComptable> getListLigneEcriture() {
		return listLigneEcriture;
	}
}
