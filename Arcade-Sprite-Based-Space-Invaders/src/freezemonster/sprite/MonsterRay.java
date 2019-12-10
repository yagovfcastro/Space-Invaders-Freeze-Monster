package freezemonster.sprite;

import spriteframework.sprite.BadSprite;

import javax.swing.*;

public class MonsterRay extends BadSprite {

    private boolean destroyed;
    private int direction;

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public MonsterRay(int x, int y) {
        initBomb(x, y);
    }

    @Override
    public void act() {

        if(this.x <= 0 || this.x >= 800 || this.y <= 0 || this.y >= 800){
            this.setDestroyed(true);
        }

        if(this.direction == 1){
            this.moveX(3);
        }

        if(this.direction == 2){
            this.moveX(-3);
        }

        if(this.direction == 3){
            this.moveY(3);
        }

        if(this.direction == 4){
            this.moveY(-3);
        }
    }

    private void initBomb(int x, int y) {
        setDestroyed(true);

        this.x = x;
        this.y = y;

        String bombImg = "images/gosma.png";
        ImageIcon ii = new ImageIcon(bombImg);
        setImage(ii.getImage());
    }

    public void setDestroyed(boolean destroyed) {

        this.destroyed = destroyed;
    }

    public boolean isDestroyed() {

        return destroyed;
    }
}
