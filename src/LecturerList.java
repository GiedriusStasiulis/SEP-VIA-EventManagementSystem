import java.util.ArrayList;

public class LecturerList 
{
	private ArrayList<Lecturer> lecturerList;
	
	public LecturerList()
	{
		lecturerList = new ArrayList<Lecturer>();
	}
	
	public int size()
	{
		return lecturerList.size();
	}
	
	public void addLecturerToList(Lecturer lecturer)
	{
		lecturerList.add(lecturer);
	}
	
	public void replaceLecturer(int index, Lecturer lecturer)
	{
		lecturerList.set(index, lecturer);
	}
	
	public Lecturer getLecturer(int index)
	{
		return lecturerList.get(index);
	}
	
	public int getLecturerIndex(Lecturer lecturer)
	{
		return lecturerList.indexOf(lecturer);
	}
	
	public void deleteLecturer(int index)
	{
		lecturerList.remove(index);
	}
	
	public boolean checkForDuplicates(LecturerList lecturerList, Lecturer lecturer)
	{
		boolean status = false;
		
		for(int i = 0; i < lecturerList.size(); i++)
		{
			if(lecturer.equals(lecturerList.getLecturer(i)))
				return true;
		}
		
		return status;
	}
	
	public String toString()
	{
		String s = String.format("%s", lecturerList);
		return s;
	}
}