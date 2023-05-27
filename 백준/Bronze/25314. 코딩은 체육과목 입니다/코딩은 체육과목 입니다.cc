#include "bits/stdc++.h"

using namespace std;

int N;

int main() {
    cin.tie(0);
    ios::sync_with_stdio(0);

    cin >> N;
    string answer = "";
    for (int i = 0; i < N/4; ++i) {
        answer += "long ";
    }
    answer += "int";

    cout << answer;

    return 0;
}

