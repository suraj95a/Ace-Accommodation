/**
 * AceAccommodation is the super-class of all kinds of accommodation such as HotelSuites, Cabins and Campsite
 * @author Suraj Sivaprasad
 * @author Ashim Abdul Khadar
 * @author Aliyu Abubakar Makarfi
 */

abstract public class AceAccomm implements Comparable<AceAccomm>
{
	//Common instance variables for all types of Accommodation
	protected int acNumber;		//The unique number of Accommodation
	protected Name ownerName; 	//The Owner of the Accommodation of type Name Class
	protected int numOfDays;	//The number of days the Suite has been booked by the guest.

	public AceAccomm(int num, Name oName, int nDays)
	{
		acNumber=num;
		ownerName=oName;
		numOfDays=nDays;
	}

	//The get methods for AceAccommodation Class
	public int getAcNumber()   
	{	return acNumber;	}

	public Name getOwnerName()  	
	{	return ownerName;	} 

	public int getNumOfDays()
	{	return numOfDays; }

	//The set methods for AceAccommodation Class
	public void setAcNumber(int an)   
	{	acNumber=an;	}

	public void setOwnerName(Name on)  	
	{	ownerName=on;	}    

	public void setNumOfDays (int nod)
	{	numOfDays=nod;	}

	//Abstract Methods of Accommodation Class that is implemented in sub-classes
	public abstract double getCost();
	public abstract String getFullDetails();
	public abstract String getShortDetails();
	public abstract int getNumOfBeds();

	/**
	 * Compare this Accomm object against another, for the sorting by number.
	 * @param other The other Accomm object to be compared against.
	 * @return a negative integer, if this Accomm's number comes before the other Accomm's number
	 *         zero, if they are both equal
	 *         a positive integer, if this Accomm's number comes after the other Accomm's number
	 */
	public int compareTo(AceAccomm other)
	{
		Integer thisNumber = new Integer(acNumber);
		Integer otherNumber = new Integer(other.getAcNumber());
		return thisNumber.compareTo(otherNumber);
	} 

	/**
	 * Override toString() method of parent Object Class
	 */
	public String toString()
	{
		return String.format("%-5d",acNumber )+String.format("%-26s", ownerName.getFullName());
	}
}
