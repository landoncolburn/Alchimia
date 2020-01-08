import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.*;

public class CommandPrompt extends JTextField{

  private static final long serialVersionUID = 42l;

  public CommandPrompt(){
    setBounds(0, (int)Window.size.getHeight()-32, (int)Window.size.getWidth(), 32);
    addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        runCommand(getText());
        hideCP();
      }
    });
  }

  public void showCP(){
    setVisible(true);
    requestFocus();
  }

  public void hideCP(){
    setVisible(false);
    Window.f.requestFocus();
  }

  public boolean runCommand(String command){
    String args[] = command.split("\\s");
    switch(args[0]){
      case "add":
        try{
          Window.addElement(ElementType.valueOf(args[1]), 100, 100, true);
          return true;
        }catch(IllegalArgumentException e){
          System.out.println("Invalid Element");
          return false;
        }
      case "remove":
        Window.ElementList.forEach((e) -> {
          if(e.getType().equals(ElementType.valueOf(args[1]))){
            Window.f.remove(e);
          }
        });
        Window.ElementList.removeIf((e) -> e.getType().equals(ElementType.valueOf(args[1])));
        Window.f.repaint();
        return true;
      case "list":
        Window.ElementList.forEach((e) -> System.out.println(e.getType()));
        return true;
      default:
        return false;
    }
  }

}
