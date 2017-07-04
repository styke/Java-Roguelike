import entities.Door;
import entities.Grass;
import entities.Player;
import entities.Wall;
import game.GameState;
import game.Renderer;
import game.World;
import gui.Element;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import static java.awt.event.KeyEvent.VK_ESCAPE;

public class Main extends Canvas implements Runnable, KeyListener {

    private boolean running;
    private Thread thread;
    private BufferStrategy bufferStrategy;
    private Renderer renderer;
    private World world;
    private Player player;
    private boolean[] keys;
    private GameState gameState; // TODO: Propagate game state to all entities, instead of singleton.

    private Main() {
        keys = new boolean[1024];
        addKeyListener(this);
        gameState = GameState.getInstance();

        renderer = new Renderer(1280, 720);
        world = new World(renderer.getWidth() / renderer.getTileSize(), renderer.getHeight() / renderer.getTileSize());
        player = new Player();
        player.setPosition(2, 2);

        for (int i = 5; i < 10; i++) {
            for (int j = 5; j < 10; j++) {
                Grass w = new Grass();
                w.setPosition(i, j);
                world.addEntity(w);
            }
        }

        for (int i = 5; i < 10; i++) {
            Wall w = new Wall();
            w.setPosition(i, 5);
            world.addEntity(w);
        }

        Door door = new Door(false);
        door.setPosition(7, 5);
        world.addEntity(player);
        world.addEntity(door);

        world.onInitGUI(renderer.getRootLayout(), renderer.getPopup());
    }

    public static void main(String[] args) {
        Dimension dimension = new Dimension(1280, 720);

        JFrame frame = new JFrame("Game");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);

        Main main = new Main();
        main.setMinimumSize(dimension);
        main.setPreferredSize(dimension);
        main.setMaximumSize(dimension);

        frame.add(main);
        frame.pack();
        frame.setVisible(true);

        main.start();
    }

    private void start() {
        thread = new Thread(this);
        running = true;
        thread.start();
    }

    private void stop() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private BufferStrategy prepareBufferStrategy() {
        BufferStrategy bufferStrategy = getBufferStrategy();
        if (bufferStrategy == null) {
            createBufferStrategy(4);
            return prepareBufferStrategy();
        }
        return bufferStrategy;
    }

    private void render() {
        Graphics g = bufferStrategy.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        renderer.setGraphics(g);
        world.render(renderer);

        renderer.renderRootLayout();

        if (gameState == GameState.POPUP) {
            renderer.renderPopup();
        }

        bufferStrategy.show();
        g.dispose();
    }

    private void input() {
        // TODO: Implement keybindings
        if (gameState == GameState.PLAYING) {
            world.onKey(keys);
        } else if (gameState == GameState.POPUP) {
            if (keys[VK_ESCAPE]) {
                renderer.getPopup().setDisplay(null);
            } else {
                renderer.getPopup().onKey(keys);
            }
        }
    }

    private void update() {
        if (gameState.hasRequestedPopup()) {
            Element element = gameState.getRequestedPopup();
            if (renderer.getPopup().getDisplay() == null) {
                gameState.clearRequest();
                renderer.getPopup().setDisplay(element);
                gameState = GameState.POPUP;
            }
        }

        if (renderer.getPopup().getDisplay() == null) {
            gameState = GameState.PLAYING;
            world.update();
        } else {
            gameState = GameState.POPUP;
            renderer.updatePopup();
        }

        renderer.updateRootLayout();
    }

    @Override
    public void run() {
        bufferStrategy = prepareBufferStrategy();
        long old;
        long now = System.currentTimeMillis();
        double accum = 0;
        double factor = 1000 / 60.0;

        while (running) {
            old = now;
            now = System.currentTimeMillis();
            long delta = now - old;
            accum += delta;
            boolean updated = false;

            while (accum >= factor) {
                accum -= factor;
                update();
                updated = true;
            }

            if (updated) {
                render();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        input();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        input();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
        input();
    }
}
