#include "bits/stdc++.h"

using namespace std;

int N, M;
int arr[1001];

int main() {
    cin.tie(0);
    ios::sync_with_stdio(0);

    cin >> N >> M;
    for (int i = 0; i < N; ++i) {
        cin >> arr[i];
    }
    sort(&arr[0], &arr[0] + N, greater<int>());
    
    cout << arr[M - 1];

    return 0;
}

