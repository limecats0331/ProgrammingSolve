#include "bits/stdc++.h"

using namespace std;

int M, N;
vector<int> nums;

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> N;
    for (int i = 0; i < N; ++i) {
        int tmp;
        cin >> tmp;
        nums.push_back(tmp);
    }
    sort(nums.begin(),nums.end());

    cin >> M;
    for (int i = 0; i < M; ++i) {
        int target;
        cin >> target;
        cout << binary_search(nums.begin(), nums.end(), target) << "\n";
    }
    return 0;
}
