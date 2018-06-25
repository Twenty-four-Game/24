import java.awt.*;
import javax.swing.*;

public  class Card extends JButton implements Variety {
	private int number,variety;
	final private static Icon back=new ImageIcon("image/card/back.png");
	final private static Icon backS=new ImageIcon("image/cardS/back.png");
	private Icon cover,coverS;

	public Card() {
		super(back);
		cover=back;
		coverS=backS;
		setRolloverIcon(backS);
		setBorderPainted(false);
		setContentAreaFilled(false);
	}

	public Card(int number) {
		this(number,0);
	}

	public Card(int number, int variety) {
		super(new ImageIcon("image/card/"+VARIETY[variety]+"/"+number+".png"));
		this.number=number;
		this.variety=variety;
		setBorderPainted(false);
		setContentAreaFilled(false);
		if (number<1 || number>13) {
			setIcon(back);
			this.number=0;
			cover=back;
			coverS=backS;
			setRolloverIcon(backS);
		} else
			cover=getIcon();
			coverS=new ImageIcon("image/cardS/"+VARIETY[variety]+"/"+number+".png");
			setRolloverIcon(coverS);
	}

	public int getNumber() {
		return number;
	}

	public String getVariety() {
		return VARIETY[variety];
	}

	public int getScore() {
		if (number>10) {
			return 2;
		} else
		if (number>0) {
			return 1;
		} else
			return 0;
	}

	public int getTime() {
		if (number>10) {
			return 30;
		} else
		if (number>0) {
			return 15;
		} else
			return 0;
	}

	public void setBack() {
		setIcon(back);
		setRolloverIcon(backS);
	}
}