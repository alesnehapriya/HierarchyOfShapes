/**
 * The Hirarchy_Shapes program demonstrates two styles of drawing the shapes.
 * 1-	Draw the shapes by giving the coordinates as the input.
 * 2-	Draw the shapes by uploading the text file which has input as Shapes and its coordinates. 
 * 3- 	Shapes blinks with random colors
 * 4-	Shapes move from the coordinates entered.
 * @author Sneha Priya Ale
 * @version 2.0
 * @since 10-14-2015
 */
package ShapesPackage;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.swing.*;


public class Hierarchy_Shapes extends JFrame implements ActionListener {
	/**
	 * Declaration components
	 */
	JPanel panel1 = new JPanel(new FlowLayout());
	JFrame frame1 = new JFrame();
	JFrame frame2 = new JFrame();
	JLabel lb = new JLabel();
	JTextArea text_area = new JTextArea("");
	Font font = new Font("Sans-serif", Font.BOLD, 24);
	JLabel lb1= new JLabel();
	JLabel lb2= new JLabel();
	JLabel lb3= new JLabel();
	JLabel lb4= new JLabel();
	JLabel lb5= new JLabel();
	JComponent component=null;
	JButton draw = new JButton("Draw the Shapes?");
	JButton submit = new JButton("Submit");
	JTextField x1 = new JTextField("0",4);
	JTextField y1 = new JTextField("0",4);
	JTextField x2 = new JTextField("0",4);
	JTextField y2 = new JTextField("0",4);
	JMenuItem quit,open;
	JMenuBar menu;
	JMenu file,insert;
	Scanner files=null;
	FileReader reader=null;
	BufferedReader br1=null,br2=null;
	ItemSelectable is;
	String[] Shapes = new String[] {"Select Shape","Line Segment", "Rectangle",
            "Circle", "Oval"};
	JComboBox<String> Shapes_list=new JComboBox<>(Shapes);
	AudioFormat AF;
	AudioInputStream ais;
	SourceDataLine sdc;
	/**
	 * Declaration of variables
	 */
	int l=0,k=0,m=0,c=0;
	int x1_num=0,y1_num=0,x2_num=0,y2_num=0;
	String line=null,shape_name=null;
    public static void main(String[] args) {
        new Hierarchy_Shapes();
        
  }
/**
 * Main Class constructor
 */
    Hierarchy_Shapes(){
    	/**
    	 * Setting up frames, panels and layout
    	 */
    	frame1.setTitle("Hierarchy Of Shapes");
    	frame1.setLayout(new GridLayout(1,1));
    	frame2.setLayout(new GridLayout(1,2));
    	draw.addActionListener(this);
    	submit.addActionListener(this);
    	panel1.add(draw);
    	lb5.setText("Please upload the text in the following format- 'LineSgement ( x1 , y1 ) and ( x2 , y2 ) \n"
    			+ "Rectangle x and y ( width and height )\n Circle ( x , y ) ( radius )'");
    	panel1.add(lb5);
    	frame1.add(panel1);
    	/**
    	 * Setting up the MenuBar
    	 */
    	menu = new JMenuBar();
    	file = new JMenu("File");
		open=file.add(new JMenuItem("Open",new ImageIcon("Open-icon.png")));
		file.add(new JMenuItem("Save"));
		file.add(new JMenuItem("Save As"));
		file.addSeparator();
		file.add(new JCheckBoxMenuItem("Autosave"));
		file.addSeparator();
		insert = new JMenu("Insert");
		insert.add(new JMenuItem("Image"));
		insert.add(new JMenuItem("Table"));
		file.add(insert);
		file.addSeparator();
		quit= file.add(quit = new JMenuItem("Quit"));
		menu.add(file);
		frame1.setJMenuBar(menu);
		open.addActionListener(this);
		quit.addActionListener(this);
		
		
    	frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	/**
    	 * Setting up the itemListener for JComboBox
    	 */
        ItemListener itemListener = new ItemListener(){
        	
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				int state = e.getStateChange();
				System.out.println("Item: " + e.getItem());
				is = e.getItemSelectable();
				System.out.println(", Selected: " + Shapes_list.getSelectedItem().toString());
				/**
				 * Components will be displayed based on the selection from JComboBox
				 */
				if(Shapes_list.getSelectedItem().toString()== "Rectangle" || Shapes_list.getSelectedItem().toString()== "Line Segment"
						|| Shapes_list.getSelectedItem().toString()== "Oval")
				{
	        	panel1.add(x1);
	   	        panel1.add(y1);
	   	        panel1.add(x2);
	   	        panel1.add(y2);
	   	        panel1.add(submit);
	   	        frame1.add(panel1,null);
	   	        frame1.setVisible(true);	
		        }
				if(Shapes_list.getSelectedItem().toString()== "Circle"){
					panel1.add(x1);
		   	        panel1.add(y1);
		   	        panel1.add(x2);
		   	        panel1.add(submit);
		   	        frame1.add(panel1);
		   	        frame1.setVisible(true);
				}
			}
	        
        	
        };
        
