import java.util.Random;


/**
 * The class <b>GameModel</b> holds the model, the state of the systems. 
 * It stores the following information:
 * - the state of all the ``dots'' on the board (mined or not, clicked
 * or not, number of neighboring mines...)
 * - the size of the board
 * - the number of steps since the last reset
 *
 * The model provides all of this informations to the other classes trough 
 *  appropriate Getters. 
 * The controller can also update the model through Setters.
 * Finally, the model is also in charge of initializing the game
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 * 
 * @author Qiguang Chu 300042722
 * 
 * @since 2018/02/24
 */
public class GameModel {
	
	private int width;
	private int height;
	private int numberOfMines;
	private DotInfo[][] board;
	private int step;
	private int numberOfUncover;



    /**
     * Constructor to initialize the model to a given size of board.
     * 
     * @param width
     *            the width of the board
     * 
     * @param height
     *            the height of the board
     * 
     * @param numberOfMines
     *            the number of mines to hide in the board
     */
    public GameModel(int width, int height, int numberOfMines) {
    	this.width=width;
    	this.height=height;
    	this.numberOfMines=numberOfMines;
    	
        board=new DotInfo[height][width];
        step=0;
        numberOfUncover=height*width-numberOfMines;
    	for(int i=0;i<width;i++) {
    		for(int j=0;j<height;j++) {
    			board[j][i]=new DotInfo(i, j);
    		}
    	}
    }


    /**
     * Resets the model to (re)start a game. The previous game (if there is one)
     * is cleared up . 
     */
    public void reset(){
    	int temp;
    	int[] s= {1,0,-1};
    	for(int i=0;i<width;i++) {
    		for(int j=0;j<height;j++) {
    			board[j][i]=new DotInfo(i, j);
    		}
    	}
    	numberOfUncover=height*width-numberOfMines;
    	step=0;
    	Random posx=new Random();
    	Random posy=new Random();
    	int x1;
    	int y1;
		for (int i = 0; i < numberOfMines;) {
			x1 = posx.nextInt(width);
			y1 = posy.nextInt(height);
			if (!board[y1][x1].isMined()) {
				board[y1][x1].setMined();
				i++;
				for (int a = 0; a < 3; a++) {
					for (int b = 0; b < 3; b++) {
						if ((x1 + s[a]) < width && (x1 + s[a]) >= 0 && (y1 + s[b]) < height && (y1 + s[b]) >= 0) {
							temp = board[y1 + s[b]][x1 + s[a]].getNeighbooringMines();
							board[y1 + s[b]][x1 + s[a]].setNeighbooringMines(temp + 1);
						}
					}
				}
			}
		}
		
    }

    
    /**
     * returns true if the flag at location (i,j) is mined, false otherwise
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean getFlag(int i,int j) {
    	return board[j][i].getFlag();
    }
    
    
    /**
     * set flag at location (i,j)
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void setFlag(int i,int j) {
    	board[j][i].setFlag();
    
    }
    
    
    /**
     * clean the flag at location (i,j)
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void clearFlag(int i,int j) {
    	board[j][i].concealFlag();
    }
    
    
    /**
     * 
     *Getter method for the height of the game
     * 
     * @return the value of the attribute heightOfGame
     */   
    public int getHeight(){
        return height;
        
    }
    
    
    /**
     * Getter method for the width of the game
     * 
     * @return the value of the attribute widthOfGame
     */   
    public int getWidth(){
    	return width;
		
    }

    /**
     * returns true if the dot at location (i,j) is mined, false otherwise
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isMined(int i, int j){
    	return board[j][i].isMined();

    }
    

    /**
     * returns true if the dot  at location (i,j) has 
     * been clicked, false otherwise
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean hasBeenClicked(int i, int j){
    	return board[j][i].hasBeenClicked();

    }

    
  /**
     * returns true if the dot  at location (i,j) has zero mined 
     * neighbor, false otherwise
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isBlank(int i, int j){
    	return this.getNeighbooringMines(i, j)==0;

    }
    
    
    /**
     * returns true if the dot is covered, false otherwise
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isCovered(int i, int j){
    	return board[j][i].isCovered();
    	
    }

    
    /**
     * returns the number of neighboring mines os the dot  
     * at location (i,j)
     *
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the number of neighboring mines at location (i,j)
     */   
    public int getNeighbooringMines(int i, int j){
    	if (board[j][i].isMined())return 9;
    	return board[j][i].getNeighbooringMines();

    }


    /**
     * Sets the status of the dot at location (i,j) to uncovered
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void uncover(int i, int j){
    	board[j][i].uncover();
    	numberOfUncover--;

    }
    

    /**
     * Sets the status of the dot at location (i,j) to clicked
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void click(int i, int j){
    	board[j][i].click();

    }
    
    
     /**
     * Uncover all remaining covered dot
     */   
    public void uncoverAll(){
        for(int i=0;i<width;i++) {
        	for(int j=0;j<height;j++) {
        		board[j][i].uncover();
        	}
        }
    }

 
    /**
     * Getter method for the current number of steps
     * 
     * @return the current number of steps
     */   
    public int getNumberOfSteps(){
    	return step;

    }

  

    /**
     * Getter method for the model's dotInfo reference
     * at location (i,j)
     *
      * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     *
     * @return model[i][j]
     */   
    public DotInfo get(int i, int j) {
        return board[j][i];
        
    }


   /**
     * The method <b>step</b> updates the number of steps. It must be called 
     * once the model has been updated after the payer selected a new square.
     */
     public void step(){
    	 step++;

    }
 
     
   /**
     * The method <b>isFinished</b> returns true iff the game is finished, that
     * is, all the non mined dots are uncovered.
     *
     * @return true if the game is finished, false otherwise
     */
    public boolean isFinished(){
    	return (numberOfUncover==0);

    }
    


   /**
     * Builds a String representation of the model
     *
     * @return String representation of the model
     */
    public String toString(){
    	String x="";
    	for (int i=0;i<height;i++) {
    		for(int j=0;j<width;j++) {
    			x=x+Integer.toString(board[i][j].label)+" ";
    		}
    		x=x+"\n";
    	}
    	x=x+"\n";
    	System.out.print(x);
    	x="";
    	for (int i=0;i<height;i++) {
    		for(int j=0;j<width;j++) {
    			x=x+Integer.toString(board[i][j].getNeighbooringMines())+" ";
    		}
    		x=x+"\n";
    	}
    	return x;

    }

}

