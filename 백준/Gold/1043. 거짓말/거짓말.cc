#include "bits/stdc++.h"

using namespace std;
using ll = long long;
const ll INF = 1e18;

int n, m;

vector<int> known;
vector<int> party[51];
int root[51];

int find_root(int num) {
    if (root[num] == num) {
        return num;
    }

    return root[num] = find_root(root[num]);
}

void set_union(int n1, int n2) {
    n1 = find_root(n1);
    n2 = find_root(n2);

    if (n1 == n2) {
        return;
    }

    root[n1] = n2;
}

int main() {
    cin.tie(0);
    ios::sync_with_stdio(0);

    cin >> n >> m;
    for (int i = 1; i <= n; ++i) {
        root[i] = i;
    }
    int cnt;
    cin >> cnt;
    if (cnt == 0) {
        cout << m;
        return 0;
    }

    for (int i = 0; i < cnt; ++i) {
        int tmp;
        cin >> tmp;
        known.push_back(tmp);
    }

    // 진실알고 있는 사람 집합 생성
    if (known.size() != 0) {
        for (int i = 1; i < known.size(); ++i) {
            set_union(known[0], known[i]);
        }
    }

    for (int i = 0; i < m; ++i) {
        cin >> cnt;
        for (int j = 0; j < cnt; ++j) {
            int tmp;
            cin >> tmp;
            party[i].push_back(tmp);
        }

        for (int j = 1; j < cnt; ++j) {
            set_union(party[i][0], party[i][j]);
        }
    }

    int answer = 0;
    for (int i = 0; i < m; ++i) {
        if(find_root(known[0]) != find_root(party[i][0])){
            answer++;
        }
    }

    cout << answer;

    return 0;
}

