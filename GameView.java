import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.ImageIcon;


/**
 * The class <b>GameView</b> provides the current view of the entire Game. It extends
 * <b>JFrame</b> and lays out a matrix of <b>DotButton</b> (the actual game) and 
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 * 
 * @author Qiguang Chu  300042722
 * 
 * @since 2018/02/24
 */

public class GameView extends JFrame {
	private DotButton[][] map;
	private GameController gameController;
	private GameModel gameModel;
	private JLabel count;
	JPanel myPanel1;
	JPanel myPanel2;

     // ADD YOUR INSTANCE VARIABLES HERE


    /**
     * Constructor used for initializing the Frame
     * 
     * @param gameModel
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */
    public GameView(GameModel gameModel, GameController gameController) {
        this.gameModel=gameModel;
        this.gameController=gameController;
        myPanel1 = new JPanel();
        myPanel1.setLayout (new GridLayout(gameModel.getHeight(),gameModel.getWidth()));
        myPanel2 = new JPanel();
        myPanel2.add(new Label("Number of steps:"));
		count=new JLabel ();
		myPanel2.add(count);
		JButton reset, quit;
		
		reset=new JButton("Reset");
        reset.addMouseListener(gameController);
        myPanel2.add(reset);
        quit=new JButton("Quit");
        quit.addMouseListener(gameController);
        myPanel2.add(quit);
    }

    

	/**
     * update the status of the board's DotButton instances based 
     * on the current game model, then redraws the view
     */
    public void update(){
    	
    	setTitle("MineSweeper it -- The ITI1121 version");
    	setSize(620, 460);
        myPanel1.removeAll();
    	add(myPanel1, BorderLayout.CENTER);
    	setVisible(true);
    	map=new DotButton[gameModel.getHeight()][gameModel.getWidth()];
        myPanel1.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
		DotButton button;
		for (int i=0;i<gameModel.getHeight();i++) {
			for(int j=0;j<gameModel.getWidth();j++) {
				int num=gameModel.getNeighbooringMines(j, i);
				button=new DotButton(j, i, num);
				map[i][j]=button;
				button.addMouseListener( gameController);
				if(gameModel.isCovered(j, i)) {
					num=11;
					}
				if (gameModel.hasBeenClicked(j, i)) {
					num=10;
					}
				else if(gameModel.isCovered(j, i) && gameModel.getFlag(j, i)) num=12;
				button.setIconNumber(num);
				myPanel1.add(button);	
				
			}
		}		
        add(myPanel2,BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
    	count.setText(Integer.toString(gameModel.getNumberOfSteps()));

    }

    /**
     * returns the icon value that must be used for a given dot 
     * in the game
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the icon to use for the dot at location (i,j)
     */   
    private int getIcon(int i, int j){
    	return map[j][i].iconNumber;
    }


}
