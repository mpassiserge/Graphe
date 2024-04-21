package Graphe;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;

public class GrapheNonOrienteValue extends GrapheNonOriente {
	private int [][]d_cout;
	public GrapheNonOrienteValue(boolean oriente) {
		super(oriente);
		if(d_matrice_d_adjascence != null)
			remplirCout();
	}
	public GrapheNonOrienteValue(int val) {
		super(val);
		if(d_matrice_d_adjascence != null)
			remplirCout();
	}
	public GrapheNonOrienteValue()
	{
		super();
	}
	public GrapheNonOrienteValue(Arete[] aretes, int nb_sommet, int nb_aretes)
	{
		super(aretes, nb_sommet, nb_aretes);
	}
	public void remplirCout() {
		int infini = Integer.MAX_VALUE;
	    int n = d_aps[0];
	    int m = getD_nb_aretes();
	    d_cout = new int[n+1][n+1];
	    d_cout[0] = new int[2];
	    d_cout[0][0] = n;
	    for (int i = 1; i <= n ; ++i)
	        d_cout[i] = new int[n+1];
	    for (int i = 1; i <= n ; ++i)
	        for (int j = 1; j <= n ; ++j)
	            d_cout[i][j] = infini;
	    for (int i = 0; i < m ; ++i) {
	        d_cout[getAretes()[i].getD_sommet_depart().getD_numero()][getAretes()[i].getD_sommet_arrive().getD_numero()] = getAretes()[i].getD_poids();
	    }
	}
	public void saisir_cout() {
		Scanner in = new Scanner(System.in);
	    int infini = Integer.MAX_VALUE;
	    int n = d_aps[0];
	    int m = getD_nb_aretes();
	    d_cout = new int[n+1][n+1];
	    for (int i = 1; i <= n ; ++i)
	        d_cout[i] = new int[n+1];
	    for (int i = 1; i <= n ; ++i)
	        for (int j = 1; j <= n ; ++j)
	            d_cout[i][j] = infini;
	    d_cout[0][0] = n;
	    for (int i = 0; i < m ; ++i) {
	        int p;
	        System.out.print("Saisir le poids de l'arc [ "+getAretes()[i].getD_sommet_depart().getD_numero()+", "+getAretes()[i].getD_sommet_arrive().getD_numero()+" ] : ");
	        while(true)
    		{
    			String input = in.nextLine();
    			try
    			{	
    				p = Integer.parseInt(input);
    				break;
    			}
    			catch(NumberFormatException ex)
    			{
    				System.out.print("Vous devez taper une valeur numérique:");
    			}
    		}
	        getAretes()[i].setD_poids(p);
	        d_cout[getAretes()[i].getD_sommet_depart().getD_numero()][getAretes()[i].getD_sommet_arrive().getD_numero()] = p;
	    }
	}

	public Arete[] kruskal(StringBuilder data) 
	{
	    trier();
	    //afficheAretes();
	    int k=0, s, tt;
	    int n = d_matrice_d_adjascence[0][0];
	    //Initialiser cfc, pilch et prem
	    
	    Arete[] aretesN = new Arete[n-1];
	    int poids = 0;
	    
	    
	    if(data != null)
	    	data.append("Le nouveau graphe obtenu contient les arêtes\n(extrémité 1, extrémité 2, poids) suivants : \n\n");
	    else
	    	System.out.println(">>>>>Le nouveau graphe obtenu contient les arêtes(extrémité 1, extrémité 2, poids) suivants : \n");
	    for (int i = 0; i < d_nb_aretes; ++i)
	    {
	        s = getAretes()[i].getD_sommet_depart().getD_numero();
	        tt = getAretes()[i].getD_sommet_arrive().getD_numero();
	        if(cfc[s] != cfc[tt])
	        {
	        	if(data != null)
	        		data.append("[ "+s+" "+tt+" ]\n");
	        	else
	        		System.out.println("[ "+s+" "+tt+" "+aretes[i].d_poids+" ]");
	        	aretesN[k] = new Arete(new Sommet(s), new Sommet(tt), aretes[i].d_poids);
	        	poids += aretes[i].getD_poids();
	        	aretes[i].setD_color(Color.MAGENTA);
	        	k++;
	            fusionner(cfc[s], cfc[tt]);
	        }
	    }
	    if(data != null)
    		data.append("Le poids de l'arbre recouvrant minimal est "+poids+"\n");
    	else
    		System.out.println("Le poids de l'arbre recouvrant minimal est "+poids);
	    return aretesN;
	}
	public void kruskal() 
	{
	    trier();
	    
	    int k=0, s, tt;
	    int n = d_matrice_d_adjascence[0][0];

	    //Initialiser cfc, pilch et prem
	    
	    Arete[] aretesN = new Arete[n-1];
	    
	    
	    System.out.println(">>>>>Le nouveau graphe obtenu contient les arêtes(extrémité 1, extrémité 2, poids) suivants : \n");
	    for (int i = 0; i < d_nb_aretes; ++i)
	    {
	    	
	        s = getAretes()[i].getD_sommet_depart().getD_numero();
	        tt = getAretes()[i].getD_sommet_arrive().getD_numero();
	        if(cfc[s] != cfc[tt])
	        {
	        	
	        	System.out.println("[ "+s+" "+tt+" "+aretes[i].d_poids+" ]");
	           
	            aretesN[k] = new Arete(new Sommet(s), new Sommet(tt), aretes[i].d_poids);
	            k++;
	            fusionner(cfc[s], cfc[tt]);
	            
	        }
	    }
	}

