package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;



public class Fenetre extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int l, h;
	private CardLayout pile;
	private GridLayout gl;
	private JPanel conteneur, droite, gauche, menu , contenant, saisieGraphe;
	private JPanel page_accueil;
	private InterfaceOriente page_oriente_non_value;
	private InterfaceNonOriente page_non_oriente_non_value;
	private InterfaceOrienteValue page_oriente_value;
	private InterfaceNonOrienteValue page_non_oriente_value;
	private InterfaceArbre Arbre;
	private JLabel accueil, oriente_non_value, oriente_value;
	private JLabel non_oriente_non_value, non_oriente_value, arbre;
	
	public Fenetre() {
		
		pile = new CardLayout();
		gl = new GridLayout(6,1,0,2);
		
		conteneur = new JPanel();
		contenant = new JPanel();
		droite = new JPanel();
		gauche = new JPanel();
		menu = new JPanel();
		saisieGraphe = new JPanel();
		page_accueil = new JPanel();
		page_oriente_non_value = new InterfaceOriente();
		page_oriente_value = new InterfaceOrienteValue();
		page_non_oriente_non_value = new InterfaceNonOriente();
		page_non_oriente_value = new InterfaceNonOrienteValue();
		Arbre = new InterfaceArbre();
		
		
		accueil = new JLabel();
		oriente_non_value = new JLabel();
		oriente_value = new JLabel();
		non_oriente_non_value = new JLabel();
		non_oriente_value = new JLabel();
		arbre = new JLabel();
		
		/*Dimension de l'ecran*/
		l = 900;
        h = 700;
        
        /*Boutton*/
        accueil.setBackground(new Color(129,20,83));
        accueil.setText("ACCUEIL");
        accueil.setHorizontalAlignment(SwingConstants.CENTER);
        accueil.setVerticalAlignment(SwingConstants.CENTER);
        accueil.setFont(new Font("Calibri", 1, 18));
        accueil.setForeground(Color.white);
        accueil.setOpaque(true);
        
        
        oriente_non_value.setBackground(new Color(128,128,128));
        oriente_non_value.setText("ORIENTE NON VALUE");
        oriente_non_value.setHorizontalAlignment(SwingConstants.CENTER);
        oriente_non_value.setVerticalAlignment(SwingConstants.CENTER);
        oriente_non_value.setFont(new Font("Calibri", 1, 18));
        oriente_non_value.setForeground(Color.white);
        oriente_non_value.setOpaque(true);
        
        oriente_value.setBackground(new Color(128,128,128));
        oriente_value.setText("ORIENTE VALUE");
        oriente_value.setHorizontalAlignment(SwingConstants.CENTER);
        oriente_value.setVerticalAlignment(SwingConstants.CENTER);
        oriente_value.setFont(new Font("Calibri", 1, 18));
        oriente_value.setForeground(Color.white);
        oriente_value.setOpaque(true);
        
        non_oriente_non_value.setBackground(new Color(128,128,128));
        non_oriente_non_value.setText("NON ORIENTE NON VALUE");
        non_oriente_non_value.setHorizontalAlignment(SwingConstants.CENTER);
        non_oriente_non_value.setVerticalAlignment(SwingConstants.CENTER);
        non_oriente_non_value.setFont(new Font("Calibri", 1, 18));
        non_oriente_non_value.setForeground(Color.white);
        non_oriente_non_value.setOpaque(true);
        
        non_oriente_value.setBackground(new Color(128,128,128));
        non_oriente_value.setText("NON ORIENTE VALUE");
        non_oriente_value.setHorizontalAlignment(SwingConstants.CENTER);
        non_oriente_value.setVerticalAlignment(SwingConstants.CENTER);
        non_oriente_value.setFont(new Font("Calibri", 1, 18));
        non_oriente_value.setForeground(Color.white);
        non_oriente_value.setOpaque(true);
        
        arbre.setBackground(new Color(128,128,128));
        arbre.setText("ARBRE");
        arbre.setHorizontalAlignment(SwingConstants.CENTER);
        arbre.setVerticalAlignment(SwingConstants.CENTER);
        arbre.setFont(new Font("Calibri", 1, 18));
        arbre.setForeground(Color.white);
        arbre.setOpaque(true);
        
        
        
        
        /*Conteneur des bouttons*/
        menu.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 2, Color.white));
        menu.setPreferredSize(new Dimension(l-600,h));
        menu.setBackground(Color.white);
        menu.setLayout(gl);
        menu.add(accueil);
        menu.add(oriente_non_value);
        menu.add(oriente_value);
        menu.add(non_oriente_non_value);
        menu.add(non_oriente_value);
        menu.add(arbre);
        
        
        //Les buttons des algo de bases communs à tous les graphes
        
        
        //Page d'accueil
        page_accueil.setBackground(Color.white);
        page_accueil.setLayout(new BorderLayout());
        JLabel presentation = new JLabel("BIENVENUE DANS L'APPLICATION DE GRAPHE");
        presentation.setBounds(10, 10, 300, 21);
        presentation.setHorizontalAlignment(SwingConstants.CENTER);
        presentation.setVerticalAlignment(SwingConstants.CENTER);
        page_accueil.add(presentation, BorderLayout.NORTH);
        BufferedImage myPicture = null;
        try {
			myPicture = ImageIO.read(new File("graphePhotoC.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        JLabel picLabel = new JLabel((Icon) new ImageIcon(myPicture));
        picLabel.setBounds(10, 120, 184, 21);
        page_accueil.add(picLabel, BorderLayout.CENTER);
        
        JButton Aide = new JButton("Manuel d'utilisation");
        Aide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//try { Desktop.getDesktop().browse(new URL("https://github.com/kokouviHoulessodji/projetGrapheAlgoJava").toURI()); } catch (Exception e1) {}
				StringBuilder data=new StringBuilder();
				try {
					FileReader file1=new FileReader("manuel.txt");
					BufferedReader in = new BufferedReader(file1);
					String line;
					line = in.readLine();
					while(line != null)
					{
						data.append(line+"\n");
						line = in.readLine();
					}
					in.close();	
					file1.close();
					JFrame frame1 = new JFrame();
					frame1.setLayout(new FlowLayout());
					JTextArea textArea = new JTextArea();
					
					//frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame1.setBackground(Color.white);
					frame1.setTitle("Manuel d'utilisation".toUpperCase());
					frame1.setSize(new Dimension(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width, GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height));
					JButton btnNewButton_1 = new JButton("Retour");
					btnNewButton_1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frame1.setVisible(false);
						}
					});
					frame1.getContentPane().add(btnNewButton_1);
					frame1.getContentPane().add(textArea);
					textArea.setText(data.toString());
					textArea.setEditable(false);
					JScrollPane js = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			        frame1.add(js);
					frame1.setVisible(true);
				}catch(Exception ex)
				{
					 System.out.println ("Fichier introuvable."+ex.getMessage());
				}
				
				
			}
		});
		page_accueil.add(Aide, BorderLayout.SOUTH);
        
        //Remplir des éléments de la page Graphe Orienté Non Valué
        
        
        
     
		//Remplir des éléments de la page Graphe Orienté Valué
        
        
        contenant.setLayout(pile);
        contenant.setPreferredSize(new Dimension(l - 200,h - 30));
        contenant.setBackground(Color.white);
        contenant.setBorder(new MatteBorder(0,2,0,0,new Color(128, 128, 128)));
        contenant.add(page_accueil,"accueil");  
        contenant.add(page_oriente_non_value,"onv");
        contenant.add(page_oriente_value,"ov");
        contenant.add(page_non_oriente_non_value,"nonv");
        contenant.add(page_non_oriente_value,"nov");
        contenant.add(Arbre,"abr");
        pile.show(contenant, "accueil");
        
        saisieGraphe.setLayout(new BorderLayout());
        saisieGraphe.setBorder(new MatteBorder(0,2,0,0,new Color(128, 128, 128)));
        saisieGraphe.setPreferredSize(new Dimension(l - 600,25));
        saisieGraphe.setBackground(Color.white);
        
        gauche.setLayout(new BorderLayout());
        gauche.setPreferredSize(new Dimension(l - 600,h));
        gauche.setBackground(Color.white);
        gauche.add(menu);
        
        droite.setLayout(new BorderLayout());
        droite.setPreferredSize(new Dimension(l - 200,h));
        droite.setBackground(Color.white);
        droite.setBorder(new MatteBorder(0,2,0,0,new Color(128,128,128)));
        droite.add(saisieGraphe, BorderLayout.NORTH);
        droite.add(contenant);
        
        conteneur.setBorder(new MatteBorder(0,0,2,0,new Color(128, 128, 128)));
        conteneur.setLayout(new BorderLayout());
        conteneur.add(gauche, BorderLayout.WEST);
        conteneur.add(droite);
        
        
        getContentPane().add(conteneur);
	    setSize(l,h);
	    setLocationRelativeTo(null);
	    setMinimumSize(new Dimension(l, h));
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	    setTitle("GRAPHE");
	    setResizable(true);
	    event();
	    
	    
	}
	private void event() {
		accueil.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt) 
            {
            	accueil.setBackground(new Color(129,20,83));
                oriente_non_value.setBackground(new Color(128,128,128));
                oriente_value.setBackground(new Color(128,128,128));
                non_oriente_non_value.setBackground(new Color(128,128,128));
                non_oriente_value.setBackground(new Color(128,128,128));
                arbre.setBackground(new Color(128,128,128));
                pile.show(contenant, "accueil");
                
            }
        });
		oriente_non_value.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt) 
            {
                
            	accueil.setBackground(new Color(128,128,128));
                oriente_non_value.setBackground(new Color(129,20,83));
                oriente_value.setBackground(new Color(128,128,128));
                non_oriente_non_value.setBackground(new Color(128,128,128));
                non_oriente_value.setBackground(new Color(128,128,128));
                arbre.setBackground(new Color(128,128,128));
                pile.show(contenant, "onv");
                
            }
        });
		
		
		oriente_value.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt) 
            {
                accueil.setBackground(new Color(128,128,128));
                non_oriente_non_value.setBackground(new Color(128,128,128));
                oriente_value.setBackground(new Color(129,20,83));
                oriente_non_value.setBackground(new Color(128,128,128));
                non_oriente_value.setBackground(new Color(128,128,128));
                arbre.setBackground(new Color(128,128,128));
                pile.show(contenant, "ov");
                
            }
        });
		non_oriente_non_value.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt) 
            {
                 accueil.setBackground(new Color(128,128,128));
                 non_oriente_non_value.setBackground(new Color(129,20,83));
                 oriente_value.setBackground(new Color(128,128,128));
                 oriente_non_value.setBackground(new Color(128,128,128));
                 non_oriente_value.setBackground(new Color(128,128,128));
                 arbre.setBackground(new Color(128,128,128));
                 pile.show(contenant, "nonv");
                
            }
        });
		
		non_oriente_value.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt) 
            {
                 accueil.setBackground(new Color(128,128,128));
                 non_oriente_non_value.setBackground(new Color(128,128,128));
                 oriente_value.setBackground(new Color(128,128,128));
                 oriente_non_value.setBackground(new Color(128,128,128));
                 non_oriente_value.setBackground(new Color(129,20,83));
                 arbre.setBackground(new Color(128,128,128));
                 pile.show(contenant, "nov");
                
            }
        });
		arbre.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt) 
            {
                 accueil.setBackground(new Color(128,128,128));
                 non_oriente_non_value.setBackground(new Color(128,128,128));
                 oriente_value.setBackground(new Color(128,128,128));
                 oriente_non_value.setBackground(new Color(128,128,128));
                 non_oriente_value.setBackground(new Color(128,128,128));
                 arbre.setBackground(new Color(129,20,83));
                 pile.show(contenant, "abr");
                
            }
        });
	}
	

}
