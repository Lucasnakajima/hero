import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Hero {
    private int x;
    private int y;
    Hero(int x, int y){
        this.x=x;
        this.y=y;
    }
    void draw(Screen screen){
        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
    }
    void moveUp(){
        this.y--;
    }
    void moveDown(){
        this.y++;
    }
    void moveRight(){
        this.x++;
    }
    void moveLeft(){
        this.x--;
    }
}
