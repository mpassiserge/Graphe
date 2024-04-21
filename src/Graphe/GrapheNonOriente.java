package Graphe;

import java.util.Scanner;

import javax.swing.JTextArea;


public class GrapheNonOriente extends Graphe {
	public GrapheNonOriente(boolean oriente) {
		super(oriente);
	}
	public GrapheNonOriente()
	{
		super();
	}
	public GrapheNonOriente(int val) {
		switch(val) {
			case 1:
				chargerMatriceFromFichier();
				break;
			case 2:
				chargerFsApsFromFichier();
				break;
			case 3:
				chargerAretesFromFichier();
				break;
		}
	}
	public GrapheNonOriente(Arete[] aretes, int nb_sommet, int nb_aretes)
	{
		super(aretes, nb_sommet, nb_aretes);
	}
	/*
	 * Déterminer la coloration d'un graphe
	 */
	public int[] coloration() //TESTED
	{
	    int n = d_aps[0];
	    int[] f = new int[n+1];
	    for (int i = 1; i <= n ; ++i) {
	        f[i] = 0;
	    }
	    f[0] = n;
	    for (int i = 1; i <= n ; ++i) {
	        int c = 1;
	        int k = d_aps[i];
	        while (d_fs[k]!=0 && f[d_fs[k]] == c) {
	            c++; k++;
	        }
	        f[i] = c;
	    }
	    return f;
	}
	/*
	 * Le nombre d'un graphe
	 */
	public int nombre_chromatique(int []f)//TESTED
	{
	    int max = f[1];
	    for (int i = 2; i <= f[0] ; ++i) {
	        if(f[i] > max)
	            max = f[i];
	    }
	    return max;
	}

	public int menu()
	{
		Scanner in = new Scanner(System.in);
	    int choix;
	    do {
	    	System.out.println( "-----------------------------MENU------------------------------------- " );
	    	System.out.println( "Quel algorithme souhaitez-vous testé sur ce graphe orienté? " );
	    	System.out.println( "1 - Coloration et nombre chromatique du graphe : taper 1" );
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
	    }while (choix < 1 || choix > 3);
	    return choix;
	}
	public void run()
	{
		Scanner in = new Scanner(System.in);
	    int choix = menu();
	    while(choix != 3)
	    {
	    	switch(choix) 
	    	{
	    		case 1 :
	    			int []f = coloration();
		            int nChr = nombre_chromatique(f);
		            System.out.println("La coloration correspondant à votre graphe est : \n[ ");
		            for (int i = 1; i <= f[0] ; ++i) {
		            	System.out.println("Couleur du sommet "+i+" = "+f[i]+" ");
		            }
		            System.out.println("]");
		            System.out.println("Le nombre chromatique du graphe est égal à "+nChr);
		            break;
	    		case 2 :
	    			afficheMatrice();
	    			afficheFsAps();
	    			afficheAretes();
	    			break;
	    		default :
	    			System.exit(0);
	    	}
	        choix = menu();
	    }
	}
	public void colorationText(JTextArea textArea) {
		int []f = coloration();
        int nChr = nombre_chromatique(f);
        StringBuilder data=new StringBuilder();
    	data.append(">>>>>>La coloration correspondant à votre graphe est : \n");
        for (int i = 1; i <= f[0] ; ++i) {
        	data.append("Couleur du sommet "+i+" = "+f[i]+" \n");
        }
        data.append("\n");
        data.append("Le nombre chromatique du graphe est égal à "+nChr+"\n");
        textArea.setText(data.toString());
	}
	public void ajoutNouveauSommet(String[] voisin) {
		int[][] mat = new int[d_nb_sommet + 2][d_nb_sommet + 2];
		mat[0][0] = d_nb_sommet + 1;
		mat[0][1] = d_matrice_d_adjascence[0][1];
		for(int i=1; i<=d_nb_sommet; i++)
			for(int j=1; j<=d_nb_sommet; j++)
				mat[i][j] = d_matrice_d_adjascence[i][j];
		for(int i=1; i<=d_nb_sommet+1; i++)
			mat[d_nb_sommet+1][i] = 0;
		for(int i=1; i<=d_nb_sommet+1; i++)
			mat[i][d_nb_sommet+1] = 0;
		for(int i=1; i<voisin.length; i++) {
			mat[d_nb_sommet+1][Integer.parseInt(voisin[i])] = 1;
			mat[0][1]++;
			setD_nb_aretes(getD_nb_aretes() + 1);
		}
		for(int i=1; i<voisin.length; i++) {
			mat[Integer.parseInt(voisin[i])][d_nb_sommet+1] = 1;
			mat[0][1]++;
			setD_nb_aretes(getD_nb_aretes() + 1);
		}
		d_nb_sommet++;
		d_matrice_d_adjascence = mat;
		matriceToAretes();
		matriceToFsAps();
	}
	public void supprimerSommet(int sommet) {
		demi_degre_ext();
		demi_degre_int();
		int[][] mat = new int[d_nb_sommet][d_nb_sommet];
		mat[0][0] = d_nb_sommet-1;
		mat[0][1] = d_matrice_d_adjascence[0][1] - ddi[sommet] - dde[sommet];
		setD_nb_aretes(mat[0][1]);
		int newTabRow = 1;
		int newTabCol = 1;
		for(int i=1; i<=d_nb_sommet; i++) {
			if(i != sommet) {
				for(int j=1; j<=d_nb_sommet; j++) {
					if(j != sommet) {
						mat[newTabRow][newTabCol] = d_matrice_d_adjascence[i][j];
						++newTabCol;
					}
					
				}
				++newTabRow;
				newTabCol = 1;
			}
		}
		d_nb_sommet--;
		d_matrice_d_adjascence = mat;
		matriceToAretes();
		matriceToFsAps();
		demi_degre_ext();
		demi_degre_int();
        for (int i = 1; i <= d_aps[0] ; ++i)
        	if(dde[i] == 0 && ddi[i] == 0)
        		supprimerSommet(i);
	}
	public void ajoutNouvelArete(int sommet1, int sommet2) {
		d_matrice_d_adjascence[0][1] = d_matrice_d_adjascence[0][1] + 2;
		d_matrice_d_adjascence[sommet1][sommet2] = 1;
		d_matrice_d_adjascence[sommet2][sommet1] = 1;
		
		matriceToFsAps();
		Arete[] NAretes = new Arete[d_nb_aretes+1];
		for(int i=0; i<d_nb_aretes; i++) {
			NAretes[i] = aretes[i];
		}
		NAretes[d_nb_aretes] = new Arete(new Sommet(sommet1), new Sommet(sommet2));
		aretes = NAretes;
		setD_nb_aretes(getD_nb_aretes() + 1);
	}
	public void supprimerArete(int arete) {
		d_matrice_d_adjascence[getAretePos(arete).getD_sommet_depart().getD_numero()][getAretePos(arete).getD_sommet_arrive().getD_numero()] = 0;
		d_matrice_d_adjascence[getAretePos(arete).getD_sommet_arrive().getD_numero()][getAretePos(arete).getD_sommet_depart().getD_numero()] = 0;
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
	

}
