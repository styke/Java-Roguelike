package game;

import gui.Element;

public enum GameState {
    POPUP,
    PLAYING,
    UNFOCUSED;

    private static GameState instance = PLAYING;
    private Element popupElement;

    public static GameState getInstance() {
        return instance;
    }

    public boolean hasRequestedPopup() {
        return popupElement != null;
    }

    public void clearRequest() {
        popupElement = null;
    }

    public Element getRequestedPopup() {
        return popupElement;
    }

    public void requestPopupDisplay(Element element) {
        popupElement = element;
    }
}
