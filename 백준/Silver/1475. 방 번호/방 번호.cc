#include "bits/stdc++.h"

using namespace std;

int N;
int cnt[10];

int main() {
    cin.tie(0);
    ios::sync_with_stdio(0);

    cin >> N;
    while (N > 0) {
        int num = N % 10;
        cnt[num]++;
        N /= 10;
    }

    int avg = (cnt[6] + cnt[9]) % 2 == 0 ? (cnt[6] + cnt[9]) / 2 : (cnt[6] + cnt[9] + 1) / 2;
    cnt[6] = avg;
    cnt[9] = avg;

    int answer = -1;
    for (int i = 0; i < 10; ++i) {
        answer = max(answer, cnt[i]);
    }
    cout << answer;

    return 0;
}