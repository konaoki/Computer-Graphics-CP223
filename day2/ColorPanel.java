import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.lang.*;
public class ColorPanel extends JPanel{
  int[] xs = new int[3];
  int[] ys = new int[3];
  int radius=100;
  Color[] colors;
  double angle=Math.PI;
  Ellipse2D[] circles;
  public ColorPanel(){
    setPreferredSize(new Dimension(1000,1000)); //set canvas size
    colors=new Color[3];
    circles=new Ellipse2D.Double[3];
    colors[0]=Color.red;
    colors[1]=Color.green;
    colors[2]=Color.blue;
    for(int i=0; i<3; i++){
      xs[i]=(int)Math.round(radius*Math.cos(angle+i*2*Math.PI/3)); //x positions of circles
      ys[i]=(int)Math.round(radius*Math.sin(angle+i*2*Math.PI/3)); //y positions of circles
    }
  }
  public void paintComponent(Graphics g)
  {

    super.paintComponent(g);  //without this no background color set.
    Graphics2D g2d = (Graphics2D)g; //cast so we can use JAVA2D.
    g2d.translate(getWidth()/2,getHeight()/2); //set the center

    for(int i=0; i<3; i++){
      g2d.setPaint(colors[i]); //set the color
      circles[i]=new Ellipse2D.Double(xs[i],ys[i],radius*4,radius*4); //create circle shapes
      g2d.fill(circles[i]);//color the circle
    }
    Area[] as = new Area[3];
    Area tempA=null;
    for(int i=0; i<3; i++){
      as[i]=new Area(circles[i]);
    }
    //fill intersections between circles
    int[] cs = new int[3];
    for(int i=0; i<3; i++){
      cs[0]=colors[0].getRed();
      cs[1]=colors[1].getGreen();
      cs[2]=colors[2].getBlue();
      cs[(i+2)%3]=0;
      g2d.setPaint(new Color(cs[0],cs[1],cs[2]));
      tempA=new Area(circles[i]);
      tempA.intersect(as[(i+1)%3]);
      g2d.fill(tempA);
    }
    tempA=as[0];
    tempA.intersect(as[1]);
    tempA.intersect(as[2]);
    cs[0]=colors[0].getRed();
    cs[1]=colors[1].getGreen();
    cs[2]=colors[2].getBlue();
    g2d.setPaint(new Color(cs[0],cs[1],cs[2]));
    g2d.fill(tempA);//fill intersection between all circles




  }
}
