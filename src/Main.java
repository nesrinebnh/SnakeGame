import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame obj = new JFrame();
		Start st = new Start(obj);
		obj.setTitle("Snake Game");
		obj.setBounds(10,10,700,700);
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.setLayout(new FlowLayout(FlowLayout.CENTER));
		obj.getContentPane().setBackground( Color.black);


		obj.add(st);


	}

}
