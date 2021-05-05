package ChessInterface;

import Pieces.*;

import java.awt.Color;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chessboard implements ActionListener {
    public JPanel chessboardPanel = new JPanel();
    public JLayeredPane layer = new JLayeredPane();
    public static Square[][] board = new Square[8][8];
    public JLayeredPane capturedPiecesPanel1 = new JLayeredPane();
    public JLayeredPane capturedPiecesPanel2 = new JLayeredPane();
    public JPanel backlightPanel = new SquareBacklight(new Color(91, 189, 116));
    public JPanel whitePromotionPanel = new JPanel();
    public JPanel blackPromotionPanel = new JPanel();
    private Adapter mouseAdapter = new Adapter(layer, capturedPiecesPanel1, capturedPiecesPanel2, backlightPanel, whitePromotionPanel, blackPromotionPanel);
    public JButton chooseWhiteRook = new JButton();
    public JButton chooseWhiteKnight = new JButton();
    public JButton chooseWhiteBishop = new JButton();
    public JButton chooseWhiteQueen = new JButton();
    public JButton chooseBlackRook = new JButton();
    public JButton chooseBlackKnight = new JButton();
    public JButton chooseBlackBishop = new JButton();
    public JButton chooseBlackQueen = new JButton();

    public Chessboard() {
        backlightPanel.setVisible(false);
        createChessboardSquares();
        createCapturedPiecesPanel();
        placeChessboardPieces();
        addMouse();
        createWhitePromotionPanel();
        createBlackPromotionPanel();
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

    public void promotion(Piece promotionFigure, JLabel newLabel) {
        addFigure(promotionFigure);
        mouseAdapter.promote(promotionFigure, newLabel);
    }

    public void addFigure(Piece figure) {
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

    public void createWhitePromotionPanel() {
        whitePromotionPanel.setBounds(210, 180, 280, 280);
        whitePromotionPanel.setLayout(new GridLayout(2, 2));
        whitePromotionPanel.setOpaque(false);

        ImageIcon figure = new ImageIcon("piecesIcons/whiterook.png");
        Image image = figure.getImage();
        Image newimg = image.getScaledInstance(100, 120,  java.awt.Image.SCALE_SMOOTH);
        figure = new ImageIcon(newimg);
        JButton rookButton = new JButton(figure);
        chooseWhiteRook = rookButton;
        chooseWhiteRook.setOpaque(false);
        chooseWhiteRook.addActionListener(this);
        whitePromotionPanel.add(chooseWhiteRook);

        figure = new ImageIcon("piecesIcons/whiteknight.png");
        image = figure.getImage();
        newimg = image.getScaledInstance(110, 120,  java.awt.Image.SCALE_SMOOTH);
        figure = new ImageIcon(newimg);
        JButton knightButton = new JButton(figure);
        chooseWhiteKnight = knightButton;
        chooseWhiteKnight.setOpaque(false);
        chooseWhiteKnight.addActionListener(this);
        whitePromotionPanel.add(chooseWhiteKnight);

        figure = new ImageIcon("piecesIcons/whitebishop.png");
        image = figure.getImage();
        newimg = image.getScaledInstance(100, 120,  java.awt.Image.SCALE_SMOOTH);
        figure = new ImageIcon(newimg);
        JButton bishopButton = new JButton(figure);
        chooseWhiteBishop = bishopButton;
        chooseWhiteBishop.setOpaque(false);
        chooseWhiteBishop.addActionListener(this);
        whitePromotionPanel.add(chooseWhiteBishop);

        figure = new ImageIcon("piecesIcons/whitequeen.png");
        image = figure.getImage();
        newimg = image.getScaledInstance(120, 110,  java.awt.Image.SCALE_SMOOTH);
        figure = new ImageIcon(newimg);
        JButton queenButton = new JButton(figure);
        chooseWhiteQueen = queenButton;
        chooseWhiteQueen.setOpaque(false);
        chooseWhiteQueen.addActionListener(this);
        whitePromotionPanel.add(chooseWhiteQueen);

        whitePromotionPanel.setVisible(false);
        whitePromotionPanel.setEnabled(false);
    }

    public void createBlackPromotionPanel() {
        blackPromotionPanel.setBounds(210, 180, 280, 280);
        blackPromotionPanel.setLayout(new GridLayout(2, 2));
        blackPromotionPanel.setOpaque(false);

        ImageIcon figure = new ImageIcon("piecesIcons/blackrook.png");
        Image image = figure.getImage();
        Image newimg = image.getScaledInstance(100, 120,  java.awt.Image.SCALE_SMOOTH);
        figure = new ImageIcon(newimg);
        JButton rookButton = new JButton(figure);
        chooseBlackRook = rookButton;
        chooseBlackRook.setOpaque(false);
        chooseBlackRook.addActionListener(this);
        blackPromotionPanel.add(chooseBlackRook);

        figure = new ImageIcon("piecesIcons/blackknight.png");
        image = figure.getImage();
        newimg = image.getScaledInstance(110, 120,  java.awt.Image.SCALE_SMOOTH);
        figure = new ImageIcon(newimg);
        JButton knightButton = new JButton(figure);
        chooseBlackKnight = knightButton;
        chooseBlackKnight.setOpaque(false);
        chooseBlackKnight.addActionListener(this);
        blackPromotionPanel.add(chooseBlackKnight);

        figure = new ImageIcon("piecesIcons/blackbishop.png");
        image = figure.getImage();
        newimg = image.getScaledInstance(100, 120,  java.awt.Image.SCALE_SMOOTH);
        figure = new ImageIcon(newimg);
        JButton bishopButton = new JButton(figure);
        chooseBlackBishop = bishopButton;
        chooseBlackBishop.setOpaque(false);
        chooseBlackBishop.addActionListener(this);
        blackPromotionPanel.add(chooseBlackBishop);

        figure = new ImageIcon("piecesIcons/blackqueen.png");
        image = figure.getImage();
        newimg = image.getScaledInstance(120, 110,  java.awt.Image.SCALE_SMOOTH);
        figure = new ImageIcon(newimg);
        JButton queenButton = new JButton(figure);
        chooseBlackQueen = queenButton;
        chooseBlackQueen.setOpaque(false);
        chooseBlackQueen.addActionListener(this);
        blackPromotionPanel.add(chooseBlackQueen);

        blackPromotionPanel.setVisible(false);
        blackPromotionPanel.setEnabled(false);
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

    public void actionPerformed(ActionEvent e) {
        Object button = e.getSource();
        try {
            if (button == chooseWhiteRook ){
                    promotion(new Rook(mouseAdapter.promotionSquare, Color.white), new JLabel(new StretchIcon("piecesIcons/whiterook.png")));
            } else if (button == chooseWhiteKnight ){
                    promotion(new Knight(mouseAdapter.promotionSquare, Color.white), new JLabel(new StretchIcon("piecesIcons/whiteknight.png")));
            } else if (button == chooseWhiteBishop ){
                    promotion(new Bishop(mouseAdapter.promotionSquare, Color.white), new JLabel(new StretchIcon("piecesIcons/whitebishop.png")));
            } else if (button == chooseWhiteQueen ) {
                    promotion(new Queen(mouseAdapter.promotionSquare, Color.white), new JLabel(new StretchIcon("piecesIcons/whitequeen.png")));
            }
            if (button == chooseBlackRook){
                    promotion(new Rook(mouseAdapter.promotionSquare, Color.black), new JLabel(new StretchIcon("piecesIcons/blackrook.png")));
            } else if (button == chooseBlackKnight){
                    promotion(new Knight(mouseAdapter.promotionSquare, Color.black), new JLabel(new StretchIcon("piecesIcons/blackknight.png")));
            } else if (button == chooseBlackBishop){
                    promotion(new Bishop(mouseAdapter.promotionSquare, Color.black), new JLabel(new StretchIcon("piecesIcons/blackbishop.png")));
            } else if (button == chooseBlackQueen) {
                    promotion(new Queen(mouseAdapter.promotionSquare, Color.black), new JLabel(new StretchIcon("piecesIcons/blackqueen.png")));
            }
                whitePromotionPanel.setVisible(false);
                whitePromotionPanel.setEnabled(false);
                blackPromotionPanel.setVisible(false);
                blackPromotionPanel.setEnabled(false);

        } catch (IllegalArgumentException sourceError) {
            System.err.println("ActionEvent fail");
        }
    }

}