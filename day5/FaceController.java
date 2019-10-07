import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
public class FaceController{
  public static void main(String[] args){
    FaceGUI sliders = new FaceGUI();
    FaceCanvas canvas = new FaceCanvas(sliders);
    sliders.addCanvas(canvas);
    JFrame frame = new JFrame();
    frame.setTitle("face");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.add(canvas, BorderLayout.CENTER);
    frame.add(sliders,BorderLayout.EAST);
    frame.pack();
    frame.setResizable(true);
    frame.setVisible(true);
  }
}
