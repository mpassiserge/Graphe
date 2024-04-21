package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Graphe.GrapheOriente;

public class InterfaceOriente extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GrapheOriente graphe;
	private JLabel erreur;
	private JTextArea resultat;
	private JButton afficherMatrice, afficherFsAps, afficherArcOuAretes, afficheGraphe, Rang, Tarjan, Distance, ajoutSommet, supSommet, ajoutArc, supArc;
	JComboBox<String> combo;
	JButton BtSais;

	public InterfaceOriente() {
		super(null);
		this.setBackground(Color.white);
        this.setLayout(null);
		JLabel sais = new JLabel("Importer le graphe");
        sais.setBounds(10, 10, 150, 30);
        add(sais);
        erreur = new JLabel("Erreur");
		erreur.setBounds(340, 30, 130, 21);
		erreur.setVisible(false);
		add(erreur);
		afficherMatrice = new JButton("Afficher matrice");
        afficherMatrice.setBounds(10, 80, 150, 30);
        
        add(afficherMatrice);
		
        afficherFsAps = new JButton("Afficher FS et APS");
        afficherFsAps.setBounds(150, 80, 150, 30);
        add(afficherFsAps);
		
		afficherArcOuAretes = new JButton("Afficher les arcs");
		afficherArcOuAretes.setBounds(300, 80	, 150, 30);
		add(afficherArcOuAretes);
		
		Rang = new JButton("Le rang du graphe");
		Rang.setBounds(300, 150, 150, 30);
		add(Rang);

		Tarjan = new JButton("Tarjan");
		Tarjan.setBounds(10, 150, 150, 30);
		add(Tarjan);
		
		Distance = new JButton("Distance");
		Distance.setBounds(150, 150, 150, 30);
		add(Distance);
		
		ajoutSommet = new JButton("Ajouter un sommet");
		ajoutSommet.setBounds(10, 115, 150, 30);
		add(ajoutSommet);
		
		supSommet = new JButton("Supprimer un sommet");
		supSommet.setBounds(150, 115, 150, 30);
		add(supSommet);

		ajoutArc = new JButton("Ajouter un arc");
		ajoutArc.setBounds(300, 115, 150, 30);
		add(ajoutArc);
		
		supArc = new JButton("Supprimer un arc");
		supArc.setBounds(450, 115, 150, 30);
		add(supArc);
		
		combo = new JComboBox<String>();
        combo.addItem("Veuillez choisir");
        combo.addItem("Importer la matrice d'adjascence");
        combo.addItem("Importer FS et APS");
        combo.addItem("Importer les arcs");
        combo.setBounds(150, 10, 150, 30);
        combo.setEditable(false);
        add(combo);
        
        BtSais = new JButton("Choisir");
        BtSais.setBounds(300, 10, 150, 30);
        BtSais.setEnabled(false);
        add(BtSais);
        JScrollPane js = new JScrollPane(resultat, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(js);
        resultat = new JTextArea(10, 10);
        resultat.setBounds(10, 200, 520, 421);
        resultat.setOpaque(true);
        resultat.setBackground(new Color(100,128,128));
        resultat.setBackground(Color.LIGHT_GRAY);
        resultat.setAutoscrolls(true);
        resultat.setEditable(false);
        add(resultat);
        
        afficheGraphe = new JButton("Afficher le graphe");
        afficheGraphe.setBounds(10, 620, 150, 30);
		add(afficheGraphe);
        
		event();
	}

	public InterfaceOriente(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public InterfaceOriente(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public InterfaceOriente(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}
	public void event() {
		combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(combo.getSelectedIndex() == 0)
		        	BtSais.setEnabled(false);
		        else
		        	BtSais.setEnabled(true);
			}
		});
		BtSais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				graphe = new GrapheOriente(combo.getSelectedIndex());
			}
		});
		Rang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else
				{
					
		        	int[] rang = graphe.rang();
		        	int n = rang[0];
		        	StringBuilder data=new StringBuilder();
		    		data.append(">>>>>>RANG DES SOMMETS<<<<<<\n");
		    		  for(int i=1;i<=n;i++)
		    		  {
		    			  data.append("Le rang du sommet "+i+" est = "+rang[i]+" \n");
		    			  if(rang[i] == Integer.MAX_VALUE)
		              		data.append("∞ ");
			              else
			              {
			              	data.append(rang[i]+" ");
			              }
		    		  }
		            int max = rang[1];
		    	    for (int i = 2; i <= n ; ++i) {
		    	        if(rang[i] > max)
		    	            max = rang[i];
		    	    }
		    	    if(max == Integer.MAX_VALUE)
		    	    	data.append(">>Le rang du graphe est plus l'infini(∞)");
		    	    else
		    	    	data.append(">>Le rang du graphe est égal à "+max);
					resultat.setText(data.toString());
				}
			}
		});
		afficherMatrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else
				{
					graphe.afficheMatricetext(resultat);
				}
			}
		});
		afficherFsAps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else
				{
					graphe.afficheFsApstext(resultat);
				}
			}
		});
		afficherArcOuAretes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else
				{
					graphe.afficheAretestext(resultat);
				}
			}
		});
		Distance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else
				{
		        	int[][] dist = graphe.distance();
					int n = dist[0][0];
					StringBuilder data=new StringBuilder();
		        	data.append(">>>>>>DISTANCE <<<<<<\n");
		            for(int i=1; i<=n; i++) {
		            	data.append("Sommet "+i+" : [ ");
		            	for(int j=1; j<=n; j++) {
		            		if(dist[i][j] == -1)
		            			data.append("∞ ");
		            		else
		            			data.append(dist[i][j]+" ");
		            	}
		            	data.append(" ]\n");
		            }
		            resultat.setText(data.toString());
				}
			}
		});
		Tarjan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else
				{
					graphe.afficheTarjanText(resultat);
				}
			}
		});
		ajoutSommet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else
				{
					JFrame frame1 = new JFrame();
					frame1.setLayout(new GridLayout(3, 4));
					frame1.setTitle("Ajouter un nouveau sommet");
					frame1.setLocationRelativeTo(null);
				    frame1.setMinimumSize(new Dimension(500, 150));
					JLabel pred = new JLabel("Prédécesseur");
					frame1.add(pred);
					JComboBox<String> CheckPred = new JComboBox<String>();
					CheckPred.addItem("choisir");
					for(int i=1; i<=graphe.getD_nb_sommet();i++) {
						CheckPred.addItem("Sommet "+i);
					}
					
					frame1.add(CheckPred);
					JButton addPred = new JButton("Ajouter");
					addPred.setSize(new Dimension(150, 30));
					addPred.setEnabled(false);
					frame1.add(addPred);
					JTextField PredT = new JTextField();
					PredT.setSize(new Dimension(150, 30));
					PredT.setEditable(false);
					PredT.setText("");
					frame1.add(PredT);
					
					CheckPred.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(CheckPred.getSelectedIndex() == 0)
					        	addPred.setEnabled(false);
					        else
					        	addPred.setEnabled(true);
						}
					});
					
					JLabel succ = new JLabel("Successeur");
					frame1.add(succ);
					JComboBox<String> CheckSucc = new JComboBox<String>();
					CheckSucc.addItem("choisir");
					for(int i=1; i<=graphe.getD_nb_sommet();i++) {
						CheckSucc.addItem("Sommet "+i);
					}
					frame1.add(CheckSucc);
					JButton addSucc = new JButton("Ajouter");
					addSucc.setSize(new Dimension(150, 30));
					addSucc.setEnabled(false);
					frame1.add(addSucc);
					JTextField SuccT = new JTextField();
					SuccT.setSize(new Dimension(150, 30));
					SuccT.setEditable(false);
					frame1.add(SuccT);
					
					CheckSucc.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(CheckSucc.getSelectedIndex() == 0)
					        	addSucc.setEnabled(false);
					        else
					        	addSucc.setEnabled(true);
						}
					});
					JButton Add = new JButton("Ajouter");
					addPred.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(PredT.getText() == "")
								PredT.setText(""+CheckPred.getSelectedIndex());
							else {
								String[] pred = PredT.getText().split(" ");
								int i=0;
								while(i<pred.length && !(String.valueOf(CheckPred.getSelectedIndex()).equals(pred[i])))
									i++;
								if(i == pred.length)
									PredT.setText(PredT.getText()+" "+CheckPred.getSelectedIndex());
							}
							if(PredT.getText() == "" && SuccT.getText() == "")
								Add.setEnabled(false);
							else
								Add.setEnabled(true);
						}
					});
					addSucc.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(SuccT.getText() == "")
								SuccT.setText(""+CheckSucc.getSelectedIndex());
							else {
								String[] succ = SuccT.getText().split(" ");
								int i=0;
								while(i<succ.length && !(String.valueOf(CheckSucc.getSelectedIndex()).equals(succ[i])))
									i++;
								if(i == succ.length)
									SuccT.setText(SuccT.getText()+" "+CheckSucc.getSelectedIndex());
							}
							if(PredT.getText() == "" && SuccT.getText() == "")
								Add.setEnabled(false);
							else
								Add.setEnabled(true);
						}
					});
					frame1.add(new JLabel());
					
					Add.setSize(new Dimension(150, 30));
					Add.setEnabled(false);
					Add.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String[] succ = SuccT.getText().split(" ");
							String[] pred = PredT.getText().split(" ");
							graphe.ajoutNouveauSommet(succ, pred);
						}
					});
					frame1.add(Add);
					frame1.add(new JLabel());
					
					frame1.setVisible(true);
					//graphe.ajoutSommet();
				}
			}
		});
		supSommet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else
				{
					JFrame frame1 = new JFrame();
					frame1.setLayout(new GridLayout(1, 3));
					frame1.setTitle("Supprimer un sommet");
					frame1.setLocationRelativeTo(null);
				    frame1.setMinimumSize(new Dimension(500, 150));
					JLabel pred = new JLabel("Sommet à supprimer");
					frame1.add(pred);
					JComboBox<String> CheckPred = new JComboBox<String>();
					CheckPred.addItem("choisir");
					for(int i=1; i<=graphe.getD_nb_sommet();i++) {
						CheckPred.addItem("Sommet "+i);
					}
					
					frame1.add(CheckPred);
					JButton addPred = new JButton("Supprimer");
					addPred.setSize(new Dimension(150, 30));
					addPred.setEnabled(false);
					frame1.add(addPred);
					
					CheckPred.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(CheckPred.getSelectedIndex() == 0)
					        	addPred.setEnabled(false);
					        else
					        	addPred.setEnabled(true);
						}
					});
					addPred.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							graphe.supprimerSommet(CheckPred.getSelectedIndex());
						}
					});
					
					frame1.setVisible(true);
				}
			}
		});
		ajoutArc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else
				{
					JFrame frame1 = new JFrame();
					frame1.setLayout(new GridLayout(3, 2));
					frame1.setTitle("Ajouter un nouvel arc");
					frame1.setLocationRelativeTo(null);
				    frame1.setMinimumSize(new Dimension(500, 150));
					JLabel pred = new JLabel("Extrémité 1");
					frame1.add(pred);
					JComboBox<String> CheckPred = new JComboBox<String>();
					CheckPred.addItem("choisir");
					for(int i=1; i<=graphe.getD_nb_sommet();i++) {
						CheckPred.addItem("Sommet "+i);
					}
					
					frame1.add(CheckPred);
					
					JButton Add = new JButton("Ajouter");
					
					
					JLabel succ = new JLabel("Extreminté 2");
					frame1.add(succ);
					JComboBox<String> CheckSucc = new JComboBox<String>();
					CheckSucc.addItem("choisir");
					for(int i=1; i<=graphe.getD_nb_sommet();i++) {
						CheckSucc.addItem("Sommet "+i);
					}
					frame1.add(CheckSucc);
					
					CheckPred.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(CheckPred.getSelectedIndex() == 0 || CheckSucc.getSelectedIndex() == 0)
					        	Add.setEnabled(false);
					        else
					        	Add.setEnabled(true);
						}
					});
					CheckSucc.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(CheckPred.getSelectedIndex() == 0 || CheckSucc.getSelectedIndex() == 0)
					        	Add.setEnabled(false);
					        else
					        	Add.setEnabled(true);
						}
					});
					
					
					
					Add.setSize(new Dimension(150, 30));
					Add.setEnabled(false);
					Add.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							graphe.ajoutNouvelArc(CheckPred.getSelectedIndex(), CheckSucc.getSelectedIndex());
							frame1.setVisible(false);
						}
					});
					frame1.add(Add);
					frame1.add(new JLabel());
					
					frame1.setVisible(true);
					//graphe.ajoutSommet();
				}
			}
		});
		supArc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else
				{
					JFrame frame1 = new JFrame();
					frame1.setLayout(new GridLayout(2, 2));
					frame1.setTitle("Supprimer un arc");
					frame1.setLocationRelativeTo(null);
				    frame1.setMinimumSize(new Dimension(500, 150));
					JLabel pred = new JLabel("Arc à supprimer");
					frame1.add(pred);
					JComboBox<String> CheckPred = new JComboBox<String>();
					CheckPred.addItem("choisir");
					for(int i=0; i<graphe.getD_nb_aretes();i++) {
						CheckPred.addItem("Arc [ "+graphe.getAretePos(i).getD_sommet_depart().getD_numero()+" "+graphe.getAretePos(i).getD_sommet_arrive().getD_numero()+" ]");
					}
					
					frame1.add(CheckPred);
					
					JButton Add = new JButton("Supprimer");
					CheckPred.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(CheckPred.getSelectedIndex() == 0)
					        	Add.setEnabled(false);
					        else
					        	Add.setEnabled(true);
						}
					});
					
					
					Add.setSize(new Dimension(150, 30));
					Add.setEnabled(false);
					Add.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							graphe.supprimerArc(CheckPred.getSelectedIndex()-1);
						}
					});
					frame1.add(Add);
					frame1.add(new JLabel());
					
					frame1.setVisible(true);
					//graphe.ajoutSommet();
				}
			}
		});
		afficheGraphe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else {
					JFrame frame1 = new JFrame();
					
					//frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame1.setBackground(Color.white);
					frame1.setTitle("Graphe".toUpperCase());
					frame1.setSize(new Dimension(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width, GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height));
					
					DessinGraphe de = new DessinGraphe(graphe.getD_nb_sommet(), graphe.getAretes(), "onv");
					frame1.add(de);
					de.setSize(getMaximumSize());
					
					frame1.setVisible(true);
				}
			}
		});
	}

}
