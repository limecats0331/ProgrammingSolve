#include "bits/stdc++.h"

using namespace std;
using ll = long long;
const ll INF = 1e18;

vector<int> str1_cnt(27);
vector<int> str2_cnt(27);

int main() {
    cin.tie(0);
    ios::sync_with_stdio(0);

    string str1, str2;
    cin >> str1 >> str2;

    for (int i = 0; i < str1.length(); ++i) {
        str1_cnt[str1[i] - 'a']++;
    }
    for (int i = 0; i < str2.length(); ++i) {
        str2_cnt[str2[i] - 'a']++;
    }

    int answer = 0;
    for (int i = 0; i < 27; ++i) {
        answer += abs(str1_cnt[i] - str2_cnt[i]);
    }

    cout << answer;

    return 0;
}

