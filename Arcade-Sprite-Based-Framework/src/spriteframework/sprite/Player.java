package spriteframework.sprite;

import javax.swing.ImageIcon;

import spriteframework.Commons;

import java.awt.Image;
import java.awt.event.KeyEvent;

public class Player extends Sprite {

    private int width;
    private int direction;


    public int getDirection() {
        return direction;
    }



    public Player(String image) {
        loadImage(image);
		getImageDimensions();
		resetState();
    }

    protected void loadImage (String image) { // mudei
        ImageIcon ii = new ImageIcon(image); // mudei
        width = ii.getImage().getWidth(null);
        setImage(ii.getImage());
    }
    
    public void act() {

        x += dx;

        if (x <= 2) {

            x = 2;
        }

        if (x >= Commons.BOARD_WIDTH - 2 * width) {

            x = Commons.BOARD_WIDTH - 2 * width;
        }
    }

    public void act2() {

        x += dx;
        y += dy;

        if (x <= 2) {

            x = 2;
        }

        if (y <= 2) {

            y = 2;
        }

        if (x >= Commons.BOARD_WIDTH2 - 2) {

            x = Commons.BOARD_WIDTH2 - 2 ;
        }

        if (y >= Commons.BOARD_HEIGHT2 - 2) {

            y = Commons.BOARD_HEIGHT2 - 2 ;
        }



    }


    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = -4;
            direction = 1;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 4;
            direction = 2;
        }

        if (key == KeyEvent.VK_UP) {

            dy = -4;
            direction = 3;
        }

        if (key == KeyEvent.VK_DOWN) {

            dy = 4;
            direction = 4;
        }
    }


    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {

            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {

            dy = 0;
        }
    }
    protected void resetState() {

        setX(Commons.INIT_PLAYER_X);
        setY(Commons.INIT_PLAYER_Y);
    }
}
