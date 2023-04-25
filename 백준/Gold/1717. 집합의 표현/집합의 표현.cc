#include "bits/stdc++.h"

using namespace std;

int N, M;
int root[1000001];

int find_root(int num) {
    if (root[num] == num) {
        return num;
    }
    return root[num] = find_root(root[num]);
}

void merge(int n1, int n2) {
    n1 = find_root(n1);
    n2 = find_root(n2);
    if (n1 == n2) {
        return;
    }
    root[n1] = n2;
}

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> N >> M;

    for (int i = 1; i <= N; ++i) {
        root[i] = i;
    }

    for (int i = 0; i < M; ++i) {
        int command, n1, n2;
        cin >> command >> n1 >> n2;
        if (command == 0) {
            merge(n1, n2);
        } else {
            string answer = find_root(n1) == find_root(n2) ? "YES" : "NO";
            cout << answer << "\n";
        }
    }

    return 0;
}
