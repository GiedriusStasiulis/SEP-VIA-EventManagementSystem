import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class MemberFile {
	private int sw = 0;
	private File file;
	private String filename = "MemberList.txt";

	private MemberList currentMemberList = new MemberList();
	private MemberList currentMembers = new MemberList();;
	private MemberList memberList = new MemberList();

	public MemberFile(String filename) {
		setFile(filename);
	}

	public void setFile(String filename) {
		file = new File(filename);
	}

	public File getFile() {
		return this.file;
	}
	

	public MemberList readMemberTextFile() throws FileNotFoundException, ParseException {
		Scanner input = null;

		try {
			input = new Scanner(file);
			int lineNumber = 0;
			while (input.hasNext()) {
				String line = input.nextLine();
				String[] lineToken = line.split(",");
				lineNumber++;

				String memberName = lineToken[0].trim();
				String memberEmail = lineToken[1].trim();

				Member member = new Member(memberName, memberEmail);
				currentMemberList.addMemberToList(member);
			}
			return currentMemberList;
		}

		finally {
			input.close();
		}
	}

	public void writeMemberTextFile(MemberList memberList) throws ParseException, FileNotFoundException, CloneNotSupportedException 
	{		
		PrintWriter output = null;		
		
		switch(sw)
		{
		case 0:
			System.out.println("Reading from file");
			currentMembers = readMemberTextFile();
			
			for(int i = 0; i < currentMembers.size(); i++)
			{
				memberList.addMemberToList(currentMembers.getMember(i));				
			}
			
			currentMembers.clearMemberList();
			System.out.println(memberList.size());			
			
			sw = 1;			
			
		case 1:
			//System.out.println("Writing to file");			
			//System.out.println(memberList.size());
			
			output = new PrintWriter(file);
			
			for (int i = 0; i < memberList.size(); i++) 
			{
				output.println(memberList.getMember(i).getName() + "," + memberList.getMember(i).getEmail());
			}
			output.flush();
			output.close();
		
			break;
		}
	}
}
