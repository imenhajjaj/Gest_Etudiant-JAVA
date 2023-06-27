package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.PopupMenu;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import dao.GestionEtudiantJDBC;
import dao.entities.Etudiant;

import metier.GestionEtudiant;
import metier.IGestionEtudiant;

public class GUIGestionEtudiant extends JFrame {
	
	int numrow=-1;
	int id;
	
	JLabel l1=new JLabel("Nom:");
	JLabel l2=new JLabel("Prenm:");
	JLabel l3=new JLabel("Filiere:");
	JLabel l4=new JLabel("Sexe:");
	JLabel l5=new JLabel("Recherche par Mot cle:");
	
	JTextField tnom=new JTextField(10);
	JTextField tprenom=new JTextField(10);
	JTextField trecherche=new JTextField(20);
	
	JComboBox<String> filiere=new JComboBox<>(new String[] {"Reseaux","Multimedia","Telecom"});
	
	JComboBox<String>
	
	JButton bAjouter=new JButton("Ajouter");
	JButton bannuler=new JButton("Annuler");
	JButton bsupprimer=new JButton("supprimer");
	JButton bmodifier=new JButton("Modifier");
	JButton brechercher=new JButton("Rechercher");
	
	JRadioButton bh=new JRadioButton("H");
	JRadioButton bf=new JRadioButton("F");
	
	TableModele tm=new TableModele();
	JTable tableau=new JTable(tm);
	JScrollPane jsp=new JScrollPane(tableau);
	
	JPanel pInf=new JPanel(new BorderLayout());
	JPanel pListe=new JPanel(new BorderLayout());
	
	JPanel pfEt=new JPanel(new GridLayout(2,2));
	JPanel pbh=new JPanel();
	JPanel prech=new JPanel();
	
	JPanel pnom=new JPanel();
	JPanel pprenom=new JPanel();
	JPanel pfiliere=new JPanel();
	JPanel psexe=new JPanel();
	Etudiant et;
	//IGestionEtudiant gestion=new GestionEtudiant();
	IGestionEtudiant gestion=new GestionEtudiantJDBC();
	
