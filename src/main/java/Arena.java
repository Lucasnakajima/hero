
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Arena {
    private int height;
    private int width;
    Hero hero = new Hero(10, 10);
    private List<Wall> walls;

    Arena(int h, int w) {
        this.height = h;
        this.width = w;
        this.walls = createWalls();
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
        System.out.println(key);
        switch (key.getKeyType()) {
            case ArrowUp -> moveHero(hero.moveUp());
            case ArrowDown -> moveHero(hero.moveDown());
            case ArrowLeft -> moveHero(hero.moveLeft());
            case ArrowRight -> moveHero(hero.moveRight());
            case Character -> {
                if (key.getCharacter() == 'q' || key.getCharacter() == 'Q') {
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
        return temphero.getPosition().getY() < getHeight() && temphero.getPosition().getX() < getWidth() && temphero.getPosition().getY() >= 0 && temphero.getPosition().getX() >= 0;
    }

    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("Red"));
                graphics.fillRectangle(new TerminalPosition(0, 0), new
                        TerminalSize(width, height), ' ');
        hero.draw(graphics);
    }

    private List<Wall> createWalls() {
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }

}