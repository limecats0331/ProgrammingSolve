#include "bits/stdc++.h"

using namespace std;

int N;
int cnt[2000001];

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> N;
    for (int i = 0; i < N; ++i) {
        int tmp;
        cin >> tmp;
        cnt[1000000 + tmp]++;
    }

    for (int i = 0; i < 2000001; ++i) {
        if (cnt[i] != 0) {
            for (int j = 0; j < cnt[i]; ++j) {
                cout << i - 1000000 << "\n";
            }
        }
    }

    return 0;
}
