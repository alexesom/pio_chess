import Pieces.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Game {

    public static Color current_turn;

    public static void main(String args[]) {

        StretchIcon icon = new StretchIcon("whitequeen.png");
        List<Piece> list = new ArrayList<>();
        list.add(new Queen(new Square(3,0, new JButton(icon)), Color.WHITE));
        Chessboard chessboard = new Chessboard(list);
                new GameInterface();
    }
}
