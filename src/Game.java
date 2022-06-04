import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public final class Game {

	private JFrame frame;
	private JButton easy, medium, hard;
	private boolean run = false;


	public static final int H = 17, W = 25;
	public static Vertex[][] board = new Vertex[H][W];

	private final int[][] win = {
			{ 0, 12 },
			{ 1, 11 },
			{ 1, 13 },
			{ 2, 10 },
			{ 2, 12 },
			{ 2, 14 },
			{ 3, 9 },
			{ 3, 11 },
			{ 3, 13 },
			{ 3, 15 }
	};
	private int tempX = 0, tempY = 0, activePlayer = 2, level;
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
	private Point[] cpuSoldiers = new Point[] {
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
	// private int coordIndex;

	public Game() {
		for (int i = 0; i < logicMat.length; i++) {
			for (int j = 0; j < logicMat[i].length; j++) {
				if (this.board[i][j] == null) {
					if (logicMat[i][j] == 1) {	//Computer
						this.board[i][j] = new Vertex(new Point(j, i), PlayerEnum.COMPUTER);
					} else if (logicMat[i][j] == 2) {	//Player
						this.board[i][j] = new Vertex(new Point(j, i), PlayerEnum.PLAYER);
					} else if (logicMat[i][j] == 0) {	//Empty
						this.board[i][j] = new Vertex(new Point(j, i), PlayerEnum.NONE);
					}else { //Out of the star
						this.board[i][j] = new Vertex(new Point(j, i), PlayerEnum.OUTSIDE);
					}
				}
			}
		}

		tempX = tempY = level = 0;
		activePlayer = PlayerEnum.PLAYER;	//Player stars first
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
				level = 2;
				run = true;
				frame.dispose();
			}

			else {
				level = 3;
				run = true;
				frame.dispose();
			}
		}
	}

	// Getting all possible next moves
	/*public ArrayList<Vertex> availableMoves(int x, int y) {

		ArrayList<Vertex> availableVertices = new ArrayList<Vertex>();

		Vertex topRight = vertexMat[y-1][x+1];
		Vertex topLeft = vertexMat[y-1][x-1];
		Vertex right = vertexMat[y][x+2];
		Vertex left = vertexMat[y][x-2];
		Vertex bottomRight = vertexMat[y+1][x+1];
		Vertex bottomLeft = vertexMat[y+1][x-1];

		if (topRight != null && topRight.content == 0) {
			System.out.println("topRight: "+  topRight.getLocation());
			availableVertices.add(topRight);
		}
	
		if (topLeft != null && topLeft.content == 0) {
			System.out.println("topLeft: "+  topLeft.getLocation());
			availableVertices.add(topLeft);
		}

		if (right != null && right.content == 0) {
			System.out.println("right: "+  right.getLocation());
			availableVertices.add(right);
		}

		if (left != null && left.content == 0) {
			System.out.println("left: "+  left.getLocation());
			availableVertices.add(left);
		}

		if (bottomRight != null && bottomRight.content == 0) {
			System.out.println("bottomRight: "+  bottomRight.getLocation());
			availableVertices.add(bottomRight);
		}

		if (bottomLeft != null && bottomLeft.content == 0) {
			System.out.println("bottomLeft: "+  bottomLeft.getLocation());
			availableVertices.add(bottomLeft);
		}

		return jump(availableVertices, x, y);
	}*/


	public ArrayList<Vertex> checkForPossibleMoves(int x, int y){
		ArrayList<Vertex> valid = new ArrayList<>();
        ArrayList<Vertex> endPoints = new ArrayList<>();
        Vertex currentVertex;
        if(y > 0 && x > 0 && board[y-1][x-1].getOccupant() == 0){
            valid.add(board[y-1][x-1]);
        }
        if(y > 0 && x < 23 && board[y-1][x+1].getOccupant() == 0){
            valid.add(board[y-1][x+1]);
        }
        if(x > 1 && board[y][x-2].getOccupant() == 0){
            valid.add(board[y][x-2]);
        }
        if(x > 0 && y < 16 && board[y+1][x-1].getOccupant() == 0){
            valid.add(board[y+1][x-1]);
        }
        if(x < 22 && board[y][x+2].getOccupant() == 0){
            valid.add(board[y][x+2]);
        }
        if(x < 23 && y < 16 && board[y+1][x+1].getOccupant() == 0){
            valid.add(board[y+1][x+1]);
        }
        //set of vertices
        /*while(!valid.isEmpty()){
            currentVertex = valid.poll();
            if(currentVertex.getOccupant() == 0){
                endPoints.add(currentVertex);
            }
            else if (currentVertex.getOccupant() != -1){
                int currentX = (int)currentVertex.getPoint().getX();
                int currentY = (int)currentVertex.getPoint().getY();
                if(currentY > 0 && currentX > 0){
                    valid.add(board[currentX-1][currentY-1]);
                }
                if(currentY > 0 && currentX < 23){
                    valid.add(board[currentY-1][currentX+1]);
                }
                if(currentX > 1){
                    valid.add(board[currentY][currentX-2]);
                }
                if(currentX > 0 && currentY < 16){
                    valid.add(board[currentY+1][currentX-1]);
                }
                if(currentX < 22){
                    valid.add(board[currentY][currentX+2]);
                }
                if(currentX < 23 && currentY < 16){
                    valid.add(board[currentY+1][currentX+1]);
                }
            }
        }*/
        return jump(valid, x, y);
    }



	ArrayList<Vertex> jump(ArrayList<Vertex> availableVertices, int x, int y) {
		Vertex JTopRight = board[y-2][x+2];
		Vertex JTopLeft = board[y-2][x-2];
		Vertex JRight = board[y][x+4];
		Vertex JLeft = board[y][x-4];
		Vertex JBottomRight = board[y+2][x+2];
		Vertex JBottomLeft = board[y+2][x-2];

		Vertex topRight = board[y-1][x+1];
		Vertex topLeft = board[y-1][x-1];
		Vertex right = board[y][x+2];
		Vertex left = board[y][x-2];
		Vertex bottomRight = board[y+1][x+1];
		Vertex bottomLeft = board[y+1][x-1];

		//Law hanbd2 mn topRight, topLeft, Right haydrb: 3lshan homa ely fe el awel m3rfsh leh
		if (topRight != null && JTopRight != null && topRight.getOccupant() != 0 && JTopRight.getOccupant() == 0 && !JTopRight.isVisited()) {
			board[y-2][x+2].setVisited(true);
			System.out.println("topRight: "+  topRight.getPoint());
			availableVertices.add(JTopRight);
			availableVertices = jump(availableVertices, JTopRight.getPoint().x, JTopRight.getPoint().y);
		}

		if (topLeft.getOccupant() != -1 && JTopLeft != null && topLeft.getOccupant() != 0 && JTopLeft.getOccupant() == 0 && !JTopLeft.isVisited()) {
			board[y-2][x-2].setVisited(true);
			System.out.println("topLeft: "+  topLeft.getPoint());
			availableVertices.add(JTopLeft);
			availableVertices = jump(availableVertices, JTopLeft.getPoint().x, JTopLeft.getPoint().y);
		}

		if (right.getOccupant() != -1 && JRight.getOccupant() != -1 && right.getOccupant() != 0 && JRight.getOccupant() == 0 && !JRight.isVisited()) {
			board[y][x+4].setVisited(true);
			System.out.println("right: "+  right.getPoint());
			availableVertices.add(JRight);
			availableVertices = jump(availableVertices, JRight.getPoint().x, JRight.getPoint().y);
		}

		if (left.getOccupant() != -1 && JLeft.getOccupant() != -1 && left.getOccupant() != 0 && JLeft.getOccupant() == 0 && !JLeft.isVisited()) {
			board[y][x-4].setVisited(true);
			System.out.println("left: "+  left.getPoint());
			availableVertices.add(JLeft);
			availableVertices = jump(availableVertices, JLeft.getPoint().x, JLeft.getPoint().y);
		}

		if (bottomRight.getOccupant() != -1 && JBottomRight.getOccupant() != -1 && bottomRight.getOccupant() != 0 && JBottomRight.getOccupant() == 0 && !JBottomRight.isVisited()) {
			board[y+2][x+2].setVisited(true);
			System.out.println("bottomRight: "+  bottomRight.getPoint());
			availableVertices.add(JBottomRight);
			availableVertices = jump(availableVertices, JBottomRight.getPoint().x, JBottomRight.getPoint().y);
		}

		if (bottomLeft.getOccupant() != -1 && JBottomLeft.getOccupant() != -1 && bottomLeft.getOccupant() != 0 && JBottomLeft.getOccupant() == 0 && !JBottomLeft.isVisited()) {
			board[y+2][x-2].setVisited(true);
			System.out.println("bottomLeft: "+  bottomLeft.getPoint());
			availableVertices.add(JBottomLeft);
			availableVertices = jump(availableVertices, JBottomLeft.getPoint().x, JBottomLeft.getPoint().y);
		}

		reset();
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
	

	/*
	 * 
	 * 
	 * public boolean win() {
	 * boolean p1 = true, p2 = true;
	 * for (int i = 0; i < 10 && (p1 || p2); i++) {
	 * p1 = (GraphFacilities.vertexMat[win[i][0]][win[i][1]].content
	 * == PlayerEnum.HUMAN_PLAYER && p1);
	 * p2 = (GraphFacilities.vertexMat[(H - 1) - win[i][0]][win[i][1]].content
	 * == PlayerEnum.CPU_PLAYER && p2);
	 * }
	 * if (p1) {
	 * if (status == 2) {
	 * JOptionPane.showConfirmDialog(null,
	 * "Blue Player Won!",
	 * "Winner",
	 * JOptionPane.CLOSED_OPTION,
	 * JOptionPane.INFORMATION_MESSAGE);
	 * } else {
	 * JOptionPane.showConfirmDialog(null,
	 * "You Won!",
	 * "Winner",
	 * JOptionPane.CLOSED_OPTION,
	 * JOptionPane.INFORMATION_MESSAGE);
	 * }
	 * player = PlayerEnum.NONE;
	 * return true;
	 * } else if (p2) {
	 * if (status == 2) {
	 * JOptionPane.showConfirmDialog(null,
	 * "Red Player Won!",
	 * "Winner",
	 * JOptionPane.CLOSED_OPTION,
	 * JOptionPane.INFORMATION_MESSAGE);
	 * } else {
	 * JOptionPane.showConfirmDialog(null,
	 * "The Computer Won!",
	 * "Winner",
	 * JOptionPane.CLOSED_OPTION,
	 * JOptionPane.INFORMATION_MESSAGE);
	 * }
	 * player = PlayerEnum.NONE;
	 * return true;
	 * }
	 * return false;
	 * }
	 * 
	 * public void Move(int y, int x) {
	 * System.out.println("Player "+this.getPlayer()+": ("+this.getTx()+", "+this.
	 * getTy()+") To ("+x+", "+y+")");
	 * GraphFacilities.vertexMat[y][x].content = player;
	 * GraphFacilities.vertexMat[ty][tx].content = 99;
	 * }
	 * 
	 * public void resetBoard() {
	 * for (int i = 0; i < H; i++) {
	 * for (int j = 0; j < W; j++) {
	 * if (GraphFacilities.vertexMat[i][j] != null
	 * && GraphFacilities.vertexMat[i][j].content > 9) {
	 * GraphFacilities.vertexMat[i][j].content = PlayerEnum.NONE;
	 * }
	 * }
	 * }
	 * GraphFacilities.updateGraph(coordMat);
	 * }
	 * 
	 * public void endTurn() {
	 * resetBoard();
	 * if (!win()) {
	 * player = (player == PlayerEnum.CPU_PLAYER)
	 * ? PlayerEnum.HUMAN_PLAYER
	 * : PlayerEnum.CPU_PLAYER;
	 * ty = 0;
	 * tx = 0;
	 * }
	 * }
	 * 
	 * 
	 * 
	 * // only search for jumps
	 * public Set<Point> optinalPlaysLen3(int x, int y) {
	 * Set<Point> endPoints = new HashSet<>();
	 * Set<CCEdge> edges
	 * = GraphFacilities.g.outgoingEdgesOf(GraphFacilities.vertexMat[y][x]);
	 * Iterator<CCEdge> it = edges.iterator();
	 * while (it.hasNext()) {
	 * CCEdge edge = it.next();
	 * // Source isn't the origin.
	 * if (!edge.getSrcVertx().getLocation().equals(new Point(x, y))) {
	 * // Cell empty
	 * if (edge.getSrcVertx().content == PlayerEnum.NONE) {
	 * if (edge.getSrcVertx().getLocation().y - 2
	 * == edge.getDestVertx().getLocation().y
	 * || edge.getSrcVertx().getLocation().y + 2
	 * == edge.getDestVertx().getLocation().y
	 * || edge.getSrcVertx().getLocation().x - 4
	 * == edge.getDestVertx().getLocation().x
	 * || edge.getSrcVertx().getLocation().x + 4
	 * == edge.getDestVertx().getLocation().x) {
	 * if (player == PlayerEnum.HUMAN_PLAYER) {
	 * edge.getSrcVertx().content
	 * = PlayerEnum.POSSIBLE_HUMAN_TARGET;
	 * } else {
	 * edge.getSrcVertx().content
	 * = PlayerEnum.POSSIBLE_CPU_TARGET;
	 * }
	 * endPoints.add(edge.getSrcVertx().getLocation());
	 * endPoints.addAll(
	 * optinalPlaysLen3(edge.getSrcVertx().getLocation().x,
	 * edge.getSrcVertx().getLocation().y));
	 * }
	 * }
	 * } else {
	 * // Cell empty
	 * if (edge.getDestVertx().content == PlayerEnum.NONE) {
	 * if (edge.getSrcVertx().getLocation().y - 2
	 * == edge.getDestVertx().getLocation().y
	 * || edge.getSrcVertx().getLocation().y + 2
	 * == edge.getDestVertx().getLocation().y
	 * || edge.getSrcVertx().getLocation().x - 4
	 * == edge.getDestVertx().getLocation().x
	 * || edge.getSrcVertx().getLocation().x + 4
	 * == edge.getDestVertx().getLocation().x) {
	 * if (player == PlayerEnum.HUMAN_PLAYER) {
	 * edge.getDestVertx().content
	 * = PlayerEnum.POSSIBLE_HUMAN_TARGET;
	 * } else {
	 * edge.getDestVertx().content
	 * = PlayerEnum.POSSIBLE_CPU_TARGET;
	 * }
	 * endPoints.add(edge.getDestVertx().getLocation());
	 * endPoints.addAll(
	 * optinalPlaysLen3(edge.getDestVertx().getLocation().x,
	 * edge.getDestVertx().getLocation().y));
	 * }
	 * }
	 * }
	 * }
	 * return endPoints;
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * public int getStatus() {
	 * return status;
	 * }
	 * 
	 * public void setStatus(int value) {
	 * if (status == 0) {
	 * if (value == 1 || value == 2) {
	 * status = value;
	 * }
	 * }
	 * }
	 * 
	 * public void AI() {
	 * int i;
	 * // If only one player is outside
	 * if (countOutsideSoldiers() == 1) {
	 * CellVertex dest = null;
	 * for (i = 0; i < cpuSoldiers.length; i++) {
	 * if (cpuSoldiers[i].y < 13) {
	 * for (int h = 13; h < GraphFacilities.H; h++) {
	 * for (int j = 9 + (h - 13); j < 16 - (h - 13); j += 2) {
	 * if (GraphFacilities.vertexMat[h][j].content
	 * == PlayerEnum.NONE) {
	 * dest = GraphFacilities.vertexMat[h][j];
	 * }
	 * }
	 * }
	 * List<CellVertex> path = GraphFacilities.findShortestPathLength(
	 * GraphFacilities.vertexMat[cpuSoldiers[i].y][cpuSoldiers[i].x],
	 * dest).getVertexList();
	 * setTx(path.get(0).getLocation().x);
	 * setTy(path.get(0).getLocation().y);
	 * Move(path.get(1).getLocation().y, path.get(1).getLocation().x);
	 * cpuSoldiers[i].setLocation(path.get(1).getLocation());
	 * return;
	 * }
	 * }
	 * }
	 * // If there's an S turn available
	 * if ((i = findCpuPlayerIndex(is_S_TurnExist())) > -1) {
	 * setTx(cpuSoldiers[i].x);
	 * setTy(cpuSoldiers[i].y);
	 * Move(cpuSoldiers[i].y + 4, cpuSoldiers[i].x);
	 * cpuSoldiers[i].y += 4;
	 * } else {
	 * if (cpuSoldiers[6].getLocation().equals(new Point(9, 3))
	 * && GraphFacilities.vertexMat[4][10].content
	 * != PlayerEnum.HUMAN_PLAYER
	 * && GraphFacilities.vertexMat[4][10].content
	 * != PlayerEnum.CPU_PLAYER) {
	 * setTx(9);
	 * setTy(3);
	 * Move(4, 10);
	 * cpuSoldiers[6].setLocation(10, 4);
	 * } else if (cpuSoldiers[9].getLocation().equals(new Point(15, 3))
	 * && GraphFacilities.vertexMat[4][14].content
	 * != PlayerEnum.HUMAN_PLAYER
	 * && GraphFacilities.vertexMat[4][14].content
	 * != PlayerEnum.CPU_PLAYER) {
	 * setTx(15);
	 * setTy(3);
	 * Move(4, 14);
	 * cpuSoldiers[9].setLocation(14, 4);
	 * } else {
	 * CCEdge jump = findFarthestJump();
	 * if (jump != null) {
	 * setTx(jump.getSrcVertx().getLocation().x);
	 * setTy(jump.getSrcVertx().getLocation().y);
	 * Move(jump.getDestVertx().getLocation().y,
	 * jump.getDestVertx().getLocation().x);
	 * cpuSoldiers[findCpuPlayerIndex(
	 * jump.getSrcVertx().getLocation())].setLocation(
	 * jump.getDestVertx().getLocation());
	 * } else {
	 * for (i = 0; i < GraphFacilities.H; i++) {
	 * for (int j = 0; j < GraphFacilities.W; j++) {
	 * if (GraphFacilities.vertexMat[i][j] != null
	 * && GraphFacilities.vertexMat[i][j].content
	 * == PlayerEnum.CPU_PLAYER) {
	 * setTx(j);
	 * setTy(i);
	 * resetBoard();
	 * Set<Point> points = optinalPlays(tx, ty);
	 * Iterator<Point> it = points.iterator();
	 * while (it.hasNext()) {
	 * Point p = it.next();
	 * if ((p.y > ty
	 * && ((Math.abs(tx - GraphFacilities.W / 2)
	 * > Math.abs(p.x - GraphFacilities.W / 2))
	 * || (Math.abs(tx - GraphFacilities.W / 2)
	 * == 0)))) {
	 * Move(p.y, p.x);
	 * cpuSoldiers[findCpuPlayerIndex(
	 * new Point(tx, ty))].setLocation(p);
	 * return;
	 * }
	 * }
	 * it = points.iterator();
	 * while (it.hasNext()) {
	 * Point p = it.next();
	 * if ((p.y == ty
	 * && Math.abs(tx - GraphFacilities.W / 2)
	 * > Math.abs(p.x - GraphFacilities.W / 2))) {
	 * Move(p.y, p.x);
	 * cpuSoldiers[findCpuPlayerIndex(
	 * new Point(tx, ty))].setLocation(p);
	 * return;
	 * }
	 * }
	 * it = points.iterator();
	 * while (it.hasNext()) {
	 * Point p = it.next();
	 * if (p.y > ty) {
	 * Move(p.y, p.x);
	 * cpuSoldiers[findCpuPlayerIndex(
	 * new Point(tx, ty))].setLocation(p);
	 * return;
	 * }
	 * }
	 * }
	 * }
	 * }
	 * }
	 * }
	 * }
	 * }
	 * 
	 * private int countOutsideSoldiers() {
	 * int counter = 10;
	 * for (int i = 13; i < GraphFacilities.H; i++) {
	 * for (int j = 9 + (i - 13); j < 16 - (i - 13); j += 2) {
	 * if (GraphFacilities.vertexMat[i][j].content
	 * == PlayerEnum.CPU_PLAYER) {
	 * counter--;
	 * }
	 * }
	 * }
	 * return counter;
	 * }
	 * 
	 * private CCEdge findFarthestJump() {
	 * CCEdge jump = null;
	 * for (int i = 0; i < GraphFacilities.vertexMat.length; i++) {
	 * for (int j = 0; j < GraphFacilities.vertexMat[i].length; j++) {
	 * if (GraphFacilities.vertexMat[i][j] != null
	 * && GraphFacilities.vertexMat[i][j].content
	 * == PlayerEnum.CPU_PLAYER) {
	 * resetBoard();
	 * optinalPlaysLen3(j, i);
	 * for (int h = i; h < GraphFacilities.vertexMat.length; h++) {
	 * for (int g = 0; g < GraphFacilities.vertexMat[h].length; g++) {
	 * if (GraphFacilities.vertexMat[h][g] != null
	 * && GraphFacilities.vertexMat[h][g].content
	 * == PlayerEnum.POSSIBLE_CPU_TARGET) {
	 * if (jump != null) {
	 * if ((jump.getDestVertx().getLocation().y < h)
	 * || (jump.getDestVertx().getLocation().y == h
	 * && Math.abs(
	 * jump.getDestVertx().getLocation().x
	 * - (GraphFacilities.W / 2))
	 * > Math.abs(g - (GraphFacilities.W / 2)))) {
	 * if ((i < h)
	 * || (i == h
	 * && Math.abs(j - (GraphFacilities.W / 2))
	 * > Math.abs(
	 * g - (GraphFacilities.W / 2)))) {
	 * jump.setSrcVertx(
	 * GraphFacilities.vertexMat[i][j]);
	 * jump.setDestVertx(
	 * GraphFacilities.vertexMat[h][g]);
	 * }
	 * }
	 * } else {
	 * if ((i < h)
	 * || (i == h
	 * && Math.abs(j - (GraphFacilities.W / 2))
	 * > Math.abs(g - (GraphFacilities.W / 2)))) {
	 * jump = new CCEdge(GraphFacilities.vertexMat[i][j],
	 * GraphFacilities.vertexMat[h][g]);
	 * }
	 * }
	 * }
	 * }
	 * }
	 * }
	 * }
	 * }
	 * return jump;
	 * }
	 * 
	 * private Point is_S_TurnExist() {
	 * for (int i = GraphFacilities.vertexMat.length - 1; i >= 0; i--) {
	 * for (int j = GraphFacilities.vertexMat[i].length - 1; j >= 0; j--) {
	 * if (GraphFacilities.vertexMat[i][j] != null
	 * && GraphFacilities.vertexMat[i][j].content
	 * == PlayerEnum.CPU_PLAYER) {
	 * resetBoard();
	 * optinalPlays(j, i);
	 * if (i + 4 < GraphFacilities.vertexMat.length
	 * && GraphFacilities.vertexMat[i + 4][j] != null
	 * && j - 1 > 0 && j + 1 < GraphFacilities.vertexMat[i].length
	 * && GraphFacilities.vertexMat[i + 2][j].content
	 * == PlayerEnum.CPU_PLAYER
	 * && GraphFacilities.vertexMat[i + 4][j].content
	 * == PlayerEnum.POSSIBLE_CPU_TARGET
	 * && ((GraphFacilities.vertexMat[i + 1][j + 1].content
	 * == PlayerEnum.CPU_PLAYER
	 * && GraphFacilities.vertexMat[i + 3][j + 1].content
	 * == PlayerEnum.CPU_PLAYER
	 * && GraphFacilities.vertexMat[i + 2][j + 2].content
	 * == PlayerEnum.POSSIBLE_CPU_TARGET)
	 * || (GraphFacilities.vertexMat[i + 1][j - 1].content
	 * == PlayerEnum.CPU_PLAYER
	 * && GraphFacilities.vertexMat[i + 3][j - 1].content
	 * == PlayerEnum.CPU_PLAYER
	 * && GraphFacilities.vertexMat[i + 2][j - 2].content
	 * == PlayerEnum.POSSIBLE_CPU_TARGET))) {
	 * return new Point(j, i);
	 * }
	 * }
	 * }
	 * }
	 * return null;
	 * }
	 * 
	 * private int findCpuPlayerIndex(Point p) {
	 * if (p != null) {
	 * for (int i = 0; i < cpuSoldiers.length; i++) {
	 * if (cpuSoldiers[i].equals(p)) {
	 * return i;
	 * }
	 * }
	 * }
	 * return -1;
	 * }
	 */
}
