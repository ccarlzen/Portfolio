/******************************************************************************\
| Program	:	DATE.cpp													   |
| Problem	:	Display a formatted date 									   |
| Purpose	:	Parse C-strings and String class 							   |
| Author	:	Casey Carlzen												   |
| Date		:	5/1/2014													   |
| Modified	:	5/4/2014															   |
\******************************************************************************/

#include <iostream>
using namespace std;

void cstringDate (char a[])
{
	int i = 0, j = 0;
	switch (a[i])
	{
		case '0' :
			i++;
			switch (a[i])
			{
				case '1' : cout << "\nJanuary ";
					break;
				case '2' : cout << "\nFebruary ";
					break;
				case '3' : cout << "\nMarch ";
					break;
				case '4' : cout << "\nApril ";
					break;
				case '5' : cout << "\nMay ";
					break;
				case '6' : cout << "\nJune ";
					break;
				case '7' : cout << "\nJuly ";
					break;
				case '8' : cout << "\nAugust ";
					break;
				case '9' : cout << "\nSeptember ";
					break;
				default : cout << "\nError ";
					break;
			}
				break;
		case '1' :
			i++;
			switch (a[i])
			{
				case '0' : cout << "\nOctober ";
					break;
				case '1' : cout << "\nNovember ";
					break;
				case '2' : cout << "\nDecember ";
					break;
				default : cout << "\nError ";
					break;
			}
				break;
		default : cout << "\nError ";
	}
	i++;
	i++;
	cout << a[i];
	i++;
	cout << a[i] << ", 19";
	i++;
	i++;
	cout << a[i];
	i++;
	cout << a[i];
}

int main(void)
{
	char a[] = "01/19/72";
	char b[] = "02/19/72";
	char c[] = "03/19/72";
	char d[] = "04/19/72";
	char e[] = "05/19/72";
	char f[] = "06/19/72";
	char g[] = "07/19/72";
	char h[] = "08/19/72";
	char i[] = "09/19/72";
	char j[] = "10/19/72";
	char k[] = "11/19/72";
	char l[] = "12/19/72";
	
	cstringDate(a);
	cstringDate(b);
	cstringDate(c);
	cstringDate(d);
	cstringDate(e);
	cstringDate(f);
	cstringDate(g);
	cstringDate(h);
	cstringDate(i);
	cstringDate(j);
	cstringDate(k);
	cstringDate(l);

	return 0;
}