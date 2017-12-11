import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

//Abstract class Event
public class Event
{
      private String eventName;
      private LocalDate eventStartDate,eventEndDate;
      private boolean isFinalized;
      private String startTime,endTime;
      private int maxMembers;
      private double price,discount;
      
      ArrayList<Member> membersRegistered;
      ArrayList<Member> tempMembersRegistered;
      
      public Event(String eventName, LocalDate eventStartDate, LocalDate eventEndDate, String startTime, String endTime, int maxMembers, double price, double discount)
      {
         this.eventName=eventName;
         this.isFinalized=false;         
         this.eventStartDate=eventStartDate;
         this.eventEndDate=eventEndDate;
         this.startTime=startTime;
         this.endTime=endTime;
         this.maxMembers=maxMembers;
         this.price=price;
         this.discount=discount;
         this.membersRegistered= new ArrayList<Member>();
         this.tempMembersRegistered = new ArrayList<Member>();
      }
      
      public void setName(String name)
      {
         this.eventName=name;
      }
      public String getName()
      {
         return this.eventName;
      }
      public LocalDate getStartDate()
      {
         return this.eventStartDate;
      }
      public LocalDate getEndDate()
      {
         return this.eventEndDate;
      }
      public void setStartTime(String time)
      {
         this.startTime=time;
      }
      public void setEndTime(String time)
      {
         this.endTime=time;
      }
      public void setPrice(double price)
      {
         this.price=price;
      }
      public void setMaxMembers(int maxMembers)
      {
         this.maxMembers=maxMembers;
      }
      public void setDiscount(double discount)
      {
         this.discount=discount;
      }
      public void registerMember(Member addThis)
      {
         membersRegistered.add(addThis);
         
      }
      public double getPrice()
      {
         return this.price;
      }
      public double getDiscount()
      {
         return this.discount;
      }
      public int getMaxMembers()
      {
         return this.maxMembers;
      }
      public String getStartTime()
      {
         return this.startTime;
      }
      public String getEndTime()
      {
         return this.endTime;
      }
      public Member getMemberByIndex(int index)
      {
         return membersRegistered.get(index);
      }
      public String toString()
      {
         return this.eventName +" " + this.isFinalized;
      }
      public void writeEventToFile() //shitty method, creates a new file and overwrites...Will have to write all the Member list to the file every time we add a member.
      {
         String filename = "events.txt";
         File txtFile= new File(filename);
         try {
         PrintWriter outFile = new PrintWriter(txtFile);
         String outString = this.eventName+"; " + this.isFinalized; 
         outFile.println(outString);
         outFile.flush();
         outFile.close();
         }
         catch(FileNotFoundException fne)
         {
            fne.printStackTrace();
         }
         System.out.println("printed to file" + txtFile.getAbsolutePath());
         
      }
      
      public void writeRegisteredMembersToFile()
      {
         String filename = "members.txt";
         File txtFile = new File(filename);
         try
         {
            PrintWriter outFile = new PrintWriter(txtFile);
            for (int i=0; i<membersRegistered.size();i++)
            {
               
               outFile.println(membersRegistered.get(i).toString());
            }
            outFile.close();
            
         }
         catch(FileNotFoundException fne)
         {
            fne.printStackTrace();
         }
         System.out.println("printed members to: " +txtFile.getAbsolutePath());
         
      }
      public void constructMemberListFromFile(String filename)
      {
         File file = new File(filename);
         try
         {
            Scanner inFile = new Scanner(file);
            while(inFile.hasNext())
            {
               String line = inFile.nextLine();
               String[] content = line.split(";");
               String name = content[0].trim();
               int age = Integer.parseInt(content[1].trim());
               Member newMember= new Member(name,String.format("%s", age));
               tempMembersRegistered.add(newMember);
            }
                        
          inFile.close(); 
          
          for(int i=0;i<tempMembersRegistered.size();i++)
          {
             membersRegistered.add(i, tempMembersRegistered.get(i));
          }
          
          
         }
         catch(FileNotFoundException fne)
         {
            fne.printStackTrace();
         }
         
      }
      
      //add  abstract methods when other classes are done.
}
