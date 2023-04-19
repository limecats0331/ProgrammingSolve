#include "bits/stdc++.h"

using namespace std;

int N;
vector<pair<int, int>> meetings;

bool compair(pair<int, int> p1, pair<int, int> p2) {
    if (p1.second != p2.second) {
        return p1.second < p2.second;
    }
    return p1.first < p2.first;
}

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> N;
    for (int i = 0; i < N; ++i) {
        int st, en;
        cin >> st >> en;
        meetings.push_back(pair<int, int>{st, en});
    }

    sort(meetings.begin(), meetings.end(), compair);

    int end = -1;
    int cnt = 0;
    for (pair<int, int> each: meetings) {
        if (end <= each.first) {
           cnt ++;
           end = each.second;
        }
    }

    cout << cnt;

    return 0;
}
