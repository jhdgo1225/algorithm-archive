// [PCCP 기출문제] 1번 / 동영상 재생기
#include <cstdlib>
#include <sstream>
#include <iostream>
#include <vector>

using namespace std;

int calcTime(string clock)
{
    stringstream ss(clock);
    string token;
    getline(ss, token, ':');
    int hour = atoi(token.c_str());
    getline(ss, token, ':');
    int minute = atoi(token.c_str());
    int res = hour * 60 + minute;
    return (res);
}

string solution(string video_len, string pos, string op_start, string op_end, vector<string> commands) {
    string answer = "";
    int videoLength = calcTime(video_len);
    int cur = calcTime(pos);
    int opStart = calcTime(op_start);
    int opEnd = calcTime(op_end);
    for (int i=0; i<commands.size(); i++)
    {
        if (cur >= opStart && cur <= opEnd) cur = opEnd;
        if (!commands[i].compare("prev"))
        {
            if (cur <= 10) cur = 0;
            else cur -= 10;
        }
        else if (!commands[i].compare("next"))
        {
            if (cur + 10 >= videoLength) cur = videoLength;
            else cur += 10;
        }
        if (cur >= opStart && cur <= opEnd) cur = opEnd;
    }
    answer = ((cur / 60 >= 0 && cur / 60 <= 9) ? "0" : "") + to_string(cur / 60) + ":" + ((cur % 60 >= 0 && cur % 60 <= 9) ? "0" : "") + to_string(cur % 60);
    return answer;
}
