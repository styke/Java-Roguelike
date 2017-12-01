package game;

import gui.elements.Element;

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
        return instance == POPUP;
    }

    public void clearRequest() {
        instance = PLAYING;
        popupElement = null;
    }

    public Element getRequestedPopup() {
        return popupElement;
    }

    public void requestPopupDisplay(Element element) {
        if (element != null) {
            instance = POPUP;
            popupElement = element;
        } else {
            throw new NullPointerException("GUI Popup cannot be null object.");
        }
    }
}
