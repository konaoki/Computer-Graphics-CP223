import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.lang.*;
import java.util.*;
public class CubeCanvas extends JPanel{
  PrecisePoint[] cubeVertices;
  int size=500;
  int count=0;
  public CubeCanvas(){
    setPreferredSize(new Dimension(2000,1000));
    cubeVertices=new PrecisePoint[8];

    //creates all vertices for the initial cube
    for(int i=0; i<=size; i+=size){
      for(int j=0; j<=size; j+=size){
        for(int k=0; k<=size; k+=size){
          cubeVertices[count]=new PrecisePoint(i-size/2,j-size/2,k-size/2);
          cubeVertices[count]=cubeVertices[count].returnRotateOnVector(30,PrecisePoint.origin,PrecisePoint.xHat); //sets initial rotation for the cube
          cubeVertices[count]=cubeVertices[count].returnRotateOnVector(30,PrecisePoint.origin,PrecisePoint.yHat);
          cubeVertices[count]=cubeVertices[count].returnRotateOnVector(30,PrecisePoint.origin,PrecisePoint.zHat);
          count++;
        }
      }
    }


  }
  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D)g;
    g2d.translate(getWidth()/2,getHeight()/2);
    g2d.scale(1,-1);
    int[] xs=new int[8];
    int [] ys=new int[8];
    //converts PrecisePoint into a Graphics2D friendly data type
    for(int i=0; i<cubeVertices.length; i++){
      cubeVertices[i].toIntArr(xs,ys,i);
    }
    //all connecting vertices of the cube
    int[][] is = new int[][]{
      new int[]{0,1},
      new int[]{1,3},
      new int[]{3,2},
      new int[]{2,0},
      new int[]{2,6},
      new int[]{3,7},
      new int[]{0,4},
      new int[]{1,5},
      new int[]{4,6},
      new int[]{6,7},
      new int[]{7,5},
      new int[]{5,4}
    };
    //draws lines for the cube
    for(int i=0; i<is.length; i++){
      double c=((cubeVertices[is[i][0]].getZ()+cubeVertices[is[i][1]].getZ())/2 + size)/(size*2);
      float cf = (float)c;
      g2d.setColor(new Color(cf,cf,cf)); //color gets lighter as the lines move farther back
      g2d.drawLine(xs[is[i][0]],ys[is[i][0]],xs[is[i][1]],ys[is[i][1]]);
    }
  }
}
