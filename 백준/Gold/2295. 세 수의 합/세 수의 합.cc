#include "bits/stdc++.h"

using namespace std;

int N;
vector<int> nums;
vector<int> twos;

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> N;
    for (int i = 0; i < N; ++i) {
        int tmp;
        cin >> tmp;
        nums.push_back(tmp);
    }
    sort(nums.begin(), nums.end());

    for (int i = 0; i < N; ++i) {
        for (int j = i; j < N; ++j) {
            twos.push_back(nums[i] + nums[j]);
        }
    }

    sort(twos.begin(),twos.end());
    for (int i = N - 1; i > 0; --i) {
        for (int j = 0; j < i; ++j) {
            if (binary_search(twos.begin(), twos.end(), nums[i] - nums[j])) {
                cout << nums[i];
                return 0;
            }
        }
    }
    return 0;
}
