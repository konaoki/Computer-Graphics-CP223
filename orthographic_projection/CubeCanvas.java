import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.lang.*;
import java.util.*;
public class CubeCanvas extends JPanel{
  double[][] points;
  double[][] groundPlane;
  int angleX;
  int angleY;
  int angleZ;
  int cameraAngle;
  int cameraHeight;
  double[] cameraPosition;
  double[] lightSource;
  double[][] shadowVertices;
  public CubeCanvas(){
    setPreferredSize(new Dimension(2000,1000));
    points=new double[8][4];
    shadowVertices=new double[8][4];

    int size=100;
    int count=0;
    angleX=15;
    angleY=15;
    angleZ=15;

    cameraAngle=45;
    cameraHeight=100;

    groundPlane=new double[][]{new double[]{-500,-size-200,-500,1},new double[]{-500,-size-200,500,1},{500,-size-200,500,1},new double[]{500,-size-200,-500,1}};

    lightSource=new double[]{-300,300,0,1};

    for(int i=0; i<=size; i+=size){
      for(int j=0; j<=size; j+=size){
        for(int k=0; k<=size; k+=size){
          points[count]=new double[]{i-size/2,j-size/2,k-size/2,1};
          count++;
        }
      }
    }
  }
  public double[] rotateX(double[] p, int angle){
    double rad = Math.toRadians((double)angle);
    return new double[]{p[0],p[1]*Math.cos(rad)-p[2]*Math.sin(rad),p[1]*Math.sin(rad)+p[2]*Math.cos(rad),p[3]};
  }
  public double[] rotateY(double[] p, int angle){
    double rad = Math.toRadians((double)angle);
    return new double[]{p[0]*Math.cos(rad)+p[2]*Math.sin(rad),p[1],p[2]*Math.cos(rad)-p[0]*Math.sin(rad),p[3]};
  }
  public double[] rotateZ(double[] p, int angle){
    double rad = Math.toRadians((double)angle);
    return new double[]{p[0]*Math.cos(rad)-p[1]*Math.sin(rad),p[0]*Math.sin(rad)+p[1]*Math.cos(rad),p[2],p[3]};
  }
  public double[] rotateCamera(double[] p, int angle){
    double rad = Math.toRadians((double)angle);
    cameraPosition=new double[]{0,cameraHeight,0};
    double[] temp1 = new double[]{p[0]-cameraPosition[0],p[1]-cameraPosition[1],p[2]-cameraPosition[2],1};
    double[] temp = new double[]{temp1[0],temp1[1]*Math.cos(rad)-temp1[2]*Math.sin(rad),temp1[1]*Math.sin(rad)+temp1[2]*Math.cos(rad),temp1[3]};
    return temp;
  }
  public double[][] renderShadow(double[][] cubePoints){
    double[][] vs = new double[8][4];
    for(int i=0; i<cubePoints.length; i++){
      double[] L = new double[]{cubePoints[i][0]-lightSource[0],cubePoints[i][1]-lightSource[1],cubePoints[i][2]-lightSource[2],1};
      double[] n = new double[]{0,1,0};
      double t = -1*(n[0]*(lightSource[0]-groundPlane[0][0])+n[1]*(lightSource[1]-groundPlane[0][1])+n[2]*(lightSource[2]-groundPlane[0][2]))/(n[0]*L[0]+n[1]*L[1]+n[2]*L[2]);
      double[] intersection = new double[]{lightSource[0]+t*L[0],lightSource[1]+t*L[1],lightSource[2]+t*L[2]};
      vs[i]=intersection;
    }
    return vs;
  }
  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D)g;
    g2d.translate(getWidth()/2,getHeight()/2);
    g2d.scale(1,-1);
    double[][] tempPoints=new double[8][4];
    for(int i=0; i<points.length; i++){
      for(int j=0; j<points[0].length;j++){
        tempPoints[i][j]=points[i][j];
      }
    }
    for(int i=0; i<tempPoints.length; i++){
      tempPoints[i]=rotateX(tempPoints[i],angleX);
      tempPoints[i]=rotateY(tempPoints[i],angleY);
      tempPoints[i]=rotateZ(tempPoints[i],angleZ);
    }
    shadowVertices=renderShadow(tempPoints);



    for(int i=0; i<tempPoints.length; i++){
      tempPoints[i]=rotateCamera(tempPoints[i],cameraAngle);
    }
    double[][] tempGroundPoints=new double[4][4];
    for(int i=0; i<groundPlane.length; i++){
      for(int j=0; j<groundPlane[0].length;j++){
        tempGroundPoints[i][j]=groundPlane[i][j];
      }
    }
    for(int i=0; i<groundPlane.length; i++){
      tempGroundPoints[i]=rotateCamera(tempGroundPoints[i],cameraAngle);
    }
    double[][] tempshadowVertices=new double[8][4];

    for(int i=0; i<tempshadowVertices.length; i++){
      for(int j=0; j<shadowVertices[0].length;j++){
        tempshadowVertices[i][j]=shadowVertices[i][j];
      }
    }
    for(int i=0; i<shadowVertices.length; i++){
      tempshadowVertices[i]=rotateCamera(tempshadowVertices[i],cameraAngle);
    }
    for(int i=0; i<4; i++){
      int[] p1 = new int[]{(int)Math.round(tempPoints[2*i][0]),(int)Math.round(tempPoints[2*i][1])};
      int[] p2 = new int[]{(int)Math.round(tempPoints[2*i+1][0]),(int)Math.round(tempPoints[2*i+1][1])};
      g2d.drawLine(p1[0],p1[1],p2[0],p2[1]);
      p1 = new int[]{(int)Math.round(tempPoints[i][0]),(int)Math.round(tempPoints[i][1])};
      p2 = new int[]{(int)Math.round(tempPoints[i+4][0]),(int)Math.round(tempPoints[i+4][1])};
      g2d.drawLine(p1[0],p1[1],p2[0],p2[1]);
      if(i<2){
        p1 = new int[]{(int)Math.round(tempPoints[i][0]),(int)Math.round(tempPoints[i][1])};
        p2 = new int[]{(int)Math.round(tempPoints[i+2][0]),(int)Math.round(tempPoints[i+2][1])};
        g2d.drawLine(p1[0],p1[1],p2[0],p2[1]);
      }
      else{
        p1 = new int[]{(int)Math.round(tempPoints[i+2][0]),(int)Math.round(tempPoints[i+2][1])};
        p2 = new int[]{(int)Math.round(tempPoints[i+4][0]),(int)Math.round(tempPoints[i+4][1])};
        g2d.drawLine(p1[0],p1[1],p2[0],p2[1]);
      }
    }

    //g2d.drawLine((int)Math.round(tempGroundPoints[0][0]),(int)Math.round(tempGroundPoints[0][1]),(int)Math.round(tempGroundPoints[3][0]),(int)Math.round(tempGroundPoints[3][1]));
    int[][] shadowInts = new int[2][8];
    for(int i=0; i<shadowInts.length; i++){
      for(int j=0; j<shadowInts[0].length; j++){
        shadowInts[i][j]=(int)Math.round(tempshadowVertices[j][i]);
      }
    }
    for(int i=0; i<8; i++){
      int xF=(int)Math.round(tempPoints[i][0]);
      int yF=(int)Math.round(tempPoints[i][1]);
      g2d.drawLine(shadowInts[0][i],shadowInts[1][i],xF+10*(xF-shadowInts[0][i]),yF+10*(yF-shadowInts[1][i]));
    }

    Area shadowArea=new Area(new Polygon(Arrays.copyOfRange(shadowInts[0],0,3),Arrays.copyOfRange(shadowInts[1],0,3),3));
    for(int i=0; i<8; i++){
      for(int j=0; j<8; j++){
        for(int k=0; k<8; k++){
          int[] p1,p2,p3;
          p1=new int[]{shadowInts[0][i],shadowInts[1][i]};
          p2=new int[]{shadowInts[0][j],shadowInts[1][j]};
          p3=new int[]{shadowInts[0][k],shadowInts[1][k]};
          Area tempArea=new Area(new Polygon(new int[]{p1[0],p2[0],p3[0]},new int[]{p1[1],p2[1],p3[1]},3));
          shadowArea.add(tempArea);
        }
      }
    }
    g2d.fill(shadowArea);


  }
}
