import java.util.ArrayList;

public class NonMemberList
{
   private static final long serialVersionUID = 1L;
   private ArrayList<NonMember> nonMemberList;
   
   public NonMemberList()
   {
      nonMemberList = new ArrayList<NonMember>();
   }
   
   public int size()
   {
      return nonMemberList.size();
   }

   public void addNonMemberToList(NonMember nonMember)
   {
      nonMemberList.add(nonMember);
   }
   
   public void replaceNonMember(int index, NonMember nonMember)
   {
      nonMemberList.set(index, nonMember);
   }
   
   public NonMember getNonMember(int index)
   {
      return nonMemberList.get(index);
   }
   
   public int getNonMemberIndex(NonMember nonMember)
   {
      return nonMemberList.indexOf(nonMember);
   }
   
   public void deleteNonMember(int index)
   {
      nonMemberList.remove(index);
   }
   
   public boolean checkForDuplicates(NonMemberList nonMemberList, NonMember nonMember)
   {
      boolean status = false;
      
      for(int i = 0; i < nonMemberList.size(); i++)
      {
         if(nonMember.equals(nonMemberList.getNonMember(i)))
            return true;
      }
      
      return status;
   }
}
