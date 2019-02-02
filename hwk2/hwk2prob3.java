import java.util.ArrayList;
import java.util.Arrays;

class Relative
{
	Node node;
	String action;
	Relative(Node n, String a)
	{
		this.node = n;
		this.action = a;
	}
}

class Parent extends Relative
{
	Parent(Node n, String a)
	{
		super(n, a);
	}
}


class Child extends Relative
{	
	Child(Node n, String a)
	{
		super(n, a);
	}
}

class Node
{
	//fields
	//(i)
	private int state[] = {1, 2, 3, 8, 0, 4, 7, 6, 5};

	//(ii)
	private Parent parent;

	//(iii) 
	//// No. of displaced tiles; (no. tiles not in desired location)
	private int hDsiplaced;	

	// The block distance sum of block distances 
	// of tiles from desired location
	private int hManhattan; 

	//gVal of parent + 1
	private int gVal;



	//(iv)
	//list of child nodes
	ArrayList<Child> children = new ArrayList<Child>();

	
	//Constructor
	Node( int _state[], Parent _parent )
	{
		if (_state == null)
		{//initial state
			this.parent = null;

		}
		else
		{
			this.state = _state;
			this.parent = _parent;
		}
	}

	//Methods
	//(v)
	//True if states in the two node objects are equal
	public static boolean equalState(Node n1, Node n2)
	{//static context
		return Arrays.equals(n1.state, n2.state);
	}

	public boolean eqaulState(Node n)
	{//compare with self
		return Arrays.equals(n.state, this.state);
	}


	public int[] getState()
	{
		return this.state;
	}


	public int[] getPosition(int num)
	{	
		boolean gotit=false;
		int resp[] = {0, 0};
		for( int ii=0; ii<3; ii++ )
		{
			for(int jj=0; jj<3; jj++)
			{
				if( this.state[3*ii+jj] == num )
				{
					resp[0] = ii;
					resp[1] = jj;
					return resp;
				}
			}	
		}
		throw new IndexOutOfBoundsException("num not in state array");
	}

	public int[] changeState(String action)
	{
		int pos[] = this.getPosition();
		int ii = pos[0];
		int jj = pos[1];
		
		int newState[] = this.state.clone();
		switch (action)
		{
			case "U":
				if( ii == 0)
				{
				      return null;
				}
				else
				{
				
				      newState[(3*(ii-1))+jj] = 0;
				      newState[3*ii+jj] = this.state[((3*(ii-1))+jj)];
				}
			      	break;

			case "D":
				if( ii == 2)
				{
				      return null;
				}
				else
				{
				
				      newState[(3*(ii+1))+jj] = 0;
				      newState[3*ii+jj] = this.state[(3*(ii+1)+jj)];
				}
			      	break;
		      case "L":
				if( jj == 0)
				{
				      return null;
				}
				else
				{
				      newState[(3*ii)+jj-1] = 0;
				      newState[3*ii+jj] = this.state[((3*ii)+jj-1)];
				}
			      	break;

		      case "R":
				if( jj == 2)
				{
				      return null;
				}
				else
				{
				      newState[(3*ii)+jj+1] = 0;
				      newState[3*ii+jj] = this.state[((3*ii)+jj+1)];
				}
			      	break;
		}
		return newState;
	}

	public String getStringState()
	{
		String retn=new String();
		for( int ii=0; ii<3; ii++ )
		{
			for(int jj=0; jj<3; jj++)
			{
				retn+=state[3*ii+jj]+" ";
			}
			
			retn+="\n";
		}
		
		return retn;
	}

	public void expand()
	{
		String actions[] = {"U", "D", "L", "R"};
		
		for(String act : actions)
		{
			int newState[] = this.changeState(act);
			if(newState  == null)
				continue;

			Node dummy = new Node(newState, new Parent(this, act));
			dummy.state = newState;
			this.children.add(new Child( dummy, act ));
		}
		
	}

	public ArrayList<Child> getChildren()
	{
		return this.children;
	}

	public String toString()
	{
		String retn="";
		retn+=this.getStringState();
		return "";
	}

	public int[] getPosition()
	{
		return this.getPosition(0);
	}

	public void printPosition(int num)
	{
		int pos[] = this.getPosition(num);
		System.out.println("[ "+pos[0]+", "+pos[1]+" ]");
	}

	public void printPosition()
	{
		int pos[] = this.getPosition();
		System.out.println("[ "+pos[0]+", "+pos[1]+" ]");
	}


	//End Methods

	//nested classes
	//end nested classes 
	/*
	public static void main(String[] args)
	{

		
	}*/
}


//Driver
public class hwk2prob3
{
	public static void main(String[] args)
	{
		Node node = new Node(null, null);
		System.out.println(node.getStringState());
		node.printPosition(8);
		node.expand();
		System.out.println(node.getChildren().get(0).node.getStringState());

	}
}
