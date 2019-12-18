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
    setBorder(new EmptyBorder(0,10,0,5));
    setLocation(100, 100);
  }

  public void setPos(Point p){
    xPos = p.x;
    yPos = p.y;
    setLocation(xPos, yPos);
  }

  public void setTitle(String s){
    setText(s);
    l = s.length() * 12;
    setBounds(xPos, yPos, l, w);
  }

}
