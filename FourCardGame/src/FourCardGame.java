import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FourCardGame extends JFrame {
	
	Deck d = new Deck();
	JButton deal = new JButton("Deal");
	Board table = new Board();
	JPanel deck = new JPanel();
	JPanel discard =new JPanel();
	ImageIcon cb = new ImageIcon("CardBack.png");
	ImageIcon disC = new ImageIcon("Discard.png");
	JLabel cardBack = new JLabel(cb);
	JLabel discardBackground = new JLabel(disC);
	JLabel lblTL = new JLabel();
	JLabel lblTR = new JLabel();
	JLabel lblBL = new JLabel();
	JLabel lblBR = new JLabel();
	ImageIcon cardImage = new ImageIcon("CardBack.png");
	CardStack discardCards = new CardStack();
	int pressed = 0;
	Card c = new Card();
	int hold;
	java.io.File file1 = new java.io.File("scores.txt");
	
	

	
	public FourCardGame(){
		
		d.shuffle();
		lblTL = new JLabel(cardImage);
		lblTR = new JLabel(cardImage);
		lblBL = new JLabel(cardImage);
		lblBR = new JLabel(cardImage);
		deck.add(cardBack);
		discard.add(discardBackground);
		table.setLayout(new GridLayout(2,2,5,5));
		table.add(lblTL);
		table.add(lblTR);
		table.add(lblBL);
		table.add(lblBR);
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		
		this.add(deck);
		this.add(table);
		this.add(deal);
		this.add(discard);
		
		/*this.add(deck, BorderLayout.NORTH);
		this.add(table, BorderLayout.CENTER);
		this.add(deal, BorderLayout.EAST);
		this.add(discard, BorderLayout.SOUTH);*/
		
		deal.addActionListener(new DealListener());
		lblTL.addMouseListener(new CardListener());
		lblTR.addMouseListener(new CardListener());
		lblBL.addMouseListener(new CardListener());
		lblBR.addMouseListener(new CardListener());
		
		
		
	}
	
	
	

	
	public static void main(String[] args) throws Exception {
		JFrame frame = new FourCardGame();
		frame.setTitle("Four Card Game");
		frame.setSize(400, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		java.io.File file = new java.io.File("scores.txt");
	
		
		
		
		if(!file.exists()){
		java.io.PrintWriter output = new java.io.PrintWriter(file);
		output.println(0);
		output.println(0);
		output.close();
		}
		
		

	}
	
	class DealListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(d.isEmpty()){
				try {
					writeScore(false);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					newGame();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			else{
			table.topLeft.insert(d.dealTopCard());
			table.topRight.insert(d.dealTopCard());
			table.bottomLeft.insert(d.dealTopCard());
			table.bottomRight.insert(d.dealTopCard());
			if(d.isEmpty()){
				cardImage = new ImageIcon("Joker.png");
				cardBack.setIcon(cardImage);
				deal.setText("New Game");
			}
			try {
				table.Display();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
			
			
		}
	}
	public void newGame() throws Exception{
		readScores();
		deal.setText("Deal");
		d.shuffle();
		cb = new ImageIcon("CardBack.png");
		disC = new ImageIcon("Discard.png");
		discardBackground.setIcon(disC);
		lblTL.setIcon(cb);
		lblTR.setIcon(cb);
		lblBL.setIcon(cb);
		lblBR.setIcon(cb);
		cardBack.setIcon(cb);
		table.topLeft.clear();
		table.topRight.clear();
		table.bottomLeft.clear();
		table.bottomRight.clear();
		discardCards.clear();
		
	}
	
	class CardListener implements MouseListener{
		
		public void mouseClicked(MouseEvent e) {
			if(e.getButton() == 1){
				if(pressed == 1){
					returnCard(hold);
					pressed = 0;
				}
				
				if(e.getSource() == lblTL){
					if(table.legalMove(table.topLeft)){
						if(table.topLeft.getIndex() >= 1){
						discardCards.insert(table.topLeft.remove());
						DisplayDiscard();
						}
					
					
					}
			
			}
				if(e.getSource() == lblTR){
					if(table.legalMove(table.topRight)){
						if(table.topRight.getIndex() >=1){
							discardCards.insert(table.topRight.remove());
							DisplayDiscard();
						}
					}
				}
				
				if(e.getSource() == lblBL){
					if(table.legalMove(table.bottomLeft)){
						if(table.bottomLeft.getIndex() >= 1){
							discardCards.insert(table.bottomLeft.remove());
							DisplayDiscard();
						}
					}
				}
				if(e.getSource() == lblBR){
					if(table.legalMove(table.bottomRight)){
						if(table.bottomRight.getIndex() >= 1){
							discardCards.insert(table.bottomRight.remove());
							DisplayDiscard();
						}
					}
				}
			}
			
			if(e.getButton() == 3){
				pressed++;
				if(pressed == 1){
					
					if(e.getSource() == lblTL){
						c = table.topLeft.remove();
						hold = 1;
					}
					
					if(e.getSource() == lblTR){
						c = table.topRight.remove();
						hold = 2;
					}
					
					if(e.getSource() == lblBL){
						c = table.bottomLeft.remove();
						hold = 3;
					}
					
					if(e.getSource() == lblBR){
						c = table.bottomRight.remove();
						hold = 4;
					}
				}
				if(pressed == 2){
					pressed = 0;
					
					if(e.getSource() == lblTL){
						if(table.topLeft.getIndex() == 0){
							table.topLeft.insert(c);
						}
						else{
							returnCard(hold);
						}
					}
					
					else if(e.getSource() == lblTR){
						if(table.topRight.getIndex() == 0){
							table.topRight.insert(c);
						}
						else{
							returnCard(hold);
						}
					}
					else if(e.getSource() == lblBL){
						if(table.bottomLeft.getIndex() == 0){
							table.bottomLeft.insert(c);
						}
						else{
							returnCard(hold);
						}
					}
					else if(e.getSource() == lblBR){
						if(table.bottomRight.getIndex() == 0){
							table.bottomRight.insert(c);
						}
						else{
							returnCard(hold);
						}
					}
					else{
						returnCard(hold);
					}
					
					try {
						table.Display();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
		}
		
		protected void returnCard(int i){
			switch(i){
			case 1:
				table.topLeft.insert(c);
				break;
			case 2:
				table.topRight.insert(c);
				break;
			case 3:
				table.bottomLeft.insert(c);
				break;
			case 4:
				table.bottomRight.insert(c);
				break;
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

	}
	public void DisplayDiscard(){
		disC = new ImageIcon(discardCards.showTopCard() + ".png");
		discardBackground.setIcon(disC);
		try {
			table.Display();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void writeScore(boolean b) throws FileNotFoundException{
		
		
		
		Scanner input = new Scanner(file1);
		
		int wins = 0;
		int looses = 0;
		if(file1.exists()){
			wins = input.nextInt();
			looses = input.nextInt();
			if(b)
				wins++;
			else
				looses++;

		}
		else{
			if(b)
				wins = 1;
			else
				looses = 1;	
		}
		java.io.PrintWriter output = new java.io.PrintWriter(file1);
		output.println(wins);
		output.println(looses);	
		output.close();
	}
	
	private void readScores() throws FileNotFoundException{
		
		java.io.File file = new java.io.File("scores.txt");
		Scanner input = new Scanner(file);
		int wins = input.nextInt();
		int looses = input.nextInt();
		JOptionPane.showMessageDialog(null, "Wins: " + wins + "\nLosses: " + looses);
	}

	class Board extends JPanel{
		
		CardStack topLeft = new CardStack();
		CardStack topRight = new CardStack();
		CardStack bottomLeft = new CardStack();
		CardStack bottomRight = new CardStack();
		
		public void Display() throws FileNotFoundException{
			
			if(table.topLeft.getIndex() == 0)
				cardImage = new ImageIcon("CardBack.png");
			
			else
				cardImage = new ImageIcon(topLeft.showTopCard().toString() + ".png");
			
			lblTL.setIcon(cardImage);
			
			if(table.topRight.getIndex() == 0)
				cardImage = new ImageIcon("CardBack.png");
			
			else
				cardImage = new ImageIcon(topRight.showTopCard().toString() + ".png");
			
			lblTR.setIcon(cardImage);
			
			if(table.bottomLeft.getIndex() == 0)
				cardImage = new ImageIcon("CardBack.png");
			
			else
				cardImage = new ImageIcon(bottomLeft.showTopCard().toString() + ".png");
			
			lblBL.setIcon(cardImage);
			
			if(table.bottomRight.getIndex() == 0)
				cardImage = new ImageIcon("CardBack.png");
			else
				cardImage = new ImageIcon(bottomRight.showTopCard().toString() + ".png");
		
			lblBR.setIcon(cardImage);
			
			
			
			if(table.topLeft.isAce() && table.topRight.isAce() && table.bottomLeft.isAce() && table.bottomRight.isAce()){
				JOptionPane.showMessageDialog(null, "You Win!");
				writeScore(true);
				try {
					newGame();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		public boolean legalMove(CardStack c){
			Card tl = topLeft.showTopCard();
			Card tr = topRight.showTopCard();
			Card bl = bottomLeft.showTopCard();
			Card br = bottomRight.showTopCard();
			Card cc = c.showTopCard();
			boolean isLegal = false;
			
			if(!topLeft.isEmpty()){
				if(cc.isSameSuite(tl)){
					if(cc.rank < topLeft.showTopCard().rank){
						isLegal = true;
					}	
				}
			}
			if(!topRight.isEmpty()){
				if(cc.isSameSuite(tr)){
					if(cc.rank < topRight.showTopCard().rank){
						isLegal = true;
					}
				}
			}
			if(!bottomLeft.isEmpty()){
				if(cc.isSameSuite(bl)){
					if(cc.rank < bottomLeft.showTopCard().rank){
						isLegal = true;
					}
				}
			}
			if(!bottomRight.isEmpty()){
				if(cc.isSameSuite(br)){
					if(cc.rank < bottomRight.showTopCard().rank){
						isLegal = true;
					}
				}
			}
			return isLegal;
			
		}
		
		
		
	}
	
	
	
}
