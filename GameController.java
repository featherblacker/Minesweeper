import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.awt.*;
import javax.swing.*;


/**
 * The class <b>GameController</b> is the controller of the game. It is a listener
 * of the view, and has a method <b>play</b> which computes the next
 * step of the game, and  updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 * 
 * @author Qiguang Chu  300042722
 * 
 * @since 2018/02/24
 */


public class GameController implements MouseListener {
	private int width;
	private int height;
	private int numberOfMines;
	private GameModel game;
	private GameView gameview;
	private JFrame show;
	private GenericArrayStack<DotInfo> emptyDot;
	private GameView view;
	JPanel myPanel1;
	JPanel myPanel2;
	JLabel st ;
	JButton quit, playagain;
	

    /**
     * Constructor used for initializing the controller. It creates the game's view 
     * and the game's model instances
     * 
     * @param width
     *            the width of the board on which the game will be played
     * @param height
     *            the height of the board on which the game will be played
     * @param numberOfMines
     *            the number of mines hidden in the board
     */
    public GameController(int width, int height, int numberOfMines) {
    	myPanel1 = new JPanel();
    	myPanel1.setLayout (new BorderLayout());
    	this.width=width;
    	this.height=height;
    	this.numberOfMines=numberOfMines;
    	game=new GameModel(width, height, numberOfMines);
    	game.reset();
    	System.out.println(game.toString());
    	show = new JFrame();
    	show.setSize(300, 200);
    	show.setLocationRelativeTo(null);
    	
    	
    	myPanel2 = new JPanel();
    	
        quit=new JButton("Quit");
        quit.setBackground(Color.white);
        quit.addMouseListener(this);
      
        playagain=new JButton("Play Again");
        playagain.setBackground(Color.blue);
        playagain.addMouseListener(this);
        
    	view =new GameView(game, this);
    	view.update();

    }


    /**
     * Callback used when the user clicks a button (reset or quit)
     *
     * @param e
     *            the ActionEvent
     */

    public void mouseEntered(MouseEvent e) {
    	    
    }

    /**
     * <b>reset</b> is used to resets the game
     */
    private void reset(){
    	game.reset();
    	view.update();
    }
    

    /**
     * <b>play</b> is the method called when the user clicks on a square.
     * If that square is not already clicked, then it applies the logic
     * of the game to uncover that square, and possibly end the game if
     * that square was mined, or possibly uncover some other squares. 
     * It then checks if the game
     * is finished, and if so, congratulates the player, showing the number of
     * moves, and gives to options: start a new game, or exit
     * @param width
     *            the selected column
     * @param heigth
     *            the selected line
     */
    private void play(int width, int heigth){
    	String nbreOfSteps = Integer.toString(game.getNumberOfSteps()+1);
    	
    	if(game.isMined(heigth, width)) {
    		game.uncoverAll();
    		game.click(heigth, width);
        	show.setTitle("Boom!");
        	myPanel1.removeAll();
        	String output = "<html><p>Aouch! You lose in " + nbreOfSteps + " steps!</p></p>Would you like to play again?</p></html>";
        	st = new JLabel(output);
        	myPanel1.add(st);
        	show.add(myPanel1, BorderLayout.CENTER);
            myPanel2.add(quit);
            myPanel2.add(playagain);
            show.add(myPanel2,BorderLayout.SOUTH);
            
            JPanel myPanel3 = new JPanel();
            JLabel label = new JLabel();
            ImageIcon img = new ImageIcon("icons/timg.jpg");
            label.setIcon(img);
            myPanel3.add(label);
            show.add(myPanel3, BorderLayout.WEST);
            
            show.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            show.setVisible(true);
    		return;
    		
    	}
    	
    	clearZone(game.get(heigth, width));
    	if(game.isFinished()) {
//    		game.uncoverAll();
        	show.setTitle("Won");     	
        	myPanel1.removeAll();
        	String output = "<html><p>Congratulations! </p></p>You won in " + nbreOfSteps + " steps! Would you like to play again?</p></html>";
        	
        	JLabel st = new JLabel(output);
        	myPanel1.add(st);
        	show.add(myPanel1, BorderLayout.CENTER);
        	
            myPanel2.add(quit);
            myPanel2.add(playagain);
            show.add(myPanel2,BorderLayout.SOUTH);
            
            JPanel myPanel3 = new JPanel();
            JLabel label = new JLabel();
            ImageIcon img = new ImageIcon("icons/timg.jpg");
            label.setIcon(img);
            myPanel3.add(label);
            show.add(myPanel3, BorderLayout.WEST);
            
            show.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            show.setVisible(true);

    	}
    }
    

   /**
     * <b>clearZone</b> is the method that computes which new dots should be ``uncovered'' 
     * when a new square with no mine in its neighborhood has been selected
     * @param initialDot
     *      the DotInfo object corresponding to the selected DotButton that
     * had zero neighboring mines
     */
    private void clearZone(DotInfo initialDot) {
    	DotInfo current;
    	DotInfo temp;
    	int[] s= {1,0,-1};
    	emptyDot=new GenericArrayStack<DotInfo>(width*height);
    	emptyDot.push(initialDot);
    	while (!emptyDot.isEmpty()) {
    		current=emptyDot.pop();
    		if(current.isCovered()) game.uncover(current.getX(), current.getY());
    		if(current.getNeighbooringMines()==0) {
	    		for (int a = 0; a < 3; a++) {
					for (int b = 0; b < 3; b++) {
						int x=current.getX() + s[a];
						int y=current.getY() + s[b];
						if (x < width && x>= 0 && y < height && y  >= 0) {	
							temp=game.get(x, y);
							if (!temp.isMined()&&temp.isCovered() &&!temp.getFlag()) {
								game.uncover(x,y);
								if(temp.getNeighbooringMines()==0) {
									emptyDot.push(game.get(x, y));
								}
							 
							}
							
						}
					}
	    		}
    		}
    	}
    }


@Override
public void mouseClicked(MouseEvent e) {
	String x1=((JButton)e.getSource()).getText();
	if( x1.equals("Reset")|| x1.equals("Play Again")) {
		show.setVisible(false);
		reset();
		System.out.println("\n\n");
		System.out.println(game.toString());
		return;
	}
	else if(x1.equals("Quit")) {
		System.exit(1);
	}
	if(e.getSource() instanceof DotButton) {
	DotButton src=(DotButton) e.getSource();
	int x=src.getRow();
	int y=src.getColumn();
	if (e.getButton()==MouseEvent.BUTTON3) {
		if (e.getSource()instanceof DotButton) {
			
			if (game.getFlag(y, x)) game.clearFlag(y, x);
			else game.setFlag(y, x);
			view.update();	
			
		}
		
		
	}
	else if(e.getButton()==MouseEvent.BUTTON1 && (!game.getFlag(y, x))) {
	if (e.getSource()instanceof DotButton) {
		if(game.isCovered(y, x)) {
    		play(x,y);
    		game.step();
        	view.update();
		}
	}
	}
	// TODO Auto-generated method stub
	}
}


@Override
public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	
}


@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	
}


@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

}
