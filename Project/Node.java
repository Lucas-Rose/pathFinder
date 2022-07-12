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
    public void actionPerformed(ActionEvent e){
        setBackground(Color.orange);
    }
}
