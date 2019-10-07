import java.util.*;
public class PrecisePoint{
  double x;
  double y;
  double z;
  public static PrecisePoint origin = new PrecisePoint(0,0,0);
  public static PrecisePoint xHat = new PrecisePoint(1,0,0);
  public static PrecisePoint yHat = new PrecisePoint(0,1,0);
  public static PrecisePoint zHat = new PrecisePoint(0,0,1);

  public PrecisePoint(double x,double y,double z){
    this.x=x;
    this.y=y;
    this.z=z;
  }
  public PrecisePoint(PrecisePoint p){
    this.x=p.getX();
    this.y=p.getY();
    this.z=p.getZ();
  }
  public void rotateX(double angle, PrecisePoint center){//rotate a point around a center by angle degrees on its local x Axis
    double[] tempPrecisePoint = new double[3];
    double rad = Math.toRadians(angle);
    double tx=getX()-center.getX();
    double ty=getY()-center.getY();
    double tz=getZ()-center.getZ();
    tempPrecisePoint[0]=tx;
    tempPrecisePoint[1]=ty*Math.cos(rad)-tz*Math.sin(rad);
    tempPrecisePoint[2]=ty*Math.sin(rad)+tz*Math.cos(rad);
    x=tempPrecisePoint[0]+center.getX();
    y=tempPrecisePoint[1]+center.getY();
    z=tempPrecisePoint[2]+center.getZ();
  }
  public void rotateY(double angle, PrecisePoint center){//rotate a point around a center by angle degrees on its local y Axis
    double[] tempPrecisePoint = new double[3];
    double rad = Math.toRadians(angle);
    double tx=getX()-center.getX();
    double ty=getY()-center.getY();
    double tz=getZ()-center.getZ();
    tempPrecisePoint[0]=tx*Math.cos(rad)+tz*Math.sin(rad);
    tempPrecisePoint[1]=ty;
    tempPrecisePoint[2]=-1*tx*Math.sin(rad)+tz*Math.cos(rad);
    x=tempPrecisePoint[0]+center.getX();
    y=tempPrecisePoint[1]+center.getY();
    z=tempPrecisePoint[2]+center.getZ();
  }
  public void rotateZ(double angle, PrecisePoint center){//rotate a point around a center by angle degrees on its local z Axis
    double[] tempPrecisePoint = new double[3];
    double rad = Math.toRadians(angle);
    double tx=getX()-center.getX();
    double ty=getY()-center.getY();
    double tz=getZ()-center.getZ();
    tempPrecisePoint[0]=tx*Math.cos(rad)-ty*Math.sin(rad);
    tempPrecisePoint[1]=tx*Math.sin(rad)+ty*Math.cos(rad);
    tempPrecisePoint[2]=tz;
    x=tempPrecisePoint[0]+center.getX();
    y=tempPrecisePoint[1]+center.getY();
    z=tempPrecisePoint[2]+center.getZ();
  }
  public double angleBetween(PrecisePoint v2){ //return the angle between vectors in degrees
    return Math.acos(this.dot(v2)/this.magnitude()/v2.magnitude())*180/Math.PI;
  }
  public PrecisePoint returnRotateX(double angle, PrecisePoint center){//returns a PrecisePoint insteado of changing the current
    double[] tempPrecisePoint = new double[3];
    double rad = Math.toRadians(angle);
    double tx=getX()-center.getX();
    double ty=getY()-center.getY();
    double tz=getZ()-center.getZ();
    tempPrecisePoint[0]=tx;
    tempPrecisePoint[1]=ty*Math.cos(rad)-tz*Math.sin(rad);
    tempPrecisePoint[2]=ty*Math.sin(rad)+tz*Math.cos(rad);
    return new PrecisePoint(tempPrecisePoint[0]+center.getX(),tempPrecisePoint[1]+center.getY(),tempPrecisePoint[2]+center.getZ());
  }
  public PrecisePoint returnRotateY(double angle, PrecisePoint center){//returns a PrecisePoint insteado of changing the current
    double[] tempPrecisePoint = new double[3];
    double rad = Math.toRadians(angle);
    double tx=getX()-center.getX();
    double ty=getY()-center.getY();
    double tz=getZ()-center.getZ();
    tempPrecisePoint[0]=tx*Math.cos(rad)+tz*Math.sin(rad);
    tempPrecisePoint[1]=ty;
    tempPrecisePoint[2]=-1*tx*Math.sin(rad)+tz*Math.cos(rad);
    return new PrecisePoint(tempPrecisePoint[0]+center.getX(),tempPrecisePoint[1]+center.getY(),tempPrecisePoint[2]+center.getZ());
  }
  public PrecisePoint returnRotateZ(double angle, PrecisePoint center){//returns a PrecisePoint insteado of changing the current
    double[] tempPrecisePoint = new double[3];
    double rad = Math.toRadians(angle);
    double tx=getX()-center.getX();
    double ty=getY()-center.getY();
    double tz=getZ()-center.getZ();
    tempPrecisePoint[0]=tx*Math.cos(rad)-ty*Math.sin(rad);
    tempPrecisePoint[1]=tx*Math.sin(rad)+ty*Math.cos(rad);
    tempPrecisePoint[2]=ty;
    return new PrecisePoint(tempPrecisePoint[0]+center.getX(),tempPrecisePoint[1]+center.getY(),tempPrecisePoint[2]+center.getZ());
  }
  public PrecisePoint returnRotateOnVector(double angle, PrecisePoint center, PrecisePoint vector){//rotates by angle degrees on arb. axis. returns a PrecisePoint insteado of changing the current
    PrecisePoint v = vector.clone();
    if(!(v.getZ()==1 && v.magnitude()==1)){
      v = vector.setZ(0);
    }
    PrecisePoint tempV = this.clone();
    double toXZ = v.angleBetween(PrecisePoint.xHat);
    v.rotateZ(-toXZ,center);
    double toZ = v.angleBetween(PrecisePoint.zHat);
    tempV.rotateZ(-toXZ,center);
    tempV.rotateY(-toZ,center);
    tempV.rotateZ(angle,center);
    tempV.rotateY(toZ,center);
    tempV.rotateZ(toXZ,center);
    return tempV;
  }
  public PrecisePoint scale(double s){ //scales the point
    return new PrecisePoint(getX()*s,getY()*s,getZ()*s);
  }
  public PrecisePoint add(PrecisePoint p){ //adds vector
    return new PrecisePoint(x+p.getX(),y+p.getY(),z+p.getZ());
  }
  public PrecisePoint subtract(PrecisePoint p){ //subtracts vector
    return new PrecisePoint(x-p.getX(),y-p.getY(),z-p.getZ());
  }
  public double dot(PrecisePoint p){//dots a vector
    return x*p.getX()+y*p.getY()+z*p.getZ();
  }
  public PrecisePoint cross(PrecisePoint p){//crosses a vector
    return new PrecisePoint(getY()*p.getZ()-getZ()*p.getY(), getZ()*p.getX()-getX()*p.getZ(), getX()*p.getY()-getY()*p.getX());
  }

  public double getX(){
    return this.x;
  }
  public double getY(){
    return this.y;
  }
  public double getZ(){
    return this.z;
  }
  public PrecisePoint setX(double x){
    return new PrecisePoint(x,this.y,this.z);
  }
  public PrecisePoint setY(double y){
    return new PrecisePoint(this.x,y,this.z);
  }
  public PrecisePoint setZ(double z){
    return new PrecisePoint(this.x,this.y,z);
  }
  public double magnitude(){ //gets magnitude of this
    return Math.sqrt(Math.pow(getX(),2)+Math.pow(getY(),2)+Math.pow(getZ(),2));
  }

  public PrecisePoint clone(){
    return new PrecisePoint(getX(),getY(),getZ());
  }

  public String toString(){
    return getX()+", "+getY()+", "+getZ();
  }
  public int[] toInt(){
    return new int[]{
      (int)Math.round(getX()), (int)Math.round(getY()), (int)Math.round(getZ())
    };
  }
  public void toIntArr(int[] xs, int[] ys, int index){
    xs[index] = this.toInt()[0];
    ys[index] = this.toInt()[1];
  }
}
