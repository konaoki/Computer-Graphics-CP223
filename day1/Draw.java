import javax.swing.*;

public class Draw
{
    //main method
    public static void main(String[] args){
    //instantiate the canvas class
		PythTriangle myCanvas = new PythTriangle();
    //instantiate jframe
		JFrame myFrame = new JFrame();
		myFrame.setTitle("Basic Draw");
		//myFrame.setSize(100,100);

		//Sets the window to close when upper right corner clicked.
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Must use getContentPane() with JFrame.
		myFrame.add(myCanvas);
		myFrame.pack(); //resizes to preferred size for components.
		myFrame.setResizable(true);
		myFrame.setVisible(true);

    }
} //Draw
