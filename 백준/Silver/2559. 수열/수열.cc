#include "bits/stdc++.h"

using namespace std;

int N, M;
int arr[100001];

int main() {
    cin.tie(0);
    ios::sync_with_stdio(0);

    cin >> N >> M;
    cin >> arr[0];
    for (int i = 1; i < N; ++i) {
        cin >> arr[i];
        arr[i] += arr[i - 1];
    }

    int answer = arr[M-1];
    for (int i = 0; i < N - M; ++i) {
        answer = max(answer, arr[i + M] - arr[i]);
    }
    cout << answer;

    return 0;
}

