import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public final class GameEngine {

    private JFrame frame;
    private JButton easy, medium, hard;
    private boolean run = false;


    public static final int H = 17, W = 25;
    public static Vertex[][] board = new Vertex[H][W];

    private final int[][] win = {
            {0, 12},
            {1, 11},
            {1, 13},
            {2, 10},
            {2, 12},
            {2, 14},
            {3, 9},
            {3, 11},
            {3, 13},
            {3, 15}
    };

    private final Point[] cpuGoal = {
            new Point(12, 16),
			new Point(11, 15),
			new Point(13, 15),
			new Point(10, 14),
			new Point(12, 14),
			new Point(14, 14),
			new Point(9, 13),
			new Point(11, 13),
			new Point(13, 13),
			new Point(15, 13)
    };

	public Vertex getGoal(){
		int x,y;
		for (int i = 0; i < goalPoints.size(); i++) {
			x = (int)goalPoints.get(i).getX();
			y = (int)goalPoints.get(i).getY();
            if(board[y][x].getOccupant() == 1){
                goalPoints.remove(i);
                board[x][y].setAllowedToMove(false);
            }
			else if(board[y][x].getOccupant() != 1){
				return board[y][x];
			}
		}
		return null;
	}


    private int tempX = 0;
    private int tempY = 0;

    public void setActivePlayer(int activePlayer) {
        this.activePlayer = activePlayer;
    }

    private int activePlayer = 2;
    private int level;
    // public static GraphFacilities graph;
    private int[][] logicMat = {
            {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 1, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
            {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 1, 9, 1, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
            {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 1, 9, 1, 9, 1, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
            {9, 9, 9, 9, 9, 9, 9, 9, 9, 1, 9, 1, 9, 1, 9, 1, 9, 9, 9, 9, 9, 9, 9, 9, 9},
            {0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0},
            {9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9},
            {9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9},
            {9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9, 9},
            {9, 9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9, 9, 9},
            {9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9, 9},
            {9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 9},
            {9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9},
            {0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0},
            {9, 9, 9, 9, 9, 9, 9, 9, 9, 2, 9, 2, 9, 2, 9, 2, 9, 9, 9, 9, 9, 9, 9, 9, 9},
            {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 2, 9, 2, 9, 2, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
            {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 2, 9, 2, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
            {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 2, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
    };
    // private Point[][] coordMat;
    private Point[] cpuSoldiers = new Point[]{
            new Point(12, 0),
            new Point(11, 1),
            new Point(13, 1),
            new Point(10, 2),
            new Point(12, 2),
            new Point(14, 2),
            new Point(9, 3),
            new Point(11, 3),
            new Point(13, 3),
            new Point(15, 3)
    };

    public ArrayList<Point> goalPoints = new ArrayList<>();

    public GameEngine() {
        for(Point point : cpuGoal){
            goalPoints.add(point);
        }
        for (int i = 0; i < logicMat.length; i++) {
            for (int j = 0; j < logicMat[i].length; j++) {
                if (this.board[i][j] == null) {
                    if (logicMat[i][j] == 1) {    //Computer
                        this.board[i][j] = new Vertex(new Point(j, i), PlayerEnum.COMPUTER);
                    } else if (logicMat[i][j] == 2) {    //Player
                        this.board[i][j] = new Vertex(new Point(j, i), PlayerEnum.PLAYER);
                    } else if (logicMat[i][j] == 0) {    //Empty
                        this.board[i][j] = new Vertex(new Point(j, i), PlayerEnum.NONE);
                    } else { //Out of the star
                        this.board[i][j] = new Vertex(new Point(j, i), PlayerEnum.OUTSIDE);
                    }
                }
            }
        }

        tempX = tempY = level = 0;
        activePlayer = PlayerEnum.PLAYER;    //Player stars first
    }

    public void gameStart() {
        JPanel menuPanel = new JPanel();
        easy = new JButton("Easy");
        easy.setActionCommand("Easy");
        easy.addActionListener(new OptionListener());
        easy.setBounds(25, 20, 180, 95);

        medium = new JButton("Medium");
        medium.setActionCommand("Medium");
        medium.addActionListener(new OptionListener());
        medium.setBounds(25, 130, 180, 95);

        hard = new JButton("Hard");
        hard.setActionCommand("Hard");
        hard.addActionListener(new OptionListener());
        hard.setBounds(25, 240, 180, 95);

        menuPanel.setLayout(null);
        menuPanel.setBounds(0, 0, 300, 450);
        menuPanel.add(easy);
        menuPanel.add(medium);
        menuPanel.add(hard);

        frame = new JFrame("Select Level: ");
        frame.add(menuPanel);
        frame.setDefaultCloseOperation(3);
        frame.setLocationRelativeTo(null);
        frame.setSize(300, 430);
        frame.setLayout(null);
        frame.setVisible(true);
    }


    public class OptionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Easy")) {
                level = 1;
                run = true;
                frame.dispose();

            } else if (e.getActionCommand().equals("Medium")) {
                level = 3;
                run = true;
                frame.dispose();
            } else {
                level = 5;
                run = true;
                frame.dispose();
            }
        }
    }

    public ArrayList<Vertex> checkForPossibleMoves(int x, int y) {
        ArrayList<Vertex> valid = new ArrayList<>();
        ArrayList<Vertex> endPoints = new ArrayList<>();
        Vertex currentVertex;
        if (y > 0 && x > 0 && board[y - 1][x - 1].getOccupant() == 0) {
            valid.add(board[y - 1][x - 1]);
        }
        if (y > 0 && x < 24 && board[y - 1][x + 1].getOccupant() == 0) {
            valid.add(board[y - 1][x + 1]);
        }
        if (x > 1 && board[y][x - 2].getOccupant() == 0) {
            valid.add(board[y][x - 2]);
        }
        if (x > 0 && y < 16 && board[y + 1][x - 1].getOccupant() == 0) {
            valid.add(board[y + 1][x - 1]);
        }
        if (x < 23 && board[y][x + 2].getOccupant() == 0) {
            valid.add(board[y][x + 2]);
        }
        if (x < 24 && y < 16 && board[y + 1][x + 1].getOccupant() == 0) {
            valid.add(board[y + 1][x + 1]);
        }
        return jump(valid, x, y);
    }


    ArrayList<Vertex> jump(ArrayList<Vertex> availableVertices, int x, int y) {
        Vertex topLeft = null;
        Vertex topRight = null;
        Vertex left = null;
        Vertex bottomLeft = null;
        Vertex right = null;
        Vertex bottomRight = null;

        Vertex JTopRight = null;
        Vertex JTopLeft = null;
        Vertex JRight = null;
        Vertex JLeft = null;
        Vertex JBottomRight = null;
        Vertex JBottomLeft = null;

        if (y > 0 && x > 0) {
            topLeft = board[y - 1][x - 1];
        }
        if (y > 0 && x < 24) {
            topRight = board[y - 1][x + 1];
        }
        if (x > 1) {
            left = board[y][x - 2];
        }
        if (x > 0 && y < 16) {
            bottomLeft = board[y + 1][x - 1];
        }
        if (x < 23) {
            right = board[y][x + 2];
        }
        if (x < 24 && y < 16) {
            bottomRight = board[y + 1][x + 1];
        }


        if (y > 1 && x < 23) {
            JTopRight = board[y - 2][x + 2];
        }
        if (y > 1 && x > 1) {
            JTopLeft = board[y - 2][x - 2];
        }
        if (x < 21) {
            JRight = board[y][x + 4];
        }
        if (x > 3) {
            JLeft = board[y][x - 4];
        }
        if (x < 23 && y < 15) {
            JBottomRight = board[y + 2][x + 2];
        }
        if (x > 1 && y < 15) {
            JBottomLeft = board[y + 2][x - 2];
        }


        if (topRight != null && JTopRight != null && topRight.getOccupant() != -1 && JTopRight.getOccupant() != -1 && topRight.getOccupant() != 0 && JTopRight.getOccupant() == 0 && !JTopRight.isVisited()) {
            board[y - 2][x + 2].setVisited(true);
            availableVertices.add(JTopRight);
            availableVertices = jump(availableVertices, JTopRight.getPoint().x, JTopRight.getPoint().y);
        }

        if (topLeft != null && JTopLeft != null && topLeft.getOccupant() != -1 && JTopLeft.getOccupant() != -1 && topLeft.getOccupant() != 0 && JTopLeft.getOccupant() == 0 && !JTopLeft.isVisited()) {
            board[y - 2][x - 2].setVisited(true);
            availableVertices.add(JTopLeft);
            availableVertices = jump(availableVertices, JTopLeft.getPoint().x, JTopLeft.getPoint().y);
        }

        if (right != null && JRight != null && right.getOccupant() != -1 && JRight.getOccupant() != -1 && right.getOccupant() != 0 && JRight.getOccupant() == 0 && !JRight.isVisited()) {
            board[y][x + 4].setVisited(true);
            availableVertices.add(JRight);
            availableVertices = jump(availableVertices, JRight.getPoint().x, JRight.getPoint().y);
        }

        if (left != null && JLeft != null && left.getOccupant() != -1 && JLeft.getOccupant() != -1 && left.getOccupant() != 0 && JLeft.getOccupant() == 0 && !JLeft.isVisited()) {
            board[y][x - 4].setVisited(true);
            availableVertices.add(JLeft);
            availableVertices = jump(availableVertices, JLeft.getPoint().x, JLeft.getPoint().y);
        }

        if (bottomRight != null && JBottomRight != null && bottomRight.getOccupant() != -1 && JBottomRight.getOccupant() != -1 && bottomRight.getOccupant() != 0 && JBottomRight.getOccupant() == 0 && !JBottomRight.isVisited()) {
            board[y + 2][x + 2].setVisited(true);
            availableVertices.add(JBottomRight);
            availableVertices = jump(availableVertices, JBottomRight.getPoint().x, JBottomRight.getPoint().y);
        }

        if (bottomLeft != null && JBottomLeft != null && bottomLeft.getOccupant() != -1 && JBottomLeft.getOccupant() != -1 && bottomLeft.getOccupant() != 0 && JBottomLeft.getOccupant() == 0 && !JBottomLeft.isVisited()) {
            board[y + 2][x - 2].setVisited(true);
            availableVertices.add(JBottomLeft);
            availableVertices = jump(availableVertices, JBottomLeft.getPoint().x, JBottomLeft.getPoint().y);
        }

        return availableVertices;
    }

    public void reset() {
        for (int i = 0; i < this.H; i++) {
            for (int j = 0; j < this.W; j++) {
                if (board[i][j] != null && board[i][j].isVisited()) {
                    board[i][j].setVisited(false);
                }
            }
        }
    }

    void move(int destX, int destY) {
        board[tempY][tempX].setOccupant(PlayerEnum.NONE);
        board[destY][destX].setOccupant(activePlayer);
    }

    public Vertex[][] fakeMove(int srcX, int srcY, int destX, int destY, Vertex[][] board) {
        Vertex[][] tmpBoard = new Vertex[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                tmpBoard[i][j] = new Vertex(board[i][j].getPoint(), board[i][j].getOccupant());
            }
        }


        tmpBoard[srcY][srcX].setOccupant(PlayerEnum.NONE);
        tmpBoard[destY][destX].setOccupant(1);
        return tmpBoard;
    }

    public void makeBestMove() {
        ArrayList<Point> result = minimax(board, level, true);
        setTempX((int) result.get(0).getX());
        setTempY((int) result.get(0).getY());
        move((int) result.get(1).getX(), (int) result.get(1).getY());
    }


    public ArrayList<Point> minimax(Vertex[][] board, int depth, boolean isMaximizing) {

        Vertex[][] tmpBoard = new Vertex[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                tmpBoard[i][j] = new Vertex(board[i][j].getPoint(), board[i][j].getOccupant());
            }
        }

        double bestScore = 0;
        ArrayList<Vertex> aiMarbles = new ArrayList<>();
        ArrayList<Vertex> possibleMoves = new ArrayList<>();
        ArrayList<Vertex> allPossibleMoves = new ArrayList<>();
        Vertex[][] currentState = null;
        ArrayList<Double> scores = new ArrayList<>();
        //Point source = new Point(0,0);
        //Point destination = new Point(0,0);
        ArrayList<Point> destination = new ArrayList<>();
        ArrayList<Point> source = new ArrayList<>();
        ArrayList<Point> srcDest = new ArrayList<>();
        Point bestSource = new Point(0, 0);
        Point bestDestination = new Point(0, 0);


        for (int i = 0; i < this.H; i++) {
            for (int j = 0; j < this.W; j++) {
                if (tmpBoard[i][j] != null && tmpBoard[i][j].getOccupant() == 1) {
                    aiMarbles.add(tmpBoard[i][j]);
                }
            }
        }
        for (Vertex vertex : aiMarbles) {
            possibleMoves = checkForPossibleMoves((int) vertex.getPoint().getX(), (int) vertex.getPoint().getY());
            for (Vertex move : possibleMoves) {
                currentState = fakeMove((int) vertex.getPoint().getX(), (int) vertex.getPoint().getY(), (int) move.getPoint().getX(), (int) move.getPoint().getY(), tmpBoard);
                scores.add(getHeuristic(currentState));
                source.add(vertex.getPoint());
                destination.add(move.getPoint());
            }
            //allPossibleMoves.addAll(possibleMoves);
            ////////////////////////////////
        }
        if (isMaximizing) {
            bestScore = Double.MAX_VALUE;
            for (int i = 0; i < scores.size(); i++) {
                //bestScore = Math.max(bestScore, scores.get(i));
                if (scores.get(i) < bestScore) {
                    bestScore = scores.get(i);
                    bestSource = source.get(i);
                    bestDestination = destination.get(i);


                }
            }
            if (depth > 0) {
                minimax(fakeMove((int) bestSource.getX(), (int) bestSource.getY(), (int) bestDestination.getX(), (int) bestDestination.getY(), tmpBoard), depth - 1, false);

            }

            //return
        } else {
            bestScore = Double.MIN_VALUE;
            for (int i = 0; i < scores.size(); i++) {
                //bestScore = Math.max(bestScore, scores.get(i));
                if (scores.get(i) > bestScore) {
                    bestScore = scores.get(i);
                    bestSource = source.get(i);
                    bestDestination = destination.get(i);


                }
            }
            if (depth > 0) {
                minimax(fakeMove((int) bestSource.getX(), (int) bestSource.getY(), (int) bestDestination.getX(), (int) bestDestination.getY(), tmpBoard), depth - 1, true);

            }
            //return
        }

        srcDest.add(bestSource);
        srcDest.add(bestDestination);

        return srcDest;
    }

    //int prevX = 0;
    //int prevY = 0;

    Vertex goal = null;
    public double getHeuristic(Vertex[][] tmpBoard) {

        try{
            goal = new Vertex(getGoal().getPoint());
        }
        catch(Exception e){
            for(Point point : cpuGoal){
                if(board[point.y][point.x].getOccupant() != 1 && !goalPoints.contains(board[point.y][point.x].getPoint())){
                    goalPoints.add(point);
                }
            }
            goal = new Vertex(getGoal().getPoint());
        }

        //System.out.println(goal.getPoint());

        ArrayList<Vertex> vertices = new ArrayList<Vertex>();

        for (int i = 0; i < this.H; i++) {
            for (int j = 0; j < this.W; j++) {
                if (tmpBoard[i][j] != null && tmpBoard[i][j].getOccupant() == 1) {
                    vertices.add(tmpBoard[i][j]);
                }
            }
        }

        int sum = 0;
        for (Vertex vertex : vertices) {
            sum += Math.sqrt(Math.pow(goal.getPoint().getX() - vertex.getPoint().getX(), 2) + Math.pow(goal.getPoint().getY() - vertex.getPoint().getY(), 2));
        }

        //prevX = (int)goal.getPoint().getX();
        //prevY = (int)goal.getPoint().getY();
        return sum;
    }




	/*boolean hasWon() {



		
	}*/


    // Setters and Getters
    public boolean isRunning() {
        return run;
    }

    public int getPlayer() {
        return activePlayer;
    }

    public int getTempY() {
        return tempY;
    }

    public void setTempY(int y) {
        tempY = y;
    }

    public int getTempX() {
        return tempX;
    }

    public void setTempX(int x) {
        tempX = x;
    }
}
