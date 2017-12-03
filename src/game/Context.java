package game;

import game.gui.elements.Element;

public class Context {
    private GameState gameState;
    private Element popupElement;

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public boolean hasRequestedPopup() {
        return getGameState() == GameState.POPUP;
    }

    public void clearRequest() {
        setGameState(GameState.PLAYING);
        popupElement = null;
    }

    public Element getRequestedPopup() {
        return popupElement;
    }

    public void requestPopupDisplay(Element element) {
        if (element != null) {
            setGameState(GameState.POPUP);
            popupElement = element;
        } else {
            throw new NullPointerException("GUI Popup cannot be a null object.");
        }
    }
}
