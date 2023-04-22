#include "bits/stdc++.h"

using namespace std;

struct comp {
    bool operator()(int &n1, int &n2) {
        if (abs(n1) == abs(n2)) {
            return n1 > n2;
        }
        return abs(n1) > abs(n2);
    }
};

int N;
priority_queue<int, vector<int>, comp> pq;

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> N;
    for (int i = 0; i < N; ++i) {
        int tmp;
        cin >> tmp;
        if (tmp == 0) {
            if (pq.empty()) cout << 0 << "\n";
            else {
                cout << pq.top() << "\n";
                pq.pop();
            }
        } else {
            pq.push(tmp);
        }
    }

    return 0;
}
