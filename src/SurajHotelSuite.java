/**
 * SurajHotelSuite Class is used to represent the Suites of Suraj Hotel.
 * @author Suraj Sivaprasad
 */
public class SurajHotelSuite extends AceAccomm //implements Comparable<SurajHotelSuite>
{

	//instance variables
	private String suiteType;  	   //The Type of Suite. Either "Deluxe" or "Royal"
	private int numOfRooms;		   //The number of rooms in the suite (1=Single, 2=Double, 3=Triple)
	private int [] bed; 	   	   //Integer Array holding the number of beds in each room. (An Extra room can have 0 beds).
	private int numOfGuests;       //The number of Guests & Beds in a Suite, since each Guest uses one Bed.
	private boolean gymSpaAccess;  /*Paid access to the Hotel's Gym and Spa services.
	 *Swimming Pool is free for all Suraj Hotel guests!*/
	private boolean lunchDinner;   /*Paid Reservation for lunch & dinner at Restaurant.
	 *Breakfast is complimentary for all guests!*/
	private int numParkingSpots;   //The Number of parking spots reserved for vehicles of the guests
	private double creditMiniBar;   //Pre-paid user-determined credit loaded on MiniBar Dispenser card

	private static final double DELUXEPRICE = 500;    	//Price for One Deluxe Suite Room for 1 day
	private static final double ROYALPRICE = 800;		//Price for One Royal Suite Room for 1 day
	private static final double BEDCOST = 75;			//Cost per Bed (One bed for each Guest) 
	private static final double GYMSPACOST= 150;		//Cost of Gym & Spa Access  for 1 day
	private static final double LUNCHDINNERCOST= 50;	//Cost per person for Lunch & Dinner at Hotel Restaurant
	private static final double PARKINGCOST= 25;		//Cost per Vehicle Parking Space per day

	/**A constructor for creating a SurajHotelSuite Object
	 * using the values passed in the parameters.
	 * @param num       The Unique number of each Suite in the Hotel.
	 * @param sType      The Type of Suite. Either "Deluxe" or "Royal".
	 * @param nRooms 	 The number of rooms in the suite (1=Single, 2=Double, 3=Triple).
	 * @param oName      The Owner of the Suite of type Name Class.
	 * @param nDays	 	 The number of days the Suite has been booked.
	 * @param gymSpaAxs  Paid access to the Hotel's Gym and Spa services.
	 * @param food       Paid Reservation for lunch & dinner at Restaurant.
	 * @param nPark      The Number of parking spots reserved for vehicles of the guests.
	 * @param cMBar      Pre-paid user-determined credit loaded on MiniBar Dispenser card.
	 * @param b		 	 Integer Array holding the number of beds in each room. (An extra room can have 0 beds).
	 */
	public SurajHotelSuite(int num, String sType, int nRooms, Name oName, int nDays, boolean gymSpaAxs, boolean food, int nPark, double cMBar, int [] b)
	{        
		super(num, oName, nDays);	//Calling the constructor of the super-class
		suiteType=sType;  
		numOfRooms=nRooms;
		gymSpaAccess=gymSpaAxs; 
		lunchDinner=food;  
		numParkingSpots=nPark;
		creditMiniBar=cMBar;
		bed=b;

		//Number of Guests in a Suite can be determined from the requested bed arrangement.
		//Since each Guest uses one Bed, No. of Guests = Total number of beds.
		numOfGuests=0;		
		for(int i=0;i<bed.length;i++)   
		{	numOfGuests+=bed[i];	}
	}

	//The get methods for SurajHotelSuite Class
	public String getSuiteType() 	
	{	return suiteType; }

	public int getNumOfRooms()
	{	return numOfRooms; }

	/**
	 * Returns the Integer Array holding the number of beds in each room of the suite.
	 * Note that this method does NOT return the total number of beds in the Suite. 
	 * There is another method for that called getNumOfBeds().
	 * @return Integer Array holding the number of beds in each room.
	 */
	public int [] getBed()  
	{	return bed; }

	public int getNumOfGuests()   	
	{	return numOfGuests;	}

	public boolean getGymSpaAccess() 
	{	return gymSpaAccess;	} 

	public boolean getLunchDinner()
	{	return lunchDinner;	}

	public int getNumParkingSpots()
	{	return numParkingSpots;	}

	public double getCreditMiniBar()
	{	return creditMiniBar;	}

	//The set methods for SurajHotelSuite Class
	public void setSuiteType(String st) 	
	{	suiteType=st; }

