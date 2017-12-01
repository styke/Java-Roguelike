package gui.elements;

import game.Renderer;

public class ProgressBar extends Element {
    private double progress;
    private int length;

    public ProgressBar(double progress, int length) {
        this.progress = progress;
        this.length = length;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Renderer renderer) {
        renderer.drawChar(getX(), getY(), '[');
        renderer.drawChar(getX() + length + 1, getY(), ']');
        for (int i = getX() + 1; i < getX() + 1 + length * progress; i++) {
            renderer.drawChar(i, getY(), '=');
        }
        for (int i = (int) (getX() + 1 + length * progress); i < getX() + 1 + length; i++) {
            renderer.drawChar(i, getY(), ' ');
        }
    }

    @Override
    public int getWidth() {
        return length + 2;
    }

    @Override
    public int getHeight() {
        return 1;
    }
}
