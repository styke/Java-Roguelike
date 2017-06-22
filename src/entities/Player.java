package entities;

import game.*;
import gui.*;

import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.VK_E;

public class Player extends Entity {

    private int HP;
    private int maxHP;
    private int speed;
    private int moveCooldown;
    private int interactCooldown;
    private Inventory inventory;

    private StorylineGUI storylineGUI;
    private PlayerGUI playerGUI;
    private InventoryGUI inventoryGUI;

    public Player() {
        inventory = new Inventory(8);

        playerGUI = new PlayerGUI(this);
        inventoryGUI = new InventoryGUI(inventory);

        Storyline storyline = new Storyline() {
            @Override
            public void onFinished() {

            }
        };
        Dialogue dialogue = new Dialogue();
        dialogue.setOptions(new IDialogueOption[]{
                new DialogueOptionAdvance(), new DialogueOptionAdvance("And why is that?")});
        dialogue.setText("Oh... a new wanderer. If you think this is the time to show your heroic character, maybe it's better to turn around and leave.\n" +
                "Ahh... whatever, maybe you'll end up just like me.");
        storyline.getDialogues().add(dialogue);

        storylineGUI = new StorylineGUI(storyline);

        HP = 10;
        maxHP = 20;
        speed = 8;
        moveCooldown = 0;
        interactCooldown = 0;

        playerGUI.updateData();

        // TEST
        Item it = new Item() {
            @Override
            public String getName() {
                return "Staff";
            }
        };
        Item it2 = new Item() {
            @Override
            public String getName() {
                return "Staff 2";
            }
        };

        inventory.add(new ItemStack(it, 5));
        inventory.add(new ItemStack(it2, 1));
        inventory.add(new ItemStack(it2, 2));
        inventory.add(new ItemStack(it2, 3));
        //
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    @Override
    public void onInitGUI(FlowLayout root, Popup popup) {
        root.addElement(playerGUI);
        popup.setDisplay(storylineGUI);
    }

    @Override
    public void onKey(boolean[] keys) {
        if (moveCooldown == 0) {
            int dx = 0;
            int dy = 0;
            if (keys[KeyEvent.VK_A]) {
                dx -= 1;
            }
            if (keys[KeyEvent.VK_D]) {
                dx += 1;
            }
            if (keys[KeyEvent.VK_W]) {
                dy -= 1;
            }
            if (keys[KeyEvent.VK_S]) {
                dy += 1;
            }

            if (dx != 0 || dy != 0) {
                int nx = dx + getX();
                int ny = dy + getY();
                if (canMoveTo(nx, ny)) {
                    setPosition(nx, ny);
                    moveCooldown = speed;
                }
            }
        }

        if (interactCooldown == 0) {
            int dx = 0;
            int dy = 0;
            if (keys[KeyEvent.VK_LEFT]) {
                dx -= 1;
            }
            if (keys[KeyEvent.VK_RIGHT]) {
                dx += 1;
            }
            if (keys[KeyEvent.VK_UP]) {
                dy -= 1;
            }
            if (keys[KeyEvent.VK_DOWN]) {
                dy += 1;
            }

            if (dx != 0 || dy != 0) {
                int nx = dx + getX();
                int ny = dy + getY();
                Entity e = getWorld().getEntity(nx, ny, EntityType.TILE);
                if (e != null) {
                    e.onInteract(this);
                    interactCooldown = 10;
                }
            }
        }

        if (keys[VK_E]) {
            GameState.getInstance().requestPopupDisplay(inventoryGUI);
        }
    }

    private boolean canMoveTo(int nx, int ny) {
        if (getWorld().inBounds(nx, ny)) {
            if (!getWorld().isEntityAt(nx, ny, EntityType.ENTITY)) {
                Entity e = getWorld().getEntity(nx, ny, EntityType.TILE);
                return e == null || e.canPass(this);
            }
        }
        return false;
    }

    @Override
    public EntityType getType() {
        return EntityType.ENTITY;
    }

    @Override
    public void update() {
        if (moveCooldown > 0) {
            moveCooldown--;
        }

        if (interactCooldown > 0) {
            interactCooldown--;
        }
    }

    @Override
    public void render(Renderer renderer) {
        renderer.drawChar(getX(), getY(), '@');
    }
}
