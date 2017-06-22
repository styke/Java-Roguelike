package game;

public interface IDialogueOption {
    String getOptionLabel();

    void onSelected(Storyline storyline, Entity entity);
}
