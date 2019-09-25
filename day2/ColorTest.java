import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
public class ColorTest
{
  public static void main(String[] args)
  {
    JFrame frame = new JFrame();
    frame.setTitle("Colors");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    ColorPanel cPanel = new ColorPanel();
	  ControlPanel sliders = new ControlPanel(cPanel);

    frame.setLayout(new BorderLayout());
    frame.add(cPanel, BorderLayout.CENTER);
    frame.add(sliders,BorderLayout.EAST);

    frame.pack();
    frame.setVisible(true);


  }//end main


}
