/******************************************************************************\
| Program	:	AREACOMPARE.cpp												   |
| Problem	:	Determine if a triangle or rectangle has the greater area	   |
| Purpose	:	Demonstrate control structure applications					   |
| Author	:	Casey Carlzen												   |
| Date		:	4/29/2014													   |
\******************************************************************************/

#include <iostream>
#include <cmath>
using namespace std;

int main(void)
{
	int flag = 1;
	double a, b, c, A, B, aTri, aRec;

	cout << "\nThis program will evalute the area of a triangle and rectangle"
		<< " and display which is greater.\n\n";

	while (flag)
	{
		cout << "Enter three sides of a triangle: ";
		cin >> a >> b >> c;
		cout << "\nEnter two sides of a rectangle: ";
		cin >> A >> B;

		if (a <= 0 || b <= 0 || c <= 0 || A <= 0 || B <= 0)
			break;

		double s = (a + b + c)/2.0;
		aTri = sqrt(s*(s-a)*(s-b)*(s-c));

		aRec = A*B;

		if (aTri > aRec)
			cout << "\nThe area of triangle is greater than the area of " <<
				"rectangle\n\n";
		else if (aTri < aRec)
			cout << "\nThe area of triangle is less than the area of " <<
				"rectangle\n\n";
		else
			cout << "\nThe area of triangle is equal to the area of " <<
				"rectangle\n\n";
	}

	return 1;
}










