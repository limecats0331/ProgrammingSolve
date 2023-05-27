#include "bits/stdc++.h"

using namespace std;

string str;
int N;
int arr[26][200001];

int main() {
    cin.tie(0);
    ios::sync_with_stdio(0);

    cin >> str;
    cin >> N;

    arr[str[0] - 'a'][0]++;
    for (int i = 0; i < 26; ++i) {
        for (int j = 1; j < str.length(); ++j) {
            arr[i][j] = arr[i][j - 1];
            if (str[j] == i + 'a') arr[i][j]++;
        }
    }

    for (int i = 0; i < N; ++i) {
        char c;
        int from, to;
        cin >> c >> from >> to;
        if (from == 0) cout << arr[c - 'a'][to] << "\n";
        else cout << arr[c - 'a'][to] - arr[c - 'a'][from - 1] << "\n";
    }

    return 0;
}

