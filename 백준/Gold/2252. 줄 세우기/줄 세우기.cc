#include "bits/stdc++.h"

using namespace std;

int N, M;
int conn[32001];
bool is_left[32001];
vector<vector<int>> students;


int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> N >> M;
    for (int i = 0; i <= N; ++i) {
        students.push_back(vector<int>());
    }

    for (int i = 0; i < M; ++i) {
        int a, b;
        cin >> a >> b;
        students[a].push_back(b);
        conn[b]++;
    }

    int cnt = 0;
    while (cnt != N) {
        for (int i = 1; i <= N; ++i) {
            if (!is_left[i] && conn[i] == 0) {
                cout << i << " ";
                cnt++;
                is_left[i] = true;
                for (int st: students[i]) {
                    conn[st]--;
                }
            }
        }
    }

    return 0;
}
