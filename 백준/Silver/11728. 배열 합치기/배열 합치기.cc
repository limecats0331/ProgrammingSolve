#include "bits/stdc++.h"

using namespace std;

int N, M;
vector<int> arr_a;
vector<int> arr_b;
int index_a, index_b;

int pick_small() {
    int out = 0;
    if (index_a == N) {
        out = arr_b[index_b];
        index_b++;
        return out;
    }
    if (index_b == M) {
        out = arr_a[index_a];
        index_a++;
        return out;
    }
    if (arr_a[index_a] < arr_b[index_b]) {
        out = arr_a[index_a];
        index_a++;
    } else {
        out = arr_b[index_b];
        index_b++;
    }
    return out;
}

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> N >> M;
    for (int i = 0; i < N; ++i) {
        int tmp;
        cin >> tmp;
        arr_a.push_back(tmp);
    }
    for (int i = 0; i < M; ++i) {
        int tmp;
        cin >> tmp;
        arr_b.push_back(tmp);
    }

    //merge
    index_a = 0;
    index_b = 0;
    vector<int> result;
    for (int i = 0; i < N + M; ++i) {
        result.push_back(pick_small());
    }

    for (int i = 0; i < N + M; ++i) {
        cout << result[i] << " ";
    }

    return 0;
}
