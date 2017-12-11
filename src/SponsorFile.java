import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *The SponsorFile class is responsible for handling operations when reading and writing to a text files.
 * 
 * @author Group#2 *
 */

public class SponsorFile 
{
   private File file;
   private String filename = "SponsorList.txt";

   private SponsorList currentSponsorList = new SponsorList();
   private SponsorList currentSponsors = new SponsorList();;
   private SponsorList SponsorList = new SponsorList();

   public SponsorFile(String filename) 
   {
      setFile(filename);
   }

   public void setFile(String filename) 
   {
      file = new File(filename);
   }

   public File getFile() 
   {
      return this.file;
   }

   public SponsorList readSponsorTextFile() throws FileNotFoundException, ParseException 
   {
      Scanner input = null;
      
      try 
      {
         input = new Scanner(file);
         int lineNumber = 0;
         while (input.hasNext()) 
         {
            String line = input.nextLine();
            String[] lineToken = line.split(",");
            lineNumber++;

            String SponsorName = lineToken[0].trim();
            String SponsorEmail = lineToken[1].trim();
            String SponsorPhone = lineToken[2].trim();

            Sponsor Sponsor = new Sponsor(SponsorName, SponsorEmail, SponsorPhone);
            currentSponsorList.addSponsorToList(Sponsor);
         }
         return currentSponsorList;
      }

      finally 
      {
         input.close();
      }
   }

   public void writeTextFile(SponsorList SponsorList) throws FileNotFoundException
      {
         PrintWriter output = null;
         try
         {
          output = new PrintWriter(file);
            
          for (int i = 0; i < SponsorList.size(); i++) 
            {
               output.println(SponsorList.getSponsor(i).getName() + "," + SponsorList.getSponsor(i).getEmail() 
                     +"," +SponsorList.getSponsor(i).getPhone());
          }
            output.flush();
         }
         finally
         {
           output.close();
         }
      }
}