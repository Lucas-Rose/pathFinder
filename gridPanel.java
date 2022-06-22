package pathFinder;

import java.awt.*;
import javax.swing.*;

public class gridPanel extends JPanel{
    int div; //Number of Squares; ie. area = div^2
    int size; //Size of the Grid (Pixels)
    public gridPanel(int gridSize, int divisions, int windowSize){
        setBackground(Color.DARK_GRAY);
        if(divisions <= 0){
            divisions=1;
        }
        if(gridSize > windowSize){
            gridSize = windowSize;
        }
        size = gridSize;
        div = divisions;
        while(size % div != 0){
            size--;
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.GRAY);
        int start = 10;
        int end = start+size;
        //Border
        g.drawLine(start,start,end,start); //top
        g.drawLine(start,start,start,end); //left
        g.drawLine(start,end,end,end); //bottom
        g.drawLine(end,start,end,end); //right

        for(int i = start + (size/div); i < end; i += (size/div)){
            g.drawLine(start, i, end, i);   
        }
        for(int j = start + (size/div); j < end; j += (size/div)){
            g.drawLine(j, start, j, end);
        }
    }
}