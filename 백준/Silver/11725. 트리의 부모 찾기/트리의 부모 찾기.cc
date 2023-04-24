#include "bits/stdc++.h"

using namespace std;

int N;
vector<vector<int>> nodes;
bool is_visit[100001];
map<int, int> parents;

void bfs() {
    queue<int> que;
    que.push(1);
    is_visit[1] = true;

    while (!que.empty()) {
        int now = que.front();
        que.pop();

        for (int node: nodes[now]) {
            if (!is_visit[node]) {
                que.push(node);
                parents[node] = now;
                is_visit[node] = true;
            }
        }
    }
}

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> N;
    for (int i = 0; i <= N; ++i) {
        nodes.push_back(vector<int>());
    }
    for (int i = 0; i < N - 1; ++i) {
        int n1, n2;
        cin >> n1 >> n2;
        nodes[n1].push_back(n2);
        nodes[n2].push_back(n1);
    }

    bfs();

    for (int i = 2; i <= N; ++i) {
        cout << parents[i] << "\n";
    }

    return 0;
}
