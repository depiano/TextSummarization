package main;


import javax.swing.JFrame;
import frame.FrameView;


public class Test1
{

    public static void main(String[] args)
    {
    	
    	JFrame frame = new FrameView();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Mining Mailing List");
		frame.setVisible(true);
 
    }
}
