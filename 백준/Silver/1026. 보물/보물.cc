#include "bits/stdc++.h"

using namespace std;

int N;
int arr1[51];
int arr2[51];

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> N;
    for (int i = 0; i < N; ++i) {
        cin >> arr1[i];
    }
    for (int i = 0; i < N; ++i) {
        cin >> arr2[i];
    }

    sort(arr1, arr1 + N);
    sort(arr2, arr2 + N, greater<int>());

    int answer = 0;
    for (int i = 0; i < N; ++i) {
        answer += arr1[i] * arr2[i];
    }

    cout << answer;

    return 0;
}
