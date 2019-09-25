import javax.swing.*;

public class GradientController{
  public static void main(String[] args){
    GradientCanvas canvas = new GradientCanvas();
    JFrame frame = new JFrame();
    frame.setTitle("Gradient");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.add(canvas);
    frame.pack();
    frame.setResizable(true);
    frame.setVisible(true);
  }
}
