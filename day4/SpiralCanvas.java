import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.lang.*;
public class SpiralCanvas extends JPanel{
  int num;
  int size=1000;
  int angle;
  int buffer=100;
  public SpiralCanvas(){
    num=20;
    size=1000;
    angle=5;
    setPreferredSize(new Dimension(size,size));
  }
  private static class DetailedPolygon{
   Polygon p;
   int l;
   int num;
   DetailedPolygon(Polygon p, int l,int num){
     this.p=p;
     this.l=l;
     this.num=num;
   }
}
  DetailedPolygon getFirstSquare(){
    int[][] points;
    points=new int[2][4];
    points[0]=new int[]{-size/2+buffer,size/2-buffer,size/2-buffer,-size/2+buffer};
    points[1]=new int[]{-size/2+buffer,-size/2+buffer,size/2-buffer,size/2-buffer};
    Polygon rp = new Polygon(points[0],points[1],4);
    return new DetailedPolygon(rp,size-buffer*2,0);
  }
  DetailedPolygon getNested(DetailedPolygon dp){
    Polygon rp = null; //return polygon
    double theta = Math.toRadians(angle);
    double a = dp.l*Math.tan(theta)/(1+Math.tan(theta));
    double b = dp.l-dp.l*Math.tan(theta)/(1+Math.tan(theta));
    double rp_length = Math.sqrt(Math.pow(a,2)+Math.pow(b,2));
    double[][] points_D;
    points_D=new double[2][4];
    points_D[0]=new double[]{-rp_length/2,rp_length/2,rp_length/2,-rp_length/2};
    points_D[1]=new double[]{-rp_length/2,-rp_length/2,rp_length/2,rp_length/2};
    int[][] points;

    points=new int[2][4];
    for(int i=0; i<4; i++){
      points[0][i]=(int)Math.round(points_D[0][i]*Math.cos(theta*(dp.num+1))-points_D[1][i]*Math.sin(theta*(dp.num+1)));
      points[1][i]=(int)Math.round(points_D[0][i]*Math.sin(theta*(dp.num+1))+points_D[1][i]*Math.cos(theta*(dp.num+1)));
    }
    rp=new Polygon(points[0],points[1],4);
    return new DetailedPolygon(rp,(int)Math.round(rp_length),dp.num+1);
  }
  Color getColor(int i){
    Color c = new Color(255-i*255/(num-1),255-i*40/(num-1),255-i*40/(num-1));
    return c;
  }
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D)g;
    g2d.translate(getWidth()/2,getHeight()/2);
    g2d.scale(1,-1);
    DetailedPolygon[] ps = new DetailedPolygon[num];
    for(int i=0; i<num; i++){
      if(i==0){
        ps[i]=getFirstSquare();
      }
      else{
        ps[i]=getNested(ps[i-1]);
      }
      g2d.setPaint(getColor(i));
      g2d.fillPolygon(ps[i].p);
    }

  }
}
