package spaceinvaders;

import java.awt.EventQueue;
import spriteframework.AbstractBoard;
import spriteframework.MainFrame;

public class SpaceInvadersGame extends MainFrame {


	public SpaceInvadersGame () {
		super("SpaceInvaders");
	}
	
	protected  AbstractBoard createBoard() {
		return new SpaceInvadersBoard("images/player.png");
	}


	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {

			new SpaceInvadersGame();
		});
	}

}
