package spriteframework;


import javax.swing.JPanel;
import javax.swing.Timer;

import spriteframework.sprite.BadSprite;
import spriteframework.sprite.Player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.LinkedList;


public abstract class AbstractBoard extends JPanel {

    private Dimension d;
    public int jogo;
    
    //define sprites
//    private List<Alien> aliens;
    protected LinkedList<Player> players;
    
    protected LinkedList<BadSprite> badSprites;
    
//    private Shot shot;
//    
    // define global control vars   
//    private int direction = -1;
//    private int deaths = 0;

    private int numberPlayers;  // to do - future use
    protected boolean inGame = true;
//    private String explImg = "src/images/explosion.png";
    protected String message = "Game Over";

    protected Timer timer;

    // Frozen Spots
    //  void initBoard()
    // 
    // HotSpots
    protected abstract void createBadSprites();
    protected abstract void createOtherSprites();
    protected abstract void drawOtherSprites(Graphics g);
    protected abstract void update();
    protected abstract void processOtherSprites(Player player, KeyEvent e);
    protected abstract void setBoardColor(Graphics g);
    protected abstract void setGameOver(Graphics g);

    public void setJogo(int jogo) {
        this.jogo = jogo;
    }

    public AbstractBoard(String image) {
        initBoard(image);
    }

    private void initBoard(String image) {

    	addKeyListener(new TAdapter());
    	setFocusable(true);

    	if(image.equals("images/player.png")) {
            d = new Dimension(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        }

    	if(image.equals("images/woody.png")){
            d = new Dimension(Commons.BOARD_WIDTH2, Commons.BOARD_HEIGHT2);
        }
    	setBackground(Color.black);

    	timer = new Timer(Commons.DELAY, new GameCycle());
    	timer.start();

    	createPlayers(image);
    	numberPlayers = 1;
    	badSprites = new LinkedList<BadSprite>();
    	createBadSprites();
    	createOtherSprites();
		//        shot = new Shot();
    }


    private void createPlayers(String image) {
		players = new LinkedList<Player>();
        players.add(createPlayer(image));
	}
	
	private Player createPlayer(String image) {
		return new Player(image);
	}

    private void drawBadSprites(Graphics g) {

        for (BadSprite bad : badSprites) {

            if (bad.isVisible()) {

                g.drawImage(bad.getImage(), bad.getX(), bad.getY(), this);
            }

            if (bad.isDying()) {

                bad.die();
            }
            if (bad.getBadnesses()!= null) {
            	for (BadSprite badness: bad.getBadnesses()) {
            		if (!badness.isDestroyed()) {
            			g.drawImage(badness.getImage(), badness.getX(), badness.getY(), this);
            		}
            	}
            }
        }
    }

    private void drawPlayers(Graphics g) {
    	for (Player player: players) {
    		if (player.isVisible()) {
    			g.drawImage(player.getImage(), player.getX(), player.getY(), this);
    		}

    		if (player.isDying()) {

    			player.die();
    			inGame = false;
    		}
    	}
    }





    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }



    private void doDrawing(Graphics g1) { // Template Method
        Graphics2D g = (Graphics2D) g1;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        setBoardColor(g);

        if (inGame) {

            if(this.jogo == 1) {
                g.setColor(Color.green);
                g.drawLine(0, Commons.GROUND,
                        Commons.BOARD_WIDTH, Commons.GROUND);
            }

            drawBadSprites(g);
            drawPlayers(g);
            drawOtherSprites(g);

        } else {

            if (timer.isRunning()) {
                timer.stop();
            }

            gameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void gameOver(Graphics g) {  //Outro template Method
        setGameOver(g);
    }



    private void doGameCycle() {
        update();
        repaint();
    }



	private class GameCycle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            doGameCycle();
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            for (Player player: players)
                 player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
        	for (Player player: players) {
                player.keyPressed(e);

                processOtherSprites(player, e); // hotspot
        	}
        }
    }
}
