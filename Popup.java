/*
  Landon Colburn
  ©2019
*/

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Popup extends JPanel {

  private static final long serialVersionUID = 42l;

  private String heading;
  private String text;

  private int pad = 50;

  private int width;
  private int height;

  public Popup(String titleMsg, String textMsg){

    heading = titleMsg;
    text = textMsg;

    width = (int)Window.size.getWidth();
    height = (int)Window.size.getHeight();

    setLayout(new BorderLayout());

    setOpaque(true);
    setBackground(Color.BLACK);
    setBounds(pad, pad, width-2*pad, height-2*pad);
    setBorder(new EmptyBorder(30,30,30,30));

    JLabel title = new JLabel(heading, SwingConstants.CENTER);
    title.setForeground(Color.WHITE);
    title.setFont(getFont().deriveFont(24.0f));
    add(title, BorderLayout.NORTH);

    JLabel body = new JLabel("<html><center>"+text+"</center></html>", SwingConstants.CENTER);
    body.setForeground(Color.WHITE);
    body.setFont(getFont().deriveFont(18.0f));
    add(body, BorderLayout.CENTER);

    JLabel dissmiss = new JLabel("Click anywhere to continue", SwingConstants.CENTER);
    dissmiss.setForeground(Color.WHITE);
    dissmiss.setFont(getFont().deriveFont(18.0f));
    add(dissmiss, BorderLayout.SOUTH);

    addMouseListener(new MouseListener(){
      @Override
      public void mouseClicked(MouseEvent e){
        setVisible(false);
      }

      @Override
      public void mousePressed(MouseEvent e){}

      @Override
      public void mouseReleased(MouseEvent e){}

      @Override
      public void mouseEntered(MouseEvent e){}

      @Override
      public void mouseExited(MouseEvent e){}
    });
  }

}
