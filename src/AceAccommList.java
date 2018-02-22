import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

/**
 * AceAccommList Class is used to hold an ArrayList of all types of Accommodation.
 * @author Suraj Sivaprasad
 * @author Ashim Abdul Khadar
 * @author Aliyu Abubakar Makarfi
 */
public class AceAccommList
{
	private ArrayList <AceAccomm> acList;	//ArrayList to hold a list of Accommodations

	/**
	 * Constructor for initialising the ArrayList of Accommodations.
	 * No parameters are passed, hence the ArrayList of Accommodations is empty (doesn't have any content)
	 */
	public AceAccommList() 
	{
		acList = new ArrayList<AceAccomm> ();
	}

	/**
	 * Returns the Accommodation object at specified index position of the List.
	 * @param index The index position of the accommodation object in the list.
	 * @return Accommodation object at specified index position
	 */
	public AceAccomm getAtIndex(int index) 
	{	return acList.get(index);	}
	
	/**
	 * @param c AceAccomm object passed as parameter
	 * @return index of AceAccomm object in the ArrayList Of Accommodations
	 */
	public int getIndexOf(AceAccomm c)
	{
		  return acList.indexOf(c);
	}

	/**
	 * Replace an Accommodation object at an index
	 * @param index Index position of object to be replaced
	 * @param s		new AceAccomm object to set in index position
	 */
	public void replaceAtIndex(int index, AceAccomm s)
	{
		acList.set(index, s);
	}
	/**
	 * Returns the current number (size) of Accommodations in the list.
	 * @return number of Accommodations currently in the list
	 */
	public int getSize() 
	{	return acList.size();	}

	/**
	 * Searches the list of accommodations and returns the Accommodation object with the specified accommodation number.
	 * If no corresponding accommodation is found, a null object is returned.
	 * @param num The Accommodation Number
	 * @return the accommodation object (if found), corresponding to the accommodation number passed, otherwise null.
	 */
	public AceAccomm findByNum(int num)
	{
		for (AceAccomm s : acList)
		{
			if (s.getAcNumber()==num)
			{	return s;	}
		}
		return null;    //No accommodation object was found
	}

	/**
	 * Adds Accommodations to the list, if there isn't an existing accommodation with the same accommodation number.
	 * @param s the AceAccommodation object to be added to the list
	 * @return Boolean true, if accommodation was successfully added to the list, otherwise false
	 */
	public boolean addOneAccommodation(AceAccomm s)
	{
		int num = s.getAcNumber();					//gets the Accommodation number of the accommodation to be added
		AceAccomm inList = this.findByNum(num);	//Check for a accommodation with the same acNumber in the list

		if (inList == null)		//add the accommodation to the list if there is no problem, and return Boolean true
		{
			acList.add(s);
			return true;
		}
		return false;		//return false, if the add has been unsuccessful due to duplicate accommodation numbers
	}
	
	/**
	 * Method to copy Accommodation objects from another List and add to this List
	 * @param another Another List Of Accommodations
	 */
	public void addFromAnotherList(AceAccommList another)
	{
		for (AceAccomm s : another.acList)
		{
			acList.add(s);
		}
	}

	/**
	 * @return All the Accommodation details
	 */
	public String listDetails()
	{
		String report="";
		for (AceAccomm s  : acList)              //Return formatted details for each accommodation in one line
		{
			report += s.getFullDetails();
		}
		return report;
	}

	/**
	 * @return All the Accommodation details sorted by full name of Owner
	 */
	public String listByName()
	{
		Collections.sort(acList, new AceAccommNameComparator());
		return listDetails();
	}

	/**
	 * @return All the Accommodation details sorted by Accommodation Number
	 */
	public String listByNumber()
	{
		Collections.sort(acList);
		return listDetails();
	}

	/**
	 * @return The List Of All Deluxe SurajHotelSuites
	 */
	public String listOnlyDeluxeSuites()
	{
		String report="";
		for (AceAccomm a : acList)
		{
			SurajHotelSuite s=(SurajHotelSuite) a;
			if (s.getSuiteType().equals("Deluxe"))
			{	report += s.getFullDetails();	}
		}
		return report;    	
	}

	/**
	 * @return The List Of All Royal SurajHotelSuites
	 */
	public String listOnlyRoyalSuites()
	{
		String report="";
		for (AceAccomm a : acList)
		{
			SurajHotelSuite s=(SurajHotelSuite) a;
			if (s.getSuiteType().equals("Royal"))
			{	report += s.getFullDetails();	}
		}
		return report;    	
	}
	
