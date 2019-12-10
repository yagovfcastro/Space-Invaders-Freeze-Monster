package freezemonster.sprite;

import spriteframework.sprite.BadSprite;

import javax.swing.*;

public class WoodyRay extends BadSprite{
    private int direction;


    public void setDirection(int direction) {
        this.direction = direction;
    }

        public WoodyRay() {
        }

        public WoodyRay(int x, int y) {

            initShot(x, y);
        }

    @Override
    public void act() {

        if(this.x <= 0 || this.x >= 800 || this.y <= 0 || this.y >= 800){
            this.die();
        }

        if(this.direction == 1){
            this.moveX(-6);
        }

        if(this.direction == 2){
            this.moveX(6);
        }

        if(this.direction == 3){
            this.moveY(-6);
        }

        if(this.direction == 4){
            this.moveY(6);
        }
    }

        private void initShot(int x, int y) {

            String shotImg = "images/ray.png";
            ImageIcon ii = new ImageIcon(shotImg);
            setImage(ii.getImage());

            int H_SPACE = 1;
            setX(x + H_SPACE);

            int V_SPACE = 1;
            setY(y - V_SPACE);
        }
}
