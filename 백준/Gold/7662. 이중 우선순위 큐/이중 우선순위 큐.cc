#include "bits/stdc++.h"

using namespace std;

int T;

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> T;
    for (int t = 0; t < T; ++t) {
        int k;
        cin >> k;

        multiset<int> Q;
        for (int i = 0; i < k; ++i) {
            char command;
            int num;
            cin >> command >> num;

            if (command == 'I') {
                Q.insert(num);
            } else {
                if (num == 1) {
                    if (Q.size() == 0) continue;
                    Q.erase(--Q.end());
                } else {
                    if (Q.size() == 0) continue;
                    Q.erase(Q.begin());
                }
            }
        }

        if (Q.size() == 0) {
            cout << "EMPTY" << "\n";
        } else {
            int q_min = *Q.begin();
            int q_max = *--Q.end();
            cout << q_max << " " << q_min << "\n";
        }
    }

    return 0;
}
