#include "bits/stdc++.h"

using namespace std;

int V, E;
//cost, node1, node2
tuple<int, int, int> edge[100001];
int root[10001];

int find_root(int num) {
    if (root[num] == num) {
        return num;
    }
    return root[num] = find_root(root[num]);
}

bool union_set(int n1, int n2) {
    n1 = find_root(n1);
    n2 = find_root(n2);
    if (n1 == n2) {
        return false;
    }
    root[n1] = n2;
    return true;
}

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> V >> E;
    for (int i = 1; i <= V; ++i) {
        root[i] = i;
    }
    for (int i = 0; i < E; ++i) {
        int n1, n2, c;
        cin >> n1 >> n2 >> c;
        edge[i] = {c, n1, n2};
    }
    sort(edge, edge + E);

    int cnt = 0;
    int answer = 0;
    for (int i = 0; i < E; ++i) {
        int cost, n1, n2;
        tie(cost, n1, n2) = edge[i];
        if (union_set(n1, n2)) {
            answer += cost;
            cnt++;
        }
        if (cnt == V - 1) break;
    }

    cout << answer;
    return 0;
}
