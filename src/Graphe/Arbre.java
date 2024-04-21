package Graphe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;

public class Arbre extends GrapheNonOriente {

	public Arbre() {
		super();
	}
	public Arbre(int val) {
		super(val);
	}
	public Arbre(boolean oriente) {
		super(oriente);
	}
	

	public int[] codage_prufer() {
	    int n = d_matrice_d_adjascence[0][0];
	    System.out.println("n = "+n);
	    int[][] mat = getMatrice();
	    
	    int[] p = new int[n-1];
	    p[0] = n-2;
	    for (int i = 1; i <= n ; ++i)
	    {
	        mat[i][0] = 0;
	        for (int j = 1; j <= n; ++j)
	            mat[i][0] += mat[i][j];
	    }/*
	    for (int i = 0; i <= n ; ++i) {
	    	for (int j = 0; j <= n ; ++j)
	    		System.out.print(mat[i][j]+" ");
	    	System.out.println("");
        }*/
	    for (int k = 1; k <= n-2 ; ++k)
	    {
	        int i = 1;
	        while(i<=n && mat[i][0] != 1)
	            i++;
	        int j = 1; //unique voisin de i
	        while(j<= n && mat[i][j] != 1)
	            j++;
	        p[k] = j;
	        mat[i][j] = 0;
	        mat[j][i] = 0;
	        mat[i][0] = 0;
	        mat[j][0]--;
	    }
	    return p;
	}

