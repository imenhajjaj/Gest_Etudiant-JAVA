package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.entities.Etudiant;
import metier.IGestionEtudiant;

public class GestionEtudiantJDBC implements IGestionEtudiant {

	Connection connection=SingletonConnection.getInstance();
	
	
	
	@Override
	public void ajouterEtudiant(Etudiant e) {
		
		try {
			PreparedStatement ps=connection.prepareStatement("insert into etudiant(nom,prenom,filiere,sexe) values(?,?,?,?)");
		    ps.setString(1, e.getNom());
		    ps.setString(2, e.getPrenom());
		    ps.setString(3, e.getFiliere());
		    ps.setString(4, e.getSexe());
		    ps.executeUpdate();
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@Override
	public List<Etudiant> rechercherParMC(String mc) {
		List<Etudiant>liste=new ArrayList<>();
		try {
			PreparedStatement ps=connection.prepareStatement("select * from etudiant where nom like ?");
			ps.setString(1, "%"+mc+"%");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
				liste.add(new Etudiant(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
			
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return liste;
	}

	@Override
	public void trierListeEtudiantsParNom() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Etudiant> listeDesEtudiants() {
		List<Etudiant>liste=new ArrayList<>();
		try {
			PreparedStatement ps=connection.prepareStatement("select * from etudiant");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
				liste.add(new Etudiant(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
			
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return liste;
	}

	@Override
	public void deleteEtudiant(int id) {
		try {
			PreparedStatement ps=connection.prepareStatement("delete from etudiant where id=?");
		    ps.setInt(1,id);
		    ps.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	@Override
	public Etudiant getEtudiant(int id) {
		Etudiant et=null;
		try {
			PreparedStatement ps=connection.prepareStatement("select * from etudiant where id=?");
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
				et=new Etudiant(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return et;
	}
	@Override
	public void Modifier(Etudiant e) {
		try {
			PreparedStatement ps=connection.prepareStatement("update etudiant set nom=?, prenom=?,filiere=?, sexe=? where id=?");
		    ps.setString(1, e.getNom());
		    ps.setString(2, e.getPrenom());
		    ps.setString(3, e.getFiliere());
		    ps.setString(4, e.getSexe());
		    ps.setInt(5, e.getId());
		    ps.executeUpdate();
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
