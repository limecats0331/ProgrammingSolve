#include "bits/stdc++.h"

using namespace std;

int N, M;
int nums[1000001];

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> N >> M;
    for (int i = 0; i < N; ++i) {
        cin >> nums[i];
    }
    sort(nums, nums + N);

    int answer = INT32_MAX;
    int st = 0;
    int en = 0;
    while (true) {
        if (en == N) {
            break;
        }
        if (nums[en] - nums[st] < M) {
            en++;
        } else if (nums[en] - nums[st] == M) {
            answer = M;
            break;
        } else if (en == st) {
            en++;
        } else {
            answer = min(answer, nums[en] - nums[st]);
            st++;
        }
    }

    cout << answer;
    return 0;
}
