/******************************************************************************\
| Program	:	TRIANGLE.cpp												   |
| Problem	:	Displays a pattern based function argument					   |
| Purpose	:	Illustrate application of parameter passing					   |
| Author	:	Casey Carlzen												   |
| Date		:	4/29/2014													   |
| Modified	:	5/1/2014													   |
\******************************************************************************/

#include <iostream>
using namespace std;

void triangle (int n)
{
	int i, j;

	for (i = 0; i < n; i++)
	{
		for (j = n - i; j > 0; j--)
		{
			cout << " ";
		}

		for (j = n - (n - i); j > 0; j--)
		{
			cout << "*";
		}
		cout << "\n";
	}

	for (i = n; i > 0; i--)
	{
		for (j = n - i; j > 0; j--)
		{
			cout << " ";
		}

		for (j = n - (n - i); j > 0; j--)
		{
			cout << "*";
		}
		cout << "\n";
	}

}

int main (void)
{
	int m, flag = 1;

	while(flag)
	{
		cout << "\nEnter number to display triangle: ";
		cin >> m;

		if (m < 0 || m >= 80)
			flag = 0;
		else
			triangle(m);
	}

	return 1;
}