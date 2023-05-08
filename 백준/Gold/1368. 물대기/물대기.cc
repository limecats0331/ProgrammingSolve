#include "bits/stdc++.h"

using namespace std;

int N;
//cost, node
vector<pair<int, int>> edges[301];
//cost, node1, node2
priority_queue<tuple<int, int, int>,
        vector<tuple<int, int, int>>,
        greater<tuple<int, int, int>>> pq;
bool check[301];

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> N;
    for (int i = 0; i < N; ++i) {
        int self;
        cin >> self;
        edges[0].push_back({self, i + 1});
    }

    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < N; ++j) {
            int con;
            cin >> con;
            if (con == 0) continue;
            edges[i + 1].push_back({con, j + 1});
        }
    }

    check[0] = true;
    for (auto edge: edges[0]) {
        pq.push({edge.first, 0, edge.second});
    }

    int cnt = 0;
    int answer = 0;
    while (cnt < N) {
        int cost, now, next;
        tie(cost, now, next) = pq.top();
        pq.pop();

        if (check[next]) continue;
        check[next] = true;
        cnt++;
        answer += cost;

        for (auto each: edges[next]) {
            pq.push({each.first, next, each.second});
        }
    }

    cout << answer;

    return 0;
}
