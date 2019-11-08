import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class GamePlay extends JPanel implements KeyListener, ActionListener  {

	//55:12
	private ImageIcon titleImage;

	private int[] snakeXlenght = new int[750];

	private int[] snakeYlenght = new int[750];

	private int lenghtOfSnake = 3;

	private int moves =0;

	private boolean play = false;

	private int[] enemyXPos = new int[34],enemyYPos = new int[23];

	private boolean left = false,right = false,up = false,down = false;

	private ImageIcon rightmouth, leftmouth, upmouth,downmouth;

	private Timer timer;
	private int delay = 100;

	private ImageIcon snakeImage;

	private int score = 0;

	private ImageIcon enemyimage;
	private Random rand = new Random();
	private int xpos = rand.nextInt(34);
	private int ypos = rand.nextInt(23);
	private JFrame obj;

	public GamePlay(JFrame obj){
		this.obj = obj;


		for(int i = 0;i < 850/25;i++){
			this.enemyXPos[i] = 25+(25*i);
		}
		for(int i = 0;i < 575/25;i++){
			this.enemyYPos[i] = 75+(25*i);
		}

		System.out.println(xpos+" "+ypos+" "+enemyXPos[xpos]+" "+enemyYPos[ypos]);

		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);


		timer = new Timer(delay,this);
		timer.start();

	}

	public void paint(Graphics g){

		if(moves == 0){
			this.snakeXlenght[2] = 50;
			this.snakeXlenght[1] = 75;
			this.snakeXlenght[0] = 100;

			this.snakeYlenght[2] = 100;
			this.snakeYlenght[1] = 100;
			this.snakeYlenght[0] = 100;
		}


		//draw title image border
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);

		//draw the title image
		titleImage = new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);

		//draw the border for the gameplay
		g.setColor(Color.blue);
		g.drawRect(24, 74, 851, 578);

		//draw background for the gameplay
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 577);

		//end of the game
		for(int i = this.lenghtOfSnake; i>=0;i--){

			//to comeback from the other side
			if(this.snakeYlenght[i]==625 || this.snakeXlenght[i]==25 || this.snakeYlenght[i]==75 || this.snakeXlenght[i]==850){
				play = false;
				right = false;
				left = false;
				up = false;
				down = false;
				g.setColor(Color.red);
				g.setFont(new Font("serif",Font.BOLD,30));
				g.drawString("Game Over, Score: "+score, 300, 300);

				g.setFont(new Font("serif",Font.BOLD,20));
				g.drawString("Press Enter to restart ", 340, 330);
			}


		}

		//draw score
		g.setColor(Color.white);
		g.setFont(new Font("serif",Font.BOLD,15));
		g.drawString("Score: "+((this.lenghtOfSnake-3)*5), 780, 30);

		//draw score
		g.setColor(Color.white);
		g.setFont(new Font("serif",Font.BOLD,15));
		g.drawString("Lenght: "+(this.lenghtOfSnake), 780, 50);

		//draw snake
		rightmouth = new ImageIcon("rightmouth.png");
		rightmouth.paintIcon(this, g, this.snakeXlenght[0], this.snakeYlenght[0]);

		for(int i = 0; i< lenghtOfSnake ; i ++){
			if(i == 0 && right){
				rightmouth = new ImageIcon("rightmouth.png");
				rightmouth.paintIcon(this, g, this.snakeXlenght[i], this.snakeYlenght[i]);
			}
			if(i == 0 && left){
				leftmouth = new ImageIcon("leftmouth.png");
				leftmouth.paintIcon(this, g, this.snakeXlenght[i], this.snakeYlenght[i]);
			}
			if(i == 0 && up){
				upmouth = new ImageIcon("upmouth.png");
				upmouth.paintIcon(this, g, this.snakeXlenght[i], this.snakeYlenght[i]);
			}
			if(i == 0 && down){
				downmouth = new ImageIcon("downmouth.png");
				downmouth.paintIcon(this, g, this.snakeXlenght[i], this.snakeYlenght[i]);
			}
			if(i != 0){
				snakeImage = new ImageIcon("snakeimage.png");
				snakeImage.paintIcon(this, g, this.snakeXlenght[i], this.snakeYlenght[i]);
			}

		}

		//draw enemy
		enemyimage = new ImageIcon("enemy.png");
		//detect collions
		if(this.enemyXPos[xpos] == this.snakeXlenght[0] && enemyYPos[ypos] == this.snakeYlenght[0]){

				this.lenghtOfSnake ++;
				xpos = rand.nextInt(34);
				ypos = rand.nextInt(23);



			System.out.println(xpos+" "+ypos+" "+enemyXPos[xpos]+" "+enemyYPos[ypos]);

		}

		enemyimage.paintIcon(this, g, this.enemyXPos[xpos], this.enemyYPos[ypos]);

		for(int i = 1; i < this.lenghtOfSnake ; i++){
			if(this.snakeXlenght[i] == this.snakeXlenght[0]  && this.snakeYlenght[i] == this.snakeYlenght[0]){
				right = false;
				left = false;
				up = false;
				down = false;

				g.setColor(Color.red);
				g.setFont(new Font("serif",Font.BOLD,30));
				g.drawString("Game Over, Score: "+score, 300, 300);

				g.setFont(new Font("serif",Font.BOLD,20));
				g.drawString("Press Enter to restart ", 340, 330);
			}
		}

		g.dispose();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		timer.start();

		if(right){
			for(int i = this.lenghtOfSnake-1; i>=0;i--){
				this.snakeYlenght[i+1] = this.snakeYlenght[i];


			}

			for(int i = this.lenghtOfSnake; i>=0;i--){
				if(i == 0){
					this.snakeXlenght[i] = this.snakeXlenght[i]+25;
				}else{
					this.snakeXlenght[i] = this.snakeXlenght[i-1];
				}
				//to comeback from the other side
				/*if(this.snakeXlenght[i]>850){
					this.snakeXlenght[i]=25;
				}*/

			}
			repaint();

		}
		if(left){
			for(int i = this.lenghtOfSnake-1; i>=0;i--){
				this.snakeYlenght[i+1] = this.snakeYlenght[i];


			}

			for(int i = this.lenghtOfSnake; i>=0;i--){
				if(i == 0){
					this.snakeXlenght[i] = this.snakeXlenght[i]-25;
				}else{
					this.snakeXlenght[i] = this.snakeXlenght[i-1];
				}
				//to comeback from the other side
				/*if(this.snakeXlenght[i]<25){
					this.snakeXlenght[i]=850;
				}*/

			}
			repaint();
		}
		if(up){
			for(int i = this.lenghtOfSnake-1; i>=0;i--){
				this.snakeXlenght[i+1] = this.snakeXlenght[i];


			}

			for(int i = this.lenghtOfSnake; i>=0;i--){
				if(i == 0){
					this.snakeYlenght[i] = this.snakeYlenght[i]-25;
				}else{
					this.snakeYlenght[i] = this.snakeYlenght[i-1];
				}
				//to comeback from the other side
				/*if(this.snakeYlenght[i]<75){
					this.snakeYlenght[i]=625;
				}*/

			}
			repaint();
		}
		if(down){
			for(int i = this.lenghtOfSnake-1; i>=0;i--){
				this.snakeXlenght[i+1] = this.snakeXlenght[i];
			}

			for(int i = this.lenghtOfSnake; i>=0;i--){
				if(i == 0){
					this.snakeYlenght[i] = this.snakeYlenght[i]+25;
				}else{
					this.snakeYlenght[i] = this.snakeYlenght[i-1];
				}
				//to comeback from the other side
				/*if(this.snakeYlenght[i]>625){
					this.snakeYlenght[i]=75;
				}*/

			}
			repaint();
		}

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT){

			moves ++;
			right = true;
			if(!left){
				right = true;
			}else{
				right = false;
				left = true;
			}
			up = false;
			down = false;

		}

		if(arg0.getKeyCode() == KeyEvent.VK_LEFT){

			moves ++;
			left = true;
			if(!right){
				left = true;
			}else{
				left = false;
				right = true;
			}
			up = false;
			down = false;
		}

		if(arg0.getKeyCode() == KeyEvent.VK_UP){
			moves ++;
			up = true;
			if(!down){
				up = true;
			}else{
				up = false;
				down = true;
			}
			right = false;
			left = false;
		}

		if(arg0.getKeyCode() == KeyEvent.VK_DOWN){
			moves ++;
			down = true;
			if(!up){
				down = true;
			}else{
				down = false;
				up = true;
			}
			right = false;
			left = false;
		}

		if(arg0.getKeyCode() == KeyEvent.VK_ENTER){



			for(int i = 0;i < 850/25;i++){
				this.enemyXPos[i] = 25+(25*i);
			}
			for(int i = 0;i < 575/25;i++){
				this.enemyYPos[i] = 75+(25*i);
			}
			moves =0;
			this.lenghtOfSnake = 3;
			repaint();

		}


	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
