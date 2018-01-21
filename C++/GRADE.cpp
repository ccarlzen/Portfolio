/******************************************************************************\
| Program	:	GRADE.cpp													   |
| Problem	:	Utilize capabilities of switch and if statements			   |
| Purpose	:	Display a grade that corresponds with user input			   |
| Author	:	Casey Carlzen												   |
| Date		:	4/29/2014													   |
\******************************************************************************/

#include <iostream>
using namespace std;

int main (void)
{
	int pts, flag = 1, mod;

	cout << "\nUsing nested if statements this program will display the grade"
		<< "associated with points. Enter a negative integer to end the "
		<< "program.\n";

	while (flag)
	{
		cout << "\nPlease enter the number of points: ";

		cin >> pts;

		if (pts >= 90)
			cout << "\n\nGrade : A\n";
		else if (pts >= 80)
			cout << "\n\nGrade : B\n";
		else if (pts >= 70)
			cout << "\n\nGrade : C\n";
		else if (pts >= 60)
			cout << "\n\nGrade : D\n";
		else if (pts >= 0)
			cout << "\n\nGrade : F\n";
		else
			flag = 0;
	}

	cout << "\nUsing the switch statement this program will display the grade"
		<< "associated with points. Enter a negative integer to end the "
		<< "program.\n";

	flag = 1;

	while (flag)
	{
		cout << "\nPlease enter the number of points: ";

		cin >> pts;

		pts = pts / 10;
		
		switch (pts)
		{
		case 10	:
		case 9 	:
			{
				cout << "\n\nGrade : A\n";
				break;
			}
		case 8  :
			{
				cout << "\n\nGrade : B\n";
				break;
			}
		case 7 	:
			{
				cout << "\n\nGrade : C\n";
				break;
			}
		case 6 	:
			{
				cout << "\n\nGrade : D\n";
				break;
			}
		case 5 	:
		case 4  :
		case 3  :
		case 2  :
		case 1  :
		case 0  :
			{
				cout << "\n\nGrade : F\n";
				break;
			}
		default:
			{
				flag = 0;
				break;
			}
		}
	}

	return 1;
}