	public String listOnlyBreakfastCabins()
	{
		String report="";
		for (AceAccomm a : acList)
		{
			AshimCabin s=(AshimCabin) a;
			if (s.getBreakfast().equals("Yes"))
			{	report += s.getFullDetails();	}
		}
		return report;    	
	}
	
	public String listOnlySeaviewCabins()
	{
		String report="";
		for (AceAccomm a : acList)
		{
			AshimCabin s=(AshimCabin) a;
			if (s.getSeaView().equals("Yes"))
			{	report += s.getFullDetails();	}
		}
		return report;    	
	}
	
	public String viewOnlyWashroom()
	{ 
		String report="";

	for (AceAccomm a: acList)
	{	 AliyuCampsite s= (AliyuCampsite) a;
		if (s.getWashroom()) 
		{
			report += s.getFullDetails();
		}
	}
	return report;
	}
	
	/**
	 * Returns a String having short details of all accommodations in the list.
	 * These details are in a Table format, with one line per Accommodation.
	 * @return String containing details of all Accommodations in List
	 */
	public String getTableOfAccommodations()
	{	
		String report= "\n----Table Of All Accommodations (Short Details)----\n";  
		report+= "NUM  OWNERINITIALS  BEDS  DAYCOST($)  TOTALCOST($)\n";  //Table headings
		report+= "---------------------------------------------------\n";
		for (AceAccomm s  : acList)              //Return formatted details for each accommodation in one line
		{
			report += s.getShortDetails();
		}
		report+= "---------------------------------------------------\n";
		return report;
	}

