import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.awt.event.*;

public class Window {

  public static JFrame f = new JFrame("Alchimia");
  public static ArrayList<Element> ElementList = new ArrayList<Element>();
  public static BufferedImage spriteSheet;
  public static ToolTip tt = new ToolTip();
  public static ScoreLabel scoreLabel = new ScoreLabel(0);


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
    {ElementType.PHOSPHORUS, ElementType.OXYGEN, ElementType.PHOSPHATE},
    {ElementType.PHOSPHATE, ElementType.SUGAR, ElementType.DNA},
    {ElementType.CELL, ElementType.DNA, ElementType.ORGANISM},
    {ElementType.ORGANISM, ElementType.BONE, ElementType.VERTABRATE},
    {ElementType.CALCIUM, ElementType.PHOSPHORUS, ElementType.BONE},
    {ElementType.ORGANIC, ElementType.DNA, ElementType.CELL},
  };

  public static void main(String[] args){
    //Initalize Tooltip
    f.add(tt);
    f.add(scoreLabel);

    //Initalization of JFrame
    f.setLayout(null);
    f.setSize(512, 512);
    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    try{
      spriteSheet = ImageIO.read(new File("resources/spritesheet.png"));
    }catch(IOException e){
      e.printStackTrace();
    }

    //Add Inital Elements
    addElement(ElementType.OXYGEN, 128, 128, false);
    addElement(ElementType.HYDROGEN, 256, 128, false);

    f.repaint();
    f.setVisible(true);
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
        break;
      }
    }
  }

  //Checks possible merger between Element e1 and e2
  public static void merge(Element e1, Element e2){
    if(recipe(e1.getType(), e2.getType())!=null){
      removeElement(e1);
      removeElement(e2);
      ElementType temp = recipe(e1.getType(), e2.getType());
      addElement(temp, e1.getX(), e2.getY(), true);
      scoreLabel.addScore(temp.getRarity()*15);
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
