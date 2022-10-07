
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Arena {
    private int height;
    private int width;
    Hero hero = new Hero(10, 10);

    Arena(int h, int w) {
        this.height = h;
        this.width = w;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void processKey(KeyStroke key, Screen screen) throws IOException {
        switch (key.getKeyType()) {
            case ArrowUp -> moveHero(hero.moveUp());
            case ArrowDown -> moveHero(hero.moveDown());
            case ArrowLeft -> moveHero(hero.moveLeft());
            case ArrowRight -> moveHero(hero.moveRight());
            case Character -> {
                if (key.getCharacter() == 'q') {
                    screen.close();
                }
            }
        }
    }

    public void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }

    private boolean canHeroMove(Position position) {
        Hero temphero = new Hero(hero.getPosition().getX(), hero.getPosition().getY());
        temphero.setPosition(position);
        return temphero.getPosition().getY() <= getHeight() && temphero.getPosition().getX() <= getWidth();
    }

    public void draw(Screen screen){hero.draw(screen);}


}