import javax.swing.*;
import javax.swing.plaf.*;
import java.awt.*;
import java.util.ArrayList;

public class gridPanel extends JPanel{
    final int maxCol = 50;
    final int maxRow = 50;
    final int nodeSize = 15;
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
        setStartNode(49, 0);
        setGoalNode(0, 49);
    //Set Random Obstacles
        for(int i = 0; i < 500; i ++){
            int col = (int)(Math.random()*50);
            int row = (int)(Math.random()*50);
            if(col == 0 && row == 49){
                continue;
            }
            if(col == 49 && row == 0){
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
        node.fCost = node.gCost+node.hCost;


        //Display costs
        if(node != startNode && node != goalNode && node.solid != true){
            //node.setText("<html>F:" + node.fCost + "<br>G:" + node.gCost+"</html>"); 
        }
    }
    public void slowSearch(){
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
            double bestNodeCost = 999;
            for(int i = 0; i < openList.size(); i++){
                //This uses Dijkstra's algorithm
                /*if(openList.get(i).fCost < bestNodeCost){
                    bestNodeIndex = i;
                    bestNodeCost = openList.get(bestNodeIndex).fCost;
                }
                else if(openList.get(i).fCost == bestNodeCost){
                    if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
                        bestNodeIndex = i;
                    }
                }*/
                
                // This uses Greedy Best-First Search
                if(openList.get(i).hCost < bestNodeCost){
                    bestNodeIndex = i;
                    bestNodeCost = openList.get(bestNodeIndex).hCost;
                } 
                
            }
            currentNode = openList.get(bestNodeIndex);
            if(currentNode == goalNode){
                goalReached = true;
                trackPath();
            }
        }
    }
    public void search(){
        while(goalReached == false){
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
            double bestNodeCost = 999;
            for(int i = 0; i < openList.size(); i++){
                //This uses Dijkstra's algorithm
                /*if(openList.get(i).fCost < bestNodeCost){
                    bestNodeIndex = i;
                    bestNodeCost = openList.get(bestNodeIndex).fCost;
                }
                else if(openList.get(i).fCost == bestNodeCost){
                    if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
                        bestNodeIndex = i;
                    }
                }*/
                
                // This uses Greedy Best-First Search
                if(openList.get(i).hCost < bestNodeCost){
                    bestNodeIndex = i;
                    bestNodeCost = openList.get(bestNodeIndex).hCost;
                } 
                
            }
            currentNode = openList.get(bestNodeIndex);
            if(currentNode == goalNode){
                goalReached = true;
                trackPath();
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