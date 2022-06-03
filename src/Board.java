import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Board {
    private static int[][] logicMat = new int[][]{ { 9, 9, 9, 9, 0, 9, 9, 9, 9, 9, 9, 9, 0, 9, 9, 9, 9 },
            { 9, 9, 9, 9, 9, 0, 9, 9, 9, 9, 9, 0, 9, 9, 9, 9, 9 },
            { 9, 9, 9, 9, 0, 9, 0, 9, 9, 9, 0, 9, 0, 9, 9, 9, 9 },
            { 9, 9, 9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9, 9, 9, 9 },
            { 9, 9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9, 9, 9 },
            { 9, 9, 9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9, 9, 9, 9 },
            { 9, 9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9, 9, 9 },
            { 9, 9, 9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9, 9, 9, 9 },
            { 9, 9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9, 9, 9 },
            { 9, 9, 9, 1, 9, 0, 9, 0, 9, 0, 9, 0, 9, 2, 9, 9, 9 },
            { 9, 9, 1, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 2, 9, 9 },
            { 9, 1, 9, 1, 9, 0, 9, 0, 9, 0, 9, 0, 9, 2, 9, 2, 9 },
            { 1, 9, 1, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 2, 9, 2 },
            { 9, 1, 9, 1, 9, 0, 9, 0, 9, 0, 9, 0, 9, 2, 9, 2, 9 },
            { 9, 9, 1, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 2, 9, 9 },
            { 9, 9, 9, 1, 9, 0, 9, 0, 9, 0, 9, 0, 9, 2, 9, 9, 9 },
            { 9, 9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9, 9, 9 },
            { 9, 9, 9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9, 9, 9, 9 },
            { 9, 9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9, 9, 9 },
            { 9, 9, 9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9, 9, 9, 9 },
            { 9, 9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9, 9, 9 },
            { 9, 9, 9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9, 9, 9, 9 },
            { 9, 9, 9, 9, 0, 9, 0, 9, 9, 9, 0, 9, 0, 9, 9, 9, 9 },
            { 9, 9, 9, 9, 9, 0, 9, 9, 9, 9, 9, 0, 9, 9, 9, 9, 9 },
            { 9, 9, 9, 9, 0, 9, 9, 9, 9, 9, 9, 9, 0, 9, 9, 9, 9 }};
         /* // 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24
            {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 1, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}, //0
            {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 1, 9, 1, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}, //1
            {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 1, 9, 1, 9, 1, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}, //2
            {9, 9, 9, 9, 9, 9, 9, 9, 9, 1, 9, 1, 9, 1, 9, 1, 9, 9, 9, 9, 9, 9, 9, 9, 9}, //3
            {0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0}, //4
            {9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9}, //5
            {9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9}, //6
            {9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9, 9}, //7
            {9, 9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9, 9, 9}, //8
            {9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9, 9}, //9
            {9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9}, //10
            {9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9}, //11
            {0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0}, //12
            {9, 9, 9, 9, 9, 9, 9, 9, 9, 2, 9, 2, 9, 2, 9, 2, 9, 9, 9, 9, 9, 9, 9, 9, 9}, //13
            {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 2, 9, 2, 9, 2, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}, //14
            {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 2, 9, 2, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}, //15
            {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 2, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}};//16*/
    /*
  // 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24
    {9, 9, 9, 9, 0, 9, 9, 9, 9, 9, 9, 9, 1, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}, //0
    {9, 9, 9, 9, 9, 0, 9, 9, 9, 9, 9, 1, 9, 1, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}, //1
    {9, 9, 9, 9, 0, 9, 0, 9, 9, 9, 0, 9, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}, //2
    {9, 9, 9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 1, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}, //3
    {9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0}, //4
    {9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9}, //5
    {9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9}, //6
    {9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9, 9, 0, 9, 0, 9, 0, 9, 9, 9}, //7
    {9, 9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9, 9, 0, 9, 0, 9, 0, 9, 9, 9, 9}, //8
    {9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 2, 9, 9, 0, 9, 0, 9, 0, 9, 9, 9}, //9
    {9, 9, 1, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9}, //10
    {9, 1, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 2, 2, 9, 0, 9, 0, 9, 0, 9, 0, 9}, //11
    {1, 9, 1, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0}, //12
    {9, 1, 9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 2, 2, 2, 9, 9, 9, 9, 9, 9, 9, 9, 9}, //13
    {9, 9, 1, 9, 0, 9, 0, 9, 9, 9, 0, 9, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}, //14
    {9, 9, 9, 9, 9, 0, 9, 9, 9, 9, 9, 0, 9, 2, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}, //15
    {9, 9, 9, 9, 0, 9, 9, 9, 9, 9, 9, 9, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}};//16
    */
    private static Point[] player1 = new Point[10];
    private static Point[] player2 = new Point[10];
    public static Vertex[][] board = new Vertex[25][17];

    Board() {

        for (int row = 0; row < 25; row++) {
            for (int column = 0; column < 17; column++) {
                board[row][column] = new Vertex(row, column, -1);
                if (logicMat[row][column] == 1) {
                    board[row][column].setOccupant(1);
                } else if (logicMat[row][column] == 2) {
                    board[row][column].setOccupant(2);
                } else if (logicMat[row][column] == 0) {
                    board[row][column].setOccupant(0);
                }
            }
        }
    }

    public static ArrayList<Vertex> checkForPossibleMoves(int x, int y){
        Queue<Vertex> valid = new LinkedList<>();
        ArrayList<Vertex> endPoints = new ArrayList<>();
        Vertex currentVertex;
        if(y > 0 && x > 0){
            valid.add(board[x-1][y-1]);
        }
        if(y > 0 && x < 24){
            valid.add(board[x+1][y-1]);
        }
        if(x > 1){
            valid.add(board[x-2][y]);
        }
        if(x > 0 && y < 17){
            valid.add(board[x-1][y+1]);
        }
        if(x < 23){
            valid.add(board[x+2][y]);
        }
        if(x < 24 && y < 17){
            valid.add(board[x+1][y+1]);
        }
        //set of vertices
        while(!valid.isEmpty()){
            currentVertex = valid.poll();
            if(currentVertex.getOccupant() == 0){
                endPoints.add(currentVertex);
            }
            else if (currentVertex.getOccupant() != -1){
                int currentX = currentVertex.getPoint().getX();
                int currentY = currentVertex.getPoint().getY();
                if(currentY > 0 && currentX > 0){
                    valid.add(board[currentX-1][currentY-1]);
                }
                if(currentY > 0 && currentX < 24){
                    valid.add(board[currentX+1][currentY-1]);
                }
                if(currentX > 1){
                    valid.add(board[currentX-2][currentY]);
                }
                if(currentX > 0 && currentY < 17){
                    valid.add(board[currentX-1][currentY+1]);
                }
                if(currentX < 23){
                    valid.add(board[currentX+2][currentY]);
                }
                if(currentX < 24 && currentY < 17){
                    valid.add(board[currentX+1][currentY+1]);
                }
            }
        }
        return endPoints;
    }


        public static void drawBoard(){
        DrawCircle d = new DrawCircle();
    }
}

