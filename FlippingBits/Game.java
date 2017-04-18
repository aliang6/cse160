import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 * Start a new game
 */
public class Game
{
    // Size of the board
    private int boardSize = 5;

    // The configurations of the board
    private FlippingBitsBoard target, current;

    // Frame
    final private JFrame frame;

    // The different panels in the frame
    private JPanel panel;
    private JPanel leftButtons;
    private JPanel rightDisplay;
    private JPanel targetConfig;
    private JPanel currentConfig;
    private JPanel currentConfigBoard;

    // JLabel for moves
    private JLabel movesMessage;

    // Number of moves made
    private int moves;

    public Game(){
        frame = new JFrame("Flipping Bits!");
        target = new FlippingBitsBoard(boardSize, true);
        current = new FlippingBitsBoard(target);
        current.shuffle(1);
        moves = 0;
        movesMessage = new JLabel("Moves so far: " + moves);
    }

    private Game(int size){
        frame = new JFrame("Flipping Bits!");
        boardSize = size;
        target = new FlippingBitsBoard(size, true);
        current = new FlippingBitsBoard(target);
        current.shuffle(1);
        moves = 0;
        movesMessage = new JLabel("Moves so far: " + moves);
    }

    private Game(int size, FlippingBitsBoard target, FlippingBitsBoard current, int moves){
        frame = new JFrame("Flipping Bits!");
        boardSize = size;
        this.target = target;
        this.current = current;
        this.moves = moves;
        movesMessage = new JLabel("Moves so far: " + moves);
    }

    private void setup(){
        panel = new JPanel(new BorderLayout());
        leftButtons = new JPanel(new GridLayout(2,1));
        panel.add(leftButtons, BorderLayout.WEST);
        JButton newBoard = new JButton("New board");
        ActionListener newBoardListener = (new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        Game win = new Game();
                        win.setup();
                        /*Game win = new Game();
                        targetConfig = new BoardPanel(target, true).getBoard();
                        rightDisplay.remove(1);
                        rightDisplay.add(targetConfig, 1);
                        frame.validate();
                        frame.repaint();*/
                    }});
        newBoard.addActionListener(newBoardListener);
        leftButtons.add(newBoard);
        JButton changeSize = new JButton("Change Size");
        ActionListener changeSizeListener = (new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        String inputValue = JOptionPane.showInputDialog("Enter Board Size");
                        try{
                            Integer.parseInt(inputValue);
                        } catch (Exception exeption) {
                            JOptionPane.showMessageDialog(frame, ("Invalid number"));
                            return;
                        }
                        int size = Integer.parseInt(inputValue);
                        frame.dispose();
                        Game win = new Game(size);
                        win.setup();
                    }});
        changeSize.addActionListener(changeSizeListener);
        leftButtons.add(changeSize);
        rightDisplay = new JPanel(new GridLayout(5, 1));
        panel.add(rightDisplay, BorderLayout.EAST);
        rightDisplay.add(new JLabel("Target Configuration"));
        targetConfig = new BoardPanel(target, true).getBoard();
        rightDisplay.add(targetConfig);
        rightDisplay.add(new JLabel("Current Configuration"));
        currentConfig = new BoardPanel(current, false).getBoard();

        updateCurrentBoard();

        rightDisplay.add(currentConfigBoard);
        rightDisplay.add(movesMessage);
        frame.getContentPane().add(panel);

        frame.getContentPane().setPreferredSize(new Dimension(500, 800));
        frame.pack();
        frame.setVisible(true);
    }

    private void updateCurrentBoard(){
        int panelLength = this.boardSize + 2;
        currentConfigBoard = new JPanel(new GridLayout(panelLength, panelLength));
        for(int i = 0; i < panelLength; i++){
            for(int j = 0; j < panelLength; j++){
                if((i == 0 || i == panelLength - 1) && (j == 0 || j == panelLength - 1)){
                    currentConfigBoard.add(new JLabelSub().getLabel());
                }
                else if(i == 0 || j == 0 || i == panelLength - 1 || j == panelLength - 1){
                    JButton button = new JButton();
                    if(i == 0 || i == panelLength - 1){
                        final int col = j - 1;
                        ActionListener buttonListener = (new ActionListener(){
                                    public void actionPerformed(ActionEvent e) {
                                        current.flipCol(col);
                                        currentConfig = new BoardPanel(current, false).getBoard();
                                        moves++;
                                        frame.dispose();
                                        Game updatedGame = new Game(boardSize, current, target, moves);
                                        updatedGame.setup();
                                        if(current.matches(target)){
                                            JOptionPane.showMessageDialog(frame, ("You win!"));
                                        }
                                        /*updateCurrentBoard();
                                        rightDisplay.remove(movesMessage);
                                        moves++;
                                        System.out.println(moves);
                                        rightDisplay.add(movesMessage);
                                        frame.validate();
                                        frame.repaint();*/
                                    }

                                });
                        button.addActionListener(buttonListener);
                    }
                    else{
                        final int row = i - 1;
                        ActionListener buttonListener = (new ActionListener(){
                                    public void actionPerformed(ActionEvent e) {
                                        current.flipRow(row);
                                        currentConfig = new BoardPanel(current, false).getBoard();
                                        moves++;
                                        frame.dispose();
                                        Game updatedGame = new Game(boardSize, current, target, moves);
                                        updatedGame.setup();
                                        if(current.matches(target)){
                                            JOptionPane.showMessageDialog(frame, ("You win!"));
                                        }
                                        /*updateCurrentBoard();
                                        moves++;
                                        rightDisplay.remove(movesMessage);
                                        rightDisplay.add(movesMessage);
                                        rightDisplay.validate();
                                        rightDisplay.repaint();
                                        frame.validate();
                                        frame.repaint();*/
                                    }

                                });
                        button.addActionListener(buttonListener);
                    }
                    currentConfigBoard.add(button);
                }
                else{
                    currentConfigBoard.add(new JLabelSub(current, i - 1, j - 1).getLabel());
                }
            }
        }
    }

    public void play(){
        Game win = new Game();
        win.setup();
    }

    public static void main(String[] args){
        Game win = new Game();
        win.setup();
    }
}
