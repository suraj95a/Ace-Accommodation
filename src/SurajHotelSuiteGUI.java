import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This Class is used to set up the GUI Layout for Suraj Hotel Suite.
 * It also configures the ActionListener Interface for the buttons.
 * JDialog is used instead of JFrame, so that the Manager class will wait for this GUI to close,
 * before using the updated list of Suites to write the booking report.
 */
public class SurajHotelSuiteGUI extends JDialog  implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private AceAccommList acList;				//List of Accommodations
	
	//GUI components to be used in this program declared as instance variables   
	JButton showListByNumber, showListByName, close, search;
	JButton showStatistics, viewRoyal, viewDeluxe, alter, book;
	JRadioButton bedSingle, bedDouble, bedTriple;
	JRadioButton searchFull, searchShort, typeDeluxe, typeRoyal, roomSingle, roomDouble, roomTriple;
	JRadioButton days1, days2, days3, days4, days5, days6, days7, parkZero, parkSingle, parkDouble, parkTriple;
	JCheckBox gs, ld;
	JComboBox <Double> mbCredit;
	JScrollPane scrollList, scrollNorth, scrollSouth;
	JTextArea displayList;
	JTextField result, nameFirst, nameMiddle, nameLast, searchField, bookTitle;
	Font myFont;

	/**
	 * The Constructor which creates the full GUI Layout of the frame and it's panels.
	 * It also sets up the Event-handling buttons using actionListener Interface
	 * @param list The List of Accommodations to be searched
	 */
	public SurajHotelSuiteGUI(AceAccommList list)
	{
		super((Window)null);	//Calling constructor of JDialog initialised with No owner (NULL)
		setModal(true);		 	//Blocks input to others so that there will be no concurrency issues
		
		this.acList = list;

		myFont=new Font (Font.SANS_SERIF,Font.PLAIN,16);	//myFont will be the main font of the GUI
		//this.setLayout(new BorderLayout(5,5));
		setTitle(" Suraj Hotel Suite GUI");		//Title of Main GUI Window
		setLocation (20,10);					//(100,10) should be near the Top-Left Corner 
		setDefaultCloseOperation(SurajHotelSuiteGUI.DO_NOTHING_ON_CLOSE);	//disable default close action
		setupNorthPanel();
		setupCenterPanel();
		setupSouthPanel();
		pack();				//pack contents to fit
		setVisible(true);	//Set it as Visible
	} 

	/**
	 * Method to set up the Center panel of the GUI.
	 * It has a scrollable JTextArea to display outputs of the buttons and other messages
	 */
	private void setupCenterPanel()
	{
		displayList = new JTextArea(19,125);		//19 row cells and 125 column cells
		displayList.setFont(new Font (Font.MONOSPACED,Font.PLAIN,16));		//Monospaced font for good formatting
		displayList.setEditable(false);
		scrollList = new JScrollPane(displayList);	//The display area can be scrolled.
		scrollList.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));	//To create a small border 
		this.add(scrollList,BorderLayout.CENTER);
	}

	/**
	 * Method to set up the North Panel of the GUI, which itself contains two panels.
	 * The firstPanel has these buttons: "Sort By Suite Number", "Sort By Owner FullName" and a "Close" button.
	 * It also has these buttons: "Show Hotel Suite Statistics", "View Only Royal Suites" & "View Only Deluxe Suites"
	 * The 2nd searchPanel is used to search a Suite Number and return either fullDetails or shortDetails of the Suite.
	 */
	private void setupNorthPanel() 
	{
		//The firstPanel has these buttons: "Sort By Suite Number", "Sort By Owner FullName" and a "Close" button.
		//It also has these buttons: "Show Hotel Suite Statistics", "View Only Royal Suites" & "View Only Deluxe Suites"
		JPanel firstPanel = new JPanel();
		showListByNumber = new JButton("List All By Suite Number");
		showListByNumber.setFont(myFont);
		showListByNumber.setBackground(Color.GREEN);
		showListByNumber.addActionListener(this);
		showListByName = new JButton("List All By Owner Name");
		showListByName.setFont(myFont);
		showListByName.setBackground(Color.GREEN);
		showListByName.addActionListener(this);
		showStatistics = new JButton("Show Suite Statistics");
		showStatistics.setFont(myFont);
		showStatistics.setBackground(Color.YELLOW);
		showStatistics.addActionListener(this);
		viewRoyal = new JButton("View Only Royal Suites");
		viewRoyal.setFont(myFont);
		viewRoyal.setBackground(Color.ORANGE);
		viewRoyal.addActionListener(this);
		viewDeluxe= new JButton("View Only Deluxe Suites");
		viewDeluxe.setFont(myFont);
		viewDeluxe.setBackground(Color.ORANGE);
		viewDeluxe.addActionListener(this);
		close = new JButton("Close & Write Booking Report");
		close.setFont(new Font (Font.SANS_SERIF,Font.BOLD,16));
		close.setBackground(Color.RED);
		close.setForeground(Color.WHITE);
		close.addActionListener(this);
		firstPanel.add(showListByNumber);
		firstPanel.add(showListByName);
		firstPanel.add(showStatistics);
		firstPanel.add(viewRoyal);
		firstPanel.add(viewDeluxe);
		firstPanel.add(close);

		//The searchPanel is used to search a Suite Number and return either fullDetails or shortDetails of the Suite.
		JPanel searchPanel = new JPanel();		
		JLabel searchPrompt = new JLabel("Enter Suite Number to show");
		searchPrompt.setFont(myFont);
		searchPanel.add(searchPrompt);  
		searchFull = new JRadioButton("FullDetails");
		searchFull.setFont(myFont);
		searchFull.setSelected(true); 	//Default option selected is to search FullDetails
		searchShort = new JRadioButton("ShortDetails");
		searchShort.setFont(myFont);
		ButtonGroup searchGroup = new ButtonGroup();
		searchGroup.add(searchFull);
		searchGroup.add(searchShort);
		searchPanel.add(searchFull);
		searchPanel.add(searchShort);
		searchField = new JTextField(5);
		searchField.setFont(myFont);
		searchPanel.add(searchField);
		search = new JButton("Search");
		search.setFont(myFont);
		search.setBackground(Color.CYAN);  
		search.addActionListener(this);			//specify action when button is pressed
		searchPanel.add(search);  

		//Set up the northPanel which contains the previous two panels
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(2,1));
		northPanel.add(firstPanel);
		northPanel.add(searchPanel);
		scrollNorth = new JScrollPane(northPanel);
		scrollNorth.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));	//To create a small border 
		this.add(scrollNorth,BorderLayout.NORTH);	//add northPanel to the North position of this Frame
	}

	/**
	 * Method to set up the SouthPanel consisting of 9 rows
	 * It sets up the layout for Booking a new Suite and entering all it's details.
	 */
	private void setupSouthPanel() 
	{ 
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(7,1));

		//alterPanel is used for editing an existing Suite
		JPanel alterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel alterPrompt = new JLabel("  To ALTER existing Suites, STEP 1) Search Suite Number above, STEP 2) Enter updated Details below & STEP 3) Press");
		alterPrompt.setFont(new Font (Font.SANS_SERIF,Font.BOLD,16));
		alterPanel.add(alterPrompt);
		alter = new JButton("Alter");
		alter.setFont(myFont);
		alter.setBackground(Color.MAGENTA);  
		alter.addActionListener(this);			//specify action when button is pressed
		alterPanel.add(alter);
		southPanel.add(alterPanel);

		//bookPanel display the "Book" button and a help text before it
		JPanel bookPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel bookingHelpText = new JLabel("  To BOOK new Suites, STEP 1) Enter new details below and STEP 2) Press");
		bookingHelpText.setFont(new Font (Font.SANS_SERIF,Font.BOLD,16));
		bookPanel.add(bookingHelpText);  
		book = new JButton("Book");
		book.setFont(myFont);
		book.setBackground(Color.BLUE);
		book.setForeground(Color.WHITE);
		book.addActionListener(this);			//specify action when button is pressed
		bookPanel.add(book);
		southPanel.add(bookPanel);

		//namePanel is used to enter the fullName of the Suite Owner
		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel nameF = new JLabel("  Enter Name of the Owner :    FirstName*");
		nameF.setFont(myFont);
		namePanel.add(nameF);
		nameFirst = new JTextField(10);
		nameFirst.setFont(myFont);
		namePanel.add(nameFirst);
		JLabel nameM = new JLabel("   MiddleName (Optional)");
		nameM.setFont(myFont);
		namePanel.add(nameM);
		nameMiddle = new JTextField(10);
		nameMiddle.setFont(myFont);
		namePanel.add(nameMiddle);
		JLabel nameL = new JLabel("   LastName*");
		nameL.setFont(myFont);
		namePanel.add(nameL);
		nameLast = new JTextField(10);
		nameLast.setFont(myFont);
		namePanel.add(nameLast);
		southPanel.add(namePanel);

		//typeRoomPanel is used to select the Suite Type: "Deluxe" OR "Royal" and the number of rooms
		JPanel typeRoomPanel = new JPanel(new GridLayout(1,2)); 
		JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		JLabel typeLabel = new JLabel("  Select the Suite Type : ");
		typeLabel.setFont(myFont);
		typePanel.add(typeLabel); 
		typeDeluxe = new JRadioButton("Deluxe");
		typeDeluxe.setFont(myFont);
		typeDeluxe.setSelected(true); 	//Default option selected is Deluxe Suite
		typeRoyal = new JRadioButton("Royal");
		typeRoyal.setFont(myFont);
		ButtonGroup typeGroup = new ButtonGroup();
		typeGroup.add(typeDeluxe);
		typeGroup.add(typeRoyal);
		typePanel.add(typeDeluxe);
		typePanel.add(typeRoyal);
		typeRoomPanel.add(typePanel);

		JPanel roomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel roomLabel;
		roomLabel=new JLabel("  Number Of Rooms : ");
		roomLabel.setFont(myFont);
		roomPanel.add(roomLabel); 
		roomSingle = new JRadioButton("Single-Room");
		roomSingle.setFont(myFont);
		roomSingle.setSelected(true); 	//Default option selected is Single-Room Suite
		roomDouble = new JRadioButton("Double-Room");
		roomDouble.setFont(myFont);
		roomTriple = new JRadioButton("Triple-Room");
		roomTriple.setFont(myFont);
		ButtonGroup roomGroup = new ButtonGroup();
		roomGroup.add(roomSingle);
		roomGroup.add(roomDouble);
		roomGroup.add(roomTriple);
		roomPanel.add(roomSingle);
		roomPanel.add(roomDouble);
		roomPanel.add(roomTriple);
		typeRoomPanel.add(roomPanel);
		southPanel.add(typeRoomPanel);

		//bedDaysPanel is used for selecting the number of beds in each room.
		//bedDaysPanel is also used for selecting the number of days the Suite is to be Booked
		JPanel bedDaysPanel = new JPanel(new GridLayout(1,2));
		JPanel bedPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		JLabel bedLabel = new JLabel("  Select Beds: ");
		bedLabel.setFont(myFont);
		bedPanel.add(bedLabel); 
		bedSingle = new JRadioButton("1 Bed per Room");
		bedSingle.setFont(myFont);
		bedDouble = new JRadioButton("2 Beds per Room");
		bedDouble.setFont(myFont);
		bedDouble.setSelected(true); 	//Default option selected is Single-Room Suite
		bedTriple = new JRadioButton("3 Beds per Room");
		bedTriple.setFont(myFont);
		ButtonGroup bedGroup = new ButtonGroup();
		bedGroup.add(bedSingle);
		bedGroup.add(bedDouble);
		bedGroup.add(bedTriple);
		bedPanel.add(bedSingle);
		bedPanel.add(bedDouble);
		bedPanel.add(bedTriple);
		bedDaysPanel.add(bedPanel);
		
		JPanel daysPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel daysLabel = new JLabel("  Number Of Days to be Booked:  ");
		daysLabel.setFont(myFont);
		daysPanel.add(daysLabel); 
		days1 = new JRadioButton("1  ");
		days1.setFont(myFont);
		days1.setSelected(true); 	//Default option selected is 1 Day Booking
		days2 = new JRadioButton("2  ");
		days2.setFont(myFont);
		days3 = new JRadioButton("3  ");
		days3.setFont(myFont);
		days4 = new JRadioButton("4  ");
		days4.setFont(myFont);
		days5 = new JRadioButton("5  ");
		days5.setFont(myFont);
		days6 = new JRadioButton("6  ");
		days6.setFont(myFont);
		days7 = new JRadioButton("7  ");
		days7.setFont(myFont);
		ButtonGroup daysGroup = new ButtonGroup();
		daysGroup.add(days1);
		daysGroup.add(days2);
		daysGroup.add(days3);
		daysGroup.add(days4);
		daysGroup.add(days5);
		daysGroup.add(days6);
		daysGroup.add(days7);
		daysPanel.add(days1);
		daysPanel.add(days2);
		daysPanel.add(days3);
		daysPanel.add(days4);
		daysPanel.add(days5);
		daysPanel.add(days6);
		daysPanel.add(days7);
		bedDaysPanel.add(daysPanel);
		southPanel.add(bedDaysPanel);

		//optionMBcPanel is used to display the extra suite options such as "Gym & Spa Access" & "Lunch & Dinner"
		//optionMBcPanel is also used to select the Minibar Credit to be stored in $.
		JPanel optionMBcPanel = new JPanel(new GridLayout(1,2));
		JPanel optionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		JLabel optionLabel = new JLabel("  Check extra Suite options : ");
		optionLabel.setFont(myFont);
		optionPanel.add(optionLabel);
		gs = new JCheckBox("Gym & Spa Access");
		gs.setFont(myFont);
		optionPanel.add(gs);
		ld = new JCheckBox("Lunch & Dinner");
		ld.setFont(myFont);
		optionPanel.add(ld);
		optionMBcPanel.add(optionPanel);
		JPanel mbcPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		JLabel mbcLabel = new JLabel("  Select Credit for your MiniBar : $");
		mbcLabel.setFont(myFont);
		mbcPanel.add(mbcLabel);
		Double [] mbcOptions = {0.0,20.0,40.0,60.0,80.0,100.0,120.0,140.0,160.0,180.0,200.0,220.0};        
		mbCredit = new JComboBox<Double>(mbcOptions);
		mbCredit.setFont(myFont);
		mbCredit.setVisible(true);
		mbCredit.setSize(5,5);
		mbCredit.setEditable(false);
		mbcPanel.add(mbCredit);
		optionMBcPanel.add(mbcPanel);
		southPanel.add(optionMBcPanel);
		
		
		//parkPanel is used to select the number of Parking Spots to be reserved.
		JPanel parkPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		JLabel parkLabel = new JLabel("  Number Of Parking Spots to Reserve : ");
		parkLabel.setFont(myFont);
		parkPanel.add(parkLabel); 
		parkZero = new JRadioButton("0  ");
		parkZero.setFont(myFont);
		parkZero.setSelected(true); 	//Default option selected is Zero parking Spots
		parkSingle = new JRadioButton("1  ");
		parkSingle.setFont(myFont);
		parkDouble = new JRadioButton("2  ");
		parkDouble.setFont(myFont);
		parkTriple = new JRadioButton("3  ");
		parkTriple.setFont(myFont);
		ButtonGroup parkGroup = new ButtonGroup();
		parkGroup.add(parkZero);
		parkGroup.add(parkSingle);
		parkGroup.add(parkDouble);
		parkGroup.add(parkTriple);
		parkPanel.add(parkZero);
		parkPanel.add(parkSingle);
		parkPanel.add(parkDouble);
		parkPanel.add(parkTriple);
		southPanel.add(parkPanel);        
		
		scrollSouth = new JScrollPane(southPanel);
		scrollSouth.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));	//To create a small border 
		this.add(scrollSouth,BorderLayout.SOUTH);	//add to the South Position
	}

	/**
	 * Implementation of the abstract actionPerformed Method of the ActionListener Interface
	 * Event Handling is performed here when a button is clicked.
	 * For each type of button, the appropriate action must be done accordingly
	 */ 
	public void actionPerformed(ActionEvent e) 
	{ 
		String d="";

		if (e.getSource() == showListByNumber) 
		{
			d="------------------------------------List Of All Suraj Hotel Suites Sorted By Suite Number-----------------------------------\n";
			d+=acList.getFullSuiteHeader();
			d+=acList.listByNumber();
			d+=acList.getFullSuiteDashes();
			displayList.setText(d);
		}
		else if (e.getSource() == showListByName )
		{
			d="------------------------------------List Of All Suraj Hotel Suites Sorted By Owner Full Name--------------------------------\n";
			d+=acList.getFullSuiteHeader();
			d+=acList.listByName();
			d+=acList.getFullSuiteDashes();
			displayList.setText(d);
		}

		else if (e.getSource() == showStatistics)
		{
			d="";
			d += "\n--------------- Statistics From Suraj Hotel Suites ------------------\n";

			double maxIncome=acList.getMaxIncomePerDay();
			String formattedMaxIncome=String.format("%.2f",maxIncome);
			d += "\n\nThe maximum income from all Hotel Suites per day is $"+formattedMaxIncome;

			double minCostingAccommodation=acList.getCostOfCheapestAccomm();
			String formattedMinCost=String.format("%.2f",minCostingAccommodation);
			d += "\n\nThe cost per-day of the cheapest Hotel Suite is $"+formattedMinCost;

			double maxCostingAccommodation=acList.getCostOfMostExpensiveAccomm();
			String formattedMaxCost=String.format("%.2f",maxCostingAccommodation);
			d += "\n\nThe cost per-day of the most expensive Hotel Suites is $"+formattedMaxCost;

			int maxDays=acList.getMaxNumOfDaysStay();
			d += "\n\nThe maximum number of days booked for any Hotel Suite is "+maxDays+".";

			int totalBeds=acList.getTotalBedsInUse();
			d += "\n\nThe total number of beds currently occupied in all Suites is "+totalBeds+".";

			int numOfAccommodations=acList.getSize();
			d += "\n\nThe number of all Hotel Suites currently booked in the list is "+numOfAccommodations+".";

			d += "\n\n---------------------------------------------------------------------";
			displayList.setText(d);	
		}
		else if(e.getSource() == viewDeluxe)
		{
			d="------------------------------------List Of Only Deluxe Suraj Hotel Suites-----------------------------------\n";
			d+=acList.getFullSuiteHeader();
			d+=acList.listOnlyDeluxeSuites();
			d+=acList.getFullSuiteDashes();
			displayList.setText(d);    			
		}
		else if(e.getSource() == viewRoyal)
		{
			d="------------------------------------List Of Only Royal Suraj Hotel Suites-----------------------------------\n";
			d+=acList.getFullSuiteHeader();
			d+=acList.listOnlyRoyalSuites();
			d+=acList.getFullSuiteDashes();
			displayList.setText(d);    	
		}
		else if (e.getSource() == search) 
		{
			search();
		}
		else if(e.getSource() == book)
		{
			AceAccomm x=readBook("book");			
			if(x!=null)
			{
				acList.addOneAccommodation(x);
				d="\n Your Suite has been Booked!\n";
				d+="\n-----------------------------------------------Full Details Of Your Booking-------------------------------------------------\n";
				d+=acList.getFullSuiteHeader();
				d+=x.getFullDetails();
				d+=acList.getFullSuiteDashes();
				displayList.setText(d);
				resetBooking();						
			}
		}
		else if (e.getSource() == close) 
		{	
			JLabel closeLabel;
			closeLabel= new JLabel("The Booking Report File can be found after all GUI's are closed.");
			closeLabel.setFont(myFont);
			closeLabel.setForeground(Color.RED);
			JOptionPane.showMessageDialog(this,closeLabel,"GoodBye From Suraj Hotel Suites!",JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
			this.dispose();
			//System.exit(0);
		}
		else if(e.getSource()==alter)
		{
			try
			{
				String searchString = searchField.getText().trim();
				if(searchString.length() > 0) 
				{
					int searchNumber=Integer.parseInt(searchString);
					AceAccomm ac = acList.findByNum(searchNumber);
					int acpos=acList.getIndexOf(ac);
					if (ac != null ) 
					{
						AceAccomm x=readBook("alter");
						if(x!=null)
						{
							acList.replaceAtIndex(acpos,x);
							d="\n Your Suite Details have been successfully altered!\n";
							d+="\n-----------------------------------------Full Details Of Your New Altered Suite---------------------------------------------\n";
							d+=acList.getFullSuiteHeader();
							d+=x.getFullDetails();
							d+=acList.getFullSuiteDashes();
							displayList.setText(d);
							resetBooking();
							searchField.setText("");					
						}
					}
					else
					{
					displayList.setText("\n Suite Number "+searchNumber+" has NOT been Found! It Does NOT Exist!");
					}
				}   
				else
				{
				displayList.setText("\n No Suite Number has been Entered");
				}
			}
			catch (NumberFormatException nfe)	
			{
				String error = "";
				error += "\n There is a Number Format Error in your Search Entry " + nfe.getMessage();
				error +="\n\n You can try again with a new Search Entry.";
				displayList.setText(error);
			}
		}
	}  

	public AceAccommList getUpdatedSuiteList()
	{
		return acList;
	}
	
	/**
	 * Method to reset all the GUI Components of the Booking Panel back to their default values.
	 */
	private void resetBooking()
	{
		typeDeluxe.setSelected(true);
		roomSingle.setSelected(true);
		nameFirst.setText("");
		nameMiddle.setText("");
		nameLast.setText("");
		days1.setSelected(true);
		gs.setSelected(false);	
		ld.setSelected(false);		
		parkZero.setSelected(true);
		mbCredit.setSelectedIndex(0);
		bedDouble.setSelected(true);	
	}
	
	/**
	 * Method to get the search text and search the List of Accommodations
	 * After the search, it return the result in the displayList JTextArea
	 */
	private void search() 
	{
		try
		{
			String searchString = searchField.getText().trim();
			if(searchString.length() > 0) 
			{
				String d="";
				int searchNumber=Integer.parseInt(searchString);
				AceAccomm ac = acList.findByNum(searchNumber);
				SurajHotelSuite s=(SurajHotelSuite) ac;
				if (ac != null ) 
				{
					if(searchFull.isSelected())
					{
						d+="\n Suite Number "+searchNumber+" has been Found!\n";
						d+="\n----------------------------------------------Full Details Of Your Suite Entry----------------------------------------------\n";
						d+=acList.getFullSuiteHeader();
						d+=s.getFullDetails();
						d+=acList.getFullSuiteDashes();
						displayList.setText(d);
					}
					else if(searchShort.isSelected())
					{
						d+="\n Suite Number "+searchNumber+" has been Found!\n";
						d+="\n--------Short Details Of Your Suite Entry--------\n";
						d+="NUM  OWNERINITIALS  BEDS  DAYCOST($)  TOTALCOST($)\n";
						d+="--------------------------------------------------\n";
						d+=s.getShortDetails();
						d+="--------------------------------------------------\n";
						displayList.setText(d);
					}
				}
				else
				{
					displayList.setText("\n Suite Number "+searchNumber+" has NOT been Found! It Does NOT Exist!");
				}
			}   
			else
			{
				displayList.setText("\n No Suite Number has been Entered");
			}
		}
		catch (NumberFormatException nfe)	
		{
			String error = "";
			error += "\n There is a Number Format Error in your Search Entry " + nfe.getMessage();
			error +="\n\n You can try again with a new Search Entry.";
			displayList.setText(error);
		}
	}
	
	private AceAccomm readBook(String option)
	{
		int num= 1+(acList.getSize()); //Initialising num with a value
		if(option.equals("book"))
		{
		num = 1+(acList.getSize());	//Suite Number
		}
		else if(option.equals("alter"))
		{
		String searchString = searchField.getText().trim();
		num=Integer.parseInt(searchString);
		}

		String sType="";				//Type of Suite ("Deluxe" OR "Royal")
		if(typeDeluxe.isSelected())
		{	sType="Deluxe";	}
		else if(typeRoyal.isSelected())
		{	sType="Royal";	}	
		
		int rooms=1;						//Number Of Rooms
		if(roomSingle.isSelected())
		{	rooms=1;	}
		else if(roomDouble.isSelected())
		{	rooms=2;	}	
		else if(roomTriple.isSelected())
		{	rooms=3;	}
		
		Name ownName;		//Owner Name
		String fString = nameFirst.getText().trim();
		String mString = nameMiddle.getText().trim();
		String lString = nameLast.getText().trim();
		if(mString.length() > 0)
		{	ownName=new Name(fString,mString,lString);	}
		else
		{	ownName=new Name(fString,lString);	}
		
		int days=1;		//Number Of Days Booked
		if(days1.isSelected())
		{	days=1;	}
		else if(days2.isSelected())
		{	days=2;	}	
		else if(days3.isSelected())
		{	days=3;	}
		else if(days4.isSelected())
		{	days=4;	}	
		else if(days5.isSelected())
		{	days=5;	}
		else if(days6.isSelected())
		{	days=6;	}	
		else if(days7.isSelected())
		{	days=7;	}
		
		boolean gymSpa = gs.isSelected();		//Access to Gym & Spa
		boolean lunchDin = ld.isSelected();		//Lunch & Dinner Reservation

		int park=0;		//Number Of Parking Spaces
		if(parkZero.isSelected())
		{	park=0;	}
		else if(parkSingle.isSelected())
		{	park=1;	}	
		else if(parkDouble.isSelected())
		{	park=2;	}	
		else if(parkTriple.isSelected())
		{	park=3;	}	
		
		double mb = (double) mbCredit.getSelectedItem();	//MiniBar credit

		int [] beds=new int[rooms];		//beds is an integer array containing bed arrangement
		for (int i=0;i<beds.length;i++)
		{
			if(bedSingle.isSelected())
			{	beds[i]=1;	}
			else if(bedDouble.isSelected())
			{	beds[i]=2;	}	
			else if(bedTriple.isSelected())
			{	beds[i]=3;	}
		}
		
		if(fString.isEmpty()||lString.isEmpty())
		{
			String d;
			d="\n Unsuccessful, due to Invalid Name Entry! Try Again using a valid name.\n";
			displayList.setText(d);		
			return null;
		}
		else
		{
			AceAccomm s = new SurajHotelSuite(num,sType,rooms,ownName,days,gymSpa,lunchDin,park,mb,beds);
			return s;
		}
	}
}