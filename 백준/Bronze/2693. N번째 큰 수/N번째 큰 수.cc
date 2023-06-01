#include "bits/stdc++.h"

using namespace std;

int main() {
    cin.tie(0);
    ios::sync_with_stdio(0);

    int T;
    cin >> T;
    for (int i = 0; i < T; ++i) {
        vector<int> arr;
        for (int j = 0; j < 10; ++j) {
            int tmp;
            cin >> tmp;
            arr.push_back(tmp);
        }
        sort(arr.begin(), arr.end(), greater<int>());
        
        cout << arr[2] << "\n";
    }

    return 0;
}

