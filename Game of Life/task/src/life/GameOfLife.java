package life;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.lang.reflect.InvocationTargetException;

public class GameOfLife extends JFrame {

    private final JLabel generation = new JLabel();
    private final JLabel alive = new JLabel();
    private final JPanel[][] cells;
    public boolean play = true;

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

        //button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        JToggleButton button = new JToggleButton("Play");
        button.setName("PlayToggleButton");
        button.addItemListener(itemEvent -> {
            int state = itemEvent.getStateChange();
            if (state == ItemEvent.SELECTED) {
                button.setText("Pause");
                play = false;
            } else {
                button.setText("Play");
                play = true;
            }
        });

        JButton resetButton = new JButton("Reset");
        resetButton.setName("ResetButton");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GameOfLifeProcess.reset = true;
            }
        });

        buttonPanel.add(button);
        buttonPanel.add(resetButton);

        //control panel
        JPanel control = new JPanel();
        control.setLayout(new BoxLayout(control, BoxLayout.Y_AXIS));
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        statistic.setAlignmentX(Component.LEFT_ALIGNMENT);
        control.add(statistic);
        control.add(buttonPanel);

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
        add(control, BorderLayout.NORTH);
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

    public boolean isPlay() {
        return play;
    }
}
