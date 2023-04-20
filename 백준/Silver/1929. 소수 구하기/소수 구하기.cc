#include "bits/stdc++.h"

using namespace std;

int M, N;
bool is_not_prime[1000001];

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> M >> N;

    is_not_prime[1] = true;
    for (int i = 2; i <= N; ++i) {
        if(is_not_prime[i]){
            continue;
        }

        for (int j = 2; j <= N/i; ++j) {
            if (i * j <= N) {
                is_not_prime[i * j] = true;
            } else {
                break;
            }
        }
    }

    for (int i = M; i <= N; i++) {
        if (!is_not_prime[i]) {
            cout << i << "\n";
        }
    }

    return 0;
}
