package spriteframework;

import javax.swing.JFrame;

public abstract class MainFrame extends JFrame  {

    // hotspot
    protected abstract AbstractBoard createBoard();
    
    public MainFrame(String t) {
          
        add(createBoard());
		
		setTitle(t);

		if(t.equals("FreezeMonster")) {
			setSize(Commons.BOARD_WIDTH2, Commons.BOARD_HEIGHT2);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setResizable(false);
			setLocationRelativeTo(null);
			setVisible(true);
		}

		if(t.equals("SpaceInvaders")) {
			setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setResizable(false);
			setLocationRelativeTo(null);
			setVisible(true);
		}

    }


//    public static void main(String[] args) {
//
//        EventQueue.invokeLater(() -> {
//
//            MainFrameExtended ex = new MainFrameExtended();
//        });
//    }
    
}
