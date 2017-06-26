package game;

public interface IDialogueOption {
    String toString();

    void onSelected(Storyline storyline, Entity entity);
}
