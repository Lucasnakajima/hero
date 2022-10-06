public class Position {
    private int x=0;
    private int y=0;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    Position(int x, int y){
        this.setX(x);
        this.setY(y);
    }

    Position(){
        setX(0);
        setY(0);
    }

}
