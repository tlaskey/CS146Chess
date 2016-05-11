public class Board {
	private Piece[][] board;

	public Board(boolean team) {
		board = new Piece[8][8];
		board[0][0] = new Elephant(!team, "Elephant", true, 0, 0, false, false);
		board[0][1] = new Horse(!team, "Horse", true, 0, 1, false, false);
		board[0][2] = new Camel(!team, "Camel", true, 0, 2, false, false);
		board[0][3] = new Queen(!team, "Queen", true, 0, 3, false, false);
		board[0][4] = new King(!team, "King", true, 0, 4, false, false);
		board[0][5] = new Camel(!team, "Camel", true, 0, 5, false, false);
		board[0][6] = new Horse(!team, "Horse", true, 0, 6, false, false);
		board[0][7] = new Elephant(!team, "Elephant", true, 0, 7, false, false);
		for (int i = 0; i < 8; i++) {
			board[1][i] = new Pawn(!team, "Pawn", true, 1, i, false, false);
		}

		board[7][0] = new Elephant(team, "Elephant", true, 7, 0, true, false);
		board[7][1] = new Horse(team, "Horse", true, 7, 1, true, false);
		board[7][2] = new Camel(team, "Camel", true, 7, 2, true, false);
		board[7][3] = new Queen(team, "Queen", true, 7, 3, true, false);
		board[7][4] = new King(team, "King", true, 7, 4, true, false);
		board[7][5] = new Camel(team, "Camel", true, 7, 5, true, false);
		board[7][6] = new Horse(team, "Horse", true, 7, 6, true, false);
		board[7][7] = new Elephant(team, "Elephant", true, 7, 7, true, false);
		for (int i = 0; i < 8; i++) {
			board[6][i] = new Pawn(team, "Pawn", true, 6, i, true, false);
		}

		// For empty places on the board
		for (int i = 2; i < 6; i++) {
			for (int m = 0; m < 8; m++) {
				board[i][m] = new blank(i,
						m);/**** Change this to blank somehow ******/
			}
		}

	}

	// Getters
	public Piece getPiece(int r, int c) {
		return board[r][c];
	}

	// Show the board
	public void showBoard() {
		System.out.println(">***************************************<");
		for (int i = 0; i < 8; i++) {
			for (int m = 0; m < 8; m++) {
				if (board[i][m].getName() != null && board[i][m].getTeam())
					System.out.print("|" + board[i][m].getName().charAt(0));
				if (board[i][m].getName() != null && !board[i][m].getTeam())
					System.out.print("|" + Character.toLowerCase(board[i][m].getName().charAt(0)));
				if (board[i][m].getName() == null)
					System.out.print("|" + "_");
			}
			System.out.print("|" + "\n");
		}
		System.out.println(">***************************************<");
	}

	// Make the move
	public void makeMove(int row, int col, Board b, Piece p) {
		int oldRow = p.getRow();
		int oldCol = p.getColumn();
		// Change the position of the piece
		p.toDeleteIntersect();
		System.out.println("Making move" + row + ", " + col);
		if (p.getTeam() != b.getPiece(row, col).getTeam() && b.getPiece(row, col).getName() != null)
			System.out.println("You have taken a " + b.getPiece(row, col).getName() + " of the other team.");
		board[row][col] = p;

		p.hasMoved();
		p.setRowColumn(row, col);
		//First erase all the old moves
		p.clearMoves();
		System.out.println("Done with clearing moves");
		p.moves(b);//Up date the move set for the piece in the new position
		System.out.println("Done with updating moves");
		blank blank = new blank(oldRow, oldCol);
		board[oldRow][oldCol] = blank;


		for(int r = 0; r < 8; r++)
			for(int c = 0; c < 8; c++)
				board[r][c].moves(b);

		System.out.println("Done with move making");
	}

}
