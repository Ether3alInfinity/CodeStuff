// Lab34ast.java
// The Student Records Doubly Linked List Program
// Student Version 


import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

      
public class Lab34ast
{
	public static void main(String args[]) throws IOException
	{
		Double_List studentList = new Double_List();
		studentList.getList();
		studentList.displayAll();
		studentList.pause();
		
		studentList.displayHonorRoll();
		studentList.pause();
		
		studentList.displayAcademicProbation();
		studentList.pause();
		
		int studentID = getID();
		Student2_Node nodeRef = studentList.search(studentID);

		if (nodeRef == null)
		    System.out.println("There is no student with an ID# of "+studentID+".\n");
		else
		{
			studentList.displayStudent(nodeRef);   // displays the information for the found student
			studentList.pause();
			studentList.delete(nodeRef);           // remove the same student from the List
			studentList.displayAll();
			studentList.pause();
		}
	}
	
	public static int getID()
	{
		Scanner input = new Scanner(System.in);	 
		System.out.print("\nEnter the 6-digit ID of the student.  { 100000 - 999999 }  -->  ");
		return input.nextInt();
	}
}


class Student2_Node
{
	private String name;
	private int id;
	private int age;
	private double gpa;
	private Student2_Node forward;
	private Student2_Node back;
	
	public Student2_Node (String n, int ID, int a, double g, Student2_Node f, Student2_Node b)
	{
		name    = n;
		id      = ID;
		age     = a;
		gpa     = g;
		forward = f;
		back    = b;
	}
	
	public String getName ()					{ return name;		}
	public int getID ()							{ return id;		}
	public int getAge ()						{ return age;		}
	public double getGPA ()						{ return gpa;		}
	public Student2_Node getForward ()			{ return forward;	}
	public Student2_Node getBack ()				{ return back;		}
	
	public void setName (String n)				{ name    = n;		}
	public void setID (int ID)			 		{ id      = ID;		}
	public void setAgee (int a)					{ age     = a;		}
	public void setGOA (double g)				{ gpa     = g;		}
	public void setForward (Student2_Node f)		{ forward = f; 		}
	public void setBack (Student2_Node b)		{ back    = b;		}
}



class Double_List
{
	Student2_Node front, back;
	DecimalFormat output;
   
	public Double_List()
	{
		front = back = null;       
		output  = new DecimalFormat("0.000");
	}

	public void getList() throws IOException
	{
		FileReader inFile = new FileReader("students2.dat");	
		BufferedReader inStream = new BufferedReader(inFile);	     
		String s1,s2,s3,s4;
		int age, id;
		double gpa;						
		while( ((s1 = inStream.readLine()) != null) && 
			   ((s2 = inStream.readLine()) != null) && 
			   ((s3 = inStream.readLine()) != null) &&
			   ((s4 = inStream.readLine()) != null) )	
		{
			String name = s1;
			id = Integer.parseInt(s2);
			age = Integer.parseInt(s3);
			gpa = Double.parseDouble(s4);

			Student2_Node newStudent = new Student2_Node(name,id,age,gpa,null,null);
			insert(newStudent);
		}   
		inStream.close(); 
					
	}
      
