package ChessInterface;

import Pieces.King;
import Pieces.Piece;
import Pieces.Square;

import javax.swing.*;
import java.awt.*;

public class CheckLogic {
    public static boolean checkState = false;
    public static boolean gameEnded = false;
    public static JPanel checkBacklight = new SquareBacklight(new Color(219, 82, 55));
    private static int figuresChecking = 0;
    private static Square checkingSquare;
    private static King currentKing;

    public static void checkLoop() {
        setCheckState();
        if (checkState)
            System.out.println("checkstate: " + true);
        gameEnded = isCheckmated();
    }

    public static void setFiguresChecking(int figuresChecking) {
        CheckLogic.figuresChecking = figuresChecking;
    }

    public static void setCheckingSquare(Square square) {
        CheckLogic.checkingSquare = square;
    }

    public static boolean isChecked() {
        currentKing = PieceList.getKing(Game.current_turn);
        assert currentKing != null;
        Square kingSquare = Chessboard.getBoardSquare(currentKing.getxPieceCoordinate(), currentKing.getyPieceCoordinate());
        Color kingColor = currentKing.getPieceColor();
        for (Pieces.Piece piece : PieceList.getOppositeColorPieces(kingColor)) {
            if (piece.isAbleToMove(kingSquare)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isChecked(Square square, Color kingColor) {
        for (Pieces.Piece piece : PieceList.getOppositeColorPieces(kingColor)) {
            if (piece.isAbleToMove(square)) {
                figuresChecking++;
                checkingSquare = Chessboard.board[piece.getxPieceCoordinate()][piece.getyPieceCoordinate()];
                System.out.println("Which figure checked" + piece);
            }
        }
        return figuresChecking != 0;
    }

    public static void resetFiguresChecking() {
        figuresChecking = 0;
    }


    private static boolean isCheckmated() {
        if (canAttackerBeTaken()) {
            return false;
        }
        if (canKingMove()) {
            return false;
        }
        if (canKingBeProtected()) {
            return false;
        }
        return true;
    }

    private static boolean canKingMove() {
        Color kingColor = Game.current_turn;
        figuresChecking = 0;
        King checkedKing = PieceList.getKing(kingColor);
        for (int y = checkedKing.getyPieceCoordinate() - 1; y <= checkedKing.getyPieceCoordinate() + 1; y++) {
            for (int x = checkedKing.getxPieceCoordinate() - 1; x <= checkedKing.getxPieceCoordinate() + 1; x++) {
                if (x >= 0 && y >= 0 && x < 8 && y < 8) {
                    if (checkedKing.isAbleToMove(Chessboard.board[x][y]) &&
                            !isChecked(Chessboard.board[x][y], kingColor)) {
                        figuresChecking = 0;
                        System.out.println("can move to" + Chessboard.board[x][y].getXSquareCoordinate() + "; " + Chessboard.board[x][y].getYSquareCoordinate());
                        System.out.println(Chessboard.board[x][y].getSquareColor());
                        if (Chessboard.board[x][y].getSquarePiece() != null &&
                                Chessboard.board[x][y].getSquarePiece().getPieceColor() == kingColor) {
                            System.out.println(figuresChecking);
                        } else return true;
                    } else {
                        figuresChecking = 0;
                    }
                }
            }
        }
        return false;
    }

    public static boolean canAttackerBeTaken() {
        if (figuresChecking == 0)
            return true;
        else if (figuresChecking > 1) {
            figuresChecking = 0;
            return false;
        } else {
            for (Pieces.Piece piece : PieceList.getColorPieces(Game.current_turn)) {
                if (piece.isAbleToMove(checkingSquare)) {
                    if (piece instanceof King) {
                        System.out.println(figuresChecking);
                    } else {
                        System.out.println(piece + " " + "Piece can take");
                        figuresChecking = 0;
                        return true;
                    }
                }
            }
            return false;
        }

    }

    private static boolean canKingBeProtected() {
        King tmpKing;
        if (Game.current_turn == Color.black)
            tmpKing = PieceList.getKing(Color.white);
        else
            tmpKing = PieceList.getKing(Color.BLACK);
        System.out.println(figuresChecking);
        if (figuresChecking > 1) {
            System.out.println(figuresChecking);
            return false;
        } else {
            Square kingSquare = Chessboard.getBoardSquare(tmpKing.getxPieceCoordinate(), tmpKing.getyPieceCoordinate());
            System.out.println(Game.current_turn);
            System.out.println(kingSquare.getYSquareCoordinate());
            Piece checkingPiece = checkingSquare.getSquarePiece();
            System.out.println(checkingPiece);
            if (checkingPiece.isAbleToMove(kingSquare)) {
                for (Pieces.Square checkedSquare : PieceList.checkedSquaresPath) {
                    for (Pieces.Piece piece : PieceList.getColorPieces(Game.current_turn)) {
                        if (piece.isAbleToMove(checkedSquare)) {
                            figuresChecking = 0;
                            return true;
                        }
                    }
                }
            } else {
                PieceList.checkedSquaresPath.clear();
                return false;
            }
        }

        return false;
    }

    private static void setCheckState() {
        King currentKing = PieceList.getKing(Game.current_turn);
        assert currentKing != null;
        Square kingSquare = Chessboard.getBoardSquare(currentKing.getxPieceCoordinate(), currentKing.getyPieceCoordinate());
        Color kingColor = currentKing.getPieceColor();
        checkState = isChecked(kingSquare, kingColor);
    }

    public static void highlightCheck() {
        if (checkState) {
            King currentKing = PieceList.getKing(Game.current_turn);
            int kingX = currentKing.getxPieceCoordinate();
            int kingY = currentKing.getyPieceCoordinate();
            int x = 70 * kingX + 10;
            int y = 500 - 70 * kingY;
            checkBacklight.setBounds(x + 60, y + 30, 80, 80);
            checkBacklight.setVisible(true);
        } else checkBacklight.setVisible(false);
    }
}