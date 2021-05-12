package test;

import static org.junit.Assert.assertEquals;
import ChessInterface.Game;
import java.awt.*;
import org.junit.jupiter.api.Test;

class nextTurnTest {

    @Test
    void testNextTurnBlack() {
        //given
        Game.main(null);
        Color current = Color.WHITE;

        //when
        Game.nextTurn();

        //then
        Color expected = Color.BLACK;
        assertEquals(expected, Game.current_turn);

    }
    @Test
    void testNextTurnWhite() {
        //given
        Game.main(null);
        Color current = Color.WHITE;

        //when
        Game.nextTurn();
        Game.nextTurn();

        //then
        Color expected = Color.WHITE;
        assertEquals(expected, Game.current_turn);

    }
}
