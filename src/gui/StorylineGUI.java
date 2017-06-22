package gui;

import game.Dialogue;
import game.IDialogueOption;
import game.Storyline;

public class StorylineGUI extends FlowLayout {
    public Storyline storyline;

    private TextView textViewDialogue;
    private List<String> listOptions;
    private int i;
    private int frame = 0;
    private boolean animationDone = false;
    private boolean itemsAdded = false;

    public StorylineGUI(Storyline storyline) {
        super(Orientation.VERTICAL);

        textViewDialogue = new TextView();
        this.storyline = storyline;
        Dialogue dialogue = storyline.getCurrentDialogue();

        textViewDialogue.setWrapped(true);
        textViewDialogue.setWrappedLength(40);

        listOptions = new List<>();

        addElement(textViewDialogue);
        addElement(listOptions);
    }

    @Override
    public void update() {
        super.update();

        frame++;
        Dialogue dialogue = storyline.getCurrentDialogue();
        if (frame >= 3 && i < dialogue.getText().length()) {
            i++;
            frame = 0;
            textViewDialogue.setText(dialogue.getText().substring(0, i));
            if (i == dialogue.getText().length()) animationDone = true;
        }

        if (animationDone && !itemsAdded) {
            for (IDialogueOption option : dialogue.getOptions()) {
                listOptions.getModel().add(option.getOptionLabel());
            }
            itemsAdded = true;
        }
    }
}
