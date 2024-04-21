package Graphe;

import java.awt.EventQueue;
import java.util.Scanner;

import javax.swing.JFrame;

import vue.Fenetre;

public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static int menu(){
		Scanner in = new Scanner(System.in);
	    int choix;
	    {
	    	System.out.println( "-----------------------------MENU------------------------------------- " );
	        System.out.println( "Sur quel type de graphe souhaitez-vous exécuter vos algorithmes? " );
	        System.out.println( "1 - Orienté non valué : taper 1" );
	        System.out.println( "2 - Orienté valué : taper 2" );
	        System.out.println( "3 - Non orienté non valué : taper 3" );
	        System.out.println( "4 - Non orienté valué : taper 4" );
	        System.out.println( "5 - Arbre : taper 5" );
	        System.out.println( "6 - Pour quitter : taper 6" );
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
	    while(choix < 1 || choix > 6);
	    return choix;
	}
	public static void run()
	{
	    int choix = menu();
	    while (choix != 6)
	    {
	    	switch(choix)
	    	{
	    		case 1 : 
	    			GrapheOriente graphe_oriente = new GrapheOriente(true);
	    			graphe_oriente.run();
	    			break;
	    		case 2 :
	    			GrapheOrienteValue graphe_oriente_value = new GrapheOrienteValue(true);
		            graphe_oriente_value.run();
		            break;
	    		case 3 :
	    			GrapheNonOriente graphe_non_oriente = new GrapheNonOriente(false);
		            graphe_non_oriente.run();
		            break;
	    		case 4 :
	    			GrapheNonOrienteValue graphe_non_oriente_value = new GrapheNonOrienteValue(false);
		            graphe_non_oriente_value.run();
		            break;
	    		case 5 :
	    			Arbre arbre = new Arbre(false);
		            arbre.run();
		            break;
	    		default : break;
	    	}
	        choix = menu();
	    }
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int choix;
		do
		{
			System.out.println( "-----------------------------MENU------------------------------------- " );
			System.out.println("Comment voulez-vous exécuter cet algorithme?");
			System.out.println("1 - Dans la console : taper 1");
			System.out.println("2 - Interface graphique : taper 2");
			System.out.println("3 - Quitter : taper 3");
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
		while(choix <= 0 || choix > 3);
		switch(choix)
		{
			case 1 :
				run();
				break;
			case 2 :
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							//Main window = new Main();
							//window.frame.setVisible(true);
							Fenetre f = new Fenetre();
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				break;
			default : break;
		}
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


}
