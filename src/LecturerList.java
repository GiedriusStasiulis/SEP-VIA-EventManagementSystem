

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;



public class LecturerList implements Serializable  
{
   private static final long serialVersionUID = 1L;
   public ArrayList<Lecturer>lecturerList;
   public LecturerList()
   {
      lecturerList=new ArrayList<>();
   }
   public void addLecturer(Lecturer lecturer)
   {
      lecturerList.add(lecturer);
   }
   
   public Lecturer getByName(String name)
   {
      for(int i=0;i<lecturerList.size();i++)
      {
         if(lecturerList.get(i).getName().equalsIgnoreCase(name))
         {
           return lecturerList.get(i); 
         }
      }
      return null;
   }
   public String toString()
   {
      String temp="";
      for(int i=0;i<lecturerList.size();i++)
      {
         temp+=lecturerList.get(i).toString();
      }
      return temp;
   }
   public Lecturer getLecturer(int index)
   {
      return lecturerList.get(index);
   }
   public int size()
   {
     return lecturerList.size();
   }
  
}
