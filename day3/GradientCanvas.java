import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.lang.*;
public class GradientCanvas extends JPanel{
  GradientPaint[] gradients;
  Polygon[] shapes;
  int size=1200;
  public GradientCanvas(){
    setPreferredSize(new Dimension(size,size));
    gradients=new GradientPaint[3];
    shapes=new Polygon[3];
    shapes[0]=new Polygon(new int[]{-size/2,size/2,size/2,-size/2},new int[]{-size/2,-size/2,size/2,size/2},4);
    gradients[0]=new GradientPaint(0,size/2,Color.black,0,-size/2,Color.blue);
    int yOffset=size/20;
    double angle=Math.PI/2;
    double triRadius=size/2.7;
    int[] xs = {(int)Math.round(triRadius*Math.cos(angle+1*2*Math.PI/3)),(int)Math.round(triRadius*Math.cos(angle+2*2*Math.PI/3)),(int)Math.round(triRadius*Math.cos(angle+3*2*Math.PI/3))};
    int[] ys = {(int)Math.round(triRadius*Math.sin(angle+1*2*Math.PI/3))-yOffset,(int)Math.round(triRadius*Math.sin(angle+2*2*Math.PI/3))-yOffset,(int)Math.round(triRadius*Math.sin(angle+3*2*Math.PI/3))-yOffset};
    shapes[1]=new Polygon(xs,ys,3);
    gradients[1]=new GradientPaint(0,size/2,Color.blue,0,-size/2,Color.black);
    angle=Math.toRadians(30);
    xs = new int[]{(int)Math.round(triRadius/2*Math.cos(angle+1*2*Math.PI/3)),(int)Math.round(triRadius/2*Math.cos(angle+2*2*Math.PI/3)),(int)Math.round(triRadius/2*Math.cos(angle+3*2*Math.PI/3))};
    ys = new int[]{(int)Math.round(triRadius/2*Math.sin(angle+1*2*Math.PI/3))-yOffset,(int)Math.round(triRadius/2*Math.sin(angle+2*2*Math.PI/3))-yOffset,(int)Math.round(triRadius/2*Math.sin(angle+3*2*Math.PI/3))-yOffset};
    shapes[2]=new Polygon(xs,ys,3);
    gradients[2]=new GradientPaint(0,size/2,Color.black,0,-size/2,Color.blue);
  }
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D)g;
    g2d.translate(getWidth()/2,getHeight()/2);
    g2d.scale(1,-1);
    for(int i=0; i<3; i++){
      g2d.setPaint(gradients[i]);
      g2d.fillPolygon(shapes[i]);
    }

  }
}
