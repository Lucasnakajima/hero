import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.lang.Math;

public class Monster extends Element{

    Monster(int x, int y){
        super(x,y);
    }

    void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getX(),
                getY()), "M");
    }

    public void move(Hero hero){
        float x=getX()-getX(), y= getY()-getY();
        //angulo proximo de 0
        if (x<0 && y==0) setPosition(moveRight());
        //angulo proximo de 45
        else if (x<0 && y>0){
            setPosition(moveUp());
           setPosition(moveRight());
        }
        //angulo próximo de 90
        else if (x==0 && y>0) setPosition(moveUp());
        //angulo próximo de 135
        else if (x>0 && y>0) {
            setPosition(moveUp());
            setPosition(moveLeft());
        }
        //angulo próximo de 180
        else if (x>0 && y==0) setPosition(moveLeft());
        //angulo proximo de 225
        else if (x>0 && y<0){
            setPosition(moveDown());
            setPosition(moveLeft());
        }
        //angulo proximo de 270
        else if (x==0 && y<0) setPosition(moveDown());
        //angulo proximo de 3115
        else{
            setPosition(moveRight());
            setPosition(moveDown());
        }
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
