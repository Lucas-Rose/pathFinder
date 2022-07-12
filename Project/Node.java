import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Node extends JButton implements ActionListener{
    Node parent;
    int col;
    int row;
    int gCost;
    int hCost;
    int fCost;

    boolean start;
    boolean goal;
    boolean solid;
    boolean open;
    boolean checked;


    public Node(int col, int row){
        this.col = col;
        this.row = row;

        setBackground(Color.darkGray);
        setForeground(Color.white);
        addActionListener(this);
    }
    public void setAsStart(){
        setBackground(Color.blue);
        start = true;
    }
    public void setAsGoal(){
        setBackground(Color.green);
        goal = true;
    }
    public void setAsSolid(){
        setBackground(Color.black);
        solid = true;
    }
    public void setAsOpen(){
        open = true;
    }
    public void setAsChecked(){
        if(start == false && goal == false){
            setBackground(Color.orange);
        }
        checked = true;
    }
    public void setAsPath(){
        setBackground(Color.red);
    }
    public void actionPerformed(ActionEvent e){
        setBackground(Color.black);
        setForeground(Color.black);
        solid = true;
    }
}
