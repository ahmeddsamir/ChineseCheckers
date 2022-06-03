public class Main {
    public static void main(String[] args){
        //new GameController();
        //initialize board
        Point[] player1 = new Point[10];
        Point[] player2 = new Point[10];
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

        Vertex[][] board = new Vertex[25][17];
        boolean occupantSet = false;
        for(int column = 0; column < 17; column++){
            for(int row = 0; row < 25; row++){
                occupantSet = false;
                board[row][column] = new Vertex(row, column, -1);
                for(Point p : player1){
                    if(p.equals(board[row][column].getPoint())){
                        board[row][column].setOccupant(1);
                        occupantSet = true;
                        break;
                    }
                }
                if(!occupantSet){
                    for(Point p : player2){
                        if(p.equals(board[row][column].getPoint())){
                            board[row][column].setOccupant(2);
                            occupantSet = true;
                            break;
                        }
                    }
                }
                if(!occupantSet){
                    board[row][column].setOccupant(3);
                }
            }
        }

        for(int j = 0; j < 17; j++){
            for(int i = 0; i < 25; i++){
                System.out.print(board[i][j].getPoint().getX() + "," + board[i][j].getPoint().getY() + " " + board[i][j].getOccupant() + "    ");
            }
            System.out.println();
        }
    }
}
