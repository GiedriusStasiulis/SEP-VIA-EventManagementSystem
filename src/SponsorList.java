import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *The SponsorList class represents an ArrayList() of Sponsor objects. 
 * 
 * @author Group#2 *
 */

public class SponsorList 
{
   private static final long serialVersionUID = 1L;
   private ArrayList<Sponsor> sponsorList;
   
   public SponsorList()
   {
      sponsorList = new ArrayList<Sponsor>();
   }
   
   public int size()
   {
      return sponsorList.size();
   }

   public void addSponsorToList(Sponsor sponsor)
   {
      sponsorList.add(sponsor);
   }
   
   public void replaceSponsorinList(int index, Sponsor sponsor)
   {
      sponsorList.set(index, sponsor);
   }
   
   public Sponsor getSponsor(int index)
   {
      return sponsorList.get(index);
   }
   
   public int getSponsorIndex(Sponsor sponsor)
   {
      return sponsorList.indexOf(sponsor);
   }
   
   public void deleteSponsorFromList(int index)
   {
      sponsorList.remove(index);
   }
   
   public void clearSponsorList()
   {
      sponsorList.clear();
   }
      
   public String toString()
   {
      String s = String.format("%s", sponsorList);
      return s;
   }   
}