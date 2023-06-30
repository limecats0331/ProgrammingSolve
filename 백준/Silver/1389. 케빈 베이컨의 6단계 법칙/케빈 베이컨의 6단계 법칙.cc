#include "bits/stdc++.h"

using namespace std;
using ll = long long;
const ll INF = 1e18;

int n, m;
vector<int> graph[101];

int bfs(int start) {
    vector<bool> isVisit(101, false);
    queue<pair<int, int>> que;
    que.push({start, 0});
    isVisit[start] = true;

    int answer = 0;
    while (!que.empty()) {
        auto now = que.front();
        que.pop();
//        cout << "{" << now.first << ", " << now.second << "}\n";
        answer += now.second;

        for (int each: graph[now.first]) {
            if (isVisit[each]) continue;

            que.push({each, now.second + 1});
            isVisit[each] = true;
        }
    }

    return answer;
}

int main() {
    cin.tie(0);
    ios::sync_with_stdio(0);

    cin >> n >> m;
    for (int i = 0; i < m; ++i) {
        int p1, p2;
        cin >> p1 >> p2;

        graph[p1].push_back(p2);
        graph[p2].push_back(p1);
    }

    int minNum = 99999999;
    int minPerson = 0;

    for (int i = 1; i <= n; ++i) {
        int num =  bfs(i);

        if(num < minNum){
            minPerson = i;
            minNum = num;
        }
    }

    cout << minPerson;

    return 0;
}

