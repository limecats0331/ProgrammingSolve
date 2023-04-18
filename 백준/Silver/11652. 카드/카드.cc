#include "bits/stdc++.h"

using namespace std;

int N;
long long arr[100001];

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> N;
    for (int i = 0; i < N; ++i) {
        cin >> arr[i];
    }
    sort(arr, arr + N);

    int cnt = 0;
    int max_cnt = 0;
    long long result = -(1ll << 62) - 1;
    for (int i = 0; i < N; ++i) {
        if (i == 0 || arr[i] == arr[i - 1]) {
            cnt++;
        } else {
            if (max_cnt < cnt) {
                max_cnt = cnt;
                result = arr[i - 1];
            }
            cnt = 1;
        }
    }
    if (max_cnt < cnt) {
        result = arr[N - 1];
    }

    cout << result;

    return 0;
}
