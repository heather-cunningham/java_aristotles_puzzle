public class Tile
{
	private boolean placed;
	private int value;
	
	public Tile()
	{
   	placed = false;
   	value = 0;	
	}//end default constructor
	
	
	public Tile(boolean placedPassed, int valuePassed)
	{
		placed = placedPassed;
		value = valuePassed;	
	}//end non-default constructor
   
   
//------------------------SETTERS----------------------		
	public void setPlaced(boolean placedPassed)
   {
	   placed = placedPassed;	
	}//end method placedPassed
	
	public void setValue(int valuePassed)
	{
	   value = valuePassed;
	}//end method setPlace
	
   
//------------------------GETTERS----------------------			
	public int getValue()
	{
		return value;
	}//end getValue
	
	public boolean getPlaced()
	{
	   return placed;
	}//end getPlaced
	
	public String toString()
	{
		return "Placed: " + placed +
					"\nValue: " + value;
	}//end toString
	
//------------------------Other Methods----------------------			
	public boolean equals(Tile tilePassed)
   {
      return (this.value == tilePassed.value &&
              this.placed == tilePassed.placed);
   }//end toString
	
}//end class Tile