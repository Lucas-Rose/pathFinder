import javax.swing.*;
import javax.swing.plaf.*;
import java.awt.*;

public class gridPanel extends JPanel{
    final int maxCol = 20;
    final int maxRow = 20;
    final int nodeSize = 30;
    final int screenWidth = nodeSize * maxCol;
    final int screenHeight = nodeSize * maxRow;


    Node[][] node = new Node[maxCol][maxRow];
    public gridPanel(){
        setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
        setBackground(Color.BLACK);
        setLayout(new GridLayout(maxCol, maxRow));

        //Place Nodes on Panel
        for(int i = 0; i < maxCol; i ++){
            for(int j = 0; j < maxRow; j ++){
                node[i][j] = new Node(i,j);
                this.add(node[i][j]);
            }
        }
    }
}