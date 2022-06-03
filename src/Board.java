import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Board {
    private static int[][] logicMat = new int[][]{{9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 1, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}, {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 1, 9, 1, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}, {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 1, 9, 1, 9, 1, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}, {9, 9, 9, 9, 9, 9, 9, 9, 9, 1, 9, 1, 9, 1, 9, 1, 9, 9, 9, 9, 9, 9, 9, 9, 9}, {0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0}, {9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9}, {9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9}, {9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9, 9}, {9, 9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9, 9, 9}, {9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9, 9}, {9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9}, {9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9}, {0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0}, {9, 9, 9, 9, 9, 9, 9, 9, 9, 2, 9, 2, 9, 2, 9, 2, 9, 9, 9, 9, 9, 9, 9, 9, 9}, {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 2, 9, 2, 9, 2, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}, {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 2, 9, 2, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}, {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 2, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}};

    private static Point[] player1 = new Point[10];
    private static Point[] player2 = new Point[10];
    private static Vertex[][] board = new Vertex[17][25];

    public static Vertex[][] initBoard() {
        player1[0] = new Point(0, 0);
        player1[1] = new Point(11, 1);
        player1[2] = new Point(13, 1);
        player1[3] = new Point(10, 2);
        player1[4] = new Point(12, 2);
        player1[5] = new Point(14, 2);
        player1[6] = new Point(9, 3);
        player1[7] = new Point(11, 3);
        player1[8] = new Point(13, 3);
        player1[9] = new Point(15, 3);

        player2[0] = new Point(9, 13);
        player2[1] = new Point(11, 13);
        player2[2] = new Point(13, 13);
        player2[3] = new Point(15, 13);
        player2[4] = new Point(10, 14);
        player2[5] = new Point(12, 14);
        player2[6] = new Point(14, 14);
        player2[7] = new Point(11, 15);
        player2[8] = new Point(14, 15);
        player2[9] = new Point(12, 16);

        for (int row = 0; row < 17; row++) {
            for (int column = 0; column < 25; column++) {
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
        return board;
    }

    public void drawBoard(){
        DrawCircle d = new DrawCircle();
    }
}

class DrawCircle extends JFrame {

    public DrawCircle() {
        setTitle("Drawing a Circle");
        setSize(1024, 1024);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Vertex[][] board = Board.initBoard();
        for(int i = 0; i < 17; i++){
            for(int j = 0; j < 25; j++){
                if(board[i][j].getOccupant() != -1){
                    g2d.drawRoundRect((board[i][j].getPoint().getX() * 40) + 150, (board[i][j].getPoint().getY() * 20) + 150, 40, 40, 40, 40);
                }
            }
        }
    }
}