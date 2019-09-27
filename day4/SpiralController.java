import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
public class SpiralController{
  public static void main(String[] args){
    SpiralCanvas canvas = new SpiralCanvas();
    SpiralGUI sliders = new SpiralGUI(canvas);
    JFrame frame = new JFrame();
    frame.setTitle("Gradient");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.add(canvas, BorderLayout.CENTER);
    frame.add(sliders,BorderLayout.EAST);
    frame.pack();
    frame.setResizable(true);
    frame.setVisible(true);
  }
}