	/**
	 * A method to write the supplied text to the file in the supplied filename.
	 * It can handle File-Not-Found and IO Exceptions.
	 * @param filename The name of the file to be written to
	 * @param report The text to be written to the file
	 */
	public void writeToFile(String filename, String report) 
	{
		FileWriter fw;
		try
		{
			fw = new FileWriter(filename);
			fw.write(report);
			fw.close();
		}
		catch (FileNotFoundException fnf)	//Print message and normal exit, if file is not found
		{
			System.out.println("The file at "+ filename + " was not found!");
			System.exit(0);
		}
		catch (IOException ioe)		//to print IO Exception Stack Trace
		{
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * This method reads the input file at the filename parameter.
	 * It scans the file line-by-line and can ignore blank lines.
	 * It then passes the scanned line to the processLine() method.
	 * @param filename The filename of the input file to add Suites to the List
	 */
	public void readFile(String filename, String acType) 
	{
		Scanner scanner;
		String inputLine;
		try 
		{
			File f = new File(filename);
			scanner = new Scanner(f);
			if(scanner.hasNextLine())			//Scanning the first line of the input file
			{
				inputLine = scanner.nextLine();	//Skip processing, since the 1st line only contains explanatory Column headers
			}
			while (scanner.hasNextLine()) 
			{
				inputLine = scanner.nextLine();	//Scanning the next line
				if (inputLine.length() != 0) 	//Blank lines can be ignored
				{
					if(acType.equals("Suite"))
					{ processLineSuite(inputLine);}	

					if(acType.equals("Cabin"))
					{ processLineCabin(inputLine);}		

					if(acType.equals("Campsite"))
					{ processLineCampsite(inputLine);}		
				}
			}
		}
		catch (FileNotFoundException fnf)	//Print message and normal exit, if file is not found
		{
			System.out.println("The file at "+ filename + " was not found!");
			System.exit(0);
		}
	}

	private void processLineSuite(String line)
	{
		/* For understanding purposes: A look at how the columns are split into a String parts[] array.
		 * The Descriptive Column name for Suite details is provided,
		 * along with a sample line of Suite information in the proper format.
		 * ------------------------------------------------------------------------------------------------------------------
		 * 	0		1		2		3			4		5	  			6				7				8		9,10,11,.....	
		 * 	Number	Type	Rooms	Name		Days	Gym&SpaAccess	Lunch&Dinner	ParkingSpaces	MiniBar	BedsArray
		 * 	18		Royal	1		Elon Musk	4		TRUE			TRUE			1				100		3
		 */
		try 
		{
			String parts [] = line.split(",");

			String sNum=parts[0];
			sNum=sNum.trim();					//Remove any spaces
			int num = Integer.parseInt(sNum);	//Suite Number

			String sType=parts[1];				//Type of Suite ("Deluxe" OR "Royal")

			String sRooms=parts[2];
			sRooms=sRooms.trim();					//Remove any spaces
			int rooms = Integer.parseInt(sRooms);	//Number Of Rooms

			Name ownName = new Name(parts[3]);		//Owner Name

			String sDays=parts[4];
			sDays=sDays.trim();						//Remove any spaces
			int days = Integer.parseInt(sDays);		//Number Of Days Booked

			String sGymSpa=parts[5];
			sGymSpa=sGymSpa.trim();								//Remove any spaces
			boolean gymSpa = Boolean.parseBoolean(sGymSpa);		//Access to Gym & Spa

			String sFood=parts[6];
			sFood=sFood.trim();								//Remove any spaces
			boolean food = Boolean.parseBoolean(sFood);		//Lunch & Dinner Reservation

			String sPark=parts[7];
			sPark=sPark.trim();						//Remove any spaces
			int park = Integer.parseInt(sPark);		//Number Of Parking Spaces

			String sMBar=parts[8];
			sMBar=sMBar.trim();						//Remove any spaces
			double mb = Double.parseDouble(sMBar);	//MiniBar credit

			//To process the array at the end of each line,
			//representing the arrangement of beds in each room.
			int nOfBeds=parts.length - 9;			//9 elements of parts array are already processed.
			String [] sBeds = new String[nOfBeds];
			System.arraycopy(parts, 9, sBeds, 0, nOfBeds);   //sBeds is a String array containing bed arrangement

			//Converting the String array into an integer array
			int [] beds=new int[nOfBeds];		//beds is an int array containing bed arrangement
			for (int i=0;i<beds.length;i++)
			{
				String temp=sBeds[i];
				temp=temp.trim();					//Remove any spaces
				beds[i] = Integer.parseInt(temp);	//Assign beds in each room one-by-one
			}

			AceAccomm s = new SurajHotelSuite(num,sType,rooms,ownName,days,gymSpa,food,park,mb,beds);
			this.addOneAccommodation(s);
		}

		/*If these two formatting errors are caught, the line with error is ignored,
		 *an appropriate error message is displayed,
		 *and the program tries to carry on and process the next line of the input file.
		 *For catching String to an integer format conversion errors.
		 *For example, trying to convert non-numeric text into integers.
		 */
		catch (NumberFormatException nfe)	
		{
			String error = "There is a Number Format conversion error in the following line of Input file :\n'" + line +"'";
			error += "\nError Generated because of Invalid Number Format : " + nfe.getMessage();
			error +="\n-----------------------------------------------------------------------\n\n";
			System.out.println(error);
		}

		/*For catching error, if there are a few missing elements.
		 *Note: This is not a fool-proof method. Other complex errors types are not covered...
		 */
		catch (ArrayIndexOutOfBoundsException air)  
		{
			String error = "Insufficient items in the following line of Input file : \n'" + line +"'";
			error +="\nError was found at column position : " + air.getMessage();
			error +="\n-----------------------------------------------------------------------\n\n";
			System.out.println(error);
		}
	}

	private void processLineCabin(String line)
	{
		/* For understanding purposes: A look at how the columns are split into a String parts[] array.
		 * The Descriptive Column name for Cabin details is provided,
		 * along with a sample line of Cabin information in the proper format.
		 * ------------------------------------------------------------------------------------------------------------------
		 * 	0			1			2			3			4			5,6,7,....	
		 *	CabinNumber	Name		NumOfDays	Breakfast	SeaView		Beds	 
		 *	1	        Emily Adam	1			TRUE		TRUE		2
		 */
		try 
		{
			String parts [] = line.split(",");

			String sNum=parts[0];
			sNum=sNum.trim();					//Remove any spaces
			int num = Integer.parseInt(sNum);	//Cabin Number 

			Name ownName = new Name(parts[1]);		//Owner Name

			String sDays=parts[2];
			sDays=sDays.trim();						//Remove any spaces
			int days = Integer.parseInt(sDays);		//Number Of Days Booked

			String sBreakfast=parts[3];
			sBreakfast=sBreakfast.trim();								//Remove any spaces
			boolean breakfast = Boolean.parseBoolean(sBreakfast);		//Breakfast

			String sSeaview=parts[4];
			sSeaview=sSeaview.trim();								//Remove any spaces
			boolean seaview = Boolean.parseBoolean(sSeaview);		//Seaview

			//To process the array at the end of each line,
			//representing the arrangement of beds in each room.

			int nOfBeds=parts.length - 5;			//5 elements of parts array are already processed.
			String [] sBeds = new String[nOfBeds];
			System.arraycopy(parts, 5, sBeds, 0, nOfBeds);   //sBeds is a String array containing bed arrangement

			//Converting the String array into an integer array
			int [] beds=new int[nOfBeds];		//beds is an int array containing bed arrangement
			for (int i=0;i<beds.length;i++)
			{
				String temp=sBeds[i];
				temp=temp.trim();					//Remove any spaces
				beds[i] = Integer.parseInt(temp);	//Assign beds in each room one-by-one
			}

			AshimCabin c = new AshimCabin(num, ownName, days, breakfast, seaview, beds);
			this.addOneAccommodation(c);
		}
		catch (NumberFormatException nfe)	
		{
			String error = "There is a Number Format conversion error in the following line of Input file :\n'" + line +"'";
			error += "\nError Generated because of Invalid Number Format : " + nfe.getMessage();
			error +="\n-----------------------------------------------------------------------\n\n";
			System.out.println(error);
		}
		catch (ArrayIndexOutOfBoundsException air)  
		{
			String error = "Insufficient items in the following line of Input file : \n'" + line +"'";
			error +="\nError was found at column position : " + air.getMessage();
			error +="\n-----------------------------------------------------------------------\n\n";
			System.out.println(error);
		}
	}

	private void processLineCampsite(String line)
	{
		try 
		{
			String parts [] = line.split(",");

			String sNum=parts[0];
			sNum=sNum.trim();					//Remove any spaces
			int num = Integer.parseInt(sNum);	//Campsite Number

			String sBeds=parts[1];
			sBeds=sBeds.trim();					//Remove any spaces
			int beds = Integer.parseInt(sBeds);	//Number Of Beds

			String sDays=parts[2];
			sDays=sDays.trim();						//Remove any spaces
			int days = Integer.parseInt(sDays);		//Number Of Days Booked

			String sWashroom=parts[3];
			sWashroom=sWashroom.trim();								//Remove any spaces
			boolean washroom = Boolean.parseBoolean(sWashroom);		//Access to Washroom

			String sPicnicTables=parts[4];
			sPicnicTables=sPicnicTables.trim();						//Remove any spaces
			int picnicTables= Integer.parseInt(sPicnicTables);		//Number Of PicnicTablesing Spaces

			Name ownName = new Name(parts[5]);		//Owner Name

			AliyuCampsite s = new AliyuCampsite(num,beds,days,washroom, picnicTables, ownName);
			this.addOneAccommodation(s);
		}
		catch (NumberFormatException nfe)	
		{
			String error = "There is a Number Format conversion error in the following line of Input file :\n'" + line +"'";
			error += "\nError Generated because of Invalid Number Format : " + nfe.getMessage();
			error +="\n-----------------------------------------------------------------------\n\n";
			System.out.println(error);
		}
		catch (ArrayIndexOutOfBoundsException air)  
		{
			String error = "Insufficient items in the following line of Input file : \n'" + line +"'";
			error +="\nError was found at column position : " + air.getMessage();
			error +="\n-----------------------------------------------------------------------\n\n";
			System.out.println(error);
		}
	}

	/**
	 * Returns a string containing Details of the accommodations with the highest popularity
	 * It considers all the accommodations in the list
	 * @return String with Details of Most Popular Accommodation
	 */
	public String getDetailsOfMostPopularAccomm()
	{
		String report="";

		int [] freqAccom = new int [3];
		for (AceAccomm s : acList) 
		{
			if(s instanceof SurajHotelSuite)
				freqAccom[0]++;	

			if(s instanceof AshimCabin)
				freqAccom[1]++;	

			if(s instanceof AliyuCampsite)
				freqAccom[2]++;	
		}
		int nSuites=freqAccom [0];
		int nCabins=freqAccom [1];
		int nCampsites=freqAccom [2];
		int nAllAccom= freqAccom [0]+freqAccom [1]+freqAccom [2];
		report += "\n The Number Of Hotel Suites booked are "+nSuites+".";
		report += "\n The Number Of Cabins booked are "+nCabins+".";
		report += "\n The Number Of Campsites booked are "+nCampsites+".";
		report += "\n The Total Number Of All Accommodations booked are "+nAllAccom+".";

		if (nSuites > nCabins && nSuites > nCampsites) 
		{
			report += "\n\n Hence, the Most Popular Accommodation is the Hotel Suite with "+nSuites+" suites currently booked.";
		}
		else if (nCabins > nSuites && nCabins > nCampsites) {
			report += "\n\n Hence, the Most Popular Accommodation is the Cabin with "+nCabins+" cabins currently booked.";
		}
		else if (nCampsites > nSuites  && nCampsites > nCabins)
		{
			report += "\n\n Hence, the Most Popular Accommodation is the Campsite with "+nCampsites+" campsites currently booked.";
		}
		else 
		{
			report+="\n\n There is no single most popular Accommodation Type as 2 or more types are equally popular.";
		} 
		return report;
	}

	/**
	 * @return Total Number of Beds currently occupied in this List Of Accommodation
	 */
	public int getTotalBedsInUse()
	{
		int totalBeds = 0;  //Initialise totalBeds to zero
		for (AceAccomm s:acList) 
		{
			totalBeds+=s.getNumOfBeds();
		}
		return totalBeds;
	}

	/**
	 * Returns the maximum income that all Ace Accommodations can get in a day.
	 * Calculated using the per-day cost all Suites in the List.
	 * @return Maximum income the hotel makes in a day
	 */
	public double getMaxIncomePerDay()
	{
		double income = 0.0;  //Initialise income to zero
		for (AceAccomm s:acList) 
		{
			income+=s.getCost();
		}
		return income;
	}

	/**
	 * Returns the per-day cost of the cheapest Accommodation in the List.
	 * @return Cost per day of the cheapest accommodation
	 */
	public double getCostOfCheapestAccomm() 
	{
		AceAccomm f1=acList.get(0);		//Returns the Accommodation at first index of this list
		double minCost = f1.getCost();  //Initialise the minCost to cost-per-day of the first Accommodation in List
		for (AceAccomm s : acList) 
		{
			double sCost = s.getCost();
			if (sCost<minCost)    //If cost-per-day of this Accommodation is lesser than minimum Cost-per-day
			{
				minCost=sCost;	  //set minimum Cost-per-day to the cost-per-day of this Accommodation
			}
		}	
		return minCost;
	}

	/**
	 * Returns the per-day cost of the most expensive Accommodation in the List.
	 * @return Cost per day of the most expensive accommodation
	 */
	public double getCostOfMostExpensiveAccomm() 
	{
		double maxCost = 0.0;       	//Initialise the maxCost to lowest possible value of zero
		for (AceAccomm s : acList) 
		{
			double sCost = s.getCost();
			if (sCost>maxCost)    //If cost-per-day of this Accommodation is greater than maximum Cost-per-day
			{
				maxCost=sCost;	  //set maximum Cost-per-day to the cost-per-day of this Accommodation
			}
		}	
		return maxCost;
	}

	/**
	 * @return Maximum number of days booked of any current Accommodation
	 */
	public int getMaxNumOfDaysStay() 
	{
		int maxNumOfDays = 0;       	//Initialise maxNumOfDays to lowest possible value of zero
		for (AceAccomm s : acList) 
		{
			int sNumOfDays = s.getNumOfDays();
			if (sNumOfDays>maxNumOfDays)    //If NumOfDays of this Accommodation is greater than maxNumOfDays
			{
				maxNumOfDays=sNumOfDays;
			}
		}	
		return maxNumOfDays;
	}
	
	/**
	 * @return	Formatted Column Headers of the Suite Accommodation
	 */
	public String getFullSuiteHeader()
	{
		String h="NUM  OWNERNAME               TYPE    ROOMS  BEDS  DAYS  GYM&SPA  LUNCH&DINNER  PARKING  MINIBAR($)  DAYCOST($)  TOTALCOST($)\n";
		h+=getFullSuiteDashes();
		return h;
	}

	public String getFullCabinHeader()
	{
		String h="NUM  OWNERNAME               ROOMS  BEDS  DAYS  BREAKFAST  SEAVIEW    DAYCOST($)  TOTALCOST($)\n";
		h+=getFullCabinDashes();
		return h;
	}

	public String getFullCampsiteHeader()
	{
		String h="NUM  BEDS  DAYS  WASHROOM  PICNICTABLES  OWNERNAME              DAYCOST($)  TOTALCOST($)\n";
		return h;
	}
	
	/**
	 * @return	List of Properly Formatted Dashes for Suite Accommodation
	 */
	public String getFullSuiteDashes()
	{
		String h="----------------------------------------------------------------------------------------------------------------------------\n";
		return h;
	}
	
	public String getFullCabinDashes()
	{
		String h="-----------------------------------------------------------------------------------------------\n";
		return h;
	}
	
	public String getFullCampDashes()
	{
		String h="----------------------------------------------------------------------------------------------------------------------------\n";
		return h;
	}
}