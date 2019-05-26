// Lab34ast.java
// The Student Records Doubly Linked List Program
// Student Version


import java.io.*;
import java.util.*;
import java.text.DecimalFormat;


public class Lab34
{
    public static void main(String args[]) throws IOException
    {
        System.out.println("Lab 34");
        DoubleList studentList = new DoubleList();
        studentList.getList();
        studentList.displayAll();
        studentList.pause();

        studentList.displayHonorRoll();
        studentList.pause();

        studentList.displayAcademicProbation();
        studentList.pause();

        int studentID = getID();
        Student2Node nodeRef = studentList.search(studentID);

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


class Student2Node
{
    private String name;
    private int id;
    private int age;
    private double gpa;
    private Student2Node forward;
    private Student2Node back;

    public Student2Node (String n, int ID, int a, double g, Student2Node f, Student2Node b)
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
    public Student2Node getForward ()			{ return forward;	}
    public Student2Node getBack ()				{ return back;		}

    public void setName (String n)				{ name    = n;		}
    public void setID (int ID)			 		{ id      = ID;		}
    public void setAgee (int a)					{ age     = a;		}
    public void setGPA (double g)				{ gpa     = g;		}
    public void setForward (Student2Node f)		{ forward = f; 		}
    public void setBack (Student2Node b)		{ back    = b;		}
}



class DoubleList
{
    Student2Node front, back;
    DecimalFormat output;

    public DoubleList()
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

            Student2Node newStudent = new Student2Node(name,id,age,gpa,null,null);
            System.out.println("student created");
            insert(newStudent);
        }
        inStream.close();

    }

    //front.getBack()==null and back.getForward()==null
    private void insert(Student2Node newStudent)
    {
        Student2Node p = newStudent;
        Student2Node t = null;

        if(front==null)
        {
            front=p;
            back=p;
            front.setBack(null);
            back.setForward(null);
        }
        else{
            if(p.getGPA()>front.getGPA()){
                p.setForward(front);
                p.setBack(null);
                if(front!=null) front.setBack(p);
                front = p;
            }
            else {
                if (p.getGPA() < back.getGPA()) {
                    p.setForward(null);
                    p.setBack(back);
                    back.setForward(p);
                    back = p;
                }

                else {
                    t = back;
                    while (t != null && p.getGPA() > t.getGPA()) {
                        t = t.getBack();
                    }
                    //if(t.getForward()!=null)
                    p.setForward(t.getForward());
                    t.setForward(p);
                    p.setBack(t);
                    if (p.getForward() != null)
                        p.getForward().setBack(p);
                }
            }
        }

    }

    public void displayAll()
    {
        System.out.println("\nDISPLAYING ALL STUDENTS");
        System.out.println("\nStudent ID#     Student Name            Age         GPA");
        System.out.println("============================================================");
        Student2Node temp = front;
        while(temp!=null){
            System.out.print(temp.getID()+"          ");
            System.out.print(temp.getName());
            writeSpaces(24,temp.getName());
            System.out.print(temp.getAge());
            System.out.print("          ");
            System.out.print(temp.getGPA());
            System.out.println();
            temp = temp.getForward();
        }
        System.out.println();

    }

    public void writeSpaces(int num,String a){
        int spaces = num-a.length();
        String space="";
        for(int b = 0; b<spaces;b++)
            space+=" ";
        System.out.print(space);
    }

    public void displayHonorRoll()
    {
        System.out.println("\nDISPLAYING HONOR ROLL STUDENTS");
        System.out.println("\nStudent ID#     Student Name            Age         GPA");
        System.out.println("============================================================");
        Student2Node temp = front;
        while(temp!=null){
            if(temp.getGPA()>=3.5){
                System.out.print(temp.getID()+"          ");
                System.out.print(temp.getName());
                writeSpaces(24,temp.getName());
                System.out.print(temp.getAge());
                System.out.print("          ");
                System.out.print(temp.getGPA());
                System.out.println();}
            temp = temp.getForward();
        }
        System.out.println();

    }

    public void displayAcademicProbation()
    {
        System.out.println("\nDISPLAYING ACADEMIC PROBATION STUDENTS");
        System.out.println("\nStudent ID#     Student Name            Age         GPA");
        System.out.println("============================================================");
        Student2Node temp = back;
        while(temp!=null){
            if (temp.getGPA() <= 2){
                System.out.print(temp.getID()+"          ");
                System.out.print(temp.getName());
                writeSpaces(24,temp.getName());
                System.out.print(temp.getAge());
                System.out.print("          ");
                System.out.print(temp.getGPA());
                System.out.println();}
            temp = temp.getBack();
        }
        System.out.println();

    }

    public void pause()
    {
        Scanner input = new Scanner(System.in);
        String dummy;
        System.out.println("\nPress <Enter> to continue.");
        dummy = input.nextLine();
    }

    public void displayStudent(Student2Node p) {
        if(p!=null){
            System.out.println("Student record for: " + p.getID());
            System.out.println();
            System.out.println("Name: " + p.getName());
            System.out.println("Age: " + p.getAge());
            System.out.println("GPA: " + p.getGPA());
            System.out.println();}
        else System.out.println("Student not found.");
    }

    public Student2Node search(int studentID)
    {
        // returns a reference to the proper Student2Node if a student with the matching ID is found
        // returns null otherwise
        Student2Node temp =front;
        while(temp!=null){
            if(temp.getID()==studentID)
                return temp;
            temp=temp.getForward();
        }
        return null;

    }

    public void delete(Student2Node p)
    {
        if(front==null || p==null)
            return;

        if(front==p)
            front = p.getForward();

        if (p.getForward() != null) {
            p.getForward().setBack(p.getBack());
        }

        if (p.getBack()!= null) {
            p.getBack().setForward(p.getForward());
        }

        return;

    }
}

