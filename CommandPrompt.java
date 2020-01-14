import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;

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
        for(int i = 0; i < ElementType.values().length; i++){
          if(ElementType.valueOf(args[1]).equals(ElementType.values()[i])){
            Window.addElement(ElementType.valueOf(args[1]), 100, 100, true);
            return true;
          }
        }
        return false;
      case "remove":
        Window.ElementList.forEach((e) -> {
          if(e.getType().equals(ElementType.valueOf(args[1]))){
            Window.f.remove(e);
          }
        });
        Window.ElementList.removeIf((e) -> e.getType().equals(ElementType.valueOf(args[1])));
        Window.f.repaint();
        return true;
      case "removeall":
        Window.ElementList.forEach((e) -> {
          Window.f.remove(e);
        });
        Window.ElementList.clear();
        Window.f.repaint();
        return true;
      case "list":
        Window.ElementList.forEach((e) -> System.out.println(e.getType()));
        return true;
      case "log":
        System.out.println(args[1]);
        return true;
      default:
        return false;
    }
  }

}
