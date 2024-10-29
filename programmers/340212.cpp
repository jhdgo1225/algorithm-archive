// [PCCP 기출문제] 2번 / 퍼즐 게임 챌린지
#include <vector>
using namespace std;

int solution(vector<int> diffs, vector<int> times, long long limit) {
    int answer = 0;
    int low = 1;
    int high = 100000;
    int mid;
    long long cur;
    while (low < high)
    {
        mid = (low + high) / 2;
        cur = 0;
        for (int i=0; i<diffs.size(); i++)
        {
            if (mid >= diffs[i]) cur += times[i];
            else cur += ((times[i] + times[i-1]) * (long long)(diffs[i] - mid) + times[i]);
            if(cur > limit)
                break;
        }
        if (cur > limit)
            low = mid + 1;
        else
            high = mid;
    }
    answer = high;
    return answer;
}