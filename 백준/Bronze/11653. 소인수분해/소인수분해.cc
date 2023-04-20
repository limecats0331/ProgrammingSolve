#include "bits/stdc++.h"

using namespace std;

int N;

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> N;

    while (N != 1) {
        for (int i = 2; i <= N; ++i) {
            if (N % i == 0) {
               cout << i << "\n";
               N /= i;
               break;
            }
        }
    }

    return 0;
}
