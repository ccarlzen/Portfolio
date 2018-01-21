/******************************************************************************\
| Program	:	FIBOTEST.cpp												   |
| Problem	:	Determine if an integer is a Fibonacci number				   |
| Purpose	:	Basic use of functions										   |
| Author	:	Casey Carlzen												   |
| Date		:	4/29/2014													   |
\******************************************************************************/

#include <iostream>
#include <cmath>
using namespace std;

int FiboTest(int n)
{
	int t1, t2, rt1, rt2;
	double r1, r2;

	t1 = 5*n*n+4;
	t2 = 5*n*n-4;

	rt1 = sqrt(t1);
	rt2 = sqrt(t2);

	r1 = int (rt1);
	r2 = int (rt2);

	r1 *= r1;
	r2 *= r2;
if
	 (r1 == t1 || r2 == t2)
		return 1;
	else
		return 0;
}

int main(void)
{
	int n;

	cout << "\nThis program will test whether or not a number is part of the"
		<< " Fibonacci sequence.\n\n";

	cout << "Enter a number to be tested: ";

	cin >> n;

	if (FiboTest(n))
		cout << "\n" << n << " is a Fibonacci number.\n";
	else
		cout << "\n" << n << " is not a Fibonacci number.\n";

	return 1;
}