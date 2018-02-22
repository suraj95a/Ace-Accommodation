/**
 * This class is for Campsite made by Aliyu  
 * @author Aliyu
 */

public class AliyuCampsite extends AceAccomm //implements Comparable<AliyuCampsite>
{ 
    private int numberOfBeds;	// min 1 and max 2
    private boolean washroom;	// true or false;
    private int picnicTables;	// min 1 and max 2

    private static final double CAMPSITEPRICE = 300;        
    private static final double BEDPRICE = 150;          
    private static final double WASHROOMPRICE= 300;        
    private static final double PICNICTABLEPRICE= 100;  
   

    public AliyuCampsite(int num, int nb, int nDays, boolean w, int p, Name oName)
    {
    	super(num, oName, nDays);	//Calling the constructor of the super-class
        this.numberOfBeds=nb;
        this.washroom=w;
        this.picnicTables=p;
    }   
    
    public void setNumberOfBeds(int beds)
    {
        this.numberOfBeds=beds;
    }

    public int getNumOfBeds()
    {
        return this.numberOfBeds;
    }
    
    public void setWashroom(boolean washroom)
    {
        this.washroom=washroom;
    }
   
    public boolean getWashroom()
    {
        return this.washroom;
    }

    public void setpicnicTables(int tables)
    {
        this.picnicTables=tables;
    }

    public int getpicnicTables()
    {
        return this.picnicTables;
    }

    public double getCost()
    {
        double costPerDay=CAMPSITEPRICE; 
        
        costPerDay+=(numberOfBeds*BEDPRICE);  

        if(washroom)                   
        {
            costPerDay+=WASHROOMPRICE;
        }

           costPerDay+=(picnicTables*PICNICTABLEPRICE);
       
        return costPerDay;
    }

    public String getFullDetails()
    {
        String output="";
        output += String.format("%-5d",getAcNumber());
        output += String.format("%-6d",getNumOfBeds());
        output += String.format("%-6d",getNumOfDays());
        output += String.format("%-10s",getWashroom()?"Yes":"No");
        output += String.format("%-14d",getpicnicTables());
        output += String.format("%-23s",getOwnerName().getFullName());
        double costpd=this.getCost();
		output += String.format("%-12.2f",costpd);
       
        double totalCost=costpd*numOfDays;
		output += String.format("%-12.2f",totalCost);
		output +="\n";
        return output;
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
    /**
     * Test for content equality between two objects.
     * @param other The object to compare to this one.
     * @return true if the argument object has same id
     */
    public boolean equals(Object other)
    {
        if(other instanceof AliyuCampsite) 
        {
        	AliyuCampsite otherSuite = (AliyuCampsite) other;
            return acNumber==otherSuite.getAcNumber();
        }
        else 
        {	return false;	}
    }
    
}