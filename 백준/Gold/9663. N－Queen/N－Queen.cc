#include "bits/stdc++.h"

using namespace std;

int N, cnt;
vector<bool> vert;
vector<bool> slide;
vector<bool> slide_2;

void n_queen(int depth) {
    if (depth == N) {
        cnt++;
        return;
    }
    for (int i = 0; i < N; ++i) {
        if (vert[i]) continue;
        int s_cnt = N - 1 - i + depth;
        int s_2_cnt = i + depth;
        if (slide[s_cnt]) continue;
        if (slide_2[s_2_cnt])continue;

        vert[i] = true;
        slide[s_cnt] = true;
        slide_2[s_2_cnt] = true;
        n_queen(depth + 1);
        vert[i] = false;
        slide[s_cnt] = false;
        slide_2[s_2_cnt] = false;
    }
}

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> N;

    vert = vector<bool>(N);
    slide = vector<bool>(2 * N - 1);
    slide_2 = vector<bool>(2 * N - 1);

    n_queen(0);

    cout << cnt;

    return 0;
}
