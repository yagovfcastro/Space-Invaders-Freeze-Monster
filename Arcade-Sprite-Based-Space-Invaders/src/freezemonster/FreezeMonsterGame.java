package freezemonster;

import spriteframework.AbstractBoard;
import spriteframework.MainFrame;
import java.awt.*;

public class FreezeMonsterGame extends MainFrame{

    public FreezeMonsterGame () {
        super("FreezeMonster");
    }

    protected AbstractBoard createBoard() {
        return new FreezeMonsterBoard("images/woody.png");
    }


    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            new FreezeMonsterGame();
        });
    }
}
