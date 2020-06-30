/**
 * @author Qiguang Chu  300042722
 * 
 * @since 2018/02/24
 */

public class GenericArrayStack<E> implements Stack<E> {
   Object[] stack;
   int tail;

   // Constructor
    public GenericArrayStack( int capacity ) {
    	 stack =new Object[capacity];
    	 tail=0;

    }

    // Returns true if this ArrayStack is empty
    public boolean isEmpty() {
    	return (tail==0);

    }

    public void push( E elem ) {
        tail++;
        stack[tail-1]=elem;

    }
    public E pop() {
    	tail--;
    	Object temp=stack[tail];
    	stack[tail]=null;
    	return (E)temp;


    }

    public E peek() {
    	return (E)stack[tail];

    }
}
