import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

public class Window {

  public static JFrame f = new JFrame("Alchimia");
  public static ArrayList<Element> ElementList = new ArrayList<Element>();
  public static BufferedImage spriteSheet;


  //List of all Recipes
  public static ElementType[][] Recipes = {
    {ElementType.HYDROGEN, ElementType.OXYGEN, ElementType.WATER},
    {ElementType.CARBON, ElementType.WATER, ElementType.CARBONWATER},
    {ElementType.CARBON, ElementType.HYDROGEN, ElementType.ORGANIC},
    {ElementType.HYDROGEN, ElementType.NITROGEN, ElementType.AMMONIA},
    {ElementType.SODIUM, ElementType.CHLORINE, ElementType.SALT},
    {ElementType.CARBONWATER, ElementType.SALT, ElementType.TONIC},
    {ElementType.ORGANIC, ElementType.OXYGEN, ElementType.SUGAR},
    {ElementType.CARBONWATER, ElementType.SUGAR, ElementType.SODA},
  };

  public static void main(String[] args) {

    //Initalization of JFrame
    f.setLayout(null);
    f.setSize(512, 512);
    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    f.setVisible(true);

    try{
      spriteSheet = ImageIO.read(new File("resources/spritesheet.png"));
    } catch(IOException e){
      e.printStackTrace();
    }

    //Add Inital Elements
    addElement(ElementType.OXYGEN, 0, 0, false);
    addElement(ElementType.CARBON, 128, 0, false);
    addElement(ElementType.HYDROGEN, 256, 0, false);
    addElement(ElementType.OXYGEN, 384, 0, false);
    addElement(ElementType.HYDROGEN, 0, 128, false);
    addElement(ElementType.CARBON, 128, 128, false);
    addElement(ElementType.CHLORINE, 256, 128, false);
    addElement(ElementType.ALUMINUM, 384, 128, false);
    addElement(ElementType.SODIUM, 0, 256, false);
    f.repaint();

  }

  //Repaints the JFrame
  public static void redraw(){
    f.repaint();
  }

  //Checks for hitbox collision between two Elements
  public static void checkCollision(Element ele){
    Rectangle r1 = ele.getBounds();

    for(int i = 0; i < ElementList.size(); i++){
      if(ElementList.get(i) != ele && ElementList.get(i).getBounds().intersects(r1)){
        merge(ele, ElementList.get(i));
      }
    }
  }

  //Checks possible merger between Element e1 and e2
  public static void merge(Element e1, Element e2){
    if(recipe(e1.getType(), e2.getType())!=null){
      removeElement(e1);
      removeElement(e2);
      addElement(recipe(e1.getType(), e2.getType()), e1.getX(), e2.getY(), true);
    }
  }

  //Creates new Element object
  public static void addElement(ElementType type, int x, int y, boolean b){
    Element temp = new Element(type, x, y);
    ElementList.add(temp);
    f.add(temp);
    if(b){
      f.repaint();
    }
  }

  //Removes Element from JFrame and ElementList
  public static void removeElement(Element e1){
    f.remove(e1);
    ElementList.remove(e1);
    f.repaint();
  }

  //Checks if a recipe exists for ElementTypes e1 and e2
  public static ElementType recipe(ElementType e1, ElementType e2){
    for(ElementType[] recipe : Recipes){
      if((recipe[0].equals(e1) || recipe[0].equals(e2)) && (recipe[1].equals(e1) || recipe[1].equals(e2))){
        return recipe[2];
      }
    }
    return null;
  }

}
