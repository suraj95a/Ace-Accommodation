import java.util.Comparator;

/**
 * This is the Comparator Class used to sort Accommodation objects based on the full name of the Owner.
 * @author Suraj Sivaprasad
 * @author Ashim Abdul Khadar
 * @author Aliyu Abubakar Makarfi
 */
public class AceAccommNameComparator implements Comparator<AceAccomm>
{
	public int compare(AceAccomm a1, AceAccomm a2) 
	{
		return a1.getOwnerName().compareTo(a2.getOwnerName());
	}
}