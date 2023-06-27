package metier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import dao.entities.Etudiant;

public class GestionEtudiant implements IGestionEtudiant {
	
	private List<Etudiant> liste=new ArrayList<>();

	@Override
	public void ajouterEtudiant(Etudiant e) {
		// TODO Auto-generated method stub
		 liste.add(e);	
	}

	@Override
	public List<Etudiant> rechercherParMC(String mc) {
		List<Etudiant>l=new ArrayList<>();
		for(Etudiant e:liste)
			if(e.getNom().contains(mc)||e.getPrenom().contains(mc))
				l.add(e);
		return l;
	}

	@Override
	public void trierListeEtudiantsParNom() {
		Collections.sort(liste);
		
	}

	@Override
	public List<Etudiant> listeDesEtudiants() {
		// TODO Auto-generated method stub
		return liste;
	}

	@Override
	public void deleteEtudiant(int id) {
		
		for(Iterator<Etudiant>i=liste.iterator();i.hasNext();)
		{
			Etudiant e=i.next();
			if(e.getId()==id)
				i.remove();
		}
		
	}

	@Override
	public Etudiant getEtudiant(int id) {
		for(Etudiant e:liste)
			if(e.getId()==id)
				return e;
		return null;
	}

	@Override
	public void Modifier(Etudiant e) {
		
		liste.set(liste.indexOf(e), e);
		
	}
	
}
