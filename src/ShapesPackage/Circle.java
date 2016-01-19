package ShapesPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.Timer;

/**
 * 
 * @author Sneha Priya Ale
 * Circle class draws the shape of the circle
 */
public class Circle extends JComponent implements Shapes, Runnable{
	static int a1=0;
	static int a2=0;
	static int a3=0;
	Random r;
	Thread clockThread = null;
	/**
	 * @return The String name as "Circle"
	 */
	public String toString(){
		return "Circle";
		
	}
	/**
	 * 
	 * @param a1 center- x coordinate
	 * @param a2 center- y coordinate
	 * @param a3 radius
	 */
	public Circle(int a1, int a2, int a3) {
		// TODO Auto-generated constructor stub
    	this.a1=a1;
    	this.a2=a2;
    	this.a3=a3;
    	r=new Random();
		if(clockThread == null){
			clockThread = new Thread(this);
			clockThread.start();
		}
		/**
		 * Circle get in motion
		 */
	    Timer timer = new Timer(50, new ActionListener(){
	        public void actionPerformed(ActionEvent e) {
	                circl.move();
	                repaint();
	            
	        }
	    });
	    timer.start();
	}
	/**
	 * Draw the circle by calling the drawCircl method from the inner class circl
	 */
	@Override
    protected void paintComponent(Graphics g1) {
		super.paintComponent(g1);
		circl.drawCircl(g1);
        
	}		
	/**
	 * Thread used for random colors generation
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(Thread.currentThread()== clockThread){
			repaint();
			try{
				Thread.currentThread().sleep(1000);
			}
			catch(InterruptedException e){
				
			}
		}
		
	}
	/**
	 * Class used to display random colors as well as for the circle to move horizontally
	 * @author Sneha Priya Ale
	 *
	 */
    public static class circl {
    	static int INCREMENT = 10;
        public static void drawCircl(Graphics g3) {
  	      int r= (int)Math.round((Math.random()*255));
  	      int g= (int)Math.round((Math.random()*255));
  	      int b= (int)Math.round((Math.random()*255));
  		Graphics2D g2 = (Graphics2D) g3;
          g2.setColor(new Color(r,g,b));
          g2.fillOval(a1, a2, a3, a3);
        }

        public static void move() {
            if (a1 == 250) {
                a1 = 0;
            } else {
                a1 += INCREMENT;
            }
        }
    }
}