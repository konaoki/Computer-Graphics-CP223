import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.lang.*;
public class PythTriangle extends JPanel implements MouseListener{
  Color color;
  Color colorRect;
  Polygon triangle;
  Polygon[] sqrs;
  public PythTriangle(){
    int size=100;
    int angle=30;
    setPreferredSize(new Dimension(500,500));
    setBackground(Color.lightGray);
    addMouseListener(this);
    int[] xs = new int[4];
    int[] ys = new int[4];
    sqrs = new Polygon[3];
    int hypot;
    xs[0]=0;
    xs[1]=size;
    xs[2]=size;
    ys[0]=0;
    ys[1]=0;
    int height=(int)Math.round(Math.tan(Math.toRadians(angle))*size);
    ys[2]=height;
    hypot=(int)Math.round(height/Math.sin(Math.toRadians(angle)));
    triangle = new Polygon(xs,ys,3);
    xs[0]=0;
    xs[1]=size;
    xs[2]=xs[1];
    xs[3]=xs[0];
    ys[0]=-size;
    ys[1]=ys[0];
    ys[2]=0;
    ys[3]=ys[2];
    sqrs[0]=new Polygon(xs,ys,4);
    xs[0]=size;
    xs[1]=size+height;
    xs[2]=xs[1];
    xs[3]=xs[0];
    ys[0]=0;
    ys[1]=ys[0];
    ys[2]=height;
    ys[3]=ys[2];
    sqrs[1]=new Polygon(xs,ys,4);
    xs[0]=0;
    xs[1]=size;
    xs[2]=size-height;
    xs[3]=-height;
    ys[0]=0;
    ys[1]=height;
    ys[2]=size+height;
    ys[3]=size;
    sqrs[2]=new Polygon(xs,ys,4);
    color = Color.red;
    colorRect = Color.green;
  }
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);  //without this no background color set.

    Graphics2D g2d = (Graphics2D)g; //cast so we can use JAVA2D.
    g2d.translate(getWidth()/2,getHeight()/2);
    g2d.setPaint(color);
    g2d.fillPolygon(triangle);
    g2d.setPaint(colorRect);
    for(int i=0; i<3; i++){
      g2d.fillPolygon(sqrs[i]);
    }

  }

  public void mouseClicked(MouseEvent e)
  {
    System.out.println("Mouse Clicked");
    if(color.equals(Color.red)){
      color = Color.green;
      colorRect = Color.red;
    }
    else{
      color = Color.red;
      colorRect = Color.green;
    }

    repaint();
  }

  //Empty methods to satisfy MouseListener interface
  public void mouseEntered(MouseEvent e){}

  public void mouseExited(MouseEvent e){}

  public void mousePressed(MouseEvent e){}

  public void mouseReleased(MouseEvent e){}
}
