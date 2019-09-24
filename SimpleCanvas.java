
/**
* SimpleCanvas.java
*
* Part of the basic graphics Template.
*
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;


public class SimpleCanvas extends JPanel implements MouseListener
{
  Color myColor;
  Line2D.Double myLine;
  Line2D.Double secondLine;
  public SimpleCanvas ()
  {
    //The following is another way to guarantee correct size.
    setPreferredSize(new Dimension(500,500));
    setBackground(Color.lightGray);
    addMouseListener(this);
    Point2D.Double p1 = new Point2D.Double(-100,-100);
    Point2D.Double p2 = new Point2D.Double(100,100);
    myLine  = new Line2D.Double();
    myLine.setLine(p1,p2);
    secondLine = new Line2D.Double();
    secondLine.setLine(new Point2D.Double(-100,100),new Point2D.Double(100,-100));
    myColor = Color.red;
  }

  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);  //without this no background color set.

    Graphics2D g2d = (Graphics2D)g; //cast so we can use JAVA2D.
    g2d.translate(getWidth()/2,getHeight()/2);

    g2d.setPaint(myColor);
    g2d.draw(myLine);
    g2d.draw(secondLine);
    int size=100;
    g2d.fillRect(-size/2,-size/2,size,size); //solid rectangle

  }

  public void mouseClicked(MouseEvent e)
  {
    System.out.println("Mouse Clicked");
    myColor = Color.green;
    repaint();
  }

  //Empty methods to satisfy MouseListener interface
  public void mouseEntered(MouseEvent e){}

    public void mouseExited(MouseEvent e){}

      public void mousePressed(MouseEvent e){}

        public void mouseReleased(MouseEvent e){}





        }// SimpleCanvas
