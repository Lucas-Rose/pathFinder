import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    gridPanel gp;

    public KeyHandler(gridPanel gp){
        this.gp = gp;
    }
    public void keyTyped(KeyEvent e) {
        
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_ENTER){
            gp.search();
            System.out.println("Key Pressed");
        }
    }
    public void keyReleased(KeyEvent e) {
        
    }

}