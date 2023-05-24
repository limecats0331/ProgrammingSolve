#include "bits/stdc++.h"

using namespace std;

int N;
vector<pair<int, int>> arr;

bool compare(pair<int, int> n1, pair<int, int> n2) {
    if (n1.second == n2.second) {
        return n1.first < n2.first;
    }
    return n1.second < n2.second;
}

int main() {
    cin.tie(0);
    ios::sync_with_stdio(0);

    cin >> N;
    for (int i = 0; i < N; ++i) {
        int n1, n2;
        cin >> n1 >> n2;
        arr.push_back({n1, n2});
    }

    sort(arr.begin(), arr.end());

    int answer = 0;
    int end = INT32_MIN;
    for (int i = 0; i < arr.size(); i++) {
        auto now = arr[i];
        if (now.second <= end) continue;
        if (now.first <= end) {
            answer += now.second - end;
        } else {
            answer += now.second - now.first;
        }
        if (now.second > end) {
            end = now.second;
        }
    }

    cout << answer;

    return 0;
}

