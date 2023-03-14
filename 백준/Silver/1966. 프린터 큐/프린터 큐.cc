#include "bits/stdc++.h"

using namespace std;

struct docs {
    int importance;
    bool goal;
};

int N, M;
queue<docs> Q;

int main() {
    ios::sync_with_stdio(false), cin.tie(nullptr);

    int T;
    cin >> T;
    for (int i = 0; i < T; ++i) {
        cin >> N >> M;
        Q = queue<docs>();

        int imp_max = 0;
        int importance[10];
        fill(importance, importance + 10, 0);
        for (int j = 0; j < N; ++j) {
            int imp;
            cin >> imp;

            // 중요도 저장
            imp_max = max(imp_max, imp);
            importance[imp]++;

            //목표로 하는 문서가 어딨는지 확인
            if (M == j) Q.push({imp, true});
            else Q.push({imp, false});
        }

        //프린터 로직
        int cnt = 0;
        while (true) {
            docs now = Q.front();
            //지금 문서가 제일 중요한 문서면
            if (now.importance == imp_max) {
                importance[now.importance]--;
                Q.pop();
                cnt ++;
                if (now.goal) break;
                //중요도가 가장 높은 문서가 없어지면 다음 중요도로 변경
                if (importance[now.importance] == 0) {
                    for (int j = imp_max; j > 0; --j) {
                        if (importance[j] != 0) {
                            imp_max = j;
                            break;
                        }
                    }
                }
            } else {
                Q.push(Q.front());
                Q.pop();
            }
        }
        cout << cnt << "\n";
    }


    return 0;
}