	public void fusionner(int i, int j)
	// i et j sont les numéros des composantes à fusionner
	// en une seule composante qui portera le numéro le plus
	// petit des deux
	{
	    int k = prem[i];
	    while (pilch[k] != 0)
	    {
	        k = pilch[k];
	    }
	    pilch[k] = prem[j];
	    k = prem[j];
	    while (k != 0)
	    {
	        cfc[k] = i;
	        k = pilch[k];
	    }
	}
	//TESTED
	//2e version avec un seul parcours de prem de i
	public void fusionner2(int i, int j)
	{
	    //Si on veut favoriser la composante ayant le plus petit numéro
	    //if(i < j) std::swap(i, j);
	    int k = prem[i];
	    while (pilch[k] != 0)
	    {
	        cfc[k] = j;
	        k = pilch[k];
	    }
	    cfc[k] = j;
	    pilch[k] = prem[j];
	    prem[j] = prem[i];
	}

	
	public int menu()
	{
		Scanner in = new Scanner(System.in);
	    int choix;
	    {
	    	System.out.println( "-----------------------------MENU------------------------------------- " );
	        System.out.println( "Quel algorithme souhaitez-vous testé sur ce graphe orienté? " );
	        System.out.println( "1 - Kruskal : taper 1");
	    	System.out.println( "2 - Afficher la matrice d'adjascence, FS et APS et les arêtes du graphe : taper 2" );
	        System.out.println( "3 - Pour quitter : taper 3" );
	        while(true)
    		{
    			String input = in.nextLine();
    			try
    			{	
    				choix = Integer.parseInt(input);
    				break;
    			}
    			catch(NumberFormatException ex)
    			{
    				System.out.print("Vous devez taper une valeur numérique:");
    			}
    		}
	    }
	    while (choix < 1 || choix > 3);
	    return choix;
	}
	public void run()
	{
	    int choix = menu();
	    while (choix != 3)
	    {
	    	switch(choix)
	    	{
	    		case 1:
	    			prem = new int[getD_nb_sommet()+1];
		            pilch = new int[getD_nb_sommet()+1];
		            cfc = new int[getD_nb_sommet()+1];
		            for (int i = 0; i <= getD_nb_sommet() ; ++i) {
		    	        cfc[i] = i;
		    	        prem[i] = i;
		    	        pilch[i] = 0;
		    	    }
		            kruskal();
		            //System.out.println(">>Les caractéristiques du graphe recevrant minimal obtenu");
		            
		            System.out.print("PREM : [ ");
		            for (int i = 1; i <= d_nb_sommet ; ++i)
		            	System.out.print(prem[i]+" ");
		            System.out.println("]");
		            System.out.print("PILCH : [ ");
		            for (int i = 1; i <= d_nb_sommet ; ++i)
		            	System.out.print(pilch[i]+" ");
		            System.out.println("]");
		            System.out.print("CFC : [ ");
		            for (int i = 1; i <= d_nb_sommet ; ++i)
		            	System.out.print(cfc[i]+" ");
		            System.out.println("]");
		            break;
	    		case 2:
	    			afficheMatrice();
	    			afficheFsAps();
	    			afficheAretes();
	    			break;
	    		default: System.exit(0);
	    	}
	        choix = menu();
	    }
	}
	@Override
	public void afficheAretestext(JTextArea textArea)
	{	
		StringBuilder data=new StringBuilder();
		data.append(">>>>>>ARETES<<<<<<\n");
		data.append("Nombre d'arêtes = "+getD_nb_aretes()+"\n");
		  for(int i=0;i<getD_nb_aretes();i++)
		  {
			  data.append("Arête n "+(i+1)+" : [ "+getAretes()[i].getD_sommet_depart().getD_numero()+" "+getAretes()[i].getD_sommet_arrive().getD_numero()+" ] - coût : "+getAretes()[i].getD_poids()+"\n");
		  }
		textArea.setText(data.toString());
		//textArea.setEditable(false);
	}
	public Arete[] kurskalText(JTextArea textArea)
	{	
		StringBuilder data=new StringBuilder();
		prem = new int[getD_nb_sommet()+1];
        pilch = new int[getD_nb_sommet()+1];
        cfc = new int[getD_nb_sommet()+1];
        for (int i = 0; i <= getD_nb_sommet() ; ++i) {
	        cfc[i] = i;
	        prem[i] = i;
	        pilch[i] = 0;
	    }
        Arete[] areteN = new Arete[d_nb_sommet-1];
        areteN = kruskal(data);
        data.append(">>PREM [ ");
        for(int i=1; i<=getD_nb_sommet(); ++i)
        	data.append(prem[i]+" ");
        data.append("]\n");
        data.append(">>PILCH [ ");
        for(int i=1; i<=getD_nb_sommet(); ++i)
        	data.append(pilch[i]+" ");
        data.append("]\n");
        data.append(">>CFC [ ");
        for(int i=1; i<=getD_nb_sommet(); ++i)
        	data.append(cfc[i]+" ");
        data.append("]\n");
        
        
        //t.afficheMatrice();
        //t.afficheFsAps();
		textArea.setText(data.toString());
		return areteN;
	}
	
