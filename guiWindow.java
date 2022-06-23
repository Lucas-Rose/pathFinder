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
   
    public guiWindow(){
        //Window Frame
        gui = new JFrame();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Project Window");
        gui.setSize(800,800);  
        
        //Menu Bar
        gui.setLayout(new FlowLayout());

        menu = new JMenuBar(); //Menu Object
        gui.setJMenuBar(menu);

        Grid = new JMenu("Grid"); //Grid Dropdown
        menu.add(Grid);

        increase = new JMenuItem("Increase Squares"); //Increase item
        Grid.add(increase);
        decrease = new JMenuItem("Decrease Squares"); //Decrease item
        Grid.add(decrease);

        //Menu Event Handlers
        gridIncrease a = new gridIncrease();
        increase.addActionListener(a);
        gridDecrease b = new gridDecrease();
        decrease.addActionListener(b);

        //Game Grid
        gridSize = 600;
        divisions = 30;
        drawGrid(gridSize, divisions, gui.getWidth());
        gui.setVisible(true);
    }

    //Event Handlers
    public class gridIncrease implements ActionListener{
        public void actionPerformed(ActionEvent a){
            resize(1);
        }
    }
    public class gridDecrease implements ActionListener{
        public void actionPerformed(ActionEvent b){
            resize(-1);
        }
    }

    public void drawGrid(int size, int divisions, int windowSize){
        gridPanel grid = new gridPanel(size, divisions, windowSize, gui);
        Container pane = gui.getContentPane();
        pane.setLayout(new GridLayout(1,1));
        pane.add(grid); 
    }

    //Dynamic Grid Resize - Redraws the Grid and Obstacles
    public void resize(int scale){
        Container pane = gui.getContentPane();
        pane.removeAll();
        gridPanel grid = new gridPanel(gridSize, divisions+(10*scale), gui.getWidth(), gui);
        divisions += (10*scale);
        pane.add(grid);
        gui.setVisible(true);
    }
}
