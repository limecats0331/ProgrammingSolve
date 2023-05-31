#include "bits/stdc++.h"

using namespace std;

int N, M, I;
map<int, vector<int>> graph;
bool is_visit[100001];
int order[100001];

void bfs(int start) {
    int cnt = 0;
    queue<int> que;
    que.push(start);
    is_visit[start] = true;
    order[start] = cnt++;

    while (!que.empty()) {
        int now = que.front();
        que.pop();
        order[now] = cnt++;

        sort(graph[now].begin(), graph[now].end());
        for (int each: graph[now]) {
            if (is_visit[each]) continue;

            que.push(each);
            is_visit[each] = true;
        }
    }
}

int main() {
    cin.tie(0);
    ios::sync_with_stdio(0);

    cin >> N >> M >> I;
    for (int i = 0; i < M; ++i) {
        int n1, n2;
        cin >> n1 >> n2;
        graph[n1].push_back(n2);
        graph[n2].push_back(n1);
    }

    bfs(I);

    for (int i = 1; i <= N; ++i) {
        cout << order[i] << "\n";
    }

    return 0;
}

