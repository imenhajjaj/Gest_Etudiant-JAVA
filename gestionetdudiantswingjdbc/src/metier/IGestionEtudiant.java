package metier;

import java.util.List;

import dao.entities.Etudiant;

public interface IGestionEtudiant {
	void ajouterEtudiant(Etudiant e);
	List<Etudiant> rechercherParMC(String mc);
	void trierListeEtudiantsParNom();
	List<Etudiant> listeDesEtudiants();
	void deleteEtudiant(int id);
    public Etudiant getEtudiant(int id);
    public void Modifier(Etudiant e);

}
