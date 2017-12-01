package game.entities;

import game.*;
import game.components.Positionable;
import gui.InventoryGUI;
import gui.PlayerGUI;
import gui.StorylineGUI;
import gui.elements.FlowLayout;

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

    @Override
    public void initComponents() {
        addComponent(new Positionable(EntityType.ENTITY));
    }

    public Player() {
        super();
        inventory = new Inventory(8);
        inventoryGUI = new InventoryGUI(inventory);
        inventory.add(new ItemStack(new Item() {
            @Override
            public String getName() {
                return "Health Potion";
            }

            @Override
            public void onConsumed(Entity sender) {
                super.onConsumed(sender);
                // TODO: Entity component system
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.setHP(player.getMaxHP());
                }
            }
        }, 3));

        HP = 10;
        maxHP = 20;
        speed = 4;
        moveCooldown = 0;
        interactCooldown = 0;

        playerGUI = new PlayerGUI(this);
        playerGUI.updateData();
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
    public void onInitGUI(FlowLayout root) {
        root.addElement(playerGUI);
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
        renderer.drawSprite(getX(), getY(), 2, 0);
    }
}
