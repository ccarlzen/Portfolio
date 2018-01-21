/******************************************************************************\
| Program	:	LINECOUNT.cpp												   |
| Problem	:	Count uppercase, lowercase, and digits in a string			   |
| Purpose	:	Write functions to analyze C-Strings						   |
| Author	:	Casey Carlzen												   |
| Date		:	5/4/2014													   |
| Modified	:																   |
\******************************************************************************/

#include <iostream>
using namespace std;

void getline(char a[])
{
	cin >> a;

}

int isupper(char a[])
{
	int n = 0, i = 0;

	while (a[i] != '\0')
	{
		if ((int)a[i] >= 65 && (int)a[i] <= 90)
			n++;
		i++;
	}

return n;
}

int islower(char a[])
{
	int n = 0, i = 0;

	while (a[i] != '\0')
	{
		if ((int)a[i] >= 97 && (int)a[i] <= 122)
			n++;
		i++;
	}

return n;
}

int isdigit(char a[])
{
	int n = 0, i = 0;

	while (a[i] != '\0')
	{
		if ((int)a[i] >= 48 && (int)a[i] <= 57)
			n++;
		i++;
	}

return n;
}

int main(void)
{
	char n[100] = "ABCIAUWJAFASFBaijssfjahwfawhf123192084125";
	getline(n);

	cout << "\nThe string contains the following:\n";
	cout << "\n" << isupper(n) << " uppercase letters.\n";
	cout << "\n" << islower(n) << " lowercase letters.\n";
	cout << "\n" << isdigit(n) << " digits.\n";

	return 0;
}