package pathFinder;
import java.awt.*;
import javax.swing.*;

public class gridPanel extends JPanel{
    int div; //Number of Squares; ie. area = div^2
    int size; //Size of the Grid (Pixels)
    public gridPanel(int gridSize, int divisions, int windowSize, JFrame gui){
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
        int start = 0;
        int end = start+size;
        //Border
        g.drawLine(start,start,end,start); //top
        g.drawLine(start,start,start,end); //left
        g.drawLine(start,end,end,end); //bottom
        g.drawLine(end,start,end,end); //right

        for(int i = start + (size/div); i < end; i += (size/div)){ //Horizontal Lines
            g.drawLine(start, i, end, i);   
        }
        for(int j = start + (size/div); j < end; j += (size/div)){ //Vertical Lines
            g.drawLine(j, start, j, end);
        }

        //Random Obstacle / Map Generator
        for(int i = 0; i < 100; i ++){
            //Generate xCoord between 0 and bottom-right square
            double xlocation = 10+(Math.random()*(size-(size/div)));
            int xloc = (int)xlocation;
            xloc = refactor(xloc); //Round to nearest div

            //Generate yCoord between 0 and bottom-right square
            double yLocation = 10+(Math.random()*(size-(size/div)));
            int yloc = (int)yLocation;
            yloc = refactor(yloc); //Round to nearest div

            paintSquare(g, xloc, yloc);
        }
    }

    public void paintSquare(Graphics g, int xloc, int yloc){
        g.setColor(Color.orange);
        g.fillRect(xloc, yloc, size/div, size/div);
    }
    
    public int refactor(int coord){
        while(coord % (size/div) != 0){
            coord--;
        }
        return coord;
    }
}