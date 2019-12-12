package freezemonster;

import freezemonster.sprite.MonsterRay;
import freezemonster.sprite.Monsters;
import freezemonster.sprite.WoodyRay;
import spriteframework.AbstractBoard;
import spriteframework.sprite.BadSprite;
import spriteframework.sprite.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class FreezeMonsterBoard extends AbstractBoard {
    private WoodyRay shot;

    private int deaths = 0;
    public String N;



    private String explImg = "images/explosion.png";

    public FreezeMonsterBoard(String image) {
        super(image);
        this.setJogo(2);
    }


    protected void createBadSprites() {  // create sprites
        for (int i = 1; i < 10; i++) {
            N = Integer.toString(i);
                Monsters alien = new Monsters(freezemonster.Commons.ALIEN_INIT_X + 40 * i,
                        freezemonster.Commons.ALIEN_INIT_Y + 30 * i, N);
                badSprites.add(alien);
            }
        }


    protected void createOtherSprites() {
        shot = new WoodyRay();
    }

    private void drawShot(Graphics g) {

        if (shot.isVisible()) {

            g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
        }
    }

    // Override
    protected void drawOtherSprites(Graphics g) {
        drawShot(g);
    }


    protected void processOtherSprites(Player player, KeyEvent e) {
        int x = player.getX();
        int y = player.getY();

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {

            if (inGame) {

                if (!shot.isVisible()) {

                    shot = new WoodyRay(x, y); //Criar aqui a direção para o tiro do Woody
                    shot.setDirection(player.getDirection());
                }
            }
        }
    }


    protected void setBoardColor(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0, freezemonster.Commons.BOARD_WIDTH, freezemonster.Commons.BOARD_HEIGHT);
    }


    protected void setGameOver(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, freezemonster.Commons.BOARD_WIDTH, freezemonster.Commons.BOARD_HEIGHT);
        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, freezemonster.Commons.BOARD_WIDTH / 2 - 30, freezemonster.Commons.BOARD_WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, freezemonster.Commons.BOARD_WIDTH / 2 - 30, freezemonster.Commons.BOARD_WIDTH - 100, 50);

        Font small = new Font("Helvetica", Font.BOLD, 20);
        FontMetrics fontMetrics = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (freezemonster.Commons.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2,
                freezemonster.Commons.BOARD_WIDTH / 2);
    }

    protected void update() {

        if (deaths == freezemonster.Commons.NUMBER_OF_ALIENS_TO_DESTROY) {

            inGame = false;
            timer.stop();
            message = "Game won!";
        }

        // player
        for (Player player: players)
            player.act2();

        // shot
        if (shot.isVisible()) {

            int shotX = shot.getX();
            int shotY = shot.getY();

            for (BadSprite alien : badSprites) {

                int alienX = alien.getX();
                int alienY = alien.getY();

                if (alien.isVisible() && shot.isVisible() && !alien.frozen) {
                    if (shotX >= (alienX)
                            && shotX <= (alienX + freezemonster.Commons.ALIEN_WIDTH)
                            && shotY >= (alienY)
                            && shotY <= (alienY + freezemonster.Commons.ALIEN_HEIGHT)) {

                        String frozen_alien = alien.getN();
                        ImageIcon ii = new ImageIcon("images/monster"+frozen_alien+"bg.png");
                        alien.setImage(ii.getImage());
                        alien.setFrozen(true);
                        deaths++;
                        shot.die();
                    }
                }
            }

            shot.act();
        }

        // aliens

        for (BadSprite alien : badSprites) {
            alien.act();
        }

        // bombs

        updateOtherSprites();
    }


    private void updateOtherSprites() {
        Random generator = new Random();

        for (BadSprite alien : badSprites) {

            int shot = generator.nextInt(15);
            MonsterRay bomb = ((Monsters)alien).getBomb();

            if (shot == spaceinvaders.Commons.CHANCE && alien.isVisible() && bomb.isDestroyed()) {

                bomb.setDestroyed(false);
                bomb.setX(alien.getX());
                bomb.setY(alien.getY());
                bomb.setDirection(((Monsters) alien).getDirection());
            }

            int bombX = bomb.getX();
            int bombY = bomb.getY();
            int playerX = players.get(0).getX();
            int playerY = players.get(0).getY();

            if (players.get(0).isVisible() && !bomb.isDestroyed()) {

                if (bombX >= (playerX)
                        && bombX <= (playerX + freezemonster.Commons.PLAYER_WIDTH)
                        && bombY >= (playerY)
                        && bombY <= (playerY + freezemonster.Commons.PLAYER_HEIGHT)) {

                    ImageIcon ii = new ImageIcon(explImg);
                    players.get(0).setImage(ii.getImage());
                    players.get(0).setDying(true);
                    bomb.setDestroyed(true);
                }
            }

            if(alien.frozen){
                bomb.setDestroyed(true);
            }

            if (!bomb.isDestroyed()) {
                bomb.act();
            }
        }
    }
}
