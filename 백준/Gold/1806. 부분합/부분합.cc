#include "bits/stdc++.h"

using namespace std;

int N, S;
int nums[100001];

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> N >> S;
    for (int i = 0; i < N; ++i) {
        cin >> nums[i];
    }

    int answer = INT32_MAX;
    int sum = nums[0];
    int st = 0;
    int en = 1;

    while (en <= N) {
//        cout << "st:" << st << " en:" << en << " sum:" << sum << "\n";
        if (sum < S) {
            sum += nums[en++];
        } else {
            answer = min(answer, en - st);
            sum -= nums[st++];
        }
    }

    answer = answer == INT32_MAX ? 0 : answer;
    cout << answer;

    return 0;
}
