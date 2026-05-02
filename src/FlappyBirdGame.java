import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FlappyBirdGame extends JFrame {

    int birdY = 150;
    int birdVelocity = 0;
    int gravity = 2;
    boolean gameRunning = true;
    int score = 0;

    Timer timer;
    int pipeX;
    int pipeWidth = 100;
    int pipeGap = 200;
    int pipeGapY = 200;
    final int PIPE_SEGMENT_HEIGHT = 400;

    final int BIRD_SIZE = 80;
    final int BIRD_X = 80;
    final int MAX_ROTATION_UP = -25;
    final int MAX_ROTATION_DOWN = 45;

    Image birdImage;
    Image BackgroundImage;
    Image PipaAtas;
    Image PipaBawah;

    GamePanel panel;

    public FlappyBirdGame() {
        setTitle("Flappy Bird By Krishna");
        setIconImage(new ImageIcon(getClass().getResource("/bird.png")).getImage());
        setSize(360, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        addKeyListener(new BirdKeyListener());
        setFocusable(true);
        setLocationRelativeTo(null);

        pipeX = getWidth();
        BackgroundImage = new ImageIcon(getClass().getResource("/bg.png")).getImage();
        PipaAtas = new ImageIcon(getClass().getResource("/b-pipa-atas.png")).getImage();
        PipaBawah = new ImageIcon(getClass().getResource("/b-pipa-bawah.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("/bird.png")).getImage();

        panel = new GamePanel(this);
        add(panel);

        timer = new Timer(20, e -> {
            update();
            panel.repaint();
        });

        timer.start();
    }

    private void randomizePipeGap() {
        int minGapY = 80;
        int maxGapY = getHeight() - pipeGap - 80;
        pipeGapY = (int) (Math.random() * (maxGapY - minGapY)) + minGapY;
    }

    private void update() {
        if (gameRunning) {
            birdVelocity += gravity;
            birdY += birdVelocity;

            pipeX -= 5;
            if (pipeX + pipeWidth < 0) {
                pipeX = getWidth();
                randomizePipeGap();
                score++;
            }

            if (birdY + BIRD_SIZE > getHeight() || birdY < 0) {
                gameOver();
                return;
            }

            boolean inPipeRange = pipeX < BIRD_X + BIRD_SIZE && pipeX + pipeWidth > BIRD_X;
            boolean hitTopPipe = birdY < pipeGapY;
            boolean hitBottomPipe = birdY + BIRD_SIZE > pipeGapY + pipeGap;

            if (inPipeRange && (hitTopPipe || hitBottomPipe)) {
                gameOver();
            }
        }
    }

    private void gameOver() {
        gameRunning = false;
        timer.stop();
        int option = JOptionPane.showConfirmDialog(this,
            "Game Over! Your Score: " + score + "\nDo you want to play again?",
            "Game Over", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            restartGame();
        } else {
            System.exit(0);
        }
    }

    private void restartGame() {
        birdY = 150;
        birdVelocity = 0;
        gameRunning = true;
        score = 0;
        pipeX = getWidth();
        randomizePipeGap();
        timer.start();
    }

    private class BirdKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                birdVelocity = -20;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {}
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FlappyBirdGame().setVisible(true));
    }
}

class GamePanel extends JPanel {
    FlappyBirdGame game;

    GamePanel(FlappyBirdGame game) {
        this.game = game;
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Latar belakang
        g.drawImage(game.BackgroundImage, 0, 0, getWidth(), getHeight(), null);

        // Cast ke Graphics2D untuk rotasi
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Hitung sudut rotasi dari velocity, clamp agar tidak berlebihan
        double angle = Math.toRadians(
            Math.max(game.MAX_ROTATION_UP,
            Math.min(game.MAX_ROTATION_DOWN, game.birdVelocity * 3))
        );

        // Titik tengah burung sebagai pusat rotasi
        int birdCenterX = game.BIRD_X + game.BIRD_SIZE / 2;
        int birdCenterY = game.birdY + game.BIRD_SIZE / 2;

        // Simpan state transform, rotasi, gambar burung, lalu restore
        g2d.rotate(angle, birdCenterX, birdCenterY);
        g2d.drawImage(game.birdImage, game.BIRD_X, game.birdY, game.BIRD_SIZE, game.BIRD_SIZE, this);
        g2d.rotate(-angle, birdCenterX, birdCenterY);

        // Pipa atas
        g2d.drawImage(game.PipaAtas,
            game.pipeX,
            game.pipeGapY - game.PIPE_SEGMENT_HEIGHT,
            game.pipeWidth,
            game.PIPE_SEGMENT_HEIGHT,
            null);

        // Pipa bawah
        g2d.drawImage(game.PipaBawah,
            game.pipeX,
            game.pipeGapY + game.pipeGap,
            game.pipeWidth,
            game.PIPE_SEGMENT_HEIGHT,
            null);

        // Skor
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("serif", Font.BOLD, 20));
        g2d.drawString("Score: " + game.score, 130, 60);
    }
}