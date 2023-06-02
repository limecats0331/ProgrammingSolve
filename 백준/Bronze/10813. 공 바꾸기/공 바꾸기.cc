#include "bits/stdc++.h"

using namespace std;

int N, M;
int arr1[101];

int main() {
    cin.tie(0);
    ios::sync_with_stdio(0);

    for (int i = 0; i <= 100; ++i) {
        arr1[i] = i;
    }

    cin >> N >> M;
    for (int i = 0; i < M; ++i) {
        int n1, n2;
        cin >> n1 >> n2;
        swap(arr1[n1], arr1[n2]);
    }

    for (int i = 1; i <= N; ++i) {
        cout << arr1[i] << " ";
    }

    return 0;
}

