import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

class SpiralGUI extends JPanel implements ChangeListener
 {
 	SpiralCanvas sc;
	JSlider angleSlider;
  JSlider numSlider;
 	public SpiralGUI(SpiralCanvas sc)
	{
		this.sc=sc;

		setLayout(new GridLayout(1,3,30,10));

    angleSlider = new JSlider(JSlider.VERTICAL,0,45,sc.angle);
		angleSlider.setMajorTickSpacing(50);
		angleSlider.setMinorTickSpacing(10);
		angleSlider.setPaintTicks(true);
		angleSlider.setPaintLabels(true);
		angleSlider.addChangeListener(this);
		JLabel alabel = new JLabel("Angle");
		JPanel a = new JPanel();
		a.setLayout(new BoxLayout(a, BoxLayout.Y_AXIS));
		a.add(alabel);
    a.add(angleSlider);
		add(a);


		numSlider = new JSlider(JSlider.VERTICAL,2,50,sc.num);
		numSlider.addChangeListener(this);
		numSlider.setMajorTickSpacing(50);
		numSlider.setMinorTickSpacing(10);
		numSlider.setPaintTicks(true);
		numSlider.setPaintLabels(true);

		JLabel nlabel = new JLabel("Num");
		JPanel n = new JPanel();
		n.setLayout(new BoxLayout(n, BoxLayout.Y_AXIS));
		n.add(nlabel);
    n.add(numSlider);
		add(n);
    sc.angle=angleSlider.getValue();
    sc.num=numSlider.getValue();
    sc.repaint();
   }//end contructor

   public void stateChanged(ChangeEvent ev)
   {
     //when state changes, change circle colors accordingly and repaint
     sc.angle=angleSlider.getValue();
     sc.num=numSlider.getValue();
     sc.repaint();
	 }//end stateChanged

}
