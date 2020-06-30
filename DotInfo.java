
/**
 * The class <b>DotInfo</b> is a simple helper class to store 
 * the state (e.g. clicked, mined, number of neighbooring mines...) 
 * at the dot position (x,y)
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 * 
 * @author Qiguang Chu  300042722
 * 
 * @since 2018/02/24
 */

public class DotInfo {
	private int x;
	private int y;
	public int label;
	private int neighbooringMines;
	private boolean clicked;
	private boolean cover;
	private boolean rightclicked;
	private boolean flag;



    /**
     * Constructor, used to initialize the instance variables
     * 
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     */
    public DotInfo(int x, int y){
    	this.x=x;
    	this.y=y;
    	label=0;
    	clicked=false;
    	cover=true;
    	neighbooringMines=0;
    	flag=false;

    }
    
    
    /**
     * Getter for flag
     *
     * @return flag
     */
    public boolean getFlag() {
    	return flag;
    }

    
    /**
     * Setter for flag
     */
    public void setFlag() {
    	flag=true;
    }
    
    
    /**
     * Setter for clearing flag
     */
    public void concealFlag() {
    	flag=false;
    }
    
    
    /**
     * Getter method for the attribute x.
     * 
     * @return the value of the attribute x
     */
    public int getX(){
    	return x;

    }
    
    
    /**
     * Getter method for the attribute y.
     * 
     * @return the value of the attribute y
     */
    public int getY(){
    	return y;

    }
    
 
    /**
     * Setter for mined
     */
    public void setMined() {
    	label=1;

    }

    /**
     * Getter for mined
     *
     * @return mined
     */
    public boolean isMined() {
    	return  (label==1) ;

    }


    /**
     * Setter for covered
     */
    public void uncover() {
    	cover=false;

    }

    /**
     * Getter for covered
     *
     * @return covered
     */
    public boolean isCovered(){
    	return cover;

    }



    /**
     * Setter for wasClicked
     */
    public void click() {
    	clicked=true;

    }


    /**
     * Getter for wasClicked
     *
     * @return wasClicked
     */
    public boolean hasBeenClicked() {
    	return clicked;

    }


    /**
     * Setter for neighbooringMines
     *
     * @param neighbooringMines
     *          number of neighboring mines
     */
    public void setNeighbooringMines(int neighbooringMines) {
    	this.neighbooringMines=neighbooringMines;

    }

    /**
     * Get for neighbooringMines
     *
     * @return neighbooringMines
     */
    public int getNeighbooringMines() {
    	return neighbooringMines;
    }
    

 }

