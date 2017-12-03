package game.story;

import game.Entity;

public interface IDialogueOption {
    String toString();

    void onSelected(Storyline storyline, Entity entity);
}
