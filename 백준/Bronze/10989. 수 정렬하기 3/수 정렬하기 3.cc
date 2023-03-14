#include "bits/stdc++.h"

using namespace std;

int nums[10001];

int main() {
    ios::sync_with_stdio(false), cin.tie(nullptr);

    int N;
    cin >> N;

    fill(nums, nums + 10001, 0);

    for (int i = 0; i < N; ++i) {
        int tmp;
        cin >> tmp;
        nums[tmp]++;
    }

    for (int i = 0; i < 10001; ++i) {
        if (nums[i] == 0)continue;
        for (int j = 0; j < nums[i]; ++j) {
            cout << i << "\n";
        }
    }

    return 0;
}