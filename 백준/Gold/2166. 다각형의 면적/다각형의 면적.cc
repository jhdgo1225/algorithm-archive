#include <iostream>
using namespace std;

double X[10001];
double Y[10001];
double polygonArea(int numPoints) 
{ 
	double area = 0;
	int j = numPoints-1;

	for (int i=0; i<numPoints; i++)
	{
		area += (X[j] + X[i]) * (Y[j]-Y[i]); 
		j = i;
	}
	return abs(area)/2;
}

int main()
{
	int N; cin >> N;
	for (int i=0; i<N; i++) {
		cin >> X[i] >> Y[i];
	}
	cout << fixed;
	cout.precision(1);
	cout << polygonArea(N);
}