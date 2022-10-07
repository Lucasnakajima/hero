import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Element {

    private Position position = new Position();

    Element(int x, int y){
        position.setX(x);
        position.setY(y);
    }

    public int getX(){
        return position.getX();
    }
    public int getY(){
        return position.getY();
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    abstract void draw(TextGraphics graphics);
}
