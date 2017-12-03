package game.gui;

import game.Entity;
import game.gui.elements.FlowLayout;
import game.gui.elements.List;
import game.gui.elements.Orientation;
import game.gui.elements.TextView;
import game.story.Dialogue;
import game.story.IDialogueOption;
import game.story.Storyline;

import java.util.ArrayList;
import java.util.Arrays;

public class StorylineGUI extends FlowLayout {
    public Storyline storyline;

    private TextView textViewDialogue;
    private List<IDialogueOption> listOptions;
    private Dialogue currentDialogue;

    public StorylineGUI(Entity owner, Storyline storyline) {
        super(Orientation.VERTICAL);

        this.storyline = storyline;

        textViewDialogue = new TextView();
        textViewDialogue.setWrapped(true);
        textViewDialogue.setWrappedLength(25);

        listOptions = new List<>();
        listOptions.setHandler((item, index) -> {
            item.onSelected(storyline, owner);
        });

        addElement(textViewDialogue);
        addElement(listOptions);
    }

    @Override
    public void update() {
        super.update();

        if (currentDialogue != storyline.getCurrentDialogue()) {
            currentDialogue = storyline.getCurrentDialogue();
            Dialogue dialogue = storyline.getCurrentDialogue();

            listOptions.setModel(new ArrayList<>(Arrays.asList(dialogue.getOptions())));

            textViewDialogue.setText(dialogue.getText());
        }
    }

    @Override
    public void onKey(boolean[] keys) {
        super.onKey(keys);
        listOptions.onKey(keys);
    }
}