    private void insert(Student2_Node newStudent)
    {

		if (front == null) {
			front = back = newStudent;
			System.out.println(front.getName()+" added");
			front.setBack(null);
			System.out.println(front.getName()+" set back to null");
		}
		else{
			if (newStudent.getGPA()>=front.getGPA()){
				newStudent.setForward(front);
				front.setBack(newStudent);
				System.out.println(front.getName()+" set back to "+newStudent.getName());
				front = newStudent;
				System.out.println(front.getName()+" added");
			}else if(newStudent.getGPA() <= back.getGPA()){
				back.setForward(newStudent);
				newStudent.setBack(back);
				back = newStudent;
			}
			else{

				//NEED TO ADD STUDENT
				Student2_Node temp = front;
				System.out.println("temp is now set to and front is "+temp.getName().toUpperCase());
				/**AT THIS POINT TEMP IS VAL*/
				while (newStudent.getGPA() < temp.getGPA()){
					//System.out.print("Grades of "+newStudent.getName()+" are less than "+temp.getName()+"--");
					//System.out.println();
					//System.out.println("case 2");
					if (temp.getForward()!=null) {
						temp = temp.getForward();
						//package
						/*newStudent.setBack(temp.getBack());
						if (temp.getBack()!=null)
							temp.getBack().setForward(newStudent);
						temp.setBack(newStudent);
						newStudent.setForward(temp);*///stackoverflowError
						/**ISSUE: TEMP IS MOVING DOWN*/
						/**ISSUE: PPL MOVE THRU REAGOR*/

						//System.out.println("CASE 2");
						System.out.println("Moving thru: "+temp.getName());
					}
					else {System.out.println("NPE");
					//NEED SOME WAY TO INTRODUCE FWD TO BART
					break;}
				}//set new Student in  front of temp
				/**at this point temp is equal to bart reaqor*/
				System.out.println("FRONT IS "+front.getName());
				//temp = front;
				newStudent.setBack(temp.getBack());
				System.out.println(newStudent.getName()+" set back to "+temp.getBack().getName());
				if (temp.getBack()!=null){
					temp.getBack().setForward(newStudent);
					System.out.println(temp.getBack().getName()+" set fwd to "+newStudent.getName());
				}else System.err.println(temp.getName());
				temp.setBack(newStudent);
				System.out.println(temp.getName()+" set back to "+newStudent.getName());
				newStudent.setForward(temp);
				System.out.println(newStudent.getName()+" set fwd to "+temp.getName());
				System.out.println(newStudent.getName()+" added");
			}
		}
    }


	public void displayAll()
	{
		System.out.println("\nDISPLAYING ALL STUDENTS");
		System.out.println("\nStudent ID#     Student Name            Age         GPA");
		System.out.println("============================================================");
		//printLinks(front);
		print(front);
	}
	void print(Student2_Node s){
		if (s!=null) {
			displayStudent(s);
			print(s.getForward());
		}else System.out.println();
	}
	void printLinks(Student2_Node s){
		if (s!=null) {
			System.out.println(s.getName().toUpperCase());
			if (s.getForward() != null)
				System.out.println("Forward: "+s.getForward().getGPA());
			else System.out.println("FWD NULL");
			if (s.getBack() != null)
				System.out.println("Backward: "+s.getBack().getGPA());
			else System.out.println("BKWD NULL");
			printLinks(s.getForward());
		}else System.out.println();
	}
	
	public void displayHonorRoll()
	{
		System.out.println("\nDISPLAYING HONOR ROLL STUDENTS");
		System.out.println("\nStudent ID#     Student Name            Age         GPA");
		System.out.println("============================================================");
		printHR(front);

	}
	void printHR(Student2_Node s){
		if (s!=null && s.getGPA() >= 3.5) {
			displayStudent(s);
			printHR(s.getForward());
		}else System.out.println();
	}
	void printAP(Student2_Node s){
		if (s!=null && s.getGPA() < 2.0) {
			displayStudent(s);
			printAP(s.getBack());
		}else System.out.println();
	}
		
	public void displayAcademicProbation()
	{
		System.out.println("\nDISPLAYING ACADEMIC PROBATION STUDENTS");
		System.out.println("\nStudent ID#     Student Name            Age         GPA");
		System.out.println("============================================================");
		printAP(back);

	}	
	
	public void pause() 
	{   
		Scanner input = new Scanner(System.in);	 
		String dummy;
		System.out.println("\nPress <Enter> to continue.");						
		dummy = input.nextLine();								
	}

	public void displayStudent(Student2_Node p)
	{
		String space = "                       ";
		for (int i=0; i<p.getName().length(); i++) space += "\b";
		//System.out.println("student printed");
		System.out.println("   "+p.getID()+"        "+p.getName()+space +
				""+p.getAge()+"          "+p.getGPA());
	}   

	public Student2_Node search(int studentID)
	{
		// returns a reference to the proper Student2_Node if a student with the matching ID is found
		// returns null otherwise
		Student2_Node temp = front;
		while (temp != null && temp.getID() != studentID){
			temp = temp.getForward();
		}
		return temp;

	}
	
	public void delete(Student2_Node p)
	{
		if (p.getBack()!=null)
			p.getBack().setForward(p.getForward());
		else front = p.getForward();
		if (p.getForward()!=null)
			p.getForward().setBack(p.getBack());
		else back = p.getBack();
	}
}    

