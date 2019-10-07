import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
public class CubeController{
  public static void main(String[] args){
    CubeCanvas canvas = new CubeCanvas();
    CubeGUI sliders = new CubeGUI(canvas);
    JFrame frame = new JFrame();
    frame.setTitle("cube");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.add(canvas, BorderLayout.CENTER);
    frame.add(sliders,BorderLayout.EAST);
    frame.pack();
    frame.setResizable(true);
    frame.setVisible(true);
  }
}
