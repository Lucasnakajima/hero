import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.util.Random;

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

        public Position move() {
            return switch (new Random().nextInt(4)) {
                case 0 -> new Position(getX(), getY() - 1);
                case 1 -> new Position(getX() + 1, getY());
                case 2 -> new Position(getX(), getY() + 1);
                case 3 -> new Position(getX() - 1, getY());
                default -> new Position(getX(), getY());
            };
        }
}
