package gui;

import entities.Player;

public class PlayerGUI extends FlowLayout {
    private FlowLayout layoutHP;
    private TextView textViewHP;
    private ProgressBar progressBarHP;
    private Player player;

    public PlayerGUI(Player player) {
        super(Orientation.VERTICAL);
        this.player = player;

        layoutHP = new FlowLayout(Orientation.HORIZONTAL);
        textViewHP = new TextView("HP ");
        progressBarHP = new ProgressBar(0, 10);
        layoutHP.addElement(textViewHP);
        layoutHP.addElement(progressBarHP);

        addElement(layoutHP);
    }

    public void updateData() {
        progressBarHP.setProgress(player.getHP() / (double) player.getMaxHP());
    }
}
