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
 * Rectangles class draws the shape of the Rectangle
 */

public class Rectangles extends JComponent implements Shapes, Runnable {
	private static int a1=0;
    private static int a2=0;
	private static int a3=0;
	private static int a4=0;
	Random r;
	Thread clockThread = null;
	/**
	 * @return The String name as "Rectangle"
	 */
	public String toString(){
		return "Rectangle";
		
	}
	/**
	 * 
	 * @param a1 x1 coordinate
	 * @param a2 y1 coordinate
	 * @param a3 width
	 * @param a4 height
	 */
	public Rectangles(int a1, int a2, int a3, int a4) {
		// TODO Auto-generated constructor stub
    	this.a1=a1;
    	this.a2=a2;
    	this.a3=a3;
    	this.a4=a4;
    	r=new Random();
		if(clockThread == null){
			clockThread = new Thread(this);
			clockThread.start();
		}
		/**
		 * Timer for Rectangle to move
		 */
	    Timer timer = new Timer(50, new ActionListener(){
	        public void actionPerformed(ActionEvent e) {
	                Rect.move();
	                repaint();
	            
	        }
	    });
	    timer.start();
	}
	/**
	 * Draw the Rectangle by calling the drawRect method from the inner class Rect
	 */
	@Override
    protected void paintComponent(Graphics g1) {
		super.paintComponent(g1);
		Rect.drawRect(g1);

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
	 * Class used to display random colors as well as for the Rectangle to move vertically
	 * @author Sneha Priya Ale
	 *
	 */
    public static class Rect {
    	static int INCREMENT = 10;
        public static void drawRect(Graphics g3) {
            Graphics2D g2 = (Graphics2D) g3;
  	      int r= (int)Math.round((Math.random()*255));
  	      int g= (int)Math.round((Math.random()*255));
  	      int b= (int)Math.round((Math.random()*255));

          g2.setColor(new Color(r,g,b));
          g2.fillRect(a1, a2, a3, a4);
        }

        public static void move() {
            if (a2 == 250) {
                a2 = 0;
            } else {
                a2 += INCREMENT;
            }
        }
    }
    
}