	public void ajoutNouvelArete(int sommet1, int sommet2, int poids) {
		d_matrice_d_adjascence[0][1] = d_matrice_d_adjascence[0][1] + 2;
		d_matrice_d_adjascence[sommet1][sommet2] = 1;
		d_matrice_d_adjascence[sommet2][sommet1] = 1;
		d_cout[sommet1][sommet2] = poids;
		d_cout[sommet2][sommet1] = poids;
		
		matriceToFsAps();
		Arete[] NAretes = new Arete[d_nb_aretes+1];
		for(int i=0; i<d_nb_aretes; i++) {
			NAretes[i] = aretes[i];
		}
		NAretes[d_nb_aretes] = new Arete(new Sommet(sommet1), new Sommet(sommet2), poids);
		aretes = NAretes;
		setD_nb_aretes(getD_nb_aretes() + 1);
	}
	public void supprimerArete(int arete) {
		d_matrice_d_adjascence[getAretePos(arete).getD_sommet_depart().getD_numero()][getAretePos(arete).getD_sommet_arrive().getD_numero()] = 0;
		d_matrice_d_adjascence[getAretePos(arete).getD_sommet_arrive().getD_numero()][getAretePos(arete).getD_sommet_depart().getD_numero()] = 0;
		d_cout[getAretePos(arete).getD_sommet_depart().getD_numero()][getAretePos(arete).getD_sommet_arrive().getD_numero()] = Integer.MAX_VALUE;
		d_cout[getAretePos(arete).getD_sommet_arrive().getD_numero()][getAretePos(arete).getD_sommet_depart().getD_numero()] = Integer.MAX_VALUE;
		d_matrice_d_adjascence[0][1] = d_matrice_d_adjascence[0][1] - 2;
		
		matriceToFsAps();
		Arete[] NAretes = new Arete[d_nb_aretes-1];
		for(int i=0; i<arete; i++) {
			NAretes[i] = aretes[i];
		}
		for(int i=arete; i<d_nb_aretes-1; i++) {
			NAretes[i] = aretes[i+1];
		}
		aretes = NAretes;
		setD_nb_aretes(getD_nb_aretes() - 1);
	}
	public int[][] getD_cout() {
		// TODO Auto-generated method stub
		return d_cout;
	}
	public void chargerMatriceCoutFromFichier(String nomFichier) {
    	
		
		try
		{
			FileReader file1=new FileReader(nomFichier);
			BufferedReader in = new BufferedReader(file1);
			String line;
			line = in.readLine();
			line = in.readLine();
			d_cout = new int[getD_nb_sommet()+1][getD_nb_sommet()+1];
			d_cout[0][0] = getD_nb_sommet();
			d_cout[0][1] = getD_nb_aretes();
			for(int i=1; i<=getD_nb_sommet(); i++)
				d_cout[i] = new int[getD_nb_sommet()+1];
			int i = 1;
			while(line != null)
			{
				String []tab = line.split(" ");
				for(int j=0; j<=getD_nb_sommet(); j++)
					d_cout[i][j] = Integer.parseInt(tab[j]);
				i++;
				line = in.readLine();
			}
			in.close();	
			file1.close();
		}
		catch(Exception e)
		{
			 System.out.println ("Fichier introuvable. "+e.getMessage());
		}
    }
	public void chargerMatriceCoutFromFichier() {
    	JFileChooser file=new JFileChooser();
		int reponse=file.showOpenDialog(null);
		String nomFichier="";
		if(reponse==JFileChooser.APPROVE_OPTION)
		{
			nomFichier=file.getSelectedFile().getAbsolutePath();
		}
		chargerMatriceCoutFromFichier(nomFichier);
    }

}
