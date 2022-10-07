
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Arena {
    private int height;
    private int width;
    Hero hero = new Hero(10, 10);
    private List<Wall> walls = new ArrayList<Wall>();
    private List<Coin> coins = new ArrayList<Coin>();
    private List<Monster> monsters = new ArrayList<Monster>();

    Arena(int h, int w) {
        this.height = h;
        this.width = w;
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
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
        verifyMonsterCollisions(screen);
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
        moveMonsters();
        verifyMonsterCollisions(screen);
    }

    public void moveHero(Position position) {
        if (canHeroMove(position)) {
            hero.setPosition(position);
            retrieveCoins();
        }
    }

    private boolean canHeroMove(Position position) {
        Hero temphero = new Hero(hero.getPosition().getX(), hero.getPosition().getY());
        temphero.setPosition(position);
        return temphero.getPosition().getY() < getHeight()-1 && temphero.getPosition().getX() < getWidth()-1 && temphero.getPosition().getY() > 0 && temphero.getPosition().getX() > 0;
    }

    public void draw(final TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("Red"));
                graphics.fillRectangle(new TerminalPosition(0, 0), new
                        TerminalSize(width, height), ' ');
        for (Wall wall : walls)
            wall.draw(graphics);
        for (Coin coin : coins)
            coin.draw(graphics);
        for (Monster monster : monsters)
            monster.draw(graphics);
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

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            boolean ND = false;
            Coin tempcoin = new Coin(random.nextInt(width - 2) + 1,
                    random.nextInt(height - 2) + 1);
            if(tempcoin.getPosition().equals(hero.getPosition())){
                i--;
                ND = true;
            }
            else {
                for(Coin coin : coins){
                    if(coin.getPosition().equals(tempcoin.getPosition())){
                        i--;
                        ND = true;
                    }
                }
            }
            if(!ND) coins.add(tempcoin);
        }
        return coins;
    }

    private void retrieveCoins() {
        boolean OC = false;
        Coin temp = new Coin(0,0);
        for (Coin coin : coins) {
            OC = false;
            if (hero.getX() == coin.getX() && coin.getY() == hero.getY()) {
                hero.addcoin();
                OC = true;
                temp = coin;
                break;
            }
        }
        if(OC) coins.remove(temp);
    }

    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            boolean ND = false;
            Monster tempm = new Monster(random.nextInt(width - 2) + 1,
                    random.nextInt(height - 2) + 1);
            if(tempm.getPosition().equals(hero.getPosition())){
                i--;
                ND = true;
            }
            else if(!ND){
                for(Monster monster : monsters){
                    if(monster.getPosition().equals(tempm.getPosition())){
                        i--;
                        ND=true;
                    }
                }
            }
            else {
                for(Coin coin : coins){
                    if(coin.getPosition().equals(tempm.getPosition())){
                        i--;
                        ND = true;
                    }
                }
            }
            if(!ND) monsters.add(tempm);
        }
        return monsters;
    }

    private void moveMonsters(){
        for(Monster monster : monsters) monster.move(hero);
    }

    private void verifyMonsterCollisions(Screen screen) throws IOException {
        for(Monster monster : monsters){
            if(monster.getPosition().equals(hero.getPosition())) {
                System.out.print("You collected: ");
                System.out.print(hero.getCoins());
                System.out.println(" Coins!");
                screen.close();
            }
        }
    }

}