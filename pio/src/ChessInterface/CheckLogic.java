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
    public static void checkLoop() {
        setCheckState();
        if (checkState)
            System.out.println("checkstate: " + true);
        gameEnded = isCheckmated();
    }

    private static boolean isChecked(Square square, Color kingColor) {
        for (Pieces.Piece piece : PieceList.getOppositeColorPieces(kingColor)) {
            if (piece.isAbleToMove(square)) {
                figuresChecking++;
                checkingSquare = Chessboard.board[piece.getxPieceCoordinate()][piece.getyPieceCoordinate()];
                System.out.println("Which figure checked" + piece);
            }
            if (figuresChecking != 0)
                return true;
        }
        return false;
    }

    private static boolean isCheckmated() {
        if (canAttackerBeTaken()) {
            return false;
        } else if (canKingMove()) {
            return false;
        }
         else return !canKingBeProtected();
    }

    private static boolean canKingMove() {
        Color kingColor = Game.current_turn;
        King checkedKing = PieceList.getKing(kingColor);
        for (int y = checkedKing.getyPieceCoordinate() - 1; y <= checkedKing.getyPieceCoordinate() + 1; y++) {
            for (int x = checkedKing.getxPieceCoordinate() - 1; x <= checkedKing.getxPieceCoordinate() + 1; x++) {
                figuresChecking = 0;
                if (x >= 0 && y >= 0 && x < 8 &&  y < 8) {
                   if(checkedKing.isAbleToMove(Chessboard.board[x][y]) &&
                           !isChecked(Chessboard.board[x][y], kingColor)) {
                       System.out.println("can move to" + Chessboard.board[x][y]);
                       return true;
                   }
                }
            }
        }
        return true;
    }

    private static boolean canAttackerBeTaken() {
        if (figuresChecking == 0)
            return true;

        else if (figuresChecking > 1) {
            figuresChecking = 0;
            return false;
        } else {
            for (Pieces.Piece piece : PieceList.getColorPieces(Game.current_turn)) {
                if (piece.isAbleToMove(checkingSquare)) {
                    System.out.println( piece + " " + "Piece can take");
                    figuresChecking = 0;
                    return true;
                }
            }
            return false;
        }

    }

    private static boolean canKingBeProtected() {
        return true;
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
            System.out.println("checkbacklight: " + x + "," + y + checkBacklight.getVisibleRect());
        } else checkBacklight.setVisible(false);
    }
}
