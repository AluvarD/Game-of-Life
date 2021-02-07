package life;

import javax.swing.*;
import java.awt.*;

public class GameOfLife extends JFrame {

    private JLabel generation = new JLabel();
    private JLabel alive = new JLabel();
    private JPanel[][] cells;

    public GameOfLife() {
        super("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);

        //statistic
        JPanel statistic = new JPanel();
        statistic.setLayout(new BoxLayout(statistic, BoxLayout.Y_AXIS));
        generation.setName("GenerationLabel");
        alive.setName("AliveLabel");
        generation.setText("Generation #1");
        alive.setText("Alive: 0");
        statistic.add(generation);
        statistic.add(alive);

        //universe
        JPanel universeView = new JPanel();
        universeView.setLayout(new GridLayout(30, 30));
        cells = new JPanel[30][30];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                JPanel cell = new JPanel();
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                universeView.add(cell);
                cells[i][j] = cell;
            }
        }

        setLayout(new BorderLayout());
        add(statistic, BorderLayout.NORTH);
        add(universeView, BorderLayout.CENTER);
        setVisible(true);
    }

    public void setGeneration(int generationCount) {
        generation.setText("Generation #" + generationCount);
    }

    public void setAlive (int aliveCount) {
        alive.setText("Alive: " + aliveCount);
    }

    public void setUniverse (char[][] universe) {
        for (int i = 0; i < universe.length; i ++) {
            for (int j = 0; j < universe[i].length; j++) {
                if (universe[i][j] == 'O') {
                    cells[i][j].setBackground(Color.BLACK);
                } else {
                    cells[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }
}
