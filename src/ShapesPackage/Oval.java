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
 * Oval class draws the shape of the Oval
 */
public class Oval extends JComponent implements Shapes, Runnable{
	private static int a1=0;
    private static int a2=0;
	private static int a3=0;
	private static int a4=0;
	Random r;
	Thread clockThread = null;
	/**
	 * 
	 * @return The String name as "Oval"
	 */
	public String toString(){
		return "Oval";
		
	}
	/**
	 * 
	 * @param a1 x coordinate
	 * @param a2 y coordinate	
	 * @param a3 width
	 * @param a4 height
	 */
	public Oval(int a1, int a2, int a3,int a4) {
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
		 * Timer for Oval to move
		 */
	    Timer timer = new Timer(50, new ActionListener(){
	        public void actionPerformed(ActionEvent e) {
	                Ovall.move();
	                repaint();
	            
	        }
	    });
	    timer.start();
	}
	/**
	 * Draw the Oval by calling the drawOval method from the inner class Ovall
	 */
	@Override
    protected void paintComponent(Graphics g1) {
		super.paintComponent(g1);
		Ovall.drawOval(g1);
        
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
	 * Class used to display random colors as well as for the oval to move horizontally
	 * @author Sneha Priya Ale
	 *
	 */
    public static class Ovall {
    	static int INCREMENT = 10;
        public static void drawOval(Graphics g3) {
            Graphics2D g2 = (Graphics2D) g3;
  	      int r= (int)Math.round((Math.random()*255));
  	      int g= (int)Math.round((Math.random()*255));
  	      int b= (int)Math.round((Math.random()*255)); 
          	g2.setColor(new Color(r,g,b));
          g2.fillOval(a1, a2, a3, a4);
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
