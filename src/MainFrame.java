import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private JPanel optionPanel;
    private Board board;

    private String elements = "BINARY AVLTREE";

    private void initMainPanel() {
        board = new Board((int) ((this.getWidth() - 200) / 2 - 17.5), 15);
        board.setBackground(new Color(193, 191, 188));
        board.setBorder(new LineBorder(new Color(48, 56, 58), 2));
        this.add(board, BorderLayout.CENTER);
    }

    private void initHeadPanel() {
        JPanel headPanel = new JPanel();
        headPanel.setPreferredSize(new Dimension(200, 30));
        headPanel.setBorder(new LineBorder(new Color(48, 56, 58), 2));
        headPanel.setBackground(new Color(249, 175, 57));
        JLabel headLabel = new JLabel("BINARY AVLTREE");
        headLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headPanel.add(headLabel);
        this.add(headPanel, BorderLayout.NORTH);
    }

    private void showWarring() {
        JOptionPane.showOptionDialog(this,
                elements, "WARRING", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, null, "default");
    }

    private void initRadioPanel() {
        JPanel radioPanel = new JPanel();
        radioPanel.setBorder(new LineBorder(Color.BLACK, 1));
        radioPanel.setPreferredSize(new Dimension(150, 100));
        radioPanel.setBackground(new Color(249, 175, 57));
        radioPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel head = new JLabel("INSERT");
        radioPanel.add(head);
        JTextField textInsert = new JTextField(10);
        radioPanel.add(textInsert);
        ActionListener actionListener = e -> {
            if (!textInsert.getText().matches("-?[0-9]+")) {
                elements = "You can only enter integers";
                this.showWarring();
                return;
            }
            board.insertNode(Integer.parseInt(textInsert.getText()));
            repaint();
        };
        JButton buttonInsert = new JButton("summit");
        buttonInsert.addActionListener(actionListener);
        radioPanel.add(buttonInsert);
        optionPanel.add(radioPanel);

        JPanel removePanel = new JPanel();
        removePanel.setBorder(new LineBorder(Color.BLACK, 1));
        removePanel.setPreferredSize(new Dimension(150, 100));
        removePanel.setBackground(new Color(249, 175, 57));
        removePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel remove = new JLabel("REMOVE");
        removePanel.add(remove);
        JTextField textRemove = new JTextField(10);
        removePanel.add(textRemove);
        ActionListener listener = e -> {
            if (!textRemove.getText().matches("-?[0-9]+")) {
                elements = "You can only enter integers";
                this.showWarring();
                return;
            }
            board.removeNode(Integer.parseInt(textRemove.getText()));
            repaint();
        };
        JButton buttonRemove = new JButton("summit");
        buttonRemove.addActionListener(listener);
        removePanel.add(buttonRemove);
        optionPanel.add(removePanel);

        JPanel travelPanel = new JPanel();
        travelPanel.setBorder(new LineBorder(Color.BLACK, 1));
        travelPanel.setPreferredSize(new Dimension(150, 70));
        travelPanel.setBackground(new Color(249, 175, 57));
        travelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel travel = new JLabel("PRINT TREE");
        travelPanel.add(travel);
        ActionListener aL = e -> {
            elements = board.toString();
            this.showWarring();
        };
        JButton buttonTravel = new JButton("summit");
        buttonTravel.addActionListener(aL);
        travelPanel.add(buttonTravel);
        optionPanel.add(travelPanel);
    }

    private void initOptionPanel() {
        optionPanel = new JPanel();
        optionPanel.setPreferredSize(new Dimension(200, 300));
        optionPanel.setBorder(new LineBorder(Color.DARK_GRAY, 2));
        optionPanel.setBackground(new Color(48, 56, 58));
        initRadioPanel();
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        optionPanel.add(exitButton);
        ActionListener actionListener = e -> {
            board.clearBoard();
            repaint();
        };
        JButton clear = new JButton("Clear");
        clear.addActionListener(actionListener);
        optionPanel.add(clear);
        this.add(optionPanel, BorderLayout.WEST);
    }

    private MainFrame(String s) {
        super(s);
        setSize(1200, 800);
        this.setLayout(new BorderLayout());
        initMainPanel();
        initHeadPanel();
        initOptionPanel();
        setVisible(true);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame("AVLTree");
    }
}
