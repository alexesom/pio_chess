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
    public JPanel backlightPanel = new SquareBacklight(new Color(91, 189, 116));
    private Adapter mouseAdapter = new Adapter(layer, capturedPiecesPanel1, capturedPiecesPanel2, backlightPanel);

    public Chessboard() {
        backlightPanel.setVisible(false);
        createChessboardSquares();
        createCapturedPiecesPanel();
        placeChessboardPieces();
        addMouse();
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

            if(destinationPiece.isAbleToMove(kingSquare)) {
                return true;
            }
        }
        return false;
    }

    /*
    Checks whether there's a Piece on originSquare and moves it to destinationSquare if the move is legal
     */
    public static void tryMove(Square originSquare, Square destinationSquare) throws Exception {
        Piece movingPiece = originSquare.getSquarePiece();
        if (movingPiece == null) {
            throw new Exception("tryMove exception: piece is null(!)");
        }

        //check if the piece is the current player's piece
        if (movingPiece.getPieceColor() != Game.current_turn) {
            throw new Exception("tryMove exception: moving other player's piece");
        }
        //check if the move is legal
        if (!movingPiece.isAbleToMove(destinationSquare)) {
            throw new Exception("tryMove exception: illegal move");
        }
        //check if trying to take own piece
        Piece pieceAtDestination = destinationSquare.getSquarePiece();
        if ((pieceAtDestination != null) && (pieceAtDestination.getPieceColor() == Game.current_turn)) {
            throw new Exception("tryMove exception: taking own piece");
        }

        int oldX = originSquare.getXSquareCoordinate();
        int oldY = originSquare.getYSquareCoordinate();
        Piece oldPiece = destinationSquare.getSquarePiece();
        int newX = destinationSquare.getXSquareCoordinate();
        int newY = destinationSquare.getYSquareCoordinate();

        movingPiece.setPieceCoordinates(newX, newY);
        destinationSquare.setSquarePiece(movingPiece);
        originSquare.setSquarePiece(null);
        PieceList.removePiece(oldPiece);
        if (CheckLogic.isChecked()){
            movingPiece.setPieceCoordinates(oldX, oldY);
            originSquare.setSquarePiece(movingPiece);
            destinationSquare.setSquarePiece(oldPiece);
            PieceList.addPiece(oldPiece);
            throw new Exception("tryMove exception: own king checked after move");
        } else {
            movingPiece.move();
            Game.nextTurn();
        }


    }

    public static Square getBoardSquare(int x, int y) {
        return Chessboard.board[x][y];
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
        PieceList.addPiece(figure);
    }
    private void addKing(King figure) {
        int x = figure.getxPieceCoordinate();
        int y = figure.getyPieceCoordinate();
        layer.add(figure.panel);
        board[x][y].setSquarePiece(figure);

        if (figure.getPieceColor() == Color.white){
            if (PieceList.whiteKing != null)
                System.err.println("White king already existed! King in list overwritten.");
            PieceList.whiteKing = figure;
        }
        else if (figure.getPieceColor() == Color.black){
            if (PieceList.blackKing != null)
                System.err.println("Black king already existed! King in list overwritten.");
            PieceList.blackKing = figure;
        }
    }


    private void addMouse() {
        layer.addMouseListener(mouseAdapter);
    }

    /*
    Place the pieces in their starting positions
     */
    private void placeChessboardPieces() {
        // Black pieces
        placePieces(7, Color.BLACK);
        placePawns(8, 6, Color.BLACK);

        // White pieces
        placePieces(0, Color.WHITE);
        placePawns(8, 1, Color.WHITE);
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
        addKing(new King(board[4][row], color));
        addFigure(new Bishop(board[5][row], color));
        addFigure(new Knight(board[6][row], color));
        addFigure(new Rook(board[7][row], color));
    }

    /*
    places Pawns of a given color in the specified amount of columns and in the specified row
     */
    private void placePawns(int columns, int row, Color color) {
        for (int i = 0; i < columns; i++) {
            addFigure(new Pawn(board[i][row], color));
        }
    }

    public static class EnPassant {
        private static Pawn enPassantPawn;
        private static Square enPassantSquare;

        public static Pawn getEnPassantPawn() {
            return enPassantPawn;
        }

        public static void setEnPassantPawn(Pawn enPassantPawnArg) {
            enPassantPawn = enPassantPawnArg;
        }

        public static Square getEnPassantSquare() {
            return enPassantSquare;
        }

        public static void setEnPassantSquare(Square enPassantSquareArg) {
            enPassantSquare = enPassantSquareArg;
        }

        public static boolean enPassantMove(Square selectedSquare, Square destinationSquare) {
            Piece selectedSquarePiece = selectedSquare.getSquarePiece();
            int selectedSquareX = selectedSquare.getXSquareCoordinate();
            int selectedSquareY = selectedSquare.getYSquareCoordinate();
            int destinationSquareY = destinationSquare.getYSquareCoordinate();

            if (selectedSquarePiece.isAbleToMove(destinationSquare) &&
                    selectedSquarePiece instanceof Pawn) {
                if (Chessboard.EnPassant.isEmpty() &&
                        ((destinationSquareY - selectedSquareY) == 2)) {
                    Chessboard.EnPassant.setEnPassantPawn((Pawn) selectedSquarePiece);
                    Chessboard.EnPassant.setEnPassantSquare(Chessboard.board[selectedSquareX][selectedSquareY - 1]);
                    //here we get previous Square to check if the next move will be EnPassant

                } else if (destinationSquareY == EnPassant.getEnPassantSquare().getYSquareCoordinate()) {
                    //trzeba usunąć pionek enPassant.getEnPassantPawn z szachownicy
                    return true;
                }
            }
            return false;
        }

        public static void makeNull() {
            enPassantPawn = null;
            enPassantSquare = null;
        }

        public static boolean isEmpty() {
            return enPassantPawn == null && enPassantSquare == null;
        }
    }
}