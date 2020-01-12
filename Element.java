/*
  Landon Colburn
  ©2019
*/

import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Cursor;
import java.awt.Component;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.*;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

public class Element extends JComponent{

  private static final long serialVersionUID = 42l;

  private BufferedImage img,img2,img3,img4;
  private ElementType type;

  protected boolean active = true;

  private RescaleOp light = new RescaleOp(1, 20, null);
  private RescaleOp dark = new RescaleOp(1, -170, null);

  private int screenX = 0;
  private int screenY = 0;
  private int x = 0;
  private int y = 0;
  private int lx,ly;
  private int size = 128;

  private int cursorX = 0;
  private int cursorY = 0;

  public void paint(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.drawImage(img, 0, 0, this);
  }

  public BufferedImage copyImage(BufferedImage coverImage){
    ColorModel colorModel = coverImage.getColorModel();
    boolean isAlphaPremultiplied = coverImage.isAlphaPremultiplied();
    WritableRaster raster = coverImage.copyData(coverImage.getRaster().createCompatibleWritableRaster());
    BufferedImage newImage = new BufferedImage(colorModel, raster, isAlphaPremultiplied, null);
    return newImage;
  }

  public Element(ElementType type, int iX, int iY){
    this.type = type;
    this.x = iX;
    this.y = iY;

    setLocation(x, y);
    setBounds(x, y, size, size);
    setOpaque(true);

    img = getSprite(type.getX(), type.getY()); //Active Image
    img2 = copyImage(img); //Light Image
    img3 = copyImage(img); //Dark Image
    img4 = copyImage(img); //Reset Image
    light.filter(img2, img2);
    dark.filter(img3, img3);

    addMouseListener(new MouseListener(){
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3){
          System.out.println(type.getName()+" X: " +x+" Y: "+y + " ScreenX: " +lx+" ScreenY: "+ly+" Active:"+active);
        } else if(e.getButton() == MouseEvent.BUTTON1 && !canMove()){
          if(active){
            deactivate();
          } else {
            activate();
          }
        }
      }

      @Override
      public void mousePressed(MouseEvent e){
        screenX = e.getXOnScreen();
        screenY = e.getYOnScreen();

        x = getX();
        y = getY();
      }

      @Override
      public void mouseReleased(MouseEvent e){
        check();
      }

      @Override
      public void mouseEntered(MouseEvent e){
        if(active){
          img = img2;
          Window.tt.setTipVis(true);
          Window.tt.setTitle(type.getName(), type.getRarity());
          Window.redraw();
        } else {
          img = img3;
          Window.tt.setTipVis(true);
          Window.tt.setTitle(type.getName() + " (inactive)", -1);
          Window.redraw();
        }
      }

      @Override
      public void mouseExited(MouseEvent e){
        if(active){
          img = img4;
          Window.tt.setTipVis(false);
          Window.redraw();
        } else {
          img = img3;
          Window.tt.setTipVis(false);
          Window.redraw();
        }
      }
    });

    addMouseMotionListener(new MouseMotionListener() {
      @Override
      public void mouseDragged(MouseEvent e){
        if(canMove()){
          cursorX = e.getXOnScreen();
          cursorY = e.getYOnScreen();
          int deltaX = cursorX - screenX;
          int deltaY = cursorY - screenY;

          setLocation(x + deltaX, y + deltaY);

          //Sets tooltip location to cursor (possible refactor)
          Point p = new Point(e.getXOnScreen(), e.getYOnScreen());
          screenToFrame(p);
          Window.tt.setPos(p);
        }
      }

      @Override
      public void mouseMoved(MouseEvent e){
        //Sets tooltip location to cursor (possible refactor)
        Point p = new Point(e.getXOnScreen(), e.getYOnScreen());
        screenToFrame(p);
        Window.tt.setPos(p);
      }

    });
  }

  public Rectangle getBounds(){
    Rectangle rect = new Rectangle(getX()+20, getY()+20, size-20, size-20);
    return rect;
  }

  public void check(){
    Window.checkCollision(this);
  }

  public ElementType getType(){
    return type;
  }

  public void deactivate(){
    active = false;
    img = img3;
    Window.redraw();
    Window.tt.setTitle(type.getName() + " (inactive)", -1);
  }

  public void activate(){
    active = true;
    img = img4;
    Window.redraw();
    Window.tt.setTitle(type.getName(), type.getRarity());
  }

  public Point screenToFrame(Point tp){
    SwingUtilities.convertPointFromScreen(tp, Window.f);
    return tp;
  }

  public BufferedImage getSprite(int spritex, int spritey){
    if(Window.spriteSheet!=null){
      return Window.spriteSheet.getSubimage(spritex, spritey, 128, 128);
    } else {
      return null;
    }
  }

  public boolean canMove(){
    return true;
  }

}
