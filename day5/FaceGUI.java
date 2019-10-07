import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.lang.*;
import java.util.*;
class FaceGUI extends JPanel implements ChangeListener
 {
	JSlider horizontal,vertical,scale,rotation;
  FaceCanvas fc;
  JButton button;
 	public FaceGUI()
	{
    fc=null;
		setLayout(new GridLayout(1,3,30,10));

    button = new JButton("Color it"); // construct a JButton
    add(button);
    button.addActionListener(new ActionListener()
   {
     public void actionPerformed(ActionEvent e)
     {
       System.out.println("button pressed");
       Random rand = new Random();
       float r = rand.nextFloat();
       float g = rand.nextFloat();
       float b = rand.nextFloat();
       fc.color=new Color(r, g, b);
       fc.repaint();
     }
   });

    horizontal = new JSlider(JSlider.VERTICAL,-500,500,0);
		horizontal.setMajorTickSpacing(50);
		horizontal.setMinorTickSpacing(10);
		horizontal.setPaintTicks(true);
		horizontal.setPaintLabels(true);
		horizontal.addChangeListener(this);
		JLabel hlabel = new JLabel("Horizontal");
		JPanel h = new JPanel();
		h.setLayout(new BoxLayout(h, BoxLayout.Y_AXIS));
		h.add(hlabel);
    h.add(horizontal);
		add(h);


		vertical = new JSlider(JSlider.VERTICAL,-200,500,0);
		vertical.addChangeListener(this);
		vertical.setMajorTickSpacing(50);
		vertical.setMinorTickSpacing(10);
		vertical.setPaintTicks(true);
		vertical.setPaintLabels(true);

		JLabel vlabel = new JLabel("Vertical");
		JPanel v = new JPanel();
		v.setLayout(new BoxLayout(v, BoxLayout.Y_AXIS));
		v.add(vlabel);
    v.add(vertical);
		add(v);

    scale = new JSlider(JSlider.VERTICAL,0,400,1);
    scale.addChangeListener(this);
    scale.setMajorTickSpacing(50);
    scale.setMinorTickSpacing(10);
    scale.setPaintTicks(true);
    scale.setPaintLabels(true);

    JLabel slabel = new JLabel("Scale");
    JPanel s = new JPanel();
    s.setLayout(new BoxLayout(s, BoxLayout.Y_AXIS));
    s.add(slabel);
    s.add(scale);
    add(s);

    rotation = new JSlider(JSlider.VERTICAL,-90,90,1);
    rotation.addChangeListener(this);
    rotation.setMajorTickSpacing(50);
    rotation.setMinorTickSpacing(10);
    rotation.setPaintTicks(true);
    rotation.setPaintLabels(true);

    JLabel rlabel = new JLabel("Rotation");
    JPanel r = new JPanel();
    r.setLayout(new BoxLayout(r, BoxLayout.Y_AXIS));
    r.add(rlabel);
    r.add(rotation);
    add(r);


   }//end contructor
   public void addCanvas(FaceCanvas f){
     fc=f;
   }

   public void stateChanged(ChangeEvent ev)
   {
     fc.changeParts();
	 }//end stateChanged





}