class DrawCircle extends JFrame {

    public DrawCircle() {
        setSize(512, 512);
        setVisible(true);
        setCursor(new Cursor(12));
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = (e.getXOnScreen() - 120) / 10;
                int y = (e.getYOnScreen() -110) / 17;
                try{
                    if(Board.board[x][y].getOccupant() == -1){
                        throw new RuntimeException();
                    }
                    System.out.println(x + ", " + y);

                    ArrayList<Vertex> endPoints = Board.checkForPossibleMoves(x, y);

                    for(Vertex vertex : endPoints){
                        Point currentPoint = vertex.getPoint();
                        Board.board[currentPoint.getX()][currentPoint.getY()].setCurrentlyValid(true);
                    }
                    Board.drawBoard();
                }
                catch(Exception exception){
                    System.out.println("Invalid Cell " + "(" + x + ", " + y + ")");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //update(g2d);
        for(int i = 0; i < 25; i++){
            for(int j = 0; j < 17; j++){
                if(Board.board[i][j].isCurrentlyValid()){
                    g2d.setColor(Color.GRAY);
                    g2d.fillRoundRect((Board.board[i][j].getPoint().getX() * 10) + 120, (Board.board[i][j].getPoint().getY() * 17) + 110, 20, 20, 20, 20);
                    Board.board[i][j].setCurrentlyValid(false);
                }
                else if(Board.board[i][j].getOccupant() == 0){
                    g2d.setColor(Color.BLACK);
                    g2d.drawRoundRect((Board.board[i][j].getPoint().getX() * 10) + 120, (Board.board[i][j].getPoint().getY() * 17) + 110, 20, 20, 20, 20);
                }
                else if(Board.board[i][j].getOccupant() == 1){
                    g2d.setColor(Color.RED);
                    g2d.fillRoundRect((Board.board[i][j].getPoint().getX() * 10) + 120, (Board.board[i][j].getPoint().getY() * 17) + 110, 20, 20, 20, 20);
                }
                else if(Board.board[i][j].getOccupant() == 2){
                    g2d.setColor(Color.GREEN);
                    g2d.fillRoundRect((Board.board[i][j].getPoint().getX() * 10) + 120, (Board.board[i][j].getPoint().getY() * 17) + 110, 20, 20, 20, 20);
                }
            }
        }
    }
}