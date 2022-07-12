import javax.swing.*;
import javax.swing.plaf.*;
import java.awt.*;
import java.util.ArrayList;

public class gridPanel extends JPanel{
    final int maxCol = 20;
    final int maxRow = 20;
    final int nodeSize = 50;
    final int screenWidth = nodeSize * maxCol;
    final int screenHeight = nodeSize * maxRow;
    
    Node[][] node = new Node[maxCol][maxRow];
    Node startNode, goalNode, currentNode;
    ArrayList<Node> openList = new ArrayList<>();
    ArrayList<Node> checkedList = new ArrayList<>();

    boolean goalReached = false;
    

    public gridPanel(){
        setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
        setBackground(Color.BLACK);
        setLayout(new GridLayout(maxCol, maxRow));
        this.addKeyListener(new KeyHandler(this));
        this.setFocusable(true);

        //Place Nodes on Panel
        for(int i = 0; i < maxCol; i ++){
            for(int j = 0; j < maxRow; j ++){
                node[i][j] = new Node(i,j);
                this.add(node[i][j]);
            }
        }
        setStartNode(19, 0);
        setGoalNode(0, 19);
    //Set Random Obstacles
        for(int i = 0; i < 100; i ++){
            int col = (int)(Math.random()*19);
            int row = (int)(Math.random()*19);
            if(col == 0 && row == 19){
                continue;
            }
            if(col == 19 && row == 0){
                continue;
            }
            setSolidNode(col, row);
        }
        //Set Costs
        setCostOnNodes();
    }
    private void setStartNode(int col, int row){
        node[col][row].setAsStart();
        startNode = node[col][row];
        currentNode = startNode;
    }
    private void setGoalNode(int col, int row){
        node[col][row].setAsGoal();
        goalNode = node[col][row];
    }
    private void setSolidNode(int col, int row){
        node[col][row].setAsSolid();
    }
    private void setCostOnNodes(){
        for(int i = 0; i < maxRow; i ++){
            for(int j = 0; j < maxCol; j++){
                getCost(node[i][j]);
            }
        }
    }
    private void getCost(Node node){
        //Get G Cost(The distance from the start node)
        int xDist = Math.abs(node.col - startNode.col);
        int yDist = Math.abs(node.row - startNode.row);
        node.gCost = xDist + yDist;

        //Get H Cost (The distance from the goal node)
        xDist = Math.abs(node.col - goalNode.col);
        yDist = Math.abs(node.row - goalNode.row);
        node.hCost = xDist + yDist;

        //Get F Cost (The Total Cost)
        node.fCost = node.gCost + node.hCost;


        //Display costs
        if(node != startNode && node != goalNode && node.solid != true){
            //node.setText("<html>F:" + node.fCost + "<br>G:" + node.gCost+"</html>"); 
        }
    }
    public void search(){
        if(goalReached == false){
            int col = currentNode.col;
            int row = currentNode.row;
            checkedList.add(currentNode);
            currentNode.setAsChecked();
            openList.remove(currentNode);

            //open top node
            if(row-1 >= 0){
                openNode(node[col][row-1]);
            } 
            //open left node
            if(col -1 >=0){
                openNode(node[col-1][row]);
            }
            //open bottom node
            if(row+1 < maxRow){
                openNode(node[col][row+1]);
            }
            //open right node
            if(col+1 < maxCol){
                openNode(node[col+1][row]);
            }

            int bestNodeIndex = 0;
            int bestNodefCost = 999;
            for(int i = 0; i < openList.size(); i++){
                if(openList.get(i).fCost < bestNodefCost){
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                }
                else if(openList.get(i).fCost == bestNodefCost){
                    if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
                        bestNodeIndex = i;
                    }
                }
            }
            currentNode = openList.get(bestNodeIndex);
            if(currentNode == goalNode){
                goalReached = true;
                trackPath();
                System.out.println("Goal Found, retracing");
            }
        }
    }

    private void openNode(Node node){
        if(node.open == false && node.checked == false && node.solid == false){
            node.setAsOpen();
            node.parent = currentNode;
            openList.add(node);
        }
    }
    private void trackPath(){
        Node current = goalNode;

        while(current != startNode){
            current = current.parent;
            if(current != startNode){
                current.setAsPath();
            }
        }
    }
}