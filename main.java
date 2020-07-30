import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class main {

	public static void main(String[] args) {
	okno window = new okno();	//we make new window
	}
}

class okno extends JFrame {  //this window extends JFrame so we can use some things from JFrame
	public okno() {
		setBounds(0,0,800,600);  //window's size
		setTitle("walmart paint");  //the title of our window
		add(new panel());
		setVisible(true);  //setting this to true so we can see our window
		setBackground(Color.WHITE);  //canvas' color
	}
}

class panel extends JPanel {
	private Color[] masColor;
	private int tCol=0;
	private int mX, mY;
	private boolean flag=false;
	panel() {
		addMouseListener(new myMouse());
		addMouseMotionListener(new myMotionMouse());
		setFocusable(true);
	}
	public void paintComponent(Graphics gr) {
		masColor=new Color[9];
		Color col1=new Color (255, 255, 255);
		masColor[0]=new Color(55, 100, 255);
		masColor[1]=Color.GREEN;
		masColor[2]=Color.BLUE;
		masColor[3]=Color.RED;
		masColor[4]=Color.YELLOW;
		masColor[5]=Color.BLACK;
		masColor[6]=Color.WHITE; //this will be an eraser
		for(int i=0; i<8; i++) {
			gr.setColor(masColor[i]);
			gr.fillRect(i * 100, 0, 100, 50); //clickable rectangles to choose color
		}
		if(flag==true) {
			gr.setColor(masColor[tCol]);
			gr.fillRect(mX, mY, 3, 3);
			if(tCol==7) {
				gr.setColor(masColor[7]);
				gr.fillRect(mX, mY, 100, 100);
			}
		}
	}
	private class myMouse implements MouseListener {  //making all mouselistener stuff so program sees my mouse
		@Override
		public void mouseClicked(MouseEvent arg0) {
			
		}
		
		@Override
		public void mouseEntered(MouseEvent arg0) {
			
		}
		
		@Override
		public void mouseExited(MouseEvent arg0) {
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			int tX=e.getX(); //mouse's x coordinates
			int tY=e.getY(); //mouse's y coordinates
			int col=e.getClickCount();
			int btn=e.getButton();
			if(tX>0 && tX<700 && tY>0 && tY<50) {
				if(col==1) {
					if(btn==1) {tCol=tX/100;}
				}
			}
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			flag=false;
		}
	}
	
	private class myMotionMouse implements MouseMotionListener {  //mousemotionlistener so program sees when I draw stuff
		
		@Override
		public void mouseDragged(MouseEvent e) {
			int tX=e.getX(); //those are still mouse's x coordinates
			int tY=e.getY(); //those are mouse's y coordinates too
			if(tY>50) {
				mX=tX;
				mY=tY;
				flag=true;
				repaint();
			}
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			System.out.println(e.getX()+"    "+e.getY()); //console writes our cursors coordinates
			int tX=e.getX(); //mouse's x coordinates once again
			int tY=e.getY(); //mouse's y coordinates 
			if(tX>0 && tX<700 && tY>0 && tY<50) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			else {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		}
	}
}