	GUIGestionEtudiant()
	{
		super("Gestion Etuidants");
		bsupprimer.setEnabled(false);
		bmodifier.setEnabled(false);
		setLocationRelativeTo(null);
		tm.charger(gestion.listeDesEtudiants());
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		tm.charger(gestion.listeDesEtudiants());
		pnom.add(l1);
		pnom.add(tnom);
		
		pprenom.add(l2);
		pprenom.add(tprenom);
		
		pfiliere.add(l3);
		pfiliere.add(filiere);
		
		psexe.add(l4);
		psexe.add(bh);
		psexe.add(bf);
		
		pfEt.add(pnom);
		pfEt.add(pprenom);
		pfEt.add(pfiliere);
		pfEt.add(psexe);
		
		pInf.add(pfEt);
		pInf.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE,2),"Informations Etudiant"));
		
		pbh.add(bAjouter);
		
		pbh.add(bsupprimer);
		pbh.add(bmodifier);
		pbh.add(bannuler);
		
		pInf.add(pbh,BorderLayout.SOUTH);
		add(pInf,BorderLayout.NORTH);
		
		prech.add(l5);
		prech.add(trecherche);
		prech.add(brechercher);
		
		pListe.add(prech,BorderLayout.NORTH);
		pListe.add(jsp);
		
		add(pListe);
		
		tm.charger(gestion.listeDesEtudiants());
		ButtonGroup bg=new ButtonGroup();
		bg.add(bh);
		bg.add(bf);
		bannuler.addActionListener(e->{
			tnom.setText("");
			tprenom.setText("");
			bg.clearSelection();
			tableau.clearSelection();
			bAjouter.setEnabled(true);
			bmodifier.setEnabled(false);
			bsupprimer.setEnabled(false);
			
			
		});
		
		bAjouter.addActionListener(e->{
			
			String nom=tnom.getText();
			String prenom=tprenom.getText();
			String fil=(String)filiere.getSelectedItem();
			String sexe="";
			if(bh.isSelected())
				sexe="H";
			else if(bf.isSelected())
				sexe="F";
			if(nom.equals("")||prenom.equals("")||sexe.equals(""))
				JOptionPane.showMessageDialog(this, "erreur de saisie");
			else
			{
				int v=JOptionPane.showConfirmDialog(this, "voulez vous ajouter cet etudiant?","Conformation",JOptionPane.YES_NO_OPTION);
				if(v==0) {
				Etudiant et=new Etudiant(nom, prenom,  fil,sexe);
				gestion.ajouterEtudiant(et);
				tm.charger(gestion.listeDesEtudiants());
				}
			}
			
		});
		brechercher.addActionListener(e->tm.charger(gestion.rechercherParMC(trecherche.getText())));
		
		bAjouter.addMouseListener(new MouseAdapter() {
		
		     public void mouseEntered(MouseEvent e) {
		    	 if(bAjouter.isEnabled()) {
		    	 bAjouter.setBackground(Color.BLUE);
		    	 bAjouter.setForeground(Color.WHITE);}
		     };
		     public void mouseExited(MouseEvent e) {
		    	 bAjouter.setBackground(null);
		    	 bAjouter.setForeground(null);
		     };
		});
		bsupprimer.addMouseListener(new MouseAdapter() {
			
		     public void mouseEntered(MouseEvent e) {
		    	 if(bsupprimer.isEnabled()) {
		    	 bsupprimer.setBackground(Color.RED);
		    	 bsupprimer.setForeground(Color.WHITE);}
		     };
		     public void mouseExited(MouseEvent e) {
		    	 bsupprimer.setBackground(null);
		    	 bsupprimer.setForeground(null);
		     };
		});
		brechercher.addMouseListener(new MouseAdapter() {
			
		     public void mouseEntered(MouseEvent e) {
		    	 
		    	 brechercher.setBackground(Color.GREEN);
		    	 brechercher.setForeground(Color.WHITE);
		     };
		     public void mouseExited(MouseEvent e) {
		    	 brechercher.setBackground(null);
		    	 brechercher.setForeground(null);
		     };
		});
		bmodifier.addMouseListener(new MouseAdapter() {
			
		     public void mouseEntered(MouseEvent e) {
		    	 if(bmodifier.isEnabled()) {
		    	 bmodifier.setBackground(Color.YELLOW);
		    	 bmodifier.setForeground(Color.BLUE);}
		     };
		     public void mouseExited(MouseEvent e) {
		    	 bmodifier.setBackground(null);
		    	 bmodifier.setForeground(null);
		     };
		});
		
		bannuler.addMouseListener(new MouseAdapter() {
			
		     public void mouseEntered(MouseEvent e) {
		    	 bannuler.setBackground(Color.gray);
		    	 bannuler.setForeground(Color.WHITE);
		     };
		     public void mouseExited(MouseEvent e) {
		    	 bannuler.setBackground(null);
		    	 bannuler.setForeground(null);
		     };
		});
		
		tableau.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		    	bsupprimer.setEnabled(true);
		    	bmodifier.setEnabled(true);
		    	bAjouter.setEnabled(false);
		    	numrow=tableau.getSelectedRow();
		    	id=(int)tm.getValueAt(numrow, 0);
		    	String nom=(String)tm.getValueAt(numrow, 1);
		    	String prenom=(String)tm.getValueAt(numrow, 2);
		    	String sexe=(String)tm.getValueAt(numrow,3);
		    	String filier=(String)tm.getValueAt(numrow, 4);
		    	
		    	tnom.setText(nom);
		    	tprenom.setText(prenom);
		    	if(sexe.equalsIgnoreCase("H"))
		    	   bh.setSelected(true);
		    	   else
		    		bf.setSelected(true);
		    	
		    	filiere.setSelectedItem(filier);
		    	 };
		});
		bsupprimer.addActionListener(e->{
			
			/*if(numrow==-1)
				JOptionPane.showMessageDialog(this, "SVP selectionnez une ligne");
			else {*/
			int v=JOptionPane.showConfirmDialog(this, "voulez vous supprimer cet etudiant?","Conformation",JOptionPane.YES_NO_OPTION);
			if(v==0) {
			id=(int)tm.getValueAt(numrow, 0);
			
			gestion.deleteEtudiant(id);
			tm.charger(gestion.listeDesEtudiants());
			bmodifier.setEnabled(false);
			bsupprimer.setEnabled(false);
			bAjouter.setEnabled(true);
			tnom.setText("");
			tprenom.setText("");
			bg.clearSelection();
			//numrow=-1;
			
			//}
			}
			
		});
		bmodifier.addActionListener(e->{
			int v=JOptionPane.showConfirmDialog(this, "voulez vous modifier cet etudiant?","Conformation",JOptionPane.YES_NO_OPTION);
			if(v==0) {
			gestion.Modifier(new Etudiant(id,
					tnom.getText(),
					tprenom.getText(),
					(String)filiere.getSelectedItem(),
					bh.isSelected()?"H":"F"));
			tm.charger(gestion.listeDesEtudiants());
			tnom.setText("");
			tprenom.setText("");
			bg.clearSelection();
			bmodifier.setEnabled(false);
			bsupprimer.setEnabled(false);
			bAjouter.setEnabled(true);
			
			}
			
			
		});
		
		tnom.addKeyListener(new KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent e) {
				char c=e.getKeyChar();
				if((c>='a' && c<='z')||(c>='A' && c<='Z'));
				else
					e.consume();
			};
		});
		tprenom.addKeyListener(new KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent e) {
				char c=e.getKeyChar();
				if((c>='a' && c<='z')||(c>='A' && c<='Z'));
				else
					e.consume();
			};
		});
		pack();
		
		
	}
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
		new GUIGestionEtudiant();
	}
	
	

}
