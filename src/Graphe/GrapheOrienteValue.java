package Graphe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;

public class GrapheOrienteValue extends GrapheOriente {
	private int[][] d_cout;
	public GrapheOrienteValue(boolean oriente) {
		super(oriente);
		if(d_matrice_d_adjascence != null)
			remplirCout();
		afficheAretes();
	}
	public GrapheOrienteValue(int val) {
		super(val);
		if(d_matrice_d_adjascence != null)
			remplirCout();
	}
	public GrapheOrienteValue()
	{
		super();
	}
	
	public void remplirCout() {
		int infini = Integer.MAX_VALUE;
	    int n = d_aps[0];
	    int m = getD_nb_aretes();
	    setD_cout(new int[n+1][n+1]);
	    getD_cout()[0] = new int[2];
	    getD_cout()[0][0] = n;
	    for (int i = 1; i <= n ; ++i)
	        getD_cout()[i] = new int[n+1];
	    for (int i = 1; i <= n ; ++i)
	        for (int j = 1; j <= n ; ++j)
	            getD_cout()[i][j] = infini;
	    for (int i = 0; i < m ; ++i) {
	        getD_cout()[getAretes()[i].getD_sommet_depart().getD_numero()][getAretes()[i].getD_sommet_arrive().getD_numero()] = getAretes()[i].getD_poids();
	    }
	}
	public void saisir_cout() //TESTED
	{
		Scanner in = new Scanner(System.in);
	    int infini = Integer.MAX_VALUE;
	    int n = d_aps[0];
	    int m = getD_nb_aretes();
	    setD_cout(new int[n+1][n+1]);
	    getD_cout()[0] = new int[2];
	    getD_cout()[0][0] = n;
	    for (int i = 1; i <= n ; ++i)
	        getD_cout()[i] = new int[n+1];
	    for (int i = 1; i <= n ; ++i)
	        for (int j = 1; j <= n ; ++j)
	            getD_cout()[i][j] = infini;
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
	        getD_cout()[getAretes()[i].getD_sommet_depart().getD_numero()][getAretes()[i].getD_sommet_arrive().getD_numero()] = p;
	    }
	}
	public void ordonnancement(int []d, int []fpc, int []appc, int []lc)
	{
	    
        
	    int n = app[0], m = fp[0];
	    fpc[0] = m;
	    appc[0] = n; lc[0] = n;
	    int kc, t, lg;
	    lc[1] = 0;
	    fpc[1] = 0; // fin de la liste 
	    appc[1] = 1;
	    kc = 1; // indice de la dernière place remplie dans fpc 
	    for(int s = 2; s <= n; s++)
	    {
		    //calcul de lc[s] en fonction des prédécesseurs de s
		    lc[s] = 0;
		    appc[s] = kc+1; // début de la liste des prédécesseurs critiques de s 
		    for (int k = app[s]; (t = fp[k]) != 0; k++)
		    {
			    lg = lc[t] + d[t]; 
			    if (lg >= lc[s]) 
			    {
				    if (lg > lc[s]) 
				    {
				    	lc[s] = lg; // Nouvelle lg candidate a être critique 
				    	kc = appc[s];
				    	fpc[kc] = t;
				    }
				    else // lg == lc[s] : ajouter un nouveau prédécesseur critique 
				    {
				    	kc++;
				    	fpc[kc] = t; 
				    }
			    }
			} //for k
		    kc++ ;
		    fpc[kc] = 0; //fin de la liste des prédécesseurs critiques de s
	    } //for s
	    fpc[0] = kc;
	}
	/*
	public String cheminCritique()
	{
		int []fs=getFs();
		int []aps=getAps();
		int []d=getD();
		FpAppD graphe=new FpAppD(fs,aps , 100,d );
		fs=graphe.getFp();
		aps=graphe.getApp();
		for(int i=2;i<aps.length;i++)
		{
			aps[i]-=1;
		}
		
		d=graphe.getD();
		int []fpc;
		int []appc;
		int []lc;
		int NombreSommet= aps[0], taillFp= fs[0];
	    fpc = new int[taillFp+1];
	    appc = new int[NombreSommet+1];
	    appc[0] = NombreSommet;
	    lc = new int[NombreSommet+1];
	    lc[0] = NombreSommet;
	    ordonnancement(fs, aps, d, fpc, appc, lc);
	    StringBuilder data=new StringBuilder();
	    int indexFpc;
	    indexFpc=appc[appc.length-1];
	    int x=appc.length-1;
	    data.append(" "+x);
	    	while(fpc[indexFpc]!=0)
	    	{
	    		
	    		data.append(" "+fpc[indexFpc]);
	    		indexFpc=appc[fpc[indexFpc]];
	    	}
	  String []f=data.toString().split(" ");
	  data=new StringBuilder();
	  data.append("[");
	  for(int i=f.length-1;i>0;i--)
	  {
		  data.append(f[i]+",");
	  }
	  data.append("]");
	   return data.toString();
	}*/
	public void Dijkstra(int s, int []d, int []pr)//TESTED
	{
	    int ind; /* nombre d'elements qui restent a traiter */
	    int i, j = 0, k, v;
	    int n = d_aps[0];
	    int m = d_fs[0];
	    int []inS = new int[n+1];
	    /* sert a dire quels sont les sommets qui restent a traiter inS[i] = 0 ou 1*/
	    /* initialisation des tableaux d, pr et inS*/
	    for (i = 1; i <= n; i++)
	    {
	        d[i] = getD_cout()[s][i];
	        inS[i] = 1;
	        k = d_aps[s];
	        while(d_fs[k] != 0 && d_fs[k] != i)
	            k++;
	        if(d_fs[k] == i)
	            pr[i] = 1;
	        else
	            pr[i] = -1;
	    }
	    d[s] = 0;
	    pr[s] = 0;
	    inS[s] = 0; /* on supprime le sommet 1 */
	    ind = n - 1;
	    while (ind > 0)
	    {
	        /* calcule du minimum selon d des sommets de S */
	        m = Integer.MAX_VALUE;
	        for (i = 1; i <= n; i++)
	            if (inS[i] == 1)
	                if (d[i] < m) {
	                    m = d[i];
	                    j = i;
	                }
	        if (m == Integer.MAX_VALUE)
	            return;
	        inS[j] = 0;
	        ind--;
	        k = d_aps[j];
	        while (d_fs[k] != 0)
	        {
	            if (inS[d_fs[k]] == 1) {
	                v = d[j] + getD_cout()[j][d_fs[k]];
	                if (v < d[d_fs[k]]) {
	                    d[d_fs[k]] = v;
	                    pr[d_fs[k]] = j;
	                }
	            }
	            k++;
	        }
	    }
	}
	public void Dijkstra(int [][]mat_d, int [][]mat_pred)//TESTED
	{
	    int n = d_aps[0];
	    mat_d = new int[n+1][n+1];
	    
	    mat_pred = new int[n+1][n+1];
	    
	    
	    mat_pred[0][0] = n;
	    mat_d[0][0] = n;
	    for(int i=1; i<=n; ++i)
	    {
	    	for(int j=1; j<=n; ++j)
	    	{
	    		mat_d[i][j] = 0;
	    		mat_pred[i][j] = 0;
	    	}
	    }
	    for (int s = 1; s <= n; ++s)
	        Dijkstra(s, mat_d[s], mat_pred[s]);
	    System.out.println(">>Remarque : la valeur "+Integer.MAX_VALUE+" correspond à 'plus l'infini.'");
	    System.out.println("------ Matrice des distances ------");
        for(int i=1;i<=n;i++){
            System.out.print("sommet "+i+" : [ ");
            for(int j=1;j<=n;j++)
            {
            	if(mat_d[i][j] == Integer.MAX_VALUE)
            		System.out.print("∞ ");
            	else
            	{
            		System.out.print(mat_d[i][j]+" ");
            	}
            }
            System.out.println("]");
        }
        System.out.println("------ Matrice des pred ------");
        for(int i=1;i<=n;i++){
        	System.out.print("sommet "+i+" : [ ");
            for(int j=1;j<=n;j++)
            	System.out.print(mat_pred[i][j]+" ");
            System.out.println("]");
        }
	    
	}
	public boolean Dantzig(StringBuilder data) {
	    //c : matrice des coûts qui sera remplacée par la matrice des distances
	    //Renvoie false si le graphe contient un circuit absorbant
	    int n = (int)(d_cout[0][0]);
	    int [][]c = d_cout;
	    for(int i=1;i<=d_matrice_d_adjascence[0][0];i++){
	    	c[i][i] = 0;
	    }
	    System.out.println("Matrice d'adjascence : ");
        for(int i=1;i<=d_matrice_d_adjascence[0][0];i++){
        	System.out.print("[ ");
            for(int j=1;j<=d_matrice_d_adjascence[0][0];j++)
            	System.out.print(c[i][j]+" ");
            System.out.println("]");
        }
	    int k, i ,j;
	    int x;
	    for (k = 1; k < n; ++k)
	    {
	        for (i = 1; i <= k; ++i)
	        {
	            for (j = 1; j <= k; ++j)
	            {
	                if((x = c[i][j] + c[j][k+1]) < c[i][k+1])
	                {
	                	c[i][k+1] = x;
	                	System.out.println("c"+i+""+(k+1)+" = "+x);
	                }
	                if((x = c[k+1][j] + c[j][i]) < c[k+1][i])
	                {
	                	c[k+1][i] = x;
	                	System.out.println("c"+(k+1)+""+(i)+" = "+x);
	                }
	            }//fin for j
	            if((c[i][k+1] + c[k+1][i]) < 0)
	            {
	                System.out.println("Présence d'un circuit absorbant passant par " + i + " et "+ (k+1));
	                System.out.println("Nouvelle Matrice des couts :");
	                if(data != null) {
	                	data.append("Présence d'un circuit absorbant passant par " + i + " et "+ (k+1)+"\n");
		                data.append("Nouvelle Matrice des couts :\n");
	                }
	                for (int l = 1; l <= n ; ++l) {
	                    for (int p = 1; p <= n ; ++p) {
	                    	if(c[l][p] == Integer.MAX_VALUE) {
	                    		System.out.print("∞ ");
	                    		if(data != null)data.append("∞ ");
	                    	}
	                    	else {
	                    		System.out.print(c[l][p]+" ");
	                    		if(data != null)data.append(c[l][p]+" ");
	                    	}
	                    	
	                    }
	                    System.out.println();
	                    if(data != null)data.append("\n");
	                }
	                return true;
	            }
	        }//fin for i
	        for (i = 1; i <= k; ++i) {
		        for (j = 1; j <= k; ++j) {
		            if((x = c[i][k+1] + c[k+1][j]) < c[i][j])
		            {
		            	c[i][j] = x;
		            	System.out.println("c"+(i)+""+(j)+" = "+x);
		            }
		        }
	        }
	    }//fin for k
	    
	    System.out.println("Nouvelle Matrice des couts :");
	    data.append("Nouvelle Matrice des couts :\n");
	    for (int l = 1; l <= n ; ++l) {
            for (int p = 1; p <= n ; ++p) {
            	if(c[l][p] == Integer.MAX_VALUE) {
            		System.out.print("∞ ");
            		if(data != null)data.append("∞ ");
            	}
            	else {
            		System.out.print(c[l][p]+" ");
            		if(data != null)data.append(c[l][p]+" ");
            	}
            	
            }
            System.out.println();
            if(data != null)data.append("\n");
        }
        System.out.println("Non présence d'un circuit absorbant");
        if(data != null)data.append("Non présence d'un circuit absorbant\n");
	    return false;
	}

	public void DijkstraTexte(int [][]mat_d, int [][]mat_pred, JTextArea textArea)//TESTED
	{
	    int n = d_aps[0];
	    mat_d = new int[n+1][n+1];
	    
	    mat_pred = new int[n+1][n+1];
	    
	    
	    mat_pred[0][0] = n;
	    mat_d[0][0] = n;
	    for(int i=1; i<=n; ++i)
	    {
	    	for(int j=1; j<=n; ++j)
	    	{
	    		mat_d[i][j] = 0;
	    		mat_pred[i][j] = 0;
	    	}
	    }
	    for (int s = 1; s <= n; ++s)
	        Dijkstra(s, mat_d[s], mat_pred[s]);
	    StringBuilder data=new StringBuilder();
        data.append(">>Remarque : le caractère ∞ correspond à 'plus l'infini.'\n");
        data.append(">>>>>>Matrice des distances<<<<<<\n");
        for(int i=1;i<=n;i++){
        	data.append("sommet "+i+" : [ ");
            for(int j=1;j<=n;j++)
            {
            	if(mat_d[i][j] == Integer.MAX_VALUE)
            		data.append("∞ ");
            	else
            	{
            		data.append(mat_d[i][j]+" ");
            	}
            }
            	
            data.append("]\n");
        }
        data.append(">>>>>>Matrice des pred<<<<<<\n");
        for(int i=1;i<=n;i++){
        	data.append("sommet "+i+" : [ ");
            for(int j=1;j<=n;j++)
            	data.append(mat_pred[i][j]+" ");
            data.append("]\n");
        }
        textArea.setText(data.toString());
	    
	}
	public int menu()
	{
		Scanner in = new Scanner(System.in);
	    int choix;
	    {
	    	System.out.println( "-----------------------------MENU------------------------------------- " );
	        System.out.println( "Quel algorithme souhaitez-vous testé sur ce graphe orienté? " );
	        System.out.println( "1 - Djikstra : taper 1");
	        System.out.println( "2 - Ordonnancement : taper 2" );
	        System.out.println( "3 - Dantzig : taper 3" );
	    	System.out.println( "4 - Afficher la matrice d'adjascence, FS et APS et les arêtes du graphe : taper 4" );
	        System.out.println( "5 - Pour quitter : taper 5" );
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
	    while (choix < 1 || choix > 5);
	    return choix;
	}
	public void run()
	{
	    int choix = menu();
	    while (choix != 5)
	    {
	        if(choix == 1){
	            int[][] dist = new int[getD_nb_sommet()+1][getD_nb_sommet()+1], pred = new int[getD_nb_sommet()+1][getD_nb_sommet()+1];
	            
	            Dijkstra(dist, pred);
	            
	            /*
	            System.out.println("------ Matrice des distances ------");
	            for(int i=1;i<=dist[0][0];i++){
	                System.out.print("sommet "+i+" : [ ");
	                for(int j=1;j<=dist[0][0];j++)
	                	System.out.print(dist[i][j]+" ");
	                System.out.println("]");
	            }
	            System.out.println("------ Matrice des pred ------");
	            for(int i=1;i<=dist[0][0];i++){
	            	System.out.print("sommet "+i+" : [ ");
	                for(int j=1;j<=dist[0][0];j++)
	                	System.out.print(pred[i][j]+" ");
	                System.out.println("]");
	            }*/
	        }
	        else if(choix == 2)
	        {
	        	//ordonnancement
	        	fs_aps_2_fp_app();
	        	int[] d = new int [getD_nb_sommet() + 1];
	        	for(int i = 1; i <= getD_nb_sommet(); i++)
	        		d[i] = 0;
	        	for(int i = 1; i <= getD_nb_sommet(); i++) {
	        		d[getAretes()[i].getD_sommet_depart().getD_numero()] = getAretes()[i].getD_poids();
	        	}
	        	int[] lc = new int[getD_nb_sommet() +1]; 
	        	int[] fpc;
	        	int []appc;
	        	int n = app[0], m = fp[0];
	    	    fpc = new int[m+1];
	    	    appc = new int[n+1];
	        	
	        	ordonnancement(d, fpc, appc, lc);
	        	System.out.println("------ Résultats ------");
	        	System.out.print(">>LC : [ ");
	            for(int i=1;i<=getD_nb_sommet();i++){
	            	System.out.print(lc[i]+" ");
	            }
	            System.out.println("]");
	            System.out.print(">>FPC : [ ");
	            for(int i=0;i<=getD_nb_aretes();i++){
	            	System.out.print(fpc[i]+" ");
	            }
	            System.out.println("]");
	            System.out.print(">>APPC : [ ");
	            for(int i=0;i<=getD_nb_sommet();i++){
	            	System.out.print(appc[i]+" ");
	            }
	            System.out.println("]");
	        }
	        else if(choix == 3) {
	        	System.out.println(Dantzig(null));
	        }
	        else if(choix == 4) {
	        	afficheMatrice();
    			afficheFsAps();
    			afficheAretes();
	        }
	        else System.exit(0);
	        choix = menu();
	    }
	}
	public void dantzigText(JTextArea resultat) {
		StringBuilder data = new StringBuilder();
		System.out.println(Dantzig(data));
		resultat.setText(data.toString());
	}
	
	@Override
	public void afficheAretestext(JTextArea textArea)
	{	
		StringBuilder data=new StringBuilder();
		data.append(">>>>>>ARCS<<<<<<\n");
		data.append("Nombre d'arcs = "+getD_nb_aretes()+"\n");
		  for(int i=0;i<getD_nb_aretes();i++)
		  {
			  data.append("Arc n "+(i+1)+" : [ "+getAretes()[i].getD_sommet_depart().getD_numero()+" "+getAretes()[i].getD_sommet_arrive().getD_numero()+" ] - coût : "+getAretes()[i].getD_poids()+"\n");
		  }
		textArea.setText(data.toString());
		//textArea.setEditable(false);
	}
	public void DijkstraText(JTextArea textArea) {
		int n = getD_nb_sommet();
		int[][] dist = new int[getD_nb_sommet()+1][getD_nb_sommet()+1], pred = new int[getD_nb_sommet()+1][getD_nb_sommet()+1];
		for(int i=0; i<=n; ++i)
	    {
	    	dist[i] = new int[n+1];
	    	pred[i] = new int[n+1];
	    }
		DijkstraTexte(dist, pred, textArea);
        
	}
	public void ordonnancementTexte(JTextArea textArea) {
		fs_aps_2_fp_app();
    	int[] d = new int [getD_nb_sommet() + 1];
    	for(int i = 1; i <= getD_nb_sommet(); i++)
    		d[i] = 0;
    	for(int i = 1; i <= getD_nb_sommet(); i++) {
    		d[getAretes()[i].getD_sommet_depart().getD_numero()] = getAretes()[i].getD_poids();
    	}
    	int[] lc = new int[getD_nb_sommet() +1]; 
    	int[] fpc;
    	int []appc;
    	int n = app[0], m = fp[0];
	    fpc = new int[m+1];
	    appc = new int[n+1];
    	
    	ordonnancement(d, fpc, appc, lc);
    	StringBuilder data=new StringBuilder();
    	data.append(">>>>>>LC : [ ");
        for(int i=1;i<=getD_nb_sommet();i++){
        	data.append(lc[i]+" ");
        }
        data.append("]\n");
        data.append(">>>>>>FPC : [ ");
        for(int i=0;i<=getD_nb_aretes();i++){
        	data.append(fpc[i]+" ");
        }
        data.append("]\n");
        data.append(">>>>>>APPC : [ ");
        for(int i=0;i<=getD_nb_sommet();i++){
        	data.append(appc[i]+" ");
        }
        data.append("]\n");
        textArea.setText(data.toString());
	}
	@Override
	public void matriceToAretes()//TESTED
    {
        int m = d_matrice_d_adjascence[0][1], n = d_matrice_d_adjascence[0][0];
        aretes = new Arete[m];
        int k = 0;
        for (int i = 1; i <= n ; ++i) {
            for (int j = 1; j <= n ; ++j) {
                if(d_matrice_d_adjascence[i][j] == 1)
                {
                	if(getD_cout() == null)
                		aretes[k] = new Arete(new Sommet(i), new Sommet(j));
                	else
                		aretes[k] = new Arete(new Sommet(i), new Sommet(j), getD_cout()[i][j]);
                    k++;
                }
            }
        }
    }
	public void ajoutNouvelArc(int sommet1, int sommet2, int poids) {
		d_matrice_d_adjascence[0][1]++;
		setD_nb_aretes(getD_nb_aretes() + 1);
		d_matrice_d_adjascence[sommet1][sommet2] = 1;
		getD_cout()[sommet1][sommet2] = poids;
		
		matriceToFsAps();
		matriceToAretes();
	}
	public void supprimerArc(int arc) {
		d_matrice_d_adjascence[getAretePos(arc).getD_sommet_depart().getD_numero()][getAretePos(arc).getD_sommet_arrive().getD_numero()] = 0;
		d_matrice_d_adjascence[0][1]--;
		d_nb_aretes--;
		getD_cout()[getAretePos(arc).getD_sommet_depart().getD_numero()][getAretePos(arc).getD_sommet_arrive().getD_numero()] = Integer.MAX_VALUE;
		
		matriceToFsAps();
		matriceToAretes();
	}
	public int[][] getD_cout() {
		return d_cout;
	}
	public void setD_cout(int[][] d_cout) {
		this.d_cout = d_cout;
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
