#include "bits/stdc++.h"

using namespace std;

int N;
int lopes[100001];

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> N;
    for (int i = 0; i < N; ++i) {
        cin >> lopes[i];
    }

    sort(lopes, lopes + N, greater<int>());

    int answer = -1;
    for (int i = 0; i < N; ++i) {
        if (lopes[i] * (i + 1) > answer) {
            answer = lopes[i] * (i + 1);
        }
    }

    cout << answer;

    return 0;
}
