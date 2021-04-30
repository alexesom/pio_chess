package ChessInterface;

import Pieces.*;

import java.awt.Color;

import javax.swing.*;
import java.util.List;

public class Chessboard {
    public JPanel chessboardPanel = new JPanel();
    public JLayeredPane layer = new JLayeredPane();
    public static Square[][] board = new Square[8][8];
    public JLayeredPane capturedPiecesPanel1 = new JLayeredPane();
    public JLayeredPane capturedPiecesPanel2 = new JLayeredPane();
    public static List<Piece> pieceList = null;
    private Adapter mouseAdapter = new Adapter(layer, capturedPiecesPanel1, capturedPiecesPanel2);

    public Chessboard() {
        createChessboardSquares();
        createCapturedPiecesPanel();
        placeChessboardPieces();
        addMouse();
    }

    public Chessboard(List<Piece> list) {
        pieceList = list;
        createChessboardSquares();
    }

    public void createChessboardPanel(int xGap, int yGap, int width, int height) {
        chessboardPanel.setLayout(null);
        chessboardPanel.setBounds(xGap, yGap, width, height);
        layer.setBounds(xGap, yGap, width, height);
    }

    public void createCapturedPiecesPanel() {
        capturedPiecesPanel1.setBounds(675, 95, 560, 200);
        capturedPiecesPanel1.setOpaque(false);

        capturedPiecesPanel2.setBounds(675, 375, 560, 300);
        capturedPiecesPanel2.setOpaque(false);
    }

    private void createChessboardSquares() {
        for (int iy = 7; iy >= 0; iy--) {
            for (int jx = 0; jx < 8; jx++) {
                board[jx][iy] = new Square(jx, iy, 560, 560);
                board[jx][iy].setSquareColor(jx, iy);
                Color boardSquareColor = board[jx][iy].getSquareColor();
                board[jx][iy].squarePanel.setBackground(boardSquareColor);
                chessboardPanel.add(board[jx][iy].squarePanel);
                System.out.println(board[jx][iy].squarePanel.getLocation() + " " + iy + " " + jx);
            }
        }
    }

    private void addFigure(Piece figure) {
        int x = figure.getxPieceCoordinate();
        int y = figure.getyPieceCoordinate();
        layer.add(figure.panel);
        board[x][y].setSquarePiece(figure);
    }

    private void addMouse() {
        layer.addMouseListener(mouseAdapter);
    }

    public static boolean tryCastling(Square originSquare, Square destinationSquare) {
        Piece movingPiece;
        Piece destinationPiece;

        if(destinationSquare.getXSquareCoordinate() == 4) {
            movingPiece = destinationSquare.getSquarePiece();
            destinationPiece = originSquare.getSquarePiece();
        } else {
            movingPiece = originSquare.getSquarePiece();
            destinationPiece = destinationSquare.getSquarePiece();

        }
        if((movingPiece.isAbleToCastle() && destinationPiece.isAbleToCastle()))
        {
            Square kingSquare = new Square(4, originSquare.getYSquareCoordinate());

            if(destinationPiece.isAbleToMove(kingSquare)) { //
               return true;
            }
        }
        return false;
    }
    /*
    Checks whether there's a Piece on originSquare and moves it to destinationSquare if the move is legal
    returns 0 when the move is successful
           -1 when there was no piece on originSquare (this should never happen)
            1 when the player tried to move their opponent's piece
            2 when the move is illegal
       TO DO: change to void and throw exceptions
     */
    public static byte tryMove(Square originSquare, Square destinationSquare) {
        Piece movingPiece = originSquare.getSquarePiece();
        if (movingPiece == null){
            System.err.println("tryMove fail: piece is null(!)");
            //System.out.println("Piece is null!!");
            return -1;
        }
        /*
        System.out.println("originSquare: " +originSquare.getXSquareCoordinate() + "," + originSquare.getYSquareCoordinate());
        System.out.println("destination: " +destinationSquare.getXSquareCoordinate() + "," + destinationSquare.getYSquareCoordinate());
        System.out.println("movingPiece: " + movingPiece);
        */
            //check if the piece is the current player's piece
        if (movingPiece.getPieceColor() != Game.current_turn){
            //System.err.println("tryMove fail: not this piece's turn");
            System.out.println("This isn't your piece! Current turn: " + Game.current_turn);
            return 1;
        }
            //check if the move is legal
        if (!movingPiece.isAbleToMove(destinationSquare)){
            //System.err.println("tryMove fail: illegal move");
            System.out.println("Illegal move!");
            return 2;
        }
            //check if trying to take own piece
            Piece pieceAtDestination = destinationSquare.getSquarePiece();
        if ((pieceAtDestination != null) && (pieceAtDestination.getPieceColor() == Game.current_turn)) {
            System.out.println("You can't take your own piece!");
            return 3;
        }

            int newX = destinationSquare.getXSquareCoordinate();
            int newY = destinationSquare.getYSquareCoordinate();
            movingPiece.setxPieceCoordinate(newX);
            movingPiece.setyPieceCoordinate(newY);
            destinationSquare.setSquarePiece(movingPiece);
            originSquare.setSquarePiece(null);
            Game.nextTurn();
            //pass the turn to the next player
            return 0;
    }

    /*
    Place the pieces in their starting positions
     */
    private void placeChessboardPieces() {
        // Black pieces
        placePieces(7, Color.BLACK);
        placePawns(8,6, Color.BLACK);

        // White pieces
        placePieces(0, Color.WHITE);
        placePawns(8,1, Color.WHITE);
    }

    /*
    places the standard piece set of a given color in the specified row
    (Rook, Knight, Bishop, Queen, King, Bishop, Knight and Rook in this order)
     */
    private void placePieces(int row, Color color) {
        addFigure(new Rook(board[0][row], color));
        addFigure(new Knight(board[1][row], color));
        addFigure(new Bishop(board[2][row], color));
        addFigure(new Queen(board[3][row], color));
        addFigure(new King(board[4][row], color));
        addFigure(new Bishop(board[5][row], color));
        addFigure(new Knight(board[6][row], color));
        addFigure(new Rook(board[7][row], color));
    }

    /*
    places Pawns of a given color in the specified amount of columns and in the specified row
     */
    private void placePawns(int columns, int row, Color color) {
        for(int i = 0; i < columns; i++){
            addFigure(new Pawn(board[i][row], color));
        }
    }
}