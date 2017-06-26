package game;

public class Dialogue {
    private String text;
    private IDialogueOption[] options;

    public Dialogue() {
        text = "";
        options = new IDialogueOption[]{};
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public IDialogueOption[] getOptions() {
        return options;
    }

    public void setOptions(IDialogueOption[] options) {
        this.options = options;
    }
}
