#include "bits/stdc++.h"

using namespace std;

vector<int> line;
vector<bool> isSelect;
int N, M;

void showLine() {
    for (int i = 0; i < line.size(); ++i) {
        cout << line[i] << " ";
    }
    cout << "\n";
}

void find(int depth) {
    if (depth == M) {
        showLine();
        return;
    }
    for (int i = 0; i < N; ++i) {
        if (isSelect[i+1]) continue;

        line[depth] = i + 1;
        isSelect[i+1] = true;
        find(depth + 1);
        isSelect[i+1] = false;
    }
}

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);
    cin >> N >> M;

    line = vector<int>(M);
    isSelect = vector<bool>(M+1);

    find(0);

    return 0;
}
