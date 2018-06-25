import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Test extends JFrame implements MouseListener {
	JButton b1=new JButton(new ImageIcon("image/NewGame.png"));
	JButton b3=new JButton(new ImageIcon("image/RankList.png"));
	JButton b4=new JButton(new ImageIcon("image/QuitGame.png"));

	public static void main(String[] args) 
	{
		new Test();
	}

	public Test() {
		super("24µ„”Œœ∑");
		JLayeredPane l=getLayeredPane();

		JLabel bg=new JLabel(new ImageIcon("image/MainMenu.jpg"));
		bg.setBounds(0,0,800,600);

		b1.setBorderPainted(false);
		b1.setContentAreaFilled(false);
		b1.setRolloverIcon(new ImageIcon("image/NewGameS.png"));
		b1.setBounds(300,300,160,40);
		b1.addMouseListener(this);
		b3.setBorderPainted(false);
		b3.setContentAreaFilled(false);
		b3.setRolloverIcon(new ImageIcon("image/RankListS.png"));
		b3.setBounds(300,350,160,40);
		b3.addMouseListener(this);
		b4.setBorderPainted(false);
		b4.setContentAreaFilled(false);
		b4.setRolloverIcon(new ImageIcon("image/QuitGameS.png"));
		b4.setBounds(300,400,160,40);
		b4.addMouseListener(this);

		l.add(bg, new Integer(0));
		l.add(b1, new Integer(10));
		l.add(b3, new Integer(10));
		l.add(b4, new Integer(10));

		addMouseListener(this);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource()==b1) {
			setVisible(false);
			new GameMode(this);
		}
		if (e.getSource()==b3) {
			String[] output=new RankList().getList();
			for (int i=0; i<output.length; i++) {
					JOptionPane.showMessageDialog(null,output[i],"≈≈––∞Ò",JOptionPane.PLAIN_MESSAGE,new ImageIcon("image\\RankList.gif"));
			}
		}
		if (e.getSource()==b4) {
			System.exit(0);
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}


}