/******************************************************************************\
| Program	:	SUM.cpp														   |
| Problem	:	Sum the components of an array								   |
| Purpose	:	Analyze execution time of recurrsive vs. iterative function	   |
| Author	:	Casey Carlzen												   |
| Date		:	5/1/2014													   |
| Modified	:																   |
\******************************************************************************/

#include <iostream>
#include <cstdlib>
#include <ctime>
using namespace std;

double sum (double a[], int na)
{
	double o = 0;

	for (int i = 0; i < na; i++)
		o += a[i];

	return o;
}

double sumRec (double a[], int na)
{
	static double o = 0;

	if (na)
	{
		sumRec (a, na - 1);
		o += a[na];
	}

	return o;
}

void fill (double a[], int na)
{
	for (int i = 0; i < na; i++)
		a[i] = double(rand())/double(RAND_MAX);
}

double sec(void)
{
	return double(clock())/CLOCKS_PER_SEC;
}

int main (void)
{
	static int na = 50000;
	double a[na];

	fill (a, na);
	cout << "\nStarting iterative function at " << sec;
	cout << "\nThe sum of the components of the array is " << sum(a, na);
	cout << "\nEnding iterative function at " << sec;
	cout << "\n\nStarting recurssive function at " << sec;
	cout << "\nThe sum of the components of the array is (rec) " << sumRec(a, na);
	cout << "\nEnding recurssive function at " << sec;

	return 0;
}
