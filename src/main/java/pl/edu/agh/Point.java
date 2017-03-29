package pl.edu.agh;

public class Point {
    private int x;
    private int y;

    public static void main(String[] args) {
        Point point = new Point();
        point.setXY(10, 15);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
