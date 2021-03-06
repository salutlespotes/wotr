package gui;

import wotr.*;
import javax.swing.JFrame;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javafx.scene.control.ProgressBar;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class Window extends JFrame {
    /**
     * 
     */
    // Grahpical variables
    private static final long serialVersionUID = 1L; // je sais pas trop aquoi ca sert mais eclipse me disait de le faire
    private JPanel panelLeft, panelDirection, panelSU, panelImage, panelRight, panelInfoTopPlayer, panelInfoPlayer,
	    panelinfoBottom, panelNPC, panelInventory, panelRoomName;
    private JButton btnWest, btnEast, btnNorth, btnSouth, btnSearch, btnPickUp, btnframeMap, btnUseNPC, btnDropNPC,
	    btnUseItem, btnDropItem;
    private JTextPane textPanePrompt, textPanePlayer, textPaneDesNPC, textPaneDesInventory, txtpnRoomName;
    private JLabel ImageHome, iconPlayer;
    private JProgressBar progressBarHealth, progressBarCorruption, progressBarWeight;
    private JScrollPane scrollBar;
    private DefaultListModel<String> modelNPC, modelInventory;
    // Other variables
    private Game game;
    private JList<String> listNPC, listInventory;
    private NPC selectedNPC;
    private Item selectedItem;
    private JScrollPane scrollPaneDesNPC;
    private JScrollPane scrollPaneDesInventory;
    private JScrollPane scrollPaneListNPC;
    private JScrollPane scrollPaneListInventory;
    private boolean isSearched =false; // to be sure the user look in the room for object before taking them

    /**
     * Constructor of class window. Creates the window.
     */
    public Window() {
	// MAIN FRAME
	this.setVisible(true);
	this.setBounds(0, 0, 1166, 768 - 150);
	this.setLocationRelativeTo(null); // center at stratup
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.getContentPane().setFont(new Font("Roboto", Font.PLAIN, 12));
	this.setOpacity(1);
	this.setResizable(false);
	this.setTitle("World of the Ring");
	this.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.getContentPane().setLayout(null);
	// PANEL LEFT
	panelLeft = new JPanel();
	panelLeft.setBackground(new Color(0, 0, 0));
	panelLeft.setBounds(0, 0, 756, 589);
	this.getContentPane().add(panelLeft);
	panelLeft.setLayout(null);
	// PANEL LEFT - Direction
	panelDirection = new JPanel();
	panelDirection.setBounds(162, 426, 148, 151);
	panelDirection.setOpaque(false);
	panelDirection.setBackground(new Color(0, 0, 0, 0));
	panelLeft.add(panelDirection);
	panelDirection.setLayout(new BorderLayout(0, 0));
	// west
	btnWest = new JButton(new ImageIcon(this.getClass().getResource("/westArrow.png")));
	btnWest.setMnemonic(KeyEvent.VK_LEFT);
	btnWest.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
		    game.getPlayer().goRoom("west");
		    routineGoRoom();
		}
	    }
	});
	btnWest.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent arg0) {
		game.getPlayer().goRoom("west");
		routineGoRoom();
	    }
	});
	btnWest.setToolTipText("West");
	btnWest.setOpaque(false);
	btnWest.setBorderPainted(false);
	btnWest.setContentAreaFilled(false);
	panelDirection.add(btnWest, BorderLayout.WEST);
	// east
	btnEast = new JButton(new ImageIcon(this.getClass().getResource("/eastArrow.png")));
	btnEast.setMnemonic(KeyEvent.VK_RIGHT);
	btnEast.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
		    game.getPlayer().goRoom("east");
		    routineGoRoom();
		}
	    }
	});
	btnEast.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		game.getPlayer().goRoom("east");
		routineGoRoom();
		;
	    }
	});
	btnEast.setToolTipText("East");
	btnEast.setOpaque(false);
	btnEast.setBorderPainted(false);
	btnEast.setContentAreaFilled(false);
	panelDirection.add(btnEast, BorderLayout.EAST);
	//
	btnNorth = new JButton(new ImageIcon(this.getClass().getResource("/northArrow.png")));
	btnNorth.setMnemonic(KeyEvent.VK_UP);
	btnNorth.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_UP) {
		    game.getPlayer().goRoom("north");
		    routineGoRoom();
		}
	    }
	});
	btnNorth.setToolTipText("North");
	btnNorth.setOpaque(false);
	btnNorth.setBorderPainted(false);
	btnNorth.setContentAreaFilled(false);
	btnNorth.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		game.getPlayer().goRoom("north");
		routineGoRoom();
	    }
	});
	panelDirection.add(btnNorth, BorderLayout.NORTH);
	// south
	btnSouth = new JButton(new ImageIcon(this.getClass().getResource("/southArrow.png")));
	btnSouth.setMnemonic(KeyEvent.VK_DOWN);
	btnSouth.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
		    game.getPlayer().goRoom("south");
		    routineGoRoom();
		}
	    }
	});
	;
	btnSouth.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		game.getPlayer().goRoom("south");
		routineGoRoom();
		;
	    }
	});
	btnSouth.setToolTipText("South");
	btnSouth.setOpaque(false);
	btnSouth.setBorderPainted(false);
	btnSouth.setContentAreaFilled(false);
	panelDirection.add(btnSouth, BorderLayout.SOUTH);
	// PANEL LEFT - Panel tools
	panelSU = new JPanel();
	panelSU.setBounds(12, 426, 126, 151);
	panelSU.setOpaque(false);
	panelSU.setBackground(new Color(0, 0, 0, 0));
	panelLeft.add(panelSU);
	panelSU.setLayout(new GridLayout(3, 1, 0, 0));
	// PANEL LEFT - Panel tools - Search
	btnSearch = new JButton(new ImageIcon(new ImageIcon(this.getClass().getResource("/eye-icon.png")).getImage()
		.getScaledInstance(70, 40, Image.SCALE_DEFAULT)));
	btnSearch.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		isSearched = true;
		ArrayList<Item> arrayItem = game.getPlayer().getCurrentRoom().getItemList();
		ArrayList<NPC> arrayNPC = game.getPlayer().getCurrentRoom().getNPCList();
		if (!arrayItem.isEmpty()) {
		    String txt = "Here you can find : ";
		    for (Item item : arrayItem) {
			txt += "\n - " + item.getName() + ": " + item.getDescription() + " (Weight: " + item.getWeight()
				+ ")";
		    }
		    JOptionPane.showMessageDialog(getRootPane(), txt, "Avaible items", JOptionPane.INFORMATION_MESSAGE);
		} else {
		    JOptionPane.showMessageDialog(getRootPane(),
			    "Nothing is present here, perhaps you will be  lucky next time !", "Nothing here",
			    JOptionPane.INFORMATION_MESSAGE);
		}
		if (!arrayNPC.isEmpty()) {
		    String txt = "Here you can meet : ";
		    for (NPC npc : arrayNPC) {
			txt += ("\n - " + npc.getName() + ": " + npc.getDescription());
		    }
		    JOptionPane.showMessageDialog(getRootPane(), txt, "Potential friends",
			    JOptionPane.INFORMATION_MESSAGE);
		} else {
		    JOptionPane.showMessageDialog(getRootPane(), "Nobody is here, you\'re alone with yourself.",
			    "Nobody is here", JOptionPane.INFORMATION_MESSAGE);
		}
	    }
	});
	btnSearch.setToolTipText("Search");
	btnSearch.setOpaque(false);
	btnSearch.setBorderPainted(false);
	btnSearch.setContentAreaFilled(false);
	panelSU.add(btnSearch);
	// PANEL LEFT - Panel tools - PickUp
	btnPickUp = new JButton(new ImageIcon(this.getClass().getResource("/Hand-icon2.png")));
	btnPickUp.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		if (isSearched) {
		    isSearched = false;
		    
		    for (Item item : new ArrayList<Item>(game.getPlayer().getCurrentRoom().getItemList())) {
			if (game.getPlayer().pickUpItem(item)) {
			    JOptionPane.showMessageDialog(getRootPane(),
				    item.getName() + " has been added to you inventory", item.getName() + " added",
				    JOptionPane.INFORMATION_MESSAGE);
			} else {
			    JOptionPane.showMessageDialog(getRootPane(), item.getName() + " (weight: "
				    + item.getWeight() + " ) " + "is too heavy for your inventory (current weight: "
				    + game.getPlayer().getWeight() + "/" + game.getPlayer().getMaximumInventoyWeight()
				    + ".\n Drop or use you other items if you want ot pickit up. ",
				    item.getName() + " not added", JOptionPane.INFORMATION_MESSAGE);
			}
			updateAll();
		    }
		    for (NPC npc : new ArrayList<NPC>(game.getPlayer().getCurrentRoom().getNPCList())) {
			if (game.getPlayer().pickUpNPC(npc) && !npc.getClass().getSimpleName().equals("Enemy")) { // verification deja faite par le get mais on sait jamais
			    System.out.println(npc.getClass().getSimpleName());
			    JOptionPane.showMessageDialog(getRootPane(),
				    npc.getName() + "has joined your fellowship \n" + npc.getDescription()
					    + "\n - Health points: " + npc.getHpPower() + "\n - Corruption points: "
					    + npc.getCpPower(),
				    npc.getName() + " added", JOptionPane.INFORMATION_MESSAGE);
			}
			updateAll();
		    }
		} else {
		    JOptionPane.showMessageDialog(getRootPane(), "You must search for object before taking them.",
			    "pickUp error", JOptionPane.INFORMATION_MESSAGE);
		}
		
	    }
	});
	btnPickUp.setToolTipText("Pick Up");
	btnPickUp.setOpaque(false);
	btnPickUp.setBorderPainted(false);
	btnPickUp.setContentAreaFilled(false);
	panelSU.add(btnPickUp);
	// PANEL LEFT - Panel tools - Map
	btnframeMap = new JButton(new ImageIcon(this.getClass().getResource("/map2.png")));
	btnframeMap.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		JFrame frameMapImg = new JFrame();
		frameMapImg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameMapImg.setVisible(true);
		frameMapImg.setTitle("Map of the game");
		JPanel panelImg = new JPanel();
		JLabel myImg = new JLabel(new ImageIcon(this.getClass().getResource("/fullMapView.jpg")));
		panelImg.add(myImg);
		frameMapImg.getContentPane().add(panelImg);
		frameMapImg.pack();
		frameMapImg.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
			myImg.setIcon(new ImageIcon(this.getClass().getResource("/fullMapViewZoom.jpg")));
			scrollBar = new JScrollPane();
			scrollBar.setViewportView(myImg);
			frameMapImg.getContentPane().add(scrollBar);
		    }
		});
	    }
	});
	btnframeMap.setToolTipText("Map of the frame");
	btnframeMap.setOpaque(false);
	btnframeMap.setBorderPainted(false);
	btnframeMap.setContentAreaFilled(false);
	panelSU.add(btnframeMap);
	//// PANEL LEFT - Panel prompt
	JScrollPane scrollPanePrompt = new JScrollPane();
	scrollPanePrompt
		.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(160, 82, 45), new Color(160, 82, 45)));
	scrollPanePrompt.setMaximumSize(new Dimension(424, 160));
	scrollPanePrompt.setBounds(320, 426, 424, 151);
	panelLeft.add(scrollPanePrompt);
	textPanePrompt = new JTextPane();
	textPanePrompt.setSelectedTextColor(new Color(238, 232, 170));
	textPanePrompt.setBorder(null);
	scrollPanePrompt.setViewportView(textPanePrompt);
	textPanePrompt.setToolTipText("Command prompt : displays a lot of informations");
	textPanePrompt.setFont(new Font("Monotype Corsiva", Font.PLAIN, 18));
	textPanePrompt.setForeground(new Color(0, 0, 0));
	textPanePrompt.setBackground(new Color(240, 230, 140));
	//// PANEL LEFT - Room Name
	panelRoomName = new JPanel();
	panelRoomName.setOpaque(false);
	panelRoomName.setBounds(12, 0, 732, 44);
	panelLeft.add(panelRoomName);
	txtpnRoomName = new JTextPane();
	txtpnRoomName.setEditable(false);
	txtpnRoomName.setMargin(new Insets(0, 3, 3, 3));
	txtpnRoomName.setForeground(new Color(218, 165, 32));
	txtpnRoomName.setFont(new Font("Monotype Corsiva", Font.PLAIN, 35));
	txtpnRoomName.setText("Room name");
	txtpnRoomName.setOpaque(false);
	panelRoomName.add(txtpnRoomName);
	//// PANEL LEFT - Panel Image
	panelImage = new JPanel();
	ImageHome = new JLabel();// NE PAS METTRE D4IMAGE ICIII
	panelImage.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
	panelImage.setBounds(12, 42, 732, 373);
	panelImage.add(ImageHome);
	panelLeft.add(panelImage);
	panelImage.setLayout(new GridLayout(0, 1, 0, 0));
	//
	//
	// PANELRIGHT
	panelRight = new JPanel();
	panelRight.setBackground(new Color(54, 29, 0));
	panelRight.setBorder(null);
	panelRight.setBounds(756, 0, 404, 589);
	this.getContentPane().add(panelRight);
	panelRight.setLayout(new GridLayout(3, 1, 0, 0));
	// PANELRIGHT - Info player
	panelInfoPlayer = new JPanel();
	panelInfoPlayer.setOpaque(false);
	panelInfoPlayer.setBackground(new Color(255, 255, 255));
	panelInfoPlayer.setForeground(Color.BLACK);
	panelRight.add(panelInfoPlayer);
	panelInfoPlayer.setLayout(new GridLayout(2, 0, 0, 0));
	// PANELRIGHT - Info player - Top
	panelInfoTopPlayer = new JPanel();
	panelInfoTopPlayer.setOpaque(false);
	panelInfoTopPlayer.setBorder(null);
	panelInfoTopPlayer.setLayout(null);
	panelInfoPlayer.add(panelInfoTopPlayer);
	//
	textPanePlayer = new JTextPane();
	textPanePlayer.setForeground(new Color(240, 230, 140));
	textPanePlayer.setOpaque(false);
	textPanePlayer.setFont(new Font("Monotype Corsiva", Font.PLAIN, 19));
	textPanePlayer.setText("You are Frodo Baggins and you have to drop the ring in the Mordor.");
	textPanePlayer.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	textPanePlayer.setBounds(96, 12, 296, 74);
	panelInfoTopPlayer.add(textPanePlayer);
	textPanePlayer.setEditable(false);
	//
	iconPlayer = new JLabel("Frodo");
	iconPlayer.setBorder(new LineBorder(new Color(139, 69, 19), 1, true));
	iconPlayer.setToolTipText("Frodo Baggins");
	iconPlayer.setOpaque(true);
	iconPlayer.setIcon(new ImageIcon((this.getClass().getResource("/frodo.png"))));
	iconPlayer.setBounds(10, 12, 76, 74);
	panelInfoTopPlayer.add(iconPlayer);
	//
	panelinfoBottom = new JPanel();
	panelinfoBottom.setOpaque(false);
	panelInfoPlayer.add(panelinfoBottom);
	// PANELRIGHT - Info player - Bottom
	panelinfoBottom.setLayout(null);
	progressBarHealth = new JProgressBar();
	progressBarHealth.setBorderPainted(false);
	progressBarHealth.setFont(new Font("Monotype Corsiva", Font.PLAIN, 18));
	progressBarHealth.setOpaque(true);
	progressBarHealth.setStringPainted(true);
	progressBarHealth.setString("Hp : 0 %");
	progressBarHealth.setBounds(12, 9, 380, 20);
	progressBarHealth.setToolTipText("Health points, if they are equal to 0 you're dead !");
	progressBarHealth.setForeground(Color.RED);
	progressBarHealth.setBackground(Color.WHITE);
	panelinfoBottom.add(progressBarHealth);
	//
	progressBarCorruption = new JProgressBar();
	progressBarCorruption.setBorderPainted(false);
	progressBarCorruption.setFont(new Font("Monotype Corsiva", Font.PLAIN, 18));
	progressBarCorruption.setOpaque(true);
	progressBarCorruption.setToolTipText("Corruption points, if they are equal to 100 you're dead !");
	progressBarCorruption.setString("Cp : 0 %");
	progressBarCorruption.setBounds(12, 38, 380, 20);
	progressBarCorruption.setStringPainted(true);
	progressBarCorruption.setBackground(Color.WHITE);
	panelinfoBottom.add(progressBarCorruption);
	//
	progressBarWeight = new JProgressBar();
	progressBarWeight.setBorderPainted(false);
	progressBarWeight.setBackground(Color.WHITE);
	progressBarWeight.setStringPainted(true);
	progressBarWeight.setToolTipText("Weight of your bag.");
	progressBarWeight.setOpaque(true);
	progressBarWeight.setForeground(new Color(143, 188, 143));
	progressBarWeight.setFont(new Font("Monotype Corsiva", Font.PLAIN, 18));
	progressBarWeight.setString("Weight : 0/50");
	progressBarWeight.setMaximum(50);
	progressBarWeight.setBounds(12, 67, 380, 20);
	panelinfoBottom.add(progressBarWeight);
	// PANELRIGHT - NPC
	panelNPC = new JPanel();
	panelNPC.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Fellowship",
		TitledBorder.CENTER, TitledBorder.TOP, null, new Color(238, 232, 170)));
	panelNPC.setOpaque(false);
	panelNPC.setLayout(null);
	panelRight.add(panelNPC);
	// PANELRIGHT - Info player - Btn
	btnUseNPC = new JButton("Ask for help");
	btnUseNPC.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent arg0) {
		if (selectedNPC != null) {
		    if (!selectedNPC.getAlreadyUsed()) {
			if (selectedNPC.getClass().getSimpleName().equals("Magician")) {
			    DefaultListModel<String> modelNPCUsed = new DefaultListModel<String>();
			    for (NPC npc : game.getPlayer().getFellowship()) {// parcour pour recup les npc already used
				if (npc.getAlreadyUsed() && !npc.getClass().getSimpleName().equals("Magician")) {
				    modelNPCUsed.addElement(npc.getName());
				}
			    } // maintenant qu'on a la liste des npc already used
			      // on cr�e un objet de cette lste
			    if (!modelNPCUsed.isEmpty()) {
				final Object[] arrayStringNPC = modelNPCUsed.toArray();
				String favoritePizza;// on declare une variable qui contiendra le nom du npc choisi par l'user
				favoritePizza = (String) JOptionPane.showInputDialog(getRootPane(),
					"Which member of your felloship will see his powers restored by"
						+ selectedNPC.getName() + " ?",
					"Choose a member", JOptionPane.QUESTION_MESSAGE, null, arrayStringNPC,
					arrayStringNPC[0]);
				// a ce niveau l'utilisateur a fermer la popup
				if (!favoritePizza.isEmpty()) {// on regarde s'il a choisi un item //PROVOQUE UN NULL
				    // on cherche ce nom dans notre fellowship
				    for (NPC npc : game.getPlayer().getFellowship()) {
					if (npc.getName().equals(favoritePizza)) { // si on l'a alors
					    Magician theMag = (Magician) selectedNPC; // on downcast gandalf de type npc vers magician pour utiliser ses pouvoirs
					    theMag.use(npc);// on utilise les pouvoirs du magician sur le npc selectionn� par user
					    // on met un pett sg pour user
					    JOptionPane.showMessageDialog(
						    getRootPane(), theMag.getName() + " restored the powers of "
							    + npc.getName() + " !",
						    "Powers restored", JOptionPane.INFORMATION_MESSAGE);
					} // si on a pastrouver le player sel daans la fellowship
				    }
				} // le gars a rien selectionn� alors on fait rein
			    } else {// pas de npc already used
				JOptionPane.showMessageDialog(getRootPane(),
					"Nobody has already used his powers...\n" + selectedNPC.getName() + " is sad.",
					"Impossible to use", JOptionPane.INFORMATION_MESSAGE);
			    }
			} else // selected != magician
			{
			    game.getPlayer().use(selectedNPC);
			    JOptionPane.showMessageDialog(getRootPane(),
				    selectedNPC.getName() + " used is powers (" + selectedNPC.getDescription() + ").",
				    "Powers used", JOptionPane.INFORMATION_MESSAGE);
			}
		    } else {// already used = true
			JOptionPane.showMessageDialog(getRootPane(),
				selectedNPC.getName()
					+ " has already been used, you cannot use it twice.\n However you can restore his powers using a potion or thans to magician's abilities.",
				"Powers already used", JOptionPane.WARNING_MESSAGE);
		    }
		} else {// selected=null
		    JOptionPane.showMessageDialog(getRootPane(), "Please select a member of you fellowship..",
			    "Wotr : warning", JOptionPane.WARNING_MESSAGE);
		}
		selectedNPC = null;
		updateAll();
	    }
	});
	btnUseNPC.setFont(new Font("Roboto", Font.PLAIN, 14));
	btnUseNPC.setBounds(166, 22, 108, 39);
	panelNPC.add(btnUseNPC);
	// PANELRIGHT - Info player -btn - drop
	btnDropNPC = new JButton("Leave here");
	btnDropNPC.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		if (selectedNPC != null) {
		    game.getPlayer().dropNPC(selectedNPC);
		    selectedNPC = null;
		    updateAll();
		} else {
		    JOptionPane.showMessageDialog(getRootPane(),
			    "Please select a member of your community you want to leave here...", "Wotr : warning",
			    JOptionPane.WARNING_MESSAGE);
		}
	    }
	});
	btnDropNPC.setFont(new Font("Roboto", Font.PLAIN, 14));
	btnDropNPC.setBounds(284, 22, 108, 39);
	panelNPC.add(btnDropNPC);
	scrollPaneDesNPC = new JScrollPane();
	scrollPaneDesNPC.setBounds(166, 72, 226, 113);
	panelNPC.add(scrollPaneDesNPC);
	// PANELRIGHT - NPC - Description
	textPaneDesNPC = new JTextPane();
	scrollPaneDesNPC.setViewportView(textPaneDesNPC);
	textPaneDesNPC.setFont(new Font("Monotype Corsiva", Font.PLAIN, 18));
	textPaneDesNPC.setBackground(new Color(238, 232, 170));
	textPaneDesNPC.setSelectedTextColor(new Color(25, 25, 112));
	textPaneDesNPC.setText("Description");
	textPaneDesNPC.setEditable(false);
	// PANELRIGHT - NPC - List
	modelNPC = new DefaultListModel<String>();
	modelNPC.addElement("");
	scrollPaneListNPC = new JScrollPane();
	scrollPaneListNPC.setBounds(10, 22, 146, 163);
	panelNPC.add(scrollPaneListNPC);
	listNPC = new JList<String>(modelNPC);
	scrollPaneListNPC.setViewportView(listNPC);
	listNPC.setBackground(new Color(238, 232, 170));
	listNPC.addListSelectionListener(new ListSelectionListener() {
	    public void valueChanged(ListSelectionEvent arg0) {
		String stringNPC = listNPC.getSelectedValue();
		for (NPC npc : game.getPlayer().getFellowship()) {
		    if (npc.getName().equals(stringNPC)) {
			selectedNPC = npc;
			updateDesNPC();
		    }
		}
	    }
	});
	listNPC.setFont(new Font("Monotype Corsiva", Font.PLAIN, 17));
	listNPC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	// PANELRIGHT - Inventory
	panelInventory = new JPanel();
	panelInventory.setBorder(new TitledBorder(null, "Inventory", TitledBorder.CENTER, TitledBorder.TOP, null,
		new Color(238, 232, 170)));
	panelInventory.setOpaque(false);
	panelInventory.setLayout(null);
	panelRight.add(panelInventory);
	// PANELRIGHT - Inventory - btn
	btnUseItem = new JButton("Use");
	btnUseItem.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		if (selectedItem != null) {
		    //
		    if (selectedItem.getClass().getSimpleName().equals("Key")) {
			Key selectedKey = (Key) selectedItem;
			Door door = selectedKey.getDoor();
			if (game.getPlayer().getCurrentRoom().getExits().containsValue(door)) {
			    game.getPlayer().use(selectedItem);
			    selectedItem = null;
			    JOptionPane.showMessageDialog(getRootPane(), "You unlocked the door to " + door.toString(),
				    "Wotr : Success", JOptionPane.INFORMATION_MESSAGE);
			} else {
			    JOptionPane.showMessageDialog(getRootPane(),
				    "Use can use this key (" + selectedItem.getName() + ") here.", "Wotr : warning",
				    JOptionPane.WARNING_MESSAGE);
			}
		    }
		    // TODO Implementer potion
		    else if (selectedItem.getClass().getSimpleName().equals("Potion")) {
			DefaultListModel<String> modelNPCUsed = new DefaultListModel<String>();
			for (NPC npc : game.getPlayer().getFellowship()) {// parcour pour recup les npc already used
			    if (npc.getAlreadyUsed()) {
				modelNPCUsed.addElement(npc.getName());
			    }
			} // maintenant qu'on a la liste des npc already used
			  // on cr�e un objet de cette lste
			if (!modelNPCUsed.isEmpty()) {
			    final Object[] arrayStringNPC = modelNPCUsed.toArray();
			    String favoritePizza;// on declare une variable qui contiendra le nom du npc choisi par l'user
			    favoritePizza = (String) JOptionPane.showInputDialog(getRootPane(),
				    "Which member of your fellowship will see his powers restored by"
					    + selectedItem.getName() + " ?",
				    "Choose a member", JOptionPane.QUESTION_MESSAGE, null, arrayStringNPC,
				    arrayStringNPC[0]);
			    // a ce niveau l'utilisateur a fermer la popup
			    if (!favoritePizza.isEmpty()) {// on regarde s'il a choisi un npc
				// on cherche ce nom dans notre fellowship
				for (NPC npc : game.getPlayer().getFellowship()) {
				    if (npc.getName().equals(favoritePizza)) { // si on l'a alors
					Potion thePotion = (Potion) selectedItem; // on downcast potion the type item vers type potion
					thePotion.use(npc);// on utilise les pouvoirs potion sur npc selectionn� par user
					JOptionPane.showMessageDialog(getRootPane(),
						thePotion.getName() + " restored the powers of " + npc.getName() + " !",
						thePotion.getName() + " used.", JOptionPane.INFORMATION_MESSAGE);
					game.getPlayer().dropItem(thePotion); // normalemment se fait auto mais pas ici car on utilise pas le use classique...
					selectedItem = null;
				    } // si on a pastrouver le player sel daans la fellowship
				}
			    } // le gars a rien selectionn� alors on fait rein
			} else {// pas de npc already used
			    JOptionPane.showMessageDialog(getRootPane(),
				    "Nobody has already used his powers...\n Impossible to use "
					    + selectedItem.getName() + " .",
				    "Impossible to use" + selectedItem.getName(), JOptionPane.INFORMATION_MESSAGE);
			}
		    } else {// ce n'est ni une clef ni un anneau, donc de la food
			if (selectedItem.getName() != "Ring of power") {
			    game.getPlayer().use(selectedItem);
			    JOptionPane.showMessageDialog(getRootPane(), selectedItem.getName() + " has been used.",
				    selectedItem.getName() + " used.", JOptionPane.INFORMATION_MESSAGE);
			    selectedItem = null;
			}
		    }
		} else {
		    JOptionPane.showMessageDialog(getRootPane(), "Please select an item if you want to use it...",
			    "Wotr : warning", JOptionPane.WARNING_MESSAGE);
		}
		updateAll();
	    }
	});
	btnUseItem.setFont(new Font("Roboto", Font.PLAIN, 16));
	btnUseItem.setBounds(166, 21, 108, 40);
	panelInventory.add(btnUseItem);
	//
	btnDropItem = new JButton("Drop");
	btnDropItem.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		if (selectedItem != null) {
		    game.getPlayer().dropItem(selectedItem);
		    selectedItem = null;
		    updateAll();
		} else {
		    JOptionPane.showMessageDialog(getRootPane(), "Please select an item if you want to drop it...",
			    "Wotr : warning", JOptionPane.WARNING_MESSAGE);
		}
	    }
	});
	btnDropItem.setFont(new Font("Roboto", Font.PLAIN, 16));
	btnDropItem.setBounds(284, 21, 108, 40);
	panelInventory.add(btnDropItem);
	scrollPaneDesInventory = new JScrollPane();
	scrollPaneDesInventory.setBounds(166, 72, 226, 113);
	panelInventory.add(scrollPaneDesInventory);
	// PANELRIGHT - Inventory - description
	textPaneDesInventory = new JTextPane();
	scrollPaneDesInventory.setViewportView(textPaneDesInventory);
	textPaneDesInventory.setFont(new Font("Monotype Corsiva", Font.PLAIN, 18));
	textPaneDesInventory.setBackground(new Color(238, 232, 170));
	textPaneDesInventory.setText("Description");
	textPaneDesInventory.setEditable(false);
	// PANELRIGHT - Inventory - list
	modelInventory = new DefaultListModel<String>();
	modelInventory.addElement("");
	scrollPaneListInventory = new JScrollPane();
	scrollPaneListInventory.setBounds(10, 21, 146, 164);
	panelInventory.add(scrollPaneListInventory);
	listInventory = new JList<String>(modelInventory);
	scrollPaneListInventory.setViewportView(listInventory);
	listInventory.setBackground(new Color(238, 232, 170));
	listInventory.addListSelectionListener(new ListSelectionListener() {
	    public void valueChanged(ListSelectionEvent e) {
		String stringItem = listInventory.getSelectedValue(); // vaut null
		for (Item item : game.getPlayer().getInventory()) {
		    if (item.getName().equals(stringItem)) {
			selectedItem = item;
			updateDesItem();
		    }
		}
	    }
	});
	listInventory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	listInventory.setFont(new Font("Monotype Corsiva", Font.PLAIN, 17));
    }

    /**
     * Add a line to the command prompt, same use as the println()
     * 
     * @param script
     *            The line which has to be added
     */
    public void setScript(String script) {
	String currentText = textPanePrompt.getText();
	textPanePrompt.setText(currentText + "\n" + script);
    }

    /**
     * @param btnWest
     *            the btnWest to set
     */
    public void setBtnWest(boolean b) {
	btnWest.setEnabled(b);
    }

    /**
     * @param btnEast
     *            the btnEast to set
     */
    public void setBtnEast(boolean b) {
	btnEast.setEnabled(b);
    }

    /**
     * @param btnNorth
     *            the btnNorth to set
     */
    public void setBtnNorth(boolean b) {
	btnNorth.setEnabled(b);
    }

    /**
     * @param btnSouth
     *            the btnSouth to set
     */
    public void setBtnSouth(boolean b) {
	btnSouth.setEnabled(b);
    }

    /**
     * method to set the butn use for item to disabled if selected items is the ring
     * @param selectedItem
     */
    public void updateUseRing(Item selectedItem) {
	try {
	    if (selectedItem.getClass().getSimpleName().equalsIgnoreCase("Ring")) {
		btnUseItem.setEnabled(false);
	    }
	} catch (Exception e) {
	    // Pas de soucis c'est normal
	}
    }

    /**
     * @param textPanePlayer
     *            the textPanePlayer to set
     */
    public void setTextPanePlayer(JTextPane textPanePlayer) {
	this.textPanePlayer = textPanePlayer;
    }

    /**
     * @param textPaneDesNPC
     *            the textPaneDesNPC to set
     */
    public void setTextPaneDesNPC(String description) {
	textPaneDesNPC.setText(description);
    }

    /**
     * @param textPanelDesInventory
     *            the textPanelDesInventory to set
     */
    public void settextPanelDesInventory(String description) {
	textPaneDesInventory.setText(description);
    }

    /**
     * @param progressBarHealth
     *            the progressBarHealth to set
     */
    public void setProgressBarHealth(int percentHealth) {
	this.progressBarHealth.setValue(percentHealth);
    }

    /**
     * @param progressBarCorruption
     *            the progressBarCorruption to set
     */
    public void setProgressBarCorruption(int percentCorruption) {
	progressBarCorruption.setValue(percentCorruption);
    }

    /**
     * set the visibility of direction buttons according to avaible exits in the current room
     * @param bEast
     * @param bNorth
     * @param bWest
     * @param bSouth
     */
    public void updateDirectionButton(boolean bEast, boolean bNorth, boolean bWest, boolean bSouth) {
	btnNorth.setEnabled(bNorth);
	btnSouth.setEnabled(bSouth);
	btnWest.setEnabled(bWest);
	btnEast.setEnabled(bEast);
    }

    /**
     * updte the three progress bars according to frodo cp,hp and bag's weight
     * @param percentHealth
     * @param percentCorruption
     */
    public void updateProgressBar() {
	int percentCorruption = game.getPlayer().getCorruption();
	int percentHealth = game.getPlayer().getHp();
	int weight = game.getPlayer().getWeight();
	int maxWeight = game.getPlayer().getMaximumInventoyWeight();
	progressBarCorruption.setValue(percentCorruption);
	progressBarCorruption.setString("Cp:" + percentCorruption + " %");
	progressBarHealth.setValue(percentHealth);
	progressBarHealth.setString("Hp:" + percentHealth + " %");
	progressBarWeight.setValue(weight);
	progressBarWeight.setString("Weight: " + weight + "/" + maxWeight);
    }

    /**
     * update the image whuie entering in the room
     * @param url the path to the image
     */
    public void updateImage(String url) {
	URL theURL = this.getClass().getResource(url);
	ImageIcon image = new ImageIcon(theURL);
	ImageHome.setIcon(image);
    }

    /**
     * Set the descript of the room in the prompt while enteriing in the room
     * @param description of the room
     */
    public void updatePromptWithRoomDescription() {
	setScript(game.getPlayer().getCurrentRoom().getDescription());
    }

    public void updatePromptWithRoomScript() {
	setScript(game.getPlayer().getCurrentRoom().getScript());
    }

    public void updateRoomName() {
	txtpnRoomName.setText(game.getPlayer().getCurrentRoom().getDescription());
    }

    /**
     * update the the JList of item
     */
    public void updateInventory() {
	modelInventory.clear();
	for (Item item : game.getPlayer().getInventory()) {
	    modelInventory.addElement(item.getName());
	}
    }

    /**
     * update the list of npc in the Jist
     */
    public void updateListNPC() {
	modelNPC.clear();
	for (NPC npc : game.getPlayer().getFellowship()) {
	    modelNPC.addElement(npc.getName());
	}
    }

    /**
     * update the text in the panelText dezcription of npc
     */
    public void updateDesNPC() {
	if (selectedNPC != null) {
	    setTextPaneDesNPC(selectedNPC.getDescription() + "\n - type: " + selectedNPC.getClass().getSimpleName()
		    + "\n - used: " + selectedNPC.getAlreadyUsed());
	} else {
	    setTextPaneDesNPC("");
	}
    }

    /**
     * update the text in the panelText dezcription of item
     */
    public void updateDesItem() {
	if (selectedItem != null) {
	    if (selectedItem.getClass().getSimpleName().equals("Food")) {
		Food selectedFood = (Food) selectedItem;
		settextPanelDesInventory(selectedFood.getDescription() + "\n - Weight: " + selectedFood.getWeight()
			+ "\n - Calory: " + selectedFood.getCalory());
	    } else {
		settextPanelDesInventory(
			selectedItem.getDescription() + " ( weight : " + selectedItem.getWeight() + ")");
	    }
	} else {
	    settextPanelDesInventory("");
	}
	;
    }

    /**
     * check if an ennemy is in the current room, if true it attacks frodo
     */
    public void tryToAttack() {
	for (NPC npc : game.getPlayer().getCurrentRoom().getNPCList()) {
	    npc.setPlayer(game.getPlayer());
	    if (npc.getClass().getSimpleName().equals("Enemy")) {
		JOptionPane.showMessageDialog(getRootPane(),
			npc.getName() + " is attacking you ! \n" + npc.getDescription() + "\n" + " - Corruption : +"
				+ npc.getCpPower() + "\n - Health: " + npc.getHpPower(),
			npc.getName() + " alert !", JOptionPane.WARNING_MESSAGE);
		npc.use();
	    }
	}
    }

    /**
     * method wich contains all action performed after moving to an other room
     */
    public void routineGoRoom() {
	String textCurrentRoomExits = game.getPlayer().getCurrentRoom().toStringExits();
	updateAll();
	tryToAttack();
	updateAll();
	updatePromptWithRoomDescription();
	updatePromptWithRoomScript();
	setScript(textCurrentRoomExits);
    }

    public void updateAll() {
	this.updateUseRing(selectedItem);
	this.updateProgressBar();
	// check if dead
	if (!game.getPlayer().isAlive()) {
	    JOptionPane.showMessageDialog(getRootPane(), "You are dead. It is GAME OVER !", "Game Over",
		    JOptionPane.ERROR_MESSAGE);
	    System.exit(1);
	}
	if (game.win()) {
	    JOptionPane.showMessageDialog(getRootPane(),
		    "Congratulation ! You win !!!\n You saved the middle earth againt the terrible Sauron.\n You can now rest in peace with the elfs.",
		    "YOU WIN", JOptionPane.INFORMATION_MESSAGE);
	    System.exit(1);
	}
	;
	this.updateListNPC(); // a checker
	this.updateInventory(); // a checker
	this.updateDesItem();
	this.updateDesNPC();
	this.updateRoomName();
	boolean bNorth, bEast, bSouth, bWest;
	bNorth = game.getPlayer().getCurrentRoom().getExits().containsKey("north");
	bSouth = game.getPlayer().getCurrentRoom().getExits().containsKey("south");
	bWest = game.getPlayer().getCurrentRoom().getExits().containsKey("west");
	bEast = game.getPlayer().getCurrentRoom().getExits().containsKey("east");
	this.updateDirectionButton(bEast, bNorth, bWest, bSouth);
	this.updateImage(game.getPlayer().getCurrentRoom().getImg());
    }

    /**
     * @param game2
     */
    public void setGame(Game game) {
	this.game = game;
    }
}