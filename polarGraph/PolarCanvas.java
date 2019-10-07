import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.lang.*;
import java.util.*;

public class PolarCanvas extends JPanel implements MouseListener{
  PrecisePoint[] points;
  double a = 10;
  double maxAngle=720;
  public PolarCanvas(){
    points=new PrecisePoint[500];
    setPreferredSize(new Dimension(2000,1000));
    addMouseListener(this);
  }

  private void eq1(){
    for(int i=0; i<points.length; i++){
      double theta=Math.toRadians(i*maxAngle/points.length);
      double r=Math.cos(3*theta);
      points[i]=new PrecisePoint(r*Math.cos(theta),r*Math.sin(theta),0.0);
    }
  }
  private void eq2(){
    for(int i=0; i<points.length; i++){
      double theta=Math.toRadians(i*maxAngle/points.length);
      double r=a*theta;
      points[i]=new PrecisePoint(r*Math.cos(theta),r*Math.sin(theta),0.0);
    }
  }
  private void eq3(){
    for(int i=0; i<points.length; i++){
      double theta=Math.toRadians(i*maxAngle/points.length);
      double r=a*(1+Math.cos(theta));
      points[i]=new PrecisePoint(r*Math.cos(theta),r*Math.sin(theta),0.0);
    }
  }
  private void eq4(){
    for(int i=0; i<points.length; i++){
      double theta=Math.toRadians(i*maxAngle/points.length);
      double r=Math.cos(3*theta);
      points[i]=new PrecisePoint(Math.pow(r,8)*Math.cos(theta),Math.pow(r,8)*Math.sin(theta),0.0);
    }
  }
  private void eq5(){
    for(int i=0; i<points.length; i++){
      double theta=Math.toRadians(i*maxAngle/points.length);
      double r=1+2*Math.cos(4*theta);
      points[i]=new PrecisePoint(r*Math.cos(theta),r*Math.sin(theta),0.0);
    }
  }
  private void eq6(){
    double e = Math.E;
    for(int i=0; i<points.length; i++){
      double theta=Math.toRadians(i*maxAngle/points.length);
      double r=Math.pow(e,Math.cos(theta))-2*Math.cos(4*theta)+Math.pow(Math.sin(theta/12),5);
      points[i]=new PrecisePoint(r*Math.cos(theta),r*Math.sin(theta),0.0);
    }
  }
  private void eq7(){
    double e = Math.E;
    for(int i=0; i<points.length; i++){
      double theta=Math.toRadians(i*maxAngle/points.length);
      double r=Math.pow(e,Math.sin(theta))-2*Math.cos(4*theta)+Math.pow(Math.cos(theta/4),3);
      points[i]=new PrecisePoint(r*Math.cos(theta),r*Math.sin(theta),0.0);
    }
  }

  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D)g;
    g2d.translate(getWidth()/2,getHeight()/2);
    g2d.scale(1,-1);
    eq4();
    for(int i=1; i<points.length; i++){
      points[i].scale(50);
      g2d.drawLine(points[i-1].toInt()[0],points[i-1].toInt()[1],points[i].toInt()[0],points[i].toInt()[1]);
    }
  }

  public void mousePressed(MouseEvent e) {}
  public void mouseReleased(MouseEvent e) {}
  public void mouseEntered(MouseEvent e) {}
  public void mouseExited(MouseEvent e) {}

  /** Called whenever the mouse clicks.
    * Could be replaced with setting the value of a JLabel, etc. */
  public void mouseClicked(MouseEvent e) {

  }



}
