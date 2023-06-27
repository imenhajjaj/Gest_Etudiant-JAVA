package dao.entities;

import java.util.Objects;

public class Etudiant implements Comparable<Etudiant> {
	@Override
	public String toString() {
		return "Etudiant [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe + ", filiere=" + filiere
				+ "]";
	}
	private int id;
	private String nom;
	private String prenom;
	private String sexe;
	private String filiere;
	//private static int  nb;
	
	public Etudiant(int id, String nom, String prenom,  String filiere,String sexe) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.filiere = filiere;
	}
	public Etudiant(String nom, String prenom, String filiere,String sexe) {
		super();
		//this.id=++nb;
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.filiere = filiere;
	}
	public Etudiant() {
		//this.id=++nb;
		}
	public int getId() {
		return id;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getFiliere() {
		return filiere;
	}
	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}
	@Override
	public int compareTo(Etudiant o) {
		// TODO Auto-generated method stub
		return this.nom.compareTo(nom);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Etudiant other = (Etudiant) obj;
		return id == other.id;
	}
	
	
	
	
	
	

}
