/***********************************************************************\
| Program:	STUDENT.CPP						|
| Problem:								|
| Purpose:	Store Student records					|
| Author:	Casey Carlzen						|
| Date:		April 15, 2014						|
\***********************************************************************/

#include <iostream>
#include <iomanip>
#include <fstream>
using namespace std;

class Student
{
	private:
		char first[], last[], const FN[]="student.dat";
		int age;
		double gpa;
		
	public:
		// Default constructor
		Student(){}
		
		// Parameterized Constructor
		Student(char &f, char &l, int a, double g)
		{
			first = f;
			last = l;
			
			// Check if age value is reasonable
			if (a > 0 && a < 150)
			{
				age = a;
			}
			else
			{
				cout << "Age out of bounds";
				age = -1;
			}
			
			// Check if GPA value is reasonable
			if (g > 0 || g < 5.0)
			{
				gpa = g;
			}
			else
			{
				cout << "GPA out of bounds";
				gpa = -1.0;
			}
		}
			
		
		// Reads input from keyboard and assigns data
		int read()
		{
			while (!cin.eof())
			{
				cout << "\nFirst name: ";
				cin >> first;
				cout << "\nLast name: ";
				cin >> last;
				cout << "\nAge: ";
				cin >> age;
				cout << "GPA: ";
				cin >> gpa;
			}
		}
			
		// Displays name, age, and GPA of a Student object
		void show()
		{
			cout << "\nName: " << last << ", " << first;
			cout << "\nAge: " << age << "    GPA: " << gpa;
		}
		
		// Writes a Student record to 'student.dat'
		int writefile()
		{
			ifstream IS1(FN, ios::in);
			
			cout << W(10) << last << W(10) << first <<
			W(5) << age << W(5) << gpa << '\n';
			
			IS1.close();
		}
		
		// Returns the GPA of a Student
		double getgpa()
		{
			return gpa;
		}
		
		// Creates and returns a new Student object from
		// 'student.dat'
		void readfile(){}
		};
		}
};
		
