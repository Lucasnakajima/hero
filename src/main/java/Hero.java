import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class Hero extends Element{

   private int coins = 0;

    Hero(int x, int y){
        super(x,y);
    }

    public int getCoins() {
        return coins;
    }

    void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getX(),
                getY()), "X");
    }

    public void addcoin(){
        coins++;
    }

    public Position moveUp(){
        return new Position(getX(), getY() - 1);
    }

    public Position moveDown(){
        return new Position(getX(), getY() + 1);
    }

    public Position moveRight(){
        return new Position(getX() + 1, getY());
    }

    public Position moveLeft(){
        return new Position(getX() - 1, getY());
    }

}
