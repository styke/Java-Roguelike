package gui;

import game.Renderer;

public class TextView extends Element {
    private String text;
    private boolean wrapped;
    private int wrappedLength;

    public TextView(String text) {
        wrapped = false;
        this.text = text;
        wrappedLength = 0;
    }

    public TextView() {
        this("");
    }

    public boolean isWrapped() {
        return wrapped;
    }

    public void setWrapped(boolean wrapped) {
        this.wrapped = wrapped;
    }

    public int getWrappedLength() {
        return wrappedLength;
    }

    public void setWrappedLength(int wrappedLength) {
        this.wrappedLength = wrappedLength;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Renderer renderer) {
        if (wrapped && wrappedLength != 0) {
            renderer.drawTextWrapped(getX(), getY(), wrappedLength, text);
        } else {
            renderer.drawText(getX(), getY(), text);
        }
    }

    @Override
    public int getWidth() {
        if (wrapped && wrappedLength != 0) {
            return wrappedLength;
        }
        return text.length();
    }

    @Override
    public int getHeight() {
        if (wrapped && wrappedLength != 0) {
            return text.length() / wrappedLength + 1;
        }
        return 1;
    }
}
