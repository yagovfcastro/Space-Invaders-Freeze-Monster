package freezemonster.sprite;

import freezemonster.Commons;
import spriteframework.sprite.BadSprite;
import spriteframework.sprite.BadnessBoxSprite;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Random;

public class Monsters extends BadnessBoxSprite {
    private MonsterRay bomb;

    public int getDirection() {
        return direction;
    }

    private int direction = 1;

    public Monsters(int x, int y, String N) {
        initMonster(x, y, N);
    }

    private void initMonster(int x, int y, String N) {

        setN(N);
        this.x = x;
        this.y = y;

        bomb = new MonsterRay(x, y);

        String alienImg = "images/monster"+N+".png";
        ImageIcon ii = new ImageIcon(alienImg);

        setImage(ii.getImage());
    }

    @Override
    public void act() {

        if(this.x <= 0){
            this.x = 10;
        }

        if(this.x >= 790){
            this.x = 790;
        }

        if(this.y <= 0){
            this.y = 10;
        }

        if(this.y >= 790){
            this.y = 790;
        }

        Random generator = new Random();
        int chance = generator.nextInt(50);

        if(this.frozen == true){
            this.moveX(0);
            this.moveY(0);
        }else {
            if (chance == Commons.left) {
                this.moveX(-10);
                direction = 1;
            }

            if (chance == Commons.right) {
                this.moveX(10);
                direction = 2;
            }

            if (chance == Commons.up) {
                this.moveY(-10);
                direction = 3;
            }

            if (chance == Commons.down) {
                this.moveY(10);
                direction = 4;
            }

            if (chance == 2 ) {
                this.moveY(10);
                this.moveX(10);
            }

            if (chance == 4 ) {
                this.moveY(-10);
                this.moveX(-10);
            }

            if (chance == 6 ) {
                this.moveY(-10);
                this.moveX(10);
            }

            if (chance == 10) {
                this.moveY(10);
                this.moveX(-10);
            }
        }
    }

    public MonsterRay getBomb() {
        return bomb;
    }

    @Override
    public LinkedList<BadSprite> getBadnesses() {
        LinkedList<BadSprite> aBomb = new LinkedList<BadSprite>();
        aBomb.add(bomb);
        return aBomb;
    }
}
