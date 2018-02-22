import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AshimCabinGUI extends JDialog  implements ActionListener {

	private static final long serialVersionUID = 1L;
	private AceAccommList acList;	

	JButton showListByNumber, showListByName, book;
	JButton showStatistics, viewBreakfast, viewSeaview, close;
	JRadioButton bed2, bed4, bed6, bed8, searchFull, searchShort;
	JScrollPane scrollList;
	JCheckBox bf, sv;
	JTextArea displayList;
	JTextField result, nameFirst, nameMiddle, nameLast, searchField;
	JButton search;
	JComboBox <Integer> nfDays;

	public AshimCabinGUI(AceAccommList list)
	{
		super((Window)null);	
		setModal(true);		 	

		this.acList = list;


		setTitle(" Ashim Cabin GUI");	
		setLocation (100,10);					
		setDefaultCloseOperation(AshimCabinGUI.DO_NOTHING_ON_CLOSE);
		setupNorthPanel();
		setupCenterPanel();
		setupSouthPanel();
		pack();		
		setVisible(true);	
	} 
	private void setupNorthPanel() 
	{
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(7,1));

		JPanel titlePanel=new JPanel(new GridLayout(1,2));
		JPanel bookTitlePanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel bookingHeader = new JLabel("                                                                New Booking");
		bookingHeader.setFont(new Font (Font.SANS_SERIF,Font.BOLD,20));
		bookTitlePanel.add(bookingHeader);
		JPanel bookTitlePanel2=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		close = new JButton("Write Booking Report & Close");
		close.setFont(new Font (Font.SANS_SERIF,Font.BOLD,14));
		close.setBackground(Color.RED);
		close.addActionListener(this);
		bookTitlePanel2.add(close);
		titlePanel.add(bookTitlePanel);
		titlePanel.add(bookTitlePanel2);
		northPanel.add(titlePanel);

		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel nameF = new JLabel("  Enter Name of the Owner :      *FirstName");
		namePanel.add(nameF);
		nameFirst = new JTextField(10);
		namePanel.add(nameFirst);
		JLabel nameM = new JLabel("   MiddleName");
		namePanel.add(nameM);
		nameMiddle = new JTextField(10);
		namePanel.add(nameMiddle);
		JLabel nameL = new JLabel("   *LastName");
		namePanel.add(nameL);
		nameLast = new JTextField(10);
		namePanel.add(nameLast);
		northPanel.add(namePanel);

		JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		JLabel typeLabel = new JLabel("  *Select the Cabin Type :     ");
		typePanel.add(typeLabel); 
		bed2 = new JRadioButton("2 Beds");
		bed2.setSelected(true); 	 
		bed4 = new JRadioButton("4 Beds");
		bed6 = new JRadioButton("6 Beds");
		bed8 = new JRadioButton("8 Beds");
		ButtonGroup typeGroup = new ButtonGroup();
		typeGroup.add(bed2);
		typeGroup.add(bed4);
		typeGroup.add(bed6);
		typeGroup.add(bed8);
		typePanel.add(bed2);
		typePanel.add(bed4);
		typePanel.add(bed6);
		typePanel.add(bed8);
		northPanel.add(typePanel);

		JPanel optionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		JLabel optionLabel = new JLabel("  Check extra Cabin Facility :     ");
		optionPanel.add(optionLabel);
		bf = new JCheckBox("Breakfast");
		optionPanel.add(bf);
		sv = new JCheckBox("Sea View");
		optionPanel.add(sv);
		northPanel.add(optionPanel);

		JPanel nfdPanel0 = new JPanel(new GridLayout(1,2)); 
		JPanel nfdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		JLabel nfdLabel = new JLabel("  * Number of Days :    ");
		nfdPanel.add(nfdLabel);
		Integer [] nfrOptions = {1,2,3,4,5,6,7,8,9};        
		nfDays = new JComboBox <Integer> (nfrOptions);
		nfDays.setVisible(true);
		nfDays.setSize(5,5);
		nfDays.setEditable(false);
		nfdPanel.add(nfDays);
		nfdPanel0.add(nfdPanel);
		JPanel nfdPanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel bookingHelpText = new JLabel("Note: * marked are mandatory fields");
		nfdPanel2.add(bookingHelpText);  
		nfdPanel0.add(nfdPanel2);  
		northPanel.add(nfdPanel0);

		JPanel bookPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		book = new JButton("Add Booking");
		book.setFont(new Font (Font.SANS_SERIF,Font.BOLD,20));
		book.addActionListener(this);	
		bookPanel.add(book); 
		northPanel.add(bookPanel);
		
		JPanel borderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel border = new JLabel("-----------------------------------------------------------------------------------------------------------------------------------------");
		border.setFont(new Font (Font.SANS_SERIF,Font.BOLD,25));
		borderPanel.add(border);
		northPanel.add(borderPanel);
		
		this.add(northPanel, BorderLayout.NORTH); 
		
	}
	private void setupSouthPanel()
	{
		displayList = new JTextArea(15,125);	
		displayList.setFont(new Font (Font.MONOSPACED,Font.PLAIN,14));	
		displayList.setEditable(false);
		scrollList = new JScrollPane(displayList);
		scrollList.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); 
		this.add(scrollList,BorderLayout.SOUTH);
	}

	private void setupCenterPanel()
	{
		JPanel firstPanel = new JPanel();
		showListByNumber = new JButton("List All By Cabin Number");
		showListByNumber.addActionListener(this);
		showListByName = new JButton("List All By Owner Name"); 
		showListByName.addActionListener(this);
		showStatistics = new JButton("Show Cabin Statistics");

		showStatistics.addActionListener(this);
		viewBreakfast = new JButton("View Only Cabin with Breakfast");
		viewBreakfast.addActionListener(this);
		viewSeaview= new JButton("View Only Cabin with Seaview");
		viewSeaview.addActionListener(this);
		firstPanel.add(showListByNumber);
		firstPanel.add(showListByName);
		firstPanel.add(showStatistics);
		firstPanel.add(viewBreakfast);
		firstPanel.add(viewSeaview);

		JPanel searchPanel = new JPanel();		
		JLabel searchPrompt = new JLabel("Enter Cabin Number to show");
		searchPanel.add(searchPrompt);  
		searchFull = new JRadioButton("FullDetails");
		searchFull.setSelected(true); 
		searchShort = new JRadioButton("ShortDetails");
		ButtonGroup searchGroup = new ButtonGroup();
		searchGroup.add(searchFull);
		searchGroup.add(searchShort);
		searchPanel.add(searchFull);
		searchPanel.add(searchShort);
		searchField = new JTextField(5);
		searchPanel.add(searchField);
		search = new JButton("Search");
		search.addActionListener(this);		
		searchPanel.add(search);  

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(2,1));
		centerPanel.add(firstPanel);
		centerPanel.add(searchPanel);
		this.add(centerPanel, BorderLayout.CENTER);
	}



	public void actionPerformed(ActionEvent e) 
	{ 
		String d="";

		if (e.getSource() == showListByNumber) 
		{
			d="------------------List Of All Ashim Cabins Sorted By Cabin Number----------------\n";
			d+=acList.getFullCabinHeader();
			d+=acList.listByNumber();
			d+=acList.getFullCabinDashes();
			displayList.setText(d);
		}
		else if (e.getSource() == showListByName )
		{
			d="----------------List Of All Ashim  Cabins Sorted By Owner Full Name---------------\n";
			d+=acList.getFullCabinHeader();
			d+=acList.listByName();
			d+=acList.getFullCabinDashes();
			displayList.setText(d);
		}

		else if (e.getSource() == showStatistics)
		{
			d="";
			d += "\n--------------- Statistics From Ashim Cabins ------------------\n";

			double maxIncome=acList.getMaxIncomePerDay();
			String formattedMaxIncome=String.format("%.2f",maxIncome);
			d += "\n\nThe maximum income from all  Cabins per day is $"+formattedMaxIncome;

			double minCostingAccommodation=acList.getCostOfCheapestAccomm();
			String formattedMinCost=String.format("%.2f",minCostingAccommodation);
			d += "\n\nThe cost per-day of the cheapest Cabin is $"+formattedMinCost;

			double maxCostingAccommodation=acList.getCostOfMostExpensiveAccomm();
			String formattedMaxCost=String.format("%.2f",maxCostingAccommodation);
			d += "\n\nThe cost per-day of the most expensive Cabis is $"+formattedMaxCost;

			int maxDays=acList.getMaxNumOfDaysStay();
			d += "\n\nThe maximum number of days booked for any Cabin is "+maxDays+".";

			int numOfAccommodations=acList.getSize();
			d += "\n\nThe number of all Cabins currently booked in the list is "+numOfAccommodations+".";

			displayList.setText(d);	
		}
		else if(e.getSource() == viewBreakfast)
		{
			d="------------------------------------List of Ashim Cabins with Breakfast-----------------------------------\n";
			d+=acList.getFullCabinHeader();
			d+=acList.listOnlyBreakfastCabins();
			d+=acList.getFullCabinDashes();
			displayList.setText(d);    			
		}
		else if(e.getSource() == viewSeaview)
		{
			d="------------------------------------List of Ashim Cabins with Sea View-----------------------------------\n";
			d+=acList.getFullCabinHeader();
			d+=acList.listOnlySeaviewCabins();
			d+=acList.getFullCabinDashes();
			displayList.setText(d);    	
		}
		else if (e.getSource() == search) 
		{
			search();
		}
		else if(e.getSource() == book)
		{

			int num = 31+(acList.getSize());	

			Name ownName;		
			String fString = nameFirst.getText().trim();
			String mString = nameMiddle.getText().trim();
			String lString = nameLast.getText().trim();
			if(mString.length() > 0)
			{	ownName=new Name(fString,mString,lString);	}
			else
			{	ownName=new Name(fString,lString);	}


			int [] beds;	
			beds = new int [] {2}; 
			if(bed2.isSelected())
			{	beds = new int [] {2}; }
			else if(bed4.isSelected())
			{	beds = new int [] {2,2}; }
			else if(bed6.isSelected())
			{	beds = new int [] {2,4}; }
			else if(bed8.isSelected())
			{	beds = new int [] {2,2,4}; }	

			boolean breakfast = bf.isSelected();		
			boolean seaview = sv.isSelected();	

			Integer days = (Integer) nfDays.getSelectedItem();


			if(fString.isEmpty()||lString.isEmpty())
			{
				d="\n Enter name in valid format. Cabin booking unsuccessful\n";
				displayList.setText(d);
			}
			else
			{
				AceAccomm s = new AshimCabin(num, ownName, days, breakfast, seaview, beds);
				acList.addOneAccommodation(s);

				d="\n Your Cabin has been Booked!\n";
				d+="\n---------------------------------Full Details Of Your Booking------------------------------------\n";
				d+=acList.getFullCabinHeader();
				d+=s.getFullDetails();
				d+=acList.getFullCabinDashes();
				displayList.setText(d);
				resetBooking();
			}
			

		}
		else if (e.getSource() == close) 
		{	
			JLabel closeLabel;
			closeLabel= new JLabel("The Booking Report File can be found after all GUI's are closed.");
			JOptionPane.showMessageDialog(this,closeLabel,"GoodBye From Ashim Cabin!",JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
			this.dispose();
			//System.exit(0);
		}
	}  

	public AceAccommList getUpdatedCabinList()
	{
		return acList;
	}


	private void resetBooking()
	{
		bed2.setSelected(true);
		nameFirst.setText("");
		nameMiddle.setText("");
		nameLast.setText("");
		nfDays.setSelectedIndex(1);
		bf.setSelected(false);	
		sv.setSelected(false);		
	}

	private void search() {
		try
		{
			String searchString = searchField.getText().trim();
			if(searchString.length() > 0) 
			{
				String d="";
				int searchNumber=Integer.parseInt(searchString);
				AceAccomm ac = acList.findByNum(searchNumber);
				AshimCabin s=(AshimCabin) ac;
				if (ac != null ) 
				{
					if(searchFull.isSelected())
					{
						d+="\n Cabin Number "+searchNumber+" has been Found!\n";
						d+="\n------------------------------Full Details Of Your Cabin Entry-----------------------------\n";
						d+=acList.getFullCabinHeader();
						d+=s.getFullDetails();
						d+=acList.getFullCabinDashes();
						displayList.setText(d);
					}
					else if(searchShort.isSelected())
					{
						d+="\n Cabin Number "+searchNumber+" has been Found!\n";
						d+="\n--------Short Details Of Your Cabin Entry--------\n";
						d+="NUM  OWNERINITIALS  BEDS  DAYCOST($)  TOTALCOST($)\n";
						d+="--------------------------------------------------\n";
						d+=s.getShortDetails();
						d+="--------------------------------------------------\n";
						displayList.setText(d);
					}
				}
				else
					displayList.setText("\n Cabin Number "+searchNumber+" has NOT been Found! It Does NOT Exist!");
			}   
			else
				displayList.setText("\n No Cabin Number has been Entered");
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
