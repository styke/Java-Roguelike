package game.story;

import java.util.ArrayList;

public abstract class Storyline {
    private int stage;
    private ArrayList<Dialogue> dialogues;

    public Storyline() {
        stage = 0;
        dialogues = new ArrayList<>();
    }

    public Dialogue getCurrentDialogue() {
        if (dialogues.size() == 0) return null;
        return dialogues.get(stage);
    }

    public abstract void onFinished();

    public ArrayList<Dialogue> getDialogues() {
        return dialogues;
    }

    public void setDialogues(ArrayList<Dialogue> dialogues) {
        this.dialogues = dialogues;
    }

    public int getStage() {
        return stage;
    }

    public void advance() {
        if (stage < dialogues.size() - 1) {
            stage++;
            if (stage == dialogues.size() - 1) {
                onFinished();
            }
        }
    }

    public void advance(int stage) {
        if (stage < dialogues.size()) {
            this.stage = stage;
        }
    }
}
