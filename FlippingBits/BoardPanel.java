import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * Creates the board panel for the board configuration
 */
public class BoardPanel
{
    // Panel for main board
    private JPanel panel;
    
    /**
     * Constructor for objects of class BoardPanel
     * @params FlippingBitsBoard - a flipping bits board
     * @params target - boolean value of whether the board is a target board
     */
    public BoardPanel(FlippingBitsBoard board, boolean target)
    {
        int panelLength = board.getSize() + 2;
        panel = new JPanel(new GridLayout(panelLength, panelLength));
        if(target){
            for(int i = 0; i < panelLength; i++){
                for(int j = 0; j < panelLength; j++){
                    if(i == 0 || j == 0 || i == panelLength - 1 || j == panelLength - 1){
                        panel.add(new JLabelSub().getLabel());
                    }
                    else{
                        panel.add(new JLabelSub(board, i - 1, j - 1).getLabel());
                    }
                }
            }
        }
        
        else{
            for(int i = 0; i < panelLength; i++){
                for(int j = 0; j < panelLength; j++){
                    if((i == 0 || i == panelLength - 1) && (j == 0 || j == panelLength - 1)){
                        panel.add(new JLabelSub().getLabel());
                    }
                    else if(i == 0 || j == 0 || i == panelLength - 1 || j == panelLength - 1){
                        panel.add(new JButton());
                    }
                    else{
                        panel.add(new JLabelSub(board, i - 1, j - 1).getLabel());
                    }
                }
            }
        }
    }
    
    public JPanel getBoard(){
        return panel;
    }
}