	public void setNumOfRooms(int nor)
	{	numOfRooms=nor;	}

	public void setBed(int [] b)   	
	{	
		bed=b;
		//Number of Guests in a Suite can be determined from the number of beds required.
		//Since each Guest uses one Bed, No. of Guests= Total number of beds.
		numOfGuests=0;		
		for(int i=0;i<bed.length;i++)   
		{	numOfGuests+=bed[i];	}
	}

	public void setGymSpaAccess(boolean gsa) 
	{	gymSpaAccess=gsa;	} 

	public void setLunchDinner(boolean ld)
	{	lunchDinner=ld;	}

	public void setNumParkingSpots(int nps)
	{	numParkingSpots=nps;	}

	public void setCreditMiniBar(double cmb)
	{	creditMiniBar=cmb;	} 

	/**
	 * A method to return the total number of beds in the Suite.
	 * @return Number of Beds in the Suite
	 */
	public int getNumOfBeds()   	
	{
		return numOfGuests;	 //The number of Guests & Beds in a Suite is the same, since each Guest uses one Bed.
		//See implementation of how numOfGuests is calculated in the constructor...
	}

	/**
	 * Returns the cost of this suite (in Double) for a single day.
	 * For Hotel bookings and prices, each new 24 hours "day" begins at 12PM.
	 * The pricing remains the same all-year round (no seasonal pricing changes).
	 * @return Cost of suite for one day 
	 */
	public double getCost()
	{
		double costPerDay=0;                //variable to hold the cost of this suite for one day 
		if (suiteType.equals("Deluxe"))		//For Deluxe Suites
		{	costPerDay=DELUXEPRICE;	}
		else if (suiteType.equals("Royal")) //For Royal Suites
		{	costPerDay=ROYALPRICE;	}

		costPerDay*=numOfRooms;				//Factoring in the number of Rooms (Single, Double, Triple) 
		costPerDay+=(numOfGuests*BEDCOST);  //Adding the bed cost for each guest in the Hotel Room

		if(gymSpaAccess)					//If the guest want to access Gym & Spa Facilities
		{	costPerDay+=GYMSPACOST;	}
		if(lunchDinner)						//If Guests opt for Lunch & Dinner in Hotel Restaurant
		{	costPerDay+=(numOfGuests*LUNCHDINNERCOST);	}

		costPerDay+=(numParkingSpots*PARKINGCOST);   //Add Parking Charge if guests have vehicles in Hotel Parking
		costPerDay+=creditMiniBar;					//Adding MiniBar credit requested by Guest
		return costPerDay;
	}

	/**
	 * A method that for getting a string with full Suite details.
	 * @return A String containing full details about the Hotel Suite
	 */
	public String getFullDetails()
	{
		String report="";
		report += String.format("%-5d",getAcNumber());
		report += String.format("%-24s",getOwnerName().getFullName());
		report += String.format("%-8s",getSuiteType());
		report += String.format("%-7d",getNumOfRooms());
		report += String.format("%-6d",getNumOfBeds());
		report += String.format("%-6d",getNumOfDays());
		report += String.format("%-9s",getGymSpaAccess()?"Yes":"No");
		report += String.format("%-14s",getLunchDinner()?"Yes":"No");
		report += String.format("%-9s",getNumParkingSpots());
		report += String.format("%-12.2f",getCreditMiniBar());
		double costpd=getCost();
		report += String.format("%-12.2f",costpd);
		double totalCost=costpd*getNumOfDays();
		report += String.format("%-12.2f",totalCost);
		report += "\n";
		return report;
	}

	/**
	 * A method that for getting a string with basic minimum Suite details.
	 * @return A String containing short details about the Hotel Suite
	 */
	public String getShortDetails()
	{
		String report="";
		report += String.format("%-5d",getAcNumber());
		report += String.format("%-15s",getOwnerName().getInitials());
		report += String.format("%-6d",getNumOfBeds());
		double costpd=getCost();
		report += String.format("%-12.2f",costpd);
		double totalCost=costpd*getNumOfDays();
		report += String.format("%-12.2f",totalCost);
		report += "\n";
		return report;
	}

	/**
	 * Test for content equality between two objects.
	 * @param other The object to compare to this one.
	 * @return true if the argument object has same id
	 */
	public boolean equals(Object other)
	{
		if(other instanceof SurajHotelSuite) 
		{
			SurajHotelSuite otherSuite = (SurajHotelSuite) other;
			return acNumber==otherSuite.getAcNumber();
		}
		else 
		{	return false;	}
	}
}