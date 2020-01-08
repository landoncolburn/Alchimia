import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.GraphicsDevice;

public class Window {

  public static JFrame f = new JFrame("Alchimia");
  public static ArrayList<Element> ElementList = new ArrayList<Element>();
  public static BufferedImage spriteSheet;
  public static ToolTip tt = new ToolTip();
  public static ScoreLabel scoreLabel = new ScoreLabel(0);
  public static Popup pop;
  public final static Dimension size = new Dimension(1000, 700);
  public static GraphicsDevice myDevice;
  public static Window win;
  public static CommandPrompt cp = new CommandPrompt();


  //List of all Recipes
  public static ElementType[][] Recipes = {
    //Uncommon
    {ElementType.HYDROGEN, ElementType.OXYGEN, ElementType.WATER},
    {ElementType.HYDROGEN, ElementType.NITROGEN, ElementType.AMMONIA},
    {ElementType.SODIUM, ElementType.CHLORINE, ElementType.SALT},
    {ElementType.CARBON, ElementType.HYDROGEN, ElementType.ORGANIC},
    {ElementType.PHOSPHORUS, ElementType.OXYGEN, ElementType.PHOSPHATE},

    //Rare
    {ElementType.CARBON, ElementType.WATER, ElementType.CARBONWATER},
    {ElementType.ORGANIC, ElementType.OXYGEN, ElementType.SUGAR},
    {ElementType.ORGANIC, ElementType.DNA, ElementType.CELL},
    {ElementType.PHOSPHATE, ElementType.SUGAR, ElementType.DNA},
    {ElementType.CALCIUM, ElementType.PHOSPHORUS, ElementType.BONE},

    //Ultra Rare
    {ElementType.CARBONWATER, ElementType.SALT, ElementType.TONIC},
    {ElementType.CARBONWATER, ElementType.SUGAR, ElementType.SODA},
    {ElementType.CELL, ElementType.DNA, ElementType.ORGANISM},

    //Legendary
    {ElementType.ORGANISM, ElementType.BONE, ElementType.VERTABRATE},
  };

  public static void main(String[] args){
    //Initalize Tooltip
    f.add(tt);
    f.add(scoreLabel);
    f.add(cp);

    //Initalization of JFrame
    f.setLayout(null);
    f.setUndecorated(true);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setResizable(false);
    f.setSize(size);

    cp.hideCP();

    f.addKeyListener(new KeyAdapter(){
      public void keyPressed(KeyEvent ke){
        if(ke.getKeyCode() == KeyEvent.VK_ESCAPE){
          System.exit(0);
        } else if(ke.getKeyCode() == 47){
          cp.showCP();
        }
      }
    });

    f.add(new Popup("Elements Game","Drag elements together to make new substances. Try to collect the most energy you can!<br><br>(Hint: Hydrogen + Oxygen = Water)"));

    try{
      spriteSheet = ImageIO.read(new File("resources/spritesheet.png"));
    }catch(IOException e){
      e.printStackTrace();
    }

    //Add Inital Elements
    Producer proHydro = new Producer(ElementType.PHYDRO, (int)Window.size.getWidth()-128, 0, ElementType.HYDROGEN);
    Producer proNitro = new Producer(ElementType.PNITRO, (int)Window.size.getWidth()-128, 128, ElementType.NITROGEN);
    Producer proOxy = new Producer(ElementType.POXY, (int)Window.size.getWidth()-128, 256, ElementType.OXYGEN);
    Producer proCarbon = new Producer(ElementType.PCARBON, (int)Window.size.getWidth()-128, 384, ElementType.CARBON);
    Producer proHelium = new Producer(ElementType.PHELIUM, (int)Window.size.getWidth()-128, 512, ElementType.HELIUM);
    f.add(proHydro);
    f.add(proNitro);
    f.add(proOxy);
    f.add(proCarbon);
    f.add(proHelium);

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
      tt.setTitle(temp.getName(), temp.getRarity());
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
