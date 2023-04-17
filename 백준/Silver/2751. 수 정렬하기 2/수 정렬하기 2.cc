#include "bits/stdc++.h"

using namespace std;

int N;
int arr[1000001];
int tmp[1000001];

void merge(int st, int en) {
    int mid = (st + en) / 2;
    int index_left = st;
    int index_right = mid;
    for (int i = st; i < en; ++i) {
        if (index_left == mid) { //왼쪽에서 다 꺼냈으면
            tmp[i] = arr[index_right++];
        } else if (index_right == en) { //오른쪽에서 다 꺼냈으면
            tmp[i] = arr[index_left++];
        } else if (arr[index_left] < arr[index_right]) {
            tmp[i] = arr[index_left++];
        } else {
            tmp[i] = arr[index_right++];
        }
    }

    for (int i = st; i < en; ++i) {
        arr[i] = tmp[i];
    }
}


void merge_sort(int st, int en) {
    if (st + 1 == en) {
        return;
    }

    int mid = (st + en) / 2;
    merge_sort(st, mid);
    merge_sort(mid, en);

    merge(st, en);
}

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> N;
    for (int i = 0; i < N; ++i) {
        cin >> arr[i];
    }

    merge_sort(0, N);

    for (int i = 0; i < N; ++i) {
        cout << arr[i] << "\n";
    }

    return 0;
}
