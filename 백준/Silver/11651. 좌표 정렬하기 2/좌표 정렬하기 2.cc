#include "bits/stdc++.h"

using namespace std;

bool comp(pair<int, int> num1, pair<int, int> num2) {
    if (num1.second != num2.second) {
        return num1.second < num2.second;
    } else {
        return num1.first < num2.first;
    }
}

int main() {
    ios::sync_with_stdio(false), cin.tie(nullptr);

    vector<pair<int, int>> nums;
    int N;
    cin >> N;
    for (int i = 0; i < N; ++i) {
        int tmpX;
        int tmpY;
        cin >> tmpX >> tmpY;
        nums.push_back({tmpX, tmpY});
    }

    stable_sort(nums.begin(), nums.begin() + nums.size(), comp);
    for (auto num: nums) {
        cout << num.first << " " << num.second << "\n";
    }

    return 0;
}