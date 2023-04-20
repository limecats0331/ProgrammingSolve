#include "bits/stdc++.h"

using namespace std;

int N;
vector<int> nums;
vector<int> input;

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> N;
    for (int i = 0; i < N; ++i) {
        int tmp;
        cin >> tmp;
        nums.push_back(tmp);
        input.push_back(tmp);
    }

    sort(nums.begin(), nums.end());
    nums.erase(unique(nums.begin(), nums.end()), nums.end());

    for (int i = 0; i < N; ++i) {
        cout << lower_bound(nums.begin(), nums.end(), input[i]) - nums.begin() << " ";
    }

    return 0;
}
