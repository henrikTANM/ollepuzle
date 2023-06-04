public class Card {
    private int leftDrawing;
    private int rightDrawing;
    private int bottomDrawing;
    private int upperDrawing;

    public Card(int leftDrawing, int rightDrawing, int bottomDrawing, int upperDrawing) {
        this.leftDrawing = leftDrawing;
        this.rightDrawing = rightDrawing;
        this.bottomDrawing = bottomDrawing;
        this.upperDrawing = upperDrawing;
    }

    public int getLeftDrawing() {
        return leftDrawing;
    }

    public int getRightDrawing() {
        return rightDrawing;
    }

    public int getBottomDrawing() {
        return bottomDrawing;
    }

    public int getUpperDrawing() {
        return upperDrawing;
    }

    public void turn() {
        int saveLeft = leftDrawing;
        leftDrawing = upperDrawing;
        upperDrawing = rightDrawing;
        rightDrawing = bottomDrawing;
        bottomDrawing = saveLeft;
    }
}