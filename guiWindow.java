package pathFinder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class guiWindow extends JFrame{
    JMenuBar menu;
    JMenu Grid;
    JMenuItem increase;
    JMenuItem decrease;
    JFrame gui;
    int gridSize, divisions;
    public static void main(String args[]){
        new guiWindow();
    }

    public guiWindow(){
        gui = new JFrame();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Project Window");
        gui.setSize(800,800);  
        
        gui.setLayout(new FlowLayout());

        menu = new JMenuBar();
        gui.setJMenuBar(menu);

        Grid = new JMenu("Grid");
        menu.add(Grid);

        increase = new JMenuItem("Increase Squares");
        Grid.add(increase);

        decrease = new JMenuItem("Decrease Squares");
        Grid.add(decrease);

        gridIncrease a = new gridIncrease();
        increase.addActionListener(a);

        gridDecrease b = new gridDecrease();
        decrease.addActionListener(b);

        gridSize = 600;
        divisions = 30;
        drawGrid(gridSize, divisions, gui.getWidth());
        gui.setVisible(true);
    }

    public class gridIncrease implements ActionListener{
        public void actionPerformed(ActionEvent a){
            Container pane = gui.getContentPane();
            pane.removeAll();
            gridPanel grid = new gridPanel(gridSize, divisions+10, gui.getWidth());
            divisions += 10;
            pane.add(grid);
            gui.repaint();
            gui.setVisible(true);
        }
    }
    public class gridDecrease implements ActionListener{
        public void actionPerformed(ActionEvent b){
            Container pane = gui.getContentPane();
            pane.removeAll();
            gridPanel grid = new gridPanel(gridSize, divisions-10, gui.getWidth());
            divisions -= 10;
            pane.add(grid);
            gui.repaint();
            gui.setVisible(true);
        }
    }
    public void drawGrid(int size, int divisions, int windowSize){
        gridPanel grid = new gridPanel(size, divisions, windowSize);
        Container pane = gui.getContentPane();
        pane.setLayout(new GridLayout(1,1));
        pane.add(grid); 
    }
    
}