        Shapes_list.addItemListener(itemListener);
        frame1.pack();
        frame1.setSize(850, 500);
        frame1.setVisible(true);
    }
    /**
     * Based on the input provided, the shapes are drawn
     */
   
	@Override
	public void actionPerformed(ActionEvent e) {	
		// TODO Auto-generated method stub
		
		if(e.getActionCommand()=="Draw the Shapes?"){
			panel1.add(Shapes_list);
			frame1.add(panel1);
			frame1.setVisible(true);
			
		}
		
		if(e.getActionCommand()== "Submit"){
	    	x1_num=	Integer.parseInt(x1.getText());
	    	y1_num= Integer.parseInt(y1.getText());
	    	x2_num= Integer.parseInt(x2.getText());
	    	y2_num= Integer.parseInt(y2.getText());
	    	/**
	    	 * "Rectangles" class is called
	    	 */
	    	if((x1_num==0 && y1_num==0&&x2_num==0 && y2_num==0)||x1_num<0||y1_num<0||x2_num<0||y2_num<0){
	    		playAudio();
	    		JOptionPane.showMessageDialog(null,"Please check the Coordinates");
	    	}
	    	else{	
			if(Shapes_list.getSelectedItem().toString()== "Rectangle"){
			component = new Rectangles(x1_num,y1_num,x2_num,y2_num);
			shape_name= component.toString();
			}
			/**
			 * LineSegment class is called
			 */
		if(Shapes_list.getSelectedItem().toString()== "Line Segment"){
			component = new LineSegment(x1_num,y1_num,x2_num,y2_num);
			shape_name= component.toString();
		}
		/**
		 * Circle class is called
		 */
		if(Shapes_list.getSelectedItem().toString()== "Circle"){
			component = new Circle(x1_num,y1_num,x2_num);
			shape_name= component.toString();
			}
		/**
		 * Oval class is called
		 */
		if(Shapes_list.getSelectedItem().toString()== "Oval"){
			component = new Oval(x1_num,y1_num,x2_num,y2_num);
			shape_name= component.toString();
			}
		/**
		 * The result is drawn into the frames.
		 */
		lb.setText(shape_name);
		frame2.setTitle(shape_name);
		lb.setFont(font);
		lb.setForeground(Color.WHITE);
		frame2.add(lb);
		frame2.add(component);
		frame2.getContentPane().setBackground(Color.BLACK);
		frame2.setSize(500, 500);
		frame2.setVisible(true);
		frame1.dispose();	
	    	}
				
			}
		if("Quit".equals(e.getActionCommand())) frame1.dispose();
		/**
		 * Here we upload the file from MenuBar
		 */
		if("Open".equals(e.getActionCommand())){
			JFileChooser chooser = new JFileChooser();
			chooser.showOpenDialog(null);
			File f= chooser.getSelectedFile();
			String[] records = null;
			int[] x= new int[20] ;
			String[] data = new String[10];
			

			
			try{
				String filename = f.getAbsolutePath();
				reader = new FileReader(filename);
				br1= new BufferedReader(reader);
/**
 * Integer literals are noted which are delimited by white spaces.
 */
				while(true){
				try{

					line=br1.readLine();
					if(line==null)break;
					records = line.split(" ");
					for (String record1: records){
						try{
							if(record1==null) break;
						int number = Integer.parseInt(record1);
						x[m]=number;
						m++;
						l++;
						}
						catch(NumberFormatException mismatch1){
							if(record1.matches("[a-zA-Z]*")&& record1.contains("and")==false){
								data[c]=record1;	
								System.out.println("The Shapes in the file are data["+c+"]:"+data[c]);
								c++;
							}
								
							l--;
						}
					}
					k++;
					
				}
				catch(NumberFormatException mismatch){
					k--;
				}

				}

/**
 * The toString method is called to print the String Representation				
 */
				
				JComponent component1= new LineSegment(x[0],x[1],x[2],x[3]);
				String shape_name1= component1.toString();
				lb2.setText(shape_name1);
				lb2.setFont(font);
				lb2.setForeground(Color.WHITE);
				frame2.add(lb2);
				frame2.add(component1);
				frame2.setVisible(true);
				JComponent component2=new Rectangles(x[4],x[5],x[6],x[7]);
				String shape_name2= component2.toString();
				lb3.setText(shape_name2);
				lb3.setFont(font);
				lb3.setForeground(Color.WHITE);
				frame2.add(lb3);
				frame2.add(component2);
				frame2.setVisible(true);
				JComponent component3 = new Circle(x[8],x[9],x[10]);
				String shape_name3= component3.toString();
				lb4.setText(shape_name3);
				lb4.setFont(font);
				lb4.setForeground(Color.WHITE);
				frame2.add(lb4);
				frame2.add(component3);
				frame2.getContentPane().setBackground(Color.BLACK);
				frame2.setSize(800, 500);
				frame2.setVisible(true);
				frame1.dispose();
	/**
	 * File is closed			
	 */
				br1.close();
			}
			
			catch(NullPointerException ae){
				playAudio();
				JOptionPane.showMessageDialog(null, "Please upload a file");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				playAudio();
				JOptionPane.showMessageDialog(null, e1);
			}
			
			
		}	
		}
	/**
	 * playAudio method is used to input the audio file
	 */
	 private void playAudio() {
		    try{
		      File soundFile =
		                   new File("error1.au");
		      /**
		       * Audio input stream is to stream the file
		       */
		      ais = AudioSystem.getAudioInputStream(soundFile);
		      AF = ais.getFormat(); // read the audio format
		      /**
		       * Here the DataLine info is used to send the audio to drivers for playing. The above audio format
		       * is used below.
		       */
		      DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class,AF);
		      sdc =(SourceDataLine)AudioSystem.getLine(dataLineInfo);
		      /**
		       * Thread is created to play complete file till it ends.
		       */
		      new PlayThread().start();
		    }
		    catch(Exception e){
		    	JOptionPane.showMessageDialog(null, e);
		    }
	 }
	 /**
	  * 
	  * Plays the file till the end
	  *
	  */
	 public class PlayThread extends Thread{
		  byte tB[] = new byte[10000];

		  public void run(){
		    try{
		      sdc.open(AF);
		      sdc.start();
		      int counter;
		      /**
		       * Here -1 indicates the end of the file
		       */
		      while((counter = ais.read(tB,0,tB.length))!= -1){
		    	  if(counter >0){
		    		  sdc.write( tB, 0, counter);
		    	  }
		    	  
		      }
		      sdc.drain();
		      sdc.close();
	}
		    catch(Exception exc){
		    	playAudio();
		    	JOptionPane.showMessageDialog(null, exc);
		    }
		  }
	}
}



