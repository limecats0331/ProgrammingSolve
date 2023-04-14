#include "bits/stdc++.h"

using namespace std;

class Sticker {
public:
    int board[11][11];
    int N, M, cnt;

    Sticker(int N, int M) {
        this->N = N;
        this->M = M;
    }

    void spin_sticker() {
        int tmp[11][11];
        for (int i = 0; i < this->N; ++i) {
            for (int j = 0; j < this->M; ++j) {
                tmp[j][N - i - 1] = this->board[i][j];
                this->board[i][j] = 0;
            }
        }
        int tmp_n = N;
        N = M;
        M = tmp_n;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                this->board[i][j] = tmp[i][j];
            }
        }
    }

    void show_sticker() {
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                cout << board[i][j];
            }
            cout << "\n";
        }
    }
};

int N, M, K;
vector<Sticker> stickers;
int laptop[41][41];

bool can_stick(Sticker s, int n, int m) {
    int remain_n = N - n;
    int remain_m = M - m;
    if (remain_n < s.N || remain_m < s.M) {
        return false;
    }

    for (int i = n; i < n + s.N; ++i) {
        for (int j = m; j < m + s.M; ++j) {
            if (laptop[i][j] == 1 && s.board[i - n][j - m] == 1) {
                return false;
            }
        }
    }
    return true;
}

void add_sticker_to_laptop(Sticker s, int n, int m) {
    for (int i = n; i < n + s.N; ++i) {
        for (int j = m; j < m + s.M; ++j) {
            if (s.board[i - n][j - m] == 1) {
                laptop[i][j] = 1;
            }
        }
    }

}

bool stick_sticker(Sticker s) {
    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < M; ++j) {
            if (can_stick(s, i, j)) {
                add_sticker_to_laptop(s, i, j);
                return true;
            }
        }
    }
    return false;
}

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> N >> M >> K;

    //스티커 입력
    for (int i = 0; i < K; ++i) {
        int sticker_n, sticker_m, s_cnt;
        cin >> sticker_n >> sticker_m;
        Sticker s = Sticker(sticker_n, sticker_m);
        for (int j = 0; j < sticker_n; ++j) {
            for (int k = 0; k < sticker_m; ++k) {
                cin >> s.board[j][k];
                s_cnt += s.board[j][k];
            }
        }
        s.cnt = s_cnt;
        stickers.push_back(s);
    }

    for (Sticker s: stickers) {
        for (int i = 0; i < 4; ++i) {
            if (stick_sticker(s)) {
                break;
            }
            s.spin_sticker();
        }
    }

    int result = 0;
    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < M; ++j) {
            result += laptop[i][j];
        }
    }
    cout << result;

    return 0;
}
