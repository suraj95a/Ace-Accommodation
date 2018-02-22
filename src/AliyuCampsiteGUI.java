import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AliyuCampsiteGUI extends JDialog  implements ActionListener 
{
	private static final long serialVersionUID = 1L;
	private AceAccommList acList;			//List of Accommodations

	//GUI components to be used in this program declared as instance variables   
	JButton showListByNumber, showListByName, close, EditInfo;
	JButton showStatistics, viewWashroom, book;
	JRadioButton searchFull, searchShort, picZero, picSingle, picDouble, picTriple;
	JRadioButton bed1, bed2, bed3, bed4;
	JComboBox <Integer> Days;
	JCheckBox yes;
	JTextArea displayList;
	JTextField result, nameFirst, nameMiddle, nameLast, searchField;
	JButton search;
	Font myFont;
	JScrollPane scrollList;

	public AliyuCampsiteGUI(AceAccommList list)
	{
		super((Window)null);
		setModal(true);		 	

		this.acList = list;

		myFont=new Font (Font.SERIF,Font.PLAIN,16);
		//this.setLayout(new BorderLayout(5,5));
		setTitle(" Aliyu Campsite GUI");		
		setLocation (10,10);					
		setDefaultCloseOperation(AliyuCampsiteGUI.DO_NOTHING_ON_CLOSE);	//disable default close action
		setupNorthPanel();
		setupCenterPanel();
		setupSouthPanel();
		pack();				//pack contents to fit
		setVisible(true);	//Set it as Visible
	} 

	private void setupCenterPanel()
	{
		displayList = new JTextArea(20,90);		
		displayList.setFont(new Font (Font.MONOSPACED,Font.PLAIN,16));
		displayList.setEditable(false);
		scrollList = new JScrollPane(displayList);
		scrollList.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		this.add(scrollList,BorderLayout.CENTER);
	}

	private void setupNorthPanel() 
	{
		JPanel firstPanel = new JPanel();
		showListByNumber = new JButton("List All By Campsite Number");
		showListByNumber.setFont(myFont);
		showListByNumber.addActionListener(this);
		showListByName = new JButton("List All By Owner Name");
		showListByName.setFont(myFont);
		showListByName.addActionListener(this);
		showStatistics = new JButton("Show Camp Statistics");
		showStatistics.setFont(myFont);
		showStatistics.addActionListener(this);

		viewWashroom = new JButton("Show Camps with Washroom");
		viewWashroom.setFont(myFont);
		viewWashroom.addActionListener(this);

		firstPanel.add(showListByNumber);
		firstPanel.add(showListByName);
		firstPanel.add(showStatistics);
		firstPanel.add(viewWashroom);
		
		JPanel searchPanel = new JPanel();		
		JLabel searchPrompt = new JLabel("Enter Campsite Number to show");
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
		search.addActionListener(this);		
		searchPanel.add(search);  

		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(2,1));
		northPanel.add(firstPanel);
		northPanel.add(searchPanel);
		this.add(northPanel, BorderLayout.NORTH);	//add northPanel to the North position of this Frame
	}

	private void setupSouthPanel() 
	{ 
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(8,1));

		JLabel bookingHeader = new JLabel("  To book a Campsit, Enter the details and press the 'Book' button");
		bookingHeader.setFont(new Font (Font.SERIF,Font.BOLD,16));
		southPanel.add(bookingHeader);

		//namePanel is used to enter the fullName of the Campsite Owner
		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel nameF = new JLabel("  Enter Name of the Owner :    FirstName");
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
		JLabel nameL = new JLabel("   LastName");
		nameL.setFont(myFont);
		namePanel.add(nameL);
		nameLast = new JTextField(10);
		nameLast.setFont(myFont);
		namePanel.add(nameLast);
		southPanel.add(namePanel);

		//daysPanel is used for selecting the number of beds
		JPanel daysPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		JLabel daysLabel = new JLabel("  Select Booking Days: ");
		daysLabel.setFont(myFont);
		daysPanel.add(daysLabel); 
		Integer [] daysOptions = {1,2,3,4,5,6,7};        
		Days = new JComboBox<Integer>(daysOptions);
		Days.setFont(myFont);
		Days.setVisible(true);
		Days.setSize(5,5);
		Days.setEditable(false);
		daysPanel.add(Days);
		southPanel.add(daysPanel);


		JPanel bedPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		JLabel bedLabel = new JLabel("  Number Of Beds to be Booked:  ");
		bedLabel.setFont(myFont);
		bedPanel.add(bedLabel); 
		bed1 = new JRadioButton("1  ");
		bed1.setFont(myFont);
		bed1.setSelected(true);
		bed2 = new JRadioButton("2  ");
		bed2.setFont(myFont);
		bed3 = new JRadioButton("3  ");
		bed3.setFont(myFont);
		bed4 = new JRadioButton("4  ");
		bed4.setFont(myFont);
		ButtonGroup bedGroup = new ButtonGroup();
		bedGroup.add(bed1);
		bedGroup.add(bed2);
		bedGroup.add(bed3);
		bedGroup.add(bed4);
		bedPanel.add(bed1);
		bedPanel.add(bed2);
		bedPanel.add(bed3);
		bedPanel.add(bed4);
		southPanel.add(bedPanel);

		//wash room Panel
		JPanel washPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		JLabel washLabel = new JLabel("  Select Washroom options : ");
		washLabel.setFont(myFont);
		washPanel.add(washLabel);
		yes = new JCheckBox("Include Washroom");
		yes.setFont(myFont);
		washPanel.add(yes);
		southPanel.add(washPanel);

		//picnicPanel is used to select the number of picnic tables to be reserved.
		JPanel picPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		JLabel picLabel = new JLabel("  Reserve Picnic Tables : ");
		picLabel.setFont(myFont);
		picPanel.add(picLabel); 
		picZero = new JRadioButton("0  ");
		picZero.setFont(myFont);
		picZero.setSelected(true);
		picSingle = new JRadioButton("1  ");
		picSingle.setFont(myFont);
		picDouble = new JRadioButton("2  ");
		picDouble.setFont(myFont);
		picTriple = new JRadioButton("3  ");
		picTriple.setFont(myFont);
		ButtonGroup picGroup = new ButtonGroup();
		picGroup.add(picZero);
		picGroup.add(picSingle);
		picGroup.add(picDouble);
		picGroup.add(picTriple);
		picPanel.add(picZero);
		picPanel.add(picSingle);
		picPanel.add(picDouble);
		picPanel.add(picTriple);

		southPanel.add(picPanel);    

		//bookPanel display the "Book" button
		JPanel bookPanel=new JPanel();
		JLabel bookingHelpText = new JLabel("Complete Booking");
		bookingHelpText.setFont(myFont);
		bookPanel.add(bookingHelpText);  
		book = new JButton("Book");
		book.setFont(myFont);
		book.setBackground(Color.BLUE);
		book.setForeground(Color.WHITE);
		book.addActionListener(this);			//specify action when button is pressed
		bookPanel.add(book); 
		southPanel.add(bookPanel);
		//add south panel to the content pane
		this.add(southPanel, BorderLayout.SOUTH);   	

		close = new JButton("Close");
		JPanel closePanel = new JPanel();
		bookingHelpText.setFont(myFont);
		close.setFont(new Font (Font.SERIF,Font.BOLD,16));
		close.setBackground(Color.RED);
		close.setForeground(Color.WHITE);
		close.addActionListener(this);
		closePanel.add(close);
		southPanel.add(closePanel);
	}


	public void actionPerformed(ActionEvent e) 
	{ 
		String d="";

		if (e.getSource() == showListByNumber) 
		{
			d="---------------List Of All Aliyu Campsite Sorted By Campsite Number---------------------\n";
			d+=acList.getFullCampsiteHeader();
			d+=acList.listByNumber();
			displayList.setText(d);
		}
		else if (e.getSource() == showListByName )
		{
			d="---------------List Of All Aliyu Campsite Sorted By Owner Full Name---------------------\n";
			d+=acList.getFullCampsiteHeader();
			d+=acList.listByName();
			displayList.setText(d);
		}

		else if (e.getSource() == showStatistics)
		{
			d="";
			d += "\n--------------- Statistics From Aliyu Campsites ------------------\n";

			double maxIncome=acList.getMaxIncomePerDay();
			String formattedMaxIncome=String.format("%.2f",maxIncome);
			d += "\n\nThe maximum income from all Campsite per day is $"+formattedMaxIncome;

			double minCostingAccommodation=acList.getCostOfCheapestAccomm();
			String formattedMinCost=String.format("%.2f",minCostingAccommodation);
			d += "\n\nThe cost per-day of the cheapest Campsite is $"+formattedMinCost;

			double maxCostingAccommodation=acList.getCostOfMostExpensiveAccomm();
			String formattedMaxCost=String.format("%.2f",maxCostingAccommodation);
			d += "\n\nThe cost per-day of the most expensive Campsite is $"+formattedMaxCost;

			int maxDays=acList.getMaxNumOfDaysStay();
			d += "\n\nThe maximum number of days booked for any Campsite is "+maxDays+".";

			int numOfAccommodations=acList.getSize();
			d += "\n\nThe number of all Campsite currently booked in the list is "+numOfAccommodations+".";

			d += "\n\n---------------------------------------------------------------------";
			displayList.setText(d);	
		}
		else if(e.getSource() == viewWashroom)
		{
			d="----------------------List Of Only Washroom------------------------------\n";
			d+=acList.getFullCampsiteHeader();
			d+=acList.viewOnlyWashroom();
			displayList.setText(d);    			
		}

		else if (e.getSource() == search) 
		{
			search();
		}
		else if(e.getSource() == book)
		{

			int num = 61+(acList.getSize());	//Campsite Number

			Name ownName;		//Owner Name
			String fString = nameFirst.getText().trim();
			String mString = nameMiddle.getText().trim();
			String lString = nameLast.getText().trim();
			if(mString.length() > 0)
			{	ownName=new Name(fString,mString,lString);	}
			else
			{	ownName=new Name(fString,lString);	}

			int nd = (Integer) Days.getSelectedItem();	

			boolean washroom = yes.isSelected();

			int pic=0;		
			if(picZero.isSelected())
			{	pic=0;	}
			else if(picSingle.isSelected())
			{	pic=1;	}	
			else if(picDouble.isSelected())
			{	pic=2;	}	
			else if(picTriple.isSelected())
			{	pic=3;	}	

			int bed=1;		//Number Of beds Booked
			if(bed1.isSelected())
			{	bed=1;	}
			else if(bed2.isSelected())
			{	bed=2;	}	
			else if(bed3.isSelected())
			{	bed=3;	}
			else if(bed4.isSelected())
			{	bed=4;	}	

			if(fString.isEmpty()||lString.isEmpty())
			{
				d="\n Enter name in valid format. Cabin booking unsuccessful\n";
				displayList.setText(d);
			}
			else
			{
			AceAccomm s = new AliyuCampsite(num,bed,nd,washroom,pic,ownName);
			acList.addOneAccommodation(s);

			d="\n Your Campsite has been Booked!\n";
			d+="\n-------------------Full Details Of Your Booking----------------------\n";
			d+=acList.getFullCampsiteHeader();
			d+=s.getFullDetails();
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
			JOptionPane.showMessageDialog(this,closeLabel,"GoodBye From AliyuCampSite",JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
			this.dispose();
			//System.exit(0);
		}
	}  

	public AceAccommList getUpdatedCampsiteList()
	{
		return acList;
	}

	private void resetBooking()
	{
		nameFirst.setText("");
		nameMiddle.setText("");
		nameLast.setText("");
		bed1.setSelected(true);
		yes.setSelected(false);
		picZero.setSelected(true);
		Days.setSelectedIndex(0);
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
				AliyuCampsite s=(AliyuCampsite) ac;
				if (ac != null ) 
				{
					if(searchFull.isSelected())
					{
						d+="\n Campsite Number "+searchNumber+" has been Found!\n";
						d+="\n------------------------------Full Details Of Your Campsite Entry--------------------------------\n";
						d+=acList.getFullCampsiteHeader();
						d+=s.getFullDetails();
						displayList.setText(d);
					}
					else if(searchShort.isSelected())
					{
						d+="\n Campsite Number "+searchNumber+" has been Found!\n";
						d+="\n--------Short Details Of Your Campsite Entry--------\n";
						d+="NUM  OWNERINITIALS  BEDS  DAYCOST($)  TOTALCOST($)\n";
						d+="--------------------------------------------------\n";
						d+=s.getShortDetails();
						d+="--------------------------------------------------\n";
						displayList.setText(d);
					}
				}
				else
					displayList.setText("\n Campsite Number "+searchNumber+" has NOT been Found! It Does NOT Exist!");
			}   
			else
				displayList.setText("\n No Campsite Number has been Entered");
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