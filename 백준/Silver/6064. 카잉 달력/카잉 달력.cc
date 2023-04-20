#include "bits/stdc++.h"

using namespace std;

int T;
int M, N, x, y;

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> T;
    for (int t = 0; t < T; ++t) {
        cin >> M >> N >> x >> y;

        if (x == M) x = 0;
        if (y == N) y = 0;

        int result = -1;
        for (int i = x; i <= lcm(N, M); i += M) {
            if(i == 0) continue;
            if (i % N == y) {
                result = i;
                break;
            }
        }
        cout << result << "\n";
    }
    return 0;
}
