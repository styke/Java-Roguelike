import game.*;
import game.Renderer;
import game.components.TileRenderable;
import game.gui.elements.Element;

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
    private boolean[] keys;
    private Context context;

    private Main() {
        keys = new boolean[1024];
        addKeyListener(this);
        context = new Context();

        renderer = new Renderer(1280, 720);
        world = new World(renderer.getWidth() / renderer.getTileSize(), renderer.getHeight() / renderer.getTileSize());
        world.setContext(context);

        Entity testEntity = new Entity() {
            @Override
            public void initComponents() {
                //addComponent(new Positionable(EntityType.ENTITY));
                TileRenderable component = new TileRenderable();
                component.setSpriteX(2);
                component.setSpriteY(0);
                addComponent(component);
            }

            @Override
            public void render(Renderer renderer) {
                super.render(renderer);
                getComponent(TileRenderable.class).render(renderer);
            }
        };

        world.addEntity(testEntity);
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
        frame.requestFocus();
        main.requestFocus();

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
            createBufferStrategy(3);
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

        if (context.getGameState() == GameState.POPUP) {
            renderer.renderPopup();
        }

        bufferStrategy.show();
        g.dispose();
    }

    private void input() {
        // TODO: Implement keybindings
        GameState gameState = context.getGameState();

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
        GameState gameState = context.getGameState();

        if (context.hasRequestedPopup()) {
            Element element = context.getRequestedPopup();
            if (renderer.getPopup().getDisplay() == null) {
                context.clearRequest();
                renderer.getPopup().setDisplay(element);
                context.setGameState(GameState.POPUP);
            }
        }

        if (renderer.getPopup().getDisplay() == null) {
            context.setGameState(GameState.PLAYING);
            world.update();
        } else {
            context.setGameState(GameState.POPUP);
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
