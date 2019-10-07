import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.lang.*;
import java.util.*;

class CubeGUI extends JPanel implements ChangeListener
 {
 	CubeCanvas cc;
  int angle=5;

  JButton xPlus,xMinus,yPlus,yMinus,zPlus,zMinus,aPlus,aMinus;
  JTextField xVal,yVal,zVal,angleVal;
 	public CubeGUI(CubeCanvas cc)
	{
    this.cc=cc;

    //text field for the increment
		setLayout(new GridLayout(1,3,30,10));
    angleVal = new JTextField("5");
    JLabel anglabel = new JLabel("Increment");
    JPanel ang = new JPanel();
		ang.setLayout(new BoxLayout(ang, BoxLayout.Y_AXIS));
		ang.add(angleVal);
    ang.add(anglabel);
		add(ang);

    //text fields for the arb. vector values
    xVal = new JTextField("1");
    JLabel xlabel = new JLabel("X Value");
    JPanel x = new JPanel();
		x.setLayout(new BoxLayout(x, BoxLayout.Y_AXIS));
		x.add(xVal);
    x.add(xlabel);
		add(x);

    yVal = new JTextField("1");
    JLabel ylabel = new JLabel("Y Value");
    JPanel y = new JPanel();
		y.setLayout(new BoxLayout(y, BoxLayout.Y_AXIS));
		y.add(yVal);
    y.add(ylabel);
		add(y);

    zVal = new JTextField("1");
    JLabel zlabel = new JLabel("Z Value");
    JPanel z = new JPanel();
		z.setLayout(new BoxLayout(z, BoxLayout.Y_AXIS));
		z.add(zVal);
    z.add(zlabel);
		add(z);

    //buttons for incrementing and decrementing
    aPlus = new JButton("+Arb. Axis"); // construct a JButton
    add(aPlus);
    aPlus.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        buttonPressed(1, new PrecisePoint(Double.parseDouble(xVal.getText()),Double.parseDouble(yVal.getText()),Double.parseDouble(zVal.getText()) ));
      }
    });
    aMinus = new JButton("-Arb. Axis"); // construct a JButton
    add(aMinus);
    aMinus.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        buttonPressed(-1, new PrecisePoint(Double.parseDouble(xVal.getText()),Double.parseDouble(yVal.getText()),Double.parseDouble(zVal.getText()) ));
      }
    });


    xPlus = new JButton("+X Axis"); // construct a JButton
    add(xPlus);
    xPlus.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        buttonPressed(1, PrecisePoint.xHat);
      }
    });
    xMinus = new JButton("-X Axis"); // construct a JButton
    add(xMinus);
    xMinus.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        buttonPressed(-1, PrecisePoint.xHat);
      }
    });

    yPlus = new JButton("+Y Axis"); // construct a JButton
    add(yPlus);
    yPlus.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        buttonPressed(1, PrecisePoint.yHat);
      }
    });
    yMinus = new JButton("-Y Axis"); // construct a JButton
    add(yMinus);
    yMinus.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        buttonPressed(-1, PrecisePoint.yHat);
      }
    });

    zPlus = new JButton("+Z Axis"); // construct a JButton
    add(zPlus);
    zPlus.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        buttonPressed(1, PrecisePoint.zHat);
      }
    });
    zMinus = new JButton("-Z Axis"); // construct a JButton
    add(zMinus);
    zMinus.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        buttonPressed(-1, PrecisePoint.zHat);
      }
    });

   }//end contructor

   //when a button is pressed, the cube is rotated by the specified angle increment on the specified axis
   public void buttonPressed(int s, PrecisePoint v){
     angle=(int)Math.round(Double.parseDouble(angleVal.getText()));
     for(int i=0; i<cc.cubeVertices.length; i++){
       cc.cubeVertices[i]=cc.cubeVertices[i].returnRotateOnVector(s*angle,PrecisePoint.origin,v);
     }
     cc.repaint();
   }

   public void stateChanged(ChangeEvent ev)
   {
	 }//end stateChanged



}
