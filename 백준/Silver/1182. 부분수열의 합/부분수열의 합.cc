#include "bits/stdc++.h"

using namespace std;

int N, sum, cnt;
vector<int> nums;
vector<bool> isSelect;

bool def() {
    int result = 0;
    for (int i = 0; i < N; ++i) {
        if (isSelect[i]) {
            result += nums[i];
        }
    }
    return sum == result;
}

void find(int depth) {
    if (N == depth) {
        if(def()) cnt++;
        return;
    }

    find(depth + 1);
    isSelect[depth] = true;
    find(depth + 1);
    isSelect[depth] = false;
}

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> N >> sum;

    for (int i = 0; i < N; ++i) {
        int num;
        cin >> num;
        nums.push_back(num);
    }
    isSelect = vector<bool>(N);

    find(0);
    if(sum == 0) cnt--;
    cout << cnt;

    return 0;
}
