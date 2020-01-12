/*
  Landon Colburn
  ©2019
*/
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class ToolTip extends JLabel {

  private static final long serialVersionUID = 42l;

  private int xPos = 0;
  private int yPos = 0;

  private int l = 0;
  private int w = 35;

  public ToolTip(){
    setVisible(false);
    setText("ToolTipNotFound");
    setForeground(Color.WHITE);
    setBackground(Color.BLACK);
    setBounds(0, 0, 100, 50);
    setOpaque(true);
    setBorder(new EmptyBorder(0,10,0,10));
    setLocation(100, 100);
  }

  public void setPos(Point p){
    xPos = p.x;
    yPos = p.y;
    if(xPos+l<=(int)Window.size.getWidth()){
      setLocation(xPos, yPos);
    } else {
      setLocation(xPos-l, yPos);
    }
  }

  public void setTitle(String s){
    setForeground(Color.WHITE);
    setText(s);
    l = 20+SwingUtilities.computeStringWidth(this.getFontMetrics(this.getFont()), s);
    setBounds(xPos, yPos, l, w);
  }

  public void setTitle(String s, int color){
    switch(color){
      case 0:
        setForeground(Color.WHITE);
        break;
      case 1:
        setForeground(Color.LIGHT_GRAY);
        break;
      case 2:
        setForeground(Color.PINK);
        break;
      case 3:
        setForeground(Color.CYAN);
        break;
      case 4:
        setForeground(Color.MAGENTA);
        break;
      case 5:
        setForeground(Color.ORANGE);
        break;
      default:
        setForeground(Color.WHITE);
        break;
    }
    setText(s);
    l = 20+SwingUtilities.computeStringWidth(this.getFontMetrics(this.getFont()), s);
    setBounds(xPos, yPos, l, w);
  }

  public void setTipVis(boolean b){
    if(b){
      setCursor(new Cursor(Cursor.HAND_CURSOR));
      setVisible(true);
    } else {
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      setVisible(false);
    }
    System.out.println(b);
  }

}
