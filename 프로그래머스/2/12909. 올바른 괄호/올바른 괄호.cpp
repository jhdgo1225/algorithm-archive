#include <string>
#include <iostream>
#include <stack>

using namespace std;

bool solution(string s)
{
    bool answer = true;
    stack<int> st;
    for (int i=0; i<s.size(); i++)
    {
        int chk = (s[i] == '(') ? 0 : 1;
        if (!st.empty() && st.top() == 0 && chk) {
            st.pop();
        }
        else st.push(chk);
    }
    if (!st.empty()) answer = false;
    return answer;
}