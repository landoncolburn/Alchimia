/*
  Landon Colburn
  ©2019
*/

import java.util.*;
import java.io.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Producer extends Element{

  private static final long serialVersionUID = 42l;

  private ElementType product;
  private static Random rnd = new Random();
  private Timer timer = new Timer();
  private ElementType[] rares = {ElementType.SODIUM, ElementType.ALUMINUM, ElementType.POTASSIUM, ElementType.CHLORINE, ElementType.SILICON, ElementType.PHOSPHORUS, ElementType.SILVER, ElementType.GOLD, ElementType.CALCIUM, ElementType.LITHIUM};

  public Producer(ElementType type, int iX, int iY, ElementType product){
    super(type, iX, iY);
    this.product = product;
    deactivate();
    timer.scheduleAtFixedRate(new TimerTask() {
    @Override
      public void run() {
          if(Window.ElementList.size()<19 && active){
            produce();
          }
      }
    }, 0, 25000);   // 1000 Millisecond = 1 second
  }

  public void produce() {
    Window.addElement(product, rnd.nextInt((int)Window.size.getWidth()-128), rnd.nextInt((int)Window.size.getHeight()-128), true);
    if(Math.random() > 0.8){
      Window.addElement(rares[(int)(Math.random()*rares.length)], rnd.nextInt((int)Window.size.getWidth()-128), rnd.nextInt((int)Window.size.getHeight()-128), true);
    }
  }

  @Override
  public boolean canMove(){
    return false;
  }

}
