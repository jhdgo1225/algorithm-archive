#include <iostream>
using namespace std;

int testArr[8];

void swap(int &a, int &b)
{
	a = a ^ b;
	b = a ^ b;
	a = a ^ b;
}

void tiltIntoLeft()
{
	int swapPos = 0;
	bool flag = false;
	for (int i=0; i<8; i++)
	{
		if (testArr[i] == 0)
			continue;
		if (swapPos - 1 >= 0 && !flag && testArr[swapPos - 1] == testArr[i])
		{
			testArr[swapPos - 1] *= 2;
			testArr[i] = 0;
			flag = true;
		}
		else {
			swap(testArr[i], testArr[swapPos++]);
			flag = false;
		}
	}
}

int main()
{
	for (int i=0; i<8; i++)
		cin >> testArr[i];
	tiltIntoLeft();
	for (int i=0; i<8; i++)
		cout << testArr[i] << ' ';
}