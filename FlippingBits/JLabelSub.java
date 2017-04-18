import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.BorderFactory;

/**
 * Create labels for the buttons in the board configuration
 */
public class JLabelSub
{
    private final Color BORDER_COLOR = Color.BLACK;
    
    JLabel label;
    
    /**
     * Constructor for label with nothing inside
     */
    public JLabelSub(){
        label = new JLabel("");
        label.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
    }

    /**
     * Constructor for objects of class JLabelSub
     * @params board - the Flipping Bits Board to get the value from
     * @params i - the row position in the board
     * @params j - the column position in the board
     */
    public JLabelSub(FlippingBitsBoard board, int i, int j)
    {
        label = new JLabel(Integer.toString(board.getValue(i, j)));
        label.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
    }
    
    public JLabel getLabel(){
        return label;
    }
    
    public void setLabel(JLabel label){
        this.label = label;
    }
}
