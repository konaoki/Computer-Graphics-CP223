import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
public class PolarController{
  public static void main(String[] args){
    PolarCanvas canvas = new PolarCanvas();
    JFrame frame = new JFrame();
    frame.setTitle("cube");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.add(canvas, BorderLayout.CENTER);
    frame.pack();
    frame.setResizable(true);
    frame.setVisible(true);
  }
}
