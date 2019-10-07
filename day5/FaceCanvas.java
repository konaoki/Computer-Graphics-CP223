import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.lang.*;
import java.util.*;
public class FaceCanvas extends JPanel implements MouseListener{
  Ellipse2D eye1,eye2,face,ear1,ear2;
  Rectangle2D nose,eyebrow1,eyebrow2,mouth;
  int eyeHeight=0;

  int conversionFactor=100;

  FaceGUI fg;
  String partsToChange;
  int offset = 500;
  int eyeAngle=0;
  double eyeScale=1;
  int browAngle=0;
  double browScale=1;
  int earAngle=0;
  double earScale=1;
  int mouthAngle=0;
  double mouthScale=1;
  int noseAngle=0;
  double noseScale=1;
  Color color;
  public FaceCanvas(FaceGUI gui){
    fg=gui;
    partsToChange="nothing";

    eye1 = new Ellipse2D.Double(-20-15+offset,eyeHeight+offset,30,10);
    eye2 = new Ellipse2D.Double(20-15+offset,eyeHeight+offset,30,10);
    eyebrow1 = new Rectangle2D.Double(-20-25+offset,eyeHeight-20+offset,50,10);
    eyebrow2 = new Rectangle2D.Double(20-25+offset,eyeHeight-20+offset,50,10);
    face = new Ellipse2D.Double(-150+offset,-300+offset,300,500);
    mouth = new Rectangle2D.Double(-50+offset,eyeHeight+200+offset,100,20);
    nose = new Rectangle2D.Double(-10+offset,eyeHeight+50+offset,20,40);
    ear1 = new Ellipse2D.Double(150-5+offset,0+offset,10,50);
    ear2 = new Ellipse2D.Double(-150-5+offset,0+offset,10,50);


    setPreferredSize(new Dimension(2000,1000));
    addMouseListener(this);
  }

  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D)g;

    g2d.drawOval(-150+offset,-250+offset,300,500);

    RectangularShape r;
    g2d.setColor(color);
    r=eye1;
    AffineTransform transform = AffineTransform.getTranslateInstance(0,0);
    transform.rotate(Math.toRadians(eyeAngle),r.getX()+r.getWidth()/2,r.getY());
    g2d.setTransform(transform);
    g2d.fill(r);
    r=eyebrow1;
    transform = AffineTransform.getTranslateInstance(0, 0);
    transform.rotate(Math.toRadians(browAngle),r.getX()+r.getWidth()/2,r.getY());
    g2d.setTransform(transform);
    g2d.fill(r);
    r=eye2;
    transform = AffineTransform.getTranslateInstance(0,0);
    transform.rotate(Math.toRadians(-eyeAngle),r.getX()+r.getWidth()/2,r.getY());
    g2d.setTransform(transform);
    g2d.fill(r);
    r=eyebrow2;
    transform = AffineTransform.getTranslateInstance(0, 0);
    transform.rotate(Math.toRadians(-browAngle),r.getX()+r.getWidth()/2,r.getY());
    g2d.setTransform(transform);
    g2d.fill(r);

    r=mouth;
    transform = AffineTransform.getTranslateInstance(0, 0);
    transform.rotate(Math.toRadians(mouthAngle),r.getX()+r.getWidth()/2,r.getY());
    g2d.setTransform(transform);
    g2d.fill(r);

    r=nose;
    transform = AffineTransform.getTranslateInstance(0, 0);
    transform.rotate(Math.toRadians(noseAngle),r.getX()+r.getWidth()/2,r.getY());
    g2d.setTransform(transform);
    g2d.fill(r);

    r=ear1;
    transform = AffineTransform.getTranslateInstance(0, 0);
    transform.rotate(Math.toRadians(earAngle),r.getX()+r.getWidth()/2,r.getY());
    g2d.setTransform(transform);
    g2d.fill(r);

    r=ear2;
    transform = AffineTransform.getTranslateInstance(0, 0);
    transform.rotate(Math.toRadians(-earAngle),r.getX()+r.getWidth()/2,r.getY());
    g2d.setTransform(transform);
    g2d.fill(r);

  }

  public void mousePressed(MouseEvent e) {}
  public void mouseReleased(MouseEvent e) {}
  public void mouseEntered(MouseEvent e) {}
  public void mouseExited(MouseEvent e) {}

  /** Called whenever the mouse clicks.
    * Could be replaced with setting the value of a JLabel, etc. */
  public void mouseClicked(MouseEvent e) {
      Point p = e.getPoint();
      partsToChange="nothing";

      if(eye1.contains(p) || eye2.contains(p)){
        fg.horizontal.setValue((int)Math.round(eye2.getX())-(int)Math.round(eye1.getX()));
        fg.vertical.setValue((int)Math.round(eye1.getY()-offset));
        fg.rotation.setValue(eyeAngle);
        fg.scale.setValue((int)Math.round(eyeScale*conversionFactor));
        partsToChange="eyes";
      }
      else if(eyebrow1.contains(p) || eyebrow2.contains(p)){
        fg.horizontal.setValue((int)Math.round(eyebrow2.getX())-(int)Math.round(eyebrow1.getX()));
        fg.vertical.setValue((int)Math.round(eyebrow1.getY()-offset));
        fg.rotation.setValue(browAngle);
        fg.scale.setValue((int)Math.round(browScale*conversionFactor));
        partsToChange="eyebrows";
      }
      else if(ear1.contains(p) || ear2.contains(p)){
        fg.horizontal.setValue((int)Math.round(ear2.getX())-(int)Math.round(ear1.getX()));
        fg.vertical.setValue((int)Math.round(ear1.getY()-offset));
        fg.rotation.setValue(earAngle);
        fg.scale.setValue((int)Math.round(earScale*conversionFactor));
        partsToChange="ears";
      }
      else if(nose.contains(p)){
        fg.horizontal.setValue((int)Math.round(nose.getX()-offset));
        fg.vertical.setValue((int)Math.round(nose.getY()-offset));
        fg.rotation.setValue(noseAngle);
        fg.scale.setValue((int)Math.round(noseScale*conversionFactor));
        partsToChange="nose";
      }
      else if(mouth.contains(p)){
        fg.horizontal.setValue((int)Math.round(mouth.getX()-offset));
        fg.vertical.setValue((int)Math.round(mouth.getY()-offset));
        fg.rotation.setValue(mouthAngle);
        fg.scale.setValue((int)Math.round(mouthScale*conversionFactor));
        partsToChange="mouth";
      }
      else{
        partsToChange="nothing";
      }
  }
  public void changeParts(){
    if(partsToChange.equals("eyes")){
      eyeScale=(double)fg.scale.getValue()/conversionFactor;
      eye1 = new Ellipse2D.Double(-(30*eyeScale)/2-fg.horizontal.getValue()/2+offset,fg.vertical.getValue()+offset,30*eyeScale,10*eyeScale);
      eye2 = new Ellipse2D.Double(-(30*eyeScale)/2+fg.horizontal.getValue()/2+offset,fg.vertical.getValue()+offset,30*eyeScale,10*eyeScale);
      eyeAngle=fg.rotation.getValue();

    }
    if(partsToChange.equals("eyebrows")){
      browScale=fg.scale.getValue()/conversionFactor;
      eyebrow1 = new Rectangle2D.Double(-(50*browScale/2)-fg.horizontal.getValue()/2+offset,fg.vertical.getValue()+offset,50*browScale,10*browScale);
      eyebrow2 = new Rectangle2D.Double(-(50*browScale/2)+fg.horizontal.getValue()/2+offset,fg.vertical.getValue()+offset,50*browScale,10*browScale);
      browAngle=fg.rotation.getValue();

    }
    if(partsToChange.equals("ears")){
      earScale=fg.scale.getValue()/conversionFactor;
      ear1 = new Ellipse2D.Double(-(10*earScale)/2-fg.horizontal.getValue()/2+offset,fg.vertical.getValue()+offset,10*earScale,50*earScale);
      ear2 = new Ellipse2D.Double(-(10*earScale)/2+fg.horizontal.getValue()/2+offset,fg.vertical.getValue()+offset,10*earScale,50*earScale);
      earAngle=fg.rotation.getValue();
    }
    if(partsToChange.equals("nose")){
      noseScale=fg.scale.getValue()/conversionFactor;
      nose = new Rectangle2D.Double(fg.horizontal.getValue()+offset,fg.vertical.getValue()+offset,20*noseScale,40*noseScale);
      noseAngle=fg.rotation.getValue();
    }
    if(partsToChange.equals("mouth")){
      mouthScale=fg.scale.getValue()/conversionFactor;
      mouth = new Rectangle2D.Double(fg.horizontal.getValue()+offset,fg.vertical.getValue()+offset,100*mouthScale,20*mouthScale);
      mouthAngle=fg.rotation.getValue();
    }

    repaint();
  }



}
