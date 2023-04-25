#include "bits/stdc++.h"

using namespace std;

int N, M;
int parents[201];
int cities[1001];

int find_root(int num) {
    if (parents[num] == num) return num;
    return parents[num] = find_root(parents[num]);
}

void merge(int n1, int n2) {
    n1 = find_root(n1);
    n2 = find_root(n2);
    if (n1 == n2) return;
    parents[n1] = n2;
}

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> N >> M;

    for (int i = 1; i <= N; ++i) {
        parents[i] = i;
    }

    for (int i = 1; i <= N; ++i) {
        for (int j = 1; j <= N; ++j) {
            int conn;
            cin >> conn;
            if (conn == 1) merge(i, j);
        }
    }

    for (int i = 0; i < M; ++i) {
        cin >> cities[i];
    }

    int root = find_root(cities[0]);
    for (int i = 1; i < M; ++i) {
        if (find_root(cities[i]) != root) {
            cout << "NO";
            return 0;
        }
    }
    cout << "YES";

    return 0;
}
