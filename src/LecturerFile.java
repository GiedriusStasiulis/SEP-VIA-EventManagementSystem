
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

public class LecturerFile 
{
   private File file;

   private LecturerList currentLecturerList = new LecturerList();
   private LecturerList currentLecturers = new LecturerList();;
   private LecturerList lecturerList = new LecturerList();

   public LecturerFile(String filename) 
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

   public LecturerList readLecturerTextFile() throws FileNotFoundException, ParseException 
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

            String lecturerName = lineToken[0].trim();
            String lecturerCategory = lineToken[1].trim();
            String lecturerPhoneNumber=lineToken[2].trim();
            int tempPhoneNumber=Integer.parseInt(lecturerPhoneNumber);
            String lecturerEmail=lineToken[3].trim();

            Lecturer lecturer = new Lecturer(lecturerName, lecturerCategory,tempPhoneNumber,lecturerEmail);
            currentLecturerList.addLecturer(lecturer);
         }
         return currentLecturerList;
      }

      finally 
      {
         input.close();
      }
   }

   public void writeTextFile(LecturerList lecturerList) throws FileNotFoundException
      {
         PrintWriter output = null;
         try
         {
          output = new PrintWriter(file);
            
          for (int i = 0; i < lecturerList.size(); i++) 
            {
               output.println(lecturerList.getLecturer(i).getName() + "," + lecturerList.getLecturer(i).getEmail());
          }
            output.flush();
         }
         finally
         {
           output.close();
         }
      }
}