	public static int[][] decodage_prufer(int []codage, StringBuilder data) {
	    int m = codage[0], n = m+2;
	    int [][]mat = new int[n+1][n+1];
	    mat[0][0] = n;
	    mat[0][1] = 0;
	    for (int i = 1; i <= n; ++i)
	        for (int j = 1; j <= n ; ++j)
	            mat[i][j] = 0;
	    int []s = new int[n+1];
	    for (int i = 1; i <= n ; ++i)
	        s[i] = 0;
	    for (int i = 1; i <= m ; ++i)
	        s[codage[i]]++;
	    System.out.println("Les arêtes du graphe correspondant au codage donné sont :");
	    data.append("Les arêtes du graphe correspondant au codage donné sont :\n");
	    for (int i = 1; i <= m ; ++i)
	    {
	    	System.out.print("[ "+codage[i]);
	    	data.append("[ "+codage[i]);
	        int k = 1;
	        while(s[k] != 0)
	            k++;
	        System.out.println(" "+k+" ]");
	        data.append(" "+k+" ]\n");
	        s[k] = -1;
	        s[codage[i]]--;
	        mat[codage[i]][k] = 1;
	        mat[k][codage[i]] = 1;
	        mat[0][1] = mat[0][1] + 2;
	    }
	    System.out.print("[ ");
	    data.append("[ ");
	    int[] sd = new int[2];
	    int j = 0;
	    for (int i = 1; i <= n ; ++i) {
	    	
	        if(s[i] == 0) {
	            sd[j] = i;
	            System.out.print(i+" ");
	            if(data != null)
	            	data.append(i+" ");
	            j++;
	        }
	    }
	    mat[sd[0]][sd[1]] = 1;
	    mat[sd[1]][sd[0]] = 1;
	    mat[0][1] = mat[0][1] + 2;
	    System.out.println("]");
	    data.append("]\n");
	    return mat;
	}
	public int menu()
	{
		Scanner in = new Scanner(System.in);
	    int choix;
	    do {
	    	System.out.println( "-----------------------------MENU------------------------------------- " );
	    	System.out.println( "Quel algorithme souhaitez-vous testé sur ce graphe orienté? " );
	    	System.out.println( "1 - Codage de Prufer : taper 1" );
	    	System.out.println( "2 - Décodage de Prufer : taper 2" );
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
	        if(choix == 1){
	        	if(estUnArbre()) {
	        		int []codage = codage_prufer();
		            System.out.print(">>Le codage de prufer correspondant au graphe est : [ ");
		            for (int i = 1; i <= codage[0] ; ++i) {
		            	System.out.print(codage[i]+" ");
		            }
		            System.out.println("] ");
	        	}
	        	else
	        		System.out.println("Ce graphe n'est pas un arbre donc prufer ne s'applique pas à lui.");
	        }
	        else if(choix == 2)
	        {
	            int n;
	            System.out.print(">>Donner le nombre d'élément du tableau correspondant au codage de prufer : ");
	            n = in.nextInt();
	            while(true)
				{
					String input = in.nextLine();
					try
					{	
						n = Integer.parseInt(input);
						break;
					}
					catch(NumberFormatException ex)
					{
						System.out.print("Vous devez taper une valeur numérique:");
					}
				}
	            int []codage = new int[n+1];
	            codage[0] = n;
	            System.out.print(">>Rentrer les valeurs du tableau de codage : ");
	            for (int i = 0; i <= n ; ++i) {
	                while(true)
	    			{
	    				String input = in.nextLine();
	    				try
	    				{	
	    					codage[i] = Integer.parseInt(input);
	    					break;
	    				}
	    				catch(NumberFormatException ex)
	    				{
	    					System.out.print("Vous devez taper une valeur numérique:");
	    				}
	    			}
	            }
	            int [][]mat = decodage_prufer(codage, null);
	            System.out.println(">>Le graphe correspondant à ce codage a pour matrice : ");
	            for (int i = 1; i <= mat[0][0] ; ++i) {
	            	System.out.print("[ ");
	                for (int j = 1; j <= mat[0][0] ; ++j) {
	                	System.out.print(mat[i][j]+" ");
	                }
	                System.out.println("] ");
	            }
	        }
	        else System.exit(0);
	        choix = menu();
	    }
	}
	public static Arbre ArbreFromFicher(String nomFicher)
	{
		JFileChooser file=new JFileChooser();
		int reponse=file.showOpenDialog(null);
		if(reponse==JFileChooser.APPROVE_OPTION)
		{
			nomFicher=file.getSelectedFile().getAbsolutePath();
		}
		try
		{
			FileReader file1=new FileReader(nomFicher);
			BufferedReader in = new BufferedReader(file1);
			String line;
			int []t=null;
			if ((line = in.readLine()) != null)
				{
				
				String []tabelEment=line.split(" ");
				
				t=new int[tabelEment.length];
			    t[0]=tabelEment.length;
			    for(int j=0;j<tabelEment.length;j++)
		        {
		         t[j]=Integer.parseInt(tabelEment[j]);
		        }
				}
			in.close();
			
			Arbre b=new Arbre();
			
			return b;
			}
		catch (Exception e) {
			System.out.println("Problem fichier");
		}
		return null;
	}
	public void pruferCodageText(JTextArea textArea) {
        StringBuilder data=new StringBuilder();
        if(estUnArbre()) {
	        int []codage = codage_prufer();
	        data.append(">>Le codage de prufer correspondant au graphe est : [ ");
	        for (int i = 1; i <= codage[0] ; ++i) {
	        	data.append(codage[i]+" ");
	        }
	        data.append("] ");
        }
        else
        {
        	data.append("Ce graphe n'est pas un arbre donc prufer ne s'applique pas à lui.\n");
        }
        textArea.setText(data.toString());
	}
	public static void pruferDecodageText(JTextArea textArea) {
		JFileChooser file=new JFileChooser();
		int reponse=file.showOpenDialog(null);
		String nomFichier="";
		if(reponse==JFileChooser.APPROVE_OPTION)
		{
			nomFichier=file.getSelectedFile().getAbsolutePath();
		}
		try
		{
			FileReader file1=new FileReader(nomFichier);
			BufferedReader in = new BufferedReader(file1);
			String line;
			
			line = in.readLine();;
			int n = Integer.parseInt(line);
			int []codage = new int[n+1];
            codage[0] = n;
            System.out.print(">>Rentrer les valeurs du tableau de codage : ");
            line = in.readLine();
            String[] tab = line.split(" ");
            for (int i = 0; i < tab.length ; ++i) {
                codage[i+1] = Integer.parseInt(tab[i]);
            }
            StringBuilder data=new StringBuilder();
            int [][]mat = decodage_prufer(codage, data);
            
    		data.append(">>>>>>Le graphe correspondant à ce codage a pour matrice d'adjascence\n");
            for (int i = 0; i <= mat[0][0] ; ++i) {
            	data.append("[ ");
                for (int j = 0; j <= mat[0][0] ; ++j) {
                	data.append(mat[i][j]+" ");
                }
                data.append("] \n");
            }
            textArea.setText(data.toString());
			in.close();	
			file1.close();
		}
		catch(Exception e)
		{
			 System.out.println ("Fichier introuvable. "+e.getMessage());
			
		}
	}
}
