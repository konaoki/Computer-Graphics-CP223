import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

class CubeGUI extends JPanel implements ChangeListener
 {
 	CubeCanvas cc;
	JSlider xSlider;
  JSlider ySlider;
  JSlider zSlider;
  JSlider cameraSlider;
  JSlider lightSource;
 	public CubeGUI(CubeCanvas cc)
	{
		this.cc=cc;

		setLayout(new GridLayout(1,3,30,10));

    xSlider = new JSlider(JSlider.VERTICAL,-90,90,cc.angleX);
		xSlider.setMajorTickSpacing(50);
		xSlider.setMinorTickSpacing(10);
		xSlider.setPaintTicks(true);
		xSlider.setPaintLabels(true);
		xSlider.addChangeListener(this);
		JLabel xlabel = new JLabel("X Angle");
		JPanel x = new JPanel();
		x.setLayout(new BoxLayout(x, BoxLayout.Y_AXIS));
		x.add(xlabel);
    x.add(xSlider);
		add(x);


    ySlider = new JSlider(JSlider.VERTICAL,-90,90,cc.angleY);
		ySlider.setMajorTickSpacing(50);
		ySlider.setMinorTickSpacing(10);
		ySlider.setPaintTicks(true);
		ySlider.setPaintLabels(true);
		ySlider.addChangeListener(this);
		JLabel ylabel = new JLabel("Y Angle");
		JPanel y = new JPanel();
		y.setLayout(new BoxLayout(y, BoxLayout.Y_AXIS));
		y.add(ylabel);
    y.add(ySlider);
		add(y);

    zSlider = new JSlider(JSlider.VERTICAL,-90,90,cc.angleZ);
		zSlider.setMajorTickSpacing(50);
		zSlider.setMinorTickSpacing(10);
		zSlider.setPaintTicks(true);
		zSlider.setPaintLabels(true);
		zSlider.addChangeListener(this);
		JLabel zlabel = new JLabel("Z Angle");
		JPanel z = new JPanel();
		z.setLayout(new BoxLayout(z, BoxLayout.Y_AXIS));
		z.add(zlabel);
    z.add(zSlider);
		add(z);

    cameraSlider = new JSlider(JSlider.VERTICAL,-180,180,cc.cameraAngle);
		cameraSlider.setMajorTickSpacing(50);
		cameraSlider.setMinorTickSpacing(10);
		cameraSlider.setPaintTicks(true);
		cameraSlider.setPaintLabels(true);
		cameraSlider.addChangeListener(this);
		JLabel clabel = new JLabel("Camera Angle");
		JPanel c = new JPanel();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
		c.add(clabel);
    c.add(cameraSlider);
		add(c);

    lightSource = new JSlider(JSlider.VERTICAL,-500,500,(int)Math.round(cc.lightSource[0]));
		lightSource.setMajorTickSpacing(50);
		lightSource.setMinorTickSpacing(10);
		lightSource.setPaintTicks(true);
		lightSource.setPaintLabels(true);
		lightSource.addChangeListener(this);
		JLabel llabel = new JLabel("lightSource");
		JPanel l = new JPanel();
		l.setLayout(new BoxLayout(l, BoxLayout.Y_AXIS));
		l.add(llabel);
    l.add(lightSource);
		add(l);


    cc.angleX=xSlider.getValue();
    cc.angleY=ySlider.getValue();
    cc.angleZ=zSlider.getValue();
    cc.cameraAngle=cameraSlider.getValue();
    cc.lightSource[0]=lightSource.getValue();
    cc.repaint();
   }//end contructor

   public void stateChanged(ChangeEvent ev)
   {
     //when state changes, change circle colors accordingly and repaint
     cc.angleX=xSlider.getValue();
     cc.angleY=ySlider.getValue();
     cc.angleZ=zSlider.getValue();
     cc.cameraAngle=cameraSlider.getValue();
     cc.lightSource[0]=lightSource.getValue();
     cc.repaint();
	 }//end stateChanged

}
