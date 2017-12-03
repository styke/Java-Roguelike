package game.story;

import game.Entity;

public class DialogueOptionAdvance implements IDialogueOption {
    private String label;

    public DialogueOptionAdvance(String label) {
        this.label = label;
    }

    public DialogueOptionAdvance() {
        this("...");
    }

    @Override
    public String toString() {
        return label;
    }

    @Override
    public void onSelected(Storyline storyline, Entity entity) {
        storyline.advance();
    }
}
