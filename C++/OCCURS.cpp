/******************************************************************************\
| Program	:	OCCURS.cpp													   |
| Problem	:	Determine occurrances of a number within an array			   |
| Purpose	:	Demonstrate use of recurssive functions						   |
| Author	:	Casey Carlzen												   |
| Date		:	5/1/2014													   |
| Modified	:																   |
\******************************************************************************/

#include <iostream>
#include <cstdlib>
using namespace std;

void fill (int a[], int na)
{
	for (int i = 0; i < na; i++)
		a[i] = rand();
}

int occurs (int x, int a[], int na)
{
	int o = 0;

	for (int i = 0; i < na; i++)
	{
		if (a[i] == x)
			o++;
	}
	return o;
}

int occursRec (int x, int a[], int na)
{
	static int o = 0;

	if (na)
	{
		if (x == a[na - 1])
			o++;
		occursRec(x, a, na - 1);
	}

	return o;

}

int main (void)
{
	const int na = 50000;
	int a[na];
	fill (a, na);

	int i;
	cout << "\nEnter integer to find occurrances(recursive): ";
	cin >> i;

	cout << "\n" << i << " occurs " << occurs(i, a, na)
			<< " times.\n";

	cout << "\nEnter integer to find occurrances(iterative): ";
	cin >> i;

	cout << "\n" << i << " occurs " << occursRec(i, a, na)
			<< " times.\n";

	return 1;
}