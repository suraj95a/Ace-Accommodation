/**
 * This class is for Cabins by Ashim
 * @author Ashim Khadar
 */

public class AshimCabin extends AceAccomm //implements Comparable<AshimCabin>
{
	private int [] beds;
	private boolean breakfast; //Additional Attribute for this project 
	private boolean seaview; //Additional Attribute for this project 

	public AshimCabin (int num, Name oName, int nDays,  boolean bf, boolean sv, int [] b) 
	{
		super(num, oName, nDays);	//Calling the constructor of the super-class
		breakfast = bf;
		seaview = sv;
		beds = b;
	}
	
	
	public double  getCost()
	{
		double cost=0;
		int sumbeds = getNumOfBeds();
		if(sumbeds == 2) {
			cost = 125;
		}
		else if(sumbeds == 4) {
			cost = 150;
		}
		else if(sumbeds == 6) {
			cost = 175;
		}
		else if(sumbeds == 8) {
			cost = 200;
		}
		if(breakfast == true)
		{
			cost = cost + (5*sumbeds);
		}
		if(seaview == true)
		{
			cost = cost +25;
		}
		return cost;
	}
	
	public String getFullDetails() {
		String report="";
		report += String.format("%-5d",getAcNumber());
		report += String.format("%-24s",getOwnerName().getFullName());
		report += String.format("%-7d",beds.length);
		report += String.format("%-6d",getNumOfBeds());
		report += String.format("%-6d",getNumOfDays());
		report += String.format("%-11s",getBreakfast());
		report += String.format("%-11s",getSeaView());
		double costpd=getCost();
		report += String.format("%-12.2f",costpd);
		double totalCost=costpd*getNumOfDays();
		report += String.format("%-12.2f",totalCost);
		report += "\n";
		return report;
	}	
		
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

	public int getNumOfBeds() {
		int sumbeds = 0;
			{
			for(int i=0 ; i<beds.length; i++) {
			sumbeds= sumbeds +beds[i];
			}
		return sumbeds;
		}

	}
	
	public String getBreakfast() {
		String report;
			if(breakfast == true)
				report="Yes";
			else
				report="No";
		return report;
		}
	
	public String getSeaView() {
		String report;
			if(seaview == true)
				report="Yes";
			else
				report="No";
		return report;
		}
	
    /**
     * Test for content equality between two objects.
     * @param other The object to compare to this one.
     * @return true if the argument object has same id
     */
    public boolean equals(Object other)
    {
        if(other instanceof AshimCabin) 
        {
        	AshimCabin otherSuite = (AshimCabin) other;
            return acNumber==otherSuite.getAcNumber();
        }
        else 
        {	return false;	}
    }
  
}
