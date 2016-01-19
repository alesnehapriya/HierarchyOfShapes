package ShapesPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.Timer;

/**
 * 
 * @author Sneha Priya Ale
 * LineSegment class draws the shape of the LineSegment
 */
public class LineSegment extends JComponent implements Shapes, Runnable{
	private static float a1=0;
    private static float a2=0;
	private static float a3=0;
	private static float a4=0;
	Random r;
	Thread clockThread = null;
	
	/**
	 * @return The String name as "Line Segment"
	 */
	public String toString(){
		return "Line Segment";
		
	}
	/**
	 * 
	 * @param a1 x1 coordinate
	 * @param a2 y1 coordinate
	 * @param a3 x2 coordinate
	 * @param a4 y2 coordinate
	 */
	public LineSegment(int a1, int a2, int a3, int a4) {
		// TODO Auto-generated constructor stub
    	this.a1=(float)a1;
    	this.a2=(float)a2;
    	this.a3=(float)a3;
    	this.a4=(float)a4;
    	r=new Random();
		if(clockThread == null){
			clockThread = new Thread(this);
			clockThread.start();
		}
		/**
		 * Timer for the line segment to move
		 */
	    Timer timer = new Timer(50, new ActionListener(){
	        public void actionPerformed(ActionEvent e) {
	                Lines.move();
	                repaint();
	            
	        }
	    });
	    timer.start();
	}
	/**
	 * Draw the line by calling the drawLine method from the inner class Lines
	 */
	@Override
    protected void paintComponent(Graphics g1) {
		super.paintComponent(g1);
		Lines.drawLine(g1);
        
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
	 * Class used to display random colors as well as for the line to move
	 * @author Sneha Priya Ale
	 *
	 */
    public static class Lines {
    	static int INCREMENT = 10;
        public static void drawLine(Graphics g3) {
            Graphics2D g2 = (Graphics2D) g3;
  	      int r= (int)Math.round((Math.random()*255));
  	      int g= (int)Math.round((Math.random()*255));
  	      int b= (int)Math.round((Math.random()*255));
          Line2D lin = new Line2D.Float(a1, a2, a3, a4);
          g2.setColor(new Color(r,g,b));
          g2.draw(lin);
        }

        public static void move() {
            if (a1 == 250) {
                a1 = 0;
                a2=0;
            } else {
                a1 += INCREMENT;
                a2 += INCREMENT;
                a3 += INCREMENT;
                a4 += INCREMENT;
            }
        }
    }
}
