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
    setPreferredSize(new Dimension(1000,1000));
    colors=new Color[3];
    circles=new Ellipse2D.Double[3];
    colors[0]=Color.red;
    colors[1]=Color.green;
    colors[2]=Color.blue;
    for(int i=0; i<3; i++){
      xs[i]=(int)Math.round(radius*Math.cos(angle+i*2*Math.PI/3));
      ys[i]=(int)Math.round(radius*Math.sin(angle+i*2*Math.PI/3));
    }
  }
  public void paintComponent(Graphics g)
  {

    super.paintComponent(g);  //without this no background color set.
    Graphics2D g2d = (Graphics2D)g; //cast so we can use JAVA2D.
    g2d.translate(getWidth()/2,getHeight()/2); //set the center

    for(int i=0; i<3; i++){
      g2d.setPaint(colors[i]); //set the color
      circles[i]=new Ellipse2D.Double(xs[i],ys[i],radius*4,radius*4);
      g2d.fill(circles[i]);//color the circle
    }
    Area a1,a2;
    a1=new Area(circles[0]);
    a2=new Area(circles[1]);
    a1.intersect(a2);
    g2d.setPaint(new Color(colors[0].getRed(),colors[1].getGreen(),0));
    g2d.fill(a1);
    a1=new Area(circles[1]);
    a2=new Area(circles[2]);
    a1.intersect(a2);
    g2d.setPaint(new Color(0,colors[1].getGreen(),colors[2].getBlue()));
    g2d.fill(a1);
    a1=new Area(circles[2]);
    a2=new Area(circles[0]);
    a1.intersect(a2);
    g2d.setPaint(new Color(colors[0].getRed(),0,colors[2].getBlue()));
    g2d.fill(a1);
    a1=new Area(circles[0]);
    a2=new Area(circles[1]);
    a1.intersect(a2);
    a2=new Area(circles[2]);
    a1.intersect(a2);
    g2d.setPaint(new Color(colors[0].getRed(),colors[1].getGreen(),colors[2].getBlue()));
    g2d.fill(a1);




  }
}
