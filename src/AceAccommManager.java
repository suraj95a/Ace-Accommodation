/**
 * AceAccommManager Class is used for managing the list of Ace Accommodations.
 * It is also used for reading the Input File and processing the ArrayList of Accommodation to the GUI Class.
 * @author Suraj Sivaprasad
 * @author Ashim Abdul Khadar
 * @author Aliyu Abubakar Makarfi
 */
public class AceAccommManager
{
	private AceAccommList suiteEntries;
	private AceAccommList cabinEntries;
	private AceAccommList campsiteEntries;
	private AceAccommList allAccomm;		//ArrayList of All Accommodations.

	/**
	 * Constructor to initialise all instance variables and read the List of Accommodations from the input file
	 */
	public AceAccommManager() 
	{
		suiteEntries = new AceAccommList();
		cabinEntries = new AceAccommList();
		campsiteEntries = new AceAccommList();
		allAccomm = new AceAccommList();
		
		suiteEntries.readFile("SuitesList.csv", "Suite");
		cabinEntries.readFile("CabinList.csv", "Cabin");
		campsiteEntries.readFile("CampList.csv", "Campsite");
	}

	/**
	 * The run() method is called by the Main Method.
	 * Method to call and show the Graphical User Interface for all Accommodation Types.
	 * The list of Accommodations read from the input file is passed as the parameter to the GUI Class.
	 */
	public void run() 
	{
		SurajHotelSuiteGUI shsgui = new SurajHotelSuiteGUI(suiteEntries);
		
		AshimCabinGUI acgui = new AshimCabinGUI(cabinEntries);
		
		AliyuCampsiteGUI acsgui = new AliyuCampsiteGUI(campsiteEntries);
		
		suiteEntries=shsgui.getUpdatedSuiteList();
		allAccomm.addFromAnotherList(suiteEntries);
		cabinEntries=acgui.getUpdatedCabinList();
		allAccomm.addFromAnotherList(cabinEntries);
		campsiteEntries=acsgui.getUpdatedCampsiteList();
		allAccomm.addFromAnotherList(campsiteEntries);
		
		String report ="\n*******BOOKING REPORT OF ACE ACCOMMODATIONS********\n";
		report += allAccomm.getTableOfAccommodations();		//Table of Details of Accommodations
		report += allAccomm.getDetailsOfMostPopularAccomm();
				
		allAccomm.writeToFile("AccommBookingReport.txt", report);   //Booking Report of Accommodations written to text file
		System.exit(0);
	}
}