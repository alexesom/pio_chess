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
        capturedPiecesPanel1.setBounds(675,95,560,200);
        capturedPiecesPanel1.setOpaque(false);

        capturedPiecesPanel2.setBounds(675,375,560,300);
        capturedPiecesPanel2.setOpaque(false);
    }

    private void createChessboardSquares() {
        for (int iy = 7; iy >= 0; iy--) {
            for (int jx = 0; jx < 8; jx++) {
                board[jx][iy] = new Square(jx, iy,  560, 560);
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

    private void addMouse(){
        layer.addMouseListener(mouseAdapter);
    }


    /*
    Place the pieces in their starting positions
     */
    private void placeChessboardPieces(){
        // Black pieces
        addFigure(new Rook(board[0][7], Color.BLACK));
        addFigure(new Knight(board[1][7], Color.BLACK));
        addFigure(new Bishop(board[2][7], Color.BLACK));
        addFigure(new Queen(board[3][7], Color.BLACK));
        addFigure(new King(board[4][7], Color.BLACK));
        addFigure(new Bishop(board[5][7], Color.BLACK));
        addFigure(new Knight(board[6][7], Color.BLACK));
        addFigure(new Rook(board[7][7], Color.BLACK));
        addFigure(new Pawn(board[0][6], Color.BLACK));
        addFigure(new Pawn(board[1][6], Color.BLACK));
        addFigure(new Pawn(board[2][6], Color.BLACK));
        addFigure(new Pawn(board[3][6], Color.BLACK));
        addFigure(new Pawn(board[4][6], Color.BLACK));
        addFigure(new Pawn(board[5][6], Color.BLACK));
        addFigure(new Pawn(board[6][6], Color.BLACK));
        addFigure(new Pawn(board[7][6], Color.BLACK));

        // White pieces
        addFigure(new Rook(board[0][0], Color.WHITE));
        addFigure(new Knight(board[1][0], Color.WHITE));
        addFigure(new Bishop(board[2][0], Color.WHITE));
        addFigure(new Queen(board[3][0], Color.WHITE));
        addFigure(new King(board[4][0], Color.WHITE));
        addFigure(new Bishop(board[5][0], Color.WHITE));
        addFigure(new Knight(board[6][0], Color.WHITE));
        addFigure(new Rook(board[7][0], Color.WHITE));
        addFigure(new Pawn(board[0][1], Color.WHITE));
        addFigure(new Pawn(board[1][1], Color.WHITE));
        addFigure(new Pawn(board[2][1], Color.WHITE));
        addFigure(new Pawn(board[3][1], Color.WHITE));
        addFigure(new Pawn(board[4][1], Color.WHITE));
        addFigure(new Pawn(board[5][1], Color.WHITE));
        addFigure(new Pawn(board[6][1], Color.WHITE));
        addFigure(new Pawn(board[7][1], Color.WHITE));
    }
}