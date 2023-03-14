#include "bits/stdc++.h"

using namespace std;

struct result {
    int left;
    int time;
    int height;
};

int N, M, B;
int board[502][502];

result get_time(int height) {
    result tmp = result{B, 0};
    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < M; ++j) {
            int diff = board[i][j] - height;
            tmp.left += diff;
            if (diff > 0) {
                tmp.time += diff * 2;
            } else {
                tmp.time -= diff;
            }
        }
    }

    if (tmp.left < 0) {
        return {-1, -1};
    } else {
        return tmp;
    }
}

int main() {
    ios::sync_with_stdio(false), cin.tie(nullptr);

    cin >> N >> M >> B;
    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < M; ++j) {
            cin >> board[i][j];
        }
    }

    result r = {INT32_MAX, INT32_MAX};
    for (int i = 256; i >= 0; --i) {
        auto re = get_time(i);
        if (re.time == -1) continue;

        if (re.time < r.time) {
            r.time = re.time;
            r.left = re.left;
            r.height = i;
        }
    }

    cout << r.time << " " << r.height;

    return 0;
}