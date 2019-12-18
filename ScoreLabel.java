/*
  Landon Colburn
  ©2019
*/
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class ScoreLabel extends JLabel{

  private static final long serialVersionUID = 42l;

  private static int score = 0;

  public ScoreLabel(int s){
    setFont(getFont().deriveFont(18.0f));
    setOpaque(true);
    setBorder(new EmptyBorder(10,10,10,10));
    setBackground(Color.WHITE);

    setScore(s);
  }

  public void setScore(int n){
    score=n;
    setText("Energy: "+score);
    int scorel = 20+SwingUtilities.computeStringWidth(getFontMetrics(getFont()),getText());
    setBounds(10, 10, scorel, 28);
  }

  public void addScore(int n){
    setScore(score+n);
  }

}
