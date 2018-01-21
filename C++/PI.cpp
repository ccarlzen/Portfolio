/******************************************************************************\
| Program	:	PI.cpp														   |
| Problem	:	Approximate PI												   |
| Purpose	:	Demonstrate abilities of the while loop						   |
| Author	:	Casey Carlzen												   |
| Date		:	4/29/2014													   |
\******************************************************************************/


#include <iostream>
using namespace std;

int main(void)
{
	// Data Field to hold our approximate value for pi
	double pi;
	// Counter for while loop and field for denominator
	int n = 0, denominator = 1;

	cout << "\nUsing the while loop this program will now approximate the" <<
		"value of pi by adding 20 terms in the following sequence:\n\n" <<
		"   pi = 4(1-1/3+1/5-1/7+1/9 ...)\n\n";

	while (++n <= 20)
	{
		if (n % 2 == 1)
			pi += (1.0/denominator);
		else
			pi -= (1.0/denominator);

		denominator += 2;
	}

	pi *= 4;

	cout << "\nApproximate value of pi:  " << pi << "\n";

	cout << "\nUsing the do loop, this program will now approximate the" <<
		"value of pi by adding 20 terms of the previous sequence\n\n";

	// Reset data field values
	pi = 0;
	n = 1;
	denominator = 1;

	do
	{
		if (n % 2 == 1)
			pi += (1.0/denominator);
		else
			pi -= (1.0/denominator);

		denominator += 2;
	} while (n++ < 20);

	pi *= 4;

	cout << "\nApproximate value of pi:  " << pi << "\n";

	return 1;
}









